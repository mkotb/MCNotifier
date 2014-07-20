/*
* Copyright (C) 2014 Mazen K.
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

import java.nio.charset.Charset;
import java.util.UUID;

public final class Encrypter {


    public static String encrypt(String plainText, UUID uuid) throws Exception {
        return new String(plainText.getBytes(Charset.forName("UTF-8")));

        //Empty until written at a later point in time
    }

    public static String decrypt(String encryptedTextBytes, UUID uuid) throws Exception {
        return encryptedTextBytes;

        //Empty until written at a later point in time
    }
}
