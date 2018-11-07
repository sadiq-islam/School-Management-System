package View;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class StudentNotice extends JPanel{
	
	StudentFrame frame;
	
	JLabel Course;
	JLabel Nttopic;
	JComboBox course;
	JComboBox nttopic;
	JButton vwntc;
	
	JLabel Pic;
	
	public StudentNotice(StudentFrame frame)
	{	
		this.frame=frame;
		this.setLayout(null);
		this.setBackground(new Color(0, 179, 134));
		addComponentToPanel();
	}
	
	public void addComponentToPanel() {
		
		this.Course=new JLabel("Course :");
		this.Nttopic=new JLabel("Topic :");
		
		
		this.course = new JComboBox();
		this.nttopic  = new JComboBox();
		
		this.vwntc=new JButton("View Notice"); 
		
		Course.setBounds(10,10,100,50);
		course.setBounds(150,10,200,50);
		
		Nttopic.setBounds(10,60,100,50);
		nttopic.setBounds(150,60,300,50);
		
		vwntc.setBounds(80, 420, 140, 50);
		
		course.addItem("Choose Course");
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/my database","root","");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select `Course Name` from course WHERE `Level`='"+frame.student.getLevel()+"' ");

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
				try{
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/my database","root","");
					Statement stmt = con.createStatement();
					String cname = course.getItemAt( course.getSelectedIndex() ).toString();
					ResultSet rs = stmt.executeQuery("select `Topic` from notice WHERE course='"+cname+"'");
					
					nttopic.removeAllItems();
					
					
					nttopic.addItem("Select Topic");
					
					while(rs.next())
					{	
						nttopic.addItem(rs.getString(1));
					}
				
					con.close();
					
					}catch(Exception et){
					System.out.println(et);
				}

			}
		});
		
		
		this.Pic = new JLabel(new ImageIcon(this.getClass().getResource("/stdntc.png")));
	    Pic.setBounds(440,120,256,256);this.add(Pic);
				
		
		
		
		this.add(Course);this.add(course);this.add(vwntc);this.add(Nttopic);this.add(nttopic);
		
	
		
		
			vwntc.addActionListener( new ActionListener(){
			
			public void actionPerformed(ActionEvent e)
			{
				if(course.getItemAt( course.getSelectedIndex() ).toString().equals("Choose Course")  ||  nttopic.getItemAt( nttopic.getSelectedIndex() ).toString().equals("Select Notice"))
				{
					JOptionPane.showMessageDialog(null,"Kindly Choose Course/Notice First!! ");
				}
			else {
				
				
				try{
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/my database","root","");
					Statement stmt = con.createStatement();
					String coursen = course.getItemAt( course.getSelectedIndex() ).toString();
					String tp = nttopic.getItemAt( nttopic.getSelectedIndex() ).toString();
					ResultSet rs = stmt.executeQuery("select Notice from notice WHERE `Course`='"+coursen+"'  AND `Topic`='"+tp+"'");

					while(rs.next()) {
						JOptionPane.showMessageDialog(null,""+rs.getString(1)+"","Notice : "+tp,JOptionPane.INFORMATION_MESSAGE);
					}
					
					con.close();
					
				}catch(Exception ee){
					System.out.println(ee);
				}  
			
			}
}
	});		
		
		
	}
}