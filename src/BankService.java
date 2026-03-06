
import java.util.*;
// Se importan todas las clases necesarias para el manejo de listas y mapas

public class BankService {

    public Summary buildSummary(String userId, List<Movement> movements) {
        Summary summary = new Summary();

        // Movimientos relevantes
        List<Movement> userMovements = filterUserMovements(userId, movements);

        if (userMovements.isEmpty()) {
            return summary; // Retorna un resumen vacío si no hay movimientos
        }

        Map<String, Integer> categoryCount = new HashMap<>(); // Para contar la frecuencia de cada categoría

        double maxExpenseAmount = -1; // Para rastrear el gasto más grande

        for (Movement movement : userMovements) {
            processMovement(userId, movement, summary, categoryCount);

            // Actualizar el gasto más grande
            if (isExpenseForUser(userId, movement)) {
                double amount = movement.getAmount();
                if (amount > maxExpenseAmount) {
                    maxExpenseAmount = amount;
                    summary.setLargestExpense(movement);
                }
            }
        }

        // Calcular balance
        summary.setBalance(summary.getTotalIncome() - summary.getTotalExpenses());

        // Determinar la categoría más utilizada
        String mostUsedCategory = categoryCount.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
        summary.setMostUsedCategory(mostUsedCategory);

        return summary;
    }

    // Movimientos relevantes para el usuario
    private List<Movement> filterUserMovements(String userId, List<Movement> movements) {
        List<Movement> userMovements = new ArrayList<>();
        for (Movement movement : movements) {
            if (movement.getFromUser() != null && movement.getFromUser().equals(userId)
                    || movement.getToUser() != null && movement.getToUser().equals(userId)) {
                userMovements.add(movement);
            }
        }
        return userMovements;
    }

    // Procesar cada movimiento para actualizar el resumen
    private void processMovement(String userId, Movement movement, Summary summary, Map<String, Integer> categoryCount) {
        String type = movement.getType();
        String category = movement.getCategory();
        double amount = movement.getAmount();

        if ("deposit".equals(type) && userId.equals(movement.getToUser())) {
            summary.setTotalIncome(summary.getTotalIncome() + amount);
            incrementCategoryCount(categoryCount, category);
        } else if ("withdrawal".equals(type) && userId.equals(movement.getFromUser())) {
            summary.setTotalExpenses(summary.getTotalExpenses() + amount);
            updateCategoryExpense(summary, category, amount);
            incrementCategoryCount(categoryCount, category);
        } else if ("transfer".equals(type)) {
            if (userId.equals(movement.getToUser())) {
                // Transferencia recibida: ingreso
                summary.setTotalIncome(summary.getTotalIncome() + amount);
                incrementCategoryCount(categoryCount, category);
            } else if (userId.equals(movement.getFromUser())) {
                // Transferencia enviada: egreso
                summary.setTotalExpenses(summary.getTotalExpenses() + amount);
                updateCategoryExpense(summary, category, amount);
                incrementCategoryCount(categoryCount, category);
            }
        }
    }

    /**
     * Actualiza el gasto por categoría
     */
    private void updateCategoryExpense(Summary summary, String category, double amount) {
        Map<String, Double> byCategory = summary.getByCategory();
        double currentAmount = byCategory.getOrDefault(category, 0.0);
        byCategory.put(category, currentAmount + amount);
    }

    /**
     * Incrementa el contador de transacciones por categoría
     */
    private void incrementCategoryCount(Map<String, Integer> categoryCount, String category) {
        int currentCount = categoryCount.getOrDefault(category, 0);
        categoryCount.put(category, currentCount + 1);
    }

    /**
     * Determina si un movimiento es un egreso para el usuario
     */
    private boolean isExpenseForUser(String userId, Movement movement) {
        String type = movement.getType();

        if ("withdrawal".equals(type) && userId.equals(movement.getFromUser())) {
            return true;
        }

        if ("transfer".equals(type) && userId.equals(movement.getFromUser())) {
            return true;
        }

        return false;
    }
}
