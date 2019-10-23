## 技术文档
[Spring boot 文档](https://spring.io)\
[thymeleaf 文档](https://www.thymeleaf.org)\
[Bootstrap 文档](https://www.bootcss.com)\
[MyBatis 文档](http://www.mybatis.org/mybatis-3/zh/index.html)
~~~~~
<dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <version>2.1.0</version>
</dependency>
~~~~~
~~~~
application.yml配置
mybatis:
  config-location: classpath:mybatis/mybatis_config.xml
  mapper-locations: classpath:mybatis/mapper/*.xml
~~~~
[mySql 文档](https://www.runoob.com/sql/sql-tutorial.html)
~~~~
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <scope>runtime</scope>
</dependency>
~~~~
~~~~
application.yml配置
spring:
  datasource:
    username: 
    password: 
    url: jdbc:
    driver-class-name: com.mysql.cj.jdbc.Driver
# 注意
# Mysql 版本8.0 以上 driver-class-name: com.mysql.cj.jdbc.Driver
# Mysql 版本8.0以下 river-class-name: com.mysql.jdbc.Driver
~~~~
## 使用工具
[lombok]<br>
[git]<br>
[mybatis Generator](http://mybatis.org/generator/reference/plugins.html)<br>
~~~~
 1.添加plugin 
  <!--	mybatis  generator 	依赖	-->
 	<plugin
 	   <groupId>org.mybatis.generator</groupId>
 	   <artifactId>mybatis-generator-maven-plugin</artifactId>
 	   <version>1.3.7</version>
 	   <dependencies>
 	<!--	配置mysql 驱动器	-->
            <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.17</version>
            </dependency>
 	   </dependencies>
 	</plugin>
 	
 2.新建generatorConfig.xml
 
 <?xml version="1.0" encoding="UTF-8"?>
 <!DOCTYPE generatorConfiguration
         PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
         "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">
 
 <generatorConfiguration>
 
     <context id="DB2Tables" targetRuntime="MyBatis3">
        <!--添加MyBatis 分页插件  只对MyBatis 3 有效  -->
         <plugin type="org.mybatis.generator.plugins.RowBoundsPlugin"/>
 
         <!--MyBatis  generator 配置数据库  -->
         <jdbcConnection driverClass="com.mysql.cj.jdbc.Driver"
                         connectionURL="jdbc:mysql://localhost:3306/user?useUnicode=true&amp;characterEncoding=utf-8&amp;serverTimezone=UTC"
                         userId="root"
                         password="123456">
         </jdbcConnection>
 
         <javaTypeResolver >
             <property name="forceBigDecimals" value="false" />
         </javaTypeResolver>
         <!--MyBatis  generator 指定数据对应生成的实体位置  -->
         <javaModelGenerator targetPackage="com.exampl.communt.mode" targetProject="src/main/java">
             <property name="enableSubPackages" value="true" />
             <property name="trimStrings" value="true" />
         </javaModelGenerator>
         <!--MyBatis  generator 指定mapper.xml 自动生成位置  -->
         <sqlMapGenerator targetPackage="mybatis/mapper"  targetProject="src/main/resources">
             <property name="enableSubPackages" value="true" />
         </sqlMapGenerator>
         <!--MyBatis  generator 指定mapper接口 自动生成位置  -->
         <javaClientGenerator type="XMLMAPPER" targetPackage="com.exampl.communt.mapper"  targetProject="src/main/java">
             <property name="enableSubPackages" value="true" />
         </javaClientGenerator>
          <!--MyBatis  generator 数据库名 以及对应生成的实体类名  -->
         <table tableName="user_info" domainObjectName="User" />
         <table tableName="publish" domainObjectName="PublishMode" />
     </context>
 </generatorConfiguration>
 
 3.执行Terminal
 mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate

~~~~