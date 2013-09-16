import numpy;
import operator;
import os;

def createdataset():
    
    group = numpy.array([ [1.0,1.1],[1.0,1.0],[0,0],[0,0.1] ]);
    
    labels = ['A','A','B','B'];
    
    return group,labels;
    
    
    
def classify0(inX,dataset,labels,k):
    dataSetSize = dataset.shape[0];
    diffmat = numpy.tile(inX,(dataSetSize,1)) - dataset;
    sqDiffMat = diffmat ** 2;
    sqDistances = sqDiffMat.sum(axis = 1);
    distances   =  sqDistances** 0.5;
    sortedDistIndices = distances.argsort();
    classCount = {};
    
    for i in range(k):
        voteIlabel = labels[sortedDistIndices[i]];
        classCount[voteIlabel]  = classCount.get(voteIlabel,0) + 1;
    
    sortedClassCount = sorted(classCount.iteritems(),key=operator.itemgetter(1), reverse=True);
    return sortedClassCount[0][0];
    
    

def filetoMatrix(filename):
    
    fr = open(filename);
    numberOfLines = len( fr.readlines() );
    returnMat = numpy.zeros((numberOfLines,3));
    classLabelVector = [];
    
    fr = open(filename);
    index = 0;
    
    for line in fr.readlines():
        line = line.strip();
        listfromline = line.split('\t');
        returnMat[index,:] = listfromline[0:3];
        classLabelVector.append(int(listfromline[-1]));
        #classLabelVector.append(int(listfromline[-1]));
        index += 1;
    return returnMat,classLabelVector;
    
def file2matrix(filename):
    
    fr = open(filename)
    numberOfLines = len(fr.readlines())         #get the number of lines in the file
    returnMat = numpy.zeros((numberOfLines,3))        #prepare matrix to return
    classLabelVector = []                       #prepare labels return   
    fr = open(filename)
    index = 0
    for line in fr.readlines():
        line = line.strip()
        listFromLine = line.split('\t')
        returnMat[index,:] = listFromLine[0:3]
        classLabelVector.append(int(listFromLine[-1]))
        index += 1
    return returnMat,classLabelVector
    
    
    

def autonorm(dataset):
    minValues = dataset.min(0);
    maxValues = dataset.max(0);
    
    ranges =  maxValues - minValues;
    normDataSet = numpy.zeros (numpy.shape(dataset));
    m =   dataset.shape[0];
    normDataSet = dataset - numpy.tile(minValues, (m,1));
    normDataSet = normDataSet/numpy.tile(ranges, (m,1));
    return normDataSet, ranges, minValues;
    
    

def datingClassTest():
    hoRatio = 0.50      #hold out 10%
    datingDataMat,datingLabels = filetoMatrix('C:\\Users\Folorunsho Solomon\\Documents\\GitHub\\ML\\python\\datingTestSet2.txt')       #load data setfrom file
    normMat, ranges, minVals = autonorm(datingDataMat)
    m = normMat.shape[0]
    numTestVecs = int(m*hoRatio)
    errorCount = 0.0
    for i in range(numTestVecs):
        classifierResult = classify0(normMat[i,:],normMat[numTestVecs:m,:],datingLabels[numTestVecs:m],3)
        print "the classifier came back with: %d, the real answer is: %d" % (classifierResult, datingLabels[i])
        if (classifierResult != datingLabels[i]): errorCount += 1.0
    print "the total error rate is: %f" % (errorCount/float(numTestVecs))
    print errorCount
