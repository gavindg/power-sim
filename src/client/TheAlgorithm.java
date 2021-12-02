package client;

import java.util.ArrayList;

public class TheAlgorithm {
	public static String report = "",report2 = "";
	
	public static void run(ArrayList<Room> rooms, ArrayList<SmartAppliance> SAs, int timeSteps, int maxWattage) 
	{
		
		ArrayList<SmartAppliance> tempSAs = new ArrayList<SmartAppliance>();
		tempSAs = (ArrayList)SAs.clone();
		
		// jesus code
		for (int i = 0; i < timeSteps; i++) 
		{
			report = "";
			report2 = "";
			int totalWattage = 0;
			Summary.startFrame();
			boolean flag = false;
			// first find the total wattage of the whole building
			for (int j = 0; j < rooms.size(); j++) 
			{
				totalWattage += rooms.get(j).randomizeWattage();
			}
			
			// check if there are any smart devices to turn on low
			do 
			{
				// if we're under the max. watt., no need to continue w/ this time step
				if (totalWattage <= maxWattage) 
				{
					
					Summary.outputFrameReport();
					System.out.println("final wattage for this frame: " + totalWattage + "\n");
					flag = true;
					break;
				}
				int temp = lowerHighestLowWattage(SAs);
				
				if (temp == -1) break;
				totalWattage -= temp;
			}
			while (true);
			
			if (flag == true) {
				AppClient.printSimDetails(i, report, report2);
				continue;
			}
			
			// brown out rooms until we're under
			do 
			{
				int[] out = brownOutOptimalRoom(rooms, totalWattage - maxWattage);
				if (out[0] == -1) 
				{
					System.out.println("Error: No more rooms to brown out, further optimization impossible");
					break;
				}
				
				totalWattage -= out[0];
				
				if (totalWattage <= maxWattage) 
				{
					Summary.outputFrameReport();
					
					for (Room r : rooms) 
					{
						r.brownOut(false);
					}
					AppClient.printSimDetails(i, report, report2);
					break;
				} 
				
			}
			while (totalWattage - maxWattage > 0);
			
			for (Room r : rooms) 
			{
				r.brownOut(false);
			}
			AppClient.printSimDetails(i, report, report2);
		}
	}
	
	/*
	 * return codes:
	 * - positive int: lowered the max SA and returned
	 * - 1: no remaining SAs to lower
	 * */
	private static int lowerHighestLowWattage(ArrayList<SmartAppliance> SAs) 
	{
		int max = -1;
		int maxIndex = -1;
		for (int i = 0; i < SAs.size(); i++) 
		{
			if (SAs.get(i).getStatus() == true && SAs.get(i).getOnOff() && SAs.get(i).getLowWattage() > max) 
			{
				max = SAs.get(i).getLowWattage();
				maxIndex = i;
			}
		}
		
		if (maxIndex == -1) 
		{
			return -1;
		}
		else 
		{	
			int ret = SAs.get(maxIndex).getOnWattage() - max;
			SAs.get(maxIndex).setStatus(false);
			report += SAs.get(maxIndex).getInfo() +" was lowered\n";
			Summary.incNumLowered();
			return ret;
		}
	}
	
	/*
	 * searches for most optimal room to brown out. optimal being the room that brings the TotalWattage under the given
	 * wattage threshold. this is compared to the difference between the two.
	 * 
	 * returns an int[] which int[0] is the total wattage of the room browned out and int[1] is the room browned out's id
	 * 
	 * returns { -1, -1 } when no possible rooms remain to be browned out.
	 * */
	private static int[] brownOutOptimalRoom(ArrayList<Room> rooms, int difference) 
	{
		Room optimal = null;
		for (int i = 0; i < rooms.size(); i++) 
		{
			if (!rooms.get(i).brownedOut) 
			{
				optimal = rooms.get(i);
				break;
			}
		}
		
		if (optimal == null) 
		{
			return new int[] {-1, -1};
		}
		
		
		int numAppliances = rooms.get(0).getNumAppliances();
		for (int i = 0; i < rooms.size(); i++) 
		{
			// if rooms.get(i) is already browned out; continue;
			if (rooms.get(i).brownedOut) continue;
			
			// if current optimal goes under but rooms.get(i) doesnt: continue
			if (optimal.getTotalWattage() > difference && rooms.get(i).getTotalWattage() < difference) continue;
	
			// if rooms.get(0) does go under but current optimal doesnt; replace current w rooms.get(i); continue;
			if (rooms.get(i).getTotalWattage() > difference && optimal.getTotalWattage() < difference) 
			{
				optimal = rooms.get(i);
				continue;
			}
			
			// if both go under
			if (optimal.getTotalWattage() > difference && rooms.get(i).getTotalWattage() > difference) 
			{
				// check which has less appliances
				if (optimal.getNumAppliances() > rooms.get(i).getNumAppliances()) 
				{
					optimal = rooms.get(i);
					continue;
				}
			}
				
			
			// if neither go under
			if (optimal.getTotalWattage() < difference && rooms.get(i).getTotalWattage() < difference) 
			{
				// check which gets closer to going under
				if (optimal.getTotalWattage() < rooms.get(i).getTotalWattage()) 
				{
					optimal = rooms.get(i);
				}
			}
		}
		int[] ans = {optimal.getTotalWattage() , optimal.getRoomID()};
		Summary.incNumRoomsOut();
		optimal.incNumTimesBrownedOut();
		optimal.brownOut(true);
		report2 += optimal.getInfo()+" was browned out\n";
		report += optimal.getApp();
		Summary.compareMostEffected(optimal);
		
		return ans;
	}
}
