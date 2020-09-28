package lab001;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu extends ArrayList<String>{
	String name;
	String choice;

	public Menu() {
	}

	public Menu(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getChoice() {
		return this.choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}

	public void print(int i){
		System.out.println(i+"-"+this.name);
		
		//have sub-menu
		if (this.size()!=0) {	
			return;
		}

		//no sub-menu
		int count = 1;
		for (String s:this) {
			System.out.println(i+"-"+count++ + "-" +s);
		}
	}

	public void printMenu() {
		int i=1;
		for (String s: this) {
			System.out.println(i++ + "-" + s);
		}
		System.out.println("Others - Quit current menu");
	}

	public int getMenuChoice() {
		/*
		if (this.isEmpty()) {
			System.out.println("Bypass this");

			return -1;
		}

		else {
			*/
			System.out.println("+++++++++++++++++++");

			this.printMenu();

			Scanner sc = new Scanner(System.in);
			System.out.println("User option:");
			
			System.out.println("+++++++++++++++++++");

			return Integer.parseInt(sc.nextLine());
	}
}
