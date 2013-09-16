import numpy;
import operator;

def createdataset():
    
    group = numpy.array([ [1.0,1.1],[1.0,1.0],[0,0],[0,0.1] ]);
    
    labels = ['A','A','B','B'];
    
    return group,labels;
    
    
    
def classify0(inX,dataset,labels,k):
    dataSetSize = dataset.shape[0];
    
    return;