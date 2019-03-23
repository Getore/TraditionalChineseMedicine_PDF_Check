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

        File file_name = new File("F:\\Trainee\\ideaIU-2018.2\\TraditionalChineseMedicine_PDF_Check\\src\\txt\\test.txt");

        try {
            Class.forName("com.mysql.jdbc.Driver");     //加载驱动
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);//加载数据库
            Statement sql = con.createStatement();      //数据库连接

            InputStreamReader isr = new InputStreamReader(new FileInputStream(file_name));
            BufferedReader bufferedR = new BufferedReader(isr);
            String temp_string = null;        // 将读取的每一行，临时存储每行数据
            String temp = null;
            int id = 0;  // 数据库中的 id

            while ((temp_string = bufferedR.readLine()) != null) { //遍历目标文档每行

                temp_string = temp_string.replaceAll(" ", ""); // 实现无空格存入数据库
                if (temp_string.startsWith("[")) {
                    String[] table_row = temp_string.split("]");//先分割
                    table_row[0] = table_row[0].substring(1);   // substring(num) 表示去掉num个字符后，输出剩下的字符串；substring(num1, num2) 表示输出num1 - num2的字符（num1显示，num2不显示）。

//                    System.out.println(table_row[0]);
//                    System.out.println(table_row[1]);

                    if (temp_string.startsWith("[注释]")){
                        temp = table_row[1];
                    }

                    String new_member = "insert into mytable(id, name, content ) values('"+ id++ +"', ' " + table_row[0] + " ',' " + table_row[1] + " ')";
                    sql.executeUpdate(new_member);
                }

                if (temp_string.startsWith("[") != true && temp_string.contains("。")){
                    int num = id - 1;   // 因为id++的缘故，此时id已经在下一行，所以需要 -1；
                    temp += temp_string;   // 进行拼接，存放到数据库；
                    String new_member = "UPDATE mytable SET content = '"+ temp +"' WHERE id = '"+ num +"'";
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