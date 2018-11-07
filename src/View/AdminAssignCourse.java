package View;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;

import User.Student;

import java.sql.*;
import java.util.ArrayList;

public class AdminAssignCourse extends JPanel{
	AdminFrame frame;
	JLabel Id;
	JLabel Course;
	
	//JTextField id;
	//JTextField course;
	JComboBox id;
	JComboBox course;
	
	JButton assign;
	JButton assignedCourse;
	
	JLabel Pic;
	String picpath;
	
	public AdminAssignCourse(AdminFrame frame)
	{	
		this.frame=frame;
		this.setLayout(null);
		this.setBackground(new Color(207, 255, 77));
		addComponentToPanel();
	}
	
	public void addComponentToPanel()
	{
			this.Id = new JLabel ("Teacher ID :");
			this.Course = new JLabel ("Course Name :");
	
			this.id = new JComboBox();
			this.course = new JComboBox();
			
			try{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/my database","root","");
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("select * from user WHERE type='teacher'");

				while(rs.next())
				{	
					id.addItem(rs.getString(4));
				}	
				
				con.close();
				
			}catch(Exception e){
				System.out.println(e);
			}
			
			
			try{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/my database","root","");
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("select * from course");

				while(rs.next())
				{	
					course.addItem(rs.getString(1));
				}	
				
				con.close();
				
			}catch(Exception e){
				System.out.println(e);
			}
			
			
	
			this.assign=new JButton("Assign");
			this.assignedCourse=new JButton("Assigned Course List");
			
			Id.setBounds(10,10,100,50);
			id.setBounds(150,10,200,50);
			
			Course.setBounds(10,60,100,50);
			course.setBounds(150,60,200,50);
			
			assign.setBounds(65, 420, 90, 50);
			assignedCourse.setBounds(170,420,165,50);
			
			id.setToolTipText("Example : 2-**");
			
			this.Pic = new JLabel(new ImageIcon(this.getClass().getResource("/asntcr.png")));
		    Pic.setBounds(440,120,256,256);this.add(Pic);
	
			
			this.add(Id);this.add(id);
			this.add(Course);this.add(course);
			this.add(assign);this.add(assignedCourse);
			
			
			
			assign.addActionListener( new ActionListener(){
				
				public void actionPerformed(ActionEvent e)
				{
					

					try{
						Class.forName("com.mysql.jdbc.Driver");
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/my database","root","");
						Statement stmt = con.createStatement();
						ResultSet rs = stmt.executeQuery("select * from teachercourse");
						
						String tid=id.getItemAt( id.getSelectedIndex() ).toString();
						String crs=course.getItemAt( course.getSelectedIndex() ).toString();
						int flag=0;
						while(rs.next() )
						{
							if( rs.getString(2).equals(crs) )
							{
								JOptionPane.showMessageDialog(null," Course Already Assigned!! ");
								flag=1;
								break;
							}
						}
						
							
						if(flag==0)
						{
							String sql ="INSERT INTO `teachercourse`(`Teacher ID`, `Course`) VALUES ('"+tid+"','"+crs+"' )";

							int a = stmt.executeUpdate(sql);
							JOptionPane.showMessageDialog(null," Course Assigned!! ");
						}
						
						con.close();
						
					}
					catch(Exception ee){
						System.out.println(ee);
					}
				}
				});
			
			
			
			assignedCourse.addActionListener( new ActionListener(){
				
				public void actionPerformed(ActionEvent e){
					
					try{
						Class.forName("com.mysql.jdbc.Driver");
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/my database","root","");
						Statement stmt = con.createStatement();
						ResultSet rs = stmt.executeQuery("select * from teachercourse");
						
						int rowCount=0;
						
						while(rs.next())
						{
							rowCount++;
							//System.out.println("  "+rs.getString(1)+"     "+rs.getString(2)+"  ");
						}
						
						String columns [] = {"Teacher ID","Course"};					
						String data[][] = new String[rowCount][2];
						
						int i=0;
						
						ResultSet rs1 = stmt.executeQuery("select * from teachercourse");
						while(rs1.next())
						{	
							data[i][0] = rs1.getString(1);
							data[i][1] = rs1.getString(2);
							i++;
						}
						
						
					     JFrame jf = new JFrame("Assigned Course List");
				         jf.setSize(700, 500);
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
							

						con.close();
						
					}
					catch(Exception ee){
						System.out.println(ee);
					}

				}
			
	});
	}}


