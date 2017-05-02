package cbh.testsqlite;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.widget.TextView;

/**
 * Created by gsb on 5/2/17.
 */

public class ShowActivity extends Activity {

    TextView id = null;
    TextView buf = null;
    TextView name = null;
    String c1;
    String c2;
    String c3;
    SQLiteDatabase db;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_show);
        id = (TextView) findViewById( R.id.id_showing_tv);
        buf = (TextView) findViewById( R.id.buf_showing_tv);
        name = (TextView) findViewById( R.id.name_showing_tv);
        db = SQLiteDatabase.openOrCreateDatabase(
                Environment.getExternalStorageDirectory()+"/"+"testsql.db",
                null
        );
        Cursor cursor = db.rawQuery("select * from test_table", null);
        cursor.moveToNext();
        c1 = cursor.getString( 0);
        c2 = new String( cursor.getBlob( 1));
        c3 = cursor.getString( 2);
        id.setText( c1);
        buf.setText( c2);
        name.setText( c3);
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();
    }
}
