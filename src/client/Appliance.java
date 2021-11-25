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
	
	
	/* This function gets the wattage of an appliance, randomly generating if it is on or off. */
	public int getWattage() {
		this.generateWattage();
		return currentWattage;
	}
	
	/* TODO: document this
	 * */
	private void generateWattage() 
	{
		boolean on = Randomizer.randomOnOff(this.getProbOn());
		if (on) 
		{
			this.setWattage(this.getOnWattage());
		}
		else 
		{
			this.setWattage(this.getOffWattage());
		}
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
	
	private void setWattage(int cw) 
	{
		this.currentWattage = cw;
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
