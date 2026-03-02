import java.io.*;
import java.util.*;

public class SalesSummaryDashboard {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        File file;

        while (true) {
            System.out.print("Enter dataset file path: ");
            String path = input.nextLine();
            file = new File(path);
            if (file.exists() && file.isFile()) {
                break;
            } else {
                System.out.println("Invalid file path. Please try again.");
            }
        }

        try {
            SalesAnalyzer analyzer = new SalesAnalyzer(file);
            analyzer.processData();
            analyzer.displayReport();
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } finally {
            input.close();
        }
    }
}

class SalesAnalyzer {
    private File file;
    private List<SalesRecord> records;
    private int totalRecords;
    private double totalSales;
    private double averageSales;
    private double highestSale;
    private double lowestSale;

    public SalesAnalyzer(File file) {
        this.file = file;
        this.records = new ArrayList<>();
        this.totalSales = 0;
        this.highestSale = Double.NEGATIVE_INFINITY;
        this.lowestSale = Double.POSITIVE_INFINITY;
    }

    public void processData() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            reader.readLine(); // skip header

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split(",");
                if (parts.length > 2) {
                    try {
                        double sales = Double.parseDouble(parts[2].trim());

                        String title = parts[0].trim();
                        String console = parts[1].trim();

                        records.add(new SalesRecord(title, console, sales));
                        totalSales += sales;

                        if (sales > highestSale) highestSale = sales;
                        if (sales < lowestSale) lowestSale = sales;
                    } catch (NumberFormatException ignored) {
                        // skip invalid data safely
                    }
                }
            }
        }

        totalRecords = records.size();
        averageSales = totalRecords > 0 ? totalSales / totalRecords : 0;
    }

    public void displayReport() {
        System.out.println("\n============================================================");
        System.out.println("                     SALES SUMMARY DASHBOARD");
        System.out.println("============================================================");
        System.out.println("File: " + file.getName());
        System.out.println("------------------------------------------------------------");
        System.out.printf("Total Records:            %d%n", totalRecords);
        System.out.printf("Total Sales/Revenue:     $%.2f%n", totalSales);
        System.out.printf("Average Sales/Transaction:$%.2f%n", averageSales);

        if (totalRecords > 0) {
            System.out.printf("Highest Sale:            $%.2f%n", highestSale);
            System.out.printf("Lowest Sale:             $%.2f%n", lowestSale);
        } else {
            System.out.println("Highest Sale:            N/A");
            System.out.println("Lowest Sale:             N/A");
        }
        System.out.println("============================================================");
    }
}

class SalesRecord {
    private String title;
    private String console;
    public SalesRecord(String title, String console, double sales) {
        this.title = title;
        this.console = console;
    }

    public String getTitle() {
        return title;
    }

    public String getConsole() {
        return console;
    }
}