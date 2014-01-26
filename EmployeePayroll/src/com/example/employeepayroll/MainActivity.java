package com.example.employeepayroll;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	EditText employeeName;
	EditText salesAmount;
	EditText hoursWorked;
	TextView bonus;
	
	Sales[] array = new Sales[5];
	int counter = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.employeeName = (EditText)findViewById(R.id.employeeNameET);
		this.salesAmount = (EditText)findViewById(R.id.salesAmountET);
		this.hoursWorked = (EditText)findViewById(R.id.workedHoursET);
		this.bonus = (TextView)findViewById(R.id.bonus);
	}
	
	public void onClickResetBtn(View view) {
		this.employeeName.setText(null);
		this.salesAmount.setText(null);
		this.hoursWorked.setText(null);
		this.employeeName.requestFocus();
		
	} 

	Calculation calculate = new Calculation();
	public void onClickComputeBtn(View view){
		
		String anEmployeeName = employeeName.getText().toString();
		double aSalesAmount = Double.parseDouble(salesAmount.getText().toString());
		double anHoursWorked = Double.parseDouble(this.hoursWorked.getText().toString());
		
		Sales salesInfo = new Sales(anEmployeeName, aSalesAmount, anHoursWorked);
		calculate.computeSalaryInfo(salesInfo);
		array[counter] = salesInfo;
		counter++;
		
		String bonusStr = String.valueOf(salesInfo.getBonusPercentage()).toString();
		String overtime = String.valueOf(salesInfo.getOvertimeAmount()).toString();
		String basicAmnt = String.valueOf(salesInfo.getBasicAmount()).toString();
		String salaryAmnt = String.valueOf(salesInfo.getSalaryAmount()).toString();
		bonus.setText(salaryAmnt);
	}
}
