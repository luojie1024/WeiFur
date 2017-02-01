package huas.fur.weifur.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import huas.fur.weifur.R;
import huas.fur.weifur.model.Room;

/**
 * 作者：Jacky
 * 邮箱：550997728@qq.com
 * 时间：2016/3/5 13:01
 */
//创建教学楼空教室适配
public class FirstLaboratoryAdapter extends BaseAdapter {
          LayoutInflater inflater;
          Context context;
          List<Room> rooms = null;
          private Button b_1;
          private Button b_2;
          private Button b_3;
          private Button b_4;
          private Button b_5;
          private String room_name;
//          private String way;

          public FirstLaboratoryAdapter(Context context, List<Room> rooms) {
                    this.context = context;
                    inflater = LayoutInflater.from(context);
                    this.rooms = rooms;
//                    this.way=way;
          }

          @Override

          public int getCount() {
                    return 32;
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
                              view = inflater.inflate(R.layout.activity_classroom_item, null);
                              b_1 = (Button) view.findViewById(R.id.b_01);
                              b_2 = (Button) view.findViewById(R.id.b_02);
                              b_3 = (Button) view.findViewById(R.id.b_03);
                              b_4 = (Button) view.findViewById(R.id.b_04);
                              b_5 = (Button) view.findViewById(R.id.b_05);
                    } else {
                              view = convertView;
                              b_1 = (Button) view.findViewById(R.id.b_01);
                              b_2 = (Button) view.findViewById(R.id.b_02);
                              b_3 = (Button) view.findViewById(R.id.b_03);
                              b_4 = (Button) view.findViewById(R.id.b_04);
                              b_5 = (Button) view.findViewById(R.id.b_05);
                              //初始化控件
                              b_1.setText("空");
                              b_1.setBackground(context.getResources().getDrawable(R.drawable.classroom_false));
                              b_1.setTextColor(context.getResources().getColor(R.color.text_false_color));
                              b_2.setText("空");
                              b_2.setBackground(context.getResources().getDrawable(R.drawable.classroom_false));
                              b_2.setTextColor(context.getResources().getColor(R.color.text_false_color));
                              b_3.setText("空");
                              b_3.setBackground(context.getResources().getDrawable(R.drawable.classroom_false));
                              b_3.setTextColor(context.getResources().getColor(R.color.text_false_color));
                              b_4.setText("空");
                              b_4.setBackground(context.getResources().getDrawable(R.drawable.classroom_false));
                              b_4.setTextColor(context.getResources().getColor(R.color.text_false_color));
                              b_5.setText("空");
                              b_5.setBackground(context.getResources().getDrawable(R.drawable.classroom_false));
                              b_5.setTextColor(context.getResources().getColor(R.color.text_false_color));
                    }
                    //设置查询到的数据 并且更新UI
                    TextView tv_roomid = (TextView) view.findViewById(R.id.tv_roomid);
                    //TODO可优化
                    if (position < 7) {
                              room_name = "110" + (position + 1) % 8;
                              tv_roomid.setText(room_name);
                              //查询数据不为0，则更新UI
                              if (rooms != null && !rooms.isEmpty()) {
                                        //更新UI
                                        showUI();
                              }
                    } else if (position == 7) {
                              room_name = "1108";
                              tv_roomid.setText(room_name);
                              //查询数据不为0，则更新UI
                              if (rooms != null && !rooms.isEmpty()) {
                                        //更新UI
                                        showUI();
                              }
                    } else if (position < 15) {
                              room_name = "120" + (position + 1) % 8;
                              tv_roomid.setText(room_name);
                              //查询数据不为0，则更新UI
                              if (rooms != null && !rooms.isEmpty()) {
                                        //更新UI
                                        showUI();
                              }
                    } else if (position == 15) {
                              room_name = "1208";
                              tv_roomid.setText(room_name);
                              //查询数据不为0，则更新UI
                              if (rooms != null && !rooms.isEmpty()) {
                                        //更新UI
                                        showUI();
                              }
                    } else if (position < 23) {
                              room_name = "130" + (position + 1) % 8;
                              tv_roomid.setText(room_name);
                              //查询数据不为0，则更新UI
                              if (rooms != null && !rooms.isEmpty()) {
                                        //更新UI
                                        showUI();
                              }
                    } else if (position == 23) {
                              room_name = "1308";
                              tv_roomid.setText(room_name);
                              //查询数据不为0，则更新UI
                              if (rooms != null && !rooms.isEmpty()) {
                                        //更新UI
                                        showUI();
                              }
                    } else if (position < 31) {
                              room_name = "140" + (position + 1) % 8;
                              tv_roomid.setText(room_name);
                              //查询数据不为0，则更新UI
                              if (rooms != null && !rooms.isEmpty()) {
                                        //更新UI
                                        showUI();
                              }
                    } else if (position == 31) {
                              room_name = "1408";
                              tv_roomid.setText(room_name);
                              //查询数据不为0，则更新UI
                              if (rooms != null && !rooms.isEmpty()) {
                                        //更新UI
                                        showUI();
                              }
                    }
                    return view;
          }

          //初始化控件
          public void showUI() {
                    //晚自习占教室
//                    if ("1".equals(way)||"2".equals(way)||"3".equals(way)||"4".equals(way)||"7".equals(way)) {
//                              //更改显示字符
//                              b_5.setText("占");
//                              //设置背景颜色
//                              b_5.setBackground(context.getResources().getDrawable(R.drawable.classroom_true));
//                              //设置文字颜色为白色
//                              b_5.setTextColor(context.getResources().getColor(R.color.text_ture_color));
//                    }
                    for (Room room : rooms) {
                              //判断对应教室
                              System.out.println();
                              //如果是周 1 2 3 4 7则需要上晚自习，教室被占
                              if (room_name.equals(room.getClassRoom())) {
                                        //判断第几节课
                                        switch (room.getStartTime()) {
                                                  case 1:
                                                            //判断上几节课
                                                            if (2 == room.getCountTime()) {
                                                                      //更改显示字符
                                                                      System.out.println(room_name);
                                                                      b_1.setText("占");
                                                                      //设置背景颜色
                                                                      b_1.setBackground(context.getResources().getDrawable(R.drawable.classroom_true));
                                                                      //设置文字颜色为白色
                                                                      b_1.setTextColor(context.getResources().getColor(R.color.text_ture_color));
                                                            } else if (3 == room.getCountTime()||4 == room.getCountTime()) {//3 4节连上
                                                                      //更改显示字符
                                                                      b_1.setText("占");
                                                                      //设置背景颜色
                                                                      b_1.setBackground(context.getResources().getDrawable(R.drawable.classroom_true));
                                                                      //设置文字颜色为白色
                                                                      b_1.setTextColor(context.getResources().getColor(R.color.text_ture_color));
                                                                      //更改显示字符
                                                                      b_2.setText("占");
                                                                      //设置背景颜色
                                                                      b_2.setBackground(context.getResources().getDrawable(R.drawable.classroom_true));
                                                                      //设置文字颜色为白色
                                                                      b_2.setTextColor(context.getResources().getColor(R.color.text_ture_color));
                                                            }
                                                            break;
                                                  case 3:
                                                            //持续时间
                                                            if (2 == room.getCountTime()) {
                                                                      //更改显示字符
                                                                      b_2.setText("占");
                                                                      //设置背景颜色
                                                                      b_2.setBackground(context.getResources().getDrawable(R.drawable.classroom_true));
                                                                      //设置文字颜色为白色
                                                                      b_2.setTextColor(context.getResources().getColor(R.color.text_ture_color));
                                                            } else if (3 == room.getCountTime()||4 == room.getCountTime()) {//3 4节连上
                                                                      //更改显示字符
                                                                      b_2.setText("占");
                                                                      //设置背景颜色
                                                                      b_2.setBackground(context.getResources().getDrawable(R.drawable.classroom_true));
                                                                      //设置文字颜色为白色
                                                                      b_2.setTextColor(context.getResources().getColor(R.color.text_ture_color));
                                                                      //更改显示字符
                                                                      b_3.setText("占");
                                                                      //设置背景颜色
                                                                      b_3.setBackground(context.getResources().getDrawable(R.drawable.classroom_true));
                                                                      //设置文字颜色为白色
                                                                      b_3.setTextColor(context.getResources().getColor(R.color.text_ture_color));
                                                            }
                                                            break;
                                                  case 5:
                                                            //持续时间
                                                            if (2 == room.getCountTime()) {
                                                                      //更改显示字符
                                                                      b_3.setText("占");
                                                                      //设置背景颜色
                                                                      b_3.setBackground(context.getResources().getDrawable(R.drawable.classroom_true));
                                                                      //设置文字颜色为白色
                                                                      b_3.setTextColor(context.getResources().getColor(R.color.text_ture_color));
                                                            } else if (3 == room.getCountTime()||4 == room.getCountTime()) {//3 4节连上
                                                                      //更改显示字符
                                                                      b_3.setText("占");
                                                                      //设置背景颜色
                                                                      b_3.setBackground(context.getResources().getDrawable(R.drawable.classroom_true));
                                                                      //设置文字颜色为白色
                                                                      b_3.setTextColor(context.getResources().getColor(R.color.text_ture_color));
                                                                      //更改显示字符
                                                                      b_4.setText("占");
                                                                      //设置背景颜色
                                                                      b_4.setBackground(context.getResources().getDrawable(R.drawable.classroom_true));
                                                                      //设置文字颜色为白色
                                                                      b_4.setTextColor(context.getResources().getColor(R.color.text_ture_color));
                                                            }
                                                            break;
                                                  case 7:
                                                            //持续时间
                                                            //持续时间
                                                            if (2 == room.getCountTime()) {
                                                                      //更改显示字符
                                                                      b_4.setText("占");
                                                                      //设置背景颜色
                                                                      b_4.setBackground(context.getResources().getDrawable(R.drawable.classroom_true));
                                                                      //设置文字颜色为白色
                                                                      b_4.setTextColor(context.getResources().getColor(R.color.text_ture_color));
                                                            } else if (3 == room.getCountTime()||4 == room.getCountTime()) {//3 4节连上
                                                                      //更改显示字符
                                                                      b_4.setText("占");
                                                                      //设置背景颜色
                                                                      b_4.setBackground(context.getResources().getDrawable(R.drawable.classroom_true));
                                                                      //设置文字颜色为白色
                                                                      b_4.setTextColor(context.getResources().getColor(R.color.text_ture_color));
                                                                      //更改显示字符
                                                                      b_5.setText("占");
                                                                      //设置背景颜色
                                                                      b_5.setBackground(context.getResources().getDrawable(R.drawable.classroom_true));
                                                                      //设置文字颜色为白色
                                                                      b_5.setTextColor(context.getResources().getColor(R.color.text_ture_color));
                                                            }
                                                            break;
                                                  case 9:
                                                            //持续时间，晚上只有9节
                                                            if (2 == room.getCountTime()) {
                                                                      //更改显示字符
                                                                      b_5.setText("占");
                                                                      //设置背景颜色
                                                                      b_5.setBackground(context.getResources().getDrawable(R.drawable.classroom_true));
                                                                      //设置文字颜色为白色
                                                                      b_5.setTextColor(context.getResources().getColor(R.color.text_ture_color));
                                                            }
                                                            break;
                                        }
                              }
                    }
          }
}
