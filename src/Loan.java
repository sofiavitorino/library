import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Loan {
    Date startDate;
    Date returnDate;
    User user;
    private List<LoanItem> loanItems;

    public Loan(Date startDate, Date returnDate, User user, Book book){
        this.startDate = startDate;
        this.returnDate = returnDate;
        this.user = user;
        this.loanItems = ArrayList<>();
    }
}
