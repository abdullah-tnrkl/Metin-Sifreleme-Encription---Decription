/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bilgiguvenligi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import java.io.*;

class vernam {

    public void Vernamcipp() throws Exception {
        // vernam girdi değerinin alınması
        File oku = new File("metin.txt");
        FileReader fileReader = new FileReader(oku);
        String line;

        BufferedReader br = new BufferedReader(fileReader);
        line = br.readLine();

        // vernam şifre  değerinin alınması   
        File oku1 = new File("key.txt");
        FileReader filereader2 = new FileReader(oku1);
        String line1;
        BufferedReader br1 = new BufferedReader(filereader2);
        line1 = br1.readLine();

        BufferedReader brrr = new BufferedReader(new InputStreamReader(System.in));
        String text = line;

        String text2 = line1;
        int deger1[] = new int[text.length()];
        int deger2[] = new int[text2.length()];
        int initval[] = new int[text2.length()];
        if (text2.length() != text.length()) {

            return;
        }
        for (int i = 0; i < text.length(); i++) {
            int k = degeral(text.charAt(i));
            deger1[i] = k;
        }
        for (int i = 0; i < text2.length(); i++) {
            int k = degeral(text2.charAt(i));
            deger2[i] = k;
        }
        for (int i = 0; i < text2.length(); i++) {
            initval[i] = deger1[i] + deger2[i];
            if (initval[i] > 25) {
                initval[i] -= 26;
            }
        }

        String cipher = "";

        for (int i = 0; i < text2.length(); i++) {
            cipher += gelendeger(initval[i]);
        }
        System.out.println("Okunan text = " + line);
        System.out.println("Okunan  text2= " + line1);
        System.out.println("Yazılan  metin= " + cipher);
        File file = new File("şifrelimetin.txt");
        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fileWriter = new FileWriter(file, false);
        BufferedWriter bWriter = new BufferedWriter(fileWriter);
        bWriter.write(cipher);
        bWriter.close();

    } public static int degeral(char x) {
        int y = (int) 'a';
        return ((int) x - y);
    }

    public static char gelendeger(int x) {
        int z = x + (int) 'a';
        return ((char) z);
    }
}
