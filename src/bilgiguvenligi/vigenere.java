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
public class vigenere {

    public void vigenereRun() {
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
                String satir;
                String ksatir;
                while ((satir = metin.readLine()) != null && (ksatir = keymetin.readLine()) != null) {
                    satir = satir.toUpperCase();
                    ksatir = ksatir.toUpperCase();
                    char[] vigenereDizisi = satir.toCharArray();
                    char[] key = ksatir.toCharArray();
                    int msgLen = vigenereDizisi.length, i, j;
                    char newKey[] = new char[msgLen];
                    char encryptedMsg[] = new char[msgLen];
                    char decryptedMsg[] = new char[msgLen];
                    for (i = 0; i < msgLen; ++i) {
                        if (vigenereDizisi[i] == 'İ') {
                            vigenereDizisi[i] = 'I';
                        }
                    }
                    for (i = 0, j = 0; i < msgLen; ++i, ++j) {
                        if (j == key.length) {
                            j = 0;
                        }
                        newKey[i] = key[j];
                    }
                    for (i = 0; i < msgLen; ++i) {
                        if (vigenereDizisi[i] == ' ') {
                            vigenereDizisi[i] = ' ';
                        } else {
                            encryptedMsg[i] = (char) (((vigenereDizisi[i] + newKey[i]) % 26) + 'A');
                        }
                    }
                    for (i = 0; i < msgLen; ++i) {
                        if (vigenereDizisi[i] == ' ') {
                            vigenereDizisi[i] = ' ';
                        } else {
                            decryptedMsg[i] = (char) ((((encryptedMsg[i] - newKey[i]) + 26) % 26) + 'A');
                        }
                    }
                    String sifreliCumle = String.valueOf(encryptedMsg);
                    sifrelimetincikti.println(sifreliCumle);
                    String sifresizCumle = String.valueOf(decryptedMsg);
                    sifresizmetincikti.println(sifresizCumle);
                    
                }
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
            Logger.getLogger(vigenere.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(vigenere.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
