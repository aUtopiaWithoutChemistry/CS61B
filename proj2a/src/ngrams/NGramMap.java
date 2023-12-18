package ngrams;

import edu.princeton.cs.algs4.In;
import org.apache.hc.core5.annotation.Internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import static ngrams.TimeSeries.MAX_YEAR;
import static ngrams.TimeSeries.MIN_YEAR;

/**
 * An object that provides utility methods for making queries on the
 * Google NGrams dataset (or a subset thereof).
 *
 * An NGramMap stores pertinent data from a "words file" and a "counts
 * file". It is not a map in the strict sense, but it does provide additional
 * functionality.
 *
 * @author Josh Hug
 */
public class NGramMap {

    // TODO: Add any necessary static/instance variables.
    // use hash map to store the TimeSeries tree of each word
    HashMap<String, TimeSeries> allWords = new HashMap<>();

    // use another hashmap to store the counts of year
    HashMap<Integer, Double> yearCounts = new HashMap<>();

    /**
     * Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME.
     */
    public NGramMap(String wordsFilename, String countsFilename) {
        // TODO: Fill in this constructor. See the "NGramMap Tips" section of the spec for help.

        // using in to access the csv file
        In in1 = new In(wordsFilename);
        int i = 0;

        // check if current cursor to the end of the file
        while(!in1.isEmpty()) {

            // go through very line of file
            i += 1;
            String nextLine = in1.readLine();
            String[] splitLine = nextLine.split("\t");
            String word = splitLine[0];
            int year = Integer.parseInt(splitLine[1]);
            double times = Double.parseDouble(splitLine[2]);

            // if already created the tree in the hashmap, then directly add new data
            if (allWords.containsKey(word)) {
                allWords.get(word).put(year, times);
            }

            // if there are no tree, then create a new tree and add into hashmap.
            else {
                TimeSeries tree = new TimeSeries();
                tree.put(year, times);
                allWords.put(word, tree);
            }
        }

        In in2 = new In(countsFilename);
        int j = 0;
        while(!in2.isEmpty()) {
            j += 1;
            String nextLine = in2.readLine();
            String[] splitLine = nextLine.split(",");
            int year = Integer.parseInt(splitLine[0]);
            double counts = Double.parseDouble(splitLine[1]);

            if (!yearCounts.containsKey(year)) {
                yearCounts.put(year, counts);
            }
        }
    }

    /**
     * Provides the history of WORD between STARTYEAR and ENDYEAR, inclusive of both ends. The
     * returned TimeSeries should be a copy, not a link to this NGramMap's TimeSeries. In other
     * words, changes made to the object returned by this function should not also affect the
     * NGramMap. This is also known as a "defensive copy". If the word is not in the data files,
     * returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word, int startYear, int endYear) {
        // TODO: Fill in this method.
        TimeSeries thisWord = allWords.get(word);
        TimeSeries ts = new TimeSeries(thisWord, startYear, endYear);

        return ts;
    }

    /**
     * Provides the history of WORD. The returned TimeSeries should be a copy, not a link to this
     * NGramMap's TimeSeries. In other words, changes made to the object returned by this function
     * should not also affect the NGramMap. This is also known as a "defensive copy". If the word
     * is not in the data files, returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word) {
        // TODO: Fill in this method.
        TimeSeries thisWord = allWords.get(word);
        return thisWord;
    }

    /**
     * Returns a defensive copy of the total number of words recorded per year in all volumes.
     */
    public TimeSeries totalCountHistory() {
        // TODO: Fill in this method.
        TimeSeries totalCount = new TimeSeries();
        for (int i = MIN_YEAR; i <= MAX_YEAR; i++) {
            if (yearCounts.containsKey(i)) {
                double value = yearCounts.get(i);
                totalCount.put(i, value);
            }
        }
        return totalCount;
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD between STARTYEAR
     * and ENDYEAR, inclusive of both ends. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word, int startYear, int endYear) {
        // TODO: Fill in this method.
        TimeSeries returnSeries = new TimeSeries();
        TimeSeries thisWord = allWords.get(word);

        // calculate every year's frequency
        for (int i = startYear; i <= endYear; i++) {
            if (thisWord.get(i) == null) {
                continue;
            } else {
                returnSeries.put(i, (thisWord.get(i) / yearCounts.get(i)));
            }
        }

        return returnSeries;
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD compared to all
     * words recorded in that year. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word) {
        // TODO: Fill in this method.
        if (allWords.containsKey(word)) {
            TimeSeries unWeight = allWords.get(word);
            TimeSeries weight = new TimeSeries();
            for (int i = MIN_YEAR; i <= MAX_YEAR; i++) {
                if (unWeight.containsKey(i)) {
                    double unWeValue = unWeight.get(i);
                    double weValue = unWeValue / yearCounts.get(i);
                    weight.put(i, weValue);
                }
            }
            return weight;
        }
        else {
            return new TimeSeries();
        }
    }

    /**
     * Provides the summed relative frequency per year of all words in WORDS between STARTYEAR and
     * ENDYEAR, inclusive of both ends. If a word does not exist in this time frame, ignore it
     * rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words,
                                          int startYear, int endYear) {
        // TODO: Fill in this method.
        TimeSeries returnSeries = new TimeSeries();
        for (int i = startYear; i <= endYear; i++) {
            returnSeries.put(i, 0.0);
        }

        for(String word: words) {
            returnSeries = returnSeries.plus(weightHistory(word));
        }

        return returnSeries;
    }

    /**
     * Returns the summed relative frequency per year of all words in WORDS. If a word does not
     * exist in this time frame, ignore it rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words) {
        // TODO: Fill in this method.
        return summedWeightHistory(words, MIN_YEAR, MAX_YEAR);
    }

    // TODO: Add any private helper methods.
    // TODO: Remove all TODO comments before submitting.
}
