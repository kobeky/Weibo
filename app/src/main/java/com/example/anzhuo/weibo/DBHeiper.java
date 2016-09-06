package com.example.anzhuo.weibo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by anzhuo on 2016/8/31.
 */
public class DBHeiper extends SQLiteOpenHelper {
     static final String DB_NAME="coll.db";
    private static final String TBL_NAME="CollTbl";
    private static final int VERSION=1;
    private static final String CREATE_TBL=" create table "
            +" CollTbl(_id integer primary key autoincrement,name text,words text)";
    private SQLiteDatabase db;

    public DBHeiper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
this.db=db;
        db.execSQL(CREATE_TBL);
    }

    public void insert(ContentValues values)
    {
        //获得SQLiteDatabase实例
        SQLiteDatabase db=getWritableDatabase();
        //插入
        db.insert(TBL_NAME, null, values);
        //关闭
        db.close();
    }
    /*
     * 查询方法
     */
    public Cursor query(String dbName, Object o, Object o1, Object o2, Object o3, Object o4, Object o5)
    {
        //获得SQLiteDatabase实例
        SQLiteDatabase db=getWritableDatabase();
        //查询获得Cursor
        Cursor c=db.query(TBL_NAME, null, null, null, null, null, null);
        return c;
    }
    /*
     * 删除方法
     */
    public void del(int id)
    {
        if(db==null)
        {
            //获得SQLiteDatabase实例
            db=getWritableDatabase();
        }
        //执行删除
        db.delete(TBL_NAME, "_id=?", new String[]{String.valueOf(id)});
    }
    /*
     * 关闭数据库
     */
    public void colse()
    {
        if(db!=null)
        {
            db.close();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
