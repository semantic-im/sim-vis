package ro.utcluj.larkc.visualization.server.rf;

import java.io.*;

import weka.core.*;
import weka.classifiers.functions.LinearRegression;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

public class Predictor {

	private Instances trainingData;
	private Instances filteredData;
	
	private LinearRegression lr;
	private double [] coef;
	
	public void setTrainingInstances(Instances in)
	{
		trainingData=in;
		trainingData.setClassIndex(trainingData.numAttributes()-1);
		filteredData = new Instances(trainingData);
	}
	
	public void setConsideredAttributes(String[] attrNames)
	{
		String attrIndices="";
		for (int i=0; i<attrNames.length; i++)
		{
			Attribute a = filteredData.attribute(attrNames[i]);
			if (i!=0) attrIndices+=","; 
			attrIndices+=(a.index()+1);
		}
		setConsideredAttributes(attrIndices);
	}
	
	public void setConsideredAttributes(String attrIndices)
	{
		Remove rFilter = new Remove();
		rFilter.setInvertSelection(true);
		rFilter.setAttributeIndices(attrIndices);
		try {
			rFilter.setInputFormat(trainingData);
			filteredData = Filter.useFilter(trainingData, rFilter);
			filteredData. setClassIndex(filteredData.numAttributes()-1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setRemovedAttributes(String attrIndices)
	{
		Remove rFilter = new Remove();
		rFilter.setInvertSelection(false);
		rFilter.setAttributeIndices(attrIndices);
		try {
			rFilter.setInputFormat(trainingData);
			filteredData = Filter.useFilter(trainingData, rFilter);
			filteredData. setClassIndex(filteredData.numAttributes()-1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void setClassAttribute(String className)
	{
		Attribute a = filteredData.attribute(className);
		filteredData.setClassIndex(a.index());
	}
	
	public void setClassAttribute(int attributeNo)
	{
		filteredData.setClassIndex(attributeNo-1);
	}

	public double[] getCoefficients()
	{
		return coef;
	}

	public Instances getFilteredData()
	{
		return filteredData;
	}

	public void buildRegressionModel()
	{
		lr=new LinearRegression();
//		Tag[] t=new Tag[1];
//		t[0]=new Tag(1,"");
//		SelectedTag s = new SelectedTag(1, t);
//		lr.setAttributeSelectionMethod(s);
		lr.setAttributeSelectionMethod(new SelectedTag(1, LinearRegression.TAGS_SELECTION));
		try {
			lr.buildClassifier(filteredData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		coef=lr.coefficients();
			
	}	
	public void loadTrainingInstances(String fileName)
	{
		BufferedReader in;
		try {
			in = new BufferedReader(new FileReader(fileName));
			try {
				trainingData = new Instances(in);
				trainingData.setClassIndex(trainingData.numAttributes()-1);
				filteredData = new Instances(trainingData);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public double predictTestInstance(Instance testInstance)
	{
		try {
			return lr.classifyInstance(testInstance);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public double predictTestInstance(String fileName)
	{
		BufferedReader in;
		Instances testInstances;
		try {
			in = new BufferedReader(new FileReader(fileName));
			try {
				testInstances = new Instances(in);
				Instance testInstance = testInstances.instance(0);
				return lr.classifyInstance(testInstance);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		return 0;
	}
}