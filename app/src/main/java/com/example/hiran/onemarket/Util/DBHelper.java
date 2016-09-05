package com.example.hiran.onemarket.Util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import android.content.Context;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/**
 * Created by hiran on 9/5/16.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String TAG = DBHelper.class.getSimpleName();
    private static final String DATABASE_FILE = "my_db_script.sql";
    private static final String DATABASE_NAME = "my_db_filename.db";
    private static final int DATABASE_VERSION = 1;
    private static final int BUFFER_SIZE = 2048;

    private static SQLiteDatabase db;
    private static DBHelper instance;
    private static Context context;

    private DBHelper(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
        DBHelper.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.beginTransaction();

        try {
            readDatabaseScript(db);
            db.setTransactionSuccessful();
        } catch (IOException ioe) {
            Log.e(TAG, ioe.getMessage());
        } catch (SQLException sqle) {
            Log.e(TAG, sqle.getMessage());
        } finally {
            db.endTransaction();
        }
    }

    private void readDatabaseScript(SQLiteDatabase db) throws IOException, SQLException {
        InputStream script = context.getAssets().open(DATABASE_FILE);
        byte[] buffer = new byte[BUFFER_SIZE];

        for (int byteRead = script.read(); byteRead != -1; byteRead = script.read()) {
            // resets the buffer
            Arrays.fill(buffer, (byte) 0);

            // reads a SQL statement to the buffer
            for (int i = 0; byteRead != -1 && i != BUFFER_SIZE; byteRead = script.read(), i++) {
                buffer[i] = (byte) byteRead;

                if (byteRead == ';')
                    break;
            }

            // execute the SQL statement from the buffer
            if (byteRead != -1)
                db.execSQL(new String(buffer));
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    @Override
    public synchronized void close() {
        if (instance != null)
            db.close();
    }

    public static synchronized DBHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DBHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
            db = instance.getWritableDatabase();
        }

        return instance;
    }

    public int getNumberRows(String table) {
        return (int) DatabaseUtils.queryNumEntries(db, table);
    }

}
