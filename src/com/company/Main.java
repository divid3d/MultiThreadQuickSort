package com.company;


public class Main {

    public static void main(String[] args) {

        SortTester sortTester = new SortTester(0, 2000000, 25000, 10);
        sortTester.setShowTerminalInfo(true);
        sortTester.setSaveResultToFile(false);
        sortTester.setDataType(DataGenerator.DataType.TYPE_DECIMAL);
        sortTester.setSortCase(SortCase.SORTED_RANDOM);
        sortTester.startTest();
    }
}
