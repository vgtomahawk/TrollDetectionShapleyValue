import sys
from matplotlib import pyplot as plt

def normalize(valueList,Norm):
    return [x/Norm for x in valueList]

attacks=[10,25,50,100,250,500,700,1000,1500,2000,2500,3000]

inFile=open(sys.argv[1])
FMFValues=[]
NPFValues=[]
FATValues=[]
NFADTValues=[]
NetTrustValues=[]

index=0
for line in inFile:
    if index%7<2:
        index+=1
        continue
    print line
    words=line.split(":")
    val=float(words[-1])
    if index%7==2:
        FMFValues.append(val)
    elif index%7==3:
        NPFValues.append(val)
    elif index%7==4:
        FATValues.append(val)
    elif index%7==5:
        NFADTValues.append(val)
    elif index%7==6:
        NetTrustValues.append(val)
    index+=1

print FMFValues

plt.xlim(xmax=4000)
plt.xlabel("Number Of Attacks")
plt.ylabel("Average Precision")
plt.title("AP vs CBGT attacks")
plt.plot(attacks,FMFValues,color="black",label="FMF",marker=".",linestyle="-")
plt.plot(attacks,NPFValues,color="black",label="NPF",marker="+",linestyle="-")
plt.plot(attacks,FATValues,color="black",label="FAT",marker="^",linestyle="-")
plt.plot(attacks,NFADTValues,color="black",label="NFADT",marker="x",linestyle="-")
plt.plot(attacks,NetTrustValues,color="black",label="NTV",marker="*",linestyle="-")
plt.legend()
plt.savefig("CBGTAttacksAbsoluteGrayScale.png")
plt.close()

FMFNorm=0.031
NTVNorm=0.044
NPFNorm=0.104
FATNorm=0.092
NFADTNorm=0.130

FMFValues=normalize(FMFValues,FMFNorm)
NPFValues=normalize(NPFValues,NPFNorm)
FATValues=normalize(FATValues,FATNorm)
NFADTValues=normalize(NFADTValues,NFADTNorm)
NetTrustValues=normalize(NetTrustValues,NTVNorm)

plt.xlim(xmax=4000)
plt.xlabel("Number Of Attacks")
plt.ylabel("Fraction Of Original AP")
plt.title("Fraction Of Original AP vs CBGT attacks")
plt.plot(attacks,FMFValues,color="black",label="FMF",marker=".",linestyle="-")
plt.plot(attacks,NPFValues,color="black",label="NPF",marker="+",linestyle="-")
plt.plot(attacks,FATValues,color="black",label="FAT",marker="^",linestyle="-")
plt.plot(attacks,NFADTValues,color="black",label="NFADT",marker="x",linestyle="-")
plt.plot(attacks,NetTrustValues,color="black",label="NTV",marker="*",linestyle="-")
plt.legend()
plt.savefig("CBGTAttacksRelativeGrayScale.png")
plt.close()           
