import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Month;
import java.util.List;

public class BankTransactionAnalyzer{
    private static final String FILEPATH = "src/main/resources/bank-data-simple.csv";
    private static final BankStatementCSVParser bankStatementParser = new BankStatementCSVParser();

    public static void main(String[] args) throws IOException {
        final Path path = Path.of(FILEPATH);
        final List<String> lines = Files.readAllLines(path);


        final List<BankTransaction> bankTransactions = bankStatementParser.parseLinesFromCSV(lines);
        final BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankTransactions);

        collectSummary(bankStatementProcessor);
    }

    private static void collectSummary(BankStatementProcessor bankStatementProcessor){
        System.out.println("총 거래금액 : " + bankStatementProcessor.calculateTotalAmount());
        System.out.println("1월 거래금액 : " + bankStatementProcessor.calculateTotalInMonth(Month.JANUARY));
        System.out.println("2월 거래금액 : " + bankStatementProcessor.calculateTotalInMonth(Month.FEBRUARY));
        System.out.println("월급 총액 : " + bankStatementProcessor.calculateTotalForCategory("Salary"));
    }
}