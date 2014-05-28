package com.ahrgahh.timon;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SMSReceiver extends BroadcastReceiver {

	//http://droidcoders.blogspot.in/2011/09/sms-receive.html
	@Override
	public void onReceive(Context context, Intent intent){
		Bundle bundle = intent.getExtras();        
		SmsMessage[] msgs = null;
		String messageReceived = "";            
		if (bundle != null){
			//---retrieve the SMS message received---
			Object[] pdus = (Object[]) bundle.get("pdus");
			msgs = new SmsMessage[pdus.length];            
			for (int i=0; i<msgs.length; i++){
				msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);                
				messageReceived += msgs[i].getMessageBody().toString();
			      
			}
			//---display the new SMS message---
			Toast.makeText(context, messageReceived, Toast.LENGTH_SHORT).show();
			Toast.makeText(context, msgs[0].getOriginatingAddress(), Toast.LENGTH_SHORT).show(); 
		}
	}


}
