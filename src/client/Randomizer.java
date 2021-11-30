package client;

import java.util.Random;

/* TODO: document this
 * */

public class Randomizer {
	
	/* This function returns whether an appliance should be turned on
	 * or off based on it's 
	 * 
	 * */
	public static boolean randomOnOff(double chance) 
	{
		if (chance <= 0.0d) return false;
		if (chance >= 1.0d) return true;
		
		Random r = new Random();
		double randNum = r.nextDouble();
		
		if (randNum <= chance) 
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
}


