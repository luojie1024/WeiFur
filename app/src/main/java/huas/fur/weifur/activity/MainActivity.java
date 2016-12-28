package huas.fur.weifur.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import java.io.File;

import huas.fur.weifur.R;
import huas.fur.weifur.dao.DataBaseHelper;
import huas.fur.weifur.util.HttpInfoUtil;
import huas.fur.weifur.util.MyImageView;

/**
 * 作者：Jacky
 * 邮箱：550997728@qq.com
 * 时间：2016/2/20 11:03
 */
public class MainActivity extends Activity {
          private String mode = null;  //登陆模式
          private String my_class = null;//我的班级
          private DataBaseHelper myDbHelper;//数据库
          private String[] classs;
          private SharedPreferences sp;
          private String cookie;
          private Handler handler=new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                              super.handleMessage(msg);
                              switch (msg.arg1) {
                                        case 0:
                                                  Toast.makeText(getApplicationContext(),"离线模式登陆成功！",Toast.LENGTH_LONG).show();
                                                  break;

                                        case 1:
                                                  Toast.makeText(getApplication(),"欢迎 "+str[0]+" 同学使用,期待您提出宝贵的意见！",Toast.LENGTH_LONG).show();
                                                  break;
                              }

                    }
          };
          private String[] str;

          @Override
          protected void onCreate(Bundle savedInstanceState) {
                    super.onCreate(savedInstanceState);

                    setContentView(R.layout.activity_main);
                    //从sp中获取cookie
                    sp = getSharedPreferences("config", 0);
                    //设置cookie
                    cookie = sp.getString("cookie", "");
                    //设置模式
                    mode = sp.getString("mode", null);
                    //判断是否为离线模式
                    if ("0".equals(mode)) {
                              //离线模式不访问教务系统获取班级信息
                              Message message = handler.obtainMessage();
                              message.arg1=0;
                              handler.sendMessage(message);
                    } else {
                              //获取登入者的姓名 班级 身份信息
                              new Thread() {
                                        @Override
                                        public void run() {
                                                  super.run();

                                                  HttpInfoUtil httpInfoUtil = new HttpInfoUtil(cookie);
                                                  //获取解析到的数据
                                                  str = httpInfoUtil.getInfo();
                                                  System.out.println(str[0]);
                                                  System.out.println(str[1]);
                                                  System.out.println(str[2]);
                                                  //将获取的数据保存到sp中
                                                  SharedPreferences.Editor edit = sp.edit();
                                                  edit.putString("name", str[0]);
                                                  edit.putString("id", str[1]);
                                                  edit.putString("class", str[2]);
                                                  edit.commit();
                                                  Message message = handler.obtainMessage();
                                                  message.arg1=1;
                                                  handler.sendMessage(message);
                                        }
                              }.start();
                    }
                    //实现登入提示功能 弹出对话框
                    //加载数据库 拷贝(第一次) 读取(第二次)
                    //判断数据库是否存在
                    String path = "/data/data/huas.fur.weifur/databases/Course.db";
                    File file = new File(path);
                    System.out.println(file.exists());
                    if (false == file.exists()) {
                              //若数据库为空,则拷贝 创建数据库
                              myDbHelper = new DataBaseHelper(this);
                              try {
                                        myDbHelper.createDataBase();
                                        myDbHelper.close();
                              } catch (Exception e) {
                                        throw new Error("Unable to create database");
                              }
                    } else {
                              System.out.println("数据库已存在!");
                    }

                    System.out.println(file.exists());
                    //requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
                    //setContentView(R.layout.activity_main);
                    //getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.activity_title);
                    Intent intent = getIntent();
                    //找到控件
                    huas.fur.weifur.util.MyImageView select_course = (huas.fur.weifur.util.MyImageView) findViewById(R.id.select_course);
                    huas.fur.weifur.util.MyImageView select_score = (huas.fur.weifur.util.MyImageView) findViewById(R.id.select_score);
                    huas.fur.weifur.util.MyImageView select_classroom = (huas.fur.weifur.util.MyImageView) findViewById(R.id.select_classroom);
                    huas.fur.weifur.util.MyImageView select_about = (huas.fur.weifur.util.MyImageView) findViewById(R.id.select_about);
                    //设置课程查询按钮的监听
                    select_course.setOnClickIntent(new MyImageView.OnViewClickListener() {
                              @Override
                              public void onViewClick(MyImageView view) {
                                        if ("0".equals(mode) && my_class == null) {

                                                  //打开数据库
                                                  DataBaseHelper myDbHelper = new DataBaseHelper(MainActivity.this);
                                                  try {
                                                            myDbHelper.openDataBase();

                                                  } catch (Exception e) {
                                                            throw new Error("database open false");
                                                  }

                                                  //提前加载好数据库
                                                  SQLiteDatabase db = myDbHelper.getWritableDatabase();
                                                  String sql = "select distinct Class from Course group by Class";
                                                  System.out.println(sql);
                                                  Cursor cursor = db.rawQuery(sql, null);
                                                  //查询班级数据
                                                  if (cursor != null && cursor.getCount() > 0) {
                                                            classs = new String[97];
                                                            for (int i = 0; cursor.moveToNext(); i++) {
                                                                      classs[i] = cursor.getString(0);
                                                                      System.out.println(cursor.getString(0));
                                                            }
                                                  } else {
                                                            Toast.makeText(MainActivity.this, "数据查询失败！", Toast.LENGTH_SHORT).show();
                                                  }
                                                  //设置班级
                                                  AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                                  builder.setTitle("请选择您的班级！");
                                                  //    设置一个下拉的列表选择项
                                                  builder.setItems(classs, new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialog, int which) {
                                                                      my_class = classs[which];
                                                                      //从sp中获取cookie
                                                                      SharedPreferences sp = getSharedPreferences("config", 0);
                                                                      SharedPreferences.Editor edit = sp.edit();
                                                                      edit.putString("class", my_class);
                                                                      edit.commit();
                                                                      Toast.makeText(MainActivity.this, "设置成功，您选择的班级为：" + classs[which], Toast.LENGTH_SHORT).show();
                                                                      //转跳到课程查询页面
                                                                      Intent intent = new Intent(MainActivity.this, CourseActivity.class);
                                                                      startActivity(intent);
                                                            }
                                                  });
                                                  builder.show();

                                        } else {
                                                  //转跳到课程查询页面
                                                  Intent intent = new Intent(MainActivity.this, CourseActivity.class);
                                                  startActivity(intent);
                                        }

                              }
                    });

        //            initValue();// 变量初始化
//                    initView();// 视图初始话
//                    initEvent();// 事件初始化



                    //设置成绩查询按钮的监听
                    select_classroom.setOnClickIntent(new MyImageView.OnViewClickListener() {
                              @Override
                              public void onViewClick(MyImageView view) {
                                        //当前为离线模式
                                        System.out.println(mode);
                                        if ("0".equals(mode)) {
                                                  Toast.makeText(MainActivity.this, "sorry..当前为离线模式，请切换到登入模式使用该功能", Toast.LENGTH_LONG).show();
                                        } else {
                                                  //转跳到成绩查询页面
                                                  Intent intent = new Intent(MainActivity.this, ScoreActivity.class);
                                                  startActivity(intent);
                                        }
                              }
                    });
                    //设置教室查询按钮的监听
                    select_score.setOnClickIntent(new MyImageView.OnViewClickListener() {
                              @Override
                              public void onViewClick(MyImageView view) {
                                        //转跳到教室查询页面
                                        Intent intent = new Intent(MainActivity.this, ClassRoomActivity.class);
                                        startActivity(intent);

                              }
                    });

                    //关于我们
                    select_about.setOnClickIntent(new MyImageView.OnViewClickListener() {
                              @Override
                              public void onViewClick(MyImageView view) {
                                        //转跳到关于页面
                                        Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                                        startActivity(intent);
                              }
                    });




          }

          /**
           * 初始化变量
           */
          private void initValue() {
//                    GridView gridView = (GridView) findViewById(R.id.gridview);
//                    //新建List
//                    data_list = new ArrayList<Map<String, Object>>();
//                    //获取数据
//                    //cion和iconName的长度是相同的，这里任选其一都可以
//                    for (int i = 0; i < icon.length; i++) {
//                              Map<String, Object> map = new HashMap<String, Object>();
//                              map.put("image", icon[i]);
//                              map.put("text", iconName[i]);
//                              data_list.add(map);
//                    }
//                    //新建适配器
//                    String[] from = {"image", "text"};
//                    int[] to = {R.id.image, R.id.text};
//                    sim_adapter = new SimpleAdapter(this, data_list, R.layout.activity_main_item, from,to);
//                    gridView.setAdapter(sim_adapter);
          }




}
