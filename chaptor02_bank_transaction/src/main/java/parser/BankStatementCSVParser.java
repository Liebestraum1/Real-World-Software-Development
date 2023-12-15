package parser;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import transaction.BankTransaction;

public class BankStatementCSVParser implements BankStatementParser{
    private static final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public List<BankTransaction> parseLinesFrom(List<String> lines) {
        List<BankTransaction> bankTransactions = new ArrayList<>();

        lines.forEach(l -> bankTransactions.add(parseFrom(l)));
        return bankTransactions;
    }

    public BankTransaction parseFrom(String line){
        String[] parsedLine = line.split(",");

        LocalDate date = LocalDate.parse(parsedLine[0], DATE_PATTERN);
        double amount = Double.parseDouble(parsedLine[1]);
        String description = parsedLine[2];

        return new BankTransaction(date, amount, description);
    }
}
