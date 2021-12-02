package client;

import java.util.ArrayList;

public class TheAlgorithm {
	
	public static void run(ArrayList<Room> rooms, ArrayList<SmartAppliance> SAs, int timeSteps, int maxWattage) 
	{
		
		int totalWattage = 0;
		ArrayList<SmartAppliance> tempSAs = new ArrayList<SmartAppliance>();
		tempSAs = (ArrayList)SAs.clone();
		
		// jesus code
		for (int i = 0; i < timeSteps; i++) 
		{
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
				System.out.printf("[DEBUG]: reducing totalWattage by %d for a total Wattage of %d\n", temp, totalWattage);
				totalWattage -= temp;
			}
			while (true);
			
			if (flag == true) continue;
			
			// brown out rooms until we're under
			do 
			{
				int[] out = brownOutOptimalRoom(rooms, totalWattage - maxWattage);
				if (out[0] == -1) 
				{
					System.out.println("Error: No more rooms to brown out, further optimization impossible");
					break;
				}
				System.out.printf("Browned out room %d\n", out[1]);
				
				totalWattage -= out[0];
				if (totalWattage <= maxWattage) 
				{
					Summary.outputFrameReport();
					continue;
				} 
				
			}
			while (totalWattage - maxWattage > 0);
			
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
			// TODO: remove this: System.out.printf("[DEBUG]: testing sa. getStatus = %b, getOnOff = %b, getLowWattage = %d", SAs.get(i).getStatus(), SAs.get(i).getOnOff(), SAs.get(i).getLowWattage());
			if (SAs.get(i).getStatus() == true && SAs.get(i).getOnOff() && SAs.get(i).getLowWattage() > max) 
			{
				max = SAs.get(i).getLowWattage();
				maxIndex = i;
			}
		}
		
		//System.out.printf("[DEBUG]: maxIndex = %d\n", maxIndex);
		if (maxIndex == -1) 
		{
			return -1;
		}
		else 
		{	
			int ret = SAs.get(maxIndex).getOnWattage() - max;
			SAs.get(maxIndex).setStatus(false);
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
		int optimalIndex = -1;
		for (int i = 0; i < rooms.size(); i++) 
		{
			if (!rooms.get(i).brownedOut) 
			{
				optimal = rooms.get(i);
				optimalIndex = i;
				break;
			}
		}
		
		if (optimalIndex == -1) 
		{
			return new int[] {-1, -1};
		}
		
		
		int numAppliances = rooms.get(0).getNumAppliances();
		for (int i = 0; i < rooms.size(); i++) 
		{
			// first check: does it go under?
			if(rooms.get(i).getTotalWattage()>difference) {
			// second check: 
				if (rooms.get(i).getNumAppliances()>numAppliances) // if rooms[i] is closer to totalWattage, but only if it uses less appliances, unless it goes under.
				{
					optimalIndex = i;
					optimal = rooms.get(i);
					numAppliances = optimal.getNumAppliances();
				}
			}
		}
		int[] ans = {optimal.getTotalWattage() , optimal.getRoomID()};
		Summary.incNumRoomsOut();
		optimal.incNumTimesBrownedOut();
		Summary.compareMostEffected(optimal);
		
		return ans;
	}
}
