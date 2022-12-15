package org.example.analyser;

import org.example.helper.FileHelper;

import java.io.*;
import java.util.*;

public class FileAnalyserImpl implements FileAnalyser {
    String filePath;

    public FileAnalyserImpl(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    @Override
    public String getFileName() {
        int index = filePath.lastIndexOf("\\") + 1;

        return filePath.substring(index);
    }

    @Override
    public int getRowsCount() {
        int rowsCount = 0;

        try {
            Scanner scanner = new Scanner(new FileInputStream(filePath));

            while (scanner.hasNextLine()) {
                rowsCount++;
                scanner.nextLine();
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            System.out.println("Фаил не найден.");
        }

        return rowsCount;
    }

    @Override
    public int getLettersCount() {
        FileHelper helper = new FileHelper();
        String content = helper.readFile(filePath);

        int count = 0;

        for (int i = 0; i < content.length(); ++i) {
            char ch = content.charAt(i);

            if (Character.isLetter(ch)) {
                count++;
            }
        }

        return count;
    }

    @Override
    public Map<Character, Integer> getSymbolsStatistics() {
        FileHelper helper = new FileHelper();
        String content = helper.readFile(filePath);

        Map<Character, Integer> symbolsStatistics = new TreeMap<>();

        for (int i = 0; i < content.length(); ++i) {
            char ch = content.charAt(i);

            if (Character.isLetter(ch) || Character.isDigit(ch)) {
                if (!symbolsStatistics.containsKey(ch)) {
                    symbolsStatistics.put(ch, 0);
                }

                int previousCount = symbolsStatistics.get(ch);
                symbolsStatistics.put(ch, previousCount + 1);
            }
        }

        return symbolsStatistics;
    }

    @Override
    public List<Character> getTopNPopularSymbols(int n) {
        Map<Character, Integer> sourceMap = getSymbolsStatistics();

        TreeMap<Integer, List<Character>> modifiedMap = new TreeMap(Collections.reverseOrder()); // для сортировки по вхождениям
        for (Map.Entry<Character, Integer> entry : sourceMap.entrySet()) {
            Character ch = entry.getKey();
            Integer occurance = entry.getValue();

            if (!modifiedMap.containsKey(occurance)) {
                modifiedMap.put(occurance, new ArrayList<Character>());
            }

            List<Character> chars = modifiedMap.get(occurance);
            chars.add(ch);
        }

        for (int i = modifiedMap.size(); i > n; i--) {
            modifiedMap.remove(modifiedMap.lastKey());
        }

        return new ArrayList(modifiedMap.values());
    }

    @Override
    public void saveSummary(String filePath) {
        String fileName = "fileName: " + getFileName() + "\n";
        String rowsCount = "rowsCount: " + getRowsCount() + "\n";
        String lettersCount = "lettersCount: " + getLettersCount() + "\n";
        String symbolsStatistics = "symbolsStatistics: " + getSymbolsStatistics() + "\n";
        String top3PopularSymbols = "top3PopularSymbols: " + getTopNPopularSymbols(3) + "\n";

        FileHelper helper = new FileHelper();
        helper.writeFile(filePath, fileName +
                rowsCount +
                lettersCount +
                symbolsStatistics +
                top3PopularSymbols);
    }
}
