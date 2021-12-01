package client;
import java.util.*;

public class Room {

	private ArrayList<Appliance> room = new ArrayList<Appliance>();

	int roomID;
	boolean isFullyOptimized = false;
	boolean brownedOut = false;
	int totalWattage;
	int numTimesBrownedOut;
	
	public Room() {
		roomID = 0;
	}
	
	public Room(int ID) {
		roomID = ID;
	}
	

	public int getRoomID() {
		return roomID;
	}
	
	public int getNumTimesBrownedOut() {
		return this.numTimesBrownedOut;
	}
	
	public void incNumTimesBrownedOut() 
	{
		this.numTimesBrownedOut++;
	}
	
	public int brownOut() 
	{
		brownedOut = true;
		return this.getTotalWattage();
	}
	
	public int getTotalWattage() {

		Iterator<Appliance> it = room.iterator();
		int total = 0;
		while (it.hasNext()) 
		{
			total += it.next().getWattage();
		}
		totalWattage = total;
		return total;
	}
	
	public int randomizeWattage() 
	{
		Iterator<Appliance> it = room.iterator();
		int total = 0;
		while (it.hasNext()) 
		{
			Appliance ap = it.next();
			
			// if `ap` is a smart device, turn it on high
			if (ap instanceof SmartAppliance) 
			{
				((SmartAppliance) ap).setStatus(true);
			}
			ap.generateWattage();
			total += ap.getWattage();
		}
		totalWattage = total;
		return total;
	}
	

	public void optimize() {
		Iterator<Appliance> it = room.iterator();
		while(it.hasNext()) {
			Appliance ap = it.next();
			if(ap instanceof SmartAppliance) {
				((SmartAppliance) ap).setStatus(false);
			}
		}
	}
	
	/* This function sums the total wattage of a room assuming that all appliances are on. 
	 * If I'm honest, I have no idea if this will actually be useful, but I'll keep it just
	 * in case.*/
	public int getTotalWattageOn() {
		Iterator<Appliance> it = room.iterator();
		int total = 0;
		while(it.hasNext()) {
			total += it.next().getOnWattage();
		}
		return total;
	}
	
	public int getNumAppliances() {
        return room.size();
    }
	
	public void addAppliance(Appliance app) {
		room.add(app);
	}
	
}

