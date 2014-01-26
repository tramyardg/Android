package com.example.employeepayroll;

public class Calculation {

	/**
	 * this function should be in the top of the other methods
	 * @param salesInfo
	 */
	public void computeSalaryInfo(Sales salesInfo) {
		
		double bonusPercent = this.computeBonusPercent(salesInfo.getSalesAmount());
		salesInfo.setBonusPercentage(bonusPercent);
		
		double overtimeAmount = this.computeOvertimeAmount(salesInfo.getHoursWorked());
		salesInfo.setOvertimeAmount(overtimeAmount);
		
		double basicAmnt = this.computeBasicSalaryAmnt(salesInfo.getSalesAmount());
		salesInfo.setBasicAmount(basicAmnt);

		double bonusSalaryAmount = this.computeBonusSalaryAmt(salesInfo.getSalesAmount());
		salesInfo.setBonusAmount(bonusSalaryAmount);
		
		double normalHourWork = this.regularHourWorkAmount(salesInfo.getHoursWorked()); 
		

		double salaryAmount;
		if (salesInfo.getSalesAmount() <= Interface.SALES_AMOUT_300) {
			salaryAmount = basicAmnt + overtimeAmount + bonusSalaryAmount + normalHourWork;
		} else { //if you have more than 300$,  you have fixed salary
			salaryAmount = basicAmnt + overtimeAmount + bonusSalaryAmount;
		}
		salesInfo.setSalaryAmount(salaryAmount);
		

	}
	public double regularHourWorkAmount(double hrsWorked) {
		double normalHourAmount = 0.0;
		if(hrsWorked > Interface.REGULAR_HOURS_WORK) {  //ex : 45 hours, so salary will be 40 * $10
			normalHourAmount = Interface.REGULAR_HOURS_WORK * Interface.HOURLY_RATE; //  40 hour *  $10
		} else {
			normalHourAmount = hrsWorked * Interface.HOURLY_RATE; // 5 * $10
		}
		return normalHourAmount;
	}	
	//calculate salary amount
	//formula:
	//bonus salary amount = total sales amount * bonus salary percent
	//salary amount = basic salary amount + bonus salary amount

/*
 * 	Sales amount			[0, 300]		]300, 1000]			]1000, 5000]		]5000, 10000]
	Basic salary amount			0				400					600					900
	Bonus salary percent		0%				10%					5%					1%
 * ----------------------------------------------------------------------------------------------
 * 	Weekly payroll 
	Name	SalesAmnt	WorkedHours   OvertimeAmnt 		BasicAmnt 	Bonus% 	SalaryAmnt
	Kim	  	200.00		45					75.00			0.00		0		475.00
	Tom		1000.00		40		   			0.00	        400.00   	10		500.00
	Ted		2000.00		35		   			0.00	        600.00     	5		700.00

	
 */

//prompt the user for name, sales amount, worked hours
//then calculate he overtime amount, basic amount, then salary amount


	public double computeOvertimeAmount(double hoursWorked){
		if(hoursWorked > Interface.REGULAR_HOURS_WORK) {
			return (hoursWorked - Interface.REGULAR_HOURS_WORK) * Interface.OVERTIME_RATE * Interface.HOURLY_RATE;
		} else {
			return Interface.NO_OVERTIME;
		}
	}
	
	public double computeBonusSalaryAmt(double salesAmount){
		if(salesAmount <= Interface.SALES_AMOUT_300) {
			return Interface.BONUS_SALARY_PERCENT_300 * salesAmount;
		}else if(salesAmount <= Interface.SALES_AMOUT_1000 ) {
			return Interface.BONUS_SALARY_PERCENT_1000 * salesAmount;
		}else if(salesAmount <= Interface.SALES_AMOUT_5000) {
			return Interface.BONUS_SALARY_PERCENT_5000 * salesAmount;
		}else {
			return Interface.BONUS_SALARY_PERCENT_10000 * salesAmount;
		} 
		
	}
	
	public double computeBonusPercent(double salesAmount) {
		if(salesAmount <= Interface.SALES_AMOUT_300) {
			return Interface.BONUS_SALARY_PERCENT_300 * 100;
		}else if(salesAmount <= Interface.SALES_AMOUT_1000 ) {
			return Interface.BONUS_SALARY_PERCENT_1000 * 100;
		}else if(salesAmount <= Interface.SALES_AMOUT_5000) {
			return Interface.BONUS_SALARY_PERCENT_5000 * 100;
		}else {
			return Interface.BONUS_SALARY_PERCENT_10000 * 100;
		} 
	}
	
	public double computeBasicSalaryAmnt(double salesAmount) {
		if(salesAmount <= Interface.SALES_AMOUT_300) {
			return Interface.BASIC_SALARY_AMOUNT_0;
		}else if(salesAmount <= Interface.SALES_AMOUT_1000 ) {
			return Interface.BASIC_SALARY_AMOUNT_400;
		}else if(salesAmount <= Interface.SALES_AMOUT_5000) {
			return Interface.BASIC_SALARY_AMOUNT_600;
		}else {
			return Interface.BASIC_SALARY_AMOUNT_900;
		}
		
	}
}