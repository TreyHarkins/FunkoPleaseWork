package com.example.funko;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

public class FunkoPops extends ContentProvider {

    public final static String DBNAME = "NameDatabase";

    protected static final class MainDatabaseHelper extends SQLiteOpenHelper {
        MainDatabaseHelper(Context context) {
            super(context, DBNAME, null, 1);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_MAIN);
        }
        @Override
        public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        }
    };


    public final static String TABLE_FUNKO = "Funko";
    public final static String COLUMN_POPNAME = "popname";
    public final static String COLUMN_POPTYPE = "poptype";
    public final static String COLUMN_POPNUM = "popnum";
    public final static String COLUMN_POPFANDOM = "popfandom";
    public final static String COLUMN_POPON = "popon";
    public final static String COLUMN_POPULTIMATE = "popultimate";
    public final static String COLUMN_POPPRICE= "poppricee";



    public static final String AUTHORITY = "com.mobile.provide";
    public static final Uri CONTENT_URI = Uri.parse(
            "content://" + AUTHORITY +"/" + TABLE_FUNKO);

    private static UriMatcher sUriMatcher;

    private MainDatabaseHelper mOpenHelper;

    private static final String SQL_CREATE_MAIN = "CREATE TABLE " +
            TABLE_FUNKO +  // Table's name
            "(" +               // The columns in the table
            " _ID INTEGER PRIMARY KEY, " +
            COLUMN_POPNAME +
            " TEXT," +
            COLUMN_POPTYPE +
            " TEXT," + COLUMN_POPFANDOM + " TEXT,"
            + COLUMN_POPNUM +" TEXT," +
            COLUMN_POPON +" TEXT,"
            + COLUMN_POPPRICE +" TEXT,"
            + COLUMN_POPULTIMATE + " TEXT)";


    public FunkoPops() {

    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return mOpenHelper.getWritableDatabase().delete(TABLE_FUNKO, selection, selectionArgs);
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        String fPopName = values.getAsString(COLUMN_POPNAME).trim();
        String fPopType = values.getAsString(COLUMN_POPTYPE).trim();
        String fPopNum = values.getAsString(COLUMN_POPNUM).trim();
        String fPopUlt = values.getAsString(COLUMN_POPULTIMATE).trim();
        String fPopPrice = values.getAsString(COLUMN_POPPRICE).trim();

        String fPopFandom = values.getAsString(COLUMN_POPFANDOM).trim();


        if (fPopName.equals(""))
            return null;

        if (fPopType.equals(""))
            return null;
        if (fPopNum.equals(""))
            return null;
        if (fPopUlt.equals(""))
            return null;
        if (fPopPrice.equals(""))
            return null;

        if (fPopFandom.equals(""))
            return null;
        long id = mOpenHelper.getWritableDatabase().insert(TABLE_FUNKO, null, values);

        return Uri.withAppendedPath(CONTENT_URI, "" + id);
    }

    @Override
    public boolean onCreate() {
        mOpenHelper = new MainDatabaseHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        return mOpenHelper.getReadableDatabase().query(TABLE_FUNKO, projection, selection, selectionArgs,
                null, null, sortOrder);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        return mOpenHelper.getWritableDatabase().update(TABLE_FUNKO, values, selection, selectionArgs);
    }
}