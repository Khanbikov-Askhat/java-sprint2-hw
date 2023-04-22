public class MonthReportRecord {
    public String itemName;
    public Boolean isExpense;
    public int quantity;
    public int sumOfOne;


    public MonthReportRecord(String itemName, Boolean isExpense, int quantity, int sumOfOne) {
        this.itemName = itemName;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.sumOfOne = sumOfOne;
    }

}
