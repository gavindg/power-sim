package client;

public class SmartAppliance extends Appliance{
	
	private int lowW;
	
	public SmartAppliance() {
		super();
	}
	
	public SmartAppliance(String name, int onWattage, int offWattage, double probOn, int ID, int lowWattage){
		super(name, onWattage, offWattage, probOn, ID);
		setLowWattage(lowWattage);
	}
	
	public void setLowWattage(int w) {
		lowW = w;
	}
	
	public int getLowWattage() {
		return lowW;
	}
	
	public void changeToLow() {
		currentWattage = getLowWattage();
	}
	
	public void changeToHigh() {
		currentWattage = getOnWattage();
	}
	
	public void turnOn() {
		System.out.println("use changeToLow() or changeToHigh() for smart appliances");
	}
	
}
