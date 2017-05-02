package cbh.testsqlite;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    String buf;
    String id;
    String name;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        File db_file = new File( Environment.getExternalStorageDirectory(), "testsql.db");
        db = SQLiteDatabase.openOrCreateDatabase(
                db_file,
                null
        );
        db.execSQL("drop table if exists test_table ;");
        db.execSQL("create table if not exists test_table(_id text,_content blob,_name text);");

        Button btn = (Button) findViewById( R.id.btn1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buf = ((EditText)findViewById( R.id.buf_et)).getText().toString();
                id = ((EditText)findViewById( R.id.id_et)).getText().toString();
                name = ((EditText)findViewById( R.id.name_et)).getText().toString();
                db.execSQL("insert into test_table values(" +
                        "'"+id+"','"+buf+"','"+name+"');");
                Intent intent = new Intent( MainActivity.this, ShowActivity.class);
                db.close();
                startActivity( intent);
            }
        });
    }
}
