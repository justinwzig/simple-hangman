
package jz.hangman2;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class SecretWord {

    String clue, visible, secret;
    public String[] wordList;
    
    public int rand(){
        double rand;
        rand = Math.random() * 1000;
        int r = (int)rand;
        return r;
    }
    
    public int numberOfWords() {
        int numwords = 0;
        
        try {
            try ( //System.out.println("Filefound");
                    Scanner s = new Scanner(JZHangman2.dictFile)) {
                while (s.hasNextLine()) {
                    numwords++;
                    s.nextLine();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File not found!");
        }
        //System.out.println(numwords);
        return numwords;
    }

    public String[] setWordList(int numwords) {
        String[] words = new String[numwords];
       
        int counter = 0;
        try {
            //System.out.println("File found");
            Scanner s = new Scanner(JZHangman2.dictFile);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                words[counter] = line;
                counter++;
            }

            s.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            e.printStackTrace();

        }

        return words;
    }

    public SecretWord() {
        wordList = setWordList(numberOfWords());
        int rand;
        rand = rand();
        System.out.println("Picking a word...");
        secret = wordList[rand];
        int wordLength = secret.length();

        visible = "";

        for (int i = 0; i < secret.length(); i++) {
            char c = secret.charAt(i);

            if (c == ' ') {
                visible += c;
            } else {
                visible += '_';
            }
        }
    }

    public String toString() {
        return visible;
    }

    // provided by Group 4 and 8
    public boolean update(char c) {
        char[] secretArray = new char[secret.length()];
        char[] visibleArray = new char[secret.length()];
        String s = "";
        boolean contains = false;

        //We make the String secret an array
        for (int i = 0; i < secret.length(); i++) {
            secretArray[i] = secret.charAt(i);
            visibleArray[i] = visible.charAt(i);
        }

        //We are uptading our empty visible array
        for (int k = 0; k < secret.length(); k++) {

            if (secretArray[k] == c) {
                visibleArray[k] = c;
                contains = true;
            }
        }
        //We are changing our visible array into the visible string
        for (int m = 0; m < secret.length(); m++) {
            s = s + visibleArray[m];
        }
        visible = s;

        return contains;
    }

    //This is a method to check if the secret word is solved

    public boolean isSolved() {
        return secret.equalsIgnoreCase(visible);
    }
}
