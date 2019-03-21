package com.company;

class SingleThreadQuickSort {

    static <T extends Comparable<T>> void sortData(T[] array, int pLeft, int pRight) {

        if (pLeft < pRight) {
            int storeIndex = partition(array,pLeft,pRight);
            sortData(array, pLeft, storeIndex-1);
            sortData(array, storeIndex+1, pRight);
        }
    }

    private static <T extends Comparable<T>> void swap(T[] array, int pLeft, int pRight) {
        T temp = array[pLeft];
        array[pLeft] = array[pRight];
        array[pRight] = temp;
    }

    private static <T extends Comparable<T>> int partition(T[] array,int pLeft, int pRight) {
        T pivotValue = array[pRight];
        int storeIndex = pLeft;
        for (int i = pLeft; i < pRight; i++) {
            if (array[i].compareTo(pivotValue) < 0) {
                swap(array, i, storeIndex);
                storeIndex++;
            }
        }
        swap(array, storeIndex, pRight);
        return storeIndex;
    }
}
