/*
* Copyright (C) 2014 Mazen K., ROTNdev
* This file is part of MCNotifier.
*
* MCNotifier for Bukkit is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, version 3 to be exact
*
* MCNotifier is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with MCNotifier. If not, see <http://www.gnu.org/licenses/>.
*/

package io.mazenmc.notifier.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.GeneralSecurityException;
import java.util.UUID;

public final class Encrypter {

    /**
     * @param plainText the text to be encrypted
     * @param key       the key to encypt with
     * @return
     * @throws GeneralSecurityException if encryption failed
     */
    public static String encrypt(String plainText, UUID key) throws GeneralSecurityException {
        String encryptionKey = key.toString().replaceAll("-", "");
        byte[] b = new byte[encryptionKey.length() / 2];
        for (int i = 0; i < b.length; i++) {
            int index = i * 2;
            int v = Integer.parseInt(encryptionKey.substring(index, index + 2), 16);
            b[i] = (byte) v;
        }
        SecretKeySpec sks = new SecretKeySpec(b, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, sks, cipher.getParameters());
        byte[] encrypted = cipher.doFinal(plainText.getBytes());
        StringBuilder sb = new StringBuilder(b.length * 2);
        for (byte aB : encrypted) {
            int v = aB & 0xff;
            if (v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString().toUpperCase();
    }

    /**
     * @param encryptedTextBytes the encrypted string
     * @param key                the key to decrypt with
     * @return decrypted string
     * @throws GeneralSecurityException if decryption fails
     */
    public static String decrypt(String encryptedTextBytes, UUID key) throws GeneralSecurityException {
        String encryptionKey = key.toString().replaceAll("-", "");
        byte[] b = new byte[encryptionKey.length() / 2];
        for (int i = 0; i < b.length; i++) {
            int index = i * 2;
            int v = Integer.parseInt(encryptionKey.substring(index, index + 2), 16);
            b[i] = (byte) v;
        }
        SecretKeySpec sks = new SecretKeySpec(b, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, sks);
        b = new byte[encryptedTextBytes.length() / 2];
        for (int i = 0; i < b.length; i++) {
            int index = i * 2;
            int v = Integer.parseInt(encryptedTextBytes.substring(index, index + 2), 16);
            b[i] = (byte) v;
        }
        byte[] decrypted = cipher.doFinal(b);
        return new String(decrypted);
    }
}
