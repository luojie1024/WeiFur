package huas.fur.weifur.util;

import org.jsoup.Jsoup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者：Jacky
 * 邮箱：550997728@qq.com
 * 时间：2016/2/26 11:16
 * 查询教务系统成绩的Util
 */
public class HttpScoreUtil {

          private String cookie = null;
          private String sel_xn = null;
          private String sel_xq = null;

          //构造工具类 传入需要查询的 学年 和 学期
          public HttpScoreUtil(String xn, String xq) {
                    sel_xn = xn;
                    sel_xq = xq;
          }


          public List<MyScore> getQuery() {
                    List<MyScore> scores = null;
                    System.out.println(cookie+"---"+sel_xn+"---"+sel_xq);
                    try {
                              //FilterHtml Jsonp = new FilterHtml();
                              //String chookie = getCookies();
                              URL url = new URL(
                                   "http://www.huas.cn:91/jwweb/xscj/Stu_MyScore_rpt.aspx");
                              /**
                               * 此处的urlConnection对象实际上是根据URL的 请求协议
                               * (此处是http)生成的URLConnection类的子类HttpURLConnection
                               * 故此处最好将其转化 为HttpURLConnection类型的对象
                               * 以便用到HttpURLConnection更多的API
                               */
                              HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                              //设置请求时长
                              conn.setConnectTimeout(5000);
                              // 设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在
                              // http正文内，因此需要设为true, 默认情况下是false;
                              conn.setDoOutput(true);
                              // 设置是否从httpUrlConnection读入，默认情况下是true
                              conn.setDoInput(true);
                              // 设定请求的方法为"POST"，默认是GET
                              conn.setRequestMethod("POST");
                              // 设定请求报文头如:Cookie 长度 等等
                              conn.setRequestProperty("Cookie", cookie);
                              //设置请求正文
                              OutputStream os = conn.getOutputStream();
                              String param = new String();
                              param = "sel_xn=" + sel_xn + "&sel_xq=" + sel_xq + "&SJ=" + "1"
                                   + "&SelXNXQ=" + "2" + "&zfx_flag=" + "0" + "&zfx=" + "0";
                              os.write(param.getBytes());
                              //获取返回码
                              int code = conn.getResponseCode();
                              if (code == 200) {
                                        String result;
                                        InputStream in = conn.getInputStream();
                                        result = streamTools(in);
                                        scores = filterHtml(result);
                                        conn.disconnect();
                              }
                    } catch (Exception e) {
                              e.printStackTrace();
                    }
                    return scores;
          }


          //获取cookie 联网操作需要开辟子线程 cookie存储在成员变量中
          public void getCookies(String username,String password) {
                    try {
                              URL url = new URL(
                                   "http://www.huas.cn:91/jwweb/_data/index_LOGIN.aspx");
                              HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                              conn.setConnectTimeout(5000);
                              conn.setRequestMethod("POST");
                              conn.setDoOutput(true);
                              conn.setDoInput(true);
                              conn.setUseCaches(false);
                              OutputStream os = conn.getOutputStream();
                              String param;
                              param = "Sel_Type=" + "STU" + "&UserID=" + username
                                   + "&PassWord=" + password;
                              os.write(param.getBytes());
                              conn.connect();
                              int code = conn.getResponseCode();
                              if (code == 200) {
                                        String responseCookie = conn.getHeaderField("Set-Cookie");// 取到所用的Cookie
                                        cookie = responseCookie.substring(0,
                                             responseCookie.indexOf(";"));
                                        conn.disconnect();
                              }
                    } catch (Exception e) {
                              e.printStackTrace();
                    }
          }


          //过滤解析获取的Html代码 , 提取出课程 成绩等信息
          public List<MyScore> filterHtml(String source) {
                    List<MyScore> scores = new ArrayList<MyScore>();
                    StringBuffer sff = new StringBuffer();
                    String score[];
                    int i = 0, j = 0;
                    String html = source;
                    // 把HTML代码加载到doc中
                    org.jsoup.nodes.Document doc = Jsoup.parse(html);
                    // 这是课程名，因为课程名的HTML标签事<td width=23% align=left>
                    //然后我发现width=23%是这个标签特有的，所以我就把它给提出来了
                    org.jsoup.select.Elements links_class = ((org.jsoup.nodes.Element) doc)
                         .select("td[width=23%][align=left]");
                    // 这是分数，原因同上
                    org.jsoup.select.Elements links_grade = ((org.jsoup.nodes.Element) doc)
                         .select("td[width=5%][align=right]");

                    score = new String[links_grade.size()];
                    for (org.jsoup.nodes.Element link_grade : links_grade) {
                              score[i++] = link_grade.text();
                    }
                    for (org.jsoup.nodes.Element link : links_class) {
                              MyScore my_score = new MyScore();
                              my_score.setMy_class(((org.jsoup.nodes.Element) link).text());
                              my_score.setMy_score(score[j]);
                              scores.add(my_score);
                              // 这里之所以+2是因为分数的标签是<td width=5%
                              // align=right>，而学分也是这样的标签，所以我就每提取一次分数标签跳过一次学分标签
                              j = j + 2;
                    }
                    return scores;
          }


          //把一个inputStream装换成一个String
          public String streamTools(InputStream in) throws IOException {
                    //解决乱码问题
                    BufferedReader bufr = new BufferedReader(new InputStreamReader(in, "GBK"));
                    StringBuffer buffer = new StringBuffer();
                    String line = "";
                    while ((line = bufr.readLine()) != null){
                              buffer.append(line);
                    }
                    return buffer.toString();
          }

}
