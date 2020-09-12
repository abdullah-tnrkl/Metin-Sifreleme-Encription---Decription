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

public class sezarEncryption {

    public  void SezarEncryptedMesaj() throws FileNotFoundException, IOException {
        String mesaj, encryptedMesaj = "";
        int sifre;
        char cc;

        File oku = new File("metin.txt");
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

        sifre = Integer.valueOf(line1);

        for (int i = 0; i < mesaj.length(); ++i) {
            cc = mesaj.charAt(i);

            if (cc >= 'a' && cc <= 'z') {
                cc = (char) (cc + sifre);

                if (cc > 'z') {
                    cc = (char) (cc - 'z' + 'a' - 1);
                }

                encryptedMesaj += cc;
            } else if (cc >= 'A' && cc <= 'Z') {
                cc = (char) (cc + sifre);

                if (cc > 'Z') {
                    cc = (char) (cc - 'Z' + 'A' - 1);
                }

                encryptedMesaj += cc;
            } else {
                encryptedMesaj += cc;
            }
        }
        File file = new File("şifrelimetin.txt");
        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fileWriter = new FileWriter(file, false);
        BufferedWriter bWriter = new BufferedWriter(fileWriter);
        bWriter.write(encryptedMesaj);
        bWriter.close();
        System.out.println("okunan deger      : " + mesaj);
        System.out.println("okunan sifre      : " + sifre);
        System.out.println("sifrelenmis mesaj : " + encryptedMesaj);
    }
}
