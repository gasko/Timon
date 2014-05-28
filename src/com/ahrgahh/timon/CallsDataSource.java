package com.ahrgahh.timon;

import java.util.ArrayList;
import java.util.List;

import com.ahrgahh.timon.db.DatabaseContract;
import com.ahrgahh.timon.db.DatabaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class CallsDataSource {
	// Database fields
	  private SQLiteDatabase database;
	  private DatabaseHelper dbHelper;
	  private String[] allColumns = { DatabaseContract.CallTable.SENDER,
			  DatabaseContract.CallTable.SENDER_NAME,
			  DatabaseContract.CallTable.TIMESTAMP_RECEIVED,
			  DatabaseContract.CallTable.BLOCKED};

	  public CallsDataSource(Context context) {
	    dbHelper = new DatabaseHelper(context);
	  }

	  public void open() throws SQLException {
	    database = dbHelper.getWritableDatabase();
	  }

	  public void close() {
	    dbHelper.close();
	  }

	  public Call createCall(String sender) {
	    ContentValues values = new ContentValues();
	    values.put(DatabaseContract.CallTable.SENDER, sender);
	    long insertId = database.insert(DatabaseContract.CallTable.TABLE_NAME, null,
	        values);
	    Cursor cursor = database.query(DatabaseContract.CallTable.TABLE_NAME,
	        allColumns, DatabaseContract.CallTable._ID + " = " + insertId, null,
	        null, null, null);
	    cursor.moveToFirst();
	    Call newCall = cursorToCall(cursor);
	    cursor.close();
	    return newCall;
	  }

	  public void deleteCall(Call call) {
	    long id = call.getId();
	    System.out.println("Comment deleted with id: " + id);
	    database.delete(DatabaseContract.CallTable.TABLE_NAME, DatabaseContract.CallTable._ID
	        + " = " + id, null);
	  }

	  public List<Call> getAllComments() {
	    List<Call> calls = new ArrayList<Call>();

	    Cursor cursor = database.query(DatabaseContract.CallTable.TABLE_NAME,
	        allColumns, null, null, null, null, null);

	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	      Call call = cursorToCall(cursor);
	      calls.add(call);
	      cursor.moveToNext();
	    }
	    // make sure to close the cursor
	    cursor.close();
	    return calls;
	  }

	  private Call cursorToCall(Cursor cursor) {
	    Call comment = new Call();
	    comment.setId(cursor.getLong(0));
	    comment.setComment(cursor.getString(1));
	    return comment;
	  }
}
