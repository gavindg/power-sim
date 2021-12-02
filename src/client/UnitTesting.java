package client;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class UnitTesting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scnr = new Scanner(System.in);
		ArrayList<Appliance> appList = new ArrayList<Appliance>();
		
		String option1, appTextFile;
		System.out.println("Enter text file to read: ");
		appTextFile = scnr.nextLine();
		readAppFile(appTextFile, appList);
		
		/*Iterator<Appliance> it = appList.iterator();
	       int count = 0;
	       System.out.println("Index\tRoom/Name/OnWattage/ProbabilityOn/LowRatio/LowWattage");
	       while (it.hasNext()) 
	       {
		       count++;
		       System.out.print(count+"\t");
		       it.next().printInfo();
		       System.out.println();
	       }*/
		
		while(true){// Application menu to be displayed to the user.
			System.out.println("------------------------");
			System.out.println("Select an option:");
			System.out.println("Type \"A\" Add an appliance");
			System.out.println("Type \"D\" Delete an appliance");	
			System.out.println("Type \"L\" List the appliances");
			System.out.println("Type \"F\" Read Appliances from a file");
			System.out.println("Type \"S\" To Start the simulation");
			System.out.println("Type \"Q\" Quit the program");
			option1=scnr.nextLine();
			/* Complete the skeleton code below */	
			if(option1.equals("a")||option1.equals("A"))
				Menus.addApp(appList);
			if(option1.equals("d")||option1.equals("D"))
				Menus.removeApp(appList);
			if(option1.equals("l")||option1.equals("L"))
				Menus.listApp(appList);
			if(option1.equals("f")||option1.equals("F"))
				Menus.readApp(appList);
			if(option1.equals("s")||option1.equals("S"))
				break;
			if(option1.equals("q")||option1.equals("Q"))
				break;
		}
		
		ArrayList<Room> rooms = new ArrayList<Room>();
		boolean roomFound = false;
		
		for (int i = 0; i < appList.size(); i++) {
			Appliance currentAppliance = appList.get(i);
			
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
		
		
		
		for (int i = 0; i < rooms.size(); i++) {
			System.out.println(rooms.get(i).getRoom().size());
			for (int j = 0; j < rooms.get(i).getRoom().size(); j++) {
				rooms.get(i).getRoom().get(j).printInfo();
				System.out.println();
			}
		}

	}
	
	
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

}
