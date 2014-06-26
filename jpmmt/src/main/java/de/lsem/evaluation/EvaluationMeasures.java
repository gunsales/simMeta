package de.lsem.evaluation;

import java.util.ArrayList;
import java.util.List;

public class EvaluationMeasures {
	private double stdPrecision;
	private double stdRecall;
	private double stdFmeasure;
	private double meanPrecision;
	private double meanRecall;
	private double meanFmeasure;
	private int numberTps = 0;
	private int numberFps = 0;
	private int numberFns = 0;
	private List<Integer> tps;
	private List<Integer> fps;
	private List<Integer> fns;
	
	public EvaluationMeasures() {
		this.fns = new ArrayList<Integer>();
		this.fps = new ArrayList<Integer>();
		this.tps = new ArrayList<Integer>();
	}
	
	public double getStdPrecision() {
		return stdPrecision;
	}
	
	public void setStdPrecision(double stdPrecision) {
		this.stdPrecision = stdPrecision;
	}
	
	public double getStdRecall() {
		return stdRecall;
	}
	
	public void setStdRecall(double stdRecall) {
		this.stdRecall = stdRecall;
	}
	
	public double getStdFmeasure() {
		return stdFmeasure;
	}
	
	public void setStdFmeasure(double stdFmeasure) {
		this.stdFmeasure = stdFmeasure;
	}
	
	public double getMeanPrecision() {
		return meanPrecision;
	}
	
	public void setMeanPrecision(double meanPrecision) {
		this.meanPrecision = meanPrecision;
	}
	
	public double getMeanRecall() {
		return meanRecall;
	}
	
	public void setMeanRecall(double meanRecall) {
		this.meanRecall = meanRecall;
	}
	
	public double getMeanFmeasure() {
		return meanFmeasure;
	}
	
	public void setMeanFmeasure(double meanFmeasure) {
		this.meanFmeasure = meanFmeasure;
	}

	public void add(int tp, int fp, int fn) {
		this.tps.add(tp);
		this.fps.add(fp);
		this.fns.add(fn);
	}
	
	public int getNumberOfDatasets() {
		return this.tps.size();
	}

	public void calculate() {
		List<Double> precisions = new ArrayList<Double>();
		List<Double> recalls = new ArrayList<Double>();
		List<Double> fmeasures = new ArrayList<Double>();
		
		for (int a = 0; a < this.tps.size(); a++) {
			double tp = this.tps.get(a);
			double fp = this.fps.get(a);
			double fn = this.fns.get(a);
			double precision = (tp + fp == 0) ? 1 : tp / (tp + fp);
			double recall = tp / (tp + fn);
			double fmeasure = (precision + recall == 0) ? 0 : 2 * precision * recall / (precision + recall);			
			precisions.add(precision);
			recalls.add(recall);
			fmeasures.add(fmeasure);
			this.numberFns += fn;
			this.numberFps += fp;
			this.numberTps += tp;
		}
		
		this.meanPrecision = this.calculateMean(precisions);
		this.meanRecall = this.calculateMean(recalls);
		this.meanFmeasure = this.calculateMean(fmeasures);
		
		this.stdPrecision = this.calculateStd(precisions, this.getMeanPrecision());
		this.stdRecall = this.calculateStd(recalls, this.getMeanRecall());
		this.stdFmeasure = this.calculateStd(fmeasures, this.getMeanFmeasure());		
	}
	
	public int getNumberTps() {
		return this.numberTps;
	}
	
	public int getNumberFps() {
		return this.numberFps;
	}
	
	public int getNumberFns() {
		return this.numberFns;
	}
	
	public void setNumberTps(int number) {
		this.numberTps = number;
	}
	
	public void setNumberFns(int number) {
		this.numberFns = number;
	}
	
	public void setNumberFps(int number) {
		this.numberFps = number;
	}

	private double calculateStd(List<Double> values, double mean) {
		double std = 0;
		
		for (Double value : values) {
			std += Math.pow(value - mean, 2);
		}
		std = Math.sqrt(std / values.size());
		
		return std;
	}

	private double calculateMean(List<Double> values) {
		double mean = 0;
		
		for (Double val : values) {
			mean += val;
		}
		
		return mean / values.size();
	}
	
}
