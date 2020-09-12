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
import java.util.Scanner;

public class sezarDecryption {

    public  void sRun() throws FileNotFoundException, IOException {
        String mesaj, decryptionmesaj = "";
        int sifre;
        char cc;
        File oku = new File("şifrelimetin.txt");
        FileReader fileReader = new FileReader(oku);
        String line;

        BufferedReader br = new BufferedReader(fileReader);
        line = br.readLine();
        mesaj = line;

        File oku1 = new File("key.txt");
        FileReader filereader2 = new FileReader(oku1);
        String line1;

        BufferedReader br1 = new BufferedReader(filereader2);
        line1 = br1.readLine();

        System.out.println("Enter key: ");
        sifre = Integer.valueOf(line1);

        for (int i = 0; i < mesaj.length(); ++i) {
            cc = mesaj.charAt(i);

            if (cc >= 'a' && cc <= 'z') {
                cc = (char) (cc - sifre);

                if (cc < 'a') {
                    cc = (char) (cc + 'z' - 'a' + 1);
                }

                decryptionmesaj += cc;
            } else if (cc >= 'A' && cc <= 'Z') {
                cc = (char) (cc - sifre);

                if (cc < 'A') {
                    cc = (char) (cc + 'Z' - 'A' + 1);
                }

                decryptionmesaj += cc;
            } else {
                decryptionmesaj += cc;
            }
        }
        File file = new File("şifresiçözülmüşmetin.txt");
        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fileWriter = new FileWriter(file, false);
        BufferedWriter bWriter = new BufferedWriter(fileWriter);
        bWriter.write(decryptionmesaj);
        bWriter.close();
        System.out.println("okunan deger           :"  + mesaj);
        System.out.println("okunan sifre           : " + sifre);
        System.out.println("sifresi cozulmus metin : " + decryptionmesaj);
    }
}
