package com.company;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

class SortTester {

    private final int MIN_DATA_COUNT;
    private final int MAX_DATA_COUNT;
    private final int NUMBER_OF_STEPS;
    private final int REPEATS;
    private boolean showInfo = false;
    private boolean saveAsFile = false;
    private SortCase sortCase = SortCase.SORTED_RANDOM;
    private List<SortMeasure> sortMeasures = new ArrayList<>();
    private DataGenerator.DataType dataType;

    SortTester(int min, int max, int step, int repeats) {
        this.MIN_DATA_COUNT = min;
        this.MAX_DATA_COUNT = max;
        this.NUMBER_OF_STEPS = step;
        this.REPEATS = repeats;
    }

    public void setDataType(DataGenerator.DataType dataType) {
        this.dataType = dataType;
    }

    void setShowTerminalInfo(boolean showInfo) {
        this.showInfo = showInfo;
    }

    void setSaveResultToFile(boolean saveAsFile) {
        this.saveAsFile = saveAsFile;
    }

    <T extends Comparable<T>> void startTest() {

        for (int i = MIN_DATA_COUNT; i <= MAX_DATA_COUNT; i += NUMBER_OF_STEPS) {
            final long[] singleThreadSortTimes = new long[REPEATS];
            final long[] multiThreadSortTimes = new long[REPEATS];
            final long[] arraySortTimes = new long[REPEATS];
            final long[] pararellSortTimes = new long[REPEATS];
            final boolean[] isSingleSortedCorrectly = new boolean[REPEATS];
            final boolean[] isMultiSortedCorrectly = new boolean[REPEATS];
            final boolean[] isArraySortedCorrectly = new boolean[REPEATS];
            final boolean[] isParallelSortCorrectly = new boolean[REPEATS];

            long iterationTimeStart = System.nanoTime();

            for (int j = 0; j < REPEATS; j++) {
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
                        dataSet = null;
                }

                T[] dataSetCpy1 = dataSet.clone();
                T[] dataSetCpy2 = dataSet.clone();
                T[] dataSetCpy3 = dataSet.clone();

                long singleThreadStartTime = System.nanoTime();
                SingleThreadQuickSort.sortData(dataSet, 0, dataSet.length - 1);
                singleThreadSortTimes[j] = (System.nanoTime() - singleThreadStartTime) / 1000000;
                isSingleSortedCorrectly[j] = isSorted(dataSet);


                long multiThreadStartTime = System.nanoTime();
                MultiThreadQuickSort.sortData(dataSetCpy1);
                multiThreadSortTimes[j] = (System.nanoTime() - multiThreadStartTime) / 1000000;
                isMultiSortedCorrectly[j] = isSorted(dataSetCpy1);

                long arraySortStartTime = System.nanoTime();
                Arrays.sort(dataSetCpy2);
                arraySortTimes[j] = (System.nanoTime() - multiThreadStartTime) / 1000000;
                isArraySortedCorrectly[j] = isSorted(dataSetCpy2);

                long parallelSortStartTime = System.nanoTime();
                Arrays.parallelSort(dataSetCpy3);
                pararellSortTimes[j] = (System.nanoTime() - multiThreadStartTime) / 1000000;
                isParallelSortCorrectly[j] = isSorted(dataSetCpy3);

            }
            double iterationExecuteTime = ((double)(System.nanoTime() - iterationTimeStart))/1000000;

            sortMeasures.add(new SortMeasure(average(singleThreadSortTimes), average(multiThreadSortTimes), average(arraySortTimes), average(pararellSortTimes)
                    , i, containsFalse(isSingleSortedCorrectly), containsFalse(isMultiSortedCorrectly)
                    , containsFalse(isArraySortedCorrectly), containsFalse(isParallelSortCorrectly), REPEATS, iterationExecuteTime));
            if (showInfo) {
                sortMeasures.get(sortMeasures.size() - 1).printMeasure();
            }
        }

        if (saveAsFile) {
            saveFile();
        }
    }


    private void saveFile() {

        if (!sortMeasures.isEmpty()) {
            StringBuilder dataToSave = new StringBuilder();

            dataToSave.append("number of Elements").append(",")
                    .append("single thread sort time").append(",")
                    .append("multi thread sort time").append(",")
                    .append("is single thread sorted correctly").append(",")
                    .append("is multi thread sorted correctly")
                    .append("\n");

            for (SortMeasure measure : sortMeasures) {
                dataToSave.append(measure.toString()).append("\n");
            }

            String date = new SimpleDateFormat("dd_MM_yyyyy_HH_mm_ss_").format(new Date());

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(date + "sortDataReport.csv"))) {
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

    private static <T extends Comparable<T>> boolean isSorted(T[] data) {
        for (int i = 1; i < data.length; i++) {
            if (data[i].compareTo(data[i - 1]) < 0) {
                return false;
            }
        }
        return true;
    }

    void setSortCase(SortCase sortCase) {
        this.sortCase = sortCase;
    }

    private boolean containsFalse(boolean[] array) {

        for (boolean val : array) {
            if (!val)
                return false;
        }

        return true;
    }

    private double average(long[] vals) {
        long sum = 0;

        for (long val : vals) {
            sum += val;
        }
        return (sum * 1.0) / vals.length;
    }

}
