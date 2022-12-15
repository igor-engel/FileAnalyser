package org.example.analyser;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

/**
 * User storage.
 */
public interface FileAnalyser {

    /**
     * Get file name.
     */
    String getFileName();

    /**
     * Get rows count
     */
    int getRowsCount() throws FileNotFoundException;

    /**
     * Get total letters count.
     */
    int getLettersCount();

    /**
     * Get symbols statistics by letters entry.
     * Analyzes only digits 0-9, letters a-z and A-Z
     *
     * @return {'a': 2, 'b': 10}
     */
    Map<Character, Integer> getSymbolsStatistics();

    /**
     * Get top N popular symbols
     * Analyzes only digits 0-9, letters a-z and A-Z
     *
     * @param n - n
     */
    List<Character> getTopNPopularSymbols(int n);

    /**
     * Store summary to file.
     *
     * @param filePath - file name
     */
    void saveSummary(String filePath);
}
