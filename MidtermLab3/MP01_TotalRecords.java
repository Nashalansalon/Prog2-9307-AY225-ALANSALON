import java.io.*;
import java.util.*;

public class MP01_TotalRecords {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("Enter dataset file path: ");
        String path = input.nextLine();

        int count = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));

            String line;

            br.readLine(); // skip header

            while ((line = br.readLine()) != null) {
                count++;
            }

            br.close();

            System.out.println("Total Records: " + count);

        } catch (IOException e) {
            System.out.println("Error reading file.");
        }

        input.close();
    }
}