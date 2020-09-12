/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bilgiguvenligi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hsyn_
 */
public class railfence {

    int depth;

    String Encryption(String plainText, int depth) throws Exception {
        int r = depth, len = plainText.length();
        int c = len / depth;
        char mat[][] = new char[r][c];
        int k = 0;

        String cipherText = "";

        for (int i = 0; i < c; i++) {
            for (int j = 0; j < r; j++) {
                if (k != len) {
                    mat[j][i] = plainText.charAt(k++);
                } else {
                    mat[j][i] = 'X';
                }
            }
        }
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                cipherText += mat[i][j];
            }
        }
        return cipherText;
    }

    String Decryption(String cipherText, int depth) throws Exception {
        int r = depth, len = cipherText.length();
        int c = len / depth;
        char mat[][] = new char[r][c];
        int k = 0;

        String plainText = "";

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                mat[i][j] = cipherText.charAt(k++);
            }
        }
        for (int i = 0; i < c; i++) {
            for (int j = 0; j < r; j++) {
                plainText += mat[j][i];
            }
        }

        return plainText;
    }

    public void railfenceRun() {

        try {
             File oku1 = new File("key.txt");
        FileReader filereader2 = new FileReader(oku1);
        String line1;

        BufferedReader br1 = new BufferedReader(filereader2);
        line1 = br1.readLine();

        System.out.println("Enter key: ");
        depth = Integer.valueOf(line1);
            BufferedReader metin = null;
            BufferedReader keymetin = null;
            PrintWriter sifrelimetincikti = null;
            PrintWriter sifresizmetincikti = null;
            try {
                metin = new BufferedReader(new FileReader("metin.txt"));
                keymetin = new BufferedReader(new FileReader("key.txt"));
                sifrelimetincikti = new PrintWriter(new FileWriter("şifrelimetin.txt"));
                sifresizmetincikti = new PrintWriter(new FileWriter("şifresiçözülmüşmetin.txt"));
                String plainText = metin.readLine();
                String cipherText = Encryption(plainText, depth);
                String decryptedText = Decryption(cipherText, depth);
                sifrelimetincikti.println(cipherText);
                sifresizmetincikti.println(decryptedText);
                System.out.println(depth);
                System.out.println(plainText);
                System.out.println(cipherText);
                System.out.println(decryptedText);

            } catch (Exception ex) {
                Logger.getLogger(railfence.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (metin != null) {
                    metin.close();
                }
                if (keymetin != null) {
                    keymetin.close();
                }
                if (sifrelimetincikti != null) {
                    sifrelimetincikti.close();
                }
                if (sifresizmetincikti != null) {
                    sifresizmetincikti.close();
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(railfence.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(railfence.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
