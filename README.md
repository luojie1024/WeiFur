# WeiFur

[![](https://user-gold-cdn.xitu.io/2017/12/13/1604ec3c62a7de14)](https://travis-ci.org/Alamofire/Alamofire)   ![](https://img.shields.io/badge/platform-android-green.svg)  ![](https://img.shields.io/badge/api_level-21-green.svg) 
![](https://img.shields.io/badge/language-java-green.svg)  ![](https://img.shields.io/badge/version-v1.0-green.svg)

## 校园助手APP
   * 在线登陆
   * 离线登陆
   * 课表查询
   * 空教室查询
   * 成绩查询
   
----
  
+ Author: 中国男人的骄傲
+ E-mail：luojie@hnu.edu.cn
+ Blog : http://blog.csdn.net/luojie140

关键词：
====
+ 校园助手
+ 爬虫
+ Jsoup网页解析
+ 教务系统
+ SQLite数据库
+ Android Studio;

基金：
==
+ 湖南省大学生研究性学习与创新性实验计划项目(2015401)

引言：
===
> 手机已成为生活中不可分割的伴侣,Android操作系统作为一个市场占有过半的手机平台,能够为用户提供高质量的服务支撑。随着无线互联网的普及,出现了越来越多的校园APP,给生活带来了诸多便利。通过使用手机APP可以方便、快捷访问校园资讯,随时随地个性化、准确地使用校园服务。大学生上课经常要去不同的教室,学校安排的课程不完全固定,结课时间不统一,学生经常会走错教室或错过上课。为此,设计了校园助手APP,目的是将这些课程信息归类整理,通过APP随时随地查询。同时,APP中还加入了成绩查询和空教室查询功能。

简介：  
====
> “微芙蓉”是本人大学期间自学Android开发的第一个项目，是一款湖南文理学院的校园查询APP，用户无需注册，只需输入学号、教务系统密码，就可以查询自己的成绩。还实现了空教室查询功能，课表查询功能，用户无需设置，程序会根据当前系统日期判断当前周 星期几，自动查询课表和当日空教室。  

摘要：
=====

> 基于Android平台，Android Studio开发，使用HttpWatch进行数据包分析，java爬虫进行数据的抓取，通过获取用Cookies信息，从而获取教务系统的访问权限，实现了教务系统数据的抓取、Jsoup数据解析、存储、展示，使用了Android本地的SQLite数据库进行课表数据的存储  

功能介绍：
====

* 【在线登陆功能】  
  - 通过学号、教务系统密码(默认123456)登陆，登陆成功，系统自动完成课表设置，提供成绩实时查询。 
    + 【成绩查询】------根据输入学号、教务系统密码，即可学年、学期查询自己的成绩。
    + 【空教室查询】----无需设置，自动判断日期，显示当天空教室信息
    + 【课表查询功能】--自动完成班级设置，显示课表信息。提供查询功能，可以按班级，按周查询课表。
   
   
* 【离线登陆功能】  
  - 相比在线登陆，离线登录不需要使用数据流量，直接访问本地数据库,可以进行课表查询,空教室查询功能。  
    + 【空教室查询】----无需设置，自动判断日期，显示当天空教室信息
    + 【课表查询功能】--需要手动设置班级，提供查询功能，可以按班级，按周查询课表
 

关键技术实现
====

1. 教务系统接入
> 登陆模块实现:登陆功能主要使用URLConnection类的直接子类HttpURLConnection进行教务系统模拟登陆,判断登陆权限,保存Cookies,使用Jsoup网页解析
  技术获取用户信息,并使用SharedPreference进行配置信息存储。
2. 主体功能实现
+ 成绩查询模块实现:
  > 在登陆成功之后,根据用户选择的查询筛选方式,使用HttpURLConnection类与教务系统服务器交互,获取服务器反馈后自动跳转到成绩显
  示页面,再通过Jsoup网页解析技术解析HTML网页,抽取成绩等相关信息,使用<LinearLayout<ListView>>布局进行展示。

+ 课表查询模块实现:
  > 通过读取SharedPreference,获取用户的班级信息,自动匹配当前学期周、单双周、检索SQLite课表数据库,使用<LinearLayout<Button>>
  进行布局,通过随机算法进行课表背景色填充,自动适应课表展示长度。同时提供全校课表按班级、按教学周检索,方便进行课表信息查询。
  空教室查询模块实现:根据前期对教学楼及教室分布的调研数据,使用<LinearLayout<ListView>>布局,构建多个Adapter进行ListView适配,通过自动教学周获
  取,检索SQLite课表数据库,填充Adapter复用convertView、更新ListView,从而高效、准确、直观地展示空教室信息。提供按教学周、按星期检索空教室功能,方
  便用户查询。


功能展示
===


<h4 align = "center">【登入界面】</h4> 
<div align=center><img width="512" src="https://github.com/luojie1024/WeiFur/blob/master/screenshots/%E7%99%BB%E5%85%A5%E7%95%8C%E9%9D%A2.png?raw=true"/></div> 



<h4 align = "center">【主界面】</h4> 
<div align=center><img width="512" src="https://github.com/luojie1024/WeiFur/blob/master/screenshots/%E4%B8%BB%E7%95%8C%E9%9D%A2.png?raw=true"/></div> 



<h4 align = "center">【空教室查询】</h4> 
<div align=center><img width="512" src="https://github.com/luojie1024/WeiFur/blob/master/screenshots/%E7%A9%BA%E6%95%99%E5%AE%A4.png?raw=true"/></div> 


<h4 align = "center">【课表查询】</h4> 
<div align=center><img width="512" src="https://github.com/luojie1024/WeiFur/blob/master/screenshots/%E8%AF%BE%E8%A1%A8%E6%9F%A5%E8%AF%A2.png?raw=true"/></div> 



<h4 align = "center">【成绩查询】</h4> 
<div align=center><img width="512" src="https://github.com/luojie1024/WeiFur/blob/master/screenshots/%E6%88%90%E7%BB%A9%E6%9F%A5%E8%AF%A2.png?raw=true"/></div> 


<h4 align = "center">【功能介绍1】</h4> 
<div align=center><img width="512" src="https://github.com/luojie1024/WeiFur/blob/master/screenshots/001.png?raw=true"/></div> 



<h4 align = "center">【功能介绍2】</h4> 
<div align=center><img width="512" src="https://github.com/luojie1024/WeiFur/blob/master/screenshots/002.png?raw=true"/></div> 



<h4 align = "center">【功能介绍3】</h4> 
<div align=center><img width="512" src="https://github.com/luojie1024/WeiFur/blob/master/screenshots/003.png?raw=true"/></div> 


<h4 align = "center">【功能介绍4】</h4> 
<div align=center><img width="512" src="https://github.com/luojie1024/WeiFur/blob/master/screenshots/004.png?raw=true"/></div> 


<h4 align = "center">【功能介绍5】</h4> 
<div align=center><img width="512" src="https://github.com/luojie1024/WeiFur/blob/master/screenshots/0101.png?raw=true"/></div> 
