import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.io.*;
import java.util.*;

public class GraphClass 
{
	//a trusts set of all nodes b
	HashMap<Integer,HashSet<Integer>> trustMap;
	
	//a is trusted by set of all nodes b
	HashMap<Integer,HashSet<Integer>> trustedMap;
	
	//a distrusts by set of all nodes b
	HashMap<Integer,HashSet<Integer>> distrustMap;
	
	//a is distrusted by set of all nodes b
	HashMap<Integer,HashSet<Integer>> distrustedMap;
	
	//Set of all trolls
	HashSet<Integer> trollSet;
	
	//Vertex Ids
	HashSet<Integer> vertexIds;
	
	//a,b,trust/distrust in string form
	HashSet<String> edges;
	
	HashMap<Long,Long> factorialTable;
	HashMap<String,Long> pascalTable;
	
	public GraphClass()
	{
		trustMap=new HashMap<Integer,HashSet<Integer>>();
		trustedMap=new HashMap<Integer,HashSet<Integer>>();
		distrustMap=new HashMap<Integer,HashSet<Integer>>();
		distrustedMap=new HashMap<Integer,HashSet<Integer>>();
		trollSet=new HashSet<Integer>();
		vertexIds=new HashSet<Integer>();
		edges=new HashSet<String>();
		factorialTable=new HashMap<Long,Long>();
		pascalTable=new HashMap<String,Long>();
		factorialTable.put(0l,1l);
	}
	
	public long getFactorial(long n)
	{
		if(n<0)
		{
			System.out.println("Invalid Value");
		}
		
		if(n==0)
		{
			return 1;
		}
		
		else if(factorialTable.containsKey(n))
		{
			return factorialTable.get(n);
		}
		
		else
		{
			factorialTable.put(n,n*getFactorial(n-1));
			return factorialTable.get(n);
		}
		
	}
	
	public long getPascalCoefficient(long n,long r)
	{
		String pascalKey=n+","+r;
		if(pascalTable.containsKey(pascalKey))
		{
			return pascalTable.get(pascalKey);
		}	
		long pascalValue = (getFactorial(n))/(getFactorial(r)*getFactorial(n-r));
		pascalTable.put(pascalKey,pascalValue);
		return pascalValue;
	}
	
	public void addVertex(int a)
	{
		vertexIds.add(a);
		trustMap.put(a,new HashSet<Integer>());
		trustedMap.put(a,new HashSet<Integer>());
		distrustMap.put(a,new HashSet<Integer>());
		distrustedMap.put(a,new HashSet<Integer>());
	}
	
	public void addTrustEdge(int a,int b)
	{
		if(!vertexIds.contains(a))
		{
			addVertex(a);
		}
		
		if(!vertexIds.contains(b))
		{
			addVertex(b);
		}
		
		//a trusts b, so
		trustMap.get(a).add(b);
		//b is trusted by a
		trustedMap.get(b).add(a);
		
		String edgeId=a+","+b+","+"+";
		edges.add(edgeId);
	}
	
	public void addDistrustEdge(int a,int b)
	{
		if(!vertexIds.contains(a))
		{
			addVertex(a);
		}
		
		if(!vertexIds.contains(b))
		{
			addVertex(b);
		}
		
		//a trusts b, so
		distrustMap.get(a).add(b);
		//b is trusted by a
		distrustedMap.get(b).add(a);
		
		String edgeId=a+","+b+","+"-";
		edges.add(edgeId);
	}
	
	public void deleteNode(int a)
	{
		for(int outNeighbor:this.getPositiveOutNeighbors(a))
		{
			this.trustedMap.get(outNeighbor).remove(a);
			this.trustMap.get(a).remove(outNeighbor);
			this.edges.remove(a+","+outNeighbor+"+");
		}
		
		for(int outNeighbor:this.getNegativeOutNeighbors(a))
		{
			this.distrustedMap.get(outNeighbor).remove(a);
			this.distrustMap.get(a).remove(outNeighbor);
			this.edges.remove(a+","+outNeighbor+"-");
		}
		
		for(int inNeighbor:this.getPositiveInNeighbors(a))
		{
			this.trustMap.get(inNeighbor).remove(a);
			this.trustedMap.get(a).remove(inNeighbor);
		}
		
		for(int inNeighbor:this.getNegativeInNeighbors(a))
		{
			this.distrustMap.get(inNeighbor).remove(a);
			this.distrustedMap.get(a).remove(inNeighbor);
		}
		
		this.trustMap.remove(a);
		this.distrustMap.remove(a);
		this.trustedMap.remove(a);
		this.distrustedMap.remove(a);
		this.vertexIds.remove(a);
		if(trollSet.contains(a))
		{
			this.trollSet.remove(a);
		}
		
		return;
	}
	
	public void addTroll(int a)
	{
		trollSet.add(a);
	}
	
	public HashSet<Integer> getVertices()
	{
		return new HashSet<Integer>(vertexIds);
	}
	
	public HashSet<String> getEdges()
	{
		return new HashSet<String>(edges);
	}
	
	public HashSet<Integer> getPositiveOutNeighbors(int a)
	{
		return new HashSet<Integer>(trustMap.get(a));
	}
	
	public HashSet<Integer> getPositiveInNeighbors(int a)
	{
		return new HashSet<Integer>(trustedMap.get(a));
	}
	
	public HashSet<Integer> getNegativeOutNeighbors(int a)
	{
		return new HashSet<Integer>(distrustMap.get(a));
	}
	
	public HashSet<Integer> getNegativeInNeighbors(int a)
	{
		return new HashSet<Integer>(distrustedMap.get(a));
	}
	
	public int getPositiveOutDegree(int a)
	{
		return trustMap.get(a).size();
	}
	
	public int getPositiveInDegree(int a)
	{
		return trustedMap.get(a).size();
	}
	
	public int getNegativeOutDegree(int a)
	{
		return distrustMap.get(a).size();
	}
	
	public int getNegativeInDegree(int a)
	{
		return distrustedMap.get(a).size();
	}
	
	public int returnVertexCount()
	{
		return vertexIds.size();
	}
	
	public int returnEdgeCount()
	{
		return edges.size();
	}
	
	public ArrayList<Integer> generatePermutation(int T)
	{
		
		ArrayList<Integer> sample=new ArrayList<Integer>();
		HashSet<Integer> vertexSet= new HashSet<Integer>(this.vertexIds);
		for(int i=0;i<T;i++)
		{
			Random rand=new Random();
			ArrayList<Integer> remainingNodes = new ArrayList(vertexSet);
			int newId=remainingNodes.get(rand.nextInt(remainingNodes.size()));
			sample.add(newId);
			vertexSet.remove(newId);
		}
		return sample;
	}

	public ArrayList<Integer> generateTPermutation(int T)
	{
		
		ArrayList<Integer> sample=new ArrayList<Integer>();
		HashSet<Integer> vertexSet= new HashSet<Integer>();
		for(int i=0;i<T;i++)
		{
			vertexSet.add(i);
		}
		for(int i=0;i<T;i++)
		{
			Random rand=new Random();
			ArrayList<Integer> remainingNodes = new ArrayList(vertexSet);
			int newId=remainingNodes.get(rand.nextInt(remainingNodes.size()));
			sample.add(newId);
			vertexSet.remove(newId);
		}
		return sample;
	}
	
	public void loadSlashdot() throws IOException
	{
		// Open the edgesFile
		FileInputStream edgeStream = new FileInputStream("SlashDotZooDataset/out.matrix.clean");
		BufferedReader edgeBr = new BufferedReader(new InputStreamReader(edgeStream));

		String edgeLine;
		//Read File Line By Line
		while ((edgeLine = edgeBr.readLine()) != null)   
		{
			String[] words = edgeLine.split(" ");
			int src=Integer.parseInt(words[0]);
			int dest=Integer.parseInt(words[1]);
			String interactionType=words[2];
			
			if(interactionType.contains("+")&&(src!=dest))
			{
				addTrustEdge(src,dest);
			}
			else if((interactionType.contains("-"))&&(src!=dest))
			{
				addDistrustEdge(src,dest);
			}
			else
			{
				//Do nothing, albeit print warnings.
				System.out.println("Invalid interaction");
				System.out.println(edgeLine);
			}
		}

		//Close the input stream
		edgeBr.close();
		
		//Now read in the troll ids
		FileInputStream trollStream = new FileInputStream("SlashDotZooDataset/out.trolls");
		BufferedReader trollBr = new BufferedReader(new InputStreamReader(trollStream));
		
		String trollLine;
		
		//Read File Line By Line
		while ((trollLine = trollBr.readLine()) != null)
		{
			String[] trollWords = trollLine.split(" ");
			trollSet.add(Integer.parseInt(trollWords[0]));
		}
		
		trollBr.close();
	}
	
	public ArrayList<Integer> getRankList(HashMap<Integer,Double> map)
	{
		List list = new LinkedList(map.entrySet());
		Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
               return ((Comparable) ((Map.Entry) (o1)).getValue())
                  .compareTo(((Map.Entry) (o2)).getValue());
            }
       });
	   
		ArrayList<Integer> rankList = new ArrayList<Integer>();
		for(Object o:list)
		{
			Integer a = (Integer) (((Map.Entry)(o)).getKey());
			rankList.add(a);
		}
		
		return rankList;
	}

	public ArrayList<Integer> generateFMFRanking() 
	{
		HashMap<Integer,Double> FMFMap = new HashMap<Integer,Double>();
		
		for(int v:this.getVertices())
		{
		   double FMFValue=this.getPositiveInDegree(v)-this.getNegativeInDegree(v);
		   FMFMap.put(v,FMFValue);
		}
		
		ArrayList<Integer> FMFRanking = this.getRankList(FMFMap);
		return FMFRanking;
	}

	public ArrayList<Integer> generateBetaFMFRanking(double beta) 
	{
		HashMap<Integer,Double> FMFMap = new HashMap<Integer,Double>();
		
		for(int v:this.getVertices())
		{
		   double FMFValue=beta*this.getPositiveInDegree(v)-(1-beta)*this.getNegativeInDegree(v);
		   FMFMap.put(v,FMFValue);
		}
		
		ArrayList<Integer> FMFRanking = this.getRankList(FMFMap);
		return FMFRanking;
	}
	
	public ArrayList<Integer> generateNetTrustRanking() 
	{
		HashMap<Integer,Double> FMFMap = new HashMap<Integer,Double>();
		
		for(int v:this.getVertices())
		{
		   double FMFValue=this.getPositiveInDegree(v)-this.getNegativeInDegree(v)+this.getNegativeOutDegree(v)-this.getPositiveOutDegree(v);
		   FMFMap.put(v,FMFValue);
		}
		
		ArrayList<Integer> FMFRanking = this.getRankList(FMFMap);
		return FMFRanking;
	}
	
	public ArrayList<Integer> generateNTVRanking(int NUMBER_OF_SAMPLES,int PERM_SIZE) 
	{
		HashMap<Integer,Double> NTVMap = new HashMap<Integer,Double>();
		HashMap<Integer,Integer> trials = new HashMap<Integer,Integer>();
		
		for(int v_i:this.getVertices())
		{
			NTVMap.put(v_i,0.0);
			trials.put(v_i,0);
		}
		
		for(int sample=0;sample<NUMBER_OF_SAMPLES;sample++)
		{
			if(sample%100==0)
			{
				System.out.println(sample);
			}
			ArrayList<Integer> permutation=this.generatePermutation(PERM_SIZE);
			ArrayList<Integer> runningSet= new ArrayList<Integer>();
			double runningValue=0.0;
			for(int k=0;k<permutation.size();k++)
			{
				int newElement=permutation.get(k);
				runningSet.add(newElement);
				int positiveInEdges=0;
				int negativeInEdges=0;
				
				for(int v_i:runningSet)
				{
					for(int v_j:this.getPositiveInNeighbors(v_i))
					{
						if(!runningSet.contains(v_j))
						{
							positiveInEdges+=1;
						}
					}
					
					for(int v_j:this.getNegativeInNeighbors(v_i))
					{
						if(!runningSet.contains(v_j))
						{
							negativeInEdges+=1;
						}
					}
				}
				
				double newValue=(positiveInEdges-negativeInEdges+0.0)/(runningSet.size()+0.0);
				double marginalGain=newValue-runningValue;
				runningValue=newValue;
				NTVMap.put(newElement,NTVMap.get(newElement)+marginalGain);
				trials.put(newElement,trials.get(newElement)+1);
			}
		}
	
		for(int v_i:NTVMap.keySet())
		{
			NTVMap.put(v_i,NTVMap.get(v_i)/trials.get(v_i));
		}
		
		ArrayList<Integer> NTVRanking = this.getRankList(NTVMap);
		return NTVRanking;
	}

	public ArrayList<Integer> generateNAVRanking(int NUMBER_OF_SAMPLES,int PERM_SIZE) 
	{
		HashMap<Integer,Double> NTVMap = new HashMap<Integer,Double>();
		HashMap<Integer,Integer> trials = new HashMap<Integer,Integer>();
		
		for(int v_i:this.getVertices())
		{
			NTVMap.put(v_i,0.0);
			trials.put(v_i,0);
		}
		
		for(int sample=0;sample<NUMBER_OF_SAMPLES;sample++)
		{
			if(sample%100==0)
			{
				System.out.println(sample);
			}
			ArrayList<Integer> permutation=this.generatePermutation(PERM_SIZE);
			ArrayList<Integer> runningSet= new ArrayList<Integer>();
			double runningValue=0.0;
			for(int k=0;k<permutation.size();k++)
			{
				int newElement=permutation.get(k);
				runningSet.add(newElement);
				HashSet<Integer> positiveInNeighbors=new HashSet<Integer>();
				HashSet<Integer> negativeInNeighbors=new HashSet<Integer>();
				
				for(int v_i:runningSet)
				{
					for(int v_j:this.getPositiveInNeighbors(v_i))
					{
						if(!runningSet.contains(v_j))
						{
							positiveInNeighbors.add(v_j);
						}
					}
					
					for(int v_j:this.getNegativeInNeighbors(v_i))
					{
						if(!runningSet.contains(v_j))
						{
							negativeInNeighbors.add(v_j);
						}
					}
				}
				
				double newValue=(-negativeInNeighbors.size()+0.0)/(Math.sqrt(runningSet.size())+0.0);
				double marginalGain=newValue-runningValue;
				runningValue=newValue;
				NTVMap.put(newElement,NTVMap.get(newElement)+marginalGain);
				trials.put(newElement,trials.get(newElement)+1);
			}
		}
	
		for(int v_i:NTVMap.keySet())
		{
			NTVMap.put(v_i,NTVMap.get(v_i)/trials.get(v_i));
		}
		
		ArrayList<Integer> NTVRanking = this.getRankList(NTVMap);
		return NTVRanking;
	}
	
	public ArrayList<Integer> generatePDCRanking() 
	{
		HashMap<Integer,Double> PDCMap = new HashMap<Integer,Double>();
		
		for(int v:this.getVertices())
		{
		   double PDCValue=this.getPositiveInDegree(v);
		   PDCMap.put(v,PDCValue);
		}
		
		ArrayList<Integer> PDCRanking = this.getRankList(PDCMap);
		return PDCRanking;
	}
	
	public ArrayList<Integer> generateNNDCRanking() 
	{
		HashMap<Integer,Double> NNDCMap = new HashMap<Integer,Double>();
		
		for(int v:this.getVertices())
		{
		   double NNDCValue=-this.getNegativeInDegree(v);
		   NNDCMap.put(v,NNDCValue);
		}
		
		ArrayList<Integer> NNDCRanking = this.getRankList(NNDCMap);
		return NNDCRanking;
	}
	
	public ArrayList<Integer> generateNPFRanking()
	{
		HashMap<Integer,Double> NPFMap = new HashMap<Integer,Double>();
		
		for(int v_i:this.getVertices())
		{
		   double NPFValue=0.0;
		   
		   NPFValue+=(1.0)/(this.getPositiveOutDegree(v_i)+1);
		   for(int v_j:this.getPositiveInNeighbors(v_i))
		   {
			   NPFValue+=(1.0)/(this.getPositiveOutDegree(v_j)+1.0);
		   }
		   
		   for(int v_j:this.getNegativeInNeighbors(v_i))
		   {
			   NPFValue+=(-1.0)/(this.getNegativeOutDegree(v_j)+0.0);
		   }
		   
		   NPFMap.put(v_i,NPFValue);
		}
		
		ArrayList<Integer> NPFRanking = this.getRankList(NPFMap);
		return NPFRanking;
	}

	public ArrayList<Integer> generateBetaNPFRanking(double beta)
	{
		HashMap<Integer,Double> NPFMap = new HashMap<Integer,Double>();
		
		for(int v_i:this.getVertices())
		{
		   double NPFValue=0.0;
		   
		   NPFValue+=(beta)/(this.getPositiveOutDegree(v_i)+1);
		   for(int v_j:this.getPositiveInNeighbors(v_i))
		   {
			   NPFValue+=(beta)/(this.getPositiveOutDegree(v_j)+1.0);
		   }
		   
		   for(int v_j:this.getNegativeInNeighbors(v_i))
		   {
			   NPFValue+=(-(1-beta))/(this.getNegativeOutDegree(v_j)+0.0);
		   }
		   
		   NPFMap.put(v_i,NPFValue);
		}
		
		ArrayList<Integer> NPFRanking = this.getRankList(NPFMap);
		return NPFRanking;
	}
	public ArrayList<Integer> generateTwoHopNPFRanking()
	{
		HashMap<Integer,Double> NPFMap = new HashMap<Integer,Double>();
		HashMap<Integer,Integer> twoHopPositiveOutDegrees = new HashMap<Integer,Integer>();
		HashMap<Integer,Integer> twoHopNegativeOutDegrees = new HashMap<Integer,Integer>();
		
		for(int v_i:this.getVertices())
		{
			HashSet<Integer> twoHopPositiveOutNeighborsVI=new HashSet<Integer>();
			HashSet<Integer> twoHopNegativeOutNeighborsVI=new HashSet<Integer>();
			
			for(int v_j:this.getPositiveOutNeighbors(v_i))
			{
				twoHopPositiveOutNeighborsVI.add(v_j);
				for(int v_k:this.getPositiveOutNeighbors(v_j))
				{
					if((v_k!=v_i)&&(!twoHopPositiveOutNeighborsVI.contains(v_k)))
					{
						twoHopPositiveOutNeighborsVI.add(v_k);
					}
				}
				
				for(int v_k:this.getNegativeOutNeighbors(v_j))
				{
					if((v_k!=v_i)&&(!twoHopNegativeOutNeighborsVI.contains(v_k)))
					{
						twoHopNegativeOutNeighborsVI.add(v_k);
					}
				}
			}
			
			for(int v_j:this.getNegativeOutNeighbors(v_i))
			{
				twoHopNegativeOutNeighborsVI.add(v_j);
				for(int v_k:this.getPositiveOutNeighbors(v_j))
				{
					if((v_k!=v_i)&&(!twoHopNegativeOutNeighborsVI.contains(v_k)))
					{
						twoHopNegativeOutNeighborsVI.add(v_k);
					}
				}
				
				for(int v_k:this.getNegativeOutNeighbors(v_j))
				{
					if((v_k!=v_i)&&(!twoHopPositiveOutNeighborsVI.contains(v_k)))
					{
						twoHopPositiveOutNeighborsVI.add(v_k);
					}
				}
			}
			twoHopPositiveOutDegrees.put(v_i,twoHopPositiveOutNeighborsVI.size());
			twoHopNegativeOutDegrees.put(v_i,twoHopNegativeOutNeighborsVI.size());
		}
		
		
		for(int v_i:this.getVertices())
		{
		   double NPFValue=0.0;
		   
		   NPFValue+=(1.0)/(twoHopPositiveOutDegrees.get(v_i)+1);
		   
		   HashSet<Integer> twoHopPositiveInNeighborsVI=new HashSet<Integer>();
		   HashSet<Integer> twoHopNegativeInNeighborsVI=new HashSet<Integer>();

			for(int v_j:this.getPositiveInNeighbors(v_i))
			{
				twoHopPositiveInNeighborsVI.add(v_j);
				for(int v_k:this.getPositiveInNeighbors(v_j))
				{
					if((v_k!=v_i)&&(!twoHopPositiveInNeighborsVI.contains(v_k)))
					{
						twoHopPositiveInNeighborsVI.add(v_k);
					}
				}
				
				for(int v_k:this.getNegativeInNeighbors(v_j))
				{
					if((v_k!=v_i)&&(!twoHopNegativeInNeighborsVI.contains(v_k)))
					{
						twoHopNegativeInNeighborsVI.add(v_k);
					}
				}
			}
			
			for(int v_j:this.getNegativeInNeighbors(v_i))
			{
				twoHopNegativeInNeighborsVI.add(v_j);
				for(int v_k:this.getPositiveInNeighbors(v_j))
				{
					if((v_k!=v_i)&&(!twoHopNegativeInNeighborsVI.contains(v_k)))
					{
						twoHopNegativeInNeighborsVI.add(v_k);
					}
				}
				
				for(int v_k:this.getNegativeInNeighbors(v_j))
				{
					if((v_k!=v_i)&&(!twoHopPositiveInNeighborsVI.contains(v_k)))
					{
						twoHopPositiveInNeighborsVI.add(v_k);
					}
				}
			}

			for(int v_j:twoHopPositiveInNeighborsVI)
			{
				NPFValue+=(1.0)/(twoHopPositiveOutDegrees.get(v_j)+1.0);
			}
			   
			for(int v_j:twoHopNegativeInNeighborsVI)
			{
				NPFValue+=(-1.0)/(twoHopNegativeOutDegrees.get(v_j)+0.0);
			}
		   
		   NPFMap.put(v_i,NPFValue);
		}
		
		ArrayList<Integer> NPFRanking = this.getRankList(NPFMap);
		return NPFRanking;
	}

	public ArrayList<Integer> generatekHopNPFRanking(int k)
	{
		HashMap<Integer,Double> NPFMap = new HashMap<Integer,Double>();
		HashMap<Integer,Integer> kHopPositiveOutDegrees = new HashMap<Integer,Integer>();
		HashMap<Integer,Integer> kHopNegativeOutDegrees = new HashMap<Integer,Integer>();
		
		for(int v_i:this.getVertices())
		{
			//System.out.println(v_i);
			HashSet<Integer> kHopPositiveOutNeighborsVI=new HashSet<Integer>();
			HashSet<Integer> kHopNegativeOutNeighborsVI=new HashSet<Integer>();
			kHopPositiveOutNeighborsVI.addAll(this.getPositiveOutNeighbors(v_i));
			kHopNegativeOutNeighborsVI.addAll(this.getNegativeOutNeighbors(v_i));
			
			for(int hop=2;hop<=k;hop++)
			{
				HashSet<Integer> nextHopPositiveOutNeighborsVI=new HashSet<Integer>();
				HashSet<Integer> nextHopNegativeOutNeighborsVI=new HashSet<Integer>();

				for(int v_j:kHopPositiveOutNeighborsVI)
				{
					for(int v_k:this.getPositiveOutNeighbors(v_j))
					{
						if((v_k!=v_i)&&(!kHopPositiveOutNeighborsVI.contains(v_k)))
						{
							nextHopPositiveOutNeighborsVI.add(v_k);
						}
					}
					
					for(int v_k:this.getNegativeOutNeighbors(v_j))
					{
						if((v_k!=v_i)&&(!kHopNegativeOutNeighborsVI.contains(v_k)))
						{
							nextHopNegativeOutNeighborsVI.add(v_k);
						}
					}
				}
				
				for(int v_j:kHopNegativeOutNeighborsVI)
				{
					for(int v_k:this.getPositiveOutNeighbors(v_j))
					{
						if((v_k!=v_i)&&(!kHopNegativeOutNeighborsVI.contains(v_k)))
						{
							nextHopNegativeOutNeighborsVI.add(v_k);
						}
					}
					
					for(int v_k:this.getNegativeOutNeighbors(v_j))
					{
						if((v_k!=v_i)&&(!kHopPositiveOutNeighborsVI.contains(v_k)))
						{
							nextHopPositiveOutNeighborsVI.add(v_k);
						}
					}
				}
				kHopPositiveOutNeighborsVI.addAll(nextHopPositiveOutNeighborsVI);
				kHopNegativeOutNeighborsVI.addAll(nextHopNegativeOutNeighborsVI);
			}
			
			kHopPositiveOutDegrees.put(v_i,kHopPositiveOutNeighborsVI.size());
			kHopNegativeOutDegrees.put(v_i,kHopNegativeOutNeighborsVI.size());
		}
		
		
		for(int v_i:this.getVertices())
		{
		   double NPFValue=0.0;
		   //System.out.println(v_i);
		   NPFValue+=(1.0)/(kHopPositiveOutDegrees.get(v_i)+1);
		   HashSet<Integer> kHopPositiveInNeighborsVI=new HashSet<Integer>();
		   HashSet<Integer> kHopNegativeInNeighborsVI=new HashSet<Integer>();
		   kHopPositiveInNeighborsVI.addAll(this.getPositiveInNeighbors(v_i));
		   kHopNegativeInNeighborsVI.addAll(this.getNegativeInNeighbors(v_i));
		   for(int hop=2;hop<=k;hop++)
		   {
			   HashSet<Integer> nextHopPositiveInNeighborsVI=new HashSet<Integer>();
			   HashSet<Integer> nextHopNegativeInNeighborsVI=new HashSet<Integer>();

				for(int v_j:kHopPositiveInNeighborsVI)
				{
					for(int v_k:this.getPositiveInNeighbors(v_j))
					{
						if((v_k!=v_i)&&(!kHopPositiveInNeighborsVI.contains(v_k)))
						{
							nextHopPositiveInNeighborsVI.add(v_k);
						}
					}
					
					for(int v_k:this.getNegativeInNeighbors(v_j))
					{
						if((v_k!=v_i)&&(!kHopNegativeInNeighborsVI.contains(v_k)))
						{
							nextHopNegativeInNeighborsVI.add(v_k);
						}
					}
				}
				
				for(int v_j:kHopNegativeInNeighborsVI)
				{
					for(int v_k:this.getPositiveInNeighbors(v_j))
					{
						if((v_k!=v_i)&&(!kHopNegativeInNeighborsVI.contains(v_k)))
						{
							nextHopNegativeInNeighborsVI.add(v_k);
						}
					}
					
					for(int v_k:this.getNegativeInNeighbors(v_j))
					{
						if((v_k!=v_i)&&(!kHopPositiveInNeighborsVI.contains(v_k)))
						{
							nextHopPositiveInNeighborsVI.add(v_k);
						}
					}
				}
				kHopPositiveInNeighborsVI.addAll(nextHopPositiveInNeighborsVI);
				kHopNegativeInNeighborsVI.addAll(nextHopNegativeInNeighborsVI);
			}
		   
			for(int v_j:kHopPositiveInNeighborsVI)
			{
				NPFValue+=(1.0)/(kHopPositiveOutDegrees.get(v_j)+1.0);
			}
			   
			for(int v_j:kHopNegativeInNeighborsVI)
			{
				NPFValue+=(-1.0)/(kHopNegativeOutDegrees.get(v_j)+0.0);
			}
		   
		   NPFMap.put(v_i,NPFValue);
		}
		
		ArrayList<Integer> NPFRanking = this.getRankList(NPFMap);
		return NPFRanking;
	}

	public ArrayList<Integer> generatekHopFATRanking(int k)
	{
		HashMap<Integer,Double> FATMap = new HashMap<Integer,Double>();
		HashMap<Integer,Integer> kHopPositiveOutDegrees = new HashMap<Integer,Integer>();
		HashMap<Integer,Integer> kHopNegativeOutDegrees = new HashMap<Integer,Integer>();
		
		for(int v_i:this.getVertices())
		{
			//System.out.println(v_i);
			HashSet<Integer> kHopPositiveOutNeighborsVI=new HashSet<Integer>();
			HashSet<Integer> kHopNegativeOutNeighborsVI=new HashSet<Integer>();
			kHopPositiveOutNeighborsVI.addAll(this.getPositiveOutNeighbors(v_i));
			kHopNegativeOutNeighborsVI.addAll(this.getNegativeOutNeighbors(v_i));
			
			for(int hop=2;hop<=k;hop++)
			{
				HashSet<Integer> nextHopPositiveOutNeighborsVI=new HashSet<Integer>();
				HashSet<Integer> nextHopNegativeOutNeighborsVI=new HashSet<Integer>();

				for(int v_j:kHopPositiveOutNeighborsVI)
				{
					for(int v_k:this.getPositiveOutNeighbors(v_j))
					{
						if((v_k!=v_i)&&(!kHopPositiveOutNeighborsVI.contains(v_k)))
						{
							nextHopPositiveOutNeighborsVI.add(v_k);
						}
					}
					
					for(int v_k:this.getNegativeOutNeighbors(v_j))
					{
						if((v_k!=v_i)&&(!kHopNegativeOutNeighborsVI.contains(v_k)))
						{
							nextHopNegativeOutNeighborsVI.add(v_k);
						}
					}
				}
				
				for(int v_j:kHopNegativeOutNeighborsVI)
				{
					for(int v_k:this.getPositiveOutNeighbors(v_j))
					{
						if((v_k!=v_i)&&(!kHopNegativeOutNeighborsVI.contains(v_k)))
						{
							nextHopNegativeOutNeighborsVI.add(v_k);
						}
					}
					
					for(int v_k:this.getNegativeOutNeighbors(v_j))
					{
						if((v_k!=v_i)&&(!kHopPositiveOutNeighborsVI.contains(v_k)))
						{
							nextHopPositiveOutNeighborsVI.add(v_k);
						}
					}
				}
				kHopPositiveOutNeighborsVI.addAll(nextHopPositiveOutNeighborsVI);
				kHopNegativeOutNeighborsVI.addAll(nextHopNegativeOutNeighborsVI);
			}
			
			kHopPositiveOutNeighborsVI.removeAll(kHopNegativeOutNeighborsVI);
			
			kHopPositiveOutDegrees.put(v_i,kHopPositiveOutNeighborsVI.size());
			kHopNegativeOutDegrees.put(v_i,kHopNegativeOutNeighborsVI.size());
		}
		
		
		for(int v_i:this.getVertices())
		{
		   double FATValue=0.0;
		   //System.out.println(v_i);
		   
		   HashSet<Integer> kHopPositiveInNeighborsVI=new HashSet<Integer>();
		   HashSet<Integer> kHopNegativeInNeighborsVI=new HashSet<Integer>();
		   
		   kHopPositiveInNeighborsVI.addAll(this.getPositiveInNeighbors(v_i));
		   kHopNegativeInNeighborsVI.addAll(this.getNegativeInNeighbors(v_i));
		   
		   for(int hop=2;hop<=k;hop++)
		   {
			   HashSet<Integer> nextHopPositiveInNeighborsVI=new HashSet<Integer>();
			   HashSet<Integer> nextHopNegativeInNeighborsVI=new HashSet<Integer>();

				for(int v_j:kHopPositiveInNeighborsVI)
				{
					for(int v_k:this.getPositiveInNeighbors(v_j))
					{
						if((v_k!=v_i)&&(!kHopPositiveInNeighborsVI.contains(v_k)))
						{
							nextHopPositiveInNeighborsVI.add(v_k);
						}
					}
					
					for(int v_k:this.getNegativeInNeighbors(v_j))
					{
						if((v_k!=v_i)&&(!kHopNegativeInNeighborsVI.contains(v_k)))
						{
							nextHopNegativeInNeighborsVI.add(v_k);
						}
					}
				}
				
				for(int v_j:kHopNegativeInNeighborsVI)
				{
					for(int v_k:this.getPositiveInNeighbors(v_j))
					{
						if((v_k!=v_i)&&(!kHopNegativeInNeighborsVI.contains(v_k)))
						{
							nextHopNegativeInNeighborsVI.add(v_k);
						}
					}
					
					for(int v_k:this.getNegativeInNeighbors(v_j))
					{
						if((v_k!=v_i)&&(!kHopPositiveInNeighborsVI.contains(v_k)))
						{
							nextHopPositiveInNeighborsVI.add(v_k);
						}
					}
				}
				kHopPositiveInNeighborsVI.addAll(nextHopPositiveInNeighborsVI);
				kHopNegativeInNeighborsVI.addAll(nextHopNegativeInNeighborsVI);
			}
		   
		    kHopPositiveInNeighborsVI.removeAll(kHopNegativeInNeighborsVI);
		    
			FATValue+=1/(kHopPositiveOutDegrees.get(v_i)+kHopNegativeOutDegrees.get(v_i)+1.0);
			for(int v_j:kHopPositiveInNeighborsVI)
			{
				FATValue+=1/(kHopPositiveOutDegrees.get(v_j)+kHopNegativeOutDegrees.get(v_j)+1.0);
			}
			
			for(int v_j:kHopNegativeInNeighborsVI)
			{
				double negativeContribution=0.0;
				int positiveOutDegree = kHopPositiveOutDegrees.get(v_j);
				int negativeOutDegree = kHopNegativeOutDegrees.get(v_j);
				for(int x=1;x<=positiveOutDegree+1;x++)
				{
					double xContribution=1.0;
					for(int i=1;i<=x;i++)
					{
						xContribution*=(positiveOutDegree+1-x+i+0.0)/(positiveOutDegree+negativeOutDegree-x+i+0.0);
					}
					xContribution/=(positiveOutDegree+negativeOutDegree+1);
					negativeContribution+=xContribution;
				}
				FATValue+=(-1)*(negativeContribution);
			}

		   
		   FATMap.put(v_i,FATValue);
		}
		
		ArrayList<Integer> FATRanking = this.getRankList(FATMap);
		return FATRanking;
	}
	
	public ArrayList<Integer> generatekHopFMFRanking(int k)
	{
		HashMap<Integer,Double> NPFMap = new HashMap<Integer,Double>();
		HashMap<Integer,Integer> kHopPositiveInDegrees = new HashMap<Integer,Integer>();
		HashMap<Integer,Integer> kHopNegativeInDegrees = new HashMap<Integer,Integer>();
		
		for(int v_i:this.getVertices())
		{
			//System.out.println(v_i);
			HashSet<Integer> kHopPositiveInNeighborsVI=new HashSet<Integer>();
			HashSet<Integer> kHopNegativeInNeighborsVI=new HashSet<Integer>();
			kHopPositiveInNeighborsVI.addAll(this.getPositiveInNeighbors(v_i));
			kHopNegativeInNeighborsVI.addAll(this.getNegativeInNeighbors(v_i));
			
			for(int hop=2;hop<=k;hop++)
			{
				HashSet<Integer> nextHopPositiveInNeighborsVI=new HashSet<Integer>();
				HashSet<Integer> nextHopNegativeInNeighborsVI=new HashSet<Integer>();

				for(int v_j:kHopPositiveInNeighborsVI)
				{
					for(int v_k:this.getPositiveInNeighbors(v_j))
					{
						if((v_k!=v_i)&&(!kHopPositiveInNeighborsVI.contains(v_k)))
						{
							nextHopPositiveInNeighborsVI.add(v_k);
						}
					}
					
					for(int v_k:this.getNegativeInNeighbors(v_j))
					{
						if((v_k!=v_i)&&(!kHopNegativeInNeighborsVI.contains(v_k)))
						{
							nextHopNegativeInNeighborsVI.add(v_k);
						}
					}
				}
				
				for(int v_j:kHopNegativeInNeighborsVI)
				{
					for(int v_k:this.getPositiveInNeighbors(v_j))
					{
						if((v_k!=v_i)&&(!kHopNegativeInNeighborsVI.contains(v_k)))
						{
							nextHopNegativeInNeighborsVI.add(v_k);
						}
					}
					
					for(int v_k:this.getNegativeInNeighbors(v_j))
					{
						if((v_k!=v_i)&&(!kHopPositiveInNeighborsVI.contains(v_k)))
						{
							nextHopPositiveInNeighborsVI.add(v_k);
						}
					}
				}
				kHopPositiveInNeighborsVI.addAll(nextHopPositiveInNeighborsVI);
				kHopNegativeInNeighborsVI.addAll(nextHopNegativeInNeighborsVI);
			}
			
			kHopPositiveInDegrees.put(v_i,kHopPositiveInNeighborsVI.size());
			kHopNegativeInDegrees.put(v_i,kHopNegativeInNeighborsVI.size());
		}
		
		
		for(int v_i:this.getVertices())
		{
		   NPFMap.put(v_i,kHopPositiveInDegrees.get(v_i)-kHopNegativeInDegrees.get(v_i)+0.0);
		}
		
		ArrayList<Integer> NPFRanking = this.getRankList(NPFMap);
		return NPFRanking;
	}

	public ArrayList<Integer> generatekHopNetTrustRanking(int k)
	{
		HashMap<Integer,Double> NPFMap = new HashMap<Integer,Double>();
		HashMap<Integer,Integer> kHopPositiveInDegrees = new HashMap<Integer,Integer>();
		HashMap<Integer,Integer> kHopNegativeInDegrees = new HashMap<Integer,Integer>();
		
		HashMap<Integer,Integer> kHopPositiveOutDegrees = new HashMap<Integer,Integer>();
		HashMap<Integer,Integer> kHopNegativeOutDegrees = new HashMap<Integer,Integer>();
		
		for(int v_i:this.getVertices())
		{
			//System.out.println(v_i);
			HashSet<Integer> kHopPositiveInNeighborsVI=new HashSet<Integer>();
			HashSet<Integer> kHopNegativeInNeighborsVI=new HashSet<Integer>();
			kHopPositiveInNeighborsVI.addAll(this.getPositiveInNeighbors(v_i));
			kHopNegativeInNeighborsVI.addAll(this.getNegativeInNeighbors(v_i));
			
			for(int hop=2;hop<=k;hop++)
			{
				HashSet<Integer> nextHopPositiveInNeighborsVI=new HashSet<Integer>();
				HashSet<Integer> nextHopNegativeInNeighborsVI=new HashSet<Integer>();

				for(int v_j:kHopPositiveInNeighborsVI)
				{
					for(int v_k:this.getPositiveInNeighbors(v_j))
					{
						if((v_k!=v_i)&&(!kHopPositiveInNeighborsVI.contains(v_k)))
						{
							nextHopPositiveInNeighborsVI.add(v_k);
						}
					}
					
					for(int v_k:this.getNegativeInNeighbors(v_j))
					{
						if((v_k!=v_i)&&(!kHopNegativeInNeighborsVI.contains(v_k)))
						{
							nextHopNegativeInNeighborsVI.add(v_k);
						}
					}
				}
				
				for(int v_j:kHopNegativeInNeighborsVI)
				{
					for(int v_k:this.getPositiveInNeighbors(v_j))
					{
						if((v_k!=v_i)&&(!kHopNegativeInNeighborsVI.contains(v_k)))
						{
							nextHopNegativeInNeighborsVI.add(v_k);
						}
					}
					
					for(int v_k:this.getNegativeInNeighbors(v_j))
					{
						if((v_k!=v_i)&&(!kHopPositiveInNeighborsVI.contains(v_k)))
						{
							nextHopPositiveInNeighborsVI.add(v_k);
						}
					}
				}
				kHopPositiveInNeighborsVI.addAll(nextHopPositiveInNeighborsVI);
				kHopNegativeInNeighborsVI.addAll(nextHopNegativeInNeighborsVI);
			}
			
			kHopPositiveInDegrees.put(v_i,kHopPositiveInNeighborsVI.size());
			kHopNegativeInDegrees.put(v_i,kHopNegativeInNeighborsVI.size());
		}
		
		for(int v_i:this.getVertices())
		{
			//System.out.println(v_i);
			HashSet<Integer> kHopPositiveOutNeighborsVI=new HashSet<Integer>();
			HashSet<Integer> kHopNegativeOutNeighborsVI=new HashSet<Integer>();
			kHopPositiveOutNeighborsVI.addAll(this.getPositiveOutNeighbors(v_i));
			kHopNegativeOutNeighborsVI.addAll(this.getNegativeOutNeighbors(v_i));
			
			for(int hop=2;hop<=k;hop++)
			{
				HashSet<Integer> nextHopPositiveOutNeighborsVI=new HashSet<Integer>();
				HashSet<Integer> nextHopNegativeOutNeighborsVI=new HashSet<Integer>();

				for(int v_j:kHopPositiveOutNeighborsVI)
				{
					for(int v_k:this.getPositiveOutNeighbors(v_j))
					{
						if((v_k!=v_i)&&(!kHopPositiveOutNeighborsVI.contains(v_k)))
						{
							nextHopPositiveOutNeighborsVI.add(v_k);
						}
					}
					
					for(int v_k:this.getNegativeOutNeighbors(v_j))
					{
						if((v_k!=v_i)&&(!kHopNegativeOutNeighborsVI.contains(v_k)))
						{
							nextHopNegativeOutNeighborsVI.add(v_k);
						}
					}
				}
				
				for(int v_j:kHopNegativeOutNeighborsVI)
				{
					for(int v_k:this.getPositiveOutNeighbors(v_j))
					{
						if((v_k!=v_i)&&(!kHopNegativeOutNeighborsVI.contains(v_k)))
						{
							nextHopNegativeOutNeighborsVI.add(v_k);
						}
					}
					
					for(int v_k:this.getNegativeOutNeighbors(v_j))
					{
						if((v_k!=v_i)&&(!kHopPositiveOutNeighborsVI.contains(v_k)))
						{
							nextHopPositiveOutNeighborsVI.add(v_k);
						}
					}
				}
				kHopPositiveOutNeighborsVI.addAll(nextHopPositiveOutNeighborsVI);
				kHopNegativeOutNeighborsVI.addAll(nextHopNegativeOutNeighborsVI);
			}
			
			kHopPositiveOutDegrees.put(v_i,kHopPositiveOutNeighborsVI.size());
			kHopNegativeOutDegrees.put(v_i,kHopNegativeOutNeighborsVI.size());
		}
		
		for(int v_i:this.getVertices())
		{
		   NPFMap.put(v_i,kHopPositiveInDegrees.get(v_i)-kHopNegativeInDegrees.get(v_i)-kHopPositiveOutDegrees.get(v_i)+kHopNegativeOutDegrees.get(v_i)+0.0);
		}
		
		ArrayList<Integer> NPFRanking = this.getRankList(NPFMap);
		return NPFRanking;
	}
	
	public ArrayList<Integer> generateNormNPFRanking()
	{
		HashMap<Integer,Double> positiveFringeMap = new HashMap<Integer,Double>();
		HashMap<Integer,Double> negativeFringeMap = new HashMap<Integer,Double>();
		HashMap<Integer,Double> normNPFMap = new HashMap<Integer,Double>();
		
		for(int v_i:this.getVertices())
		{
		   double positiveNPFValue=0.0;
		   
		   positiveNPFValue+=(1.0)/(this.getPositiveOutDegree(v_i)+1);
		   for(int v_j:this.getPositiveInNeighbors(v_i))
		   {
			   positiveNPFValue+=(1.0)/(this.getPositiveOutDegree(v_j)+1);
		   }
		   
		   double negativeNPFValue=0.0;
		   for(int v_j:this.getNegativeInNeighbors(v_i))
		   {
			   negativeNPFValue+=(1.0)/(this.getNegativeOutDegree(v_j)+0.0);
		   }
		   
		   positiveFringeMap.put(v_i,positiveNPFValue);
		   negativeFringeMap.put(v_i,negativeNPFValue);
		}
		
		double positiveSum=0.0;
		double negativeSum=0.0;
		
		for(int v_i:this.getVertices())
		{
			positiveSum+=positiveFringeMap.get(v_i);
			negativeSum+=negativeFringeMap.get(v_i);
		}
		
		for(int v_i:this.getVertices())
		{
			double normNPFValue = Math.sqrt(positiveFringeMap.get(v_i)/(positiveSum))-Math.sqrt(negativeFringeMap.get(v_i)/(negativeSum));
			normNPFMap.put(v_i,normNPFValue);
		}
		
		ArrayList<Integer> normNPFRanking = this.getRankList(normNPFMap);
		return normNPFRanking;
	}

	public ArrayList<Integer> generateNNFRanking()
	{
		HashMap<Integer,Double> NPFMap = new HashMap<Integer,Double>();
		
		for(int v_i:this.getVertices())
		{
		   double NPFValue=0.0;
		   
		   for(int v_j:this.getNegativeInNeighbors(v_i))
		   {
			   NPFValue+=(-1.0)/(this.getNegativeOutDegree(v_j)+0.0);
		   }
		   
		   NPFMap.put(v_i,NPFValue);
		}
		
		ArrayList<Integer> NPFRanking = this.getRankList(NPFMap);
		return NPFRanking;
	}

	public ArrayList<Integer> generateFATRanking()
	{
		HashMap<Integer,Double> FATValues = new HashMap<Integer,Double>();
		
		for(int v_i:this.getVertices())
		{
			double FATValue=0.0;
			FATValue+=1/(this.getPositiveOutDegree(v_i)+this.getNegativeOutDegree(v_i)+1.0);
			for(int v_j:this.getPositiveInNeighbors(v_i))
			{
				FATValue+=1/(this.getPositiveOutDegree(v_j)+this.getNegativeOutDegree(v_j)+1.0);
			}
			
			for(int v_j:this.getNegativeInNeighbors(v_i))
			{
				double negativeContribution=0.0;
				int positiveOutDegree = this.getPositiveOutDegree(v_j);
				int negativeOutDegree = this.getNegativeOutDegree(v_j);
				for(int x=1;x<=positiveOutDegree+1;x++)
				{
					double xContribution=1.0;
					for(int i=1;i<=x;i++)
					{
						xContribution*=(positiveOutDegree+1-x+i+0.0)/(positiveOutDegree+negativeOutDegree-x+i+0.0);
					}
					xContribution/=(positiveOutDegree+negativeOutDegree+1);
					negativeContribution+=xContribution;
				}
				FATValue+=(-1)*(negativeContribution);
			}
			
			FATValues.put(v_i,FATValue);
		}
		
		ArrayList<Integer> FATRanking = this.getRankList(FATValues);
		return FATRanking;
		
	}

	public ArrayList<Integer> generateNAFRanking()
	{
		HashMap<Integer,Double> FATValues = new HashMap<Integer,Double>();
		HashMap<Integer,Double> NFADTValues = new HashMap<Integer,Double>();
		HashMap<Integer,Double> NAFValues = new HashMap<Integer,Double>();
		
		for(int v_i:this.getVertices())
		{
			double FATValue=0.0;
			FATValue+=1/(this.getPositiveOutDegree(v_i)+this.getNegativeOutDegree(v_i)+1.0);
			for(int v_j:this.getPositiveInNeighbors(v_i))
			{
				FATValue+=1/(this.getPositiveOutDegree(v_j)+this.getNegativeOutDegree(v_j)+1.0);
			}
			
			for(int v_j:this.getNegativeInNeighbors(v_i))
			{
				double negativeContribution=0.0;
				int positiveOutDegree = this.getPositiveOutDegree(v_j);
				int negativeOutDegree = this.getNegativeOutDegree(v_j);
				for(int x=1;x<=positiveOutDegree+1;x++)
				{
					double xContribution=1.0;
					for(int i=1;i<=x;i++)
					{
						xContribution*=(positiveOutDegree+1-x+i+0.0)/(positiveOutDegree+negativeOutDegree-x+i+0.0);
					}
					xContribution/=(positiveOutDegree+negativeOutDegree+1);
					negativeContribution+=xContribution;
				}
				FATValue+=(-1)*(negativeContribution);
			}
			
			FATValues.put(v_i,FATValue);
		}

		for(int v_i:this.getVertices())
		{
			double FATValue=0.0;

			for(int v_j:this.getNegativeInNeighbors(v_i))
			{
				FATValue+=1/(this.getPositiveOutDegree(v_j)+this.getNegativeOutDegree(v_j)+1.0);
			}
			
			HashSet<Integer> netPositiveNeighbors= new HashSet<Integer>(this.getPositiveInNeighbors(v_i));
			netPositiveNeighbors.add(v_i);
			
			for(int v_j:netPositiveNeighbors)
			{
				double negativeContribution=0.0;
				int positiveOutDegree = this.getPositiveOutDegree(v_j);
				int negativeOutDegree = this.getNegativeOutDegree(v_j);
				for(int x=1;x<=negativeOutDegree;x++)
				{
					double xContribution=1.0;
					for(int i=1;i<=x;i++)
					{
						xContribution*=(negativeOutDegree-x+i+0.0)/(positiveOutDegree+negativeOutDegree-x+i+0.0);
					}
					xContribution/=(positiveOutDegree+negativeOutDegree+1);
					negativeContribution+=xContribution;
				}
				FATValue+=(-1)*(negativeContribution);
			}
			
			NFADTValues.put(v_i,-FATValue);
		}

		for(int v_i:this.getVertices())
		{
			NAFValues.put(v_i,FATValues.get(v_i)+NFADTValues.get(v_i));
		}
		
		ArrayList<Integer> NAFRanking = this.getRankList(NAFValues);
		return NAFRanking;
		
	}
	
	public ArrayList<Integer> generateNFADTRanking()
	{
		HashMap<Integer,Double> FATValues = new HashMap<Integer,Double>();
		
		for(int v_i:this.getVertices())
		{
			double FATValue=0.0;

			for(int v_j:this.getNegativeInNeighbors(v_i))
			{
				FATValue+=1/(this.getPositiveOutDegree(v_j)+this.getNegativeOutDegree(v_j)+1.0);
			}
			
			HashSet<Integer> netPositiveNeighbors= new HashSet<Integer>(this.getPositiveInNeighbors(v_i));
			netPositiveNeighbors.add(v_i);
			
			for(int v_j:netPositiveNeighbors)
			{
				double negativeContribution=0.0;
				int positiveOutDegree = this.getPositiveOutDegree(v_j);
				int negativeOutDegree = this.getNegativeOutDegree(v_j);
				for(int x=1;x<=negativeOutDegree;x++)
				{
					double xContribution=1.0;
					for(int i=1;i<=x;i++)
					{
						xContribution*=(negativeOutDegree-x+i+0.0)/(positiveOutDegree+negativeOutDegree-x+i+0.0);
					}
					xContribution/=(positiveOutDegree+negativeOutDegree+1);
					negativeContribution+=xContribution;
				}
				FATValue+=(-1)*(negativeContribution);
			}
			
			FATValues.put(v_i,-FATValue);
		}
		
		ArrayList<Integer> FATRanking = this.getRankList(FATValues);
		return FATRanking;
	}

	
	
	public int trollsFound(ArrayList<Integer> rankList,int trollCount)
	{
		int trollsFoundCount=0;
		for(int i=0;i<trollCount;i++)
		{
			if(trollSet.contains(rankList.get(i)))
			{
				trollsFoundCount+=1;
			}
		}
		return trollsFoundCount;
	}

	public double averagePrecision(ArrayList<Integer> rankList,HashSet<Integer> relevantSet) 
	{
		int relevantDocumentsSeen=0;
		int n=relevantSet.size();
		double prDiscreteSum=0;
		for(int k=0;k<rankList.size();k++)
		{
			int documentId=rankList.get(k);
			int documentsSeen=k+1;
			double deltaK=0;
			if(relevantSet.contains(documentId))
			{
				relevantDocumentsSeen+=1;
				deltaK=1;
			}
			double precision_k=(relevantDocumentsSeen+0.0)/(documentsSeen+0.0);
			prDiscreteSum+=precision_k*deltaK;
		}
		prDiscreteSum/=n;
		return prDiscreteSum;
	}

	public ArrayList<Integer> generateK1K2Ranking(int k_1, int k_2) 
	{
		HashMap<Integer,Double> centralityVals = new HashMap<Integer,Double>();
		
		for(int v_i:this.getVertices())
		{
			int positiveOutDegreeVI = this.getPositiveOutDegree(v_i);
			int negativeOutDegreeVI = this.getNegativeOutDegree(v_i);
			
			double centralityVal=0.0;
			
			if(positiveOutDegreeVI<k_1)
			{
				centralityVal+=1.0;
			}
			else if((positiveOutDegreeVI>=k_1)&&(negativeOutDegreeVI<=k_2))
			{
				centralityVal+=(k_1+0.0)/(1+positiveOutDegreeVI+0.0);
			}
			else if((positiveOutDegreeVI>=k_1)&&(negativeOutDegreeVI>=k_2+1))
			{
				for(int x=1;x<=k_1-1;x++)
				{
					for(int y=1;y<k_2;y++)
					{
						double xyContribution=1.0/(positiveOutDegreeVI+negativeOutDegreeVI+1+0.0);
						for(int alpha=1;alpha<=x;alpha++)
						{
							xyContribution*=(positiveOutDegreeVI-x+alpha+0.0)/(positiveOutDegreeVI+negativeOutDegreeVI-x-y+alpha+0.0);
						}
						for(int beta=1;beta<=y;beta++)
						{
							xyContribution*=(negativeOutDegreeVI-y+beta+0.0)/(positiveOutDegreeVI+negativeOutDegreeVI-y+beta);
						}
						centralityVal+=xyContribution;
					}
				}
			}
			
			//Contributions through positive in neighbors
			for(int v_j:this.getPositiveInNeighbors(v_i))
			{
				int positiveOutDegreeVJ=this.getPositiveOutDegree(v_j);
				int negativeOutDegreeVJ=this.getNegativeOutDegree(v_i);
				
				if(positiveOutDegreeVJ<k_1)
				{
					centralityVal+=0.0;
				}
				else if((positiveOutDegreeVJ>=k_1)&&(negativeOutDegreeVJ<=k_2))
				{
					centralityVal+=(1+positiveOutDegreeVJ-k_1+0.0)/((1+positiveOutDegreeVJ+0.0)*(positiveOutDegreeVJ+0.0));
				}
				else if((positiveOutDegreeVJ>=k_1)&&(negativeOutDegreeVJ>=k_2+1))
				{
					for(int x=1;x<=k_2;x++)
					{
						double xContribution=1.0/(positiveOutDegreeVJ+negativeOutDegreeVJ+1.0);
						for(int alpha=1;alpha<=x;alpha++)
						{
							xContribution*=(negativeOutDegreeVJ-x+alpha+0.0)/(positiveOutDegreeVJ+negativeOutDegreeVJ-x-(k_1-1)+alpha+0.0);
						}
						for(int beta=1;beta<=k_1-1;beta++)
						{
							xContribution*=(positiveOutDegreeVJ-1-(k_1-1)+beta+0.0)/(positiveOutDegreeVJ+negativeOutDegreeVJ-(k_1-1)+beta+0.0);
						}
						centralityVal+=xContribution;
					}
				}
			}
			
			//Contributions through negative in neighbors
			for(int v_j:this.getNegativeInNeighbors(v_i))
			{
				int positiveOutDegreeVJ=this.getPositiveOutDegree(v_j);
				int negativeOutDegreeVJ=this.getNegativeOutDegree(v_i);
				
				//This is the only case in which v_i can contribute through a negativeInNeighbor
				if((positiveOutDegreeVJ>=k_1)&&(negativeOutDegreeVJ<=k_2))
				{
					for(int x=k_1;x<=positiveOutDegreeVJ;x++)
					{
						double xContribution=-1.0/(positiveOutDegreeVJ+negativeOutDegreeVJ+0.0);
						for(int alpha=1;alpha<=x;alpha++)
						{
							xContribution*=(positiveOutDegreeVJ-x+alpha+0.0)/(positiveOutDegreeVJ+negativeOutDegreeVJ-x-k_2+alpha+0.0);
						}
						for(int beta=1;beta<=k_2;beta++)
						{
							xContribution*=(negativeOutDegreeVJ-1-k_2+beta+0.0)/(positiveOutDegreeVJ+negativeOutDegreeVJ-k_2+beta+0.0);
						}
						centralityVal+=xContribution;
					}
				}
			}
			
			centralityVals.put(v_i,centralityVal);
			
		}
		ArrayList<Integer> centralityRanking = this.getRankList(centralityVals);
		return centralityRanking;
	}

	public double getInfluenceWC(HashSet<Integer> initialSet, int trials,boolean onlyPositive) 
	{
		double averageInfluence=0.0;
		for(int trial=0;trial<trials;trial++)
		{
			HashMap<Integer,Integer> newlyActivatedNodes = new HashMap<Integer,Integer>();
			HashMap<Integer,Integer> activatedNodes = new HashMap<Integer,Integer>();
			for(int initialNode:initialSet)
			{
				activatedNodes.put(initialNode,+1);
				newlyActivatedNodes.put(initialNode,+1);
			}
			
			while(newlyActivatedNodes.size()>0)
			{
				HashMap<Integer,Integer> updatedNewlyActivatedNodes = new HashMap<Integer,Integer>();
				HashMap<Integer,ArrayList<Integer>> attemptsForNodes = new HashMap<Integer,ArrayList<Integer>>();
				
				for(int v_i:newlyActivatedNodes.keySet())
				{
					for(int v_j:this.getPositiveInNeighbors(v_i))
					{
						if((!newlyActivatedNodes.containsKey(v_j))&&(!activatedNodes.containsKey(v_j)))
						{
							double coinToss=Math.random();
							double activationProbability=1.0/(this.getPositiveOutDegree(v_j)+this.getNegativeOutDegree(v_j));
							//double activationProbability=0.1;
							if(coinToss<activationProbability)
							{
								if(!attemptsForNodes.containsKey(v_j))
								{
									attemptsForNodes.put(v_j,new ArrayList<Integer>());
								}
								attemptsForNodes.get(v_j).add(newlyActivatedNodes.get(v_i));
							}
						}
					}
					
					for(int v_j:this.getNegativeInNeighbors(v_i))
					{
						if((!newlyActivatedNodes.containsKey(v_j))&&(!activatedNodes.containsKey(v_j)))
						{
							double coinToss=Math.random();
							double activationProbability=1.0/(this.getPositiveOutDegree(v_j)+this.getNegativeOutDegree(v_j));
							//double activationProbability=0.1;
							if(coinToss<activationProbability)
							{
								if(!attemptsForNodes.containsKey(v_j))
								{
									attemptsForNodes.put(v_j,new ArrayList<Integer>());
								}
								attemptsForNodes.get(v_j).add(-newlyActivatedNodes.get(v_i));
							}
						}
					}
					
					
				}
				
				for(int v_j:attemptsForNodes.keySet())
				{
					int randomElem = attemptsForNodes.get(v_j).get(new Random().nextInt(attemptsForNodes.get(v_j).size()));
					updatedNewlyActivatedNodes.put(v_j,randomElem);		
				}
				
				for(int v_j:newlyActivatedNodes.keySet())
				{
					activatedNodes.put(v_j,newlyActivatedNodes.get(v_j));
				}
				
				newlyActivatedNodes=updatedNewlyActivatedNodes;
			}
			
			for(int v_j:newlyActivatedNodes.keySet())
			{
				activatedNodes.put(v_j,newlyActivatedNodes.get(v_j));
			}
			
			int positiveInfluence=0;
			int negativeInfluence=0;
			
			for(int v_j:activatedNodes.keySet())
			{
				if(activatedNodes.get(v_j)==1)
				{
					positiveInfluence+=1;
				}
				else
				{
					negativeInfluence+=1;
				}
			}
			
			if(onlyPositive)
			{
				averageInfluence+=(positiveInfluence+0.0)/(trials+0.0);
			}
			else
			{
				averageInfluence+=(positiveInfluence-negativeInfluence+0.0)/(trials+0.0);
			}
		}
		
		return averageInfluence;
	}

	public HashSet<Integer> selectTopKDiverse(ArrayList<Integer> rankList,int k)
	{
		HashSet<Integer> selectedNodes=new HashSet<Integer>();
		HashSet<Integer> inNeighborSet=new HashSet<Integer>();
		int currentIndex=0;
		while(selectedNodes.size()<k)
		{
			int nodeUnderConsideration=rankList.get(currentIndex);
			currentIndex+=1;
			if(inNeighborSet.contains(nodeUnderConsideration))
			{
				continue;
			}
			else
			{
				selectedNodes.add(nodeUnderConsideration);
				inNeighborSet.addAll(this.getPositiveInNeighbors(nodeUnderConsideration));
			}
		}
		return selectedNodes;
	}
	
	/*public void writeVertexIdsToFile(String fileName) 
	{
		
		
	}*/
	
	public void addTrollMutualLikeAttacks(int K)
	{
		ArrayList<String> prospectiveAttacks=new ArrayList<String>();
		
		for(int i:this.trollSet)
		{
			for(int j:this.trollSet)
			{
				if((!this.getPositiveOutNeighbors(i).contains(j))&&(!this.getPositiveOutNeighbors(j).contains(i)))
				{
					if(i>j)
					{
						prospectiveAttacks.add(i+","+j);
					}
				}
			}
		}
		
		//System.out.println(prospectiveAttacks.size());
		ArrayList<Integer> permutation=this.generateTPermutation(prospectiveAttacks.size());

		for(int i=0;i<K;i++)
		{
			int element=permutation.get(i);
			String prospectiveAttackString=prospectiveAttacks.get(element);
			String[] words=prospectiveAttackString.split(",");
			int a=Integer.parseInt(words[0]);
			int b=Integer.parseInt(words[1]);
			this.addTrustEdge(a,b);
			this.addTrustEdge(b,a);
		}
		
		
	}
	

	public void addCamouflageAttacks(int K)
	{

		HashSet<Integer> nonTrolls=new HashSet<Integer>(this.getVertices());
		nonTrolls.removeAll(this.trollSet);
		
		ArrayList<Integer> trollList=new ArrayList<Integer>(this.trollSet);
		ArrayList<Integer> nonTrollList=new ArrayList<Integer>(nonTrolls);
		HashSet<String> chosenAttacks=new HashSet<String>();
		
		while(chosenAttacks.size()<K)
		{
			Random rand1=new Random();
			Random rand2=new Random();
			int troll=trollList.get(rand1.nextInt(trollList.size()));
			int nonTroll=nonTrollList.get(rand2.nextInt(nonTrollList.size()));
			if((!this.getPositiveOutNeighbors(troll).contains(nonTroll))&&(!this.getPositiveOutNeighbors(nonTroll).contains(troll)))
			{
				this.addTrustEdge(troll,nonTroll);
				this.addTrustEdge(nonTroll,troll);
				chosenAttacks.add(troll+","+nonTroll);
			}
		}				
	}


}
