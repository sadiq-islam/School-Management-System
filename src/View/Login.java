package View;

import javax.swing.*;

import User.Admin;
import User.Student;
import User.Teacher;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.xml.bind.*;

public class Login extends JFrame {
	
	JButton btn1;
	JButton frgtps;
	
	JLabel lbl1;
	JLabel lbl2;
	
	JTextField dg1;
	JPasswordField dg2;
	
	
	JLabel pc;
	public Login()
	{
		this.setTitle("School Management System Login");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(550,300);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setIconImage(new ImageIcon(this.getClass().getResource("/students-128.png")).getImage());

		addComponentToFrame();
		this.setVisible(true);
	}
	
	public void addComponentToFrame()
	{
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0,0,350,300);
		panel.setBackground(new Color(119, 119, 60));
		
		this.dg1 = new JTextField();
		this.dg2 = new JPasswordField();
		
		
		this.lbl1 = new JLabel("Username : ");
		this.lbl2 = new JLabel("Password : ");

		this.pc = new JLabel(new ImageIcon(this.getClass().getResource("/students-128.png")));
		
		lbl1.setBounds(10,15,100,50);
		dg1.setBounds(110,15,200,50);
		
		lbl2.setBounds(10,65,100,50);
		dg2.setBounds(110,65,200,50);
		
		
		
		
		/////
		pc.setBounds(360,60,128,128);
		/////
		
		this.btn1 = new JButton("Login");
		this.frgtps = new JButton("Forgot Password");
		btn1.setBounds(120, 190, 100, 30);
		frgtps.setBounds(150, 190, 140, 30);
		
		
	 	panel.add(lbl1);
		panel.add(dg1); 
		
		panel.add(lbl2);
		panel.add(dg2);
		
		
		panel.add(btn1);
		//panel.add(frgtps);
		panel.add(pc);
		
		this.add(panel);
		
		loginButtonLis lis = new loginButtonLis(this);
		btn1.addActionListener(lis);
		
		

		
	}
		


	
		//inner class 
		public class loginButtonLis implements ActionListener{
			
			Login login;

			public loginButtonLis(Login login) {
				this.login = login;
			}
			
			public void actionPerformed(ActionEvent e)
			{
				if(dg1.getText().equals("") || dg2.getText().equals("")) 
				{
					JOptionPane.showMessageDialog(null,"Kindly fill up all the fields!!!");
				}
			else {
				 try{
						Class.forName("com.mysql.jdbc.Driver");
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/my database","root","");
						
						Statement stmt = con.createStatement();
						ResultSet rs = stmt.executeQuery("SELECT * FROM `user` WHERE type='admin'");
						
						Statement stmt1 = con.createStatement();
						ResultSet rs1 = stmt1.executeQuery("SELECT * FROM `user` WHERE type='teacher'");
						
						Statement stmt2 = con.createStatement();
						ResultSet rs2 = stmt2.executeQuery("SELECT * FROM `user` WHERE type='student'");
						
						int flag=0;
						
						 while(rs.next())
						{
							 if ( rs.getString(6).equals(login.dg1.getText()) && rs.getString(7).equals(login.dg2.getText()) )
							 {	
								 Admin admin = new Admin( rs.getString(1) , rs.getString(2) , rs.getString(3) , rs.getString(4) , rs.getString(6) , rs.getString(7) , rs.getString(8) );
								 AdminFrame a = new AdminFrame(admin);
								 
								 JOptionPane.showMessageDialog(null,"Welcome,"+rs.getString(1)+"!!!");
								 
								 login.dispose();
								 
								 flag=1;
								 
								 if(flag==1)
								 {
									 break;
								 }								 
								 
							 }

						}
						 
						while(rs1.next())
						 {	 
							 if(flag==1)
							 {
								 break;
							 }
							
							
							 if( rs1.getString(6).equals(login.dg1.getText()) && rs1.getString(7).equals(login.dg2.getText()) )
							 {	 
								 Teacher teacher = new Teacher( rs1.getString(1) , rs1.getString(2) , rs1.getString(3) , rs1.getString(4) , rs1.getString(6) , rs1.getString(7) , rs1.getString(8) );
								 TeacherFrame t = new TeacherFrame(teacher);
								 
								 JOptionPane.showMessageDialog(null,"Welcome,"+rs1.getString(1)+"!!!");
								 
								 login.dispose();
								 
								 flag=1;
								 
								 if(flag==1)
								 {
									 break;
								 }				
							 }
							 
						 }
						 
						 while(rs2.next())
						 {	
							 if(flag==1)
							 {
								 break;
							 }				

							 
							 if ( rs2.getString(6).equals(login.dg1.getText()) && rs2.getString(7).equals(login.dg2.getText()) )
							 {	 
								 Student student = new Student( rs2.getString(1) , rs2.getString(2) , rs2.getString(3) , rs2.getString(4) , rs2.getString(5) , rs2.getString(6) , rs2.getString(7) , rs2.getString(8) );
								 StudentFrame t = new StudentFrame(student);
								 
								 JOptionPane.showMessageDialog(null,"Welcome,"+rs2.getString(1)+"!!!");
								 
								 login.dispose();
								 
								 flag=1;
								 
								 if(flag==1)
								 {
									 break;
								 }				
							 }

						 }	
						 
						 if(flag==0)
							 {
								 JOptionPane.showMessageDialog(null," Wrong Username or Password!Try Again! ");
							 }		 
						}
						 
					catch(Exception ee) 
				    {
						System.out.println(ee);
					}	 
			}		 		 
				 }

			}

		}
	
	
