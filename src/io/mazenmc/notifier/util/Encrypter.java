package io.mazenmc.notifier.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.UUID;

public final class Encrypter {

    private final static String IV = "AAAAAAAAAAAAAAAA";

    /**
     * Encrypts the text provided with the key provided (key is required to decrypt)
     * @param plainText The text you wish to encrypt
     * @param encryptionKey The key you wish to use to encrypt the text
     * @return Encrypted text
     * @throws Exception
     */
    public static byte[] encrypt(String plainText, UUID encryptionKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
        SecretKeySpec key = new SecretKeySpec(encryptionKey.toString().getBytes("UTF-8"), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, key,new IvParameterSpec(IV.getBytes("UTF-8")));
        return cipher.doFinal(plainText.getBytes("UTF-8"));
    }

    /**
     * Decrypts the bytes provided with the key provided
     * @param cipherText The bytes you wish to decrypt
     * @param encryptionKey The key you wish to use to decrypt the text
     * @return Decrypted text
     * @throws Exception
     */
    public static String decrypt(byte[] cipherText, UUID encryptionKey) throws Exception{
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
        SecretKeySpec key = new SecretKeySpec(encryptionKey.toString().getBytes("UTF-8"), "AES");
        cipher.init(Cipher.DECRYPT_MODE, key,new IvParameterSpec(IV.getBytes("UTF-8")));
        return new String(cipher.doFinal(cipherText),"UTF-8");
    }
}
