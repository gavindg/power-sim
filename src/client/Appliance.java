package client;

public class Appliance {
	protected String name;
	protected int onWattage;
	protected int offWattage;
	protected double probOn;
	protected int location;
	protected int ID;
	
	// default constructor
	Appliance() 
	{
		setName("empty");
		setOnWattage(0);
		setOffWattage(0);
		setProbOn(0);
		setLocation(0);
		setID(0);
	}
	
	// non-default constructor
	Appliance(String name, int onWattage, int offWattage, double probOn, int location, int ID)  
	{
		setName(name);
		setOnWattage(onWattage);
		setOffWattage(offWattage);
		setProbOn(probOn);
		setLocation(location);
		setID(ID);
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
	
	public int getLocation() 
	{
		return this.location;
	}
	
	public int getID() 
	{ 
		return this.ID;
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
	
	public void setLocation(int location) 
	{
		this.location = location;
	}
	
	public void setID(int ID) 
	{
		this.ID = ID;
	}
	
	
}
