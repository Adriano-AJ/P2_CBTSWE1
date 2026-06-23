package model;

public class SalesMan {
	
	//Attributes
	
	private int salesManId;
	private String name;
	private String city;
	private double commission;
	
	//Constructors
	
	public SalesMan() {
		
	}
	
	public SalesMan(int salesManId, String name, String city, double commission) {
		this.salesManId = salesManId;
		this.name = name;
		this.city = city;
		this.commission = commission;
	}
	
	// Getters and Setters

	public int getSalesMan() {
		return salesManId;
	}
	
	public String getName() {
		return name;
	}
	public String getCity() {
		return city;
	}
	public double getCommssion() {
		return commission;
	}
	
	@Override
	
	public String toString() {
        return "SalesMan{" +
               "salesmanId="   + salesManId  +
               ", name='"      + name        + '\'' +
               ", city='"      + city        + '\'' +
               ", commission=" + commission  +
               '}';
    }


}
