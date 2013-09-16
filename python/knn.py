import numpy;
import operator;

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