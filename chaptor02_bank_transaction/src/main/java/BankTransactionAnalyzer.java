import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class BankTransactionAnalyzer{
    public static void main(String[] args) throws IOException {
        // 파일에 접근할 경로 생성하기
        Path path = Path.of("src/main/resources/bank-data-simple.csv");

        // 파일에서 줄 읽어오기
        List<String> file = Files.readAllLines(path);

        double total = 0;


        for(String line : file){
            String[] parsedLine = line.split(",");
            total += Double.parseDouble(parsedLine[1]);

            System.out.println("거래 날짜: " + parsedLine[0] + ", 거래 금액: " + parsedLine[1] + ", 거래 내용: " + parsedLine[2]);
        }

        System.out.println("총 거래 금액: " + total);
    }
}
