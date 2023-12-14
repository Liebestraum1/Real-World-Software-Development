
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class BankStatementCSVParserTest {
    private final BankStatementParser statementParser = new BankStatementCSVParser();

    @Test
    public void shouldParseOneCorrectLine() {
        final String line = "30-12-2023,-10,배달";
        final BankTransaction result = statementParser.parseFrom(line);
        final BankTransaction expected = new BankTransaction(LocalDate.of(2023, Month.DECEMBER, 30), -10, "배달");
        final double tolerance = 0.0d;

        Assertions.assertEquals(expected.getDate(), result.getDate());
        Assertions.assertEquals(expected.getAmount(), result.getAmount(), tolerance);
        Assertions.assertEquals(expected.getDescription(), result.getDescription());
    }

    @Test
    public void shouldParseMultipleLines() {
        final List<String> lines = new ArrayList<>();
        final double tolerance = 0.0d;

        final String line1 = "30-12-2023,-10,배달";
        final String line2 = "31-12-2023,-20,책";
        lines.add(line1);
        lines.add(line2);

        final List<BankTransaction> result = statementParser.parseLinesFrom(lines);

        final List<BankTransaction> expected = new ArrayList<>();
        expected.add(new BankTransaction(LocalDate.of(2023, Month.DECEMBER, 30), -10, "배달"));
        expected.add(new BankTransaction(LocalDate.of(2023, Month.DECEMBER, 31), -20, "책"));

        for(int i = 0; i < result.size(); i++){
            Assertions.assertEquals(expected.get(i).getDate(), result.get(i).getDate());
            Assertions.assertEquals(expected.get(i).getAmount(), result.get(i).getAmount(), tolerance);
            Assertions.assertEquals(expected.get(i).getDescription(), result.get(i).getDescription());
        }
    }
}