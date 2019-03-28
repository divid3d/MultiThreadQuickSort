package com.company;


import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Class to generate testing data to be sorted
 */
class DataGenerator {


    /**
     * @param size size of the generated data
     * @param sortCase constant representing order of elements
     * @return array of data with length of size
     */
    static <T extends Comparable<T>> T[] generateIntegerData(int size, SortCase sortCase) {

        Integer data[] = new Integer[size];

        switch (sortCase) {

            case SORTED_RANDOM:                         //generate random data
                for (int i = 0; i < size; i++) {
                    data[i] = generateInteger();
                }
                break;

            case SORTED_REVERSED:                       //generate reversed order data
                for (int i = 0; i < size; i++) {
                    data[i] = size - i;
                }
                break;

            case SORTED_INORDER:
                for (int i = 0; i < size; i++) {        //generate in order data
                    data[i] = i;
                }
                break;
        }
        return (T[]) data;
    }

    static <T extends Comparable<T>> T[] generateDecimalData(int size, SortCase sortCase) {

        Float data[] = new Float[size];

        switch (sortCase) {

            case SORTED_RANDOM:
                for (int i = 0; i < size; i++) {
                    data[i] = generateDecimal();
                }
                break;

            case SORTED_REVERSED:
                for (int i = 0; i < size; i++) {
                    data[i] = (float) (size - i);
                }
                break;

            case SORTED_INORDER:
                for (int i = 0; i < size; i++) {
                    data[i] = (float) i;
                }
                break;
        }
        return (T[]) data;
    }

    static <T extends Comparable<T>> T[] generateStringData(int size, SortCase sortCase) {

        String data[] = new String[size];

        switch (sortCase) {

            case SORTED_RANDOM:
                for (int i = 0; i < size; i++) {
                    data[i] = generateString();
                }
                break;

            case SORTED_REVERSED:
                for (int i = 0; i < size; i++) {
                    data[i] = String.valueOf(size - i);
                }
                break;

            case SORTED_INORDER:
                for (int i = 0; i < size; i++) {
                    data[i] = String.valueOf(i);
                }
                break;
        }
        return (T[]) data;
    }


    /**
     * @return random String from random UUID
     */
    private static String generateString() {
        return UUID.randomUUID().toString();
    }

    /**
     * @return random integer value
     */
    private static Integer generateInteger() {
        return ThreadLocalRandom.current().nextInt();
    }

    /**
     * @return random decimal value
     */
    private static Float generateDecimal() {
        return ThreadLocalRandom.current().nextFloat();
    }

    /**
     * enum of data types
     */
    enum DataType {
        TYPE_INTEGER,
        TYPE_DECIMAL,
        TYPE_STRING
    }

    /**
     * enum of generating data case
     */
    public enum SortCase {
        SORTED_RANDOM,
        SORTED_REVERSED,
        SORTED_INORDER
    }
}
