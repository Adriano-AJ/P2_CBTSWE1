package model;

public class Customer {
	 	
		// Attributes
	
		private int customerId;
	    private String custName;
	    private String city;
	    private int grade;
	    private int salesmanId;
	 
	    // Constructors 
	    
	    public Customer() {}
	 
	    public Customer(int customerId, String custName, String city, int grade, int salesmanId) {
	        this.customerId = customerId;
	        this.custName   = custName;
	        this.city       = city;
	        this.grade      = grade;
	        this.salesmanId = salesmanId;
	    }
	 
	    // Getters and Setters 
	 
	    public int getCustomerId() {
	        return customerId;
	    }
	 
	    public void setCustomerId(int customerId) {
	        this.customerId = customerId;
	    }
	 
	    public String getCustName() {
	        return custName;
	    }
	 
	    public void setCustName(String custName) {
	        this.custName = custName;
	    }
	 
	    public String getCity() {
	        return city;
	    }
	 
	    public void setCity(String city) {
	        this.city = city;
	    }
	 
	    public int getGrade() {
	        return grade;
	    }
	 
	    public void setGrade(int grade) {
	        this.grade = grade;
	    }
	 
	    public int getSalesmanId() {
	        return salesmanId;
	    }
	 
	    public void setSalesmanId(int salesmanId) {
	        this.salesmanId = salesmanId;
	    }
	
	    @Override
	    public String toString() {
	        return "Customer{" +
	               "customerId=" + customerId  +
	               ", custName='" + custName   + '\'' +
	               ", city='"     + city       + '\'' +
	               ", grade="     + grade      +
	               ", salesmanId=" + salesmanId +
	               '}';
	    }
}
