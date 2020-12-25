package us.bubblenews.bubbleserver.service.impl;

import opennlp.tools.stemmer.PorterStemmer;
import org.springframework.stereotype.Service;
import us.bubblenews.bubbleserver.service.TextProcessingService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("Simple")
public class TextProcessingServiceImpl implements TextProcessingService {

    private static final String WORD_PATTERN_STRING = "\\[\\[[^\\[]+?\\]\\]|[^\\W_]+'[^\\W_]+|[^\\W_]+";

    private static final Set<String> STOP_WORDS = readInStopWords("src/main/resources/static/stopwords");

    public Map<String, Integer> getTermFrequencyMap(String text, boolean doRemoveStopWords, boolean doStem) {
        Map<String, Integer> termFrequency = new HashMap<>();
        PorterStemmer porterStemmer = new PorterStemmer();

        text = text.toLowerCase();

        Pattern wordPattern = Pattern.compile(WORD_PATTERN_STRING);
        Matcher wordMatcher = wordPattern.matcher(text);
        while (wordMatcher.find()) {
            String word = wordMatcher.group();
            if (doRemoveStopWords && isStopWord(word)) {
                continue;
            }
            if (doStem) {
                porterStemmer.stem(word);
                word = porterStemmer.toString();
                porterStemmer.reset();
            }
            termFrequency.put(word, termFrequency.getOrDefault(word, 0) + 1);
        }
        return termFrequency;
    }

    private boolean isStopWord(String word) {
        return STOP_WORDS.contains(word);
    }

    private static Set<String> readInStopWords(String fileName) {
        Set<String> words = new HashSet<>();
        try {
            FileReader file = new FileReader(fileName);
            try (BufferedReader reader = new BufferedReader(file)) {
                String row = reader.readLine();
                while (row != null) {
                    words.add(row);
                    row = reader.readLine();
                }
            }
        } catch (IOException e) {
            System.err.println(Arrays.toString(e.getStackTrace()));
        }
        return words;
    }
}
