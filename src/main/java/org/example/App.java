package org.example;

import org.example.analyser.FileAnalyser;
import org.example.analyser.FileAnalyserImpl;


public class App {
    public static void main(String[] args) {
        FileAnalyser fileAnalyser = new FileAnalyserImpl("input.txt");
        fileAnalyser.saveSummary("summary.txt");
    }
}
