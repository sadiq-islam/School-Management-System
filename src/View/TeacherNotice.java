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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;

public class TeacherNotice extends JPanel{
	
	TeacherFrame frame;
	
	JLabel Course;
	JLabel Notice;
	JLabel Nttopic;
	
	JComboBox course;
	JTextArea notice;
	JTextField nttopic;
	
	JButton upntc;
	JButton vwntc;
	
	JLabel Pic;
	
	public TeacherNotice(TeacherFrame frame)
	{	
		this.frame=frame;
		this.setLayout(null);
		this.setBackground(new Color(172, 115, 57));
		addComponentToPanel();
	}
	
	public void addComponentToPanel() {
		
		this.Course=new JLabel("Course :"); 
		this.Notice=new JLabel("Notice :");
		this.Nttopic=new JLabel("Topic :");
		
		this.course = new JComboBox();
		this.notice=new JTextArea();
		this.nttopic=new JTextField();
		
		this.upntc=new JButton("Update");
		this.vwntc=new JButton("View Notice"); 
		
		Course.setBounds(10,10,100,50);
		course.setBounds(150,10,200,50);
		
		Nttopic.setBounds(10,60,100,50);
		nttopic.setBounds(150,60,300,50);
		
		Notice.setBounds(10,110,100,50);
		notice.setBounds(150,110,300,200);
		
		upntc.setBounds(65, 420, 90, 50);
		vwntc.setBounds(170,420,140,50);
		
		notice.setLineWrap(true);
		
		
		this.Pic = new JLabel(new ImageIcon(this.getClass().getResource("/ntcup.png")));
	    Pic.setBounds(495,120,256,256);this.add(Pic);
		
		
		this.add(Notice);this.add(notice);this.add(Course);this.add(course);this.add(upntc);this.add(vwntc);
		this.add(Nttopic);this.add(nttopic);
		
		
		course.addItem("Choose Course");
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
		
		
		
		upntc.addActionListener( new ActionListener(){
			
			public void actionPerformed(ActionEvent e)
			{
				if(course.getItemAt( course.getSelectedIndex() ).toString().equals("Choose Course") ||  nttopic.getText().equals("")  ||  notice.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null,"Kindly Choose/Fill UP All The Fields First!! ");
				}
				else {
					
					   int b=JOptionPane.showConfirmDialog(null,"Update Notice?");  
					   if(b==JOptionPane.YES_OPTION){  
					       try{
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/my database","root","");
					Statement stmt = con.createStatement();
					String crs = course.getItemAt( course.getSelectedIndex() ).toString();
					
					String sql = "INSERT  INTO notice (`Course`,`Topic`,`Notice`) VALUES  ('"+crs+"', '"+nttopic.getText()+"' , '"+notice.getText()+"' )";
					int a = stmt.executeUpdate(sql);
					
					nttopic.setText("");
					notice.setText("");
					
					con.close();
					
				}catch(Exception ee){
					System.out.println(ee);
				}  
					   }
					   
					   if(b==JOptionPane.CANCEL_OPTION)  
					   {notice.setText("");nttopic.setText("");}
					   
					   
				}
			}
		
});
		
			vwntc.addActionListener( new ActionListener(){
			
			public void actionPerformed(ActionEvent e)
			{
				if(course.getItemAt( course.getSelectedIndex() ).toString().equals("Choose Course"))
				{
					JOptionPane.showMessageDialog(null,"Kindly Choose A Course First!! ");
				}
			else {
				
				
				try{
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/my database","root","");
					Statement stmt = con.createStatement();
					String coursen = course.getItemAt( course.getSelectedIndex() ).toString();
					ResultSet rs = stmt.executeQuery("select `Topic`,`Notice` from notice WHERE `Course`='"+coursen+"'");

					int rowCount=0;
					
					while(rs.next())
					{
						rowCount++;
					}
					
					String columns [] = {"Topic","Notice"};					
					String data[][] = new String[rowCount][2];
					
					int i=0;
					
					ResultSet rs1 = stmt.executeQuery("select `Topic`,`Notice` from notice WHERE `Course`='"+coursen+"'");
					while(rs1.next())
					{	
						data[i][0] = rs1.getString(1);
						data[i][1] = rs1.getString(2);
						//System.out.println(data[i][0]);
						i++;
					}
					
					
				     JFrame jf = new JFrame(coursen+" : Notice List");
			         jf.setSize(350, 400);
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
					
				}catch(Exception ee){
					System.out.println(ee);
				}  
			
			}
}
	});		
		
	}
	}
