import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.NoSuchElementException;
import java.util.Scanner;



public class ListBooks
      {
         String connData[] = new String[4];
         ImageIcon title_icon = new ImageIcon("./assets/icons/logo.png");


         ListBooks()
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
               String[] columnNames = {"Book ISBN No","Book Name","Book Author","Book Price","Book Quantity","Data Editor","Data Edited Date"};
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
                     ResultSet rs = stat.executeQuery("SELECT * from `books`");
                     while(rs.next())
                        {
                        
                           data[i][0]=rs.getString("book_isbn");
                           data[i][1]=rs.getString("book_name");
                           data[i][2]=rs.getString("book_author");
                           data[i][3]=rs.getString("book_price");
                           data[i][4]=rs.getString("book_quantity");
                           data[i][5]=rs.getString("data_editor");
                           data[i][6]=rs.getString("data_edit_date");
                           // System.out.println(rs.getString("book_author"));
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
                  
                  frame.setTitle("List of Books in Stock");
            }
         public static void main(String args[])
            {
                              
              new ListBooks();
            }
}