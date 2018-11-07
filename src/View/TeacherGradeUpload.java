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
import java.util.Timer;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class TeacherGradeUpload extends JPanel{
	
	TeacherFrame frame;
	JLabel Id;
	JLabel Course;
	JLabel Level;
	JLabel Grade;
	
	//JTextField id;
	//JTextField course;
	JComboBox id;
	
	JComboBox course;
	JComboBox level;
	JComboBox grade;
	

	
	JButton upgrd;
	JButton vwgrd;
	
	JLabel Pic;
	
	public TeacherGradeUpload(TeacherFrame frame)
	{	
		this.frame=frame;
		this.setLayout(null);
		this.setBackground(new Color(153, 102, 51));
		addComponentToPanel();
	}
	
	public void addComponentToPanel() {
		
		//this.Level= new JLabel("Level :");
		this.Id = new JLabel ("Student ID :");
		this.Course = new JLabel ("Course Name :");
		this.Grade = new JLabel("Grade :");
		
		//this.level= new JComboBox();
		this.id = new JComboBox();
		this.course = new JComboBox();
		this.grade = new JComboBox();
		
		this.upgrd = new JButton("Upload");
		this.vwgrd = new JButton("View Grade");
		
		//Level.setBounds(10,10,100,50);
		//level.setBounds(150,10,200,50);
		
		Course.setBounds(10,10,100,50);
		course.setBounds(150,10,200,50);
		
		Id.setBounds(10,60,100,50);
		id.setBounds(150,60,200,50);
		
		Grade.setBounds(10,110,100,50);
		grade.setBounds(150,110,200,50);
		
		upgrd.setBounds(65, 420, 90, 50);
		vwgrd.setBounds(170,420,140,50);
		
		this.Pic = new JLabel(new ImageIcon(this.getClass().getResource("/grdup.png")));
	    Pic.setBounds(440,120,256,256);this.add(Pic);
		//this.add(Level);this.add(level);
		this.add(Id);this.add(id);this.add(Course);this.add(course);this.add(Grade);this.add(grade);this.add(upgrd);this.add(vwgrd);
		
		course.addItem("Choose Course");
		id.addItem("Select Student ID");
		grade.addItem("Choose Grade");
		

		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/my database","root","");
			Statement stmt = con.createStatement();
			
			
			String tid = frame.teacher.getID();
			ResultSet rs = stmt.executeQuery("select `Course` from teachercourse WHERE `Teacher ID`='"+tid+"' ");

			while(rs.next())
			{	
				course.addItem(rs.getString(1));
			}	
		
			con.close();
			
		}catch(Exception e){
			System.out.println(e);
		}
		
		
		
//		try{
//			Class.forName("com.mysql.jdbc.Driver");
//			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/my database","root","");
//			Statement stmt = con.createStatement();
//			String cname = course.getItemAt( course.getSelectedIndex() ).toString();
//			ResultSet rs = stmt.executeQuery("select `OrgID` from user WHERE StdLevel=(select level from course WHERE `Course Name`='"+cname+"')");
//
//			while(rs.next())
//			{	
//				id.addItem(rs.getString(1));
//			}
//			
//			con.close();
//			
//			}catch(Exception e){
//			System.out.println(e);
//		}
		
		
		
		grade.addItem("4.00");
		grade.addItem("3.75");
		grade.addItem("3.50");
		grade.addItem("3.25");
		grade.addItem("3.00");
		grade.addItem("2.75");
		grade.addItem("2.50");
		
		
		
		course.addActionListener( new ActionListener(){
			
			public void actionPerformed(ActionEvent e)
			{
				try{
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/my database","root","");
					Statement stmt = con.createStatement();
					String cname = course.getItemAt( course.getSelectedIndex() ).toString();
					ResultSet rs = stmt.executeQuery("select `OrgID` from user WHERE StdLevel=(select level from course WHERE `Course Name`='"+cname+"')");
					
					id.removeAllItems();
					id.addItem("Select Student ID");
					while(rs.next())
					{	
						id.addItem(rs.getString(1));
					}
					
					con.close();
					
					}catch(Exception et){
					System.out.println(et);
				}
			}
			
		});
		
		
		
		
		upgrd.addActionListener( new ActionListener(){
					
					public void actionPerformed(ActionEvent e)
					{
						if(id.getItemAt( id.getSelectedIndex() ).toString().equals("Select Student ID") ||course.getItemAt( course.getSelectedIndex() ).toString().equals("Choose Course") || grade.getItemAt( grade.getSelectedIndex() ).toString().equals("Choose Grade") ) {
							JOptionPane.showMessageDialog(null," Choose/Select all the fields! ");
						}
						else {
						try{
							Class.forName("com.mysql.jdbc.Driver");
							Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/my database","root","");
							Statement stmt = con.createStatement();
							ResultSet rs = stmt.executeQuery("select * from grade");

							String coursen = course.getItemAt( course.getSelectedIndex() ).toString();
							String sid  = id.getItemAt( id.getSelectedIndex() ).toString();
							String grd	= grade.getItemAt( grade.getSelectedIndex() ).toString();
							
							int flag=0;
							
							while(rs.next())
							{
								if (rs.getString(1).equals(coursen) && rs.getString(2).equals(sid))
								{
									JOptionPane.showMessageDialog(null," Grade Already Uploaded!! ");
									flag=1;
									break;
								}
							}
							
							
							if(flag==0)
							{
								
								String sql = "INSERT INTO `grade`(`Course`, `StudentID`, `Grade`) VALUES ('"+coursen+"','"+sid+"','"+grd+"')";
								int a = stmt.executeUpdate(sql);
								JOptionPane.showMessageDialog(null," Grade Updated!! ");
							}

							
							con.close();
							
						}catch(Exception ee){
							System.out.println(ee);
						}}
						
					}
					
		});
		
		
		
		vwgrd.addActionListener( new ActionListener(){
			
			public void actionPerformed(ActionEvent e)
			{
				try{
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/my database","root","");
					Statement stmt = con.createStatement();
					
					String crsn = course.getItemAt( course.getSelectedIndex() ).toString();
					ResultSet rs = stmt.executeQuery("select * from grade WHERE Course = '"+crsn+"'");
					
					int rowCount=0;
					
					while(rs.next())
					{
						rowCount++;
						
					}
					
					String columns [] = {"Course","Student ID","Grade"};					
					String data[][] = new String[rowCount][3];
					
					int i=0;
					
					ResultSet rs1 = stmt.executeQuery("select * from grade WHERE Course = '"+crsn+"'");
					while(rs1.next())
					{	
						data[i][0] = rs1.getString(1);
						data[i][1] = rs1.getString(2);
						data[i][2] = rs1.getString(3);
						i++;
					}
					
					
				     JFrame jf = new JFrame("Grade Sheet");
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
				catch(Exception eee){
					System.out.println(eee);
				}
				
			}
			
});
		
					
		
	}
		
		
	}
