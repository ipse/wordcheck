package com.example;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FlaggedWordChecker {
    private Set<String> flaggedWords;

    public FlaggedWordChecker(List<String> providedFlaggedWords) {
        if (providedFlaggedWords == null) {
            throw new IllegalArgumentException("Flagged words list cannot be null");
        }

        this.flaggedWords = providedFlaggedWords.stream()
                .map(String::toLowerCase)
                .collect(Collectors.toSet());
    }

    public Long countFlaggedWords(List<String> wordsToCheck) {
        if (wordsToCheck == null) {
            throw new IllegalArgumentException("Words to check cannot be null");
        }
        return wordsToCheck.stream()
                .map(String::toLowerCase)
                .filter(word -> flaggedWords.contains(word))
                .count();

    }
}
