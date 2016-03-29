package fujingdong.com.manageeverything.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/3/14.
 * 当调用SQLiteOpenHelper的getWritableDatabase()或者getReadableDatabase()方法
 * 获取用于操作数据库的SQLiteDatabase实例的时候，如果数据库不存在，Android系统会自动生成一个数据库，
 * 接着调用onCreate()方法，onCreate()方法在初次生成数据库时才会被调用，
 * 在onCreate()方法里可以生成数据库表结构及添加一些应用使用到的初始化数据。
 *
 * onUpgrade()方法在数据库的版本发生变化时会被调用，一般在软件升级时才需改变版本号
 *
 * getWritableDatabase() 方法以读写方式打开数据库，一旦数据库的磁盘空间满了，数据库就只能读而不能写，
 * 倘若使用getWritableDatabase()打开数据库就会出错。getReadableDatabase()方法先以读写方式打开数据库，
 * 如果数据库的磁盘空间满了，就会打开失败，当打开失败后会继续尝试以只读方式打开数据库。
 */
public class MDatabaseHelper extends SQLiteOpenHelper {
    //类没有实例化,是不能用作父类构造器的参数,必须声明为静态

    private static final String name = "mDatabase"; //数据库名称

    private static final int version = 1; //数据库版本

    public MDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        //第三个参数CursorFactory指定在执行查询时获得一个游标实例的工厂类,设置为null,代表使用系统默认的工厂类
    }

    public MDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS mDatabase (" + "scheduleId integer primary key autoincrement," + "title varchar," +
                "content varchar," +
                "value integer," +
                "maxvalue integer," + "beizhu varchar)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public Cursor getAll(String where, String orderBy) {//返回表中的数据,where是调用时候传进来的搜索内容,orderby是设置中传进来的列表排序类型
        StringBuilder buf=new StringBuilder("SELECT scheduleId, title, content, value, maxvalue, beizhu FROM mDatabase");

        if (where!=null) {
            buf.append(" WHERE ");
            buf.append(where);
        }

        if (orderBy!=null) {
            buf.append(" ORDER BY ");
            buf.append(orderBy);
        }

        return(getReadableDatabase().rawQuery(buf.toString(), null));
    }
    public Cursor getById(String id) {//根据点击事件获取id,查询数据库
        String[] args={id};

        return(getReadableDatabase()
                .rawQuery("SELECT  scheduleId, title, content, value, maxvalue, beizhu FROM mDatabase WHERE scheduleId=?",
                        args));
    }
    public void insert(String title, String content, String value, String maxvalue, String beizhu) {
        ContentValues cv=new ContentValues();

        cv.put("title", title);
        cv.put("content", content);
        cv.put("value", value);
        cv.put("maxvalue", maxvalue);
        cv.put("beizhu", beizhu);

        getWritableDatabase().insert("mDatabase", null, cv);
    }
    public void update(String scheduleId, String title, String content, String value, String maxvalue, String beizhu) {
        ContentValues cv=new ContentValues();
        String[] args={scheduleId};

        cv.put("title", title);
        cv.put("content", content);
        cv.put("value", value);
        cv.put("maxvalue", maxvalue);
        cv.put("beizhu", beizhu);

        getWritableDatabase().update("mDatabase", cv, "scheduleId=?",
                args);
    }
    public void todelete(boolean isall,String clause){
        if (isall){
            getWritableDatabase().delete("mDatabase","1",null);//第二个参数为1就删除全部
        }else {
            String[] arg={clause};
            getWritableDatabase().delete("mDatabase","scheduleId=?",arg);
        }
    }
    public String gettitle(Cursor c) {
        return(c.getString(1));
    }

    public String getcontent(Cursor c) {
        return(c.getString(2));
    }

    public String getvalue(Cursor c) {
        return(c.getString(3));
    }

    public String getmaxvalue(Cursor c) {
        return(c.getString(4));
    }

    public String getbeizhu(Cursor c) {
        return(c.getString(5));
    }

}
