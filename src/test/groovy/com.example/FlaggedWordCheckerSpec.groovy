package com.example

import spock.lang.Specification

class FlaggedWordCheckerSpec extends Specification {

    def "It fails with null flagged word list"() {
        when:
        new FlaggedWordChecker(null)

        then:
        def e = thrown(IllegalArgumentException)
        assert e.message == "Flagged words list cannot be null"
    }

    def "It fails with null words to check"() {
        given:
        def checker = new FlaggedWordChecker(["escort"])

        when:
        checker.countFlaggedWords(null)

        then:
        def e = thrown(IllegalArgumentException)
        assert e.message == "Words to check cannot be null"
    }

    def "It counts flagged word once"() {
        given:
        def checker = new FlaggedWordChecker(["escort"])

        when:
        def flaggedWordsCount = checker.countFlaggedWords(["A", "quick", "escort", "fox", "jumps", "over", "bodyguard"])

        then:
        assert flaggedWordsCount == 1
    }

    def "It counts flagged word twice"() {
        given:
        def checker = new FlaggedWordChecker(["escort"])

        when:
        def flaggedWordsCount = checker.countFlaggedWords(["A", "quick", "escort", "fox", "jumps", "over", "escort", "bodyguard"])

        then:
        assert flaggedWordsCount == 2
    }

    def "It counts multiple flagged words"() {
        given:
        def checker = new FlaggedWordChecker(["escort", "bodyguard", "gambling"])

        when:
        def flaggedWordsCount = checker.countFlaggedWords(["A", "quick", "gambling", "fox", "jumps", "over", "escort", "bodyguard"])

        then:
        assert flaggedWordsCount == 3
    }

    def "It counts case insensitive words"() {
        given:
        def checker = new FlaggedWordChecker(["escort", "bodyguard", "gambling"])

        when:
        def flaggedWordsCount = checker.countFlaggedWords(["A", "quick", "GambLing", "fox", "jumps", "over", "escort", "Bodyguard"])

        then:
        assert flaggedWordsCount == 3
    }

    def "It uses a case-insensitive list of provided words"() {
        given:
        def checker = new FlaggedWordChecker(["ESCORT", "BodyGuard", "GAMbling"])

        when:
        def flaggedWordsCount = checker.countFlaggedWords(["A", "quick", "gambling", "fox", "jumps", "over", "escort", "bodyguard"])

        then:
        assert flaggedWordsCount == 3
    }
}
