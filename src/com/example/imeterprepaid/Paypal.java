package com.example.imeterprepaid;

import java.text.DecimalFormat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import com.actionbarsherlock.app.SherlockActivity;

public class Paypal extends SherlockActivity{
	
	String mRechargeAmount;
	String mMeterNumber;
	EditText textMeterNumber;
	EditText textRechargeAmount;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order);
		
	}
	
	public void GotoPaypal(View view){
		if(null!=(EditText)findViewById(R.id.meter_number)) {
			textMeterNumber = (EditText)findViewById(R.id.meter_number);
			mMeterNumber = textMeterNumber.getText().toString();
		}
		if(null!=(EditText)findViewById(R.id.meter_number)) {
			textRechargeAmount = (EditText)findViewById(R.id.recharge_amount);
			mRechargeAmount = textRechargeAmount.getText().toString();
		}
		
		if ((!"".equals(mRechargeAmount))&&(!"".equals(mMeterNumber))) {
			setContentView(R.layout.paypal);
			TextView txMeterNumber = (TextView)findViewById(R.id.title_meter_number);
			txMeterNumber.setText(mMeterNumber);
			
			TextView txRechargeAmount = (TextView)findViewById(R.id.title_recharge_amount);
			txRechargeAmount.setText(mRechargeAmount);
		}
	}
	
	public void PaySucceed(View view){
		
		DecimalFormat format = new DecimalFormat("#.00");
		
		float energycost = (float) (Integer.parseInt(mRechargeAmount)/(1.14));
		
		String contString = "Meter No: " +
				mMeterNumber +
				"\nVending Account:User1\nEnergy:" +
				(Integer.parseInt(mRechargeAmount))*1.2 +
				"\nTotal Cost:" +
				mRechargeAmount +
				"\nPay Method: Credit Card"; 
				
		String repString = "Electricity Token\nMeter No: " +
				mMeterNumber +
				"\nTokenNumber: 6308-3059-1288-7722-8962\nEnergy:" +
				(Integer.parseInt(mRechargeAmount))*1.2 +
				"kWh\nUnit Cost: " +
				format.format(energycost)+
				"\nVAT(14.00%): " +
				format.format(Integer.parseInt(mRechargeAmount)-energycost);
		
		Intent resultIntent = new Intent();  
        Bundle bundle = new Bundle();  
        bundle.putString("request", contString);  
        bundle.putString("response", repString); 
        
        resultIntent.putExtras(bundle);  
        this.setResult(RESULT_OK, resultIntent); 
       
        Paypal.this.finish();
	
	}
}
