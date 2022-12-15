package org.example.helper;

import java.io.*;

public class FileHelper {
    public String readFile(final String filePath) {
        StringBuilder result = new StringBuilder();

        try (FileInputStream fis = new FileInputStream(filePath);
             BufferedReader is = new BufferedReader(new InputStreamReader(fis))) {
            String line = is.readLine();

            while (line != null) {
                result.append(line);
                line = is.readLine();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return result.toString();
    }

    public void writeFile(String filePath, String content) {

        try (FileOutputStream fos = new FileOutputStream(filePath);
             OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fos)) {

            outputStreamWriter.write(content);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
