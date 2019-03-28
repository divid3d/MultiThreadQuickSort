package com.company;

/**
 * Class representation single thread sort
 */
class SingleThreadQuickSort {

    /**
     * @param array  input array to be sorted (extending Comparable)
     * @param pLeft  start index of array
     * @param pRight end index of array
     */
    static <T extends Comparable<T>> void sortData(T[] array, int pLeft, int pRight) {

        if (pLeft < pRight) {
            int storeIndex = partition(array, pLeft, pRight);
            sortData(array, pLeft, storeIndex - 1);
            sortData(array, storeIndex + 1, pRight);
        }
    }

    /**
     * @param array  array which elements going to be swapped
     * @param pLeft  element which swaps second element
     * @param pRight second element shich swaps first element
     */
    private static <T extends Comparable<T>> void swap(T[] array, int pLeft, int pRight) {
        T temp = array[pLeft];
        array[pLeft] = array[pRight];
        array[pRight] = temp;
    }

    /**
     * @param array  input data array
     * @param pLeft  start index of array
     * @param pRight end index of array
     * @return index representing point of division of the array
     */
    private static <T extends Comparable<T>> int partition(T[] array, int pLeft, int pRight) {
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
