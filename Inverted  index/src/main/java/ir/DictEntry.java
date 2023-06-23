/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ir;
import java.io.*;
import java.util.*;



/**
 *
 * @author Hannibal lecter
 */
public class DictEntry {

    int doc_freq = 0;
    int term_freq = 0;
    Posting pList = null;
    LinkedList<Integer> docIds = new LinkedList<>();

}
