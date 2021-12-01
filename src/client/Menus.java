package client;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

public class Menus {
    static Scanner scnr = new Scanner(System.in);
    
    public static void addApp(ArrayList<Appliance> applianceList) {
        String tempName;
        int tempOnWattage = 0, tempID;
        double tempProbOn, tempRatio = 0;
        char tempSmart;
        boolean isSmart;
        boolean input;
        System.out.println("Enter Name:");
            tempName = scnr.next();
        while(true) {
        	try {
        		System.out.println("Enter On Wattage:");
        		tempOnWattage = scnr.nextInt();
        		break;
        	} catch(InputMismatchException E){ 
        		System.out.println("Input is not an Integer");
        		scnr.next();
        	}
        }
        while(true) {
        	try{
        		System.out.println("Enter Probability of On:");
        		tempProbOn = scnr.nextDouble();    
        		if (tempProbOn > 0 && tempProbOn <= 1)
        			break;
        		throw new ArithmeticException("Input is not greater then 0 and less then/equal to 1."); 
        	} catch(InputMismatchException E) {
        		System.out.println("Input is not greater then 0 and less then/equal to 1.");
        		scnr.next();
        	} catch(ArithmeticException E) {
        		System.out.println("Input is not greater then 0 and less then/equal to 1.");
        	}
        }
        while(true) {
        	try {
        		System.out.println("Enter Room ID:");
        		tempID = scnr.nextInt();
        		break;
        	} catch (InputMismatchException E) {
        		System.out.println("Invalid Room ID:");
        		scnr.next();
        	}
        }
        while(true) {
        	try {
            	System.out.println("Is Smart Appliance? (y/n):");
                tempSmart = scnr.next().charAt(0);
                if (tempSmart == 'y' || tempSmart == 'Y'||tempSmart == 'n' || tempSmart == 'N')
                	break;
                throw new ArithmeticException();
        	} catch (InputMismatchException E) {
        		System.out.println("Answer is not y or n.");
        		scnr.next();
        	} catch (ArithmeticException E) {
        		System.out.println("Answer is not y or n.");
        	}
        }
            if(tempSmart == 'y' || tempSmart == 'Y')
                isSmart = true;
            else
                isSmart = false;
            if (isSmart) {
            	while(true) {
            		try {
            			System.out.println("Enter Low Ratio:");
            			tempRatio = scnr.nextDouble(); 
            			if(tempRatio > 0 && tempRatio <= 1)
            				break;
            			throw new ArithmeticException(); 
            		} catch (InputMismatchException E) {
            			System.out.println("Input is not greater then 0 and less then/equal to 1.");
            			scnr.next();
            		} catch (ArithmeticException E) {
            			System.out.println("Input is not greater then 0 and less then/equal to 1.");
            		}
            	}
            }
        Appliance app;
        if (isSmart) {
            app = new SmartAppliance(tempID, tempName, tempOnWattage, tempProbOn, tempRatio);
        }
        else {
            app = new Appliance(tempID, tempName, tempOnWattage, tempProbOn);
        }    
        applianceList.add(app);
    }
    public static void removeApp(ArrayList<Appliance> applianceList) {
    	System.out.println("<!>Item Index Changes Once an Item Above it is removed <!>");
    	System.out.println("<!>Check Index in List Function <!>");
    	System.out.println("<!> 0 Cancels this function <!>");
    	System.out.println("Enter Appliance Index:");
    	int num = scnr.nextInt()-1;
    	if(num!=-1) {
    		System.out.print("Removed: ");
    		applianceList.get(num).printInfo();
    		System.out.println();
    		applianceList.remove(num);
    	}
    }
    public static void listApp(ArrayList<Appliance> applianceList) {
        Iterator<Appliance> it = applianceList.iterator();
        int count = 0;
        System.out.println("Index\tRoom/Name/OnWattage/ProbabilityOn/LowRatio/LowWattage");
        while (it.hasNext()) 
        {
        	count++;
        	System.out.print(count+"\t");
            it.next().printInfo();
            System.out.println();
        }
    }
    public static void readApp(ArrayList<Appliance> applianceList) {
        String tempFile;
    	System.out.println("Enter File Path:");
        tempFile = scnr.next();
        AppClient.readAppFile(tempFile, applianceList);
    }
}