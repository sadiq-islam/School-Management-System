package View;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;

import Other.Course;
import User.Admin;

public class AdminAddCourse extends JPanel{
	
	AdminFrame frame;
	
	JLabel CourseName;
	JLabel Level;
	JLabel Notice;

	JTextField courseName;
	JTextField level;
	JTextField notice;

	JButton Enroll;
	JButton Sas;//show all students/teachers
	
	JLabel Pic;
	String picpath;
	
	public AdminAddCourse(AdminFrame frame)
	{	
		this.frame=frame;
		this.setLayout(null);
		this.setBackground(new Color(153, 204, 0));
		addComponentToPanel();
	}
	
	public void addComponentToPanel() {
		
		this.CourseName=new JLabel("Course Name :");
		this.Level=new JLabel("Level :");
        this.Notice=new JLabel("Notice :");
		
		
		
		this.courseName=new JTextField();
		this.level=new JTextField();
		this.notice=new JTextField();

		
		this.Enroll=new JButton("Add");
		this.Sas=new JButton("Course List");
		
		CourseName.setBounds(10,10,100,50);
		courseName.setBounds(150,10,200,50);
		
		Level.setBounds(10,60,100,50);
		level.setBounds(150,60,200,50);
		
		//Notice.setBounds(10,110,100,50);
		//notice.setBounds(150,110,200,50);
		
		
		Enroll.setBounds(65, 420, 90, 50);
		Sas.setBounds(170,420,115,50);
		
		
		this.Pic = new JLabel(new ImageIcon(this.getClass().getResource("/crss.png")));
	    Pic.setBounds(440,120,256,256);this.add(Pic);
		
		this.add(CourseName);this.add(courseName);this.add(Level);this.add(level);//this.add(Notice);this.add(notice);
		this.add(Enroll);this.add(Sas);
		
		
		
		Enroll.addActionListener( new ActionListener(){
			
			public void actionPerformed(ActionEvent e)
			{	
				if(courseName.getText().equals("") || level.getText().equals("")) 
				{
					 JOptionPane.showMessageDialog(null," Kindly fill up all the fields! ");
				}
				
				else
				{
				int flag=0;
				 try{
						Class.forName("com.mysql.jdbc.Driver");
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/my database","root","");
						
						Statement stmt = con.createStatement();
						ResultSet rs = stmt.executeQuery("SELECT * FROM `course`");

						
						 while(rs.next())
						{ if(rs.getString(1).equals(courseName.getText()))
								 {
									 JOptionPane.showMessageDialog(null," Choose another Course Name! ");
									 courseName.setText("");
									 flag=1;
									 break;
								 }
								 }
							 
						}
						 
						 
					catch(Exception ee) 
				    {
						System.out.println(ee);
					}		
				 
				 if(flag==0) 
				 {
					 	Admin.addCourse(courseName.getText(),level.getText(),"" );
					 	courseName.setText("");
					 	level.setText("");
					 	
					 	
					 	flag=1;

					 	JOptionPane.showMessageDialog(null," Course Added! ");					 
				 }
				
			}
			}		
		});
		
		
		Sas.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				
				ArrayList<Course> list = frame.admin.courseList();
				String columns [] = {"Course Name","Level"/*,"Notice"*/};
				String data[][] = new String[list.size()][2];


				for(int i=0;i<list.size();i++)
				{	
					data[i][0] = list.get(i).getCourseName();
					data[i][1] = list.get(i).getLevel();;
					//data[i][2] = list.get(i).getNotice();
				}
	
	
				
			
					 JFrame jf = new JFrame("Course List");
			         jf.setSize(500, 500);
			         jf.setVisible(true);
			         jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			         jf.setLocationRelativeTo(null);

			         JTable jt = new JTable(data, columns)
					   {
		               // Determines if data can be entered by users
		               public boolean isCellEditable(int data, int columns)
		               {
		                   return false;
		               }
		   
		               //  Creates cells for the table         
		               public Component prepareRenderer(
		                            TableCellRenderer r, int data, int columns)
		               {
		                   Component c = super.prepareRenderer(r, data, columns);
		                  
		                   // Every even numbers
		                   if (data % 2 == 0)
		                       c.setBackground(Color.WHITE);
		    
		                   else
		                       c.setBackground(Color.LIGHT_GRAY);
		    
		                   return c;
		               }
		         };
		  
		         // Set size of table     
		         jt.setPreferredScrollableViewportSize(new Dimension(450, 63));
		
		         // This will resize the height of the table automatically 
		         // to all data without scrolling. 
		         jt.setFillsViewportHeight(true);
		
		         JScrollPane jps = new JScrollPane(jt);
		         jf.add(jps);
				
			}
		});
		
			}
		
		
	}
