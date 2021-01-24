package com.itxm.android.writeitdown;

import com.itxm.android.writeitdown.data.DBAdapter;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;

/**
 * @author itanex
 *
 */
public class writeitdown extends ListActivity {
	DBAdapter mDbHelper;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mDbHelper = new DBAdapter(this);
        
        mDbHelper.open();
        
        fillData();
        
        mDbHelper.close();

    }
    
    private void fillData() {

        // Get all of the notes from the database and create the item list
        Cursor c = mDbHelper.fetchAllNotes();
        startManagingCursor(c);

        //String[] from = new String[] { DBAdapter.COLUMNS.get(0).Col_Name, DBAdapter.COLUMNS.get(1).Col_Name };
        String[] from = new String[] { DBAdapter.TABLELIST.get(0).Columns.get(1).Col_Name };
        //int[] to = new int[] { R.id.ListEntryID, R.id.ListEntryLabel };
        int[] to = new int[] { R.id.ListEntry };
        
        // Now create an array adapter and set it to display using our row
        try{
	        SimpleCursorAdapter notes = new SimpleCursorAdapter(this, R.layout.listentry, c, from, to);
	        setListAdapter(notes);
        }catch(Throwable exp){
        	return;
        
        }
	}
}
