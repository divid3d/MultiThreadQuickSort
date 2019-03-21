package com.company;

class SingleThreadQuickSort {

    static <T extends Comparable<T>> void sortData(T[] array, int pLeft, int pRight) {

        if (pLeft < pRight) {
            int i = pLeft;
            int j = pRight;

            T center = array[(i + j) / 2];

            do {
                while (array[i].compareTo(center) < 0) i++;
                while (center.compareTo(array[j]) < 0) j--;

                if (i <= j) {
                    swap(array, i, j);
                    i++;
                    j--;
                }

            } while (i <= j);

            sortData(array, pLeft, j);
            sortData(array, i, pRight);
        }
    }

    private static <T extends Comparable<T>> void swap(T[] array, int pLeft, int pRight) {
        T temp = array[pLeft];
        array[pLeft] = array[pRight];
        array[pRight] = temp;
    }
}
