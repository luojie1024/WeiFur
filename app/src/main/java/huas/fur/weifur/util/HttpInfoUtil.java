package huas.fur.weifur.util;

import org.jsoup.Jsoup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 作者：Jacky
 * 邮箱：550997728@qq.com
 * 时间：2016/3/13 09:58
 */
public class HttpInfoUtil {
          private String cookie;
          //private Student student;
          private String [] str=new String[]{"","",""};
          public HttpInfoUtil(String cookie){
                    this.cookie=cookie;
          }

          public String [] getInfo() {
                    try {
                              URL url = new URL("http://www.huas.cn:91/jwweb/xsxj/Stu_MyInfo_RPT.aspx");
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
                              conn.setRequestMethod("GET");
                              // 设定请求报文头如:Cookie 长度 等等
                              conn.setRequestProperty("Cookie", cookie);
                              //设置请求正文
                              //获取返回码
                              int code = conn.getResponseCode();
                              if (code == 200) {
                                        String result;
                                        InputStream in = conn.getInputStream();
                                        result = streamTools(in);
                                        System.out.println(result);
                                        conn.disconnect();
                                        filterHtml(result);

                              }
                    } catch (Exception e) {
                              e.printStackTrace();
                    }
                    return str;

          }

          public void filterHtml(String source) {
                    StringBuffer sff = new StringBuffer();
                    int i = 0, j = 0;
                    String html = source;
                    org.jsoup.nodes.Document doc = Jsoup.parse(html);   //把HTML代码加载到doc中
                    org.jsoup.select.Elements links_class = ((org.jsoup.nodes.Element) doc).select("td");
                    //获取姓名
                    System.out.println(((org.jsoup.nodes.Element) links_class.get(4)).text());
                    //student.setMy_Name(((org.jsoup.nodes.Element) links_class.get(4)).text());
                    str[0]=((org.jsoup.nodes.Element) links_class.get(4)).text();
                    //获取身份证号
                    System.out.println(((org.jsoup.nodes.Element) links_class.get(13)).text());
                    //student.setMy_ID(((org.jsoup.nodes.Element) links_class.get(13)).text());
                    str[1]=((org.jsoup.nodes.Element) links_class.get(13)).text();
                    //获取班级
                    System.out.println(((org.jsoup.nodes.Element) links_class.get(113)).text());
                    //student.setMy_Class(((org.jsoup.nodes.Element) links_class.get(113)).text());
                    str[2]=((org.jsoup.nodes.Element) links_class.get(113)).text();
          }

          //把一个inputStream装换成一个String
          public static String streamTools(InputStream in) throws IOException {
                    //解决乱码问题
                    BufferedReader bufr = new BufferedReader(new InputStreamReader(in, "GBK"));
                    StringBuffer buffer = new StringBuffer();
                    String line = "";
                    while ((line = bufr.readLine()) != null) {
                              buffer.append(line);
                    }
                    return buffer.toString();
          }
}
