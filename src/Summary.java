import java.util.HashMap;
import java.util.Map;

public class Summary {

    /* ATRIBUTOS:
    totalIncome
    totalExpenses, 
    balance
    byCategory
    largestExpense
    mostUsedCategory */

    private double totalIncome;
    private double totalExpenses;
    private double balance;
    private Map<String, Double> byCategory;
    private Movement largestExpense;
    private String mostUsedCategory;

    // Método Constructor
    public Summary() {
        this.totalIncome = 0;
        this.totalExpenses = 0;
        this.balance = 0;
        this.byCategory = new HashMap<>();  //Categoría de la transacción 
        this.byCategory.put("food", 0.0);
        this.byCategory.put("transport", 0.0);
        this.byCategory.put("entertainment", 0.0);
        this.byCategory.put("payments", 0.0);
        this.byCategory.put("other", 0.0);
        this.largestExpense = null;
        this.mostUsedCategory = null;
    }

    // Métodos Getters y Setters
    public double getTotalIncome() {
        return totalIncome;
    }
    public void setTotalIncome(double totalIncome) {
        this.totalIncome = totalIncome;
    }
    public double getTotalExpenses() {
        return totalExpenses;
    }
    public void setTotalExpenses(double totalExpenses) {
        this.totalExpenses = totalExpenses;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public Map<String, Double> getByCategory() {
        return byCategory;
    }
    public void setByCategory(Map<String, Double> byCategory) {
        this.byCategory = byCategory;
    }
    public Movement getLargestExpense() {
        return largestExpense;
    }
    public void setLargestExpense(Movement largestExpense) {
        this.largestExpense = largestExpense;
    }
    public String getMostUsedCategory() {
        return mostUsedCategory;
    }
    public void setMostUsedCategory(String mostUsedCategory) {
        this.mostUsedCategory = mostUsedCategory;
    }

    //Este Override es para mostrar un resumen de las transacciones,
    //incluyendo el total de ingresos, gastos, balance,
    //gastos por categoría, la mayor transacción de gasto y la categoría más utilizada.
    @Override
    public String toString() {
        return "Summary{" +
                "totalIncome=" + totalIncome +
                ", totalExpenses=" + totalExpenses +
                ", balance=" + balance +
                ", byCategory=" + byCategory +
                ", largestExpense=" + (largestExpense != null ? largestExpense.getId() : "null") +
                ", mostUsedCategory='" + mostUsedCategory + '\'' +
                '}';
    }
}
