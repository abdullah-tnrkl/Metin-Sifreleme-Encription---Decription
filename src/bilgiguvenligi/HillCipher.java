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
public class HillCipher {

    public void hillRun() {
        cipherEncryption();
        cipherDecryption();
    }

    private static void cipherDecryption() {
        try {
            BufferedReader sifrelimetincikti = null;
            BufferedReader mkey = null;
            PrintWriter sifresizmetincikti = null;
            try {
                sifrelimetincikti = new BufferedReader(new FileReader("şifrelimetin.txt"));
                mkey = new BufferedReader(new FileReader("key.txt"));
                sifresizmetincikti = new PrintWriter(new FileWriter("şifresiçözülmüşmetin.txt"));
                String msg = sifrelimetincikti.readLine();
                msg = msg.replaceAll("\\s", "");
                msg = msg.toUpperCase();
                int lenChk = 0;
                if (msg.length() % 2 != 0) {
                    msg += "0";
                    lenChk = 1;
                }
                int[][] msg2D = new int[2][msg.length()];
                int itr1 = 0;
                int itr2 = 0;
                for (int i = 0; i < msg.length(); i++) {
                    if (i % 2 == 0) {
                        msg2D[0][itr1] = ((int) msg.charAt(i)) - 65;
                        itr1++;
                    } else {
                        msg2D[1][itr2] = ((int) msg.charAt(i)) - 65;
                        itr2++;
                    } 
                } 
                String key = mkey.readLine();
                key = key.replaceAll("\\s", "");
                key = key.toUpperCase();
                int[][] key2D = new int[2][2];
                int itr3 = 0;
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 2; j++) {
                        key2D[i][j] = (int) key.charAt(itr3) - 65;
                        itr3++;
                    }
                }
                int deter = key2D[0][0] * key2D[1][1] - key2D[0][1] * key2D[1][0];
                deter = moduloFunc(deter, 26);
                int mulInverse = -1;
                for (int i = 0; i < 26; i++) {
                    int tempInv = deter * i;
                    if (moduloFunc(tempInv, 26) == 1) {
                        mulInverse = i;
                        break;
                    } else {
                        continue;
                    } 
                } 
                int swapTemp = key2D[0][0];
                key2D[0][0] = key2D[1][1];
                key2D[1][1] = swapTemp;
                key2D[0][1] *= -1;
                key2D[1][0] *= -1;
                key2D[0][1] = moduloFunc(key2D[0][1], 26);
                key2D[1][0] = moduloFunc(key2D[1][0], 26);
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 2; j++) {
                        key2D[i][j] *= mulInverse;
                    }
                }
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 2; j++) {
                        key2D[i][j] = moduloFunc(key2D[i][j], 26);
                    }
                }
                String decrypText = "";
                int itrCount = msg.length() / 2;
                if (lenChk == 0) {
                    for (int i = 0; i < itrCount; i++) {
                        int temp1 = msg2D[0][i] * key2D[0][0] + msg2D[1][i] * key2D[0][1];
                        decrypText += (char) ((temp1 % 26) + 65);
                        int temp2 = msg2D[0][i] * key2D[1][0] + msg2D[1][i] * key2D[1][1];
                        decrypText += (char) ((temp2 % 26) + 65);
                    }
                } else {
                    for (int i = 0; i < itrCount - 1; i++) {
                        int temp1 = msg2D[0][i] * key2D[0][0] + msg2D[1][i] * key2D[0][1];
                        decrypText += (char) ((temp1 % 26) + 65);
                        int temp2 = msg2D[0][i] * key2D[1][0] + msg2D[1][i] * key2D[1][1];
                        decrypText += (char) ((temp2 % 26) + 65);
                    }
                }
                sifresizmetincikti.println(decrypText);
            } finally {
                if (sifrelimetincikti != null) {
                    sifrelimetincikti.close();
                }
                if (mkey != null) {
                    mkey.close();
                }
                if (sifresizmetincikti != null) {
                    sifresizmetincikti.close();
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(HillCipher.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HillCipher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private static void cipherEncryption() {
        try {
            BufferedReader metin = null;
            BufferedReader mkey = null;
            PrintWriter sifrelimetincikti = null;
            try {
                metin = new BufferedReader(new FileReader("metin.txt"));
                mkey = new BufferedReader(new FileReader("key.txt"));
                sifrelimetincikti = new PrintWriter(new FileWriter("şifrelimetin.txt"));
                String msg = metin.readLine();
                msg = msg.replaceAll("\\s", "");
                msg = msg.toUpperCase();
                int lenChk = 0;
                if (msg.length() % 2 != 0) {
                    msg += "0";
                    lenChk = 1;
                }
                int[][] msg2D = new int[2][msg.length()];
                int itr1 = 0;
                int itr2 = 0;
                for (int i = 0; i < msg.length(); i++) {
                    if (i % 2 == 0) {
                        msg2D[0][itr1] = ((int) msg.charAt(i)) - 65;
                        itr1++;
                    } else {
                        msg2D[1][itr2] = ((int) msg.charAt(i)) - 65;
                        itr2++;
                    } 
                } 
                String key = mkey.readLine();
                key = key.replaceAll("\\s", "");
                key = key.toUpperCase();
                int[][] key2D = new int[2][2];
                int itr3 = 0;
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 2; j++) {
                        key2D[i][j] = (int) key.charAt(itr3) - 65;
                        itr3++;
                    }
                }
                int deter = key2D[0][0] * key2D[1][1] - key2D[0][1] * key2D[1][0];
                deter = moduloFunc(deter, 26);
                int mulInverse = -1;
                for (int i = 0; i < 26; i++) {
                    int tempInv = deter * i;
                    if (moduloFunc(tempInv, 26) == 1) {
                        mulInverse = i;
                        break;
                    } else {
                        continue;
                    } 
                } 
                if (mulInverse == -1) {
                    System.out.println("invalid key");
                    System.exit(1);
                }
                String encrypText = "";
                int itrCount = msg.length() / 2;
                if (lenChk == 0) {
                    for (int i = 0; i < itrCount; i++) {
                        int temp1 = msg2D[0][i] * key2D[0][0] + msg2D[1][i] * key2D[0][1];
                        encrypText += (char) ((temp1 % 26) + 65);
                        int temp2 = msg2D[0][i] * key2D[1][0] + msg2D[1][i] * key2D[1][1];
                        encrypText += (char) ((temp2 % 26) + 65);
                    }
                } else {
                    for (int i = 0; i < itrCount - 1; i++) {
                        int temp1 = msg2D[0][i] * key2D[0][0] + msg2D[1][i] * key2D[0][1];
                        encrypText += (char) ((temp1 % 26) + 65);
                        int temp2 = msg2D[0][i] * key2D[1][0] + msg2D[1][i] * key2D[1][1];
                        encrypText += (char) ((temp2 % 26) + 65);
                    }
                }
                sifrelimetincikti.println(encrypText);
            } finally {
                if (metin != null) {
                    metin.close();
                }
                if (mkey != null) {
                    mkey.close();
                }
                if (sifrelimetincikti != null) {
                    sifrelimetincikti.close();
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(HillCipher.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HillCipher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static int moduloFunc(int a, int b) {
        int result = a % b;
        if (result < 0) {
            result += b;
        }
        return result;
    }
}