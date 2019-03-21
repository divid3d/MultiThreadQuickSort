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
    private boolean isParallelSortedCorrecly;
    private double itrerationExecutionTime;
    private int averageOf;

    SortMeasure(double singleThreadSortTime, double multiThreadSortTime, double arraySortTime, double parallelSortTime
            , int numberOfElements, boolean isSortedCorrectly, boolean isMultiSortedCorrectly, boolean isArraySortedCorrectly, boolean isParallelSortedCorrecly
            , int averageOf, double itrerationExecutionTime) {
        this.singleThreadSortTime = singleThreadSortTime;
        this.multiThreadSortTime = multiThreadSortTime;
        this.arraySortTime = arraySortTime;
        this.parallelSortTime = parallelSortTime;
        this.numberOfElements = numberOfElements;
        this.isSingleSortedCorrectly = isSortedCorrectly;
        this.isMultiSortedCorrectly = isMultiSortedCorrectly;
        this.isArraySortedCorrectly = isArraySortedCorrectly;
        this.isParallelSortedCorrecly = isParallelSortedCorrecly;
        this.averageOf = averageOf;
        this.itrerationExecutionTime = itrerationExecutionTime;
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
        System.out.println(toString());
    }

    @Override
    public String toString() {

        return (this.numberOfElements + "\t") +
                this.singleThreadSortTime + "," +
                this.multiThreadSortTime + ","+
                this.arraySortTime + "," +
                this.parallelSortTime + "\t" +
                this.isSingleSortedCorrectly + "," +
                this.isMultiSortedCorrectly + "," +
                this.isArraySortedCorrectly + "," +
                this.isParallelSortedCorrecly + "\t" +
                "(Average of: " + this.averageOf +
                ". Execution time: "+this.itrerationExecutionTime+")";
    }
}
