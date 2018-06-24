package com.example.admin.scanner;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DataBaseControl
{

    private static final String DATABASE_NAME = "recycle.db";
    private static final int DATABASE_VERSION = 1;
    public static SQLiteDatabase mDB;
    private DatabaseHelper mDBHelper;
    private Context mCtx;

    private class DatabaseHelper extends SQLiteOpenHelper
    {


        public DatabaseHelper(Context context, String name,
                              SQLiteDatabase.CursorFactory factory, int version)
        {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            db.execSQL(DataBase.CreateDB.CREATE);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            db.execSQL("DROP TABLE IF EXISTS "+DataBase.CreateDB.TABLENAME);
            onCreate(db);
        }
    }

    public DataBaseControl(Context context)
    {
        this.mCtx = context;
    }

    public DataBaseControl open() throws SQLException
    {
        mDBHelper = new DatabaseHelper(mCtx, DATABASE_NAME, null, DATABASE_VERSION);
        mDB = mDBHelper.getWritableDatabase();
        return this;
    }

    public void close()
    {
        mDB.close();
    }

}
