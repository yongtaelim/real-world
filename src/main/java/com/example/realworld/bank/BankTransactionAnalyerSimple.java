package com.example.realworld.bank;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BankTransactionAnalyerSimple {
    private static final String RESOURCES = "src/main/resources";

    public static void main(String[] args) throws IOException {
//        final Path path = Paths.get(RESOURCES + args[0]);
//        final List<String> lines = Files.readAllLines(path);
        List<String> lines = new ArrayList<String>();
        double total = 0d;

        for (final String line: lines) {
            final String[] columns = line.split(",");
            final double amount = Double.parseDouble(columns[1]);
            total += amount;
        }

        System.out.println(total);
    }
}
