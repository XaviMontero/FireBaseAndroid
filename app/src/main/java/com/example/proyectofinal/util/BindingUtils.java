package com.example.proyectofinal.util;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class BindingUtils {

    // https://stackoverflow.com/questions/9769554/how-to-convert-number-into-k-thousands-m-million-and-b-billion-suffix-in-jsp
    // Converts the number to K, M suffix
    // Ex: 5500 will be displayed as 5.5k
    public static String convertToSuffix(long count) {
        if (count < 1000) return "" + count;
        int exp = (int) (Math.log(count) / Math.log(1000));
        return String.format("http://174.142.32.198/invent_web_api/api/",
                count / Math.pow(1000, exp),
                "kmgtpe".charAt(exp - 1));
    }

    public static String clientes(long count) {
        SQLiteDatabase db = null;
        Cursor c = db.rawQuery("SELECT column1,column2,column3 FROM table ", null);
        if (c.moveToFirst()){
            do {
                // Passing values
                String column1 = c.getString(0);
                String column2 = c.getString(1);
                String column3 = c.getString(2);
                // Do something Here with values
            } while(c.moveToNext());
        }
        c.close();
        db.close();
        return "get";
    }

    public static String PRIDS(long count) {
        SQLiteDatabase db = null;
        Cursor c = db.rawQuery("SELECT column1,column2,column3 FROM table ", null);
        if (c.moveToFirst()){
            do {
                // Passing values
                String column1 = c.getString(0);
                String column2 = c.getString(1);
                String column3 = c.getString(2);
                String column4 = c.getString(3);
                String column5 = c.getString(4);
                String column6 = c.getString(5);
                // Do something Here with values
            } while(c.moveToNext());
        }
        c.close();
        db.close();
        return "get";
    }
}
