package client;

import java.util.ArrayList;

public class TheAlgorithm {
	
	public static void run(ArrayList<Room> rooms, SmartAppliance[] SAs, int timeSteps, int maxWattage) 
	{
		Summary.startFrame();
		int totalWattage = 0;
		SmartAppliance[] tempSAs = SAs.clone();
		// jesus code
		for (int i = 0; i < timeSteps; i++) 
		{
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
					continue;
				}
				int temp = lowerHighestLowWattage(SAs);
				
				if (temp == -1) break;
				totalWattage -= temp;
			}
			while (true);
			
			// brown out rooms until we're under
			do 
			{
				int[] out = brownOutOptimalRoom(rooms, totalWattage - maxWattage);
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
	private static int lowerHighestLowWattage(SmartAppliance[] SAs) 
	{
		int max = -1;
		int maxIndex = -1;
		for (int i = 0; i < SAs.length; i++) 
		{
			if (SAs[i].getStatus() == true && SAs[i].getOnOff() && SAs[i].getLowWattage() > max) 
			{
				max = SAs[i].getLowWattage();
				maxIndex = i;
			}
		}
		
		if (maxIndex == -1) 
		{
			return 1;
		}
		else 
		{	
			SAs[maxIndex].setStatus(false);
			Summary.incNumLowered();
			return max;
		}
	}
	
	/*
	 * searches for most optimal room to brown out. optimal being the room that brings the TotalWattage under the given
	 * wattage threshold. this is compared to the difference between the two.
	 * 
	 * returns an int[] which int[0] is the total wattage of the room browned out and int[1] is the room browned out's id
	 * */
	private static int[] brownOutOptimalRoom(ArrayList<Room> rooms, int difference) 
	{
		Room optimal = rooms.get(0);
		int optimalIndex = 0;
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
