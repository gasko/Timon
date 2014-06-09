package com.ahrgahh.timon;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class CallReceiver extends BroadcastReceiver {
	
	@Override
    public void onReceive(final Context context, Intent intent) {
		final CallsDataSource datasource = new CallsDataSource(context);
		
        TelephonyManager telephony = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        telephony.listen(new PhoneStateListener(){
            @Override
            public void onCallStateChanged(int state, String incomingNumber) {
                super.onCallStateChanged(state, incomingNumber);
                if(state == TelephonyManager.CALL_STATE_RINGING){
                	Call call = MissatSamtal.verifyPhoneNumber(incomingNumber);
                	
                	//missat samtal
//                	if(incomingNumber != null ){
//                		new MissatSamtal().verifyPhoneNumber(view, number);
//                	}
                	
                	String bookname = Utils.getContactDisplayNameByNumber(incomingNumber, context);
                	
                	final GMailSender sender = new GMailSender(SecretConstants.EMAIL_ADDRESS, SecretConstants.EMAIL_PASSWORD);
                	try {
						sender.sendMail("[TIMON] Missed call",   
								"Missat samtal från: \n"
								+ " telefonnummer: " + incomingNumber + "\n"
										+ "Adressbok: " + bookname + "\n"
												+ "missatSamtal : " + call.name + "\n",
								"TIMON",   
								"linus.bjork@gmail.com");
					} catch (Exception e) {
						Log.e("SendMail", e.getMessage(), e);   
					}
                	
                	Toast.makeText(context, "incomingNumber : "+call.number + " " + call.name, Toast.LENGTH_SHORT).show();
                	
                	
                }
                //System.out.println("incomingNumber : "+incomingNumber);
            }
        },PhoneStateListener.LISTEN_CALL_STATE);
    }


}
