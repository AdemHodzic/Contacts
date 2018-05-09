package com.adem.App;

import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.Character.isLetterOrDigit;

public class InputUtil {
	private Scanner input = new Scanner(System.in);
	
	public InputUtil() {

	}
	
	public String getString() {

		String str = "";
		try {
			str = input.next();
			if(!validWord(str))
				throw new InputMismatchException("We only support alphabetic characters and digits.");
		}catch (InputMismatchException e){
			System.out.println("Fatal Error!");
		}

		return str;
	}

	private boolean validWord(String str) {
		for(char ch : str.toCharArray()){
			if(!isLetterOrDigit(ch))
				return false;
		}
		return true;
	}

	public int getInt() {
		String x = "";
		try{
			x = input.next();
			if(!confirmNumber(x))
				throw new InputMismatchException("Not a number");
		}catch (InputMismatchException e){
			System.out.println("I'm sorry. but we don't support anything tha tis not a number");
		}
		return Integer.parseInt(x);
	}

	private boolean confirmNumber(String x) {
		for(char ch : x.toCharArray()){
			if(!Character.isDigit(ch))
				return false;
		}
		return true;
	}

	public void consume(){
		input.nextLine();
	}
	
}
