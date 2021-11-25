package client;
import java.util.*;

public class Room {
	Set<Appliance> room = new HashSet<>();
	int roomID;
	
	public Room() {
		roomID = 0;
	}
	
	public Room(int ID) {
		roomID = ID;
	}
	
	/* This function sums the wattage of a room, randomly picking some appliances to be on 
	 * and some to be off. */
	public int getTotalWattage() 
	{
		Iterator<Appliance> it = room.iterator();
		int total = 0;
		while (it.hasNext()) 
		{
			total += it.next().getWattage();
		}
		return total;
	}
	
	/* This function sums the total wattage of a room, assuming that all appliances are off. 
	 * This will be used for "browning out" a room. */
	public int getTotalWattageOff() 
	{
		Iterator<Appliance> it = room.iterator();
		int total = 0;
		while (it.hasNext()) 
		{
			total += it.next().getOffWattage();
		} 
		return total;
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
}
