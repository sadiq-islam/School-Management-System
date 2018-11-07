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
import javax.swing.JPanel;
import javax.swing.JTextField;

public class StudentNoteDownload extends JPanel{
	
	StudentFrame frame;
	JLabel Course;
	JLabel Note;
	JComboBox course;
	JComboBox note;
	JButton dnldnts;
	JLabel Pic;

	 
	public StudentNoteDownload(StudentFrame frame)
	{	
		this.frame=frame;
		this.setLayout(null);
		this.setBackground(new Color(0, 204, 153));
		
		addComponentToPanel();
	}
	
	public void addComponentToPanel() {
		this.Course = new JLabel("Course :");
		this.course = new JComboBox();
		this.Note = new JLabel("Note :");
		this.note = new JComboBox();
		
		this.dnldnts=new JButton("Download Note");
		
		
		Course.setBounds(10,10,100,50);
		course.setBounds(150,10,200,50);
		Note.setBounds(10,60,100,50);
		note.setBounds(150,60,200,50);
		
		dnldnts.setBounds(170,420,140,50);
		
		this.Pic = new JLabel(new ImageIcon(this.getClass().getResource("/ntdnld.png")));
	    Pic.setBounds(440,120,256,256);this.add(Pic);
		
		
		course.addItem("Choose Course");
		note.addItem("Choose Note");	
	
		
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
		
		this.add(Course);this.add(course);this.add(dnldnts);
		this.add(Note);this.add(note);
		
		
		course.addActionListener( new ActionListener(){
			
			public void actionPerformed(ActionEvent e)
			{	
				try{
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/my database","root","");
					Statement stmt = con.createStatement();
					String cname = course.getItemAt( course.getSelectedIndex() ).toString();
					ResultSet rs = stmt.executeQuery("select `Filename` from uploadedfile WHERE course='"+cname+"'");
					
					note.removeAllItems();
					
					note.addItem("Choose Note");
					
					while(rs.next())
					{	
						note.addItem(rs.getString(1));
					}
				
					con.close();
					
					}catch(Exception et){
					System.out.println(et);
				}

			}
		});
		
		
		
	}
}