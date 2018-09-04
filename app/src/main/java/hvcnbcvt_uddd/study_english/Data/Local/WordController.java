package hvcnbcvt_uddd.study_english.Data.Local;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import hvcnbcvt_uddd.study_english.Data.Model.Word;

public class WordController {
    private DBHelper dbHelper;

   public WordController(Context context)
   {
       dbHelper = new DBHelper(context);
   }

   public ArrayList<Word> getWord(int unit)
   {
       ArrayList<Word> lsData = new ArrayList<Word>();
       SQLiteDatabase db = dbHelper.getReadableDatabase();
       Cursor cursor = db.rawQuery("SELECT * FROM tbl_vocabulary WHERE unit = '"+unit+"'",null);
       cursor.moveToFirst();
       do{
            Word item;
            item = new Word(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),
                    cursor.getString(4),cursor.getString(5),cursor.getInt(6),"");
            lsData.add(item);
       }while(cursor.moveToNext());
       return lsData;
   }

}
