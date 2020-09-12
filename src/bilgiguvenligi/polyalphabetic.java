/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bilgiguvenligi;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hsyn_
 */
public class polyalphabetic {

    public void polyalphabeticRun() {
        try {
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
                String secretKey = keymetin.readLine();
                String encryptedText = encrypt(plainText, secretKey);
                String decryptedText = decrypt(encryptedText, secretKey);
                sifrelimetincikti.println(encryptedText);
                sifresizmetincikti.println(decryptedText);

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
            Logger.getLogger(polyalphabetic.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(polyalphabetic.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static String encrypt(String plainText, String secretKey) {
        StringBuffer encryptedString = new StringBuffer();
        int encryptedInt;
        for (int i = 0; i < plainText.length(); i++) {
            int plainTextInt = (int) (plainText.charAt(i) - 'A');
            int secretKeyInt = (int) (secretKey.charAt(i) - 'A');
            encryptedInt = (plainTextInt + secretKeyInt) % 26;
            encryptedString.append((char) ((encryptedInt) + (int) 'A'));
        }
        return encryptedString.toString();
    }

    private static String decrypt(String decryptedText, String secretKey) {
        StringBuffer decryptedString = new StringBuffer();
        int decryptedInt;
        for (int i = 0; i < decryptedText.length(); i++) {
            int decryptedTextInt = (int) (decryptedText.charAt(i) - 'A');
            int secretKeyInt = (int) (secretKey.charAt(i) - 'A');
            decryptedInt = decryptedTextInt - secretKeyInt;
            if (decryptedInt < 0) {
                decryptedInt += 26;
            }
            decryptedString.append((char) ((decryptedInt) + (int) 'A'));
        }
        return decryptedString.toString();
    }
}
