package com.example.imeterprepaid;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.SubMenu;


public class Main extends SherlockActivity {
	private String[]msgArray = new String[]{"Meter No: 000014065764\nVending Account:User1\nEnergy:60\nTotal Cost:50\nPay Method: Credit Card", 
			"Electricity Token\nMeter No: 014065763644\nTokenNumber: 6308-3059-1288-7722-8962\nEnergy:60kWh\nUnit Cost: 43.86\nVAT(14.00%): 6.14",
			};

	private String[]dataArray = new String[]{"2013-09-01 18:00", "2013-09-01 18:10"}; 
	
	private final static int COUNT = 2;
	
	private ListView mListView;
	private ChatMsgViewAdapter mAdapter;
	private List<ChatMsgEntity> mDataArrays = new ArrayList<ChatMsgEntity>();
	private Context context;
	
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Used to put dark icons on light action bar
        boolean isLight = true;
        //
        SubMenu subApply = menu.addSubMenu("Apply");
		subApply.add(0, 1, 0, "Apply Pay Online");//.setIcon(R.drawable.ofm_group_chat_icon);
		subApply.add(0, 2, 0, "Application Progress");//.setIcon(R.drawable.ofm_add_icon);
		subApply.add(0, 3, 0, "Apply Offices");//.setIcon(R.drawable.ofm_video_icon);
		
		
		MenuItem itemApply = subApply.getItem();
		//itemApply.setIcon(R.drawable.abs__ic_menu_share_holo_dark);
		itemApply.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS|MenuItem.SHOW_AS_ACTION_WITH_TEXT);
		
		SubMenu subVending = menu.addSubMenu("Vend");
		subVending.add(0, 4, 0, "Paypal");//.setIcon(R.drawable.ofm_group_chat_icon);
		subVending.add(0, 5, 0, "Vending Query");//.setIcon(R.drawable.ofm_add_icon);
		
		
		MenuItem itemVending = subVending.getItem();
		//itemVending.setIcon(R.drawable.abs__ic_menu_share_holo_dark);
		itemVending.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS|MenuItem.SHOW_AS_ACTION_WITH_TEXT);
		
		SubMenu subAccount = menu.addSubMenu("Acct");
		subAccount.add(0, 6, 0, "Bind Account");//.setIcon(R.drawable.ofm_group_chat_icon);
		subAccount.add(0, 7, 0, "My Account");//.setIcon(R.drawable.ofm_add_icon);
		subAccount.add(0, 8, 0, "Query History");//.setIcon(R.drawable.ofm_video_icon);
		
		MenuItem itemAccount = subAccount.getItem();
		//itemAccount.setIcon(R.drawable.abs__ic_menu_share_holo_dark);
		itemAccount.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS|MenuItem.SHOW_AS_ACTION_WITH_TEXT);
		
		SubMenu subTools = menu.addSubMenu("Tool");
		subTools.add(0, 9, 0, "Promotion");//.setIcon(R.drawable.ofm_group_chat_icon);
		subTools.add(0, 10, 0, "Guide");//.setIcon(R.drawable.ofm_add_icon);
		subTools.add(0, 11, 0, "Service");//.setIcon(R.drawable.ofm_video_icon);
		subTools.add(0, 12, 0, "Office Nearby");//.setIcon(R.drawable.ofm_qrcode_icon);
		subTools.add(0, 13, 0, "Reminder");//.setIcon(R.drawable.actionbar_camera_icon);
		
		MenuItem itemTools = subTools.getItem();
		//itemTools.setIcon(R.drawable.abs__ic_menu_share_holo_dark);
		itemTools.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);


        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	// TODO Auto-generated method stub
    	switch(item.getItemId()) {
    	case 4:
    		startActivityForResult(new Intent(this, Paypal.class), 1);
    		break;
    	
    	case 7:
    		startActivity(new Intent(this, MyAccount.class));
    		break;
    		
    	case 8:
    		startActivity(new Intent(this, AccountHistory.class));
    		break;
    		
    	case 13:
    		startActivityForResult(new Intent(this, Reminder.class),2);
    		break;
    	
    	}
    	
    	return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	setTheme(R.style.Theme_Sherlock_Light_DarkActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        
        mListView = (ListView) findViewById(R.id.taskflow);
        initData();
        mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Dialog dialog = new AlertDialog.Builder(context)
				.setIcon(android.R.drawable.btn_star)
				.setTitle("Online Recharge")
				.setMessage("Are you sure to recharge the meter with token Code:\n" +
						"6308-3059-1288-7722-8962")
				   	.setPositiveButton("Net Send",
					     new DialogInterface.OnClickListener() {
						     @Override
						     public void onClick(DialogInterface dialog, int which) {
						    	 dialog.cancel();
						     }
					     })
					.setNegativeButton("Cancle", 
						new DialogInterface.OnClickListener() {
						    @Override
						    public void onClick(DialogInterface dialog, int which) {
						    	 dialog.cancel();
						    }
						})
					.create();
				dialog.show();
			}
			
			
		});
    }
    public void Dialog(View view) {
    	Dialog dialog = new AlertDialog.Builder(context)
		.setIcon(android.R.drawable.btn_star)
		.setTitle("Online Recharge")
		.setMessage("Are you sure to recharge the meter with token Code:\n" +
				"6308-3059-1288-7722-8962")
		   	.setPositiveButton("Net Send",
			     new DialogInterface.OnClickListener() {
				     @Override
				     public void onClick(DialogInterface dialog, int which) {
				    	 dialog.cancel();
				     }
			     })
			.setNegativeButton("Cancle", 
				new DialogInterface.OnClickListener() {
				    @Override
				    public void onClick(DialogInterface dialog, int which) {
				    	 dialog.cancel();
				    }
				})
			.create();
    	dialog.show();
    }
    
    public void initData()
    {
    	for(int i = 0; i < COUNT; i++)
    	{
    		ChatMsgEntity entity = new ChatMsgEntity();
    		entity.setDate(dataArray[i]);
    		if (i % 2 == 0)
    		{
    			entity.setName("User1");
    			entity.setMsgType(false);
    		}else{
    			entity.setName("Office");
    			entity.setMsgType(true);
    		}
    		
    		entity.setText(msgArray[i]);
    		mDataArrays.add(entity);
    	}

    	mAdapter = new ChatMsgViewAdapter(this, mDataArrays);
		mListView.setAdapter(mAdapter);
		
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	// TODO Auto-generated method stub
    	if(resultCode == Activity.RESULT_OK){ 
	    	switch (requestCode) {
	    	case 1:
	    		String contString = data.getStringExtra("request");
	    		String repString = data.getStringExtra("response");
	    		if (contString.length() > 0)
	    		{
	    			ChatMsgEntity entity = new ChatMsgEntity();
	    			entity.setDate(getDate());
	    			entity.setName("User1");
	    			entity.setMsgType(false);
	    			entity.setText(contString);
	    			
	    			mDataArrays.add(entity);
	    			
	    			entity = new ChatMsgEntity();
	    			entity.setDate(getDate());
	    			entity.setName("Office");
	    			entity.setMsgType(true);
	    			entity.setText(repString);
	    			
	    			mDataArrays.add(entity);
	    			mAdapter.notifyDataSetChanged();
	    			
	    			   			
	    			mListView.setSelection(mListView.getCount() - 1);
	    		}
	    		break;
	    	case 2:
	    		String reminderString = data.getStringExtra("reminder");
	    		if (reminderString.length() > 0) {
	    			
	    			ChatMsgEntity entity = new ChatMsgEntity();
	    			entity.setDate(getDate());
	    			entity.setName("Office");
	    			entity.setMsgType(true);
	    			entity.setText("The user account balance remind threshold has been set to:\n"
	    					+ reminderString);
	    			
	    			mDataArrays.add(entity);
	    			mAdapter.notifyDataSetChanged();
	    			
	    			   			
	    			mListView.setSelection(mListView.getCount() - 1);
	    		}
	    	}
    	}
    	
    	super.onActivityResult(requestCode, resultCode, data);
    }
    private String getDate() {
        Calendar c = Calendar.getInstance();

        String year = String.valueOf(c.get(Calendar.YEAR));
        String month = String.valueOf(c.get(Calendar.MONTH));
        String day = String.valueOf(c.get(Calendar.DAY_OF_MONTH) + 1);
        String hour = String.valueOf(c.get(Calendar.HOUR_OF_DAY));
        String mins = String.valueOf(c.get(Calendar.MINUTE));
        
        
        StringBuffer sbBuffer = new StringBuffer();
        sbBuffer.append(year + "-" + month + "-" + day + " " + hour + ":" + mins); 
        						
        						
        return sbBuffer.toString();
    }

    
}
