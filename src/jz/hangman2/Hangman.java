/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jz.hangman2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author CSLAB313-1740
 */
public class Hangman {
//vars

    int numGuesses = 5;
    String currentGuess;
    String clue, visible, secret;
    public String[] wordList;
    private String secretWord;
    
//constructer 

    public Hangman() {

    }

    public String getSecretWord() {
        return secretWord;
    }

    public void setSecretWord(String s) {
        secretWord = s;
    }

    //gets a random number
    public int rand() {
        double rand;
        rand = Math.random() * 1000;
        int r = (int) rand;
        return r;
    }

    //gets a random word
    public void getRandomWord() {
        String w = wordList[rand()];
        setSecretWord(w);
    }

    //gets the number of words so that we can get a random word
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

    //gets the word list and chatches stuff
    public void setWordList(int numwords) {
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

        wordList = words;
    }

    public void playGame() {
        //Intro
        System.out.println("Welcome to hangman!");
        getRandomWord();
        //Get word
        String word = getSecretWord();
        //System.out.println(word);
        String show = "";
        String charsGuessed = "";
        //set intl display
        for (int c = 0; c < word.length(); c++) {
            show = show + "□";
        }
        Scanner scan = new Scanner(System.in);
        char cg = '~';
        //Ensures we haven't won and we have guesses left
        while (numGuesses > 0 && !show.equals(word)) {
            
            while (1 == 1) {
                //stuff to make it pretty
                System.out.println("You have " + numGuesses + " guesses left");
                System.out.println("Please type your guess (1 letter)");
                if(charsGuessed != "")
                System.out.println("Characters guessed: "+charsGuessed);
                //get input & Verify the input
                currentGuess = null;
                currentGuess = scan.nextLine();
                if(currentGuess.length()!=1){
                    System.out.println("That's not a valid guess. Guess exactly one letter.");
                    continue;
                }
                currentGuess = currentGuess.toLowerCase();
                cg = currentGuess.charAt(0);
                String blank = "";
                blank += cg;
                
                if (charsGuessed.contains(blank)||Character.isLetter(cg)==false) {
                    System.out.println("That's not a valid guess");
                    continue;
                }
                break;
            }
            //add char guessed to charsGuessed
            charsGuessed = charsGuessed + cg;
            boolean there = false;
            
            //see if you guessed right
            for (int i = 0; i < word.length(); i++) {
                if (cg == word.charAt(i)) {
                    //if you guessed right, add it to the display in the right place
                    show = show.substring(0, i) + cg + show.substring(i + 1);
                    there = true;
                }
            }
            //stuff to make it pretty
            if (there == true) {
                System.out.println("Correct Guess!");
//                 System.out.println(show);
//                 System.out.println(word);
                
                if (show.equals(word)) {
                    System.out.println("Congrats! You got it!");
                    break;
                }
            } else {
                System.out.println("Incorrect Guess!");
                numGuesses--;
                if(numGuesses==0){
                    System.out.println("Game over! The word was "+word+".");
                }
            }
            if(!show.equals(word))
                System.out.println(show);
        }
    }
}
