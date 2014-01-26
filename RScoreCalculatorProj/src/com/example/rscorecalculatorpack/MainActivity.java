package com.example.rscorecalculatorpack;

import java.text.DecimalFormat;



import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
/**
 * 
 * @author Raymart
 * The purpose of this program is to calculate student rscore.
 * source: http://en.wikipedia.org/wiki/R_score
 * The R score (cote de rendement au collégial, CRC or cote R in French) 
 * is a statistical method which classifies college students' academic performances 
 * in Quebec. It is used by Quebec universities for selection purposes. The R score is 
 * in fact a z-score to which an indicator of group strength (ISG) has been added.
 * Adding the constant C (C = 5) eliminates negative values in the sum of Z and ISG. 
 * If the sum of these three terms is further multiplied by D (D = 5), the product 
 * becomes a quantity between 0 and 50. Most R scores will fall between 15 and 35.  
 * 
 * R score = ( Z score + ISG + C ) x D
 * 1. Calculating the  Z score:
 *  	z score = student grade - class average / standard deviation
 *  
 * 2. Calculating the strength of the group.
 *  	isg = high school class average - 75 / 14
 *  
 * 3. The constraints C and D
 * 		R score = ( Z score + ISG + 5 ) x 5
 * 
 */
public class MainActivity extends Activity {

	private static final double CONSTRAINT_C = 5;
	private static final double CONSTRAINT_D = 5;
	
	EditText yourMark;
	EditText classAverage;
	EditText standardDev;
	EditText highSchoolAvg;
	EditText yourRScore;
	
	TextView yourMarkTV;
	TextView classAverageTV;
	TextView standardDevTV;
	TextView highSchoolAvgTV;
	TextView yourRScoreTV;
	TextView RscoreTV;
	
	Button clearBtn;
	Button exitBtn;
	Button computeBtn; 
	
	private String yourMarkStr;
	private int yourMarkInt;
	
	private String classAverageStr;
	private int classAverageInt;
	
	private String standardDevStr;
	private double standardDevDouble;
	
	private String highSchoolAvgStr;
	private int highSchoolAvgInt;
	
	private double zscore;
	private int groupStrength;
	private double rscore;
	
	private final int MAXIMUM_GRADE  = 100;
	private String orangeColor = "#F2AC13";
	private String greenColor = "#147A00";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.activity_main);
		yourMark = (EditText)findViewById(R.id.yourMarkET);
		classAverage = (EditText)findViewById(R.id.classAverageET);
		standardDev = (EditText)findViewById(R.id.standardDevET);
		highSchoolAvg = (EditText)findViewById(R.id.highSchoolAvgET);
		yourRScore = (EditText)findViewById(R.id.yourRScoreET);
		
		computeBtn = (Button)findViewById(R.id.calculateBtn);
		clearBtn = (Button)findViewById(R.id.clearBtn);
		exitBtn = (Button)findViewById(R.id.exitBtn);

		yourMarkTV = (TextView)findViewById(R.id.yourMarkTV);
		classAverageTV = (TextView)findViewById(R.id.classAverageTV);
		standardDevTV = (TextView)findViewById(R.id.standardDevTV);
		highSchoolAvgTV = (TextView)findViewById(R.id.highSchoolAvgTV);
		yourRScoreTV = (TextView)findViewById(R.id.yourRScoreTV);
		RscoreTV = (TextView)findViewById(R.id.RscoreTV);
		
		yourMark.addTextChangedListener(mTextWatcher);
		classAverage.addTextChangedListener(mTextWatcher);
		standardDev.addTextChangedListener(mTextWatcher);
		highSchoolAvg.addTextChangedListener(mTextWatcher);
		checkFieldsForEmptyValues();
		
		

	}
	/**
	 * This method will validate the mark, class average, standard deviation
	 * and high school average not exceeding 100.
	 * @param yourMarkInt
	 * @param classAverageInt
	 * @param standardDevDouble
	 * @param highSchoolAvgInt
	 */
	public void calculate(int yourMarkInt, int classAverageInt, 
							double standardDevDouble, int highSchoolAvgInt) {
		if(yourMarkInt > MAXIMUM_GRADE) {
			yourMark.requestFocus();
		} else if(classAverageInt > MAXIMUM_GRADE) {
			classAverage.requestFocus();
		} else if(standardDevDouble > MAXIMUM_GRADE) {
			standardDev.requestFocus();
		} else if(highSchoolAvgInt > MAXIMUM_GRADE) {
			highSchoolAvg.requestFocus();
		} else {
			zscore = (yourMarkInt - classAverageInt) / (standardDevDouble);
			groupStrength = ((highSchoolAvgInt - 75) / 14);
			rscore = (zscore + groupStrength + CONSTRAINT_C) * CONSTRAINT_D;
			if(rscore <= 20) {
				yourRScore.setTextColor(Color.RED);
			} else if(rscore > 20 && rscore <= 25) {
				yourRScore.setTextColor(Color.parseColor(orangeColor)); 
			} else {
				yourRScore.setTextColor(Color.parseColor(greenColor)); 
			}
			DecimalFormat df = new DecimalFormat("0.00");
			String result = df.format(rscore);
			yourRScore.setText(result);
		}
	}
	
	/**
	 * When you clicked compute button, first it will validate 
	 * everything then it will output the results if all the input 
	 * is valid.
	 * @param view
	 */
	@SuppressLint("NewApi")
	public void onClickComputeBtn(View view) {
		yourMarkStr = yourMark.getText().toString();
		yourMarkInt = Integer.parseInt(yourMarkStr);
		
		classAverageStr = classAverage.getText().toString();
		classAverageInt = Integer.parseInt(classAverageStr);
		
		standardDevStr = standardDev.getText().toString();
		standardDevDouble = Double.parseDouble(standardDevStr);
		
		highSchoolAvgStr = highSchoolAvg.getText().toString();
		highSchoolAvgInt = Integer.parseInt(highSchoolAvgStr);

		calculate(yourMarkInt, classAverageInt, standardDevDouble, highSchoolAvgInt);

	}
	
	/**
	 * This will clear all the fields when the button clear is clicked.
	 * @param view 
	 */
	public void onClickClearBtn(View view) {
		yourMark.setText(null);
		classAverage.setText(null);	
		standardDev.setText(null); 
		yourRScore.setText(null);
		highSchoolAvg.setText(null);
		yourMark.requestFocus();
	}

	/**
	 * This will exit the application and return home.
	 * @param view
	 */
	public void onClickExitBtn(View view) {
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
		finish();
	}

	private TextWatcher mTextWatcher = new TextWatcher() {
	    @Override
		public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
	    }
	    @Override
		public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
	    }
	    @Override
		public void afterTextChanged(Editable editable) {
	    	checkFieldsForEmptyValues();
			try {

				int number = Integer.parseInt(editable.toString());
				if (number > 100) {
					editable.replace(0, editable.length(), "");
					Toast.makeText(getApplicationContext(),
							"Please enter a valid mark between 1-100.",
							Toast.LENGTH_LONG).show();
				}
			} catch (NumberFormatException e) {
			}
		}
	};
	 
	@SuppressLint("NewApi")
	void checkFieldsForEmptyValues(){
		yourMarkStr = yourMark.getText().toString();
		classAverageStr = classAverage.getText().toString();
		standardDevStr = standardDev.getText().toString();
		highSchoolAvgStr= highSchoolAvg.getText().toString();
		
	    if(yourMarkStr.isEmpty() || classAverageStr.isEmpty() || highSchoolAvgStr.isEmpty() || standardDevStr.isEmpty()){
	    	computeBtn.setEnabled(false);
	    } else {computeBtn.setEnabled(true);}
	}
	public void onClickEnButton(View view) {
		yourMarkTV.setText(R.string.YourMarkTxt);
		classAverageTV.setText(R.string.ClassAverageTxt);	
		standardDevTV.setText(R.string.StandardDevTxt); 
		highSchoolAvgTV.setText(R.string.HighSchoolAvgTxt);
		yourRScoreTV.setText(R.string.YourRScoreTxt);
		computeBtn.setText(R.string.CalculateTxt);
		clearBtn.setText(R.string.ClearTxt);
		exitBtn.setText(R.string.ExitTxt);
		RscoreTV.setText(R.string.RscoreTV);
		
	}
	public void onClickFrButton(View view) {
		yourMarkTV.setText("Note");
		classAverageTV.setText("Moyenne");	
		standardDevTV.setText("Ecart Type"); 
		yourRScoreTV.setText("Votre Cote R");
		highSchoolAvgTV.setText("Moyenne Secondaire de la Classe");
		computeBtn.setText("Calculer");
		clearBtn.setText("Effacer");
		exitBtn.setText("Sortie");
		RscoreTV.setText("Calculatrice Cote R");
	}
}
