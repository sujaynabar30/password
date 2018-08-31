package com.psl.encryptdecrypt;

import com.github.windpapi4j.WinAPICallFailedException;
import com.github.windpapi4j.WinDPAPI;

import java.util.Base64;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Decryption {
	
	private static WinDPAPI winDPAPI;
	
	public static String decrypt(String encryptedString) throws WinAPICallFailedException {
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedString);
        return new String(winDPAPI.unprotectData(encryptedBytes), UTF_8);
    }
}
