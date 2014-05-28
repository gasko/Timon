package com.ahrgahh.timon;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.os.AsyncTask;
import android.view.View;

public class MissatSamtal {

	public final static String action = "search";
	public final static String format = "xml";
	public final static String numberOfCompanies = "1";
	public final static String apiUrl = "http://api.missatsamtal.se/?";
	
	public MissatSamtal() {
		super();
		// TODO Auto-generated constructor stub
	}

	private class numberVerificationResult {
		public String statusNbr;
		public String hygieneResult; 
	}

	private numberVerificationResult parseXML( XmlPullParser parser ) throws XmlPullParserException, IOException {

		int eventType = parser.getEventType();
		numberVerificationResult result = new numberVerificationResult();

		while( eventType!= XmlPullParser.END_DOCUMENT) {
			String name = null;

			switch(eventType)
			{
			case XmlPullParser.START_TAG:
				name = parser.getName();

				if( name.equals("Error")) {
					System.out.println("Web API Error!");
				}
				else if ( name.equals("name")) {
					result.statusNbr = parser.nextText();
				}
				else if (name.equals("number")) {
					result.hygieneResult = parser.nextText();
				}

				break;

			case XmlPullParser.END_TAG:
				break;
			} // end switch

			eventType = parser.next();
		} // end while

		return result;       
	}

	private class CallAPI extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... params) {
			String urlString=params[0]; // URL to call
			String resultToDisplay = "";
			numberVerificationResult result = null;
			InputStream in = null;

			// HTTP Get
			try {		
				URL url = new URL(urlString);
				HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
				in = new BufferedInputStream(urlConnection.getInputStream());
			} catch (Exception e ) {
				System.out.println(e.getMessage());
				return e.getMessage();
			}    

			// Parse XML
			XmlPullParserFactory pullParserFactory;

			try {
				pullParserFactory = XmlPullParserFactory.newInstance();
				XmlPullParser parser = pullParserFactory.newPullParser();

				parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
				parser.setInput(in, null);
				result = parseXML(parser);
			} catch (XmlPullParserException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (result.hygieneResult != null && result.statusNbr != null){
				resultToDisplay = result.statusNbr + " " + result.hygieneResult;
			} else if (result.hygieneResult != null ) {
				resultToDisplay = result.hygieneResult;
			} else if (result.statusNbr != null) {
				resultToDisplay = result.statusNbr;
			} else
				resultToDisplay ="faan0";

			return resultToDisplay;      
		}

		protected void onPostExecute(String result) {
			//TextView resultText = (TextView) findViewById(R.id.textView1);
			//resultText.setText(result);
		}

	} // end CallAPI 
	
	public void verifyPhoneNumber(View view, String number) {
		if( number != null ) {
			
			String urlString = apiUrl + "action=" + action + "&format=" + format + "&number=" + number + "&numberOfCompanies=" + numberOfCompanies;

			new CallAPI().execute(urlString); 
		}
	}
	
}
