package client;

public class SmartAppliance extends Appliance{
	
	private int lowW;
	private double lowRatio;
	private boolean high;
	private boolean on;
	
	public SmartAppliance() {
		super();
	}
	
	public SmartAppliance(int ID, String name, int onWattage, double probOn, double lowRatio){
		super(ID, name, onWattage, probOn);
		setLowRatio(lowRatio);
		setStatus(true);
		setOnOff(true);
		
		int temp = calculateLowWattage();
		setLowWattage(temp);
	}
	
	@Override
	public int getWattage() 
	{
		if (!on) 
		{
			return 0;
		}
		else if (high) 
		{
			return this.getOnWattage();
		}
		else {
			return this.getLowWattage();
		}
	}
	
	@Override
	public void generateWattage() 
	{
		boolean onOff = Randomizer.randomOnOff(this.getProbOn());
		if (onOff) 
		{
			this.setWattage(this.getOnWattage());
			this.on = true;
		}
		else 
		{
			this.setWattage(0);
			this.on = false;
		}
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
	
	// returns whether the SA is on high.
	public boolean getStatus() 
	{
		return high;
	}
	
	public void setStatus(boolean high) 
	{
		this.high = high;
	}
	
	// returns whether the SA is on or off
	public boolean getOnOff() 
	{
		return on;
	}
	
	public void setOnOff(boolean onOff) 
	{
		this.on = onOff;
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
	   System.out.print(" "+this.lowRatio +" "+ this.getLowWattage());
	}
	
}
