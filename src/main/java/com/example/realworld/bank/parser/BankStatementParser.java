package com.example.realworld.bank.parser;

import com.example.realworld.bank.transaction.BankTransaction;

import java.util.List;

public interface BankStatementParser {
    BankTransaction parseFrom(String line);
    List<BankTransaction> parseLinesFrom(List<String> lines);
}
