package ir;
import java.io.*;
import java.util.*;
public class IR {

       public static void main(String[] args) throws IOException {
        String[] filenames = {"1.txt","2.txt","3.txt","4.txt","5.txt","6.txt","7.txt","8.txt","9.txt","10.txt"};
       PostingList index = new PostingList();
        index.buildIndex(filenames);
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a query: ");
        String query = scanner.nextLine();
        DictEntry result = index.search(query);
        if (result != null) {
            System.out.println("Term frequency: " + result.term_freq);
            System.out.println("Document frequency: " + result.doc_freq);
            System.out.println("Document IDs: " + result.docIds);
        } else {
            System.out.println("Not found.");
        }

    }
}
