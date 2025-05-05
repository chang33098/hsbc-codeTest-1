package org.example;

import org.example.dictionary.CompositeDictionary;
import org.example.dictionary.NormalDictionary;
import org.example.loader.CollectionDictionaryLoader;
import org.example.loader.DictionaryLoader;
import org.example.splitter.SentenceSplitter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws Exception {
        String sentence = "ilikesamsungmobile";

        SentenceSplitter sentenceSplitter = new SentenceSplitter();

        /**
         * Stage 1 - requirements
         * Given a valid sentence without any spaces between the words and a dictionary of valid
         * English words, find all possible ways to break the sentence in individual dictionary words.
         */
        DictionaryLoader collectionDictionaryLoader1 = new CollectionDictionaryLoader(Set.of("i", "like", "sam", "sung", "samsung", "mobile", "ice", "cream", "man", "go"));
        NormalDictionary normalDictionary1 = new NormalDictionary(collectionDictionaryLoader1);

        List<String> result1 = sentenceSplitter.split(sentence, normalDictionary1);
        System.out.println("result1: " + result1);

        /**
         * Stage 2 - new requirements
         * If user provide a customized dictionary of valid English words as additional input, and the
         * program will only find in the user customized dictionary
         */
        DictionaryLoader collectionDictionaryLoader2 = new CollectionDictionaryLoader(Stream.of("i", "like", "sam", "samsung", "samsun", "g", "mobile").collect(Collectors.toSet()));
        NormalDictionary normalDictionary2 = new NormalDictionary(collectionDictionaryLoader2);

        List<String> result2 = sentenceSplitter.split(sentence, normalDictionary2);
        System.out.println("result2: " + result2);

        /**
         * Stage 3 - new requirements
         * If user provide a customized dictionary of valid English words as additional input, and the
         * program will find all the valid words in the both dictionaries¬
         */
        CompositeDictionary compositeDictionary = new CompositeDictionary(normalDictionary1, normalDictionary2);
        List<String> result3 = sentenceSplitter.split(sentence, compositeDictionary);

        System.out.println("result3: " + result3);
    }
}
