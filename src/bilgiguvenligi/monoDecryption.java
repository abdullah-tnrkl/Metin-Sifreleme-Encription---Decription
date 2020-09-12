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
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class monoDecryption {

    public  void monoDeRun() throws IOException {

        FileReader fileReader = null;
        try {
            File oku = new File("şifrelimetin.txt");
            fileReader = new FileReader(oku);
            String line = null;
            BufferedReader br = new BufferedReader(fileReader);
            try {
                line = br.readLine();
            } catch (IOException ex) {
                Logger.getLogger(monoDecryption.class.getName()).log(Level.SEVERE, null, ex);
            }

            File oku1 = new File("monokey1.txt");
            FileReader filereader2 = new FileReader(oku1);
            String line1 = null;
            BufferedReader br1 = new BufferedReader(filereader2);
            try {
                line1 = br1.readLine();
            } catch (IOException ex) {
                Logger.getLogger(monoDecryption.class.getName()).log(Level.SEVERE, null, ex);
            }
            File oku2 = new File("monokey2.txt");
            FileReader filereader3 = new FileReader(oku2);
            String line2 = null;
            BufferedReader br2 = new BufferedReader(filereader3);
            try {
                line2 = br2.readLine();
            } catch (IOException ex) {
                Logger.getLogger(monoDecryption.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            String mesaj = line;
            String key = line1;
            String KEY = line2;
            String metinn = "";
            String alphabet = "abcdefghijklmnopqrstuvwxyz";
            String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            
            int i, j;
            for (i = 0; i < mesaj.length(); i++) {
                for (j = 0; j < key.length(); j++) {
                    if (mesaj.charAt(i) == key.charAt(j)) {
                        metinn += alphabet.charAt(j);
                        break;
                    }
                    if (mesaj.charAt(i) == KEY.charAt(j)) {
                        metinn += ALPHABET.charAt(j);
                        break;
                    }
                }
                if (j == KEY.length()) {
                    metinn += mesaj.charAt(i);
                }
            } File file = new File("şifresiçözülmüşmetin.txt");
        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fileWriter = new FileWriter(file, false);
        BufferedWriter bWriter = new BufferedWriter(fileWriter);
        bWriter.write(metinn);
        bWriter.close();
          
            System.out.println("Okunan deger: " + mesaj);
            System.out.println("1.key          : " + key);
            System.out.println("2.key          : " + KEY);
            System.out.println("çözülmüs metin : " + metinn);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(monoDecryption.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fileReader.close();
            } catch (IOException ex) {
                Logger.getLogger(monoDecryption.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
