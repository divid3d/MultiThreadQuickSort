package com.company;

/**
 * Class for storing information about sorting iterations
 */
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

    /**
     * @param singleThreadSortTime      time of sorting by single thread implementation of  quicksort
     * @param multiThreadSortTime       time of sorting by multi thread implementation of quicksort
     * @param arraySortTime             time of sorting by Arrays.sort() Java method
     * @param parallelSortTime          time of sorting by Arrays.parallelSort() Java method
     * @param numberOfElements          number of elements to sort
     * @param isSortedCorrectly         is single thread implementation of quicksort sorted correctly
     * @param isMultiSortedCorrectly    is multi thread implementation of quicksort sorted correctly
     * @param isArraySortedCorrectly    is Arrays.sort() sorted correctly
     * @param isParallelSortedCorrectly is Arrays().parallelsort() sorted correctly
     * @param averageOf                 Number of repeats
     * @param iterationExecutionTime    Execution time of whole iteration for numberOfElements
     */
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

    /**
     * @return SortMeasure object in String representation
     */
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
