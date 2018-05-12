package redblacktrees;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author ahmed
 */
public class Test {

    public static void main(String[] args) {
        RedBlackTree<Integer> tree = new RedBlackTree<>();

//        for (int i = 1; i < 2000; i++) {
//            int item = (int) (Math.random() * 1000);
//            tree.insert(item);
//        }
//        tree.printSorted();
        FileReader fileReader = null;
        try {
            File file = new File("Numbers.txt");
            fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);
            String newLine = null;
            while ((newLine = reader.readLine()) != null) {
                tree.insert(Integer.parseInt(newLine.trim()));
            }
            tree.printSorted();
            reader.close();
        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {
        } finally {
            try {
                fileReader.close();
            } catch (IOException ex) {
            }
        }

    }

}
