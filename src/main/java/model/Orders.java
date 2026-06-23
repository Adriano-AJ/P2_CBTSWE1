package model;

import java.util.Date;

public class Orders {

	// Attributes
    private int ordNo;
    private double purchAmt;
    private Date ordDate;
    private int customerId;
    private int salesmanId;
 
    // Constructors
 
    public Orders() {}
 
    public Orders(int ordNo, double purchAmt, Date ordDate, int customerId, int salesmanId) {
        this.ordNo      = ordNo;
        this.purchAmt   = purchAmt;
        this.ordDate    = ordDate;
        this.customerId = customerId;
        this.salesmanId = salesmanId;
    }
 
    // Getters and Setters 
 
    public int getOrdNo() {
        return ordNo;
    }
 
    public void setOrdNo(int ordNo) {
        this.ordNo = ordNo;
    }
 
    public double getPurchAmt() {
        return purchAmt;
    }
 
    public void setPurchAmt(double purchAmt) {
        this.purchAmt = purchAmt;
    }
 
    public Date getOrdDate() {
        return ordDate;
    }
 
    public void setOrdDate(Date ordDate) {
        this.ordDate = ordDate;
    }
 
    public int getCustomerId() {
        return customerId;
    }
 
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
 
    public int getSalesmanId() {
        return salesmanId;
    }
 
    public void setSalesmanId(int salesmanId) {
        this.salesmanId = salesmanId;
    }
 
    @Override
    public String toString() {
        return "Orders{" +
               "ordNo="      + ordNo      +
               ", purchAmt=" + purchAmt   +
               ", ordDate="  + ordDate    +
               ", customerId=" + customerId +
               ", salesmanId=" + salesmanId +
               '}';
    }
}
