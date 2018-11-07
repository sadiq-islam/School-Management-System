package View;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TeacherProfile extends JPanel{
	TeacherFrame frame;
	
	JLabel Name;
	JLabel DOB;
	JLabel Email;
	JLabel ID;
	JLabel Type;
	
	JTextField name;
	JTextField dob;
	JTextField email;
	JTextField id;
	JTextField type;
	
	JButton logout;
	
	JLabel Pic;
	String picpath;
	
	public TeacherProfile(TeacherFrame frame)
	{	
		this.frame=frame;
		this.setLayout(null);
		this.setBackground(new Color(115, 77, 38));
		addComponentToPanel();
	}
	
	public void addComponentToPanel() 
	{
		this.Name=new JLabel("Name :");
		this.DOB=new JLabel("DOB :");
		this.Email=new JLabel("E-Mail :");
		this.ID=new JLabel("ID :");
		this.Type=new JLabel("Type :");
		
		
		
		this.name=new JTextField();
		this.dob=new JTextField();
		this.email=new JTextField();
		this.id=new JTextField();
		this.type=new JTextField();
		
		this.logout =new JButton("Logout");
		
		Name.setBounds(10,10,100,50);
		name.setBounds(150,10,200,50);
		
		DOB.setBounds(10,60,100,50);
		dob.setBounds(150,60,200,50);
		
		Email.setBounds(10,110,100,50);
		email.setBounds(150,110,200,50);
		
		ID.setBounds(10,160,100,50);
		id.setBounds(150,160,200,50);
		
		Type.setBounds(10,210,100,50);
		type.setBounds(150,210,200,50);
		
		logout.setBounds(590, 420, 100, 40);
		
		this.Pic = new JLabel();
	    Pic.setBounds(440,20,256,256);
		
	    
	    try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/my database","root","");
			Statement stmt = con.createStatement();
			
			
			String tid = frame.teacher.getID();
			ResultSet rs = stmt.executeQuery("select `Picture` from user WHERE `OrgID`='"+tid+"' ");
			
			while(rs.next())
			{
				picpath = rs.getString(1);
			}
			
			Pic.setIcon(ResizeImage(picpath));
		
			con.close();
			
		}catch(Exception e){
			System.out.println(e);
		}
	    
	    
		
		
		name.setText(frame.teacher.getName());
		dob.setText(frame.teacher.getDOB());
		email.setText(frame.teacher.getEmail());
		id.setText(frame.teacher.getID());
		type.setText(frame.teacher.getType());
		
		name.setEditable(false);
		dob.setEditable(false);
		email.setEditable(false);
		id.setEditable(false);
		type.setEditable(false);
		
		
		this.add(Name);this.add(name);this.add(DOB);this.add(dob);this.add(Email);this.add(email);
		this.add(ID);this.add(id);this.add(Type);this.add(type);this.add(logout);
		this.add(Pic);
		
		tlogoutButtonLis lis = new tlogoutButtonLis(this.frame);
        logout.addActionListener(lis);
		
	}
	
	public ImageIcon ResizeImage(String imgPath){
        ImageIcon MyImage = new ImageIcon(imgPath);
        Image img = MyImage.getImage();
        Image newImage = img.getScaledInstance(Pic.getWidth(), Pic.getHeight(),Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImage);
        return image;
    }
	
}

class tlogoutButtonLis implements ActionListener{
	
	TeacherFrame frame;

	public tlogoutButtonLis(TeacherFrame frame) {
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