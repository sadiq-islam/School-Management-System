package View;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

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

public class StudentProfile extends JPanel{
	
	StudentFrame frame;
	
	JLabel Name;
	JLabel DOB;
	JLabel Email;
	JLabel ID;
	JLabel Level;
	JLabel Type;
	
	JTextField name;
	JTextField dob;
	JTextField email;
	JTextField id;
	JTextField level;
	JTextField type;
	
	JButton vwgrd;
	
	JButton logout;
	
	JLabel Pic;
	String picpath;
	
	
	public StudentProfile(StudentFrame frame)
	{	
		this.frame=frame;
		this.setLayout(null);
		this.setBackground(new Color(0, 128, 96));
		addComponentToPanel();
	}
	
	public void addComponentToPanel() 
	{
		this.Name=new JLabel("Name :");
		this.DOB=new JLabel("DOB :");
		this.Email=new JLabel("E-Mail :");
		this.ID=new JLabel("ID :");
		this.Level=new JLabel("Level :");
		this.Type=new JLabel("Type :");
		
		
		
		this.name=new JTextField();
		this.dob=new JTextField();
		this.email=new JTextField();
		this.id=new JTextField();
		this.level=new JTextField();
		this.type=new JTextField();
		
		this.vwgrd = new JButton("Grade Sheet");
		this.logout =new JButton("Logout");
		
		Name.setBounds(10,10,100,50);
		name.setBounds(150,10,200,50);
		
		DOB.setBounds(10,60,100,50);
		dob.setBounds(150,60,200,50);
		
		Email.setBounds(10,110,100,50);
		email.setBounds(150,110,200,50);
		
		ID.setBounds(10,160,100,50);
		id.setBounds(150,160,200,50);
		
		Level.setBounds(10,210,100,50);
		level.setBounds(150,210,200,50);
		
		Type.setBounds(10,260,100,50);
		type.setBounds(150,260,200,50);
		
		vwgrd.setBounds(65, 420, 130, 50);
		
		
		logout.setBounds(590, 430, 100, 40);
		
		
		this.Pic = new JLabel();
	    Pic.setBounds(440,30,256,256);
		
	    
	    try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/my database","root","");
			Statement stmt = con.createStatement();
			
			
			String sid = frame.student.getID();
			ResultSet rs = stmt.executeQuery("select `Picture` from user WHERE `OrgID`='"+sid+"' ");
			
			while(rs.next())
			{
				picpath = rs.getString(1);
			}
			
			Pic.setIcon(ResizeImage(picpath));
		
			con.close();
			
		}catch(Exception e){
			System.out.println(e);
		}
		
		
		name.setText(frame.student.getName());
		dob.setText(frame.student.getDOB());
		email.setText(frame.student.getEmail());
		id.setText(frame.student.getID());
		level.setText(frame.student.getLevel());
		type.setText(frame.student.getType());
		
		name.setEditable(false);
		dob.setEditable(false);
		email.setEditable(false);
		id.setEditable(false);
		level.setEditable(false);
		type.setEditable(false);
		
		
		this.add(Name);this.add(name);this.add(DOB);this.add(dob);this.add(Email);this.add(email);
		this.add(ID);this.add(id);this.add(Level);this.add(level);this.add(Type);this.add(type);
		this.add(vwgrd);this.add(logout);this.add(Pic);
		
		slogoutButtonLis lis = new slogoutButtonLis(this.frame);
        logout.addActionListener(lis);
		
		vwgrd.addActionListener( new ActionListener(){
			
			public void actionPerformed(ActionEvent e)
			{
				try{
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/my database","root","");
					Statement stmt = con.createStatement();
					
					ResultSet rs = stmt.executeQuery("select * from grade WHERE StudentID = '"+frame.student.getID()+"'");
					
					int rowCount=0;
					
					while(rs.next())
					{
						rowCount++;
						
					}
					
					String columns [] = {"Course","Grade"};					
					String data[][] = new String[rowCount][2];
					
					int i=0;
					
					ResultSet rs1 = stmt.executeQuery("select * from grade WHERE StudentID = '"+frame.student.getID()+"'");
					while(rs1.next())
					{	
						data[i][0] = rs1.getString(1);
						data[i][1] = rs1.getString(3);
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
	
	public ImageIcon ResizeImage(String imgPath){
        ImageIcon MyImage = new ImageIcon(imgPath);
        Image img = MyImage.getImage();
        Image newImage = img.getScaledInstance(Pic.getWidth(), Pic.getHeight(),Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImage);
        return image;
    }
	
	
}


class slogoutButtonLis implements ActionListener{
	
	StudentFrame frame;

	public slogoutButtonLis(StudentFrame frame) {
		this.frame=frame;
	}

	public void actionPerformed(ActionEvent e)
	{
		int a=JOptionPane.showConfirmDialog(null, "Sure To Logout???");  
		if(a==JOptionPane.YES_OPTION){
			frame.dispose();
			Login login = new Login();      	      
		}  

		if(a==JOptionPane.CANCEL_OPTION){
			      
		}
		
	}
	
}
	





