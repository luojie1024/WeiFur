package huas.fur.weifur.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import huas.fur.weifur.R;
import huas.fur.weifur.util.MyScore;

/**
 * 作者：Jacky
 * 邮箱：550997728@qq.com
 * 时间：2016/2/28 10:33
 */
public class ScoreShowActivity extends Activity {
          private ListView lv;
          private List<MyScore> result;

          @Override
          protected void onCreate(Bundle savedInstanceState) {
                    super.onCreate(savedInstanceState);
                    setContentView(R.layout.activity_score_show);
                    //[1]找到控件
                    lv = (ListView) findViewById(R.id.lv_scoreshow);
                    //[2]准备listview要显示的数据 去服务器取数据
                    initListData();
                    lv.setAdapter(new MyAdapter());
          }

          private void initListData() {
                    result = (List<MyScore>)getIntent().getSerializableExtra("result");

          }

          private class MyAdapter extends BaseAdapter{
                    //设置显示条目数量
                    @Override
                    public int getCount() {
                              return result.size();
                    }

                    @Override
                    public Object getItem(int position) {
                              return null;
                    }

                    @Override
                    public long getItemId(int position) {
                              return 0;
                    }

                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                              View view;
                              if (convertView == null) {
                                        view = View.inflate(getApplicationContext(), R.layout.activity_score_show_item, null);
                              } else {
                                        view=convertView;
                              }
                              TextView tv_class = (TextView) view.findViewById(R.id.tv_class);
                              TextView tv_score = (TextView) view.findViewById(R.id.tv_score);
                              tv_score.setText(result.get(position).getMy_Score());
                              tv_class.setText(result.get(position).getMy_class());
                              return view;
                    }
          }
}
