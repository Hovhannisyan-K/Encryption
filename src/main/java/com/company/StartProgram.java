package com.company;

public class StartProgram {
	public static void main(String[] args) {
		CaesarEncryption encryption = new CaesarEncryption("abcdefghijklmnopqrstuvwxyz", 1);
		
		System.out.println(encryption.getPlainAlphabet());
		System.out.println("Plain Alphabet Length: " + encryption.getPlainAlphabet().length());
		System.out.println(encryption.getShiftedAlphabet());
		System.out.println("Shifted Alphabet Length: " + encryption.getShiftedAlphabet().length());
		encryption.encrypt("acddr");
		System.out.println("ENCRYPTED PASSWORD: " + encryption.getPasswordEncrypted());
		System.out.println(encryption.decrypt("acddr"));
	}
	
}
