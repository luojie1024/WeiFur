package huas.fur.weifur.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import huas.fur.weifur.model.Course;
import huas.fur.weifur.util.MyUtil;

/**
 * 作者：Jacky
 * 邮箱：550997728@qq.com
 * 时间：2016/3/8 17:16
 */
public class CourseQuery {
          private SQLiteDatabase db;//数据库
          private String week;//第几周
          private String way;//星期几
          private String everyWeek = null;
          private String my_class; //我的班级

          public CourseQuery(SQLiteDatabase db,String my_class){
                    //获取db
                    this.db = db;
                    //获取系统星期 时间 ,计算出第几周
                    MyUtil myUtil = new MyUtil();
                    everyWeek=myUtil.getEveryWeek();
                    week=myUtil.getWeek();
                    way=myUtil.getWay();
                    //获取班级
                    this.my_class=my_class;
          }
          
          //课表查询操作
          public List<Course> getCourse(){
                    //定义查询结果集
                    List<Course> courses = new ArrayList();
                    //课表查询的逻辑
                    String sql="select CourseName,ClassRoom,StartTime,CountTime,Teacher,EveryWeek,DayOfWeek from Course where Class=\""+my_class+"\" and "+week+">=StartWeek and "+week+"<=EndWeek and (EveryWeek=0 or EveryWeek="+everyWeek+") ORDER BY DayOfWeek,StartTime";
                    Cursor cursor = db.rawQuery(sql, null);
                    System.out.println(sql);
                    //封装查询到的数据
                    if (cursor != null && cursor.getCount() > 0) {
                              while (cursor.moveToNext()) {
                                        Course course = new Course();
                                        //columnIndex打表列的索引
                                        course.setCourseName(cursor.getString(0));
                                        course.setClassRoom(cursor.getString(1));
                                        course.setStartTime(cursor.getString(2));
                                        course.setCountTime(cursor.getString(3));
                                        course.setTeacher(cursor.getString(4));
                                        course.setEverWeek(cursor.getString(5));
                                        course.setDayOfWeek(cursor.getString(6));
                                        courses.add(course);
                              }
                    } else {
                              System.out.println("查询数据为空");
                              return null;
                    }
                    return courses;
          }

          public void setWeek(int week){
                    int i=week+1;
                    //星期设置
                    this.week=i+"";
                    //单双周设置
                    if (i% 2 == 1) {
                              this.everyWeek = "1";
                    } else {
                              this.everyWeek = "2";
                    }
                    System.out.println(this.week);
          }

          public String getWeek(){
                    return this.week;
          }

          public void setClass(String my_class){
                    this.my_class=my_class;
          }
}
