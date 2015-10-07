k=[0,1,2,3,4,5,6,7,8,9]

FMFResults=[0,877,1279,1904,2378,2791,3336,3541,3660,3788]
NPFResults=[0,886,1521,2098,2563,2979,3336,3538,3758,3870]
FATResults=[0,883,1523,2088,2554,2972,3336,3544,3755,3871]
NAFResults=[0,885,1521,2094,2550,2967,3326,3541,3762,3864]

from matplotlib import pyplot as plt

plt.xlabel("Size of influence set - k")
plt.ylabel("Expected Influence")

plt.plot(k,FMFResults,'-b',label="FMF");
plt.plot(k,NPFResults,'-r',label="NPF");


plt.legend(loc='upper left')

plt.show()
