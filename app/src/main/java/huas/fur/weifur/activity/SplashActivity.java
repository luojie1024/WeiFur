package huas.fur.weifur.activity;

/**
 * 作者：Jacky
 * 邮箱：550997728@qq.com
 * 时间：2016/2/26 10:48
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import huas.fur.weifur.R;

public class SplashActivity extends Activity {

          @Override
          protected void onCreate(Bundle savedInstanceState) {
                    super.onCreate(savedInstanceState);
                    setContentView(R.layout.activity_splash);
                    ((ImageView) findViewById(R.id.bg)).postDelayed(new Runnable() {

                              @Override
                              public void run() {
//                                        SharedPreferenceUtil util = new SharedPreferenceUtil(
//                                             getApplicationContext(), "accountInfo");
//                                        String isLogin = util.getKeyData("isLogin");
                                        //是否已登录
//                                        if (isLogin.equals("TRUE")) {
//                                                  Intent intent = new Intent(SplashActivity.this,
//                                                       MainActivity.class);
//                                                  startActivity(intent);
//                                                  finish();
//                                        } else {
                                                  Intent intent = new Intent(SplashActivity.this,
                                                       LoginActivity.class);
                                                  startActivity(intent);
                                                  finish();
//                                        }
                              }
                    }, 3000);
          }
}

