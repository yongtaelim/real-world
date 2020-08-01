package com.example.realworld.bank.filter;

import java.time.Month;

import com.example.realworld.bank.transaction.BankTransaction;

public class BankTransactionIsInFebruaryAndExpensive implements BankTransactionFilter{

	@Override
	public boolean test(final BankTransaction bankTransaction) {
		return bankTransaction.getDate().getMonth() == Month.FEBRUARY 
				&& bankTransaction.getAmount() >= 1.000;
	}

}
