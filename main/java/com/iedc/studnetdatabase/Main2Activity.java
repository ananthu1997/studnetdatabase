
package com.iedc.studnetdatabase;


        import android.app.Activity;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.renderscript.Sampler;
        import android.support.v7.app.AlertDialog;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.Menu;
        import android.view.MenuInflater;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.CheckBox;
        import android.widget.EditText;
        import android.widget.ListView;
        import android.widget.RadioButton;
        import android.widget.RadioGroup;
        import android.widget.Spinner;
        import android.widget.Toast;
        import android.widget.AdapterView.OnItemSelectedListener;
        import java.sql.ResultSet;
        import java.sql.SQLClientInfoException;
        import java.util.ArrayList;
        import java.util.List;

        import static android.os.Build.VERSION_CODES.M;


public class Main2Activity extends AppCompatActivity
        implements OnItemSelectedListener {
    EditText t1;Button b1;
    SQLiteDatabase db;
    RadioGroup rb;
    RadioButton ra,m,n;
    CheckBox a,b,c;
    public String i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        t1=(EditText)findViewById(R.id.t1);
        b1=(Button)findViewById(R.id.b1);
        rb=(RadioGroup)findViewById(R.id.rb);
        m=(RadioButton)findViewById(R.id.m);
        n=(RadioButton)findViewById(R.id.n);

        a=(CheckBox)findViewById(R.id.a);
        b=(CheckBox)findViewById(R.id.b);
        c=(CheckBox)findViewById(R.id.c);
        db=openOrCreateDatabase("database",MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS student4("+
                "name TEXT NOT NULL PRIMARY KEY,"+
                "branch TEXT NOT NULL ,"+
                "gender TEXT NOT NULL DEFAULT MALE ,"+
                "web INT NOT NULL CHECK (web IN (0,1)),"+
                "android INT NOT NULL CHECK (android IN (0,1)),"+
                "design INT NOT NULL CHECK (design IN (0,1)));");
        Spinner s = (Spinner) findViewById(R.id.t2);
        s.setOnItemSelectedListener(this);
        List list=new ArrayList();
        list.add("");
        list.add("CIVIL ENGG");
        list.add("CHEMICAL ENGG");
        list.add("COMPUTER SC & ENGG");
        list.add("ELECTRONICS & COMM ENGG");
        list.add("ELECTRICAL & ELECTRONICS ENGG");
        list.add("MECHANICAL ENGG");
        list.add("MECHANICAL(prodn)ENGG");
        list.add("B ARCH");
        ArrayAdapter d=new ArrayAdapter(this, android.R.layout.simple_spinner_item,list);
        d.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(d);
        b1.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      int ce=rb.getCheckedRadioButtonId();
                                      ra=(RadioButton)findViewById(ce);
                                      String nam=t1.getText().toString();
                                      Cursor resultSet = db.rawQuery("SELECT * from student4 WHERE name=?",
                                              new String[]{nam});
                                      resultSet.moveToFirst();
                                      if(!resultSet.isAfterLast()){
                                          AlertDialog.Builder build = new AlertDialog.Builder(Main2Activity.this);
                                          build.setTitle("DUPLICATION");
                                          build.setMessage("already exist");
                                          build.setCancelable(true);
                                          build.setIcon(R.mipmap.ic_launcher);
                                          build.show();
                                          resultSet.close();
                                          return;
                                      }
                                      String gend=ra.getText().toString();

                                      String br=i;
                                      Boolean ac=a.isChecked();
                                      Boolean bc=b.isChecked();
                                      Boolean cc=c.isChecked();
                                      int st,su,sv;
                                      st=ac?1:0;
                                      su=bc?1:0;
                                      sv=cc?1:0;
                                      db.execSQL("INSERT INTO student4 VALUES(" + "'" + nam + "'" + ",'" + br + "','" + gend + "'," + st + "," + su + "," + sv + ");");
                                      Toast.makeText(getApplicationContext(), "Values stored", Toast.LENGTH_LONG).show();
                                  }


                              }
        );
    }
    @Override
    public void onBackPressed(){
        onBackPressed();
    }
    @Override
    public  void onItemSelected(AdapterView<?> parent,View view,int position,long id) {
        i = parent.getItemAtPosition(position).toString();}
    public void onNothingSelected(AdapterView<?> arg0){

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.mymenu, menu);
        return true;

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.credits:
                Toast.makeText(Main2Activity.this, "Created by ANANTHU S AJAYAN ,R3", Toast.LENGTH_LONG).show();
                return true;
            case R.id.website:
                Intent l = new Intent(Main2Activity.this, webview.class);
                startActivity(l);
                return true;
            default:
                return onOptionsItemSelected(item);
        }
    }
}
