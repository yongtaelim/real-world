package com.example.realworld;

import com.example.realworld.bank.analyzer.BankStatementAnalyzer;
import com.example.realworld.bank.parser.BankStatementParser;
import com.example.realworld.bank.parser.BankStatementSCVParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class RealWorldApplication {

	public static void main(String[] args) {
		SpringApplication.run(RealWorldApplication.class, args);
	}

	private static void bankProcess() throws IOException {
		final BankStatementAnalyzer bankStatementAnalyzer = new BankStatementAnalyzer();
		final String fileName = "bankFile.txt";
		BankStatementParser bankStatementParser = new BankStatementSCVParser();

		bankStatementAnalyzer.analyze(fileName, bankStatementParser);
	}
}
