/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bilgiguvenligi;

import java.awt.Point;
import java.io.BufferedReader;
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
public class playfair {

    public char[][] charTable;
    public Point[] positions;

    public void playfairRun() {
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
                String key = keymetin.readLine();
                String jti = "n";
                String txt;
                while (null != (txt = metin.readLine())) {
                    txt = txt.toUpperCase();
                    char[] playfairDizisi = txt.toCharArray();

                    for (int i = 0; i < playfairDizisi.length; ++i) {
                        if (playfairDizisi[i] == 'İ') {
                            playfairDizisi[i] = 'I';
                        }
                    }
                    txt = String.valueOf(playfairDizisi);
                    boolean changeJtoI = jti.equalsIgnoreCase("y");

                    createTable(key, changeJtoI);

                    String enc = encode(prepareText(txt, changeJtoI));

                    sifrelimetincikti.println(enc);

                    sifresizmetincikti.println(decode(enc));
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
            Logger.getLogger(playfair.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(playfair.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String prompt(String promptText, Scanner sc, int minLen) {
        String s;
        do {
            System.out.print(promptText);
            s = sc.nextLine().trim();
        } while (s.length() < minLen);
        return s;
    }

    public static String prepareText(String s, boolean changeJtoI) {
        s = s.toUpperCase().replaceAll("[^A-Z]", "");
        return changeJtoI ? s.replace("J", "I") : s.replace("Q", "");
    }

    public void createTable(String key, boolean changeJtoI) {
        charTable = new char[5][5];
        positions = new Point[26];

        String s = prepareText(key + "ABCDEFGHIJKLMNOPQRSTUVWXYZ", changeJtoI);

        int len = s.length();
        for (int i = 0, k = 0; i < len; i++) {
            char c = s.charAt(i);
            if (positions[c - 'A'] == null) {
                charTable[k / 5][k % 5] = c;
                positions[c - 'A'] = new Point(k % 5, k / 5);
                k++;
            }
        }
    }

    public String encode(String s) {
        StringBuilder sb = new StringBuilder(s);

        for (int i = 0; i < sb.length(); i += 2) {
            if (i == sb.length() - 1) {
                sb.append(sb.length() % 2 == 1 ? 'X' : "");
            } else if (sb.charAt(i) == sb.charAt(i + 1)) {
                sb.insert(i + 1, 'X');
            }
        }
        return codec(sb, 1);
    }

    public String decode(String s) {
//        StringBuilder sb = new StringBuilder(s);
//        for (int i = 0; i < sb.length(); i += 2) {
//            if (i == sb.length()) {
//                sb.insert(i, " ");
//            }
//        }
        return codec(new StringBuilder(s), 4);
    }

    public String codec(StringBuilder text, int direction) {
        int len = text.length();
        for (int i = 0; i < len; i += 2) {
            char a = text.charAt(i);
            char b = text.charAt(i + 1);

            int row1 = positions[a - 'A'].y;
            int row2 = positions[b - 'A'].y;
            int col1 = positions[a - 'A'].x;
            int col2 = positions[b - 'A'].x;

            if (row1 == row2) {
                col1 = (col1 + direction) % 5;
                col2 = (col2 + direction) % 5;

            } else if (col1 == col2) {
                row1 = (row1 + direction) % 5;
                row2 = (row2 + direction) % 5;

            } else {
                int tmp = col1;
                col1 = col2;
                col2 = tmp;
            }

            text.setCharAt(i, charTable[row1][col1]);
            text.setCharAt(i + 1, charTable[row2][col2]);
        }
        return text.toString();
    }
}
