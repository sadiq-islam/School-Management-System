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

public class StudentViewAttendance extends JPanel{
		
	StudentFrame frame;
	JLabel Course;
	JLabel Tatd;
	
	JComboBox course;
	JTextField tatd;
	
	JButton vwatd;
	
	JLabel Pic;
	
	
	public StudentViewAttendance(StudentFrame frame)
	{	
		this.frame=frame;
		this.setLayout(null);
		this.setBackground(new Color(0, 153, 115));
		addComponentToPanel();
	}
	
	public void addComponentToPanel() {
		
		this.Course = new JLabel ("Course Name :");
		this.Tatd = new JLabel ("Total Attendance :");
		
		this.course = new JComboBox();
		this.tatd   = new JTextField();
		
		this.vwatd = new JButton("View Attendance History");
		
		Course.setBounds(10,10,100,50);
		course.setBounds(150,10,200,50);
		
		Tatd.setBounds(10,60,150,50);
		tatd.setBounds(225,60,50,50);
		
		vwatd.setBounds(90,420,200,50);
		
		
		this.Pic = new JLabel(new ImageIcon(this.getClass().getResource("/stdatn.png")));
	    Pic.setBounds(440,120,256,256);this.add(Pic);
				
		
		
		this.add(Course);this.add(course);this.add(Tatd);this.add(tatd);this.add(vwatd);
		
		course.addItem("Choose Course");
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/my database","root","");
			Statement stmt = con.createStatement();
			
			String sid = frame.student.getID();
			ResultSet rs = stmt.executeQuery("select DISTINCT `Course` from attendance WHERE `StudentID`='"+sid+"' ");

			while(rs.next())
			{	
				course.addItem(rs.getString(1));
			}	
		
			con.close();
			
		}catch(Exception e){
			System.out.println(e);
		}
		
		course.addActionListener( new ActionListener(){
			
			public void actionPerformed(ActionEvent e)
			{	
				if(course.getItemAt( course.getSelectedIndex() ).toString().equals("Choose Course")) {
					
				}
				else {
				try{
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/my database","root","");
					Statement stmt = con.createStatement();
					String sid = frame.student.getID();
					String cname = course.getItemAt( course.getSelectedIndex() ).toString();
					ResultSet rs = stmt.executeQuery("SELECT SUM(Attendance) FROM attendance WHERE `Course`='"+cname+"'  AND  `StudentID`='"+sid+"'");
					
					while(rs.next())
					{	
						tatd.setText(rs.getString(1));
					}
					
					
					con.close();
					
					}catch(Exception et){
					System.out.println(et);
				}}
			}
			
		});
		
		
		
		vwatd.addActionListener( new ActionListener(){
			
			public void actionPerformed(ActionEvent e)
			{	
				
				if(course.getItemAt( course.getSelectedIndex() ).toString().equals("Choose Course")) 
				{
					JOptionPane.showMessageDialog(null," Kindly Choose A Course!!! ");
				}
				
				else {
				try{
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/my database","root","");
					Statement stmt = con.createStatement();
					String sid = frame.student.getID();
					String cname = course.getItemAt( course.getSelectedIndex() ).toString();
					ResultSet rs = stmt.executeQuery("select `Date`,`Attendance` from attendance WHERE StudentID='"+sid+"' AND Course='"+cname+"'");
					int rowCount=0;
					
					while(rs.next())
					{
						rowCount++;
						
					}
					
					String columns [] = {"Date","Attendance"};					
					String data[][] = new String[rowCount][3];
					
					int i=0;
					
					ResultSet rs1 = stmt.executeQuery("select `Date`,`Attendance` from attendance WHERE StudentID='"+sid+"' AND Course='"+cname+"'");
					while(rs1.next())
					{	
						data[i][0] = rs1.getString(1);
						data[i][1] = rs1.getString(2);
						i++;
					}
					
					
				     JFrame jf = new JFrame("Attendance Sheet : "+cname+"");
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
					
					}catch(Exception et){
					System.out.println(et);
				}}
			}
			
		});
		
		
		
	}
	
	
}
