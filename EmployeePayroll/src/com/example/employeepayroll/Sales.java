package com.example.employeepayroll;

public class Sales {
	public Sales(String employeeName, double salesAmount, double hoursWorked) {
		this.employeeName = employeeName;
		this.salesAmount = salesAmount;
		this.hoursWorked = hoursWorked;
	}
	public double getSalesAmount() {
		return salesAmount;
	}
	public void setSalesAmount(double salesAmount) {
		this.salesAmount = salesAmount;
	}
	public double getHoursWorked() {
		return hoursWorked;
	}
	public void setHoursWorked(double hoursWorked) {
		this.hoursWorked = hoursWorked;
	}
	public double getOvertimeAmount() {
		return overtimeAmount;
	}
	public void setOvertimeAmount(double overtimeAmount) {
		this.overtimeAmount = overtimeAmount;
	}
	public double getBasicAmount() {
		return basicAmount;
	}
	public void setBasicAmount(double basicAmount) {
		this.basicAmount = basicAmount;
	}
	public double getBonusPercentage() {
		return bonusPercentage;
	}
	public void setBonusPercentage(double bonusPercentage) {
		this.bonusPercentage = bonusPercentage;
	}
	public double getBonusAmount() {
		return bonusAmount;
	}
	public void setBonusAmount(double bonusAmount) {
		this.bonusAmount = bonusAmount;
	}
	public double getSalaryAmount() {
		return salaryAmount;
	}
	public void setSalaryAmount(double salaryAmount) {
		this.salaryAmount = salaryAmount;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	private String employeeName;
	private double salesAmount;
	private double hoursWorked;
	private double overtimeAmount;
	private double basicAmount;
	private double bonusPercentage;
	private double bonusAmount;
	private double salaryAmount;
}
