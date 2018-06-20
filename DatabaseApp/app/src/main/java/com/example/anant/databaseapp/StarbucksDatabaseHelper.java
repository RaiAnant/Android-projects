package com.example.anant.databaseapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StarbucksDatabaseHelper extends SQLiteOpenHelper{

    private static final String DB_NAME="Starbucks";
    private static final int DB_VERSION=4;

    public StarbucksDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        updateMyDatabase(sqLiteDatabase,0,DB_VERSION);
    }

    public static void insertProduct(SQLiteDatabase sqLiteDatabase, String dbName,String name, String description, int resourceid){
        ContentValues drink_vals=new ContentValues();
        drink_vals.put("NAME",name);
        drink_vals.put("DESCRIPTION",description);
        drink_vals.put("IMAGE_RESOURCE_ID",resourceid);
        sqLiteDatabase.insert(dbName,null,drink_vals);
    }

    public void updateMyDatabase(SQLiteDatabase sqLiteDatabase,int oldVersion,int newVersion){
        if(oldVersion<1) {
            sqLiteDatabase.execSQL("CREATE TABLE DRINK ("
                    + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NAME TEXT, "
                    + "DESCRIPTION TEXT, "
                    + "IMAGE_RESOURCE_ID INTEGER);");
            insertProduct(sqLiteDatabase,"DRINK" ,"Latte", "Hot mild coffe , with several shots of espresso and steamy milk ", R.drawable.latte);
            insertProduct(sqLiteDatabase,"DRINK" , "Cappuccino", "Sweet coffe, with milk cream and foam", R.drawable.latte);
            insertProduct(sqLiteDatabase,"DRINK" , "Filter", "Strong coffe, brimmed from high quality coffe beans with shots of espesso", R.drawable.latte);
        }
        if(oldVersion<2){
            sqLiteDatabase.execSQL("ALTER TABLE DRINK ADD COLUMN FAVOURITE NUMERIC");
            ContentValues contentValues = new ContentValues();
            contentValues.put("FAVOURITE",2);
            sqLiteDatabase.update("DRINK",contentValues,"_id=1",null);
            contentValues.put("FAVOURITE",1);
            sqLiteDatabase.update("DRINK",contentValues,"_id>1",null);
        }
        if(oldVersion<3){
            sqLiteDatabase.execSQL("CREATE TABLE FOOD ("
            + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "NAME TEXT, "
            + "DESCRIPTION TEXT, "
            + "IMAGE_RESOURCE_ID);");
            insertProduct(sqLiteDatabase,"FOOD","French Fries","Hot,long,fried and crispy potato with spices,sause and mayo ",R.drawable.latte);
            insertProduct(sqLiteDatabase,"FOOD","Garlic Bread","Crispy bread slices, with garlic flavour, served with hot sauces",R.drawable.latte);
            insertProduct(sqLiteDatabase,"FOOD","Cookies","Freshly backed, high quality cookies, with choco chips",R.drawable.latte);

        }
        if(oldVersion<4){
            ContentValues contentValues=new ContentValues();
            String[] tableName={"DRINK","FOOD"};
            int[] imgSrc={R.drawable.latte,R.drawable.cappuccino,R.drawable.filter,R.drawable.frenchfries,R.drawable.garlicbread,R.drawable.cookies};
            for(int i=0;i<tableName.length;i++){
                for(int j=0;j<3;j++){
                    contentValues.put("IMAGE_RESOURCE_ID",imgSrc[j+3*i]);
                    sqLiteDatabase.update(tableName[i],contentValues,"_id=?",new String[] {Integer.toString(j+1)});
                }
            }
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        updateMyDatabase(sqLiteDatabase,oldVersion,newVersion);
    }
}
