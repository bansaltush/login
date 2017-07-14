package com.example.tushar.login;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.tushar.login.MyHelper;
import com.example.tushar.login.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class graphactivity extends AppCompatActivity {

    Button button;
    EditText xInput , yInput ;
    GraphView graph;
    LineGraphSeries<DataPoint> series;
    MyHelper myHelper;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button)findViewById(R.id.button);
        xInput=(EditText)findViewById(R.id.inputTextX);
        yInput=(EditText)findViewById(R.id.inputTextY);
        graph = (GraphView)findViewById(R.id.graph);

        myHelper = new MyHelper (this);
        sqLiteDatabase = myHelper.getWritableDatabase();
        exqButton();

    }
    private void exqButton(){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int xVal= Integer.parseInt(String.valueOf(xInput.getText()));
                int yVal= Integer.parseInt(String.valueOf(yInput.getText()));

                myHelper.insertData(xVal,yVal);
                series=new LineGraphSeries<DataPoint>(getData());
                graph.addSeries(series);
            }
        });
    }
    private  DataPoint[] getData(){
        String[] columns = {"xValues", "yValues"};
        Cursor cursor =  sqLiteDatabase.query("MyTable",columns,null,null,null,null,null);
        DataPoint[] dp = new DataPoint[cursor.getCount()];

        for(int i=0;i<cursor.getCount();i++)
        {
            cursor.moveToNext();
            dp[i]=new DataPoint(cursor.getInt(0),cursor.getInt(1));
        }
        return dp;
    }

}

