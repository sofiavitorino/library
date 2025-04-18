import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Loan {
    private LocalDate startDate;
    private LocalDate returnDate;
    private User user;
    private List<LoanItem> loanItems;

    public Loan(LocalDate startDate, LocalDate returnDate, User user, List<LoanItem> loanItems){
        this.startDate = startDate;
        this.returnDate = returnDate;
        this.user = user;
        this.loanItems = loanItems;
    }

    public List<LoanItem> getLoanItems(){
        return loanItems;
    }
}
