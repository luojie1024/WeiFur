package huas.fur.weifur.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import huas.fur.weifur.R;
import huas.fur.weifur.adapter.MyspinnerAdapter;
import huas.fur.weifur.dao.CourseQuery;
import huas.fur.weifur.dao.DataBaseHelper;
import huas.fur.weifur.model.Course;
import huas.fur.weifur.util.DensityUtil;

/**
 * 作者：Jacky
 * 邮箱：550997728@qq.com
 * 时间：2016/2/26 10:04
 */
public class CourseActivity extends Activity {
          private String week;
          private PopupWindow popupWindow;
          private LinearLayout spinnerLayout;
          private TextView tv_weekshow;
          private View i_selectbuilding;
          private ArrayList<String> list;
          private MyspinnerAdapter adapter;
          private LinearLayout layout;
          private ListView listView;
          private String[] classs;

          //课程页面的button引用，6行7列
          private int[][] lessons = {
               {R.id.lesson01, R.id.lesson02, R.id.lesson03, R.id.lesson04, R.id.lesson05, R.id.lesson06, R.id.lesson07},
               {R.id.lesson11, R.id.lesson12, R.id.lesson13, R.id.lesson14, R.id.lesson15, R.id.lesson16, R.id.lesson17},
               {R.id.lesson21, R.id.lesson22, R.id.lesson23, R.id.lesson24, R.id.lesson25, R.id.lesson26, R.id.lesson27},
               {R.id.lesson31, R.id.lesson32, R.id.lesson33, R.id.lesson34, R.id.lesson35, R.id.lesson36, R.id.lesson37},
               {R.id.lesson41, R.id.lesson42, R.id.lesson43, R.id.lesson44, R.id.lesson45, R.id.lesson46, R.id.lesson47},
               {R.id.lesson51, R.id.lesson52, R.id.lesson53, R.id.lesson54, R.id.lesson55, R.id.lesson56, R.id.lesson57},
               {R.id.lesson61, R.id.lesson62, R.id.lesson63, R.id.lesson64, R.id.lesson65, R.id.lesson66, R.id.lesson67},
          };

          private SQLiteDatabase db;
          private CourseQuery courseQuery;
          //某节课的背景图,用于随机获取
          private int[] bg = {R.drawable.kb1, R.drawable.kb2, R.drawable.kb3, R.drawable.kb4, R.drawable.kb5, R.drawable.kb6, R.drawable.kb7};
          //我的班级
          private String my_class;
          private List<Course> courses;
          private int px;

          @Override
          protected void onCreate(Bundle savedInstanceState) {
                    super.onCreate(savedInstanceState);
                    setContentView(R.layout.activity_course);
                    initValue();
                    initView();
                    intibar();
          }


          /**
           * 初始化变量
           */
          private void initValue() {
                    //获取班级信息
                    SharedPreferences sp = getSharedPreferences("config", 0);
                    my_class = sp.getString("class", "");
                    System.out.println(my_class);

                    //打开数据库
                    DataBaseHelper myDbHelper = new DataBaseHelper(this);
                    try {
                              myDbHelper.openDataBase();

                    } catch (Exception e) {
                              throw new Error("database open false");
                    }
                    //提前加载好数据库
                    db = myDbHelper.getWritableDatabase();
                    //初始化查询类
                    courseQuery = new CourseQuery(db, my_class);
          }


          /**
           * 初始化视图
           */
          @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
          private void initView() {
                    System.out.println("initView");
                    //获得查询到的课程数据
                    courses = null;
                    //获得课程查询结果
                    courses = courseQuery.getCourse();
                    week = courseQuery.getWeek();
                    //随机背景颜色
                    java.util.Random random = new java.util.Random();// 定义随机类
                    //初始化早晚自习
                    for (int i = 0; i < 4; i++) {
                              Button lesson1 = (Button) findViewById(lessons[0][i]);
                              lesson1.setText("早自习");
                              lesson1.setBackground(getResources().getDrawable(bg[random.nextInt(7)]));
                              Button lesson2 = (Button) findViewById(lessons[5][i]);
                              lesson2.setText("晚自习");
                              lesson2.setBackground(getResources().getDrawable(bg[random.nextInt(7)]));
                    }
                    Button lesson01 = (Button) findViewById(lessons[0][4]);
                    lesson01.setText("早自习");
                    lesson01.setBackground(getResources().getDrawable(bg[random.nextInt(7)]));
                    Button lesson02 = (Button) findViewById(lessons[5][6]);
                    lesson02.setText("晚自习");
                    lesson02.setBackground(getResources().getDrawable(bg[random.nextInt(7)]));
                    //课表显示
                    if (courses != null && courses.size() > 0) {
                              for (Course course : courses) {
                                        System.out.println(course.getClassRoom());
                                        //根据星期几更新列项UI视图
                                        if ("2".equals(course.getCountTime())) {//如果是两节连上
                                                  //转换为lessons数组对应的横坐标
                                                  int l_x = (Integer.parseInt(course.getStartTime()) / 2) + 1;
                                                  //转换为lessons数组对应的竖坐标
                                                  int l_y = Integer.parseInt(course.getDayOfWeek()) - 1;
                                                  Button lesson = (Button) findViewById(lessons[l_x][l_y]);
                                                  //分单双周显示
                                                  if ("1".equals(course.getEverWeek())) {
                                                            lesson.setText(course.getCourseName() + "\n" + "单周");
                                                            lesson.setBackground(getResources().getDrawable(bg[random.nextInt(7)]));

                                                  } else if ("2".equals(course.getEverWeek())) {
                                                            lesson.setText(course.getCourseName() + "\n" + "双周");
                                                            lesson.setBackground(getResources().getDrawable(bg[random.nextInt(7)]));
                                                  } else {
                                                            lesson.setText(course.getCourseName() + "\n" + course.getClassRoom());
                                                            lesson.setBackground(getResources().getDrawable(bg[random.nextInt(7)]));
                                                  }
                                        } else if ("3".equals(course.getCountTime())) {//如果是三节连上
                                                  //转换为lessons数组对应的横坐标
                                                  int l_x = (Integer.parseInt(course.getStartTime()) / 2) + 1;
                                                  //转换为lessons数组对应的竖坐标
                                                  int l_y = Integer.parseInt(course.getDayOfWeek()) - 1;

                                                  //进行dp于dx的转换
                                                  int px = DensityUtil.dip2px(this, 51);
                                                  //微芙蓉1.4 解决三节连上显示不正确问题
                                                  if (Integer.parseInt(course.getStartTime()) % 2 == 0) {
                                                            //找到控件
                                                            Button lesson1 = (Button) findViewById(lessons[l_x-1][l_y]);
                                                            Button lesson2 = (Button) findViewById(lessons[l_x][l_y]);
                                                            lesson1.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,px));
                                                            lesson2.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 3*px));
                                                            //更新UI
                                                            lesson2.setText(course.getCourseName() + "\n" + course.getClassRoom() + "\n" + "(三节连上)");
                                                            lesson2.setBackground(getResources().getDrawable(bg[random.nextInt(7)]));

                                                  } else {

                                                            //找到控件
                                                            Button lesson1 = (Button) findViewById(lessons[l_x][l_y]);
                                                            Button lesson2 = (Button) findViewById(lessons[l_x + 1][l_y]);
                                                            lesson1.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, px*3));
                                                            lesson2.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, px));
                                                            //更新UI
                                                            lesson1.setText(course.getCourseName() + "\n" + course.getClassRoom() + "\n" + "(三节连上)");
                                                            lesson1.setBackground(getResources().getDrawable(bg[random.nextInt(7)]));

                                                  }
                                        } else if ("4".equals(course.getCountTime())) {
                                                  //转换为lessons数组对应的横坐标
                                                  int l_x = (Integer.parseInt(course.getStartTime()) / 2) + 1;
                                                  //转换为lessons数组对应的竖坐标
                                                  int l_y = Integer.parseInt(course.getDayOfWeek()) - 1;
                                                  //控件高度
                                                  Button lesson1 = (Button) findViewById(lessons[l_x][l_y]);
                                                  //进行dp于dx的转换
                                                  int px = DensityUtil.dip2px(this, 51);
                                                  lesson1.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0));
                                                  Button lesson2 = (Button) findViewById(lessons[l_x + 1][l_y]);
                                                  lesson2.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, px * 4));
                                                  //更新UI
                                                  lesson2.setText(course.getCourseName() + "\n" + course.getClassRoom() + "\n" + "(四节连上)");
                                                  lesson2.setBackground(getResources().getDrawable(bg[random.nextInt(7)]));
                                        }
                              }
                    } else {
                              Toast.makeText(this, "sorry..该功能尚处测试阶段，目前仅限东院学生使用", Toast.LENGTH_SHORT).show();
                              System.out.println("查询结果为空");
                    }

          }


          public void intibar(){
                    //[1]找到控件
                    ImageView iv_back = (ImageView) findViewById(R.id.iv_back);
                    ImageView iv_search = (ImageView) findViewById(R.id.iv_search);
                    //[2]准备listview要显示的数据 去服务器取数据
                    //lv.setAdapter(new MyAdapter());
                    //TODO
                    //返回按钮响应
                    iv_back.setOnClickListener(new View.OnClickListener() {
                              @Override
                              public void onClick(View v) {
                                        finish();
                              }
                    });
                    //点击查询按钮
                    iv_search.setOnClickListener(new View.OnClickListener() {
                              @Override
                              public void onClick(View v) {
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
                                                  Toast.makeText(CourseActivity.this, "数据查询失败！", Toast.LENGTH_SHORT).show();
                                        }
                                        //设置班级
                                        AlertDialog.Builder builder = new AlertDialog.Builder(CourseActivity.this);
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
                                                            Toast.makeText(CourseActivity.this, "设置成功，您选择的班级为：" + classs[which], Toast.LENGTH_SHORT).show();
                                                            courseQuery.setClass(my_class);
                                                            //清理课表
                                                            clearView();
                                                            //重新现象课表信息
                                                            initView();

                                                  }
                                        });
                                        builder.show();

                              }
                    });
                    //查询页面


                    //自定义标题配置
                    tv_weekshow = (TextView) findViewById(R.id.text);
                    i_selectbuilding = findViewById(R.id.arrowbut);
                    // 实例化一个List,添加数据
                    list = new ArrayList<String>();
                    for (int i=1;i<20;i++){
                              list.add("第" + i + "周");

                    }
                    // 实例化一个适配器，list的数据作为Adapter的数据
                    adapter = new MyspinnerAdapter(this, list);
                    // 默认设置下拉框的标题为数据的第一个
                    tv_weekshow.setText("第" + week + "周");
                    spinnerLayout = (LinearLayout) findViewById(R.id.spinnerid);
                    // 点击右侧按钮，弹出下拉框
                    i_selectbuilding.setOnClickListener(new View.OnClickListener() {

                              @Override
                              public void onClick(View v) {

                                        showWindow(v);

                              }
                    });
          }


          //显示下拉选择列表
          public void showWindow(View v) {
                    // 找到布局文件
                    layout = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.mypinner_dropdown, null);
                    // 实例化listView
                    listView = (ListView) layout.findViewById(R.id.listView);
                    // 设置listView的适配器
                    listView.setAdapter(adapter);
                    // 实例化一个PopuWindow对象
                    popupWindow = new PopupWindow(v);
                    // 设置弹框的宽度为布局文件的宽
                    popupWindow.setWidth(spinnerLayout.getWidth());
                    // 高度随着内容变化
                    popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                    // 设置一个透明的背景，不然无法实现点击弹框外，弹框消失
                    popupWindow.setBackgroundDrawable(new BitmapDrawable());
                    // 设置点击弹框外部，弹框消失
                    popupWindow.setOutsideTouchable(true);
                    // 设置焦点
                    popupWindow.setFocusable(true);
                    // 设置所在布局
                    popupWindow.setContentView(layout);
                    // 设置弹框出现的位置，在v的正下方横轴偏移textview的宽度，为了对齐~纵轴不偏移
                    popupWindow.showAsDropDown(v, -tv_weekshow.getWidth(), 0);
                    // listView的item点击事件
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                              @Override
                              public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                                        // 设置所选的item作为下拉框的标题
                                        tv_weekshow.setText(list.get(arg2));
                                        //实现选择响应事件
                                        //设置星期 设置单双周
                                        courseQuery.setWeek(arg2);
                                        //清理课表
                                        clearView();
                                        //重新现象课表信息
                                        initView();
                                        // 弹框消失
                                        popupWindow.dismiss();
                                        popupWindow = null;
                              }
                    });
          }

          //清理视图列表
          private void clearView() {
                    for (int i = 0; i < 7; i++) {
                              for (int j = 0; j < 7; j++) {

                                        //进行dp于dx的转换
                                        px = DensityUtil.dip2px(this, 51);
                                        Button lesson1 = (Button) findViewById(lessons[i][j]);
                                        lesson1.setText("");
                                        if (i == 0) {
                                                  lesson1.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, px));
                                        } else {

                                                  lesson1.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, px * 2));
                                        }
                                        lesson1.setBackground(getResources().getDrawable(R.drawable.kb0));
                              }
                    }
          }


}
