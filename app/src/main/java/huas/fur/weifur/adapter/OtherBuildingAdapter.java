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
 * 时间：2016/3/5 13:13
 */
public class OtherBuildingAdapter extends BaseAdapter {
          LayoutInflater inflater;
          Context context;
          List<Room> rooms = null;
          private Button b_1;
          private Button b_2;
          private Button b_3;
          private Button b_4;
          private Button b_5;
          private String room_name;
          public OtherBuildingAdapter(Context context,List<Room> rooms) {
                    this.context = context;
                    inflater = LayoutInflater.from(context);
                    this.context = context;
                    inflater = LayoutInflater.from(context);
                    this.rooms = rooms;
          }
          @Override
          public int getCount() {
                    return 17;
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
                    switch (position) {
                              case 0:
                                        room_name="T图南";
                                        tv_roomid.setText(room_name);
                                        //查询数据不为0，则更新UI
                                        if (rooms != null && !rooms.isEmpty()) {
                                                  //更新UI
                                                  showUI();
                                        }
                                        break;
                              case 1:
                                        room_name="T图北";
                                        tv_roomid.setText(room_name);
                                        //查询数据不为0，则更新UI
                                        if (rooms != null && !rooms.isEmpty()) {
                                                  //更新UI
                                                  showUI();
                                        }
                                        break;
                              case 2:
                                        room_name="图书馆多媒体";
                                        tv_roomid.setText(room_name);
                                        //查询数据不为0，则更新UI
                                        if (rooms != null && !rooms.isEmpty()) {
                                                  //更新UI
                                                  showUI();
                                        }
                                        break;
                              case 3:
                                        room_name="琴房";
                                        tv_roomid.setText(room_name);
                                        //查询数据不为0，则更新UI
                                        if (rooms != null && !rooms.isEmpty()) {
                                                  //更新UI
                                                  showUI();
                                        }
                                        break;
                              case 4:
                                        room_name="体操房";
                                        tv_roomid.setText(room_name);
                                        //查询数据不为0，则更新UI
                                        if (rooms != null && !rooms.isEmpty()) {
                                                  //更新UI
                                                  showUI();
                                        }
                                        break;
                              case 5:
                                        room_name="1舞蹈房";
                                        tv_roomid.setText(room_name);
                                        //查询数据不为0，则更新UI
                                        if (rooms != null && !rooms.isEmpty()) {
                                                  //更新UI
                                                  showUI();
                                        }
                                        break;
                              case 6:
                                        room_name="2舞蹈房";
                                        tv_roomid.setText(room_name);
                                        //查询数据不为0，则更新UI
                                        if (rooms != null && !rooms.isEmpty()) {
                                                  //更新UI
                                                  showUI();
                                        }
                                        break;
                              case 7:
                                        room_name="田径场";
                                        tv_roomid.setText(room_name);
                                        //查询数据不为0，则更新UI
                                        if (rooms != null && !rooms.isEmpty()) {
                                                  //更新UI
                                                  showUI();
                                        }
                                        break;
                              case 8:
                                        room_name="足球场";
                                        tv_roomid.setText(room_name);
                                        //查询数据不为0，则更新UI
                                        if (rooms != null && !rooms.isEmpty()) {
                                                  //更新UI
                                                  showUI();
                                        }
                                        break;
                              case 9:
                                        room_name="体育馆";
                                        tv_roomid.setText(room_name);
                                        //查询数据不为0，则更新UI
                                        if (rooms != null && !rooms.isEmpty()) {
                                                  //更新UI
                                                  showUI();
                                        }
                                        break;
                              case 10:
                                        room_name="1制作室";
                                        tv_roomid.setText(room_name);
                                        //查询数据不为0，则更新UI
                                        if (rooms != null && !rooms.isEmpty()) {
                                                  //更新UI
                                                  showUI();
                                        }
                                        break;
                              case 11:
                                        room_name="2制作室";
                                        tv_roomid.setText(room_name);
                                        //查询数据不为0，则更新UI
                                        if (rooms != null && !rooms.isEmpty()) {
                                                  //更新UI
                                                  showUI();
                                        }
                                        break;
                              case 12:
                                        room_name="3制作室";
                                        tv_roomid.setText(room_name);
                                        //查询数据不为0，则更新UI
                                        if (rooms != null && !rooms.isEmpty()) {
                                                  //更新UI
                                                  showUI();
                                        }
                                        break;
                              case 13:
                                        room_name="4制作室";
                                        tv_roomid.setText(room_name);
                                        //查询数据不为0，则更新UI
                                        if (rooms != null && !rooms.isEmpty()) {
                                                  //更新UI
                                                  showUI();
                                        }
                                        break;
                              case 14:
                                        room_name="1画室";
                                        tv_roomid.setText(room_name);
                                        //查询数据不为0，则更新UI
                                        if (rooms != null && !rooms.isEmpty()) {
                                                  //更新UI
                                                  showUI();
                                        }
                                        break;
                              case 15:
                                        room_name="2画室";
                                        tv_roomid.setText(room_name);
                                        //查询数据不为0，则更新UI
                                        if (rooms != null && !rooms.isEmpty()) {
                                                  //更新UI
                                                  showUI();
                                        }
                                        break;
                              case 16:
                                        room_name="3画室";
                                        tv_roomid.setText(room_name);
                                        //查询数据不为0，则更新UI
                                        if (rooms != null && !rooms.isEmpty()) {
                                                  //更新UI
                                                  showUI();
                                        }
                                        break;
                    }
                    return view;
          }
          //初始化控件
          public void showUI() {
                    for (Room room : rooms) {
                              //判断对应教室
                              System.out.println();
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
                                                            } else if (3 == room.getCountTime() || 4 == room.getCountTime()) {//3 4节连上
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
                                                            } else if (3 == room.getCountTime() || 4 == room.getCountTime()) {//3 4节连上
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
                                                            } else if (3 == room.getCountTime() || 4 == room.getCountTime()) {//3 4节连上
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
                                                            } else if (3 == room.getCountTime() || 4 == room.getCountTime()) {//3 4节连上
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
