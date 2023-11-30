import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class OPanel extends JFrame implements ActionListener
   {
        String connData[] = new String[4];
        ImageIcon title_icon = new ImageIcon("./assets/icons/logo.png");

        JLabel id,name,contact,demand,isbn,price,editor,date,warning,logger,quantity,line,title,subject;
        JButton list,search,cancel,main_menu,logout,add,edit,clear,check,book_list;
        JTextField id_data,name_data,contact_data,demand_data,isbn_data,quantity_data,price_data,editor_data,date_data,logger_data;

        JFileChooser pic;
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG Photo (300x300)px", "png");
        
        Font info_font = new Font("Poppins Medium", 0, 25);
        Font title_font = new Font("Poppins", 1, 50);
        Font subject_font = new Font("Poppins", 1, 35);
        Font btn_font = new Font("Poppins", 1, 15);
        Font wf = new Font("Poppins Bold", 2, 20);
        Font pf = new Font("Cambria Math",Font.BOLD, 25);

        Border test_border;

   void MainMenu()
      {
        
            //READ SERVER CREDENTIALS from FILE config.txt
                int i=0;
                try
                    {
                        File conf = new File("config.txt");
                        Scanner sc = new Scanner(conf);

                        while(sc.hasNext())
                            {
                                connData[i]=sc.next();
                                i++;
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
        


        title = new JLabel("SALES SECTION",JLabel.CENTER);
        title.setBounds(200, 30, 900, 50);
        title.setFont(title_font);

        main_menu = new JButton("Main Menu");
        main_menu.setBounds(20, 30, 120, 40);
        main_menu.setFocusable(false);
        main_menu.setFont(btn_font);
        main_menu.addActionListener(this);

        logout = new JButton("Logout");
        logout.setBounds(1140, 30, 100, 40);
        logout.setFont(btn_font);
        logout.setFocusable(false);
        logout.addActionListener(this);
      
        id = new JLabel("Order ID");
        id.setBounds(30, 100, 250, 100);
        id.setFont(info_font);
      
        id_data = new JTextField();
        id_data.setBounds(250, 130, 350, 45);
        id_data.setFont(info_font);
      
        name = new JLabel("Customer Name");
        name.setBounds(30, 170, 250, 100);
        name.setFont(info_font);
      
        name_data = new JTextField();
        name_data.setBounds(250, 200, 350, 45);
        name_data.setFont(info_font);
      
        contact = new JLabel("Contact No.");
        contact.setBounds(30, 240, 250, 100);
        contact.setFont(this.info_font);
      
        contact_data = new JTextField();
        contact_data.setBounds(250, 270, 350, 45);
        contact_data.setFont(info_font);
      
        demand = new JLabel("Book Name");
        demand.setBounds(30, 310, 250, 100);
        demand.setFont(info_font);
      
        demand_data = new JTextField();
        demand_data.setBounds(250, 340, 350, 45);
        demand_data.setFont(info_font);
    
        isbn = new JLabel("Book ISBN");
        isbn.setBounds(30, 380, 150, 100);
        isbn.setFont(info_font);
      
        isbn_data = new JTextField();
        isbn_data.setBounds(250, 410, 350, 45);
        isbn_data.setFont(info_font);
         
        price = new JLabel("Price");
        price.setBounds(400, 450, 150, 100);
        price.setFont(info_font);
      
        price_data= new JTextField();
        price_data.setBounds(480, 470, 120, 55);
        price_data.setFont(pf);

        quantity = new JLabel("Quantity");
        quantity.setBounds(30, 450, 150, 100);
        quantity.setFont(info_font);
      
        quantity_data= new JTextField();
        quantity_data.setBounds(150, 470, 120, 55);
        quantity_data.setFont(pf);

        logger = new JLabel("System User");
        logger.setBounds(900, 470, 200, 100);
        logger.setFont(info_font);
      
        logger_data = new JTextField();
        logger_data.setBounds(800, 540, 350, 45);
        logger_data.setFont(info_font);
        logger_data.setEnabled(false);
        logger_data.setText(new Login().UserNAME);
            
        editor = new JLabel("Edited By");
        editor.setBounds(920, 60, 200, 100);
        editor.setFont(info_font);
     
        editor_data = new JTextField();
        editor_data.setBounds(800, 130, 350, 45);
        editor_data.setFont(info_font);
        editor_data.setEnabled(false);
     
        date = new JLabel("Edited On");
        date.setBounds(920, 180, 200, 100);
        date.setFont(info_font);
        
        date_data = new JTextField();
        date_data.setBounds(800, 250, 350, 45);
        date_data.setFont(info_font);
        date_data.setEnabled(false);
     
        warning = new JLabel("Order added successfully");
        warning.setBounds(185, 500, 1000, 100);
        warning.setFont(wf);
        warning.setForeground(Color.RED);
        warning.setVisible(false);
      
        add = new JButton("Sell");
        add.setBounds(40, 575, 100, 40);
        add.setFont(btn_font);
        add.setFocusable(false);
        add.addActionListener(this);

        edit = new JButton("Edit");
        edit.setBounds(180, 575, 100, 40);
        edit.setFont(btn_font);
        edit.setFocusable(false);
        edit.addActionListener(this);

        cancel = new JButton("BOOKS");
        cancel.setBounds(320, 575, 100, 40);
        cancel.setFont(btn_font);
        cancel.setFocusable(false);
        cancel.addActionListener(this);
      
        search = new JButton("Search");
        search.setBounds(40, 625, 100, 40);
        search.setFont(btn_font);
        search.setFocusable(false);
        search.addActionListener(this);
      
        list = new JButton("List");
        list.setBounds(180, 625, 100, 40);
        list.setFont(btn_font);
        list.setFocusable(false);
        list.addActionListener(this);

        clear = new JButton("Clear");
        clear.setBounds(320,625,100,40);
        clear.setFont(btn_font);
        clear.setFocusable(false);
        clear.addActionListener(this);

        check = new JButton("CHECK");
        check.setBounds(465,575,120,40);
        check.setFont(btn_font);
        check.setFocusable(false);
        check.addActionListener(this);
        check.setBackground(new Color(0x00aa00));
        check.setForeground(Color.WHITE);
        check.setBorder(null);

        book_list = new JButton("Book List");
        book_list.setBounds(465,625,120,40);
        book_list.setFont(btn_font);
        book_list.setFocusable(false);
        book_list.addActionListener(this);

        line = new JLabel("");
        line.setBorder(test_border);
        line.setBounds(640, 90, 2, 550);

        //Adding components to Frame
        add(quantity);add(quantity_data);       add(price);add(price_data);
    
        add(add);     add(edit);      add(cancel);    add(check);
        add(search);  add(list);      add(clear);     add(book_list);
       
        add(main_menu);
        add(logout);
    
        add(line);
    
        add(id);add(id_data);
        add(name);add(name_data);
        add(contact);add(contact_data);
        add(price);add(price_data);
        add(isbn);add(isbn_data);
        add(logger);add(logger_data);
        add(editor);add(editor_data);
        add(date);add(date_data);
    
        add(demand);
        add(demand_data);
          
        add(warning);
        add(title);
    
        setVisible(true);
   }

   OPanel()
      {
      test_border = BorderFactory.createLineBorder(Color.black);
      setSize(1280, 720);
      setIconImage(title_icon.getImage());
      setDefaultCloseOperation(3);
      setTitle("Bookshop Inventory System | Order Panel");
      setLayout(null);
      setLocationRelativeTo(null);
      setResizable(false);
   }

   public void actionPerformed(ActionEvent en)
      {
         int found=0;
         float stk_quan=0F,cus_quantity=0F;
         String demanded_book;
         String demanded_isbn;
         Float book_price=0F;

        if(en.getSource()==check)
            {
                try
                    {
                        demanded_book=demand_data.getText();
                        demanded_isbn=isbn_data.getText();
                        Connection con=null;
                        con=DriverManager.getConnection("jdbc:mysql://"+connData[0],connData[1],connData[2]);
                        Statement stat = con.createStatement();
                        stat.executeUpdate("use"+" "+connData[3]);

                        ResultSet rs = stat.executeQuery("SELECT * from `books` where book_isbn="+"\""+demanded_isbn+"\""+"or book_name="+"\""+demanded_book+"\"");
                        while(rs.next())
                            {
                                found=1;
                                stk_quan=rs.getInt("book_quantity");
                                book_price=rs.getFloat("book_price");
                                demand_data.setText(rs.getString("book_name"));
                                isbn_data.setText(rs.getString("book_isbn"));
                            }
                        
                        if(found==1)
                            {
                                cus_quantity=Integer.parseInt(quantity_data.getText());
                                if(stk_quan>=cus_quantity)
                                    {
                                        add.setEnabled(true);
                                        warning.setText("Book can be sold");
                                        warning.setForeground(new Color(0x009200)); //Green Color 
                                        // warning.setForeground(Color.RED);
                                        warning.setVisible(true);
                                               Timer a = new Timer();
                                               TimerTask a_task = new java.util.TimerTask()
                                                                         {
                                                                             @Override
                                                                             public void run()
                                                                                 {
                                                                                     warning.setVisible(false);
                                                                                 }
                                                                         };a.schedule(a_task,1500);
                                                price_data.setText(Float.toString(cus_quantity*book_price));
                                    }
                                else
                                    {
                                
                                        add.setEnabled(false);
                                        warning.setText("Error : Only "+(int) stk_quan+" book in stock");
                                        warning.setForeground(new Color(0x009200)); //Green Color 
                                        // warning.setForeground(Color.RED);
                                        warning.setVisible(true);
                                               Timer a = new Timer();
                                               TimerTask a_task = new java.util.TimerTask()
                                                                         {
                                                                             @Override
                                                                             public void run()
                                                                                 {
                                                                                     warning.setVisible(false);
                                                                                 }
                                                                         };a.schedule(a_task,2500);
                                                price_data.setText(Float.toString(cus_quantity*book_price));
                                    }
                                
                            }
                        else if(found==0)
                            {
                                warning.setText("Error : No such book found in Stock");
                                // warning.setForeground(new Color(0x009200)); //Green Color 
                                warning.setForeground(Color.RED);
                                warning.setVisible(true);
                                       Timer a = new Timer();
                                       TimerTask a_task = new java.util.TimerTask()
                                                                 {
                                                                     @Override
                                                                     public void run()
                                                                         {
                                                                             warning.setVisible(false);
                                                                         }
                                                                 };a.schedule(a_task,2500);
                                price_data.setText(Float.toString(cus_quantity*book_price));
                            }
                        
                    }

                catch(NumberFormatException nfe)
                    {
                        warning.setText("Error : Please fill details correctly");
                        warning.setForeground(new Color(0x009200)); //Green Color 
                        // warning.setForeground(Color.RED);
                        warning.setVisible(true);

                        Timer a = new Timer();
                            TimerTask a_task = new java.util.TimerTask()
                                {
                                    @Override
                                        public void run()
                                            {
                                                warning.setVisible(false);
                                            }
                                };a.schedule(a_task,2500);

                        price_data.setText(Float.toString(cus_quantity*book_price));
                    }
                
                catch(Exception ex)
                    {
                        ex.printStackTrace();
                    }
                
            }
                 
         else if(en.getSource()==add)
                    {

                     try
                            {

                                Connection con=null;
                                con=DriverManager.getConnection("jdbc:mysql://"+connData[0],connData[1],connData[2]);
                                Statement stat = con.createStatement();

                                String OrderID=id_data.getText();
                                String OrderNAME=name_data.getText();
                                String OrderCONTACT=contact_data.getText();
                                String OrderDEMAND=demand_data.getText();
                                int OrderQUANTITY=Integer.parseInt(quantity_data.getText());
                                float OrderPRICE=Float.parseFloat(price_data.getText());
                                String EditorNAME=new Login().UserNAME;

                                stat.executeUpdate("use"+" "+connData[3]);
                                stat.executeUpdate("INSERT INTO `orders` VALUES("+ "\""+OrderID+"\"" + ","+ "\""+OrderNAME+"\"" + "," + "\""+OrderCONTACT+"\"" + ","+"\""+OrderDEMAND+"\""+","+OrderQUANTITY+","+OrderPRICE+","+"\""+EditorNAME+"\""+","+"CURRENT_DATE"+")");

                                //Reducing book as the book is sold from stock
                                stat.executeUpdate("update `books` set book_quantity=book_quantity-"+OrderQUANTITY+" where book_isbn="+"\""+isbn_data.getText()+"\"");

                                warning.setText("Book sold successfully");
                                warning.setForeground( new Color(0x009200));
                                warning.setVisible(true);

                                id_data.setText("");
                                name_data.setText("");
                                contact_data.setText("");
                                demand_data.setText("");
                                isbn_data.setText("");
                                price_data.setText("");
                                quantity_data.setText("");
                                quantity_data.setText("");
                                editor_data.setText("");
                                date_data.setText("");

                                        Timer a = new Timer();
                                        TimerTask a_task = new java.util.TimerTask()
                                                                {
                                                                    @Override
                                                                    public void run()
                                                                        {
                                                                            warning.setVisible(false);
                                                                        }
                                                                };
                                                    a.schedule(a_task,2250);
                            }

                        catch(NumberFormatException num)
                            {
                                 warning.setText("Error : Please fill details properly");
                                        // warning.setForeground(new Color(0x009200)); //Green Color 
                                        warning.setForeground(Color.RED);
                                        warning.setVisible(true);

                                               Timer a = new Timer();
                                               TimerTask a_task = new java.util.TimerTask()
                                                                         {
                                                                             @Override
                                                                             public void run()
                                                                                 {
                                                                                     warning.setVisible(false);
                                                                                 }
                                                                         };
                                                             a.schedule(a_task,1500);
                            }

                        catch(SQLIntegrityConstraintViolationException dublicate)
                            {
                                warning.setText("Error : Order data already exists");
                                warning.setForeground(Color.RED);
                                warning.setVisible(true);
                                Timer a = new Timer();
                                        TimerTask a_task = new java.util.TimerTask()
                                                                {
                                                                    @Override
                                                                    public void run()
                                                                        {
                                                                            warning.setVisible(false);
                                                                        }
                                                                };
                                                    a.schedule(a_task,2250);
                            }
                        
                        catch(Exception ex)
                            {
                                ex.printStackTrace();
                            }
                    }

                  else if(en.getSource()==edit)
                    {
                        try
                            {                              
                                Connection con=null;
                                con=DriverManager.getConnection("jdbc:mysql://"+connData[0],connData[1],connData[2]);
                                Statement stat = con.createStatement();

                                String OrderID=id_data.getText();
                                String prev_ID=OrderID;
                                String OrderNAME=name_data.getText();
                                String OrderCONTACT=contact_data.getText();
                                String OrderDEMAND=demand_data.getText();
                                int OrderQUANTITY=Integer.parseInt(quantity_data.getText());
                                float OrderPRICE=Float.parseFloat(price_data.getText());
                                String EditorNAME=new Login().UserNAME;
                                stat.executeUpdate("use"+" "+connData[3]);

                                String sql1="update `orders` set order_id="+"\""+OrderID+"\""+" where order_id="+"\""+prev_ID+"\"";
                                String sql2="update `orders` set order_name="+"\""+OrderNAME+"\""+" where order_id="+"\""+prev_ID+"\"";
                                String sql3="update `orders` set order_contact="+"\""+OrderCONTACT+"\""+" where order_id="+"\""+prev_ID+"\"";
                                String sql4="update `orders` set order_demand="+"\""+OrderDEMAND+"\""+" where order_id="+"\""+prev_ID+"\"";
                                String sql5="update `orders` set order_quantity="+OrderQUANTITY+" where order_id="+"\""+prev_ID+"\"";
                                String sql6="update `orders` set order_price="+OrderPRICE+" where order_id="+"\""+prev_ID+"\"";
                                String sql7="update `orders` set data_editor="+"\""+EditorNAME+"\""+" where order_id="+"\""+prev_ID+"\"";
                                String sql8="update `orders` set data_edit_date="+"CURRENT_DATE"+" where order_id="+"\""+prev_ID+"\"";

                                stat.executeUpdate(sql1);
                                stat.executeUpdate(sql2);
                                stat.executeUpdate(sql3);
                                stat.executeUpdate(sql4);
                                stat.executeUpdate(sql5);
                                stat.executeUpdate(sql6);
                                stat.executeUpdate(sql7);
                                stat.executeUpdate(sql8);

                                warning.setText("Order Edited Successfully");
                                warning.setForeground(new Color(0x009200));
                                warning.setVisible(true);
                                
                                Timer a = new Timer();
                                TimerTask a_task = new java.util.TimerTask()
                                    {
                                        @Override
                                        public void run()
                                            {
                                                warning.setVisible(false);
                                            }
                                    };
                                a.schedule(a_task,1500);
                            }

                        catch(NumberFormatException num)
                            {
                                warning.setText("Error : Please fill details properly");
                                // warning.setForeground(new Color(0x009200)); //Green Color 
                                warning.setForeground(Color.RED);
                                warning.setVisible(true);
                                
                                Timer a = new Timer();
                                TimerTask a_task = new java.util.TimerTask()
                                    {
                                        @Override
                                        public void run()
                                            {
                                                warning.setVisible(false);
                                            }
                                    };
                                    a.schedule(a_task,1500);
                            }
                        
                        catch(Exception ex)
                            {
                                ex.printStackTrace();
                          }

                    }

                 else if(en.getSource()==search)
                    {
                        try
                            {
                                String OrderID=id_data.getText();
                                String OrderNAME;
                                String OrderCONTACT;
                                String OrderDEMAND;
                                int OrderQUANTITY;
                                float  OrderPRICE;
                                String data_EDITOR;
                                String data_EDIT_DATE;

                                found=0;

                                Connection con=null;
                                con=DriverManager.getConnection("jdbc:mysql://"+connData[0],connData[1],connData[2]);
                                Statement stat = con.createStatement();
                                stat.executeUpdate("use"+" "+connData[3]);
                                ResultSet rs = stat.executeQuery(" SELECT * from `orders` where order_id="+"\""+OrderID+"\"");
                                while(rs.next())
                                    {
                                        found=1;
                                        OrderID=rs.getString("order_id");
                                        OrderNAME=rs.getString("order_name");
                                        OrderCONTACT=rs.getString("order_contact");
                                        OrderDEMAND=rs.getString("order_demand");
                                        OrderQUANTITY=rs.getInt("order_quantity");
                                        OrderPRICE=rs.getFloat("order_price");
                                        data_EDITOR=rs.getString("data_editor");
                                        data_EDIT_DATE=rs.getString("data_edit_date");

                                        id_data.setText(OrderID);
                                        name_data.setText(OrderNAME);
                                        contact_data.setText(OrderCONTACT);
                                        demand_data.setText(OrderDEMAND);
                                        isbn_data.setText(Integer.toString(OrderQUANTITY));
                                        price_data.setText(Float.toString(OrderPRICE));
                                       
                                        price_data.setText(Float.toString(OrderPRICE));
                                        editor_data.setText(data_EDITOR);
                                        date_data.setText(data_EDIT_DATE);
                                    }
                                        stat.close();
                                        con.close();

                                      if(found==0)
                                    {
                                        warning.setText("Error : Order not Found");
                                        // warning.setForeground(new Color(0x009200)); //Green Color 
                                        warning.setForeground(Color.RED);
                                        warning.setVisible(true);
                                        Timer a = new Timer();
                                        TimerTask a_task = new java.util.TimerTask()
                                            {
                                                @Override
                                                public void run()
                                                    {
                                                        warning.setVisible(false);
                                                    }
                                            };
                                            a.schedule(a_task,1500);
                                    }
                    
                            }
                        catch(Exception ex)
                            {
                                ex.printStackTrace();
                            }
                    }

                else if(en.getSource()==cancel)
                    {
                        new BPanel().MainMenu();
                    }

                else if(en.getSource()==main_menu)
                     {
                        setVisible(false);
                        new AHome().MainMenu();
                     }

                  else if(en.getSource()==logout)
                     {
                        setVisible(false);
                        new Login().LoginDisplay();
                     }

                  else if(en.getSource()==clear)
                     {
                        id_data.setText("");
                        name_data.setText("");
                        contact_data.setText("");
                        demand_data.setText("");
                        isbn_data.setText("");
                        price_data.setText("");
                        quantity_data.setText("");
                        editor_data.setText("");
                        date_data.setText("");
                     }

                else if(en.getSource()==list)
                     {
                        new ListOrders();
                     }
                else if(en.getSource()==book_list)
                    {
                        new ListBooks();  
                    }
      }
}

public class OrderPanel
   {
      public static void main(String args[])
         {
            OPanel run = new OPanel();
            run.MainMenu();
         }
}