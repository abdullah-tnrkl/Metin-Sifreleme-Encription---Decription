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
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author abdul
 */
public class onetimepad {

    public void onetimepadRun() {
        try {
            BufferedReader metin = null;
            PrintWriter sifrelimetincikti = null;
            PrintWriter sifresizmetincikti = null;
            try {
                metin = new BufferedReader(new FileReader("metin.txt"));
                sifrelimetincikti = new PrintWriter(new FileWriter("şifrelimetin.txt"));
                sifresizmetincikti = new PrintWriter(new FileWriter("şifresiçözülmüşmetin.txt"));
                String text = metin.readLine();
                String key = RandomAlpha(text.length());
                String enc = OTPEncryption(text, key);
                String dec = OTPDecryption(enc, key);
                sifrelimetincikti.println(enc);
                sifresizmetincikti.println(dec);

            } finally {
                if (metin != null) {
                    metin.close();
                }
                if (sifrelimetincikti != null) {
                    sifrelimetincikti.close();
                }
                if (sifresizmetincikti != null) {
                    sifresizmetincikti.close();
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(onetimepad.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(onetimepad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String RandomAlpha(int len) {
        Random r = new Random();
        String key = "";
        for (int x = 0; x < len; x++) {
            key = key + (char) (r.nextInt(26) + 'A');
        }
        return key;
    }

    public static String OTPEncryption(String text, String key) {
        String alphaU = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphaL = "abcdefghijklmnopqrstuvwxyz";

        int len = text.length();

        String sb = "";
        for (int x = 0; x < len; x++) {
            char get = text.charAt(x);
            char keyget = key.charAt(x);
            if (Character.isUpperCase(get)) {
                int index = alphaU.indexOf(get);
                int keydex = alphaU.indexOf(Character.toUpperCase(keyget));

                int total = (index + keydex) % 26;

                sb = sb + alphaU.charAt(total);
            } else if (Character.isLowerCase(get)) {
                int index = alphaL.indexOf(get);
                int keydex = alphaU.indexOf(Character.toLowerCase(keyget));

                int total = (index + keydex) % 26;

                sb = sb + alphaL.charAt(total);
            } else {
                sb = sb + get;
            }
        }

        return sb;
    }

    public static String OTPDecryption(String text, String key) {
        String alphaU = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphaL = "abcdefghijklmnopqrstuvwxyz";

        int len = text.length();

        String sb = "";
        for (int x = 0; x < len; x++) {
            char get = text.charAt(x);
            char keyget = key.charAt(x);
            if (Character.isUpperCase(get)) {
                int index = alphaU.indexOf(get);
                int keydex = alphaU.indexOf(Character.toUpperCase(keyget));

                int total = (index - keydex) % 26;
                total = (total < 0) ? total + 26 : total;

                sb = sb + alphaU.charAt(total);
            } else if (Character.isLowerCase(get)) {
                int index = alphaL.indexOf(get);
                int keydex = alphaU.indexOf(Character.toLowerCase(keyget));

                int total = (index - keydex) % 26;
                total = (total < 0) ? total + 26 : total;

                sb = sb + alphaL.charAt(total);
            } else {
                sb = sb + get;
            }
        }

        return sb;
    }

}
