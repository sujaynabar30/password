package com.psl.main;

import com.github.windpapi4j.InitializationFailedException;
import com.github.windpapi4j.WinAPICallFailedException;
import com.github.windpapi4j.WinDPAPI;
import com.github.windpapi4j.WinDPAPI.CryptProtectFlag;
import com.psl.demo.SimpleClass;
import com.psl.encryptdecrypt.Decryption;
import com.psl.encryptdecrypt.Encryption;
import com.psl.read.CopyToConfigFile;
import com.psl.read.ReadFromConfigFile;

import java.io.IOException;


public class MainClass {
	
	private static WinDPAPI winDPAPI;
	
	static String fileName = "D:\\Eclipse Workspace\\Practice\\PasswordProtect\\TextFile.txt";
	static String propfile = "D:\\Eclipse Workspace\\Practice\\PasswordProtect\\Configuation.properties";
	
	public static void main(String[] args) throws InitializationFailedException, WinAPICallFailedException, IOException {
		
		String plaintext = ReadFromConfigFile.readUsingFiles(fileName);
		

        if (!WinDPAPI.isPlatformSupported()) {
            System.err.println("The Windows Data Protection API (DPAPI) is not available on " + System.getProperty("os.name") + ".");
            return;
        }
        
        winDPAPI = WinDPAPI.newInstance(CryptProtectFlag.CRYPTPROTECT_UI_FORBIDDEN);
        
        System.out.println("Plain text:       \n" + plaintext);
        
        String encrypted = Encryption.encrypt(plaintext);
        System.out.println("\nEncrypted String: \n" + encrypted);
        
        CopyToConfigFile c = new CopyToConfigFile();
        c.copyEncrypted(propfile,encrypted);
        
        SimpleClass.clearTheFile();
        
        String decrypted = Decryption.decrypt(encrypted);
        System.out.println("\nDecrypted String: \n" + decrypted);
	}
}
