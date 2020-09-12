/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bilgiguvenligi;

/**
 *
 * @author abdul
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class monoEncryption {
    public  void monoEnRun() throws IOException {
         File oku = new File("metin.txt");
        FileReader fileReader = new FileReader(oku);
        String line;

        BufferedReader br = new BufferedReader(fileReader);
        line = br.readLine();
      

        File oku1 = new File("monokey1.txt");
        FileReader filereader2 = new FileReader(oku1);
        String line1;

        BufferedReader br1 = new BufferedReader(filereader2);
        line1 = br1.readLine();
        
        File oku2 = new File("monokey2.txt");
        FileReader filereader3 = new FileReader(oku2);
        String line2;

        BufferedReader br2 = new BufferedReader(filereader3);
        line2 = br2.readLine();
        
        
        
        
        String plaintext = line;
         String key = line1, KEY = line2;
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        
        ArrayList<Integer> permutation = new ArrayList<>();
        for (int i = 0; i < alphabet.length(); i++)
            permutation.add(i);
        Collections.shuffle(permutation);
        
        
        for (int j = 0; j < ALPHABET.length(); j++) {
            key += alphabet.charAt(permutation.get(j));
            KEY += ALPHABET.charAt(permutation.get(j));
        }
        
        String ciphertext = "";
        
        int i, j;
        for (i = 0; i < plaintext.length(); i++) {
            for (j = 0; j < alphabet.length(); j++) {
                if (plaintext.charAt(i) == alphabet.charAt(j)) {
                    ciphertext += key.charAt(j);
                    break;
                }
                if (plaintext.charAt(i) == ALPHABET.charAt(j)) {
                    ciphertext += KEY.charAt(j);
                    break;
                }
            }
            if (j == ALPHABET.length())
                ciphertext += plaintext.charAt(i);
        }
        File file = new File("şifrelimetin.txt");
        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fileWriter = new FileWriter(file, false);
        BufferedWriter bWriter = new BufferedWriter(fileWriter);
        bWriter.write(ciphertext);
        bWriter.close();
    
        System.out.println("okunan metin  : " + plaintext);
//        System.out.println("key         : " + key);
//        System.out.println("KEY         : " + KEY);
        System.out.println("sifreli metin : " + ciphertext);
    }
}