public class Month {
    public String item_name;
    public Boolean is_expense;
    public int quantity;
    public int sum_of_one;
    public int monthNumber;

    public Month(String item_name, Boolean is_expense, int quantity, int sum_of_one, int monthNumber) {
        this.item_name = item_name;
        this.is_expense = is_expense;
        this.quantity = quantity;
        this.sum_of_one = sum_of_one;
        this.monthNumber = monthNumber;
    }
}
