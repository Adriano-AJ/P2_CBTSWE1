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

	public int getSalesManId() {
		return salesManId;
	}
	
	public void setSalesManId(int salesManId) {
		this.salesManId = salesManId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public double getCommission() {
		return commission;
	}
	
	public void setCommission(double commission) {
		this.commission = commission;
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
