package com.example.imeterprepaid;

import com.actionbarsherlock.app.SherlockActivity;

import android.os.Bundle;
import android.view.View;

public class MyAccount extends SherlockActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myaccount);
		
	}
	
	public void Confirm(View view) {
		finish();
	}
}
