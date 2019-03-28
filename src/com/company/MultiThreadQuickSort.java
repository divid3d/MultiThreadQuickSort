package com.company;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Class representing multi thread sort
 */
class MultiThreadQuickSort {

    private static final int N_THREADS = Runtime.getRuntime().availableProcessors(); // number of threads available
    private static final int FALLBACK = 2;                                           // fallback constant
    private static Executor threadPool = Executors.newFixedThreadPool(N_THREADS);    // Thread pool executor object


    /**
     * @param input data array to sort
     */
    static <T extends Comparable<T>> void sortData(T[] input) {

        final AtomicInteger count = new AtomicInteger(1);                                           // Atomic integer represents number of running threads
        threadPool.execute(new QuickSortRunnable<>(input, 0, input.length - 1, count));   // Executing new QuickSortRunnable in thread pool
        try {
            synchronized (count) {
                count.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * inner static class implementing Runnable interface
     */
    private static class QuickSortRunnable<T extends Comparable<T>> implements Runnable {

        private final T[] array;        //data to sort
        private final int pLeft;        //start index
        private final int pRight;       //end index
        private final AtomicInteger count; //count of threads running


        /**
         * @param array  data to sort as generic array (object are extending Comparable)
         * @param pLeft  start index of array
         * @param pRight end index of array
         * @param count  count of threads running
         */
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


        /**
         * @param pLeft  start index of array
         * @param pRight end index of array
         */
        void quicksort(int pLeft, int pRight) {
            if (pLeft < pRight) {
                int storeIndex = partition(pLeft, pRight);     // split data
                if (count.get() >= FALLBACK * N_THREADS) {     // check if running new threads possible
                    quicksort(pLeft, storeIndex - 1);   // run quicksort on current thread
                    quicksort(storeIndex + 1, pRight);
                } else {                                       //add 2 Runnables to thread pool and execute
                    count.getAndAdd(2);
                    threadPool.execute(new QuickSortRunnable<>(array, pLeft, storeIndex - 1, count));
                    threadPool.execute(new QuickSortRunnable<>(array, storeIndex + 1, pRight, count));
                }
            }
        }


        /**
         * @param pLeft  start index of array
         * @param pRight end index of array
         * @return index representing point of division of the array
         */
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
         * @param array  input array
         * @param pLeft  element which swaps second element
         * @param pRight second element which swaps first element
         */
        private void swap(T[] array, int pLeft, int pRight) {
            T temp = array[pLeft];
            array[pLeft] = array[pRight];
            array[pRight] = temp;
        }
    }
}

