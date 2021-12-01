package client;

public class TheAlgorithm {
	
	public static void run(Room[] rooms, SmartAppliance[] SAs, int timeSteps, int maxWattage) 
	{
		int totalWattage = 0;
		SmartAppliance[] tempSAs = SAs.clone();
		// jesus code
		for (int i = 0; i < timeSteps; i++) 
		{
			// first find the total wattage of the whole building
			for (int j = 0; j < rooms.length; j++) 
			{
				totalWattage += rooms[j].getTotalWattage();
			}
			
			// check if there are any smart devices to turn on low
			do 
			{
				// if we're under the max. watt., no need to continue w/ this time step
				if (totalWattage <= maxWattage) continue;
				int temp = lowerHighestLowWattage(SAs);
				
				if (temp == -1) break;
				totalWattage -= temp;
			}
			while (true);
			
			// brown out rooms until we're under
			
			
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
			return max;
		}
	}
	
	/*
	 * */
	private static int brownOutOptimalRoom(Room[] rooms, int totalWattage) 
	{
		Room optimal;
		int optimalIndex = 0;
		for (int i = 0; i < rooms.length; i++) 
		{
			// first check: does it go under?
			// second check: 
			if () // if rooms[i] is closer to totalWattage, but only if it uses less appliances, unless it goes under.
			{
				optimalIndex = i;
				optimal = rooms[i];
			}
		}
		return -1;
	}
}
