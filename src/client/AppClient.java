/* This is a stub code. You can modify it as you wish. */
package client;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
class AppClient{
	
	public void readAppFile(String file){ // method to read the comma seperated appliance file.

		Scanner scan;

		ArrayList<Room> totalRooms = new ArrayList<Room>(); 
		totalRooms.add(new Room(10000001));
		int i = 1;
		

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
				
				if (i != (locationID - 10000000)) {
					i++;
					totalRooms.add(new Room(i + 10000000));
				}
				
				Appliance app;
				
				if (isSmart.equals("false")) {
					app = new Appliance(locationID, appName, onPower, probOn);
				}
				else {
					app = new SmartAppliance(locationID, appName, onPower, probOn, lowRatio);
				}
				
				totalRooms.get(i).addAppliance(app);

			}
			
			scan.close();
		}catch(IOException ioe){ 
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
			if(option1.equals("a")||option1.equals("A"))
				Menus.addApp();
			if(option1.equals("d")||option1.equals("D"))
				Menus.removeApp();
			if(option1.equals("l")||option1.equals("L"))
				Menus.listApp();
			if(option1.equals("f")||option1.equals("F"))
				Menus.readApp();
			if(option1.equals("s")||option1.equals("S"))
				break;
			if(option1.equals("q")||option1.equals("Q"))
				break;
		}
		
		
	}
}