package com.example.imeterprepaid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.actionbarsherlock.app.SherlockActivity;

public class AccountHistory extends SherlockActivity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.accounthistory);
		
		
	}
	
	public void Confirm(View view) {
		
		finish();
	}

}
