package com.psl.encryptdecrypt;

import com.github.windpapi4j.WinAPICallFailedException;
import com.github.windpapi4j.WinDPAPI;

import java.util.Base64;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Encryption {
		
	 private static WinDPAPI winDPAPI;

	    public static String encrypt(String plaintext) throws WinAPICallFailedException {
	        byte[] encryptedBytes = winDPAPI.protectData(plaintext.getBytes(UTF_8));
	        return Base64.getEncoder().encodeToString(encryptedBytes);
	    }
}
