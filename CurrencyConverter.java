import java.util.Scanner;

public class CurrencyConverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double usdToInr = 83.10;
        double eurToInr = 89.65;
        double usdToEur = 0.94;

        System.out.println("=== Currency Converter ===");
        System.out.println("Available Currencies: USD, INR, EUR");
        System.out.print("Enter base currency (e.g., USD): ");
        String baseCurrency = scanner.next().toUpperCase();

        System.out.print("Enter target currency (e.g., INR): ");
        String targetCurrency = scanner.next().toUpperCase();

        System.out.print("Enter amount to convert: ");
        double amount = scanner.nextDouble();

        double result = 0.0;
        boolean valid = true;

        if (baseCurrency.equals("USD") && targetCurrency.equals("INR")) {
            result = amount * usdToInr;
        } else if (baseCurrency.equals("INR") && targetCurrency.equals("USD")) {
            result = amount / usdToInr;
        } else if (baseCurrency.equals("EUR") && targetCurrency.equals("INR")) {
            result = amount * eurToInr;
        } else if (baseCurrency.equals("INR") && targetCurrency.equals("EUR")) {
            result = amount / eurToInr;
        } else if (baseCurrency.equals("USD") && targetCurrency.equals("EUR")) {
            result = amount * usdToEur;
        } else if (baseCurrency.equals("EUR") && targetCurrency.equals("USD")) {
            result = amount / usdToEur;
        } else if (baseCurrency.equals(targetCurrency)) {
            result = amount;
        } else {
            System.out.println("Conversion for selected currencies is not available.");
            valid = false;
        }

        if (valid) {
            System.out.printf("Converted Amount: %.2f %s\n", result, targetCurrency);
        }

        scanner.close();
    }
}
