package analyzer;

import exporter.Exporter;
import exporter.SummaryStatistics;
import parser.BankStatementParser;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import processor.BankStatementProcessor;
import transaction.BankTransaction;


public class BankStatementAnalyzer {
    private static final String RESOURCES = "src/main/resources/";

    public void analyze(String fileName,
                BankStatementParser bankStatementParser,
                Exporter exporter) throws IOException {

        final Path path = Path.of(RESOURCES + fileName);
        final List<String> lines = Files.readAllLines(path);

        final List<BankTransaction> bankTransactions = bankStatementParser.parseLinesFrom(lines);
        final BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankTransactions);
        final SummaryStatistics summaryStatistics = bankStatementProcessor.summarizeTransactions();
        System.out.println(exporter.export(summaryStatistics));
    }
}