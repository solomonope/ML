import numpy;
import operator;
import math;


def createDataSet():
    dataSet = [[1, 1, 'yes'],
               [1, 1, 'yes'],
               [1, 0, 'no'],
               [0, 1, 'no'],
               [0, 1, 'no']]
    labels = ['no surfacing','flippers']
    #change to discrete values
    return dataSet, labels
    
    
def calcShannonEnt(dataset):
    numEntries = len(dataset);
    labelCounts = {};
    
    for featVec in dataset:
        currentLabel = featVec[-1];
        
        if (currentLabel not in labelCounts.keys()):
            labelCounts[currentLabel] = 0;
        labelCounts[currentLabel] += 1;
    shannonEnt = 0.0;
    
    for key in labelCounts:
        
        prob = float(labelCounts[key])/numEntries;
        shannonEnt -= prob * math.log(prob,2);

    return shannonEnt;
    
    

def splitDataSet(dataSet,axis,value):
    #beginfunctioblock
    
    retDataSet = [];
    
    for featVec in dataSet:
        #beginforblock
        
        if( featVec[axis] == value ):
            #beginifblock
            reducedFeatVec  = featVec[:axis];
            reducedFeatVec.extend(featVec[axis+1:])
            retDataSet.append(reducedFeatVec)
            #endifblock
        #endforblock
    
    return retDataSet;
    #endfunctionblock

def chooseBestFeatureToSplit(dataSet):
    #beginfunctionblock
    numFeatures = len(dataSet[0]) - 1;
    baseEntropy = 0.0;
    bestInfoGain = 0.0;
    bestFeature = -1;
    
    for i in range(numFeatures):
        #beginforblock
        featList = [example[i] for example in dataSet];#create a list of all the examples of this feature
        uniqueVals = set(featList);       #get a set of unique values
        newEntropy = 0.0;
        for value in uniqueVals:
            #beginforblock 
            subDataSet = splitDataSet(dataSet, i, value);
            prob = len(subDataSet)/float(len(dataSet));
            newEntropy += prob * calcShannonEnt(subDataSet);
            #endforblock     
        infoGain = baseEntropy - newEntropy     #calculate the info gain; ie reduction in entropy
        if (infoGain > bestInfoGain):      #compare this to the best gain so far
            #beginifblock
            bestInfoGain = infoGain         #if better than current best, set to best
            bestFeature = i
            #endifblock
        #endforblock
    
    
    return bestFeature;
    #endfunctionblock
    
    
    def majorityCnt(classList):
        #{
        
        classCount = {};
        
        for vote in classList:
            #{
            
            if vote not in classList.keys():
                #{
                
                classCount[vote] = 0;
                #}
            classCount[vote] += 1;
            #}
        sortedClassCount = sorted(classCount.iteritems(), key=operator.itemgetter(1), reverse=True)
    
        
        return sortedClassCount[0][0];
        #}