import java.io.*;
import java.util.*;

public class MP03_SearchKeyword {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        String path = null;
        String keyword = null;

        if (args.length > 0) {
            path = args[0].trim();
        }
        if (args.length > 1) {
            keyword = args[1].trim();
        }

        if (path == null || path.isEmpty()) {
            System.out.print("Enter dataset file path: ");
            path = input.nextLine().trim();
        }

        if (keyword == null || keyword.isEmpty()) {
            System.out.print("Enter keyword to search: ");
            keyword = input.nextLine().trim();
        }

        // Remove surrounding quotes (common when pasting file paths).
        if (path.startsWith("\"") && path.endsWith("\"")) {
            path = path.substring(1, path.length() - 1);
        }

        if (keyword.isEmpty()) {
            System.out.println("No keyword provided. Exiting.");
            input.close();
            return;
        }

        int matches = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            System.out.println("Working dir: " + System.getProperty("user.dir"));
            System.out.println("\nMatching Rows:\n");

            String line;
            String lowerKeyword = keyword.toLowerCase(Locale.ROOT);

            while ((line = br.readLine()) != null) {
                // Search within CSV cells rather than across comma boundaries
                String[] cells = line.split(",", -1);
                boolean found = false;
                for (String cell : cells) {
                    if (cell.toLowerCase(Locale.ROOT).contains(lowerKeyword)) {
                        found = true;
                        break;
                    }
                }

                if (found) {
                    System.out.println(line);
                    matches++;
                }
            }

            System.out.println("\nTotal Matches: " + matches);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + path);
        } catch (IOException e) {
            System.out.println("Error reading dataset: " + e.getMessage());
            e.printStackTrace();
        } finally {
            input.close();
        }
    }
}
