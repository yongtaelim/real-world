package com.example.realworld.bank.dao;

import com.example.realworld.bank.transaction.BankTransaction;

import java.time.LocalDate;

public class BankTransactionDAO {

    public BankTransaction create(final LocalDate date, final double amount, final String description) {
        /* 로직 구현 */
        return null;
    }
    
    public BankTransaction read(final long id) {
        /* 로직 구현 */
        return null;
    }

    public BankTransaction update(final long id) {
        /* 로직 구현 */
        return null;
    }

    public void delete(final BankTransaction bankTransaction) {
        /* 로직 구현 */
        return;
    }
}
