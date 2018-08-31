package com.psl.createfile;

import java.io.File;

public class Filesmethods {
	
	File file = new File("Configuation2.properties");
	
	public void createFile() {
		
		try {
			
			if(!file.exists()) {
				file.createNewFile();
				System.out.println("File created");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void deleteFile(){
		
		if(file.delete()) {
			System.out.println("'"+file.getName()+"'"+ " is deleted");
		}
		else {
			System.out.println("Operation of Deletion failed");
		}
	}
	
	public static void main(String[] args) {
		
		Filesmethods nfile = new Filesmethods();
		nfile.createFile();
		nfile.deleteFile();
		
	}
}
