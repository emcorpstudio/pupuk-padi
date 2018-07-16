package emcorp.studio.pupukpadi.Library;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by ASUS on 28/06/2015.
 */
public class DBHandler extends SQLiteOpenHelper {


    public DBHandler(Context context, String name,
                     SQLiteDatabase.CursorFactory factory, int version) {
        super(context, Constant.DATABASE_NAME, factory, Constant.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String TABLE_DATA =
                "CREATE TABLE IF NOT EXISTS " + Constant.TABLE_DATA+ "("
                        + Constant.DATA_ID + " INTEGER PRIMARY KEY,"
                        + Constant.DATA_PANJANG + " TEXT,"
                        + Constant.DATA_LEBAR + " TEXT,"
                        + Constant.DATA_LUAS + " TEXT,"
                        + Constant.DATA_HEKTAR + " TEXT,"
                        + Constant.DATA_FUZZY + " TEXT,"
                        + Constant.DATA_PUPUK + " TEXT,"
                        + Constant.DATA_TANGGAL + " TEXT " + ")";

        db.execSQL(TABLE_DATA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
//        onCreate(db);
    }

    public void deleteAll(String tbl){
        SQLiteDatabase db = this.getWritableDatabase(); //get database
        db.execSQL("DELETE FROM "+ tbl); //delete all rows in a table
        db.close();
    }

    //Add Olahan
    public void addData(DataPupuk listData) {
        ContentValues values = new ContentValues();
        values.put(Constant.DATA_PANJANG, listData.getDATA_PANJANG());
        values.put(Constant.DATA_LEBAR, listData.getDATA_LEBAR());
        values.put(Constant.DATA_LUAS, listData.getDATA_LUAS());
        values.put(Constant.DATA_HEKTAR, listData.getDATA_HEKTAR());
        values.put(Constant.DATA_FUZZY, listData.getDATA_FUZZY());
        values.put(Constant.DATA_PUPUK, listData.getDATA_PUPUK());
        values.put(Constant.DATA_TANGGAL, listData.getDATA_TANGGAL());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(Constant.TABLE_DATA, null, values);
        db.close();
    }

    //Select All
    public Cursor selectAllTable(String TABLENAME,String ORDERBY) throws SQLException
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String command = "SELECT * FROM " + TABLENAME;
        if(!ORDERBY.equals("")){
            command = command + " ORDER BY "+ORDERBY+" ASC";
        }
        return db.rawQuery(command, null);
    }

    //Select One Condition
    public Cursor selectOneCondition(String TABLENAME, String FIELD, String KEY) throws SQLException
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String command = "SELECT * FROM " + TABLENAME +" WHERE "+ FIELD +" LIKE '%"+KEY+"%'";
        Log.d("QUERY",command);
        return db.rawQuery(command, null);
    }

    //Select Two Condition
    public Cursor selectTwoCondition(String TABLENAME, String FIELD1, String KEY1, String FIELD2, String KEY2) throws SQLException
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String command = "SELECT * FROM " + TABLENAME +" WHERE "+ FIELD1 +" LIKE '%"+KEY1+"%' AND "+ FIELD2 +" LIKE '%"+KEY2+"%'";
        return db.rawQuery(command, null);
    }

    //Select Three Condition
    public Cursor selectThreeCondition(String TABLENAME, String FIELD1, String KEY1, String FIELD2, String KEY2, String FIELD3, String KEY3) throws SQLException
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String command = "SELECT * FROM " + TABLENAME +" WHERE "+ FIELD1 +" LIKE '%"+KEY1+"%' AND "+ FIELD2 +" LIKE '%"+KEY2+"%' AND "+ FIELD3 +" LIKE '%"+KEY3+"%'";
        return db.rawQuery(command, null);
    }

    public boolean isTableExists(String tableName)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        if (tableName == null || db == null || !db.isOpen())
        {
            return false;
        }
        Cursor cursor = db.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '" + tableName + "'", null);

        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.close();
                return true;
            }
            cursor.close();
        }
        return false;
    }


}
