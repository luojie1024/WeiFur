package huas.fur.weifur.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

import huas.fur.weifur.R;
import huas.fur.weifur.util.HttpScoreUtil;
import huas.fur.weifur.util.MyScore;

/**
 * 作者：Jacky
 * 邮箱：550997728@qq.com
 * 时间：2016/2/27 13:23
 */
public class ScoreActivity extends Activity {
          private String s_xq = null;
          private String s_xn = null;
          private Spinner sel_xn = null;
          private Spinner sel_xq = null;
          private Button query;
          //Handler消息接收
          private Handler handler = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                              super.handleMessage(msg);
                              switch (msg.arg1) {
                                        case 1:
                                                  Toast.makeText(getApplicationContext(), "数据获取失败!请检查设置是否有误!", Toast.LENGTH_LONG).show();
                                                  break;
                              }
                    }
          };
          private String password;
          private String username;

          @Override
          protected void onCreate(Bundle savedInstanceState) {
                    super.onCreate(savedInstanceState);
                    setContentView(R.layout.activity_score);
                    //获取用户名密码
                    SharedPreferences sp = getSharedPreferences("config", 0);
                    password = sp.getString("password","");
                    username = sp.getString("username","");

                    query = (Button) findViewById(R.id.query);
                    sel_xn = (Spinner) findViewById(R.id.sel_xn);
                    sel_xq = (Spinner) findViewById(R.id.sel_xq);
                    //设置学年Spinner的监听事件 设置选择的值
                    sel_xn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                              @Override
                              public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        s_xn = parent.getItemAtPosition(position).toString();
                              }

                              @Override
                              public void onNothingSelected(AdapterView<?> parent) {
                                        s_xn = "2013";
                              }
                    });
                    //设置学期Spinner的监听事件 设置选择的值
                    sel_xq.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                              @Override
                              public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        s_xq = String.valueOf(position);
                              }

                              @Override
                              public void onNothingSelected(AdapterView<?> parent) {
                                        s_xq = "0";
                              }
                    });

                    //进行查询成绩的操作
                    query.setOnClickListener(new View.OnClickListener() {
                              @Override
                              public void onClick(View v) {
                                        //连接服务器 ,获取成绩信息的逻辑
                                        new Thread() {
                                                  @Override
                                                  public void run() {
                                                            super.run();
                                                            HttpScoreUtil httpScoreUtil = new HttpScoreUtil(s_xn, s_xq);
                                                            List<MyScore> result = null;
                                                            //连接服务器 获取Cookies
                                                            System.out.println("SetCookies  : "+username+"---"+password);
                                                            httpScoreUtil.getCookies(username, password);
                                                            result = httpScoreUtil.getQuery();
                                                            if (result.size() == 0) {
                                                                      //获取数据失败 发送提示消息
                                                                      Message msg = handler.obtainMessage();
                                                                      msg.arg1 = 1;
                                                                      handler.sendMessage(msg);

                                                            } else {
                                                                      Intent intent = new Intent(getApplicationContext(), ScoreShowActivity.class);
                                                                      intent.putExtra("result", (Serializable) result);
                                                                      startActivity(intent);
                                                            }
                                                  }
                                        }.start();
                              }
                    });
          }
}
