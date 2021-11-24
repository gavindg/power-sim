package client;

public class SmartAppliance extends Appliance{
	
	private int lowW;
	
	public SmartAppliance() {
		super();
	}
	
	public SmartAppliance(String name, int onWattage, int offWattage, double probOn, int location, int ID, int lowWattage){
		super(name, onWattage, offWattage, probOn, location, ID);
		lowW = lowWattage;
	}
	public void setLowWattage(int w) {
		lowW = w;
	}
	
	public int getLowWattage() {
		return lowW;
	}
	
}
