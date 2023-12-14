import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class BankStatementProcessor {
    private final List<BankTransaction> bankTransactions;
    public BankStatementProcessor(List<BankTransaction> bankTransactions){
        this.bankTransactions = bankTransactions;
    }

    public double summarizeTransactions(BankTransactionSummarizer bankTransactionSummarizer){
        double result = 0;
        for(BankTransaction bankTransaction : bankTransactions){
            result = bankTransactionSummarizer.summarize(result, bankTransaction);
        }
        return result;
    }
    public double calculateTotalInMonth(Month month){
        return summarizeTransactions((acc, bankTransaction)
                -> bankTransaction.getDate().getMonth() == month ? acc + bankTransaction.getAmount() : acc);
    }

    public double calculateTotalAmount(){
        return summarizeTransactions((acc, bankTransaction)
                -> bankTransaction.getAmount() + acc);
    }

    public double calculateTotalForCategory (String category){
        return summarizeTransactions((acc, bankTransaction)
                -> bankTransaction.getDescription().equals(category) ? acc + bankTransaction.getAmount() : acc);
    }

    public List<BankTransaction> findTransactions(BankTransactionFilter bankTransactionFilter){
        List<BankTransaction> result = new ArrayList<>();
        for(BankTransaction bankTransaction : bankTransactions){
            if(bankTransactionFilter.test(bankTransaction)){
                result.add(bankTransaction);
            }
        }
        return result;
    }

    public List<BankTransaction> findTransactionGreaterThanEqual(int amount) {
        return findTransactions(bankTransaction -> bankTransaction.getAmount() >= amount);
    }
}
