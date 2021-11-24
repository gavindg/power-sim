package client;

public class SmartAppliance extends Appliance{
	
	private int lowW;
	
	public void setLowWattage(int w) {
		lowW = w;
	}
	
	public int getLowWattage() {
		return lowW;
	}
	
}
