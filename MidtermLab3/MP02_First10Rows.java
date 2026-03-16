import java.io.*;
import java.util.*;

public class MP02_First10Rows {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("Enter dataset file path: ");
        String path = input.nextLine();

        try {

            BufferedReader br = new BufferedReader(new FileReader(path));

            String line;
            int count = 0;

            System.out.println("\nFirst 10 Rows:\n");

            while ((line = br.readLine()) != null && count < 10) {
                System.out.println(line);
                count++;
            }

            br.close();

        } catch (IOException e) {
            System.out.println("Error reading dataset.");
        }

        input.close();
    }
}