/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bilgiguvenligi;

import java.util.Scanner;

/**
 *
 * @author abdul
 */
public class BilgiGuvenligi {

    /**
     * @param args the command line arguments
     */
    public  static void main(String[] args) throws Exception {
        int secim;
        System.out.println("Lütfen Şifrelem yapacaginiz metodu seciniz ");
        System.out.print("1) Caesar  Cipher\n2) MonoAlphetik Cipher\n3) Vernam Cipher\n4) Vigenere Cipher\n5) Railfence Cipher\n6) Hill Cipher\n7) One Time Pad Cipher\n8) Playfair Cipher\n9) Polyalphabetic Cipher ");
        Scanner sec = new Scanner(System.in);
        secim = sec.nextInt();
        switch (secim) {
            case 1:
                int secimm;
                
           System.out.println("Caesar Cipher seçildi..\n1)Encryption\n2)Decryption");
           Scanner scannn=new Scanner(System.in);
           secimm=scannn.nextInt();
           if(secimm==1){
               sezarEncryption sr=new sezarEncryption();
               sr.SezarEncryptedMesaj();
           }
           else if(secimm==2){
               sezarDecryption sd=new sezarDecryption();
               sd.sRun();
           }else{
               break;
           }
           
                
                break;

            case 2:
                      System.out.println(" Monoalphabetic Cipher seçildi..\n1)Encryption\n2)Decryption");
           Scanner scann=new Scanner(System.in);
           secimm=scann.nextInt();
           if(secimm==1){
              monoEncryption  me=new monoEncryption();
               me.monoEnRun();
           }
           else if(secimm==2){
               monoDecryption sd=new monoDecryption();
               sd.monoDeRun();
           }else{
               break;
           }
                break;

            case 3:
                vernam vr=new vernam();
                vr.Vernamcipp();
                break;
            case 4:
                vigenere vg=new vigenere();
                vg.vigenereRun();
                break;
            case 5:
                railfence rf=new railfence();
                rf.railfenceRun();
                break;
            case 6:
                HillCipher hp=new HillCipher();
                hp.hillRun();
                break;
            case 7:
               onetimepad otp=new onetimepad();
               otp.onetimepadRun();
                break;
            case 8:
               playfair pf=new playfair();
               pf.playfairRun();
                break;
            case 9:
                polyalphabetic pc=new polyalphabetic();
                pc.polyalphabeticRun();
                break;

            default:
                System.out.println("Hatali secim! ");
                break;
        }

//        metinIslemleri mi = new metinIslemleri();
//        mi.keyOkuma();
//        mi.metinOkuma();
////        mi.sifreliMetinYazma();
//        vigenere v = new vigenere();
////        v.vigenereRun();
//        sezar s = new sezar();
//        s.sRun();
//        s s = new s();
//        s.sRun();
//        playfair p = new playfair();
//        p.playfairRun();
//
//hill h=new hill();
//h.hillRun();
//onetimepad otp=new onetimepad();
//otp.onetimepadRun();
//polyalphabetic p=new polyalphabetic();
//p.polyalphabeticRun();
//railfence r=new railfence();
//r.railfenceRun();
    }
}
