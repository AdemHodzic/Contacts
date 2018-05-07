package com.adem.App;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputUtil {
	private Scanner input;
	
	public InputUtil() {
		input = new Scanner(System.in);
	}
	
	public String getString() {
		return input.nextLine();
	}
	
	public int getInt() {
		int x = 0;
		try{
			x = input.nextInt();
		}catch (InputMismatchException e){
			System.out.println("I'm sorry. but we don't support anything tha tis not a number");
		}
		return x;
	}

	public void consume(){
		input.nextLine();
	}
	
}
