import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class MainClass 
{
	public static void main(String[] args) throws IOException
	{
		int NO_OF_TRIALS=0;
		double DROP_PROBABILITY=0.2;

		/*
		double meanPrecisionFMF=0.0;
		double meanPrecisionPDC=0.0;
		double meanPrecisionNNF=0.0;
		double meanPrecisionNNDC=0.0;
		double meanPrecisionNPF=0.0;
		double meanPrecisionFAT=0.0;
		double meanPrecisionNFADT=0.0;
		double meanPrecisionNAF=0.0;
		double meanPrecisionTwoHopNPF=0.0;
		double meanPrecisionThreeHopNPF=0.0;
		*/
		
		/*
		double meanPrecisionFourHopNPF=0.0;
		double meanPrecisionTwoHopFMF=0.0;
		double meanPrecisionThreeHopFMF=0.0;
		*/
		
		/*
		double meanPrecisionFourHopFMF=0.0;	
		double meanPrecisionNTV=0.0;
		*/
		/*
		for(int trialNo=1;trialNo<=NO_OF_TRIALS;trialNo++)
		{
			System.out.print(trialNo+" ");
			GraphClass g = new GraphClass();
			g.loadSlashdot();
			ArrayList<Integer> verticesToBeDeleted = new ArrayList<Integer>();
			for(int v_i:g.getVertices())
			{
				double coinToss=Math.random();
				if(coinToss<=DROP_PROBABILITY)
				{
					verticesToBeDeleted.add(v_i);
				}
			}
			
			for(int v_i:verticesToBeDeleted)
			{
				g.deleteNode(v_i);
			}

			
			ArrayList<Integer> FMFRanking=g.generateFMFRanking();
			ArrayList<Integer> twoHopFMFRanking=g.generatekHopFMFRanking(2);
			ArrayList<Integer> threeHopFMFRanking=g.generatekHopFMFRanking(3);
	        ArrayList<Integer> fourHopFMFRanking=g.generatekHopFMFRanking(4);
			ArrayList<Integer> PDCRanking=g.generatePDCRanking();
			ArrayList<Integer> NNDCRanking=g.generateNNDCRanking();
			ArrayList<Integer> NNFRanking=g.generateNNFRanking();
			ArrayList<Integer> NPFRanking=g.generateNPFRanking();
			ArrayList<Integer> twoHopNPFRanking=g.generateTwoHopNPFRanking();
			ArrayList<Integer> threeHopNPFRanking=g.generatekHopNPFRanking(3);
			ArrayList<Integer> fourHopNPFRanking=g.generatekHopNPFRanking(4);
			ArrayList<Integer> FATRanking=g.generateFATRanking();
			ArrayList<Integer> NFADTRanking=g.generateNFADTRanking();
			ArrayList<Integer> NAFRanking=g.generateNAFRanking();
			ArrayList<Integer> NTVRanking=g.generateNetTrustRanking(); 
			
			double precisionFMF=g.averagePrecision(FMFRanking,g.trollSet);
			double precisionPDC=g.averagePrecision(PDCRanking,g.trollSet);
			double precisionNNF=g.averagePrecision(NNFRanking,g.trollSet);
			double precisionNNDC=g.averagePrecision(NNDCRanking,g.trollSet);
			double precisionNPF=g.averagePrecision(NPFRanking,g.trollSet);
			double precisionFAT=g.averagePrecision(FATRanking,g.trollSet);
			double precisionNFADT=g.averagePrecision(NFADTRanking,g.trollSet);
			double precisionNAF=g.averagePrecision(NAFRanking,g.trollSet);
			double precisionTwoHopNPF=g.averagePrecision(twoHopNPFRanking,g.trollSet);
			double precisionThreeHopNPF=g.averagePrecision(threeHopNPFRanking,g.trollSet);
			double precisionFourHopNPF=g.averagePrecision(fourHopNPFRanking,g.trollSet);
			double precisionTwoHopFMF=g.averagePrecision(twoHopFMFRanking,g.trollSet);
			double precisionThreeHopFMF=g.averagePrecision(threeHopFMFRanking,g.trollSet);
			double precisionFourHopFMF=g.averagePrecision(fourHopFMFRanking,g.trollSet);
			double precisionNTV=g.averagePrecision(NTVRanking,g.trollSet);
			
			meanPrecisionFMF+=precisionFMF/NO_OF_TRIALS;
			meanPrecisionPDC+=precisionPDC/NO_OF_TRIALS;
			meanPrecisionNNF+=precisionNNF/NO_OF_TRIALS;
			meanPrecisionNNDC+=precisionNNDC/NO_OF_TRIALS;
			meanPrecisionNPF+=precisionNPF/NO_OF_TRIALS;
			meanPrecisionFAT+=precisionFAT/NO_OF_TRIALS;
			meanPrecisionNFADT+=precisionNFADT/NO_OF_TRIALS;
			meanPrecisionNAF+=precisionNAF/NO_OF_TRIALS;
			meanPrecisionTwoHopNPF+=precisionTwoHopNPF/NO_OF_TRIALS;
			meanPrecisionThreeHopNPF+=precisionThreeHopNPF/NO_OF_TRIALS;
			meanPrecisionFourHopNPF+=precisionFourHopNPF/NO_OF_TRIALS;
			meanPrecisionTwoHopFMF+=precisionTwoHopFMF/NO_OF_TRIALS;
			meanPrecisionThreeHopFMF+=precisionThreeHopFMF/NO_OF_TRIALS;
			meanPrecisionFourHopFMF+=precisionFourHopFMF/NO_OF_TRIALS;
			meanPrecisionNTV+=precisionNTV/NO_OF_TRIALS;
			
			
		}

		*/
		/*
		System.out.println("meanPrecisionFMF:"+meanPrecisionFMF);
		System.out.println("meanPrecisionPDC"+meanPrecisionPDC);
		System.out.println("meanPrecisionNNF"+meanPrecisionNNF);
		System.out.println("meanPrecisionNNDC"+meanPrecisionNNDC);
		System.out.println("meanPrecisionNPF"+meanPrecisionNPF);
		System.out.println("meanPrecisionFAT"+meanPrecisionFAT);
		System.out.println("meanPrecisionNFADT"+meanPrecisionNFADT);
		System.out.println("meanPrecisionNAF"+meanPrecisionNAF);
		System.out.println("meanPrecisionTwoHopNPF"+meanPrecisionTwoHopNPF);
		System.out.println("meanPrecisionThreeHopNPF"+meanPrecisionThreeHopNPF);
		System.out.println("meanPrecisionFourHopNPF"+meanPrecisionFourHopNPF);
		System.out.println("meanPrecisionTwoHopFMF"+meanPrecisionTwoHopFMF);
		System.out.println("meanPrecisionThreeHopFMF"+meanPrecisionThreeHopFMF);
		System.out.println("meanPrecisionFourHopFMF"+meanPrecisionFourHopFMF);
		System.out.println("meanPrecisionNTV"+meanPrecisionNTV);
		*/
		
		/*
		GraphClass g = new GraphClass();
		g.loadSlashdot();
		*/
		
		/*
		ArrayList<Integer> netTrustRanking=g.generatekHopNetTrustRanking(3);
		double precisionNetTrust=g.averagePrecision(netTrustRanking,g.trollSet);
		System.out.println(precisionNetTrust);
		
		
		//Net Trust Votes
	
		ArrayList<Integer> NTVRanking = g.generateNTVRanking(100000,100);
		double precisionNTV=g.averagePrecision(NTVRanking,g.trollSet);
		System.out.println(precisionNTV);
	
		//Net Approvers Votes
		
		ArrayList<Integer> NAVRanking = g.generateNAVRanking(100000,100);
		double precisionNAV=g.averagePrecision(NAVRanking,g.trollSet);
		System.out.println(precisionNAV);
		*/
		
		/*
		//g.writeVertexIdsToFile("vertexIds.dump");
		*/
		
		/*
		System.out.println("Total Number Of Vertices:"+g.vertexIds.size());
		System.out.println("Maximum Vertex Id:"+Collections.max(g.vertexIds));
		*/
		/*
		ArrayList<Integer> FMFRanking=g.generateFMFRanking();
		ArrayList<Integer> twoHopFMFRanking=g.generatekHopFMFRanking(2);
		ArrayList<Integer> threeHopFMFRanking=g.generatekHopFMFRanking(3);
        ArrayList<Integer> fourHopFMFRanking=g.generatekHopFMFRanking(4);
		ArrayList<Integer> PDCRanking=g.generatePDCRanking();
		ArrayList<Integer> NNDCRanking=g.generateNNDCRanking();
		ArrayList<Integer> NNFRanking=g.generateNNFRanking();
		ArrayList<Integer> NPFRanking=g.generateNPFRanking();
		ArrayList<Integer> twoHopNPFRanking=g.generateTwoHopNPFRanking();
		ArrayList<Integer> threeHopNPFRanking=g.generatekHopNPFRanking(3);
		ArrayList<Integer> fourHopNPFRanking=g.generatekHopNPFRanking(4);
		ArrayList<Integer> FATRanking=g.generateFATRanking();
		ArrayList<Integer> NFADTRanking=g.generateNFADTRanking();
		ArrayList<Integer> NAFRanking=g.generateNAFRanking();
		ArrayList<Integer> netTrustRanking=g.generateNetTrustRanking();
		for(int i=1;i<=10;i++)
		{
			ArrayList<Integer> betaNPFRanking=g.generateBetaFMFRanking((i+0.0)/10.0);
			System.out.println(g.averagePrecision(betaNPFRanking,g.trollSet));
		}*/
		
		/*
		int trollsFoundFMF = g.trollsFound(FMFRanking,g.trollSet.size());
		int trollsFoundPDC = g.trollsFound(PDCRanking,g.trollSet.size());
		int trollsFoundNNF = g.trollsFound(NNFRanking,g.trollSet.size());
		int trollsFoundNNDC = g.trollsFound(NNDCRanking,g.trollSet.size());
		int trollsFoundNPF = g.trollsFound(NPFRanking,g.trollSet.size());
		int trollsFoundFAT = g.trollsFound(FATRanking,g.trollSet.size());
		int trollsFoundNFADT = g.trollsFound(NFADTRanking,g.trollSet.size());
		int trollsFoundNAF  = g.trollsFound(NAFRanking,g.trollSet.size());
		int trollsFoundTwoHopNPF=g.trollsFound(twoHopNPFRanking,g.trollSet.size());
		int trollsFoundThreeHopNPF=g.trollsFound(threeHopNPFRanking,g.trollSet.size());
		int trollsFoundFourHopNPF=g.trollsFound(fourHopNPFRanking,g.trollSet.size());
		int trollsFoundTwoHopFMF=g.trollsFound(twoHopFMFRanking,g.trollSet.size());
		int trollsFoundThreeHopFMF=g.trollsFound(threeHopFMFRanking,g.trollSet.size());
		int trollsFoundFourHopFMF=g.trollsFound(fourHopFMFRanking,g.trollSet.size());
		int trollsFoundNetTrust=g.trollsFound(netTrustRanking,g.trollSet.size());
		*/
		
		/*
		double precisionFMF=g.averagePrecision(FMFRanking,g.trollSet);
		double precisionPDC=g.averagePrecision(PDCRanking,g.trollSet);
		double precisionNNF=g.averagePrecision(NNFRanking,g.trollSet);
		double precisionNNDC=g.averagePrecision(NNDCRanking,g.trollSet);
		double precisionNPF=g.averagePrecision(NPFRanking,g.trollSet);
		double precisionFAT=g.averagePrecision(FATRanking,g.trollSet);
		double precisionNFADT=g.averagePrecision(NFADTRanking,g.trollSet);
		double precisionNAF=g.averagePrecision(NAFRanking,g.trollSet);
		double precisionTwoHopNPF=g.averagePrecision(twoHopNPFRanking,g.trollSet);
		double precisionThreeHopNPF=g.averagePrecision(threeHopNPFRanking,g.trollSet);
		double precisionFourHopNPF=g.averagePrecision(fourHopNPFRanking,g.trollSet);
		double precisionTwoHopFMF=g.averagePrecision(twoHopFMFRanking,g.trollSet);
		double precisionThreeHopFMF=g.averagePrecision(threeHopFMFRanking,g.trollSet);
		double precisionFourHopFMF=g.averagePrecision(fourHopFMFRanking,g.trollSet);
		double precisionNetTrust=g.averagePrecision(netTrustRanking,g.trollSet);
		*/
		
		
		/*
		System.out.println("Number Of Trolls FMF:"+trollsFoundFMF);
		System.out.println("Number Of Trolls PDC:"+trollsFoundPDC);
		System.out.println("Number Of Trolls NNF:"+trollsFoundNNF);
		System.out.println("Number Of Trolls NNDC:"+trollsFoundNNDC);
		System.out.println("Number Of Trolls NPF:"+trollsFoundNPF);
		System.out.println("Number Of Trolls FAT:"+trollsFoundFAT);
		System.out.println("Number Of Trolls NFADT:"+trollsFoundNFADT);
		System.out.println("Number Of Trolls NAF:"+trollsFoundNAF);
		System.out.println("Number Of Trolls 2 Hop NPF:"+trollsFoundTwoHopNPF);
		System.out.println("Number Of Trolls 3 Hop NPF:"+trollsFoundThreeHopNPF);
		System.out.println("Number Of Trolls 4 Hop NPF:"+trollsFoundFourHopNPF);
		System.out.println("Number Of Trolls 2 Hop NPF:"+trollsFoundTwoHopFMF);
		System.out.println("Number Of Trolls 3 Hop NPF:"+trollsFoundThreeHopFMF);
		System.out.println("Number Of Trolls 4 Hop NPF:"+trollsFoundFourHopFMF);
		System.out.println("Number Of Trolls Net Trust:"+trollsFoundNetTrust);
		*/
		
		/*
		System.out.println("FMF Average Precision:"+precisionFMF);
		System.out.println("PDC Average Precision:"+precisionPDC);
		System.out.println("NNF Average Precision:"+precisionNNF);
		System.out.println("NNDC Average Precision:"+precisionNNDC);
		System.out.println("NPF Average Precision:"+precisionNPF);
		System.out.println("FAT  Average Precision:"+precisionFAT);
		System.out.println("NFADT Average Precision:"+precisionNFADT);
		System.out.println("NAF Precision:"+precisionNAF);
		System.out.println("2 Hop NPF Precision:"+precisionTwoHopNPF);
		System.out.println("3 Hop NPF Precision:"+precisionThreeHopNPF);
		System.out.println("4 Hop NPF Precision:"+precisionFourHopNPF);
		System.out.println("2 Hop FMF Precision:"+precisionTwoHopFMF);
		System.out.println("3 Hop FMF Precision:"+precisionThreeHopFMF);
		System.out.println("3 Hop FMF Precision:"+precisionFourHopFMF);
		System.out.println("NetTrust Precision:"+precisionNetTrust);
		*/
		
		/*
		Collections.reverse(FMFRanking);
		Collections.reverse(NPFRanking);
		Collections.reverse(FATRanking);
		Collections.reverse(netTrustRanking);
		Collections.reverse(NFADTRanking);
		Collections.reverse(NAFRanking);
		Collections.reverse(twoHopNPFRanking);
		*/
	
		/*
		for(int k=1;k<=15;k++)
		{
			System.out.println("k="+k);
			
			
			HashSet<Integer> initialSet=g.selectTopKDiverse(FMFRanking,k);
			System.out.println("Positive Sigma FMF:"+g.getInfluenceWC(initialSet,3000,true));
			System.out.println("Net Sigma FMF:"+g.getInfluenceWC(initialSet,3000,false));
			
			
			initialSet=g.selectTopKDiverse(NPFRanking,k);
			System.out.println("Positive Sigma NPF:"+g.getInfluenceWC(initialSet,3000,true));
			System.out.println("Net Sigma NPF:"+g.getInfluenceWC(initialSet,3000,false));
		
			
			initialSet=g.selectTopKDiverse(netTrustRanking,k);
			System.out.println("Positive Sigma FAT:"+g.getInfluenceWC(initialSet,3000,true));
			System.out.println("Net Sigma FAT:"+g.getInfluenceWC(initialSet,3000,false));
		
			
			initialSet=new HashSet<Integer>(NFADTRanking.subList(0,k));
			System.out.println("Positive Sigma NFADT:"+g.getInfluenceWC(initialSet,3000,true));
			System.out.println("Net Sigma NFADT:"+g.getInfluenceWC(initialSet,3000,false));
		
			
			initialSet=new HashSet<Integer>(NAFRanking.subList(0,k));
			System.out.println("Positive Sigma NAF:"+g.getInfluenceWC(initialSet,3000,true));
			System.out.println("Net Sigma NAF:"+g.getInfluenceWC(initialSet,3000,false));
			
			initialSet=new HashSet<Integer>(twoHopNPFRanking.subList(0,k));
			System.out.println("Positive Sigma 2 Hop NPF:"+g.getInfluenceWC(initialSet,3000,true));
			System.out.println("Net Sigma 2 Hop NPF:"+g.getInfluenceWC(initialSet,3000,false));

		}
		*/


		ArrayList<Integer> numberOfAttacks = new ArrayList<Integer>(Arrays.asList(10,25,50,100,250,500,700,1000,1500,2000,2500,3000));
		for(int i=0;i<numberOfAttacks.size();i++)
		{
			double meanPrecisionFMF=0.0;
			double meanPrecisionNPF=0.0;
			double meanPrecisionFAT=0.0;
			double meanPrecisionNFADT=0.0;
			double meanPrecisionNetTrust=0.0;
			System.out.println("--------Number Of Attacks:"+i+"----");
			for(int j=0;j<50;j++)
			{
				System.out.print(j+" ");
				GraphClass h=new GraphClass();
				h.loadSlashdot();
				h.addCamouflageAttacks(numberOfAttacks.get(i));
				ArrayList<Integer> FMFRanking=h.generateFMFRanking();
				ArrayList<Integer> NPFRanking=h.generateNPFRanking();
				ArrayList<Integer> FATRanking=h.generateFATRanking();
				ArrayList<Integer> NFADTRanking=h.generateNFADTRanking();
				ArrayList<Integer> NetTrustRanking=h.generateNetTrustRanking();
				double precisionFMF=h.averagePrecision(FMFRanking,h.trollSet);
				double precisionNPF=h.averagePrecision(NPFRanking,h.trollSet);
				double precisionFAT=h.averagePrecision(FATRanking,h.trollSet);
				double precisionNFADT=h.averagePrecision(NFADTRanking,h.trollSet);
				double precisionNetTrust=h.averagePrecision(NetTrustRanking,h.trollSet);
				meanPrecisionFMF+=precisionFMF/50;
				meanPrecisionNPF+=precisionNPF/50;
				meanPrecisionFAT+=precisionFAT/50;
				meanPrecisionNFADT+=precisionNFADT/50;
				meanPrecisionNetTrust+=precisionNetTrust/50;
			}
			System.out.println("FMF Average Precision:"+meanPrecisionFMF);
			System.out.println("NPF Average Precision:"+meanPrecisionNPF);
			System.out.println("FAT  Average Precision:"+meanPrecisionFAT);
			System.out.println("NFADT Average Precision:"+meanPrecisionNFADT);
			System.out.println("NetTrust Precision:"+meanPrecisionNetTrust);
		}
	}
	
	
}
