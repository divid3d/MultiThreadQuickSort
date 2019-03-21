package com.company;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

class MultiThreadQuickSort {

    private static final int N_THREADS = Runtime.getRuntime().availableProcessors();
    private static final int FALLBACK = 2;
    private static Executor pool = Executors.newFixedThreadPool(N_THREADS);


    static <T extends Comparable<T>> void sortData(T[] input) {

        final AtomicInteger count = new AtomicInteger(1);
        pool.execute(new QuickSortRunnable<>(input, 0, input.length - 1, count));
        try {
            synchronized (count) {
                count.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class QuickSortRunnable<T extends Comparable<T>> implements Runnable {

        private final T[] array;
        private final int pLeft;
        private final int pRight;
        private final AtomicInteger count;


        private QuickSortRunnable(T[] array, int pLeft, int pRight, AtomicInteger count) {
            this.array = array;
            this.pLeft = pLeft;
            this.pRight = pRight;
            this.count = count;
        }


        @Override
        public void run() {
            quicksort(pLeft, pRight);
            synchronized (count) {
                if (count.getAndDecrement() == 1)
                    count.notify();
            }
        }


        void quicksort(int pLeft, int pRight) {
            if (pLeft < pRight) {
                int storeIndex = partition(pLeft, pRight);
                if (count.get() >= FALLBACK * N_THREADS) {
                    quicksort(pLeft, storeIndex - 1);
                    quicksort(storeIndex + 1, pRight);
                } else {
                    count.getAndAdd(2);
                    pool.execute(new QuickSortRunnable<>(array, pLeft, storeIndex - 1, count));
                    pool.execute(new QuickSortRunnable<>(array, storeIndex + 1, pRight, count));
                }
            }
        }


        private int partition(int pLeft, int pRight) {
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

        /**
         * Simple swap method.
         *
         * @param pLeft  The index of the first value to swap with the second value.
         * @param pRight The index of the second value to swap with the first value.
         */
        private void swap(T[] array, int pLeft, int pRight) {
            T temp = array[pLeft];
            array[pLeft] = array[pRight];
            array[pRight] = temp;
        }
    }
}

