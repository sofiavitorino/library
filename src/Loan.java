import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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

    public User getUser(){
        return user;
    }

    public LocalDate getStartDate(){
        return startDate;
    }

    public LocalDate getReturnDate(){
        return returnDate;
    }

    public double calculateFine(){
        LocalDate today = LocalDate.now();
        if (today.isAfter(this.returnDate)) {
            long daysLate = ChronoUnit.DAYS.between(this.returnDate, today);
            double finePerDay = 2.50;
            return daysLate * finePerDay;
        }
        return 0.0;
    }

}
