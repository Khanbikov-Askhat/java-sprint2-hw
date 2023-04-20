public class Year {
    public int month;
    public int amount;
    public Boolean is_expense;
    public int yearNumber;

    public Year(int month, int amount, Boolean is_expense, int yearNumber) {
        this.month = month;
        this.amount = amount;
        this.is_expense = is_expense;
        this.yearNumber = yearNumber;
    }
}
