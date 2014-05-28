package com.ahrgahh.timon;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends ActionBarActivity {
	private static final int SETTINGS_RESULT = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final Button send = (Button) this.findViewById(R.id.send);
		send.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {


				final GMailSender sender = new GMailSender("timon@ahrgahh.com", "timon!14");

				new AsyncTask<Void, Void, Void>() {

					@Override
					protected void onPreExecute()
					{
					}

					@Override
					protected Void doInBackground(Void... params)
					{
						try {

							sender.sendMail("This is Subject",   
									"This is Body",   
									"TIMON",   
									"linus.bjork@gmail.com");
						} catch (Exception e) {
							Log.e("SendMail", e.getMessage(), e);   
						} 
						return null;
					}

					@Override
					protected void onPostExecute(Void res)
					{
					}
				}.execute();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {

			Intent i = new Intent(getApplicationContext(), UserSettings.class);

			startActivityForResult(i, SETTINGS_RESULT);
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

	public void verifyPhoneNumber(View view) {

		EditText phoneEditText = (EditText) findViewById(R.id.phonenumber);
		String number = phoneEditText.getText().toString(); 

		if( number != null ) {
			new MissatSamtal().verifyPhoneNumber(view, number);
		}
	}
	
	
}
