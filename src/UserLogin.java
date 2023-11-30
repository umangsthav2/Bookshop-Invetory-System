import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

import java.awt.Color;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import java.io.*;

import javax.sound.sampled.*;

class Login extends JFrame implements ActionListener
    {
        JLabel subject,title,username,password,lock_label,login_error;
        JTextField username_data;
        JPasswordField password_data;
        JButton login;
        JPanel login_panel,login_design_panel;
        ImageIcon lock_open,lock_close;

        Font info_font = new Font("Poppins", 1, 20);
        Font title_font = new Font("Poppins", 1, 50);
        Font subject_font = new Font("Poppins", 1, 70);
        Border test_border;
        String connData[] = new String[4];

        static String UserNAME;
        void LoginDisplay()
                {
                    
                    ImageIcon title_ico = new ImageIcon("./assets/icons/logo.png");
                
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
                    
                    //Panel for Lock Image and GIF                
                        login_panel = new JPanel();

                    //Panel for Lock Image and GIF
                        login_design_panel = new JPanel();

                    lock_label = new JLabel(lock_close);
                    lock_label.setBounds(50, 10, 450, 450);
                    
                    subject = new JLabel("LOGIN", 0);
                    subject.setBounds(395, 100, 1000, 65);
                    subject.setForeground(Color.WHITE);
                    subject.setFont(subject_font);

                    username = new JLabel("Username", 0);
                    username.setBounds(830, 175, 150, 20);
                    username.setForeground(Color.WHITE);
                    username.setFont(info_font);

                    username_data = new JTextField();
                    username_data.setBounds(810,200,200,35);
                    username_data.setFont(info_font);
        
                    password_data = new JPasswordField();
                    password_data.setBounds(810,300,200,35);
                    password_data.setFont(info_font);

                    password = new JLabel("Password",JLabel.CENTER);
                    password.setBounds(830, 275, 150, 20);
                    password.setForeground(Color.WHITE);
                    password.setFont(info_font);

                    login_error = new JLabel("Error : ID & Password",JLabel.CENTER);
                    login_error.setBounds(780,355,250,30);
                    login_error.setForeground(Color.WHITE);
                    login_error.setBackground(new Color(0xcc0000));
                    login_error.setFont(info_font);
                    login_error.setVisible(false);
                    login_error.setOpaque(true);

                    login = new JButton("LOGIN");
                    login.setBounds(850,400,100,40);
                    login.setFont(this.info_font);
                    login.addActionListener(this);

                    login_design_panel.add(username);
                    login_design_panel.add(username_data);
                    login_design_panel.add(password);
                    login_design_panel.add(password_data);
                    login_design_panel.add(login_error);
                    login_design_panel.add(subject);
                    login_design_panel.add(login);
                    login_design_panel.setLayout(null);
                    login_design_panel.setBackground(new Color(77402));

                    login_panel.setLayout(null);
                    login_panel.setBackground(new Color(2403));
                    login_panel.add(lock_label);
                    login_panel.setSize(600,500);
                    login_design_panel.setSize(1200,500);

                    add(login_panel);
                    setIconImage(title_ico.getImage());
                    add(login_design_panel);
                    setVisible(true);
                }

      void geet()
            {
                 try
                    {
                        File f = new File("./assets/sprites/lock_open.wav");
                        AudioInputStream a = AudioSystem.getAudioInputStream(f);
                        Clip c = AudioSystem.getClip();
                        
                        c.open(a);
                        c.start();
                    }

                catch(Exception ex)
                    {
                        ex.printStackTrace();
                    }
            }
    
   Login()
        {
            test_border = BorderFactory.createLineBorder(Color.black);
            lock_close = new ImageIcon("./assets/icons/animations/locked.gif");
            lock_open = new ImageIcon("./assets/icons/animations/unlocked.gif");
            setSize(1200, 500);
            setDefaultCloseOperation(3);
            setTitle("Bookshop Inventory System | Login");
            setLayout(null);
            setResizable(false);
            setLocationRelativeTo(null);
        }
        
    @Override
    public void actionPerformed(ActionEvent en)
        {
            if (en.getSource()==login)
                {
                    try {
                            String userID = username_data.getText();
                            String userKEY = new String(password_data.getPassword());
                            int authorization = 0,hack=0;
                            Connection con = null;

                            for(int i=0;i<userID.length();i++)
                                {
                                    if(userID.charAt(i)=='=')
                                        {
                                            hack=1;
                                            login_error.setText("Data INVALID");
                                        }
                                }
                            if(hack==1)
                                {
                                    authorization=0;
                                }

                            else 
                                {
                                    // con = DriverManager.getConnection("jdbc:mysql://"+connData[0]+","+connData[1]+connData[2]);
                                    con=DriverManager.getConnection("jdbc:mysql://"+connData[0],connData[1],connData[2]);
                                    Statement stat = con.createStatement();
                                    stat.executeUpdate("use"+" "+connData[3]);
                                    System.out.println(connData[3]);
                                    ResultSet rs = stat.executeQuery("SELECT * from staffs where staff_id=\"" + userID + "\"and staff_key=\"" + userKEY + "\"");

                                    while(rs.next())
                                        {
                                            authorization = 1;
                                            lock_label.setIcon(this.lock_open);
                                            UserNAME = rs.getString("staff_name");
                                        }
                                }

                            if (authorization==1)
                                {
                                    System.out.println(UserNAME);
                                     Timer t = new Timer();
                                                     TimerTask task = new TimerTask()
                                                         {
                                                             @Override
                                                             public void run()
                                                                 {
                                                                     new AHome().MainMenu(); //Goto Login Page after Votes
                                                                     setVisible(false);

                                                                 }
                                                         };
                                     t.schedule(task,1900);

                                     Timer u = new Timer();
                                                     TimerTask geet2 = new TimerTask()
                                                         {
                                                             @Override
                                                             public void run()
                                                                 {
                                                                     geet();
                                                                 }
                                                         };
                                     u.schedule(geet2,100);
                                }
                            else if (authorization==0)
                                {
                                    
                                    username_data.setText(null);
                                    password_data.setText(null);
                                    
                                    
                                    login_error.setText("Error: ID & Password");
                                    login_error.setVisible(true);
                                     Timer msg = new Timer();
                                                     TimerTask display = new TimerTask()
                                                         {
                                                             @Override
                                                             public void run()
                                                                {
                                                                     login_error.setVisible(false);
                                                                }
                                                         };
                                     msg.schedule(display,3000);
                                }
                            
                    }
                    
                    catch(SQLException con_err)
                        {
                            con_err.printStackTrace();
                            login_error.setText("Error: Connection Failed");
                            login_error.setVisible(true);
                                     Timer msg = new Timer();
                                                     TimerTask display = new TimerTask()
                                                         {
                                                             @Override
                                                             public void run()
                                                                {
                                                                     login_error.setVisible(false);
                                                                }
                                                         };
                                     msg.schedule(display,3000);

                        }


                    catch(Exception ex)
                       {
                           ex.printStackTrace();
                       }
                }
        }
    }
       

public class UserLogin
    {

        public static void main(String args[])
            {
                Login log = new Login();
                log.LoginDisplay();
                System.out.println(log.connData[3]);
            }
    }