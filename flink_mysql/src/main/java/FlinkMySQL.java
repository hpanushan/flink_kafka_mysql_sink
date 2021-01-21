import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;

import java.sql.*;

public class FlinkMySQL extends RichSinkFunction {

    PreparedStatement ps;
    private Connection connection;

    private static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://192.168.8.105:3306/flink?useUnicode=true&characterEncoding=UTF-8", "anushan", "Omnibis.1234");
        } catch (Exception e) {
            System.out.println("error = "+ e.getMessage());
        }
        return con;
    }


    public static void main(String[] args) throws SQLException {

        Connection con = getConnection();

        Statement stmt=con.createStatement();

        ResultSet rs=stmt.executeQuery("insert into Persons (id,name,password,age) values (12,damon,damon.1,34)");

        while(rs.next()){
            System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+" "+rs.getInt(4));
        }
    }
}
