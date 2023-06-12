package com.jgx.lamdas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Collections {

    public static void main(String[] args) {

        List<String> words = new ArrayList<>();
        words.add("AAA");
        words.add("EEE");
        words.add("bbb");
        words.add("ddd");
        words.add("CCC");

        System.out.println("----CASE SENSITIVE----");
        sortCaseSensitive(words);
        printCollection(words);

        System.out.println("----CASE INSENSITIVE----");
        sortCaseInsensitive(words);
        printCollection(words);
    }

    private static void sortCaseSensitive(List<String> words) {
        // CASE SENSITIVE

        java.util.Collections.sort(words, String::compareTo);
    }

    private static void sortCaseInsensitive(List<String> words) {
        // CASE INSENSITIVE

        java.util.Collections.sort(words, String::compareToIgnoreCase);
    }

    private static <Z extends Object> void printCollection(Collection<Z> collection) {
        for (Z string : collection) {
            System.out.println(string);
        }
    }

}
