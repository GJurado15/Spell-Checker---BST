import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: java Main <dictionary file>");
            return;
        }

        BinarySearchTree<String> bst = new BinarySearchTree<>();

        // The dictionary file name is passed as a parameter to main
        String fileName = args[0];
        try (Scanner fileScanner = new Scanner(new File(fileName))) {
            // Read every word from the file and insert it into the tree
            while (fileScanner.hasNext()) {
                bst.insert(fileScanner.next());
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: The file '" + fileName + "' was not found.");
            return;
        }

        System.out.println("Height of the tree: " + bst.height());

        Scanner input = new Scanner(System.in);
        System.out.println("Enter words to spell check (type END to quit):");

        // Check each word the user types until they enter END
        while (input.hasNext()) {
            String word = input.next();
            if (word.equals("END")) {
                break;
            }

            // Only report words that aren't in the dictionary
            if (bst.search(word) == null) {
                System.out.println(word + " is misspelled");
            }
        }

        input.close();
    }
}
