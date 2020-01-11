package com.company;

import org.paukov.combinatorics3.Generator;

import java.util.List;
import java.util.stream.Collectors;

public class CaesarEncryption implements Encryption {
	private final String plainAlphabet;
	private final int shiftSize;
	private StringBuilder shiftedAlphabet;
	private StringBuilder password;
	
	public CaesarEncryption(String plainAlphabet, int shiftSize) {
		this.plainAlphabet = plainAlphabet;
		this.shiftSize     = shiftSize;
		shiftedAlphabet    = new StringBuilder();
		this.password      = new StringBuilder();
		generateShiftedAlphabet(plainAlphabet.split(""));
	}
	
	@Override
	public void encrypt(String passwordToEncrypt) {
		for (int i = 0; i < passwordToEncrypt.length(); i++) {
			for (int j = 0; j < plainAlphabet.length(); j++) {
				if (passwordToEncrypt.charAt(i) == plainAlphabet.charAt(j)) {
					password.append(shiftedAlphabet.charAt(j));
				}
			}
		}
	}
	
	@Override
	public String decrypt(String login) {
		if (password.length() == 0) {
			return "NO ENCRYPTED PASSWORD DETECTED...";
		}
		else {
			int counter = 0;
			String result = "***";
			int passwordToDecryptLength = password.length();
			String[] plainAlphabetArray = plainAlphabet.split("");
			List<List<String>> allCombinations = Generator.permutation(plainAlphabetArray).withRepetitions(passwordToDecryptLength).stream()
														  .collect(Collectors.toList());
			for (List<String> allCombination : allCombinations) {
				StringBuilder temp = new StringBuilder();
				System.out.println(++counter);
				for (String s : allCombination) {
					temp.append(s);
				}
				if (temp.toString().equals(login)) {
					result = temp.toString();
					return result;
				}
			}
			return result;
		}
	}
	
	private void generateShiftedAlphabet(String[] plainAlphabet) {
		int newIndex = shiftSize % plainAlphabet.length;
		for (int i = 0; i < plainAlphabet.length; i++) {
			if (newIndex >= plainAlphabet.length) {
				newIndex = 0;
			}
			shiftedAlphabet.append(plainAlphabet[newIndex]);
			newIndex++;
		}
	}
	
	public String getPlainAlphabet() {
		return plainAlphabet;
	}
	
	public StringBuilder getPasswordEncrypted() {
		return password;
	}
	
	public StringBuilder getShiftedAlphabet() {
		return shiftedAlphabet;
	}
}
