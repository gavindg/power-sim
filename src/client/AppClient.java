/* This is a stub code. You can modify it as you wish. */
package client;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
class AppClient{
	
	public void readAppFile(String file){ // method to read the comma seperated appliance file.
		ArrayList<Room> totalRooms = new ArrayList<Room>(); 
		int i = 0;
		
		try {
			File myFile = new File("app.txt");
			Scanner scan = new Scanner(myFile);//each line has the format
			//locationID,name of app,onPower,probability of staying on, smart or not,Smart appliances (if "on") power reduction percent when changed to "low" status(floating point, i.e..33=33%).
			
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
					app = new Appliance(locationID, appName, onPower, probOn, 0);
				}
				else {
					app = new SmartAppliance(locationID, appName, onPower, probOn, lowRatio, 0);
				}
				
				Room temp = new Room(i);

			}
			
			
			scan.close();
			
		} catch(IOException ioe){ 
			System.out.println("The file can not be read");
		}
		
	}
	
	
	public static void main(String []args){
		
		AppClient app = new AppClient();
		//User interactive part
		String option1, option2;
		Scanner scan = new Scanner(System.in);
		while(true){// Application menu to be displayed to the user.
			System.out.println("Select an option:");
			System.out.println("Type \"A\" Add an appliance");
			System.out.println("Type \"D\" Delete an appliance");	
			System.out.println("Type \"L\" List the appliances");
			System.out.println("Type \"F\" Read Appliances from a file");
			System.out.println("Type \"S\" To Start the simulation");
			System.out.println("Type \"Q\" Quit the program");
			option1=scan.nextLine();
			/* Complete the skeleton code below */
		
				
		}
		
		
	}
}