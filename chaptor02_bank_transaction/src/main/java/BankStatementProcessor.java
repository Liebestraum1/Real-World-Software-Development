import java.time.Month;
import java.util.List;

public class BankStatementProcessor {
    private final List<BankTransaction> bankTransactions;
    public BankStatementProcessor(List<BankTransaction> bankTransactions){
        this.bankTransactions = bankTransactions;
    }
    public double calculateTotalAmount(){
        return bankTransactions
                .stream()
                .mapToDouble(BankTransaction :: getAmount)
                .sum();
    }

    public double calculateTotalInMonth(Month month){
        return bankTransactions
                .stream()
                .filter(b -> b.getDate().getMonth() == month)
                .mapToDouble(BankTransaction :: getAmount)
                .sum();
    }

    public double calculateTotalForCategory (String category){
        return bankTransactions
                .stream()
                .filter(b -> b.getDescription().equals(category))
                .mapToDouble(BankTransaction :: getAmount)
                .sum();
    }
}
