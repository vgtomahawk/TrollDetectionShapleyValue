index=0
src=None
dest=None
outFile=open("karate.out","w")

for line in open("karate.in"):
	if index%5==2:
		words=line.split()
		src=int(words[-1])
		index+=1
	elif index%5==3:
		words=line.split()
		dest=int(words[-1])
		outFile.write(str(src)+" "+str(dest)+"\n")
		index+=1
	else:
		index+=1
