import knn;
import operator;
from numpy import *;

#TESt creating  datasets
group, labels = knn.createdataset();


print(group);


print(labels);

#Test classifier
label  = knn.classify0([5,0],group,labels,3);

print(label);


#test loading file
datingDataMat,datingLabels = knn.filetoMatrix('C:\\Users\Folorunsho Solomon\\Documents\\GitHub\\ML\\python\\datingTestSet2.txt');

print(datingDataMat);

print(datingLabels);


import matplotlib;
import matplotlib.pyplot as plt
fig = plt.figure()
ax = fig.add_subplot(111)
#ax.scatter(datingDataMat[:,1], datingDataMat[:,2])
ax.scatter(datingDataMat[:,1], datingDataMat[:,2],15.0* array(datingLabels), 15.0*array(datingLabels))
plt.show()



knn.datingClassTest();