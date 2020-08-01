package com.example.realworld.bank.summarizer;

import com.example.realworld.bank.transaction.BankTransaction;

@FunctionalInterface
public interface BankTransactionSummarizer {
	double summarize(double accumulator, BankTransaction bankTransaction);
}
