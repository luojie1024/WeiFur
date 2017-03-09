package huas.fur.weifur.util;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * 作者：Jacky
 * 邮箱：550997728@qq.com
 * 时间：2016/3/8 17:21
 */
public class MyUtil {
          private String week;//第几周
          private String way;//星期几
          private String everyweek=null;//单双周  0全周 1单周 2双周

          public MyUtil(){
                    //获取系统星期 时间 ,计算出第几周
                    Calendar c = Calendar.getInstance();
                    c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
                    int month = c.get(Calendar.MONTH) + 1;// 获取当前月份
                    int day = c.get(Calendar.DAY_OF_MONTH);// 获取当前月份的日期号码
                    int mWay = c.get(Calendar.DAY_OF_WEEK);
                    //转换周次顺序 Android中每周是以星期天为第一天
                    if (--mWay == 0) mWay = 7;
                    way = mWay + "";
                    //计算学期周
                    switch (month) {
                              case 2:
                                        //周末有BUG ,周末为下周的第一天
                                        week = String.valueOf(day-25 / 7 + 1);
                                        break;
                              case 3:
                                        week = String.valueOf((46 + day) / 7 + 1);
                                        break;
                              case 4:
                                        week = String.valueOf((77 + day) / 7 + 1);
                                        break;
                              case 5:
                                        week = String.valueOf((107 + day) / 7 + 1);
                                        break;
                              case 6:
                                        week = String.valueOf((138 + day) / 7 + 1);
                                        break;
                              case 9:
                                        //周末有BUG ,周末为下周的第一天
                                        week = String.valueOf(day-5 / 7 + 1);
                                        break;
                              case 10:
                                        week = String.valueOf((26 + day) / 7 + 1);
                                        break;
                              case 11:
                                        week = String.valueOf((57 + day) / 7 + 1);
                                        break;
                              case 12:
                                        week = String.valueOf((87 + day) / 7 + 1);
                                        break;
                              case 1:
                                        week = String.valueOf((118 + day) / 7 + 1);
                                        break;
                    }
                    //TODO 判断单双周 ,可优化
                    if (Integer.parseInt(week) % 2 == 1) {
                              everyweek = "1";
                    } else {
                              everyweek = "2";
                    }
          }
          public String getWeek(){
                    return week;
          };


          public String getWay(){
                    return way;
          };


          public String getEveryWeek(){
                    return everyweek;
          };
}
