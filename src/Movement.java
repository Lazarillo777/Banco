
import java.time.LocalDate;

public class Movement {
    // ATRIBUTOS:
    // id, type, amount, fromUser, toUser, category, timestamp
    private String id;
    private String type;
    private double amount;
    private String fromUser;
    private String toUser;
    private String category;

    private LocalDate timestamp;

    // Método Constructor
    public Movement(String id, String type, double amount, String fromUser, String toUser, String category) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.category = category;
        this.timestamp = LocalDate.now();
    }

    // Métodos Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }
}
