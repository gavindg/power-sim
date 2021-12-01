package client;

public class SmartAppliance extends Appliance{
	
	private int lowW;
	private double lowRatio;
	
	public SmartAppliance() {
		super();
	}
	
	public SmartAppliance(int ID, String name, int onWattage, double probOn, double lowRatio){
		super(ID, name, onWattage, probOn);
		setLowRatio(lowRatio);
		
		int temp = calculateLowWattage();
		setLowWattage(temp);
	}
	
	public void setLowWattage(int w) {
		this.lowW = w;
	}
	
	public void setLowRatio(double w) 
	{
		this.lowRatio = w;
	}
	
	public int getLowWattage() {
		return this.lowW;
	}
	
	public double getLowRatio() 
	{
		return this.lowRatio;
	}
	
	public void changeToLow() {
		currentWattage = getLowWattage();
	}
	
	public void changeToHigh() {
		currentWattage = getOnWattage();
	}
	
	private int calculateLowWattage() 
	{
		return (int)(this.onWattage * (1 - this.lowRatio));
	}
	
	public void turnOn() {
		System.out.println("use changeToLow() or changeToHigh() for smart appliances");
	}
	
	public void printInfo() {
		super.printInfo();
		System.out.println("Low Ratio: " + this.lowRatio);
		System.out.println("Low Wattage: " + this.getLowWattage());
		
	}
	
}
