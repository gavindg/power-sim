package client;
import java.util.*;

public class Room {
	private ArrayList<Appliance> room = new ArrayList<>();
	int roomID;
	boolean isFullyOptimized = false;
	
	public Room() {
		roomID = 0;
	}
	
	public Room(int ID) {
		roomID = ID;
	}
	

	public int getRoomID() {
		return roomID;
	}
	
	public int getTotalWattage() {

		Iterator<Appliance> it = room.iterator();
		int total = 0;
		while (it.hasNext()) 
		{
			total += it.next().getWattage();
		}
		return total;
	}
	

	public void optimize() {
		Iterator<Appliance> it = room.iterator();
		while(it.hasNext()) {
			Appliance ap = it.next();
			if(ap instanceof SmartAppliance) {
				((SmartAppliance) ap).changeToLow();
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
	
	public void addAppliance(Appliance app) {
		room.add(app);
	}
	
}

