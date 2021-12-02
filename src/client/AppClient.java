/* This is a stub code. You can modify it as you wish. */
package client;

import java.io.*;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
class AppClient{
	
	public static void readAppFile(String file, ArrayList<Appliance> applianceList){ // method to read the comma seperated appliance file.

		Scanner scan;

		ArrayList<SmartAppliance> SAs = new ArrayList<SmartAppliance>();

		try {
			File myFile=new File(file);
			scan=new Scanner(myFile);//each line has the format
			//locationID,name of app,onPower,probability of staying on, smart or not,Smart appliances (if "on") power reduction percent when changed to "low" status(floating point, i.e..33=33%).
			

			/*Complete the method*/

			while (scan.hasNext()) {
				String str = scan.nextLine();
				String [] appStr = str.split(",");
				
				
				int locationID = Integer.parseInt(appStr[0]);
				String appName = appStr[1];
				int onPower = Integer.parseInt(appStr[2]);
				double probOn = Double.parseDouble(appStr[3]);
				String isSmart = appStr[4];
				double lowRatio = Double.parseDouble(appStr[5]);
				
				Appliance app;
				if (isSmart.equals("false")) {
					app = new Appliance(locationID, appName, onPower, probOn);
				}
				else {
					app = new SmartAppliance(locationID, appName, onPower, probOn, lowRatio);
				}
				
				applianceList.add(app);

			}
			
			scan.close();
		}catch(IOException ioe){ 
			System.out.println("The file can not be read");
		}
	}
	
	
	public static void main(String []args){
		
		ArrayList<Appliance> applianceList = new ArrayList<Appliance>();

		
		AppClient app = new AppClient();
		//User interactive part
		String option1, appTextFile;
		Scanner scan = new Scanner(System.in);
		int maxWattage, timeSteps;
		
		do {
			System.out.println("Enter max total wattage: ");
			maxWattage = scan.nextInt();
			if (maxWattage < 0)
				System.out.println("Invalid input. Max wattage cannot be a negative number.");
		} while (maxWattage < 0);
		
		do {
			System.out.println("Enter number of time steps: ");
			timeSteps = scan.nextInt();
			if (timeSteps <= 0)
				System.out.println("Invalid input. Time steps cannot be less than 1.");
		} while (timeSteps <= 0);
		
		
		System.out.println("Enter the name of the appliance text file: ");
		appTextFile = scan.nextLine();
		
		
		while(true){// Application menu to be displayed to the user.
			System.out.println("------------------------");
			System.out.println("Select an option:");
			System.out.println("Type \"A\" Add an appliance");
			System.out.println("Type \"D\" Delete an appliance");	
			System.out.println("Type \"L\" List the appliances");
			System.out.println("Type \"F\" Read Appliances from a file");
			System.out.println("Type \"S\" To Start the simulation");
			System.out.println("Type \"Q\" Quit the program");
			option1=scan.nextLine();
			/* Complete the skeleton code below */	
			if(option1.equals("a")||option1.equals("A"))
				Menus.addApp(applianceList);
			if(option1.equals("d")||option1.equals("D"))
				Menus.removeApp(applianceList);
			if(option1.equals("l")||option1.equals("L"))
				Menus.listApp(applianceList);
			if(option1.equals("f")||option1.equals("F"))
				Menus.readApp(applianceList);
			if(option1.equals("s")||option1.equals("S"))
				break;
			if(option1.equals("q")||option1.equals("Q"))
				break;
		}

		readAppFile(appTextFile, applianceList);
		ArrayList<Room> rooms = new ArrayList<Room>();
		boolean roomFound = false;
		
		for (int i = 0; i < applianceList.size(); i++) {
			Appliance currentAppliance = applianceList.get(i);

			
			for (Room r : rooms) {
				if (currentAppliance.getID() == r.getRoomID()) {
					r.addAppliance(currentAppliance);
					roomFound = true;
				}
			}
			
			if (!roomFound) {
				Room newRoom = new Room(currentAppliance.getID());
				newRoom.addAppliance(currentAppliance);
				rooms.add(newRoom);
			}
			roomFound = false;
		}
		
		/*for (int i = 0; i < timeSteps; i++) {
			//run simulation
			
			printSimDetails();
		}*/
		
		for (int i = 0; i < rooms.size(); i++) {
			System.out.println(rooms.get(i).getRoom().size());
			for (int j = 0; j < rooms.get(i).getRoom().size(); j++) {
				rooms.get(i).getRoom().get(j).printInfo();
				System.out.println();
			}
		}
		
	}
	
	public static void printSimDetails() {
		try {
			FileWriter fw = new FileWriter("SimDetails.txt");
			PrintWriter outputFile = new PrintWriter(fw);
			outputFile.println("Appliances that were affected during the simulation: ");
			outputFile.println("Locations that were affected during the simulation: ");
			outputFile.close();
			
		}  catch (Exception E) {
			System.out.println("ERROR");
		}
		
	}
}