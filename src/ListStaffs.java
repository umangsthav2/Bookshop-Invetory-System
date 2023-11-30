import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ListStaffs
      {
         String connData[] = new String[4];
         ImageIcon title_icon = new ImageIcon("./assets/icons/logo.png");
         ListStaffs()
            {


               //READ SERVER CREDENTIALS from FILE config.txt
                int j=0;
                try
                    {
                        File conf = new File("config.txt");
                        Scanner sc = new Scanner(conf);

                        while(sc.hasNext())
                            {
                                connData[j]=sc.next();
                                j++;
                            }
                        sc.close();
                    }
                
                catch(FileNotFoundException noFIle)
                    {
                        System.out.println("The config file does not exist, the program cannot run smoothly");
                    }
                

                 catch(NoSuchElementException ex)
                    {
                        System.out.println("The file has been modified, please download the file again");
                    }




               JFrame frame = new JFrame();
               String[] columnNames = {"Staff ID","Staff Name","Staff Designation","Staff Salary","Staff Password","Data Editor","Data Edited Date"};
               Object[][] data = new Object[100][100];

               JTable table = new JTable(data, columnNames);

               Font f = new Font("Poppins",Font.BOLD,15);
               table.setFont(f);
               table.setRowHeight(25);
 
               JPanel p = new JPanel(new BorderLayout(10, 10));
               p.setSize(1080,1080);
               p.add(new JScrollPane(table));
               table.setBounds(1,1,100,100);

               try
                  {
                     int i=0;
                     Connection con=null;
                     con=DriverManager.getConnection("jdbc:mysql://"+connData[0],connData[1],connData[2]);
                     Statement stat = con.createStatement();
                     stat.executeUpdate("use"+" "+connData[3]);
                     ResultSet rs = stat.executeQuery("SELECT * from `staffs`");
                     while(rs.next())
                        {
                        
                           data[i][0]=rs.getString("staff_id");
                           data[i][1]=rs.getString("staff_name");
                           data[i][2]=rs.getString("staff_designation");
                           data[i][3]=rs.getString("staff_salary");
                           data[i][4]=rs.getString("staff_key");
                           data[i][5]=rs.getString("data_editor");
                           data[i][6]=rs.getString("data_edit_date");
                           i++;

                        }  
                  }
               catch(Exception ex)
                  {
                     ex.printStackTrace();
                  }
         
                  table.setEnabled(false);
                  frame.add(p);
                  frame.setIconImage(title_icon.getImage());
                  frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                  frame.setSize(720,720);
                  frame.setLocationRelativeTo(null);  
                  frame.setVisible(true);
                  frame.setTitle("List of Staffs");
            }
         
         public static void main(String args[])
            {
               new ListStaffs();
            }
}