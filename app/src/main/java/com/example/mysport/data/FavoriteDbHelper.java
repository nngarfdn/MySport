package com.example.mysport.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Movie;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.mysport.model.Aktivitas;

import java.util.ArrayList;
import java.util.List;

public class FavoriteDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "favorite.db";

    private static final int DATABASE_VERSION = 1;

    public static final String LOGTAG = "FAVORITE";

    SQLiteOpenHelper dbhandler;
    SQLiteDatabase db;

    public FavoriteDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

        public void open(){
        Log.i(LOGTAG, "Database Opened");
        db = dbhandler.getWritableDatabase();
    }

    public void close(){
        Log.i(LOGTAG, "Database Closed");
        dbhandler.close();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_FAVORITE_TABLE = "CREATE TABLE " + FavoriteContract.FavoriteEntry.TABLE_NAME + " (" +
                FavoriteContract.FavoriteEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                FavoriteContract.FavoriteEntry.COLUMN_AKTIVITASID + " TEXT, " +
                FavoriteContract.FavoriteEntry.COLUMN_NAME + " TEXT , " +
                FavoriteContract.FavoriteEntry.COLUMN_DESCRIPTION + " TEXT, " +
                FavoriteContract.FavoriteEntry.COLUMN_PHOTO + " TEXT, " +
                FavoriteContract.FavoriteEntry.COLUMN_COLOR + " INTEGER, " +
                FavoriteContract.FavoriteEntry.COLUMN_FAVSTATUS + " TEXT" +
                "); ";

        sqLiteDatabase.execSQL(SQL_CREATE_FAVORITE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public void addFavorite(Aktivitas aktivitas){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FavoriteContract.FavoriteEntry.COLUMN_AKTIVITASID, aktivitas.getKey_id());
        values.put(FavoriteContract.FavoriteEntry.COLUMN_NAME, aktivitas.getName());
        values.put(FavoriteContract.FavoriteEntry.COLUMN_DESCRIPTION, aktivitas.getDetail());
        values.put(FavoriteContract.FavoriteEntry.COLUMN_PHOTO, aktivitas.getPhoto());
        values.put(FavoriteContract.FavoriteEntry.COLUMN_COLOR, aktivitas.getColor());

        db.insert(FavoriteContract.FavoriteEntry.TABLE_NAME, null, values);

        db.close();
    }

    public void deleteFavorite(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(FavoriteContract.FavoriteEntry.TABLE_NAME, FavoriteContract.FavoriteEntry.COLUMN_AKTIVITASID+ "=" + id, null);
    }

     public List<Aktivitas> getAllFavorite(){
        String[] columns = {
              FavoriteContract.FavoriteEntry._ID,
                FavoriteContract.FavoriteEntry.COLUMN_AKTIVITASID,
                FavoriteContract.FavoriteEntry.COLUMN_NAME,
                FavoriteContract.FavoriteEntry.COLUMN_DESCRIPTION,
                FavoriteContract.FavoriteEntry.COLUMN_PHOTO,
                FavoriteContract.FavoriteEntry.COLUMN_COLOR

        };
        String sortOrder =
                FavoriteContract.FavoriteEntry._ID + " ASC";
        List<Aktivitas> favoriteList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(FavoriteContract.FavoriteEntry.TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                sortOrder);

        if (cursor.moveToFirst()){
            do {
                Aktivitas aktivitas = new Aktivitas();
                aktivitas.setColor(Integer.parseInt(cursor.getString(cursor.getColumnIndex(FavoriteContract.FavoriteEntry.COLUMN_COLOR))));
                aktivitas.setKey_id(cursor.getString(cursor.getColumnIndex(FavoriteContract.FavoriteEntry.COLUMN_AKTIVITASID)));
                aktivitas.setName(cursor.getString(cursor.getColumnIndex(FavoriteContract.FavoriteEntry.COLUMN_NAME)));
                aktivitas.setDetail(cursor.getString(cursor.getColumnIndex(FavoriteContract.FavoriteEntry.COLUMN_DESCRIPTION)));
                aktivitas.setPhoto(cursor.getString(cursor.getColumnIndex(FavoriteContract.FavoriteEntry.COLUMN_PHOTO)));
                favoriteList.add(aktivitas);

            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return favoriteList;
    }

    //todo : create empty table
    public void insertEmpty() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        for (int x = 1; x < 11; x ++ ) {
            values.put(FavoriteContract.FavoriteEntry.COLUMN_AKTIVITASID, x);
            values.put(FavoriteContract.FavoriteEntry.COLUMN_FAVSTATUS, "0");

            db.insert(FavoriteContract.FavoriteEntry.TABLE_NAME, null, values);
        }
    }

        public void insertIntoTheDatabase(Aktivitas aktivitas){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FavoriteContract.FavoriteEntry.COLUMN_AKTIVITASID, aktivitas.getKey_id());
        values.put(FavoriteContract.FavoriteEntry.COLUMN_NAME, aktivitas.getName());
        values.put(FavoriteContract.FavoriteEntry.COLUMN_DESCRIPTION, aktivitas.getDetail());
        values.put(FavoriteContract.FavoriteEntry.COLUMN_PHOTO, aktivitas.getPhoto());
        values.put(FavoriteContract.FavoriteEntry.COLUMN_COLOR, aktivitas.getColor());
        values.put(FavoriteContract.FavoriteEntry.COLUMN_FAVSTATUS, aktivitas.getFavStatus());

        db.insert(FavoriteContract.FavoriteEntry.TABLE_NAME, null, values);

        db.close();
    }

     public Cursor read_all_data(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select * from " + FavoriteContract.FavoriteEntry.TABLE_NAME +
                " where " + FavoriteContract.FavoriteEntry.COLUMN_AKTIVITASID + "=" + id +"";
        return db.rawQuery(sql, null, null);
     }

     //todo remove from database
    public void remove_fav(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "UPDATE " + FavoriteContract.FavoriteEntry.TABLE_NAME + " SET " +
                FavoriteContract.FavoriteEntry.COLUMN_FAVSTATUS + " ='0' WHERE "  +
                FavoriteContract.FavoriteEntry.COLUMN_AKTIVITASID + "="+id+"";
        db.execSQL(sql);
    }

    public Cursor select_all_favorite_list() {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT * FROM " + FavoriteContract.FavoriteEntry.TABLE_NAME +
                " WHERE " + FavoriteContract.FavoriteEntry.COLUMN_FAVSTATUS + " ='1'";
        return db.rawQuery(sql,null, null);
    }

}
