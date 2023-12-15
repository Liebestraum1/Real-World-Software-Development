import analyzer.BankStatementAnalyzer;
import exporter.Exporter;
import exporter.HtmlExporter;
import parser.BankStatementCSVParser;
import parser.BankStatementParser;
import java.io.IOException;

public class MainApplication{
    public static void main(String[] args) throws IOException {
        final BankStatementAnalyzer bankStatementAnalyzer = new BankStatementAnalyzer();
        final BankStatementParser bankStatementParser = new BankStatementCSVParser();
        final Exporter exporter = new HtmlExporter();

        bankStatementAnalyzer.analyze("bank-data-simple.csv", bankStatementParser, exporter);
    }
}
