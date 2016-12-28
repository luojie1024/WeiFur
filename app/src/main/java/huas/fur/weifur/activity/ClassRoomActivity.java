package huas.fur.weifur.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import huas.fur.weifur.R;
import huas.fur.weifur.adapter.FirstLaboratoryAdapter;
import huas.fur.weifur.adapter.MyspinnerAdapter;
import huas.fur.weifur.adapter.OtherBuildingAdapter;
import huas.fur.weifur.adapter.SecondLaboratoryAdapter;
import huas.fur.weifur.adapter.TeachingBuildingAdapter;
import huas.fur.weifur.dao.DataBaseHelper;
import huas.fur.weifur.dao.RoomQuery;
import huas.fur.weifur.model.Room;
import huas.fur.weifur.util.MyUtil;

/**
 * 作者：Jacky
 * 邮箱：550997728@qq.com
 * 时间：2016/2/28 19:29
 */
public class ClassRoomActivity extends Activity {
          private ListView lv;
          private ArrayList<String> list;
          private View i_selectbuilding;
          private TextView tv_building; //显示当前查询的教学楼
          private MyspinnerAdapter adapter;
          private LinearLayout spinnerLayout;
          private PopupWindow popupWindow;
          private LinearLayout layout;
          private ListView listView;
          private SQLiteDatabase db;
          private RoomQuery roomQuery;
          private List<Room> teaching_cursor = null;//查询到的空教室数据
          private List<Room> first_cursor = null;
          private List<Room> second_cursor = null;
          private List<Room> other_cursor = null;
          private String way;
          private String[] set_way;//查询星期几
          private int my_arg;


          @Override
          protected void onCreate(Bundle savedInstanceState) {
                    super.onCreate(savedInstanceState);
                    setContentView(R.layout.activity_classroom);
                    MyUtil myUtil = new MyUtil();
                    way = myUtil.getWay();
                    Toast.makeText(getApplicationContext(),"当前第"+myUtil.getWeek()+"周 "+"星期"+way,Toast.LENGTH_LONG).show();
                    initValue();

          }

          /**
           * 初始化变量
           */
          private void initValue() {
                    //[1]找到控件
                    lv = (ListView) findViewById(R.id.lv_classroom);
                    ImageView iv_back = (ImageView) findViewById(R.id.iv_back);
                    ImageView iv_search = (ImageView) findViewById(R.id.iv_search);
                    //[2]准备listview要显示的数据 去服务器取数据
                    //lv.setAdapter(new MyAdapter());

                    //返回按钮响应
                    //TODO
                    iv_back.setOnClickListener(new View.OnClickListener() {
                              @Override
                              public void onClick(View v) {
                                        finish();
                              }
                    });

                    //查询按钮响应事件
                    iv_search.setOnClickListener(new View.OnClickListener() {
                              @Override
                              public void onClick(View v) {
                                        //查询空教室
                                        set_way = new String[]{"星期一","星期二","星期三","星期四","星期五","星期六","星期天"};
                                        AlertDialog.Builder builder = new AlertDialog.Builder(ClassRoomActivity.this);
                                        builder.setTitle("请选择所要查询的星期！");
                                        //    设置一个下拉的列表选择项
                                        builder.setItems(set_way, new DialogInterface.OnClickListener() {
                                                  @Override
                                                  public void onClick(DialogInterface dialog, int which) {
                                                            //my_class = classs[which];
                                                            Toast.makeText(ClassRoomActivity.this, "设置成功，您选择的班级为：" + set_way[which], Toast.LENGTH_SHORT).show();
                                                            //设置星期
                                                            roomQuery.setWay(++which + "");
                                                            //清理数据
                                                            teaching_cursor = null;//查询到的空教室数据
                                                            first_cursor = null;
                                                            second_cursor = null;
                                                            other_cursor = null;
                                                            //设置适配，展现结果
                                                            initListData(my_arg);
                                                  }
                                        });
                                        builder.show();
                              }
                    });


                    //自定义标题配置
                    tv_building = (TextView) findViewById(R.id.text);
                    i_selectbuilding = findViewById(R.id.arrowbut);
                    // 实例化一个List,添加数据
                    list = new ArrayList<String>();
                    list.add("一实验楼");
                    list.add("二实验楼");
                    list.add("教学楼");
                    list.add("其他");
                    // 实例化一个适配器，list的数据作为Adapter的数据
                    adapter = new MyspinnerAdapter(this, list);
                    // 默认设置下拉框的标题为数据的第三个,标志为2
                    tv_building.setText((CharSequence) adapter.getItem(2));
                    my_arg=2;
                    spinnerLayout = (LinearLayout) findViewById(R.id.spinnerid);
                    // 点击右侧按钮，弹出下拉框
                    i_selectbuilding.setOnClickListener(new View.OnClickListener() {

                              @Override
                              public void onClick(View v) {

                                        showWindow(v);

                              }
                    });
//                    //数据库 拷贝(第一次) 读取(第二次)
//                    DataBaseHelper myDbHelper = new DataBaseHelper(this);
//                    try {
//                              myDbHelper.createDataBase();
//                              myDbHelper.openDataBase();
//
//                    } catch (Exception e) {
//                              throw new Error("Unable to create database");
//                    }

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
                    roomQuery = new RoomQuery(db);
                    //初始化显示教学楼空教室情况
                    initListData(2);
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
                    popupWindow.showAsDropDown(v, -tv_building.getWidth(), 0);
                    // listView的item点击事件
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                              @Override
                              public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                                        // 设置所选的item作为下拉框的标题
                                        tv_building.setText(list.get(arg2));
                                        //设置当前选项
                                        my_arg = arg2;
                                        //实现选择响应事件
                                        initListData(arg2);
                                        // 弹框消失
                                        popupWindow.dismiss();
                                        popupWindow = null;
                              }
                    });

          }

          //准备要显示的空教室数据
          private void initListData(int arg2) {
                    switch (arg2) {
                              //选择的是第一实验楼
                              case 0:
                                        //查询信息为空 就进行查询操作
                                        if (first_cursor != null) {//多次点击 沿用上次查询的结果 不再访问数据库
                                                  lv.setAdapter(new FirstLaboratoryAdapter(getApplicationContext(), first_cursor));
                                                  break;
                                        } else {//第一次查询
                                                  first_cursor = roomQuery.FirstLaboratoryBuilding();
                                                  if (first_cursor == null) {
                                                            //没有查询到相应的信息
                                                            System.out.println("没有查询到信息!");
                                                            lv.setAdapter(new FirstLaboratoryAdapter(getApplicationContext(), null));
                                                  } else {
                                                            //展示查询的信息
                                                            lv.setAdapter(new FirstLaboratoryAdapter(getApplicationContext(), first_cursor));
                                                  }
                                        }
                                        break;

                              //选择的是第二实验楼
                              case 1:
                                        if (second_cursor != null) {//多次点击 沿用上次查询的结果 不再访问数据库
                                                  lv.setAdapter(new SecondLaboratoryAdapter(getApplicationContext(), second_cursor));
                                                  break;
                                        } else {//第一次查询
                                                  second_cursor = roomQuery.SecondLaboratoryBuilding();
                                                  if (second_cursor == null) {
                                                            //没有查询到相应的信息
                                                            System.out.println("没有查询到信息!");
                                                            lv.setAdapter(new SecondLaboratoryAdapter(getApplicationContext(), null));
                                                  } else {
                                                            //展示查询的信息
                                                            lv.setAdapter(new SecondLaboratoryAdapter(getApplicationContext(), second_cursor));
                                                  }
                                        }
                                        break;

                              //选择的教学楼
                              case 2:
                                        //查询信息为空 就进行查询操作
                                        if (teaching_cursor != null) {//多次点击 沿用上次查询的结果 不再访问数据库
                                                  lv.setAdapter(new TeachingBuildingAdapter(getApplicationContext(), teaching_cursor,roomQuery.getWay()));
                                                  break;
                                        } else {//第一次查询
                                                  teaching_cursor = roomQuery.TeachingBuilding();
                                                  if (teaching_cursor == null) {
                                                            //没有查询到相应的信息
                                                            System.out.println("没有查询到信息!");
                                                            lv.setAdapter(new TeachingBuildingAdapter(getApplicationContext(), null,roomQuery.getWay()));
                                                  } else {
                                                            //展示查询的信息
                                                            lv.setAdapter(new TeachingBuildingAdapter(getApplicationContext(), teaching_cursor,roomQuery.getWay()));
                                                  }
                                        }
                                        break;
                              //选择的是其他
                              case 3:

                                        //查询信息为空 就进行查询操作
                                        if (other_cursor != null) {//多次点击 沿用上次查询的结果 不再访问数据库
                                                  lv.setAdapter(new OtherBuildingAdapter(getApplicationContext(), other_cursor));
                                                  break;
                                        } else {//第一次查询
                                                  other_cursor = roomQuery.other();
                                                  if (other_cursor == null) {
                                                            //没有查询到相应的信息
                                                            System.out.println("没有查询到信息!");
                                                            lv.setAdapter(new OtherBuildingAdapter(getApplicationContext(), null));
                                                  } else {
                                                            //展示查询的信息
                                                            lv.setAdapter(new OtherBuildingAdapter(getApplicationContext(), other_cursor));
                                                  }
                                        }
                                        break;

                    }
          }


}
