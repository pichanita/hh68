package nso.hh2568.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteQueryBuilder;
import android.os.Environment;
import android.util.Log;

import nso.hh2568.utils.Global;


public class DataBase  extends SQLiteAssetHelper{
    private static final String DATABASE_NAME = "hh67";
	private static final int DATABASE_VERSION = 1;
	private static SQLiteDatabase db ; 
	
	public  DataBase(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		db = getWritableDatabase();
	}
	
	public static SQLiteDatabase getDb(Context context){
		String dbPath =Environment.getExternalStorageDirectory().getAbsolutePath()+"/database/"+DATABASE_NAME;
		//HH65
		Log.e("dbPath",dbPath);
		 if (db == null) {
			   db = context.openOrCreateDatabase(dbPath, Context.MODE_PRIVATE, null);
			}
			if (!db.isOpen()) {
				db = context.openOrCreateDatabase(dbPath, Context.MODE_PRIVATE, null);
			}
		return db;
	}
	
	public static void CloseDB(){
		if(db !=null | db.isOpen())
			db.close();
	}
	
	public boolean ExecuteSQL(String value) 
	{
		try{
	    	db.execSQL(value);   
	    	return true;
		}
		catch(SQLiteException e){ 
			  return false;
		}
	}
	public Cursor Select(String[] SelectColumn,String Table,String Condition)
	{
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

		String [] sqlSelect = SelectColumn;
		String sqlTables = Table;

		qb.setTables(sqlTables);
		Cursor c = qb.query(db, sqlSelect, Condition, null, null, null, null);
			 
		 
		return c;

	}
	public Cursor Select(String[] SelectColumn,String Table,String Condition,String Sort)
	{
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

		String [] sqlSelect = SelectColumn;
		String sqlTables = Table;

		qb.setTables(sqlTables);
		Cursor c = qb.query(db, sqlSelect, Condition, null, null, null, Sort);
			 
		 
		return c;

	}
	public Cursor Select(String Statement)
	{
		 return db.rawQuery(Statement,null);
		 
	}
	public long Insert(String table,ContentValues values)
	{
		try{
			return db.insert(table, null, values);
		}
		catch(SQLiteException e)
		{
		//	 Utils.AlertText(_context, "Error,"+e.getMessage());
			 return 0;
		}
	}
	public long Update(String table,ContentValues values,String whereClause,String[] whereArgs)
	{
		try{
			return db.update(table, values, whereClause, whereArgs);
		}
		catch(Exception e)
		{
		//	 Utils.AlertText(_context, "Error,"+e.getMessage());
			 return 0;
		}
	}
	
	
	public long Update(String table,ContentValues values)
	{
		try{
			return db.update(table, values, null, null);
		}
		catch(Exception e)
		{
		//	 Utils.AlertText(_context, "Error,"+e.getMessage());
			 return 0;
		}
	}
	public long Delete(String table,String whereClause,String[] whereArgs)
	{
		try{
			return db.delete(table, whereClause, whereArgs) ;
		}
		catch(SQLiteException e)
		{
			// Utils.AlertText(_context, "Error,"+e.getMessage());
			 return 0;
		}
	}
	 
	
	 
	
	public int checkUser(String userName ,String password)//0:true 1:wrong user/password 2:connection error
	{
		try
		{
			Cursor c = this.Select(String.format("select * from TB_USERS where USER_NAME ='%s' and USER_PASSWORD ='%s'",						userName.toString(),password.toString()));
			if(c.getCount() > 0)
			{
				 while(c.moveToNext())
				 {
					 Global.getInstance().setUser_cwt(c.getInt(c.getColumnIndex("CWT")));
					 Global.getInstance().setUser_type(c.getInt(c.getColumnIndex("USER_TYPE")));
					 Global.getInstance().setUser_cwtname(c.getString(c.getColumnIndex("CWT_NAME")));

				 }

				return 0;

			}
			else{
					return 1;
			}

		}
		catch(Exception e)
		{
			return 2;
		}
	}

	
}
