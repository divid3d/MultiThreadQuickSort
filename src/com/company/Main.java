package com.company;


public class Main {

    public static void main(String[] args) {

        SortTester sortTester = new SortTester(0, 6000000, 25000, 5);
        sortTester.setShowTerminalInfo(true);
        sortTester.setSaveResultToFile(true);
        sortTester.setDataType(DataGenerator.DataType.TYPE_INTEGER);
        sortTester.setSortCase(DataGenerator.SortCase.SORTED_RANDOM);
        sortTester.startTest();
    }
}
