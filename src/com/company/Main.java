package com.company;


public class Main {

    public static void main(String[] args) {

        SortTester sortTester = new SortTester(0, 6000000, 25000, 5);   //create SortTester object from 0 to 6M elements, step of 25k elements, 5 repeats each
        sortTester.setShowTerminalInfo(true);                                                 //show terminal info
        sortTester.setSaveResultToFile(true);                                                 //save result to file
        sortTester.setDataType(DataGenerator.DataType.TYPE_INTEGER);                          //generate data type of integer
        sortTester.setSortCase(DataGenerator.SortCase.SORTED_RANDOM);                         //random data value case
        sortTester.startTest();                                                               //start execution
    }
}
