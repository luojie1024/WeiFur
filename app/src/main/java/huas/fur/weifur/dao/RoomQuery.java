package huas.fur.weifur.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import huas.fur.weifur.model.Room;
import huas.fur.weifur.util.MyUtil;

/**
 * 作者：Jacky
 * 邮箱：550997728@qq.com
 * 时间：2016/3/3 20:13
 */
public class RoomQuery {
          private SQLiteDatabase db;//数据库
          private String week;//第几周
          private String way;//星期几
          private String everyweek=null;


          public RoomQuery(SQLiteDatabase db) {
                    //获取db
                    this.db = db;
                    //获取系统星期 时间 ,计算出第几周
                    MyUtil myUtil = new MyUtil();
                    everyweek=myUtil.getEveryWeek();
                    week=myUtil.getWeek();
                    way=myUtil.getWay();
                    System.out.println("当前第" + week + "周" + "----" + everyweek+ "星期" + way);
          }

          //查询教学楼空教室
          public List<Room> TeachingBuilding() {
                    List<Room> rooms= new ArrayList();
                    String sql="select distinct ClassRoom,StartTime,CountTime from Course where DayOfWeek=" + way +
                         " and (StartWeek <=" + week + " and " + week + "<=EndWeek) and (ClassRoom like \"T_\" or ClassRoom like \"_0_\"  or ClassRoom like \"_1_\") and (EveryWeek=0 or EveryWeek=" + everyweek+") ORDER BY ClassRoom,StartTime";
                    System.out.println(sql);
                    Cursor cursor = db.rawQuery(sql, null);
                    if (cursor != null && cursor.getCount() > 0) {
                              while (cursor.moveToNext()) {
                                        Room room = new Room();
                                        //columnIndex打表列的索引
                                        room.setClassRoom(cursor.getString(0));
                                        room.setStartTime(cursor.getInt(1));
                                        room.setCountTime(cursor.getInt(2));
                                        room.setCourseName(null);
                                        room.setTeacher(null);
                                        rooms.add(room);
                              }
                              //db.close();
                    } else {
                              System.out.println("查询数据为空");
                              //db.close();
                              return null;
                    }
                    return rooms;
          }


          //查询一实验楼空教室
          public List<Room> FirstLaboratoryBuilding() {
                    List<Room> rooms= new ArrayList();
                    String sql="select distinct ClassRoom,StartTime,CountTime from Course where DayOfWeek=" + way +
                         " and (StartWeek <=" + week + " and " + week + "<=EndWeek) and (ClassRoom like \"1_0_\") and (EveryWeek=0 or EveryWeek=" + everyweek+") ORDER BY ClassRoom,StartTime";
                    System.out.println(sql);
                    Cursor cursor = db.rawQuery(sql, null);
                    if (cursor != null && cursor.getCount() > 0) {
                              while (cursor.moveToNext()) {
                                        Room room = new Room();
                                        //columnIndex打表列的索引
                                        room.setClassRoom(cursor.getString(0));
                                        room.setStartTime(cursor.getInt(1));
                                        room.setCountTime(cursor.getInt(2));
                                        room.setCourseName(null);
                                        room.setTeacher(null);
                                        rooms.add(room);
                              }
                              //db.close();
                    } else {
                              System.out.println("查询数据为空");
                             // db.close();
                              return null;
                    }
                    return rooms;
          }


          //查询二实验楼空教室
          public List<Room> SecondLaboratoryBuilding() {
                    List<Room> rooms= new ArrayList();
                    String sql="select distinct ClassRoom,StartTime,CountTime from Course where DayOfWeek=" + way +
                         " and (StartWeek <=" + week + " and " + week + "<=EndWeek) and (ClassRoom like \"2_0_\" or ClassRoom like \"_语音室\") and (EveryWeek=0 or EveryWeek=" + everyweek+") ORDER BY ClassRoom,StartTime";
                    System.out.println(sql);
                    Cursor cursor = db.rawQuery(sql, null);
                    if (cursor != null && cursor.getCount() > 0) {
                              while (cursor.moveToNext()) {
                                        Room room = new Room();
                                        //columnIndex打表列的索引
                                        room.setClassRoom(cursor.getString(0));
                                        room.setStartTime(cursor.getInt(1));
                                        room.setCountTime(cursor.getInt(2));
                                        room.setCourseName(null);
                                        room.setTeacher(null);
                                        rooms.add(room);
                              }
                              //db.close();
                    } else {
                              System.out.println("查询数据为空");
                              //db.close();
                              return null;
                    }
                    return rooms;
          }


          //查询其他空教室
          public List<Room> other() {
                    List<Room> rooms= new ArrayList();
                    String sql="select distinct ClassRoom,StartTime,CountTime from Course where DayOfWeek=" + way +
                         " and (StartWeek <=" + week + " and " + week + "<=EndWeek) " +
                         "and (ClassRoom like \"T图_\" or ClassRoom like \"_制作室\"  or ClassRoom like \"_画室\" or ClassRoom like \"_舞蹈房\" or ClassRoom =\"体操房\" or ClassRoom =\"体育馆\" or ClassRoom=\"足球场\" or ClassRoom=\"田径场\" or ClassRoom=\"琴房\") and (EveryWeek=0 or EveryWeek=" + everyweek+") ORDER BY ClassRoom,StartTime";
                    System.out.println(sql);
                    Cursor cursor = db.rawQuery(sql, null);
                    if (cursor != null && cursor.getCount() > 0) {
                              while (cursor.moveToNext()) {
                                        Room room = new Room();
                                        //columnIndex打表列的索引
                                        room.setClassRoom(cursor.getString(0));
                                        room.setStartTime(cursor.getInt(1));
                                        room.setCountTime(cursor.getInt(2));
                                        room.setCourseName(null);
                                        room.setTeacher(null);
                                        rooms.add(room);
                              }
                              //db.close();
                    } else {
                              System.out.println("查询数据为空");
                              //db.close();
                              return null;
                    }
                    return rooms;
          }

          //设置星期几
          public void setWay(String way){
                    this.way=way;
                    System.out.println(way);
          }

          //设置星期几
          public String getWay(){
                    return way;
                    //System.out.println(way);
          }
}
