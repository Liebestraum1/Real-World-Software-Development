import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Month;
import java.util.List;

public class BankStatementAnalyzer {
    private static final String RESOURCES = "src/main/resources/";
    private static void collectSummary(BankStatementProcessor bankStatementProcessor){
        System.out.println("총 거래금액 : " + bankStatementProcessor.calculateTotalAmount());
        System.out.println("1월 거래금액 : " + bankStatementProcessor.calculateTotalInMonth(Month.JANUARY));
        System.out.println("2월 거래금액 : " + bankStatementProcessor.calculateTotalInMonth(Month.FEBRUARY));
        System.out.println("월급 총액 : " + bankStatementProcessor.calculateTotalForCategory("Salary"));
    }
    public void analyze(String fileName, BankStatementParser bankStatementParser) throws IOException {
        final Path path = Path.of(RESOURCES + fileName);
        final List<String> lines = Files.readAllLines(path);

        final List<BankTransaction> bankTransactions = bankStatementParser.parseLinesFrom(lines);
        final BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankTransactions);

        collectSummary(bankStatementProcessor);
    }
}