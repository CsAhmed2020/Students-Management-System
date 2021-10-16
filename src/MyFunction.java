
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MyFunction {

public static int countData(String tableName) throws SQLException{

    int total=0;
    Connection con=MyConnection.getconniction();
    Statement st=con.createStatement();
    ResultSet rs=st.executeQuery("SELECT COUNT(*) AS 'total' FROM "+tableName);
    while(rs.next()){
    total=rs.getInt(1);
    }
    
    return total;
}    
}
