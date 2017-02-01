package huas.fur.weifur.util;

import java.io.Serializable;

/**
 * 作者：Jacky
 * 邮箱：550997728@qq.com
 * 时间：2016/2/27 13:44
 * JavaBean成绩类
 */
public class MyScore implements Serializable{
          public String getMy_class() {
                    return my_class;
          }

          public void setMy_class(String my_class) {
                    this.my_class = my_class;
          }

          public String getMy_Score() {
                    return my_score;
          }

          public void setMy_score(String my_score) {
                    this.my_score = my_score;
          }

          private String my_class;
          private String my_score;
}

