/*
SQLyog Ultimate v12.5.0 (64 bit)
MySQL - 5.7.30-log : Database - vueblog
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`vueblog` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `vueblog`;

/*Table structure for table `m_blog` */

DROP TABLE IF EXISTS `m_blog`;

CREATE TABLE `m_blog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `title` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `content` longtext,
  `created` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `status` tinyint(4) DEFAULT NULL,
  `views` tinyint(5) DEFAULT '0',
  `modified` datetime DEFAULT NULL,
  `top` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4;

/*Data for the table `m_blog` */

insert  into `m_blog`(`id`,`user_id`,`title`,`description`,`content`,`created`,`status`,`views`,`modified`,`top`) values 
(25,1,'Flutter 获取屏幕宽高','Flutter 获取屏幕宽高','屏幕宽度：\n>MediaQuery.of(context).size.width\n\n屏幕高度：\n\n>MediaQuery.of(context).size.height','2020-11-21 19:02:36',0,24,'2020-11-21 19:16:04',0),
(26,1,'啊沙发上','山豆根山豆根第三个是单独','# 士大夫士大夫士大夫大师傅似的 \n>saasafas','2020-11-21 19:02:40',0,53,'2020-11-21 19:02:37',1),
(27,1,'大飒飒发','啊沙发沙发沙发','士大夫士大夫多福多寿','2020-02-21 19:24:51',0,1,'2020-02-21 19:24:51',0),
(28,1,'flutter沉浸式状态栏','flutter沉浸式状态栏','在main方法内加入以下函数:\n\n```dart\nvoid main() {\n  runApp(new MyApp());\n  \n  // 判断当前设备是否为安卓\n  if (Platform.isAndroid) {\n  	// 关键代码\n	SystemUiOverlayStyle systemUiOverlayStyle =\n        SystemUiOverlayStyle(statusBarColor: Colors.transparent);\n	SystemChrome.setSystemUIOverlayStyle(systemUiOverlayStyle);\n  }\n}\n```\n然后导入所需要的包就ok了','2019-11-22 18:44:09',0,37,'2020-11-23 16:49:34',0),
(29,1,'Flutter自定义Drawer的图标','Flutter自定义Drawer的图标','当我们创建一个Drawer后，在AppBar左侧会默认出现一个图标，点击它可以弹出drawer，但是有时候我们希望将这个图标换成我们想要的图标。如果我们单纯地直接改AppBar的leading属性，改了之后再点击新的Icon不会再弹出Drawer了。\n\n正确的做法如下：\n```Dart\nappBar: AppBar(\n	leading: Builder(builder: (BuildContext context) {\n		return IconButton(\n			icon: const Icon(Icons.face),\n				onPressed: (){\n				 Scaffold.of(context).openDrawer();\n			},\n		);\n          }),\n	title: Text(title),\n	drawer: xxx(),\n),\n```\n转载于：https://blog.csdn.net/M_zoneL/article/details/86669521','2020-11-22 18:46:07',0,1,'2020-10-22 13:10:05',0),
(30,1,'hello world!','测试一段Java代码吧','# Hello world\n>测试一段Java代码吧\n```java\npublic static void main(String[] args){\n	System.out.print(\"hello world!\");\n}\n```','2020-11-23 17:23:49',0,3,'2020-11-23 17:24:49',1),
(31,1,'JAVA使用QQ邮箱发送邮件','JAVA使用QQ邮箱发送邮件','编写该程序需要用到的jar包：\n1. javax.mail-1.5.5.jar\n1. spring-beans-4.0.7.RELEASE.jar\n1. spring-context-support-4.0.7.RELEASE.jar\n1. spring-core-4.0.7.RELEASE.jar\n\n# 关键代码\n```java\npackage mail;\n\n\nimport java.io.BufferedReader;\n\nimport java.io.FileReader;\n\n\nimport java.util.Properties;\n\n\nimport org.springframework.mail.SimpleMailMessage;\n\nimport org.springframework.mail.javamail.JavaMailSenderImpl;\n\n\n/**\n *\n * 使用QQ发送邮件工具类\n *\n * @author canye\n *\n */\n\n\npublic class SendEmailUtil {\n	private final static String SERVICE_HOST = \"smtp.qq.com\";       /* QQ服务器 */\n\n	private final static int PORT = 465;                            /* smtp的端口号 */\n\n	private final static String PROTOCOL = \"smtp\";                  /* 协议名称。smtp表示简单邮件传输协议 */\n\n	private final static String ACCOUNT = \"xxxx@qq.com\";      /* 发送邮件的QQ账号 */\n\n	private final static String AUTH_CODE = \"xxxxxxxxxxx\";     /* QQ授权码(需要到https://mail.qq.com/申请) */\n\n\n	private static final JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();\n\n\n/*\n *\n * *发送QQ邮件的初始化配置\n *\n */\n\n	static {\n		senderImpl.setHost( SERVICE_HOST );                                             /* 设置 使用QQ邮箱发送邮件的主机名 */\n\n		senderImpl.setPort( PORT );                                                     /* 设置端口号 */\n\n		senderImpl.setProtocol( PROTOCOL );                                             /* 协议名称 */\n\n		senderImpl.setUsername( ACCOUNT );                                              /* 设置自己的邮箱帐号名称 */\n\n		senderImpl.setPassword( AUTH_CODE );                                            /* 设置对应账号申请到的授权码 */\n\n		Properties prop = new Properties();\n\n		prop.put( \" mail.smtp.auth \", \"true\" );                                         /* 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确 */\n\n		prop.put( \"mail.smtp.starttls.enable\", \"true\" );\n\n		prop.put( \"mail.smtp.socketFactory.class\", \"javax.net.ssl.SSLSocketFactory\" );  /* 使用ssl协议来保证连接安全 */\n\n		prop.put( \" mail.smtp.timeout \", \"25000\" );                                     /*传输超时时间 */\n\n		senderImpl.setJavaMailProperties( prop );\n	}\n\n\n/**\n *\n * 发送简单邮件\n *\n * @param accounts  被发邮件的用户数组\n *\n * @param info      邮件信息\n *\n * @param title     邮件主题\n *\n */\n\n	public static void sendSimpleMail( String[] accounts, String info, String title )\n	{\n/* 创建简单邮件对象 */\n\n		SimpleMailMessage mailMessage = new SimpleMailMessage();\n\n		mailMessage.setTo( accounts );          /* 设置邮件接收者账号数组 */\n\n		mailMessage.setFrom( ACCOUNT );         /* 设置邮件的发送者 */\n\n		mailMessage.setSubject( title );        /* 设置邮件的主题 */\n\n		mailMessage.setText( info );            /* 设置邮件的内容 */\n\n/* 发送邮件 */\n\n		senderImpl.send( mailMessage );\n	}\n\n\n\n\n```\n然后导入所需要的包就ok了','2020-11-23 17:29:24',0,1,'2020-11-23 17:29:46',0),
(32,1,'Plugin caching_sha2_password could not be loaded','Plugin caching_sha2_password could not be loaded','![](https://images2018.cnblogs.com/blog/1192534/201808/1192534-20180804225657811-1627061237.png)\n\n\n## 原因\n通过本地去连接远程的mysql时报错，原因是mysql8.0的加密方法变了。\n\n\n第三方客户端基本都不支持这种加密方式，只有自带的命令行支持，所以需要修改加密方式。\n\n\n## 解决方法\n\n1. 修改加密方式\n\n	> ALTER USER\'root\'@\'localhost\' IDENTIFIED BY \'密码@\' PASSWORD EXPIRE NEVER;\n\n2. 重新修改密码\n\n	> ALTER USER\'root\'@\'localhost\' IDENTIFIED BY \'密码@\' PASSWORD EXPIRE NEVER;\n\n3. 刷新\n\n	> FLUSH PRIVILEGES;\n','2020-11-23 17:31:15',0,2,'2020-11-23 17:31:17',0),
(33,1,'gitalk经常报错network error','gitalk经常报错network error','原因是hexo 的全局配置 _config.yml 里关于文章 url 路径的配置是这样：\n> permalink: :year/:month/:day/:title/\n\n如果你的文章 title 是中文，这会导致你的文章地址栏的地址很长。这会带来两个问题，第一如果修改了文章 title，原来的地址就失效了，SEO 也没了。第二就是 gitalk 需要在 GitHub中创建 issue，而 issue 的 label 长度必须小于 50，而 gitalk 会拿地址栏的 path 作为 label，所以会导致 gitalk 连接失败。\n\n使用 hexo-abbrlink 插件，解决 path 超出：\n>npm i hexo-abbrlink --save-dev\n\n修改_config.yml里的abbrlink为\n```xml\n# abbrlink config\nabbrlink:\n  alg: crc16  #support crc16(default) and crc32\n  rep: hex    #support dec(default) and hex\n# 更改 permalink 值\npermalink: p/:abbrlink.html \n```\n\n重新运行静态构建\n>hexo g\n\n生成的文章 url 将会是这样的：\n\n>https://yanx1n.github.io/p/839a.html\n\n之后每次 generate 都不会变更，还需要为每一篇文章初始化 issue\n','2020-11-23 17:31:48',0,3,'2020-11-23 17:31:56',0),
(34,1,'Github Hexo 404','Github Hexo 404','![](https://pic4.zhimg.com/80/v2-d0451d70eb7d2d3be21d2cc8bea70427_720w.jpg)\n\n绑定了自己的域名，但是并没有在Github中设置，或许说绑定了的，但是每次Hexo d后Github page的自定义域名都会重置消失，解决方案是\n\nGithub默认把域名写在了一个CNAME的无后缀名文件中，在 `/source` 中创建一个叫CNAME的无后缀名文件，在里面写你要解析的域名即可，不需要带协议头。','2020-11-23 17:32:17',0,4,'2020-11-23 17:32:31',0),
(35,1,' Gboard 谷歌输入法导入词库',' Gboard 谷歌输入法导入词库','<p>\n    &nbsp;&nbsp;&nbsp;&nbsp;谷歌输入法的词库挺少的，如果不导入词库太难用了，所以写个最简单的导入词库的方法。\n</p>\n<!-- more -->\n<p>\n    &nbsp; &nbsp; 我打包放在了下面的蓝奏云链接下面，有搜狗和百度的一些官方带的词库，下好压缩包直接导入，不需要压缩，就以ZIP格式导入。因为词库比较大，所以在导入的过程中会黑屏等待几分钟，不用慌，可以挂后台让它自个导入，过几分钟就好了。\n</p>\n<p>\n    &nbsp; &nbsp; 导入词库需要手机登录过Google账号。\n    <br>\n</p>\n<p>\n    &nbsp; &nbsp; goole输入法导入步骤：\n    <font color=\"#f9963b\">\n        字典 -&gt;&nbsp;个人字典 -&gt;&nbsp;中文（简体） -&gt; 左上角三个点 -&gt;&nbsp;导入\n    </font>\n    <br>\n</p>\n<p>\n    <img src=\"http://yanx1n.top/usr/uploads/2020/01/27393732.png\" style=\"max-width:100%;\">\n    <br>\n</p>\n<p>词库蓝奏云下载地址：</p>\n<p>https://www.lanzous.com/b0aqg582d</p>\n<p>密码:gm2s</p>','2020-11-23 17:32:54',0,1,'2020-11-23 17:33:04',0);

/*Table structure for table `m_link` */

DROP TABLE IF EXISTS `m_link`;

CREATE TABLE `m_link` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `sitename` varchar(30) NOT NULL,
  `email` varchar(55) DEFAULT NULL,
  `url` varchar(100) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `m_link` */

insert  into `m_link`(`id`,`sitename`,`email`,`url`,`description`) values 
(1,'百度','email@baidu.com','https://baidu.com','百度搜索引擎'),
(2,'腾讯新闻网','email@qq.com','https://qq.com','腾讯新闻'),
(3,'谷歌','email@gmail.com','https://google.com','google搜索引擎');

/*Table structure for table `m_user` */

DROP TABLE IF EXISTS `m_user`;

CREATE TABLE `m_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(16) DEFAULT NULL,
  `nickname` varchar(32) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `email` varchar(64) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `status` int(5) NOT NULL,
  `created` datetime DEFAULT NULL,
  `last_login` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_USERNAME` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `m_user` */

insert  into `m_user`(`id`,`username`,`nickname`,`avatar`,`email`,`password`,`status`,`created`,`last_login`) values 
(1,'admin','普罗· 米修斯','https://image-1300566513.cos.ap-guangzhou.myqcloud.com/upload/images/5a9f48118166308daba8b6da7e466aab.jpg',NULL,'96e79218965eb72c92a549dd5a330112',0,'2020-04-20 10:44:01',NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
