package client;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Menus {
	static Scanner scnr = new Scanner(System.in);
	
	public static void addApp() {
		String tempName;
		int tempOnWattage;
		double tempProbOn;
		int tempID;
		char tempSmart;
		boolean isSmart;
		double tempRatio;
		System.out.println("Enter Name:");
			tempName = scnr.next();
		System.out.println("Enter On Wattage:");
			tempOnWattage = scnr.nextInt();
		System.out.println("Enter Probability of On:");
			tempProbOn = scnr.nextDouble();
		System.out.println("Enter Room ID:");
			tempID = scnr.nextInt();
		System.out.println("Is Smart Appliance? (y/n):");
			tempSmart = scnr.next().charAt(0);
			if(tempSmart == 'y' || tempSmart == 'Y')
				isSmart = true;
			else
				isSmart = false;
			if(isSmart)
				tempRatio = scnr.nextDouble();
		
		//if(isSmart)
			
	}
	public static void removeApp() {
		
	}
	public static void listApp() {
		
	}
	public static void readApp() {
		
	}
}
