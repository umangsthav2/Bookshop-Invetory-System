import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.*;

class AHome extends JFrame implements ActionListener
    {
        JLabel title,subject;
        JButton book_section,order_section,staff_section,logout;

        Font info_font = new Font("Poppins",Font.BOLD,20);
        Font title_font = new Font("Poppins",Font.BOLD,50);
        Font subject_font = new Font("Poppins",Font.BOLD,35);
        ImageIcon books_icon = new ImageIcon("./assets/icons/1x/books.png");
        ImageIcon sales_icon = new ImageIcon("./assets/icons/1x/sales.png");
        ImageIcon staffs_icon = new ImageIcon("./assets/icons/1x/staffs.png");
        ImageIcon title_icon = new ImageIcon("./assets/icons/logo.png");

        Border test_border = BorderFactory.createLineBorder(Color.black);
      
        static String UserNAME= new Login().UserNAME;

        String MainMenu()
            {
                title = new JLabel("BOOKSHOP INVENTORY SYSTEM",JLabel.CENTER);
                title.setBounds(200,30,900,50);
                title.setFont(title_font);
                
                subject = new JLabel("Select an Option from Below",JLabel.CENTER);
                subject.setBounds(200,150,900,50);
                subject.setFont(subject_font);
                
                book_section = new JButton("BOOKS");
                book_section.setBounds(50,250,300,300);
                book_section.addActionListener(this);
                book_section.setIcon(books_icon);
                book_section.setVerticalTextPosition(JLabel.BOTTOM);
                book_section.setHorizontalTextPosition(JLabel.CENTER);
                book_section.setFocusable(false);
                book_section.setFont(subject_font);

                staff_section = new JButton("STAFFS");
                staff_section.setBounds(475,250,300,300);
                staff_section.addActionListener(this);
                staff_section.setIcon(staffs_icon);
                staff_section.setVerticalTextPosition(JLabel.BOTTOM);
                staff_section.setHorizontalTextPosition(JLabel.CENTER);
                staff_section.setFocusable(false);
                staff_section.setFont(subject_font);

                order_section = new JButton("SALES");
                order_section.setBounds(900,250,300,300);
                order_section.addActionListener(this);
                order_section.setIcon(sales_icon);
                order_section.setVerticalTextPosition(JLabel.BOTTOM);
                order_section.setHorizontalTextPosition(JLabel.CENTER);
                order_section.setFocusable(false);
                order_section.setFont(subject_font);

                logout = new JButton("LOGOUT");
                logout.setBounds(575,600,120,40);
                logout.addActionListener(this);
                logout.setFont(info_font);
                logout.setFocusable(false);


                // This code prevents SQL Injection Attacks
                

                if(new Login().UserNAME==null)
                    {
                        book_section.setEnabled(false);   
                        staff_section.setEnabled(false);    
                        order_section.setEnabled(false);  
                    }

                // Adding components to JFrame
                add(title);
                add(subject);

                add(book_section);  add(staff_section); add(order_section);
                                        add(logout);
                
                setVisible(true);
                return this.UserNAME;               
            }

        //Constructor
        AHome()
            {
                setSize(1280,720);
                setIconImage(title_icon.getImage());
                setDefaultCloseOperation(3);
                setTitle("Bookshop Inventory System | Home");
                setLayout(null);
                setLocationRelativeTo(null);
                setResizable(false);
                
            }

        @Override
        public void actionPerformed(ActionEvent en)
            {
               if(en.getSource()==book_section)
                    {
                       new BPanel().MainMenu();
                       setVisible(false);
                    }

                else if(en.getSource()==staff_section)
                    {
                        new SPanel().MainMenu();
                       setVisible(false);
                    }

                else if(en.getSource()==order_section)
                    {
                        new OPanel().MainMenu();
                       setVisible(false);
                    }

                else if(en.getSource()==logout)
                    {
                        new Login().LoginDisplay();
                        UserNAME= new Login().UserNAME;
                        // setVisible(false);
                        dispose();
                    }
            
            }
    }

public class AdminHome
    {
        public static void main(String args[])
            {
                 AHome run = new AHome();
                run.MainMenu();
            }
    }