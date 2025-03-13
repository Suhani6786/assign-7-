import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class BubbleSort {
    
    
    public static int[] createRandomArray(int arrayLength) {
        Random random = new Random();
        int[] array = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            array[i] = random.nextInt(101); // Random numbers between 0-100
        }
        return array;
    }

    
    public static void writeArrayToFile(int[] array, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int num : array) {
                writer.write(num + "\n");
            }
            System.out.println("Array successfully written to file: " + filename);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    
    public static int[] readFileToArray(String filename) {
        try (Scanner scanner = new Scanner(new File(filename))) {
            int[] tempArray = new int[1000]; 
            int count = 0;

            while (scanner.hasNextInt()) {
                tempArray[count++] = scanner.nextInt();
            }

            int[] array = new int[count]; 
            System.arraycopy(tempArray, 0, array, 0, count);
            return array;
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return new int[0]; 
        }
    }

    // Bubble sort implementation
    public static void bubbleSort(int[] array) {
        int n = array.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    // Swap elements
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break; // Optimized: Stop if already sorted
        }
    }

    // Main function: Handles user input
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bubble Sort Program");
        System.out.println("1. Generate random array and save to file");
        System.out.println("2. Read numbers from file, sort, and save sorted output");
        System.out.print("Enter your choice: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); 
        switch (choice) {
            case 1:
                System.out.print("Enter array length: ");
                int length = scanner.nextInt();
                int[] randomArray = createRandomArray(length);
                System.out.print("Enter filename to save: ");
                String file1 = scanner.next();
                writeArrayToFile(randomArray, file1);
                break;

            case 2:
                System.out.print("Enter filename to read numbers from: ");
                String inputFile = scanner.next();
                int[] numbers = readFileToArray(inputFile);
                
                if (numbers.length == 0) {
                    System.out.println("No valid numbers found in the file.");
                    break;
                }

                bubbleSort(numbers);
                System.out.print("Enter filename to save sorted numbers: ");
                String outputFile = scanner.next();
                writeArrayToFile(numbers, outputFile);
                System.out.println("Sorted numbers saved to " + outputFile);
                break;

            default:
                System.out.println("Invalid choice. Exiting...");
        }

        scanner.close();
    }
}
