package com.company;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Class to perform sorting testing on various sorting methods
 */
class SortTester {

    private final int MIN_DATA_COUNT;
    private final int MAX_DATA_COUNT;
    private final int NUMBER_OF_STEPS;
    private final int REPEATS;
    private boolean showInfo = false;
    private boolean saveAsFile = false;
    private DataGenerator.SortCase sortCase = DataGenerator.SortCase.SORTED_RANDOM;
    private List<SortMeasure> sortMeasures = new ArrayList<>();
    private DataGenerator.DataType dataType = DataGenerator.DataType.TYPE_INTEGER;

    /**
     * @param min     min number of elements
     * @param max     max number of elements
     * @param step    step to increase current number of elements
     * @param repeats repeats for each number of elements
     */
    SortTester(int min, int max, int step, int repeats) {
        this.MIN_DATA_COUNT = min;
        this.MAX_DATA_COUNT = max;
        this.NUMBER_OF_STEPS = step;
        this.REPEATS = repeats;
    }

    void setDataType(DataGenerator.DataType dataType) {
        this.dataType = dataType;
    }

    void setShowTerminalInfo(boolean showInfo) {
        this.showInfo = showInfo;
    }

    void setSaveResultToFile(boolean saveAsFile) {
        this.saveAsFile = saveAsFile;
    }

    /**
     * method to start performing test
     */
    <T extends Comparable<T>> void startTest() {

        for (int i = MIN_DATA_COUNT; i <= MAX_DATA_COUNT; i += NUMBER_OF_STEPS) {       //main for loop
            final long[] singleThreadSortTimes = new long[REPEATS];
            final long[] multiThreadSortTimes = new long[REPEATS];
            final long[] arraySortTimes = new long[REPEATS];
            final long[] parallelSortTimes = new long[REPEATS];
            final boolean[] isSingleSortedCorrectly = new boolean[REPEATS];
            final boolean[] isMultiSortedCorrectly = new boolean[REPEATS];
            final boolean[] isArraySortedCorrectly = new boolean[REPEATS];
            final boolean[] isParallelSortCorrectly = new boolean[REPEATS];

            long iterationTimeStart = System.nanoTime();                              //time of starting procedure execution

            for (int j = 0; j < REPEATS; j++) {                                       //generating test data
                T[] dataSet;
                switch (dataType) {
                    case TYPE_INTEGER:
                        dataSet = DataGenerator.generateIntegerData(i, sortCase);
                        break;

                    case TYPE_DECIMAL:
                        dataSet = DataGenerator.generateDecimalData(i, sortCase);
                        break;

                    case TYPE_STRING:
                        dataSet = DataGenerator.generateStringData(i, sortCase);
                        break;

                    default:
                        dataSet = DataGenerator.generateIntegerData(i, sortCase);
                        break;
                }

                T[] dataSetCpy1 = dataSet.clone();                                  //creating 3 copies of test data for each sorting method
                T[] dataSetCpy2 = dataSet.clone();
                T[] dataSetCpy3 = dataSet.clone();

                long singleThreadStartTime = System.nanoTime();                                     //start time of sorting by single thread implementation of quicksort
                SingleThreadQuickSort.sortData(dataSet, 0, dataSet.length - 1);         //start of sorting
                singleThreadSortTimes[j] = (System.nanoTime() - singleThreadStartTime) / 1000000;   //end time of sorting
                isSingleSortedCorrectly[j] = isSorted(dataSet);                                     //check is sorted correctly


                long multiThreadStartTime = System.nanoTime();
                MultiThreadQuickSort.sortData(dataSetCpy1);
                multiThreadSortTimes[j] = (System.nanoTime() - multiThreadStartTime) / 1000000;
                isMultiSortedCorrectly[j] = isSorted(dataSetCpy1);

                long arraySortStartTime = System.nanoTime();
                Arrays.sort(dataSetCpy2);
                arraySortTimes[j] = (System.nanoTime() - arraySortStartTime) / 1000000;
                isArraySortedCorrectly[j] = isSorted(dataSetCpy2);

                long parallelSortStartTime = System.nanoTime();
                Arrays.parallelSort(dataSetCpy3);
                parallelSortTimes[j] = (System.nanoTime() - parallelSortStartTime) / 1000000;
                isParallelSortCorrectly[j] = isSorted(dataSetCpy3);

            }
            double iterationExecuteTime = ((double) (System.nanoTime() - iterationTimeStart)) / 1000000;        //end time of iteration for current number of elements


            //adding sort data report to list as new SortMeasure object
            sortMeasures.add(new SortMeasure(average(singleThreadSortTimes), average(multiThreadSortTimes), average(arraySortTimes), average(parallelSortTimes)
                    , i, containsFalse(isSingleSortedCorrectly), containsFalse(isMultiSortedCorrectly)
                    , containsFalse(isArraySortedCorrectly), containsFalse(isParallelSortCorrectly), REPEATS, iterationExecuteTime));
            if (showInfo) {                                                 //print info at terminal if needed
                sortMeasures.get(sortMeasures.size() - 1).printMeasure();
            }
        }

        if (saveAsFile) {                                                   //save data to CSV if needed
            saveFile();
        }
    }


    private void saveFile() {

        if (!sortMeasures.isEmpty()) {
            StringBuilder dataToSave = new StringBuilder();                   //if list not empty create StringBuilder

            dataToSave.append("number of Elements").append(",")               //append headers
                    .append("single thread sort time").append(",")
                    .append("multi thread sort time").append(",")
                    .append("arraySort  sort time").append(",")
                    .append("parallelSort sort time").append(",")
                    .append("is single thread sorted correctly").append(",")
                    .append("is multi thread sorted correctly").append(",")
                    .append("is arraySort sorted correctly").append(",")
                    .append("is parallelSort sorted correctly").append(",")
                    .append("Iteration Time")
                    .append("\n");

            for (SortMeasure measure : sortMeasures) {                       //append data as rows
                dataToSave.append(measure.toString()).append("\n");
            }

            String date = new SimpleDateFormat("dd_MM_yyyyy_HH_mm_ss_").format(new Date());      //name of file as current date

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(date + "sortDataReport.csv"))) { //write data from StringBuilder
                writer.write(dataToSave.toString());
                System.out.println("File " + date + "sortDataReport.txt" + " saved successfully");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("File " + date + "sortDataReport.txt" + " save error");
            }
        }
    }

    private static <T> void printData(T[] data) {
        for (T element : data) {
            System.out.println(element.toString());
        }
    }

    /**
     * @param data sorted array of data
     * @return returns true if sorted correctly, false if not
     */
    private static <T extends Comparable<T>> boolean isSorted(T[] data) {
        for (int i = 1; i < data.length; i++) {
            if (data[i].compareTo(data[i - 1]) < 0) {
                return false;
            }
        }
        return true;
    }

    void setSortCase(DataGenerator.SortCase sortCase) {
        this.sortCase = sortCase;
    }

    /**
     * @param array arrays of booleans which each element represents is data sorted correctly in repeat
     * @return true if data sorted correctly in every repeat, false if not
     */
    private boolean containsFalse(boolean[] array) {
        for (boolean val : array) {
            if (!val)
                return false;
        }
        return true;
    }

    /**
     * @param values array with timestamps values
     * @return average value per repeat
     */
    private double average(long[] values) {
        long sum = 0;
        for (long value : values) {
            sum += value;
        }
        return (sum * 1.0) / values.length;
    }
}
