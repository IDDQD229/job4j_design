package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;

public class ResultFile {
    public static void main(String[] args) {
        int size = 2;

        try (FileOutputStream out = new FileOutputStream("rsl.txt")) {
            for (int i = 0; i < size; i++) {
                out.write(System.lineSeparator().getBytes());
                for (int j = 0; j < size; j++) {
                    String rsl = (i + 1) * (j + 1) + "\t";
                    out.write(rsl.getBytes());
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}