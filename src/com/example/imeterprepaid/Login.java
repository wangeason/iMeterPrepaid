package com.example.imeterprepaid;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.EditText;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

public class Login extends SherlockActivity implements OnSharedPreferenceChangeListener {
	
	private EditText mUser; // 帐号编辑框
	private EditText mPassword; // 密码编辑框
	private CheckBox mSavePassword;
	SharedPreferences prefs;
	SharedPreferences.Editor mprefsEditor;
	
	 public void login(View v) {
		 getSupportActionBar().show();
	    	if("".equals(mUser.getText().toString()) || "".equals(mPassword.getText().toString()))   //判断 帐号和密码
	        {
	    		// 为了测试方便暂时不进行密码验证
	        	new AlertDialog.Builder(Login.this)
				.setIcon(getResources().getDrawable(R.drawable.login_error_icon))
				.setTitle("Login Error")
				.setMessage("username or password shall not be null\npls login again.")
				.create().show();
	    		//this.finish();
	        } else if(verify(mUser,mPassword)) {
	        	saveUserPassword();
	        	Intent intent = new Intent();
				intent.setClass(Login.this,Main.class);
				startActivity(intent);
				//this.finish();
	        } else {
	        	new AlertDialog.Builder(Login.this)
				.setIcon(getResources().getDrawable(R.drawable.login_error_icon))
				.setTitle("Login Failed")
				.setMessage("username or password is not right，\npls login again.")
				.create().show();
	        }
	    }
		
		private void saveUserPassword() {
			// TODO Auto-generated method stub
			if (mSavePassword.isChecked()) {
				mprefsEditor.putBoolean("savepassword", mSavePassword.isChecked()?true:false);
				mprefsEditor.putString("username",mUser.getText().toString());
				mprefsEditor.putString("password",mPassword.getText().toString()).commit();
			} else {
				mprefsEditor.putBoolean("savepassword", false);
				mprefsEditor.putString("username",mUser.getText().toString());
				mprefsEditor.putString("password","").commit();
			}
		}
		private boolean verify(EditText mUser2, EditText mPassword2) {
			// TODO Auto-generated method stub
			// 网络鉴权放在这里
			if (mUser.getText().toString().equals(mPassword.getText().toString())) {
				return true;
			}
			return false;
		}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//Set the Theme
		setTheme(R.style.Theme_Sherlock_Light_DarkActionBar);
		
		//You could also use 
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
						
		mUser = (EditText)findViewById(R.id.login_user_edit);
        mPassword = (EditText)findViewById(R.id.login_passwd_edit);
        mSavePassword = (CheckBox)findViewById(R.id.save_passwd);
		
		prefs= PreferenceManager.getDefaultSharedPreferences(this);
        prefs.registerOnSharedPreferenceChangeListener(this);
        
        mprefsEditor = prefs.edit();
		
		mSavePassword.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (((CheckBox)v).isChecked()) {
					((CheckBox)v).setChecked(true);
				} else {
					((CheckBox)v).setChecked(false);
					mPassword.setText("");
				}
			}
		});
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		/*
		menu.add(0,1,0,"Server")
        .setIcon(R.drawable.ofm_setting_icon)
        .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_WITH_TEXT);*/ 
		return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case 1:
			Intent intent = new Intent(this,Preference.class);
			intent.putExtra("CALL_FROM", "Login");
			startActivity(intent);
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		//显示用户名等登陆信息
		mSavePassword.setChecked(prefs.getBoolean("savepassword", false));        
        mUser.setText(prefs.getString("username", ""));
        
        if (mSavePassword.isChecked()) {
        	mPassword.setText(prefs.getString("password", ""));
        }
	}
	
	@Override
    protected void onDestroy() {
    	// TODO Auto-generated method stub
    	super.onDestroy();
    	prefs.unregisterOnSharedPreferenceChangeListener(this);
    }

	@Override
	public void onSharedPreferenceChanged(SharedPreferences arg0, String arg1) {
		// TODO Auto-generated method stub
		super.onResume();
	}

}
