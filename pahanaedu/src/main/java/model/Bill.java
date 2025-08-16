package model;

import java.sql.Timestamp;

public class Bill {
    private int id;
    private String accountNumber;
    private String customerName;
    private String booksPurchased;
    private double totalAmount;
    private Timestamp billingTime;
    private String staffUsername;

    // Getters and setters

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getBooksPurchased() { return booksPurchased; }
    public void setBooksPurchased(String booksPurchased) { this.booksPurchased = booksPurchased; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public Timestamp getBillingTime() { return billingTime; }
    public void setBillingTime(Timestamp billingTime) { this.billingTime = billingTime; }
    
    public String getStaffUsername() {
        return staffUsername;
    }

    public void setStaffUsername(String staffUsername) {
        this.staffUsername = staffUsername;
    }
}
