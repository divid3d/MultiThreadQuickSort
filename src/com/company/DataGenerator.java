package com.company;


import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class DataGenerator {


    static <T extends Comparable<T>> T[] generateIntegerData(int size, SortCase sortCase) {

        Integer data[] = new Integer[size];

        switch (sortCase) {

            case SORTED_RANDOM:
                for (int i = 0; i < size; i++) {
                    data[i] = generateInteger();
                }
                break;

            case SORTED_REVERSED:
                for (int i = 0; i < size; i++) {
                    data[i] = size - i;
                }
                break;

            case SORTED_INORDER:
                for (int i = 0; i < size; i++) {
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


    public static String generateString() {
        return UUID.randomUUID().toString();
    }

    public static Integer generateInteger() {
        return ThreadLocalRandom.current().nextInt();
    }

    public static Float generateDecimal() {
        return ThreadLocalRandom.current().nextFloat();
    }

    enum DataType {
        TYPE_INTEGER,
        TYPE_DECIMAL,
        TYPE_STRING
    }
}
