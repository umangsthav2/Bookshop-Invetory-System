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

import java.sql.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.ConnectException;
import java.nio.file.Files;

import java.util.TimerTask;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Timer;

class BPanel extends JFrame implements ActionListener
    {
        
        String connData[] = new String[4];

        ImageIcon book_icon;
        ImageIcon title_icon = new ImageIcon("./assets/icons/logo.png");

        JLabel isbn,name,author,price,editor,date,quantity,warning,logger,book_img,title,subject,line;
        JTextField isbn_data,name_data,author_data,price_data,quantity_data,editor_data,date_data,logger_data;
        JButton main_menu,logout,add,edit,cancel,search,list,clear,upload;

        File photo;

        JFileChooser pic;
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG Photo (275x350)px", "png");

        Font info_font = new Font("Poppins Medium",Font.PLAIN,25);
        Font title_font = new Font("Poppins",Font.BOLD,50);
        Font subject_font = new Font("Poppins",Font.BOLD,35);
        Font btn_font = new Font("Poppins",Font.BOLD,15);
        Font wf = new Font("Poppins Bold",Font.ITALIC,20);
        
        String picLocation="./assets/book_photos/1x/";

        Border test_border = BorderFactory.createStrokeBorder(new BasicStroke(2.0F));

        static String UserNAME= new Login().UserNAME;

        String MainMenu()
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
                                
                main_menu = new JButton("Main Menu");
                main_menu.setBounds(20,30,120,40);
                main_menu.setFocusable(false);
                main_menu.setFont(btn_font);
                main_menu.addActionListener(this);
                
                title = new JLabel("BOOK SECTION",JLabel.CENTER);
                title.setBounds(200,30,900,50);
                title.setFont(title_font);

                logout = new JButton("Logout");
                logout.setBounds(1140,30,100,40);
                logout.setFont(btn_font);
                logout.setFocusable(false);
                logout.addActionListener(this);

                isbn = new JLabel("ISBN");
                isbn.setBounds(30,100,100,100);
                isbn.setFont(info_font);

                isbn_data = new JTextField();
                isbn_data.setBounds(150,130,350,45);
                isbn_data.setFont(info_font);

                name = new JLabel("Name");
                name.setBounds(30,170,100,100);
                name.setFont(info_font);
                
                name_data = new JTextField();
                name_data.setBounds(150,200,350,45);
                name_data.setFont(info_font);

                author = new JLabel("Author");
                author.setBounds(30,240,100,100);
                author.setFont(info_font);
                
                author_data = new JTextField();
                author_data.setBounds(150,270,350,45);
                author_data.setFont(info_font);

                price = new JLabel("Price");
                price.setBounds(30,310,100,100);
                price.setFont(info_font);
                
                price_data = new JTextField();
                price_data.setBounds(150,340,350,45);
                price_data.setFont(info_font);

                quantity = new JLabel("Quantity");
                quantity.setBounds(30,380,150,100);
                quantity.setFont(info_font);
                
                quantity_data = new JTextField();
                quantity_data.setBounds(150,410,350,45);
                quantity_data.setFont(info_font);

                //Who is currently using the system
                logger = new JLabel("System User");
                logger.setBounds(650,580,200,100);
                logger.setFont(info_font);
               
                logger_data = new JTextField();
                logger_data.setBounds(840,600,350,45);
                logger_data.setFont(info_font);
                logger_data.setEnabled(false);
                logger_data.setText(new Login().UserNAME);

                //Who is logging in the system
                editor = new JLabel("Edited By");
                editor.setBounds(650,450,200,100);
                editor.setFont(info_font);


                editor_data = new JTextField();
                editor_data.setBounds(840,480,350,45);
                editor_data.setFont(info_font);
                editor_data.setEnabled(false);

                date = new JLabel("Edited On");
                date.setBounds(650,520,200,100);
                date.setFont(info_font);

                date_data = new JTextField();
                date_data.setBounds(840,540,350,45);
                date_data.setFont(info_font);
                date_data.setEnabled(false);

                warning = new JLabel("All the messages to the user");
                warning.setBounds(185,440,1000,100);
                warning.setFont(wf);
                warning.setForeground(Color.RED);
                warning.setVisible(false);

                // All the buttons (Add, edit, delete, search, list, clear)
                add = new JButton("Add");
                add.setBounds(40,525,100,40);
                add.setFont(btn_font);
                add.setFocusable(false);
                add.addActionListener(this);

                edit = new JButton("Edit");
                edit.setBounds(180,525,100,40);
                edit.setFont(btn_font);
                edit.setFocusable(false);
                edit.addActionListener(this);

                cancel = new JButton("Delete");
                cancel.setBounds(320,525,100,40);
                cancel.setFont(btn_font);
                cancel.setFocusable(false);
                cancel.addActionListener(this);

                search = new JButton("Search");
                search.setBounds(40,600,100,40);
                search.setFont(btn_font);
                search.setFocusable(false);
                search.addActionListener(this);


                list = new JButton("List");
                list.setBounds(180,600,100,40);
                list.setFont(btn_font);
                list.setFocusable(false);
                list.addActionListener(this);

                clear = new JButton("Clear");
                clear.setBounds(320,600,100,40);
                clear.setFont(btn_font);
                clear.setFocusable(false);
                clear.addActionListener(this);

                //Frame Dividing Line
                line = new JLabel("");
                line.setBorder(test_border);
                line.setBounds(640,90,2,550);
                
                book_img = new JLabel(book_icon);
                book_img.setVerticalTextPosition(JLabel.BOTTOM);
                book_img.setHorizontalAlignment(JLabel.CENTER);
                book_img.setBounds(700,100,275,350);
                book_img.setVisible(true);
                book_img.setBorder(test_border);

                upload = new JButton("Upload");
                upload.setBounds(1000,410,100,40);
                upload.setFont(btn_font);
                upload.setFocusable(false);
                upload.addActionListener(this);

                //Adding Components to the frame                

                add(main_menu);
                add(title);
                add(logout);

                add(add);add(edit);add(cancel);
                add(search);add(list);add(clear);

                add(line);
                
                add(book_img);
                add(upload);

                add(logger);add(logger_data);

                add(editor);add(editor_data);

                add(date);add(date_data);

                add(isbn);add(isbn_data);
                add(name);add(name_data);

                add(author);add(author_data);

                add(price);add(price_data);

                add(quantity);add(quantity_data);

                add(warning);

                setVisible(true);
                return this.UserNAME;
            }

        //Constructor
        BPanel()

            {
                setSize(1280,720);
                setIconImage(title_icon.getImage());
                setDefaultCloseOperation(3);
                setTitle("Bookshop Inventory System | Book Section");
                setLayout(null);
                setResizable(false);
                setLocationRelativeTo(null);
                setDefaultCloseOperation(HIDE_ON_CLOSE);
            }
        
        @Override
        public void actionPerformed(ActionEvent en)
            {
                if(en.getSource()==add)
                    {
                                try
                                    {
                                        if(photo!=null)
                                            {
                                                Connection con=null;
                                                con=DriverManager.getConnection("jdbc:mysql://"+connData[0],connData[1],connData[2]);
                                                Statement stat = con.createStatement();
                                                int BookISBN=Integer.parseInt(isbn_data.getText());
                                                String BookNAME=name_data.getText();
                                                String BookAUTHOR=author_data.getText();
                                                float BookPRICE=Float.parseFloat(price_data.getText());
                                                int BookQUANTITY=Integer.parseInt(quantity_data.getText());
                                                String EditorNAME= new Login().UserNAME;
                                                stat.executeUpdate("use"+" "+connData[3]);
                                                stat.executeUpdate("INSERT INTO `books` VALUES ("+Integer.toString(BookISBN)+","+ "\""+BookNAME+"\""+","+"\""+BookAUTHOR+"\""+","+BookPRICE+","+BookQUANTITY+","+"\""+EditorNAME+"\""+","+"CURRENT_DATE"+")");                                                
                                                                                                
                                                File picdown = new File(picLocation+isbn_data.getText()+".png");
                                                Files.copy(photo.toPath(),picdown.toPath());
                                        
                                                isbn_data.setText("");
                                                name_data.setText("");
                                                author_data.setText("");
                                                price_data.setText("");
                                                quantity_data.setText("");
                                                book_img.setIcon(null);
                                                photo=null;                       

                                                warning.setText("Book added Successfully");
                                                warning.setForeground( new Color(0x009200));
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
                                        else
                                            {
                                                warning.setText("Error: Please upload Photo");
                                                // warning.setForeground( new Color(0x009200));
                                                warning.setForeground( Color.RED);
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

                                }
                                catch(NumberFormatException ramrolekh)
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

                                catch(ConnectException con_err)
                                    {
                                        System.out.println("Database Connection Error");
                                    }
                                

                                catch(SQLIntegrityConstraintViolationException dublicate)
                                    {
                                        warning.setText("Error : Book already exists");
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
                                int BookISBN,prev_ISBN=Integer.parseInt(isbn_data.getText());
                                int found=0,prefound=0;
                                String BookNAME=name_data.getText();                            
                                String BookAUTHOR=author_data.getText();      
                                                      
                                Connection con=null;
                                con=DriverManager.getConnection("jdbc:mysql://"+connData[0],connData[1],connData[2]);
                                Statement stat = con.createStatement();
                                stat.executeUpdate("use"+" "+connData[3]);
                                ResultSet prs = stat.executeQuery(" SELECT * from `books` where book_name="+"\""+BookNAME+"\""+"and "+"book_author="+"\""+BookAUTHOR+"\"");
                                    while(prs.next())
                                        {
                                            prefound=1;
                                            prev_ISBN=prs.getInt("book_isbn");
                                        }

                                stat.executeUpdate("use"+" "+connData[3]);
                                ResultSet rs = stat.executeQuery(" SELECT * from `books` where book_isbn="+prev_ISBN);

                                while(rs.next())
                                    {
                                        found=1;
                                    }
                                    
                                BookISBN=Integer.parseInt(isbn_data.getText());
                                BookNAME=name_data.getText();
                                BookAUTHOR=author_data.getText();
                                float BookPRICE=Float.parseFloat(price_data.getText());
                                int BookQUANTITY=Integer.parseInt(quantity_data.getText());
                                String EditorNAME= new Login().UserNAME;
                                if(found==1)
                                    {
                                        
                                        String sql1="update books set book_isbn="+BookISBN+" where book_isbn="+prev_ISBN;
                                        String sql2="update books set book_name="+"\""+BookNAME+"\""+" where book_isbn="+BookISBN;
                                        String sql3="update books set book_author="+"\""+BookAUTHOR+"\""+" where book_isbn="+BookISBN;
                                        String sql4="update books set book_price="+BookPRICE+" where book_isbn="+BookISBN;
                                        String sql5="update books set book_quantity="+BookQUANTITY+" where book_isbn="+BookISBN;
                                        String sql6="update books set data_editor="+"\""+EditorNAME+"\""+" where book_isbn="+BookISBN;
                                        String sql7="update books set data_edit_date="+"CURRENT_DATE"+" where book_isbn="+BookISBN;

                                        System.out.println(prev_ISBN);
                                        System.out.println("Prefound = "+prefound);
                                        System.out.println("ISBN = "+BookISBN);
                                        System.out.println("Bookname = "+BookNAME);
                                        System.out.println("Author = "+BookAUTHOR);
                                        System.out.println("Price = "+BookPRICE);
                                        System.out.println("Quantity = "+BookQUANTITY);

                                        stat.executeUpdate(sql1);
                                        stat.executeUpdate(sql2);
                                        stat.executeUpdate(sql3);
                                        stat.executeUpdate(sql4);
                                        stat.executeUpdate(sql5);
                                        stat.executeUpdate(sql6);
                                        stat.executeUpdate(sql7);

                                        // ImageIcon pp_pic = new ImageIcon(picLocation+prev_ISBN+".png");
                                        photo = new File(picLocation+prev_ISBN+".png");
                                        File renamer = new File(picLocation+BookISBN+".png");
                                        // Files.copy(photo.toPath(),picdown.toPath(),StandardCopyOption.REPLACE_EXISTING);
                                        photo.renameTo(renamer);


                                        warning.setText("Book edited successfully");
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
                                else if(found==0)
                                    {
                                        warning.setText("Error : Book not found to edit");
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
                else if(en.getSource()==cancel)
                    {
                        try
                            {
                                Connection con=null;
                                con=DriverManager.getConnection("jdbc:mysql://"+connData[0],connData[1],connData[2]);
                                Statement stat = con.createStatement();
                                int BookISBN=Integer.parseInt(isbn_data.getText());
                                stat.executeUpdate("use"+" "+connData[3]);
                                stat.executeUpdate("delete from books where book_isbn="+BookISBN);

                                warning.setText("Book deleted successfully");
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
                                                                 };a.schedule(a_task,1500);
                                isbn_data.setText("");
                                name_data.setText("");
                                author_data.setText("");
                                price_data.setText("");
                                quantity_data.setText("");
                                book_img.setIcon(null);


                                editor_data.setText(null);
                                date_data.setText(null);

                            }
                        catch(NumberFormatException ex)
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

                                int bookISBN=Integer.parseInt(isbn_data.getText());
                                String bookNAME;
                                String bookAUTHOR;
                                float bookPRICE;
                                int bookQUANTITY=0;
                                String data_EDITOR=new Login().UserNAME;
                                String data_EDIT_DATE;

                                int found=0;


                                                                
                                // bookISBN= Integer.parseInt( (isbn_data.getText()) );
                                Connection con=null;
                                con=DriverManager.getConnection("jdbc:mysql://"+connData[0],connData[1],connData[2]);
                                Statement stat = con.createStatement();
                                stat.executeUpdate("use"+" "+connData[3]);
                                ResultSet rs = stat.executeQuery(" SELECT * from `books` where book_isbn="+Integer.toString(bookISBN));
                                while(rs.next())
                                    {
                                        found=1;
                                        bookISBN=rs.getInt("book_isbn");
                                        bookNAME=rs.getString("book_name");
                                        bookAUTHOR=rs.getString("book_author");
                                        bookPRICE=rs.getFloat("book_price");
                                        bookQUANTITY=rs.getInt("book_quantity");
                                        data_EDITOR=rs.getString("data_editor");
                                        data_EDIT_DATE=rs.getString("data_edit_date");

                                        isbn_data.setText(Integer.toString(bookISBN));
                                        name_data.setText(bookNAME);
                                        author_data.setText(bookAUTHOR);
                                        price_data.setText(Float.toString(bookPRICE));
                                        quantity_data.setText(Integer.toString(bookQUANTITY));
                                        editor_data.setText(data_EDITOR);
                                        date_data.setText(data_EDIT_DATE);
                                        book_icon = new ImageIcon(picLocation+bookNAME+".png");
                                        book_img.setIcon(book_icon);

                                        ImageIcon pp_pic = new ImageIcon(picLocation+bookISBN+".png");
                                        book_img.setIcon(pp_pic);

                                        System.out.println(pp_pic);

                                    }
                                        warning.setText("Book found");
                                        warning.setForeground(new Color(0x009200));
                                        warning.setVisible(true);

                                              Timer b = new Timer();
                                              TimerTask b_task = new java.util.TimerTask()
                                                                        {
                                                                            @Override
                                                                            public void run()
                                                                                {
                                                                                    warning.setVisible(false);
                                                                                }
                                                                        };
                                                            b.schedule(b_task,1500);

                                        stat.close();
                                        con.close();

                                      if(found==0)
                                        {
                                        warning.setText("Error : Book not Found");
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
                        
                        catch(NumberFormatException ex)
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
                               warning.setText("Error : Connection Failed");
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

                  if(en.getSource()==upload)
                        {
                                pic = new JFileChooser();
                                pic.setFileFilter(filter);

                                int picUploadStatus = pic.showOpenDialog(null);
                                System.out.println(picUploadStatus);

                                ImageIcon imageIcon = new ImageIcon("./img/imageName.png"); // load the image to a imageIcon
                                Image image = imageIcon.getImage(); // transform it 
                                Image newimg = image.getScaledInstance(120, 120,Image.SCALE_SMOOTH); // scale it the smooth way  
                                imageIcon = new ImageIcon(newimg);  // transform it back

                                try
                                    {
                                         photo = new File(pic.getSelectedFile().getAbsolutePath());
                                         ImageIcon newImg = new ImageIcon(photo.getPath());

                                         pic.setCurrentDirectory(null);
                                         if(newImg.getIconWidth()==-1)
                                             {
                                                 warning.setText("Error : Cannot Load the Photo");
                                                 // warning.setForeground( new Color(0x009200));
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


                                         else if(newImg.getIconWidth()==275 && newImg.getIconHeight()==350)
                                                 {
                                                 System.out.println("Photo Loaded");
                                                 book_img.setIcon(newImg);
                                                         }
                                                     else
                                                         {
                                                         photo=null;
                                                     warning.setText("Error : Please use Photo of 275 x 350");
                                                     // warning.setForeground( new Color(0x009200));
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
                                    }
                                    catch(Exception ex)
                                        {
                                            ex.printStackTrace();
                                        }
                        }
                    
                    else if(en.getSource()==clear)
                        {
                            isbn_data.setText("");
                            name_data.setText("");
                            author_data.setText("");
                            price_data.setText("");
                            quantity_data.setText("");
                            book_img.setIcon(null);

                            editor_data.setText(null);
                            date_data.setText(null);
                        }

                    else if(en.getSource()==list)
                        {
                            new ListBooks();
                        }
                   
                    else if(en.getSource()==main_menu)
                        {
                            setVisible(false);
                            new AHome().MainMenu();
                        }

                    else if(en.getSource()==logout)
                        {
                            new Login().LoginDisplay();
                            setVisible(false);
                        }
                        
            }
    }

public class BookPanel
    {
        public static void main(String args[])
            {
                 BPanel run = new BPanel();
                run.MainMenu();
            }
    }