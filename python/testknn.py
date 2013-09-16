import knn;


group, labels = knn.createdataset();


print(group);


print(labels);

label  = knn.classify0([5,0],group,labels,3);

print(label);