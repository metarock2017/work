package sqldemo;
 import java.sql.*;

public class main {

    public static void main(String[] args) {
        //声明Connection对象
        Connection con;
        //驱动程序名
        String driver = "com.mysql.jdbc.Driver";
        //URL指向要访问的数据库名mydata
        String url = "jdbc:mysql://localhost:3306/two?useUnicode=true&characterEncoding=utf-8";
        //MySQL配置时的用户名
        String user = "root";
        //MySQL配置时的密码
        String password = "";
        //遍历查询结果集
        try {
            //加载驱动程序
            Class.forName(driver);
            //1.getConnection()方法，连接MySQL数据库！！
            con = DriverManager.getConnection(url,user,password);
            if(!con.isClosed())
                System.out.println("Succeeded connecting to the Database!");
            //2.创建statement类对象，用来执行SQL语句！！
            Statement statement = con.createStatement();




            //增
            //PreparedStatement psql;
            //psql = con.prepareStatement("insert into one(empno, ename, job, sal)" + "values(?, ?, ?, ?)");
            //psql.setInt(1, 33);
            //psql.setString(2, "李刚");
            //psql.setString(3, "总裁");
            //psql.setString(4, "111");
            //psql.executeUpdate();



            //改
            //PreparedStatement psql;
            //psql = con.prepareStatement("update one set sal = ? where ename = ?");
            //psql.setString(1, "1000000000000");
            //psql.setString(2, "李刚");
            //psql.executeUpdate();



            //删
            PreparedStatement psql;
            psql = con.prepareStatement("delete from one where sal > ?");
            psql.setString(1, "4");
            psql.executeUpdate();
            psql.close();





            //要执行的SQL语句
            String sql = "select * from one";
            //3.ResultSet类，用来存放获取的结果集！！
            ResultSet rs = statement.executeQuery(sql);
            System.out.println("-----------------");
            System.out.println("执行结果如下所示:");
            System.out.println("-----------------");
            System.out.println("姓名" + "\t" + "职称");
            System.out.println("-----------------");

            String job = null;
            String id = null;
            while(rs.next()){
                //获取stuname这列数据
                job = rs.getString("job");
                //获取stuid这列数据
                id = rs.getString("ename");

                //输出结果
                System.out.println(id + "\t" + job);
            }
            rs.close();
            con.close();
        } catch(ClassNotFoundException e) {
            //数据库驱动类异常处理
            System.out.println("Sorry,can`t find the Driver!");
            e.printStackTrace();
        } catch(SQLException e) {
            //数据库连接失败异常处理
            //System.out.println("SQLException");
            e.printStackTrace();
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            //System.out.println("Exception");
        }finally{
            System.out.println("数据库数据成功获取！！");
        }
    }

}