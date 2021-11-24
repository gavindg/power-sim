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
	
	public int getTotalWattage() {
		Iterator<Appliance> it = room.iterator();
		int total = 0;
		while(it.hasNext()) {
			total += it.next().getWattage();
		}
		return total;
	}
}
