package com.example.realworld.bank.filter;

import com.example.realworld.bank.transaction.BankTransaction;

@FunctionalInterface
public interface BankTransactionFilter {
	boolean test(BankTransaction bankTransaction);
}
