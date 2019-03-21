package com.company;

public class SortMeasure {

    private double singleThreadSortTime;
    private double multiThreadSortTime;
    private double arraySortTime;
    private double parallelSortTime;
    private int numberOfElements;
    private boolean isSingleSortedCorrectly;
    private boolean isMultiSortedCorrectly;
    private boolean isArraySortedCorrectly;
    private boolean isParallelSortedCorrectly;
    private double iterationExecutionTime;
    private int averageOf;

    SortMeasure(double singleThreadSortTime, double multiThreadSortTime, double arraySortTime, double parallelSortTime
            , int numberOfElements, boolean isSortedCorrectly, boolean isMultiSortedCorrectly, boolean isArraySortedCorrectly, boolean isParallelSortedCorrectly
            , int averageOf, double iterationExecutionTime) {
        this.singleThreadSortTime = singleThreadSortTime;
        this.multiThreadSortTime = multiThreadSortTime;
        this.arraySortTime = arraySortTime;
        this.parallelSortTime = parallelSortTime;
        this.numberOfElements = numberOfElements;
        this.isSingleSortedCorrectly = isSortedCorrectly;
        this.isMultiSortedCorrectly = isMultiSortedCorrectly;
        this.isArraySortedCorrectly = isArraySortedCorrectly;
        this.isParallelSortedCorrectly = isParallelSortedCorrectly;
        this.averageOf = averageOf;
        this.iterationExecutionTime = iterationExecutionTime;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public double getMultiThreadSortTime() {
        return multiThreadSortTime;
    }

    public double getSingleThreadSortTime() {
        return singleThreadSortTime;
    }

    public boolean isSingleSortedCorrectly() {
        return isSingleSortedCorrectly;
    }

    public boolean isMultiSortedCorrectly() {
        return isMultiSortedCorrectly;
    }

    public void printMeasure() {
        System.out.println(toString() + "\tAverage of: " + this.averageOf + "\tExecution time: " + this.iterationExecutionTime);
    }

    @Override
    public String toString() {

        return (this.numberOfElements + ",") +
                this.singleThreadSortTime + "," +
                this.multiThreadSortTime + "," +
                this.arraySortTime + "," +
                this.parallelSortTime + "," +
                this.isSingleSortedCorrectly + "," +
                this.isMultiSortedCorrectly + "," +
                this.isArraySortedCorrectly + "," +
                this.isParallelSortedCorrectly;
    }
}
