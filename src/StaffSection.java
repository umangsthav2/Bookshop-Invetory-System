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
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.*;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;


class SPanel extends JFrame implements ActionListener
    {
        JLabel title,subject,line,id,name,designation,salary,editor,date,password,warning,logger,staff_img;

        JTextField id_data,name_data,designation_data,salary_data,password_data,editor_data,date_data,logger_data;

        JButton main_menu,logout,add,edit,cancel,search,list,upload,clear;

        ImageIcon staff_icon;
        ImageIcon title_icon = new ImageIcon("./assets/icons/logo.png");

        File photo;

        String picLocation="./assets/staff_photos/1x/";
        String connData[] = new String[4];

        Border test_border = BorderFactory.createLineBorder(Color.black);

        JFileChooser pic;
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG Photo (300x300)px", "png");

        Font info_font = new Font("Poppins Medium",Font.PLAIN,25);
        Font title_font = new Font("Poppins",Font.BOLD,50);
        Font subject_font = new Font("Poppins",Font.BOLD,35);
        Font btn_font = new Font("Poppins",Font.BOLD,15);
        Font wf = new Font("Poppins Bold",Font.ITALIC,20);
        
        static String UserNAME= new AHome().UserNAME;        
        
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
                
                
                
                
                
                title = new JLabel("STAFF SECTION",JLabel.CENTER);
                title.setBounds(200,30,900,50);
                title.setFont(title_font);

                main_menu = new JButton("Main Menu");
                main_menu.setBounds(20,30,120,40);
                main_menu.setFocusable(false);
                main_menu.setFont(btn_font);
                main_menu.addActionListener(this);

                logout = new JButton("Logout");
                logout.setBounds(1140,30,100,40);
                logout.setFont(btn_font);
                logout.setFocusable(false);
                logout.addActionListener(this);

                id = new JLabel("Staff ID");
                id.setBounds(30,100,100,100);
                id.setFont(info_font);

                id_data = new JTextField();
                id_data.setBounds(240,130,350,45);
                id_data.setFont(info_font);

                name = new JLabel("Staff Name");
                name.setBounds(30,170,150,100);
                name.setFont(info_font);
                
                name_data = new JTextField();
                name_data.setBounds(240,200,350,45);
                name_data.setFont(info_font);

                designation = new JLabel("Designation");
                designation.setBounds(30,240,200,100);
                designation.setFont(info_font);
                
                designation_data = new JTextField();
                designation_data.setBounds(240,270,350,45);
                designation_data.setFont(info_font);

                salary = new JLabel("Salary");
                salary.setBounds(30,310,100,100);
                salary.setFont(info_font);
                
                salary_data = new JTextField();
                salary_data.setBounds(240,340,350,45);
                salary_data.setFont(info_font);

                password = new JLabel("Password");
                password.setBounds(30,380,200,100);
                password.setFont(info_font);
                
                password_data = new JTextField();
                password_data.setBounds(240,410,350,45);
                password_data.setFont(info_font);

                //Who is logging in the system
                logger = new JLabel("System User");
                logger.setBounds(650,580,200,100);
                logger.setFont(info_font);
               

                logger_data = new JTextField();
                logger_data.setBounds(840,600,350,45);
                logger_data.setFont(info_font);
                logger_data.setEnabled(false);
                logger_data.setText(new Login().UserNAME);

                //Who edited the data
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
                //
                date_data = new JTextField();
                date_data.setBounds(840,540,350,45);
                date_data.setFont(info_font);
                date_data.setEnabled(false);

                warning = new JLabel("All the messages displayed to user");
                warning.setBounds(185,440,1000,100);
                warning.setFont(wf);
                warning.setForeground(Color.RED);
                warning.setVisible(false);

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

                //Frame Dividing Border
                line = new JLabel("");
                line.setBorder(test_border);
                line.setBounds(640,90,2,550);

                staff_img = new JLabel(staff_icon);
                staff_img.setVerticalTextPosition(JLabel.BOTTOM);
                staff_img.setHorizontalAlignment(JLabel.CENTER);
                staff_img.setBounds(800,100,300,300);
                staff_img.setVisible(true);
                staff_img.setBorder(test_border);

                upload = new JButton("Upload");
                upload.setBounds(910,410,100,40);
                upload.setFont(btn_font);
                upload.setFocusable(false);
                upload.addActionListener(this);

                clear = new JButton("Clear");
                clear.setBounds(320,600,100,40);
                clear.setFont(btn_font);
                clear.setFocusable(false);
                clear.addActionListener(this);

                add(upload);
                add(staff_img);

                add(add);   add(edit);  add(cancel);
                add(search);    add(list);  add(clear);
                add(line);

                add(main_menu);
                add(logout);

                add(id);add(id_data);
                add(name);add(name_data);
                add(designation);add(designation_data);
                add(salary);add(salary_data);
                add(password);add(password_data);
                add(logger);add(logger_data);
                add(editor);add(editor_data);
                add(date);add(date_data);

                add(warning);add(title);

                setVisible(true);
                return this.UserNAME;
            }

        SPanel()
            {
                setSize(1280,720);
                setIconImage(title_icon.getImage());
                setDefaultCloseOperation(3);
                setTitle("Bookshop Inventory System | Staff Section");
                setLayout(null);
                setLocationRelativeTo(null);
                setResizable(false);
            }

        @Override
        public void actionPerformed(ActionEvent en)
            {

                if(en.getSource()==upload)
                        {
                                pic = new JFileChooser();
                                pic.setFileFilter(filter);

                                int picUploadStatus = pic.showOpenDialog(null);
                                
                                try
                                    {
                                         photo = new File(pic.getSelectedFile().getAbsolutePath());
                                         System.out.println(pic.getSelectedFile().getAbsolutePath());
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

                                         else if(newImg.getIconWidth()==300 && newImg.getIconHeight()==300)
                                                 {                                                 
                                                 System.out.println("Photo Loaded");
                                                 staff_img.setIcon(newImg);
                                                         }
                                                     else
                                                         {
                                                     warning.setText("Error : Please use Photo of 300 x 300");
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

                                catch(NullPointerException npe)
                                    {
                                         warning.setText("Error : User canceled photo selection");
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
                
                if(en.getSource()==add)
                    {
                        
                        try
                            {
                                if(photo!=null)
                                    {
                                            Connection con=null;
                                            con=DriverManager.getConnection("jdbc:mysql://"+connData[0],connData[1],connData[2]);
                                            Statement stat = con.createStatement();

                                            String StaffID=id_data.getText();
                                            String StaffNAME=name_data.getText();
                                            String StaffDESIGNATION=designation_data.getText();
                                            float StaffSALARY=Float.parseFloat(salary_data.getText());
                                            String StaffKEY=password_data.getText();
                                            String EditorNAME=new Login().UserNAME;

                                            
                                            stat.executeUpdate("use"+" "+connData[3]);
                                            stat.executeUpdate("INSERT INTO `staffs` VALUES ("+"\""+StaffID+"\""+","+ "\""+StaffNAME+"\""+","+"\""+StaffDESIGNATION+"\""+","+Float.toString(StaffSALARY)+","+"\""+StaffKEY+"\""+","+"\""+EditorNAME+"\""+","+"CURRENT_DATE"+")");

                                            File picdown = new File(picLocation+id_data.getText()+".png");

                                            Files.copy(photo.toPath(),picdown.toPath(),StandardCopyOption.REPLACE_EXISTING);

                                            staff_img.setIcon(null);                       

                                            id_data.setText("");
                                            name_data.setText("");
                                            designation_data.setText("");
                                            salary_data.setText("");
                                            password_data.setText(""); 

                                            warning.setText("Staff recruited successfully");
                                            warning.setForeground( new Color(0x009200));
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

                        catch(NumberFormatException num)
                            {
                                 warning.setText("Error : Fill details properly");
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

                        catch(SQLIntegrityConstraintViolationException dublicate)
                            {
                                warning.setText("Error : Staff already exists");
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

                                String StaffID=id_data.getText();
                                String prev_ID=StaffID;
                                String StaffNAME=name_data.getText();
                                String StaffDESIGNATION=designation_data.getText();
                                float StaffSALARY=Float.parseFloat(salary_data.getText());
                                String StaffKEY=password_data.getText();
                                String EditorNAME=new Login().UserNAME;

                                stat.executeUpdate("use"+" "+connData[3]);
                                String sql1="update staffs set staff_id="+"\""+StaffID+"\""+" where staff_id="+"\""+prev_ID+"\"";
                                String sql2="update staffs set staff_name="+"\""+StaffNAME+"\""+" where staff_id="+"\""+prev_ID+"\"";
                                String sql3="update staffs set staff_designation="+"\""+StaffDESIGNATION+"\""+" where staff_id="+"\""+prev_ID+"\"";
                                String sql4="update staffs set staff_salary="+StaffSALARY+" where staff_id="+"\""+prev_ID+"\"";
                                String sql5="update staffs set staff_key="+"\""+StaffKEY+"\""+" where staff_id="+"\""+prev_ID+"\"";
                                String sql6="update staffs set data_editor="+"\""+EditorNAME+"\""+" where staff_id="+"\""+prev_ID+"\"";
                                String sql7="update staffs set data_edit_date="+"CURRENT_DATE"+" where staff_id="+"\""+prev_ID+"\"";
                                
                                stat.executeUpdate(sql1);
                                stat.executeUpdate(sql2);
                                stat.executeUpdate(sql3);
                                stat.executeUpdate(sql4);
                                stat.executeUpdate(sql5);
                                stat.executeUpdate(sql6);
                                stat.executeUpdate(sql7);

                                photo = new File(picLocation+prev_ID+".png");
                                File renamer = new File(picLocation+StaffID+".png");
                                photo.renameTo(renamer);

                                warning.setText("Staff details edited successfully");
                                warning.setForeground( new Color(0x009200));
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
                                                          };
                                              a.schedule(a_task,2250);

                            }

                        catch(NumberFormatException num)
                            {
                                warning.setText("Error : Please fill details properly");
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
                        
                        catch(Exception ex)
                            {
                                ex.printStackTrace();
                            }
                    }

                else if(en.getSource()==search)
                    {
                        
                        try
                            {

                                String StaffID=id_data.getText();
                                String StaffNAME;
                                String StaffDESIGNATION;
                                float  StaffSALARY;
                                String StaffKEY;
                                String data_EDITOR;
                                String data_EDIT_DATE;

                                int found=0;
                                                                
                                Connection con=null;
                                con=DriverManager.getConnection("jdbc:mysql://"+connData[0],connData[1],connData[2]);
                                Statement stat = con.createStatement();
                                stat.executeUpdate("use"+" "+connData[3]);
                                ResultSet rs = stat.executeQuery(" SELECT * from `staffs` where staff_id="+"\""+StaffID+"\"");
                                while(rs.next())
                                    {
                                        found=1;
                                        StaffID=rs.getString("staff_id");
                                        StaffNAME=rs.getString("staff_name");
                                        StaffDESIGNATION=rs.getString("staff_designation");
                                        StaffSALARY=rs.getFloat("staff_salary");
                                        StaffKEY=rs.getString("staff_Key");
                                        data_EDITOR=rs.getString("data_editor");
                                        data_EDIT_DATE=rs.getString("data_edit_date");

                                        id_data.setText(StaffID);
                                        name_data.setText(StaffNAME);
                                        designation_data.setText(StaffDESIGNATION);
                                        salary_data.setText(Float.toString(StaffSALARY));
                                        password_data.setText(StaffKEY);
                                        editor_data.setText(data_EDITOR);
                                        date_data.setText(data_EDIT_DATE);
                                        

                                        ImageIcon pp_pic = new ImageIcon(picLocation+StaffID+".png");
                                        staff_img.setIcon(pp_pic);
                                    }
                                        stat.close();
                                        con.close();

                                      if(found==0)
                                    {
                                        warning.setText("Error : Staff not found");
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

              else if(en.getSource()==cancel)
                    {
                        try
                            {
                                Connection con=null;
                                con=DriverManager.getConnection("jdbc:mysql://"+connData[0],connData[1],connData[2]);
                                Statement stat = con.createStatement();
                                String StaffID=id_data.getText();
                                stat.executeUpdate("use"+" "+connData[3]);
                                stat.executeUpdate("delete from staffs where staff_id="+"\""+StaffID+"\"");

                                 warning.setText("Staff fired successfully");
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
                                id_data.setText("");
                                name_data.setText("");
                                designation_data.setText("");
                                salary_data.setText("");
                                password_data.setText("");
                                staff_img.setIcon(null);

                                editor_data.setText(null);
                                date_data.setText(null);

                            }
                        catch(Exception ex)
                            {
                                ex.printStackTrace();
                            }
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

             else if(en.getSource()==list)
                {
                    new ListStaffs();
                }
            
            else if(en.getSource()==clear)
                {
                    id_data.setText(null);
                    name_data.setText(null);
                    designation_data.setText(null);
                    designation_data.setText(null);
                    salary_data.setText(null);
                    password_data.setText(null);
                    staff_img.setIcon(null);
                    editor_data.setText(null);
                    date_data.setText(null);
                    photo=null;
                }

            }
    }

public class StaffSection
    {
        public static void main(String args[])
            {
                 SPanel run = new SPanel();
                run.MainMenu();
            }
    }
