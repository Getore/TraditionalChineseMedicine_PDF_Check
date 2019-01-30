package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Description: 将 txt 放入 MySQL 数据库中
 *
 * @author shenli3
 * @createDate 2019/1/20 21:20
 */
public class TxtToMysql {

    private static final String URL = "jdbc:mysql://localhost:3306/mysql?characterEncoding=UTF-8";//jdbc:mysql//服务器地址/数据库名
    private static final String USER = "root";//用户名
    private static final String PASSWORD = "123456";//密码

    public static void main(String[] args) {

        File file_name = new File("F:\\Trainee\\ideaIU-2018.2\\readpdf\\src\\txt\\test.txt");

        try {
            Class.forName("com.mysql.jdbc.Driver");//加载驱动
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);//加载数据库
            Statement sql = con.createStatement();//数据库连接

            InputStreamReader isr = new InputStreamReader(new FileInputStream(file_name));
            //编码方式UTF-8，防止中文乱码
            BufferedReader bufferedR = new BufferedReader(isr);
            String temp_string = null;        //临时存储每行数据
            int id = 0;  // id

            while ((temp_string = bufferedR.readLine()) != null) { //遍历目标文档每行
                //System.out.println(temp_string);
                // TODO 2.注释由于换行导致保存不完整问题；
                temp_string = temp_string.replaceAll(" ", ""); // 实现无空格存入数据库

                if (temp_string.startsWith("[")) {
                    String[] table_list = temp_string.split("]");//先分割
                    table_list[0] = table_list[0].substring(1);
                    String new_member = "";  // 用来存储 SQL 语句
                    if (table_list.length >= 2) {
                        System.out.println(table_list[0]);
                        System.out.println(table_list[1]);
                        new_member = "insert into mytable(id, name, content ) values('"+ id++ +"', ' " + table_list[0] + " ',' " + table_list[1] + " ')";
                    } else {
                        System.out.println(table_list[0]);
                        new_member = "insert into mytable(id, name, content ) values('"+ id++ +"', '" + table_list[0] + "',' ')";
                    }
                    sql.executeUpdate(new_member);
                }
            }
            bufferedR.close();
            isr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}