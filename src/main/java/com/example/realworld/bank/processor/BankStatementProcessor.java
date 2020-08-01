package com.example.realworld.bank.processor;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import com.example.realworld.bank.filter.BankTransactionFilter;
import com.example.realworld.bank.summarizer.BankTransactionSummarizer;
import com.example.realworld.bank.transaction.BankTransaction;

public class BankStatementProcessor {
    private final List<BankTransaction> bankTransactions;

    public BankStatementProcessor(final List<BankTransaction> bankTransactions) {
        this.bankTransactions = bankTransactions;
    }

    public double calculateTotalAmount() {
        double total= 0;
        for (final BankTransaction bankTransaction: bankTransactions) {
            total += bankTransaction.getAmount();
        }
        return total;
    }

    public double summarizeTransactions(final BankTransactionSummarizer bankTransactionSummarizer) {
    	double result = 0;
    	for (BankTransaction bankTransaction : bankTransactions) {
			result = bankTransactionSummarizer.summarize(result, bankTransaction);
		}
    	return result;
    }
    
    public double calculateTotalInMonth(final Month month) {
    	return summarizeTransactions((acc, bankTransaction) ->
    		bankTransaction.getDate().getMonth() == month ? acc + bankTransaction.getAmount() : acc 
		);
//        double total = 0;
//        for (final BankTransaction bankTransaction: bankTransactions) {
//            if(bankTransaction.getDate().getMonth() == month) {
//                total += bankTransaction.getAmount();
//            }
//        }
//        return total;
    }

    public double calculateTotalForCategory(final String category) {
    	return summarizeTransactions((acc, bankTransaction) ->
    		bankTransaction.getDescription().equals(category) ? acc + bankTransaction.getAmount() : acc
		);
//        double total = 0;
//        for (final BankTransaction bankTransaction: bankTransactions) {
//            if(bankTransaction.getDescription().equals(category)) {
//                total += bankTransaction.getAmount();
//            }
//        }
//        return total;
    }
    
    public List<BankTransaction> findTranctions(BankTransactionFilter bankTransactionFilter) {
    	final List<BankTransaction> result = new ArrayList<>();
    	for (final BankTransaction bankTransaction : bankTransactions) {
			if(bankTransactionFilter.test(bankTransaction)) {
				result.add(bankTransaction);
			}
		}
    	return result;
    }
    
    public List<BankTransaction> findTransactionsGreaterThanEqual(final int amount) {
    	return findTranctions(bankTransaction -> bankTransaction.getAmount() >= amount);
    }
}
