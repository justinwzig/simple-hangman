
package jz.hangman2;

import java.io.File;

public class JZHangman2 {
    //vars
    public static File dictFile = new File(System.getProperty("user.dir") + "/commonwords.txt");
    
    public static void main(String[] args) {
        Hangman h = new Hangman();
        
        h.setWordList(h.numberOfWords());
        
        h.playGame();
        
    }
    
}
