package com.example.realworld.bank.parser;

import com.example.realworld.bank.transaction.BankTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class BankStatementSCVParserTest {

    private final BankStatementParser statementParser = new BankStatementSCVParser();

    @Test
    public void shouldParseOneCorrectLine() {
        final String line = "30-01-2017,-50,Tesco";

        final BankTransaction result = statementParser.parseFrom(line);

        final BankTransaction expected = new BankTransaction(LocalDate.of(2017, Month.JANUARY, 30), -50, "Tesco");
        final double tolerance = 0.0d;

        Assertions.assertEquals(expected.getDate(), result.getDate());
        Assertions.assertEquals(expected.getAmount(), result.getAmount(), tolerance);
        Assertions.assertEquals(expected.getDescription(), result.getDescription());
    }
}
