package huas.fur.weifur.model;

/**
 * 作者：Jacky
 * 邮箱：550997728@qq.com
 * 时间：2016/3/8 17:10
 */
public class Course {
          private String courseName;//课程名
          private String startTime;//开始时间
          private String countTime;//持续时间
          private String classRoom;//教室
          private String teacher;//老师
          private String everWeek;//单双周
          private String dayOfWeek;//星期几

          public String getEverWeek() {
                    return everWeek;
          }

          public void setEverWeek(String everWeek) {
                    this.everWeek = everWeek;
          }



          public String getClassRoom() {
                    return classRoom;
          }

          public void setClassRoom(String classRoom) {
                    this.classRoom = classRoom;
          }

          public String getCountTime() {
                    return countTime;
          }

          public void setCountTime(String countTime) {
                    this.countTime = countTime;
          }

          public String getCourseName() {
                    return courseName;
          }

          public void setCourseName(String courseName) {
                    this.courseName = courseName;
          }

          public String getDayOfWeek() {
                    return dayOfWeek;
          }

          public void setDayOfWeek(String dayOfWeek) {
                    this.dayOfWeek = dayOfWeek;
          }

          public String getStartTime() {
                    return startTime;
          }

          public void setStartTime(String startTime) {
                    this.startTime = startTime;
          }

          public String getTeacher() {
                    return teacher;
          }

          public void setTeacher(String teacher) {
                    this.teacher = teacher;
          }

}
