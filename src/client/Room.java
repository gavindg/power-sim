package client;
import java.util.*;

public class Room {
	Set<Appliance> room = new HashSet<>();
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
		while(it.hasNext()) {
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
}

