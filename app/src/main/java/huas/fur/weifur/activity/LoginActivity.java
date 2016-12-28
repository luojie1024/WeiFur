package huas.fur.weifur.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import huas.fur.weifur.R;

/**
 * 作者：Jacky
 * 邮箱：550997728@qq.com
 * 时间：2016/2/23 22:41
 */
public class LoginActivity extends Activity {

          private String username;
          private String password;
          private Handler handler=new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                              super.handleMessage(msg);
                              switch (msg.arg1) {
                                        case 0:
                                                  Toast.makeText(getApplicationContext(),"登陆失败!请检查用户名密码是否正确!",Toast.LENGTH_LONG).show();
                                                  break;

                                        case 1:
                                                  Toast.makeText(getApplicationContext(),"登陆成功!",Toast.LENGTH_SHORT).show();
                                                  break;
                                        case 2:
                                                  Toast.makeText(getApplicationContext(),"教务系统连接失败，请更换网络或选择离线登入！",Toast.LENGTH_LONG).show();
                                                  break;
                              }

                    }
          };
          private EditText ed_username;
          private EditText ed_password;
          private int mode=0;//网络连接控制

          @Override
          protected void onCreate(Bundle savedInstanceState) {
                    super.onCreate(savedInstanceState);
                    setContentView(R.layout.activity_login);
                    //找到登入按钮
                    Button login = (Button) findViewById(R.id.login);
                    Button off_linelogin = (Button) findViewById(R.id.off_linelogin);
                    ed_username = (EditText) findViewById(R.id.username);
                    ed_password = (EditText) findViewById(R.id.password);
                    //取到保存的账号密码
                    SharedPreferences sp = getSharedPreferences("config", 0);
                    String user = sp.getString("username", null);
                    String pass = sp.getString("password", null);
                    ed_username.setText(user);
                    ed_password.setText(pass);
                    //登入响应事件
                    login.setOnClickListener(new View.OnClickListener() {
                              //设置登入事件
                              @Override
                              public void onClick(View v) {
                                        //登入逻辑
                                        if (0==mode) {
                                                  Toast.makeText(getApplicationContext(), "玩命加载中...", Toast.LENGTH_SHORT).show();
                                                  mode=1;
                                                  new Thread() {
                                                            @Override
                                                            public void run() {
                                                                      super.run();
                                                                      //通过教务系统模拟登陆 测试账号密码是否正确
                                                                      try {

                                                                                //获取EditText中输入的信息
                                                                                LoginActivity.this.username = ed_username.getText().toString().trim();
                                                                                LoginActivity.this.password = ed_password.getText().toString().trim();
                                                                                URL url = new URL(
                                                                                     "http://www.huas.cn:91/jwweb/_data/index_LOGIN.aspx");
                                                                                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                                                                                conn.setConnectTimeout(5000);
                                                                                conn.setRequestMethod("POST");
                                                                                //2016.4.3加入报头
                                                                                conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
                                                                                conn.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; rv:11.0) like Gecko");
                                                                                //
                                                                                conn.setDoOutput(true);
                                                                                conn.setDoInput(true);
                                                                                conn.setUseCaches(false);
                                                                                OutputStream os = conn.getOutputStream();
                                                                                String param;
                                                                                param = "Sel_Type=" + "STU" + "&UserID=" + LoginActivity.this.username
                                                                                     + "&PassWord=" + LoginActivity.this.password;
                                                                                os.write(param.getBytes());
                                                                                int content_length = conn.getContentLength();
                                                                                int code=conn.getResponseCode();
                                                                                String responseCookie = conn.getHeaderField("Set-Cookie");
                                                                                //11.24添加
//                                                                                InputStream in=conn.getInputStream();
//                                                                                String result = HttpInfoUtil.streamTools(in);
//                                                                                System.out.println(result);

                                                                                //TODO 判断返回长度来验证用户名密码是否正确 可能存在BUG
                                                                                if (code==200) {//content_length == 10525
                                                                                          // 取到所用的Cookie
                                                                                          responseCookie = conn.getHeaderField("Set-Cookie");
                                                                                          //提示用户登陆成功
                                                                                          Message msg = handler.obtainMessage();
                                                                                          msg.arg1 = 1;
                                                                                          handler.sendMessage(msg);
                                                                                          //登入成功,保存 用户名 密码
                                                                                          SharedPreferences sp = getSharedPreferences("config", 0);
                                                                                          SharedPreferences.Editor edit = sp.edit();
                                                                                          edit.putString("username", LoginActivity.this.username);
                                                                                          edit.putString("password", LoginActivity.this.password);
                                                                                          edit.putString("mode", "1");
                                                                                          edit.putString("cookie", responseCookie);
                                                                                          System.out.println(LoginActivity.this.username + "------" + LoginActivity.this.password);
                                                                                          edit.commit();
                                                                                          conn.disconnect();
                                                                                          //登入成功跳转到MainActivity
                                                                                          Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                                                                          mode=0;
                                                                                          startActivity(intent);
                                                                                } else {
                                                                                          //提示用户登陆失败
                                                                                          Message msg = handler.obtainMessage();
                                                                                          msg.arg1 = 0;
                                                                                          handler.sendMessage(msg);
                                                                                          conn.disconnect();
                                                                                          mode=0;
                                                                                }
                                                                                System.out.println(content_length);
                                                                      } catch (Exception e) {
                                                                                e.printStackTrace();
                                                                                //提示连接超时
                                                                                Message msg = handler.obtainMessage();
                                                                                msg.arg1 = 2;
                                                                                handler.sendMessage(msg);
                                                                                mode=0;
                                                                      }
                                                            }
                                                  }.start();
                                        }


                              }
                    });

                    //离线登入模式
                    off_linelogin.setOnClickListener(new View.OnClickListener() {
                              @Override
                              public void onClick(View v) {
                                        //跳转到登入页面
                                        Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                                        //设置登入模式 0为离线模式 ，1为登入模式
                                        SharedPreferences sp = getSharedPreferences("config", 0);
                                        SharedPreferences.Editor edit = sp.edit();
                                        edit.putString("mode", "0");
                                        edit.commit();
                                        startActivity(intent);
                              }
                    });
          }
}
