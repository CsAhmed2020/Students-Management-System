
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class course {
  
     public void insertUpdateDeleteCourse(char operation,Integer id,String label, Integer hours_number) {
    
        Connection con=MyConnection.getconniction();
        PreparedStatement ps;
        
        //i for insert
        if(operation=='i'){
            try {
                ps=con.prepareStatement("INSERT INTO `course`(label,hours_number) VALUES (?,?)");
                ps.setString(1, label);
                ps.setInt(2, hours_number);
                
                if(ps.executeUpdate()>0){
                    JOptionPane.showMessageDialog(null,"New Course Added");
                }   } catch (SQLException ex) {
                Logger.getLogger(student.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("error in course insert");
            }
        }
        //u for update
        if(operation=='u')
        {
            try {
                ps=con.prepareStatement("UPDATE `course`SET label= ?, hours_number= ?  WHERE id= ?");
                ps.setString(1, label);
                ps.setInt(2, hours_number);
                ps.setInt(3, id);
                
                if(ps.executeUpdate()>0){
                    JOptionPane.showMessageDialog(null,"Course Updated");
                }   } catch (SQLException ex) {
                Logger.getLogger(student.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("error in course ubdate");
        }
         
        //d for delete
         if(operation=='d')
        {
             int YesOrNo=JOptionPane.showConfirmDialog(null, "All Related Scores will also be deleted","Delete Course",JOptionPane.OK_OPTION,0);
            if(YesOrNo==JOptionPane.OK_OPTION)
            {
            try {
                ps=con.prepareStatement("DELETE FROM `course` WHERE id= ?");
                ps.setInt(1, id); 
                if(ps.executeUpdate()>0){
                    JOptionPane.showMessageDialog(null,"Course Deleted");
                }   } catch (SQLException ex) {
                Logger.getLogger(student.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("error in course delete");
            }
            }
        }
    
}
     }
     public boolean isCourseExist(String CourseName){
         boolean isExist=false;
         Connection con =MyConnection.getconniction();
        PreparedStatement ps;
        try {
            ps=con.prepareStatement("SELECT * FROM `course` WHERE label =?");
            ps.setString(1,CourseName);
            
            ResultSet rs= ps.executeQuery();
            
            
            if(rs.next()){
                 isExist=true;
            }
                    
                    } catch (SQLException ex) {
            Logger.getLogger(student.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
     return isExist;
     }
     
       public void fillCourseJtable(JTable table){
        Connection con =MyConnection.getconniction();
        PreparedStatement ps;
        try {
           // ps=con.prepareStatement("SELECT * FROM `course` WHERE CONCAT(label, hours_number)LIKE ?");
           ps=con.prepareStatement("SELECT * FROM `course` "); 
            ResultSet rs= ps.executeQuery();
            DefaultTableModel model=(DefaultTableModel) table.getModel();
            
            Object[] row ;
            
            while(rs.next()){
            row=new Object[3];
            row[0]=rs.getInt(1);
            row[1]=rs.getString(2);
            row[2]=rs.getInt(3);
            model.addRow(row);
            }     
                    } catch (SQLException ex) {
            Logger.getLogger(student.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
       public int getCourseID(String courselabel){
           int courseID=0;
           
            Connection con =MyConnection.getconniction();
            PreparedStatement ps;
        try {
            ps=con.prepareStatement("SELECT * FROM `course` WHERE label =?");
            ps.setString(1,courselabel);
            
            ResultSet rs= ps.executeQuery();
            
            
            if(rs.next()){
                 courseID=rs.getInt("Id");
            }
                    
        }catch (SQLException ex) {
            Logger.getLogger(student.class.getName()).log(Level.SEVERE, null, ex);
        }
         return courseID;
       }
       
       
        public void fillCoursecombox(JComboBox combobox){
        Connection con =MyConnection.getconniction();
        PreparedStatement ps;
        try {
           // ps=con.prepareStatement("SELECT * FROM `course` WHERE CONCAT(label, hours_number)LIKE ?");
           ps=con.prepareStatement("SELECT * FROM `course`"); 
            ResultSet rs= ps.executeQuery();
            
            while(rs.next()){
            combobox.addItem(rs.getString(2));
            }
                    
                    } catch (SQLException ex) {
            Logger.getLogger(student.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
}
