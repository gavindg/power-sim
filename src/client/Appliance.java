package client;

public class Appliance {
	protected String name;
	protected int onWattage;
	protected int offWattage;
	protected double probOn;
	protected int ID;
	protected int currentWattage;
	
	// default constructor
	Appliance() 
	{
		setName("empty");
		setOnWattage(0);
		setOffWattage(0);
		setProbOn(0);
		setID(0);
		currentWattage = 0;
	}
	
	// non-default constructor
	Appliance(int ID, String name, int onWattage, double probOn, int offWattage)  
	{
		setName(name);
		setOnWattage(onWattage);
		setOffWattage(offWattage);
		setProbOn(probOn);
		setID(ID);
		currentWattage = onWattage;
	}
	
	// accessors
	public String getName() 
	{
		return this.name;
	}
	
	public int getOnWattage() 
	{
		return this.onWattage;
	}
	
	public int getOffWattage() 
	{
		return this.offWattage;
	}
	
	public double getProbOn() 
	{
		return this.probOn;
	}

	public int getID() 
	{ 
		return this.ID;
	}
	
	public int getWattage() {
		return currentWattage;
	}
	
	// mutators
	public void setName(String name) 
	{
		this.name = name;
	}
	
	public void setOnWattage(int onWattage) 
	{
		this.onWattage = onWattage;
	}
	
	public void setOffWattage(int offWattage) 
	{
		this.offWattage = offWattage;
	}
	
	
	public void setProbOn(double probOn) 
	{
		this.probOn = probOn;
	}
	
	public void setID(int ID) 
	{
		this.ID = ID;
	}
	
	// switchers
	
	public void turnOn() {
		currentWattage = getOnWattage();
	}
	
	public void turnOff() {
		currentWattage = getOffWattage();
	}
}
