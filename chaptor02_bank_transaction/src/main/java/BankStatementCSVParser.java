import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BankStatementCSVParser {
    private static final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    List<BankTransaction> parseLinesFromCSV(List<String> lines) {
        List<BankTransaction> bankTransactions = new ArrayList<>();

        lines.forEach(l -> bankTransactions.add(parseLine(l)));
        return bankTransactions;
    }

    BankTransaction parseLine(String line){
        String[] parsedLine = line.split(",");

        LocalDate date = LocalDate.parse(parsedLine[0], DATE_PATTERN);
        double amount = Double.parseDouble(parsedLine[1]);
        String description = parsedLine[2];

        return new BankTransaction(date, amount, description);
    }
}
