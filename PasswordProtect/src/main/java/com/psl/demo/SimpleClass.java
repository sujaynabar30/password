package com.psl.demo;

import com.github.windpapi4j.InitializationFailedException;
import com.github.windpapi4j.WinAPICallFailedException;
import com.github.windpapi4j.WinDPAPI;
import com.github.windpapi4j.WinDPAPI.CryptProtectFlag;
import com.psl.read.CopyToConfigFile;
import com.psl.read.ReadFromConfigFile;

import java.util.Base64;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.IOException;
import java.io.PrintWriter;

public class SimpleClass {
	
	static String fileName = "D:\\Eclipse Workspace\\Practice\\PasswordProtect\\TextFile.txt";
	static String propfile = "D:\\Eclipse Workspace\\Practice\\PasswordProtect\\Configuation.properties";

    private static WinDPAPI winDPAPI;

    public static String encrypt(String plaintext) throws WinAPICallFailedException {
        byte[] encryptedBytes = winDPAPI.protectData(plaintext.getBytes(UTF_8));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String encryptedString) throws WinAPICallFailedException {
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedString);
        return new String(winDPAPI.unprotectData(encryptedBytes), UTF_8);
    }
    
    public static void clearTheFile() throws IOException {
    	PrintWriter writer = new PrintWriter(fileName);
    	writer.print("");
    	writer.close();
    }

    public static void main(String[] args) throws InitializationFailedException, WinAPICallFailedException, IOException {

        //String plaintext = "https://www.amazon.in/";
    	
    	String plaintext = ReadFromConfigFile.readUsingFiles(fileName);
    	
    	/*Properties prop = new Properties();
    	OutputStream output = null;*/

        if (!WinDPAPI.isPlatformSupported()) {
            System.err.println("The Windows Data Protection API (DPAPI) is not available on " + System.getProperty("os.name") + ".");
            return;
        }

        winDPAPI = WinDPAPI.newInstance(CryptProtectFlag.CRYPTPROTECT_UI_FORBIDDEN);

        System.out.println("Plain text:       \n" + plaintext);

        String encrypted = encrypt(plaintext);
        System.out.println("\nEncrypted String: \n" + encrypted);
        
       /* PrintWriter out = new PrintWriter(new FileWriter(propfile));  				//write encrypted string to config file
        out.print(encrypted);  
        out.close(); */
        
        CopyToConfigFile c = new CopyToConfigFile();
        c.copyEncrypted(propfile,encrypted);
        
        clearTheFile();
        
        String decrypted = decrypt(encrypted);
        System.out.println("\nDecrypted String: \n" + decrypted);
        
    }
    
}