package com.example.imeterprepaid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;

import com.actionbarsherlock.app.SherlockActivity;

public class Reminder extends SherlockActivity{
	
	SharedPreferences prefs;
	SharedPreferences.Editor mprefsEditor;
	EditText edReminder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reminder);
		prefs= PreferenceManager.getDefaultSharedPreferences(this);
		mprefsEditor = prefs.edit();
		
		edReminder = (EditText)findViewById(R.id.reminder);
		
		edReminder.setText(prefs.getInt("Reminder", 20)+"");
		
	}
	
	public void Confirm(View view) {
		mprefsEditor.putInt("Reminder", Integer.parseInt(edReminder.getText().toString())).commit();
		Intent reminderIntent = new Intent();
		Bundle bundle = new Bundle();  
        bundle.putString("reminder", edReminder.getText().toString());
        
        reminderIntent.putExtras(bundle);  
        this.setResult(RESULT_OK, reminderIntent);
		finish();
	}
}