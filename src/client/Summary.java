package client;



public class Summary {
	
	/* LINES 9-43 HANDLE SINGLE FRAME INFORMATION */
	
	private static int frame = 0;
	
	private static int numLowered = 0;
	private static int numLocationsBrownedOut = 0;
	
	public static void startFrame() 
	{
		frame++;
		numLowered = 0;
		numLocationsBrownedOut = 0;
	}
	
	public static void incNumLowered() 
	{
		numLowered++;
	}
	public static void incNumRoomsOut() 
	{
		numLocationsBrownedOut++;
	}
	public static void outputFrameReport() 
	{
		System.out.printf("Time Step %d\n", frame);
		System.out.printf("Number of Smart Appliances switched to low power mode: %d\n", numLowered);
		System.out.printf("Number of Locations browned out: %d\n\n", numLocationsBrownedOut);
	}
	
	/* LINES 47-X HANDLE INFORMATION UPDATED THROUGHOUT THE PROGRAM'S EXECUTION */
	
	/* this array holds two integers: the ID of the most effected room and the number of times it has been effected.
	 * */
	private static int[] currentMostEffectedRoom = { -1, 0 };
	
	public static void compareMostEffected(Room r) 
	{
		if (r.numTimesBrownedOut > currentMostEffectedRoom[1]) 
		{
			currentMostEffectedRoom[0] = r.roomID;
		}
	}
	
	public static void output() 
	{
		System.out.printf("Room %d effected the greatest number of times with %d brown-outs.", currentMostEffectedRoom[0], currentMostEffectedRoom[1]);
	}
	
	/* TODO: A detailed report to a text file containing the appliances/locations were effected during each interval. */
	
}
