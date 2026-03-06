import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) {
        // Crear servicio
        BankService bankService = new BankService();

        // Crear movimientos de ejemplo
        List<Movement> movements = createSampleMovements();

        // Generar resumen para un usuario específico
        String userId = "user123";
        Summary summary = bankService.buildSummary(userId, movements);

        // Mostrar resultado
        System.out.println("Resumen para usuario: " + userId);
        System.out.println("Ingresos totales: $" + summary.getTotalIncome());
        System.out.println("Gastos totales: $" + summary.getTotalExpenses());
        System.out.println("Balance: $" + summary.getBalance());
        System.out.println("Gastos por categoria: " + summary.getByCategory());

        if (summary.getLargestExpense() != null) {
            System.out.println("Mayor gasto: $" + summary.getLargestExpense().getAmount()
                    + " - " + summary.getLargestExpense().getCategory());
        }

        System.out.println("Categoria mas usada: " + summary.getMostUsedCategory());
    }

    private static List<Movement> createSampleMovements() {
        List<Movement> movements = new ArrayList<>();

        // Depósito
        movements.add(new Movement(
                "1", "deposit", 1000.0, null, "user123",
                "salary"
        ));

        // Retiro
        movements.add(new Movement(
                "2", "withdrawal", 50.0, "user123", null,
                "food"
        ));

        // Transferencia recibida
        movements.add(new Movement(
                "3", "transfer", 200.0, "user456", "user123",
                "other"
        ));

        // Transferencia enviada
        movements.add(new Movement(
                "4", "transfer", 30.0, "user123", "user789",
                "transport"
        ));

        return movements;
    }
}
