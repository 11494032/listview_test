package com.example.moresmart_pc006.listview_test;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.view.ScrollingView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

public class MainActivity extends Activity implements AbsListView.OnScrollListener {

    private ListView lv1 = null;
    private ListView lv2 = null;
    private ListView lv3 = null;

    private int random1 = 0;
    private int random2 = 0;
    private int random3 = 0;

    private int currentState1 = -1;
    private int currentState2 = -1;
    private int currentState3 = -1;

    private TextView tv_result;

    //是否中奖
    private boolean isBigno = false;

    //抽奖后的新的三个随机数
    private int rad1;
    private int rad2;
    private int rad3;

    private MyAdapter myAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv1 =(ListView) findViewById(R.id.lv1);
        lv2 =(ListView) findViewById(R.id.lv2);
        lv3 =(ListView) findViewById(R.id.lv3);

        tv_result = (TextView) findViewById(R.id.tv_result);

        myAdapter = new MyAdapter();

        lv1.setAdapter( myAdapter );
        lv2.setAdapter( myAdapter );
        lv3.setAdapter( myAdapter );

        random1 = new Random().nextInt(Integer.MAX_VALUE/2);
        random2 = new Random().nextInt(Integer.MAX_VALUE/2);
        random3 = new Random().nextInt(Integer.MAX_VALUE/2);

        lv1.setSelection(random1);
        lv2.setSelection(random2);
        lv3.setSelection(random3);


        //设置滚动监听
        lv1.setOnScrollListener(this);
        lv2.setOnScrollListener(this);
        lv3.setOnScrollListener(this);



    }
    private class MyAdapter extends BaseAdapter
    {

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            if( view == null )
            {
                view = new TextView(MainActivity.this);

            }
            TextView textView = (TextView)view;

            switch (i % 3)
            {
                case 0:
                    textView.setText("香蕉");
                    textView.setTextColor(Color.YELLOW);
                    break;
                case 1:
                    textView.setText("苹果");
                    textView.setTextColor(Color.GREEN);
                    break;
                case 2:
                    textView.setText("草莓");
                    textView.setTextColor(Color.RED);
                    break;
                default:
                    break;
            }
            textView.setTextSize(24);

            return view;

        }
    }
    public void start( View view)
    {
        rad1 = random1 + new Random().nextInt(100)-50;
        rad2 = random2 + new Random().nextInt(100)-50;
        rad3 = random3 + new Random().nextInt(100)-50;

        if( rad1 % 3 == rad2 % 3 && rad2 % 3 == rad1 % 3 )
        {
          isBigno = true;
        }
        else
        {
            isBigno = false;
        }

        tv_result.setText("抽奖中。。。");

        lv1.smoothScrollToPosition( rad1 );
        lv2.smoothScrollToPosition( rad2 );
        lv3.smoothScrollToPosition( rad3 );
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {

            switch ( absListView.getId() )
            {
                case R.id.lv1:
                    currentState1 = i;
                    break;
                case R.id.lv2:
                    currentState2 = i;
                    break;
                case R.id.lv3:
                    currentState3 = i;
                    break;
                default:
                    break;
            }
        /**/

        if( currentState1 == 0 && currentState2 == 0 && currentState3 == 0)
        {
            lv1.setSelection(rad1);
            lv2.setSelection(rad2);
            lv3.setSelection(rad3);

            if( isBigno)
                tv_result.setText("恭喜你中奖了");
            else
                tv_result.setText("这次没中奖，下次概率更高");

        }
    }


    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {

        Log.d("Scroll","onScroll i"+ i);
    }
}
