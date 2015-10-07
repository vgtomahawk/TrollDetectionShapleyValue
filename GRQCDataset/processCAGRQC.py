import igraph
import random

def readGraphFromFile(fileName="CA-GrQc.txt"):
	g=igraph.Graph()
	vertexId=0
	vertexMap={}
	vertexReverseMap={}
	for line in open(fileName):
		words=line.split()
		src=int(words[0])
		dest=int(words[1])
		if src not in vertexMap:
			vertexMap[src]=vertexId
			vertexReverseMap[vertexId]=src
			vertexId+=1
		if dest not in vertexMap:
			vertexMap[dest]=vertexId
			vertexReverseMap[vertexId]=dest
			vertexId+=1
	
	g.add_vertices(vertexId)

	for line in open(fileName):
		words=line.split()
		src=int(words[0])
		dest=int(words[1])
		g.add_edge(vertexMap[src],vertexMap[dest],weight=1.0)

	return g,vertexMap,vertexReverseMap

def getPageRank(g):
	return g.pagerank(weights="weight",damping=0.9)

def generatePermutation(N):
	Nperm=[i for i in range(N)]
	random.shuffle(Nperm)
	return Nperm

def generateStringHash(perm):
	perm.sort()
	permStrings=[str(p) for p in perm]
	return ",".join(permStrings)

def setPageRank(g,listOfVertices):
	minVertex=min(listOfVertices.keys())
	#Weight from supervertex to each vertex outside
	totalWeights={}
	#for v in listOfVertices:
	#	for e in g.incident(v):
	#		print g.es[e].source
	#		print g.es[e].dest
	for u in listOfVertices.keys():
		for v in g.neighbors(u):
			#Ignore internal edges
			if v in listOfVertices:
				continue
			if v not in totalWeights:
				totalWeights[v]=0.0				
			totalWeights[v]=totalWeights[v]+1.0

	severedEdges={}
	#Sever all edges from any vertex in listOfVertices
	for u in listOfVertices.keys():
		for v in g.neighbors(u):
			g.delete_edges(g.get_eid(u,v))
			severedEdges[str(u)+","+str(v)]=1

	#Add a new weighted edge to each outside vertex from the new "super-vertex"
	for v in totalWeights.keys():
		g.add_edge(minVertex,v,weight=totalWeights[v])
	
	resetVector=[]
	for i in range(g.vcount()):
		resetVector.append(1.0)

	for i in listOfVertices.keys():
		resetVector[i]=0.0

	resetVector[minVertex]=len(listOfVertices.keys())
	resetVectorSum=sum(resetVector)
	
	for i in range(g.vcount()):
		resetVector[i]=resetVector[i]/resetVectorSum

	#Find the required "set" pageRank 
	returnValue=g.personalized_pagerank(weights="weight",reset=resetVector,damping=0.9)[minVertex]
	
	#Remove the new weighted edges you added
	for v in g.neighbors(minVertex):
		g.delete_edges(g.get_eid(minVertex,v))

	#Restore the severed edges
	for edgeKey in severedEdges.keys():
		words=edgeKey.split(",")
		src=int(words[0])
		dest=int(words[1])
		g.add_edge(src,dest,weight=1.0)

	return returnValue
	
g,vertexMap,vertexReverseMap=readGraphFromFile("karate/karate.out")

pageRanks=getPageRank(g)
#listRanks=getPageRank(g)
#print generatePermutation(70)
#print generateStringHash(generatePermutation(70))
#print setPageRank(g,{0:True,3:True,4:True})
#print setPageRank(g,{0:True,3:True})
#print setPageRank(g,{0:True,3:True,4:True})
#print getPageRank(g)[0]
#print getPageRank(g)[3]
#print getPageRank(g)[4]
setValueTable={}
marginalGainTable={}
occurrenceTable={}
sampleComplexity=10**4

normalize=True
for v in range(g.vcount()):
	occurrenceTable[v]=0.0
	marginalGainTable[v]=0.0

for sample in range(sampleComplexity):
	print sample
	permutation=generatePermutation(g.vcount())
	runningSet=[]
	runningValue=0.0
	for element in permutation:
		runningSet.append(element)
		setHash=generateStringHash(runningSet)
		newRunningValue=0.0
		if setHash in setValueTable:
			newRunningValue=setValueTable[setHash]
		else:
			setHashMap={}
			for elem in runningSet:
				setHashMap[elem]=True
			newRunningValue=setPageRank(g,setHashMap)
			setValueTable[setHash]=newRunningValue
		marginalGain=0.0	
		if not normalize:	
			marginalGain=newRunningValue-runningValue
			runningValue=newRunningValue
		else:
			marginalGain=newRunningValue/len(runningSet)-runningValue
			runningValue=newRunningValue/len(runningSet)
		marginalGainTable[element]=marginalGainTable[element]+marginalGain
		occurrenceTable[element]=occurrenceTable[element]+1.0

for v in range(g.vcount()):
	marginalGainTable[v]=marginalGainTable[v]/occurrenceTable[v]

marginalGainFile=open("ShapleyRankNormalized.txt","w")
pageRankFile=open("PageRank.txt","w")

pageRankTuples=[(vertexReverseMap[v],pageRanks[v]) for v in range(g.vcount())]
marginalGainTuples=[(vertexReverseMap[v],marginalGainTable[v]) for v in range(g.vcount())]

pageRankTuples.sort(key=lambda x:x[1])
marginalGainTuples.sort(key=lambda x:x[1])

for tup in pageRankTuples:
	pageRankFile.write(str(tup[0])+" "+str(tup[1])+"\n")
for tup in marginalGainTuples:
	marginalGainFile.write(str(tup[0])+" "+str(tup[1])+"\n")
