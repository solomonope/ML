import dt;



datasets,labels = dt.createDataSet();


entropy = dt.calcShannonEnt(datasets);

#print(entropy);

#(dataSet,axis,value)

print("DataSet");
print(datasets);
print("Split");
print (dt.splitDataSet(datasets,0,1) );