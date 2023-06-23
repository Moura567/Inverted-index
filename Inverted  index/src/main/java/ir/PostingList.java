package ir;
import java.io.*;
import java.util.*;
public class PostingList {
     HashMap<String, DictEntry> index;

    public PostingList() {
        index = new HashMap<>();
    }

    public void buildIndex(String[] filenames) throws IOException {
        for (String filename : filenames) {
            int docId = Integer.parseInt(filename.substring(0, filename.lastIndexOf(".")));
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] terms = line.split(" ");
                for (String term : terms) {
                    term = term.toLowerCase().replaceAll("\\s+", "");
                    if (term.length() == 0)
                        continue;
                    if (!index.containsKey(term))
                        index.put(term, new DictEntry());
                    DictEntry entry = index.get(term);
                    entry.term_freq++;
                    if (entry.pList == null || entry.pList.docId != docId) {
                        entry.doc_freq++;
                        Posting posting = new Posting();
                        posting.docId = docId;
                        entry.pList = addPostingToList(entry.pList, posting);
                    } else {
                        entry.pList.term_freq++;
                    }
                }
            }
            reader.close();
        }
    }

    private Posting addPostingToList(Posting head, Posting posting) {
        if (head == null)
            return posting;
        if (posting.docId < head.docId) {
            posting.next = head;
            return posting;
        }
        head.next = addPostingToList(head.next, posting);
        return head;
    }

    public DictEntry search(String query) {
        query = query.toLowerCase().replaceAll("\\s+", "");
        if (!index.containsKey(query))
            return null;
        Posting pList = index.get(query).pList;
        DictEntry result = new DictEntry();
        result.term_freq = index.get(query).term_freq;
        result.doc_freq = index.get(query).doc_freq;
        while (pList != null) {
            result.docIds.add(pList.docId);
            pList = pList.next;
        }
        return result;
    }

}
