package View;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableCellRenderer;

import User.Admin;
import User.Student;
import User.Teacher;

public class AdminEnrollTeacher extends JPanel{
	
	AdminFrame frame;
	
	JLabel Name;
	JLabel DOB;
	JLabel Email;
	JLabel ID;
	//JLabel Level;
	JLabel Username;
	JLabel Password;
	JLabel Type;
	
	JTextField name;
	JTextField dob;
	JTextField email;
	JTextField id;
	JTextField username;
	JTextField password;
	//JTextField level;
	JTextField type;
	
	JTextField Choosefile;
	JButton choosefile;
	
	
	JButton Enroll;
	JButton Sas;//show all students/teachers

	String sf;
	
	JLabel Pic;
	String picpath;
	
	public AdminEnrollTeacher(AdminFrame frame)
	{	
		this.frame=frame;
		this.setLayout(null);
		this.setBackground(new Color(134, 179, 0));
		addComponentToPanel();
	}
	
	public void addComponentToPanel() 
	{
		this.Name=new JLabel("Name :");
		this.DOB=new JLabel("DOB :");
		this.Email=new JLabel("E-Mail :");
		this.ID=new JLabel("ID :");
		//this.Level=new JLabel("Level :");
		this.Username=new JLabel("Username : ");
		this.Password=new JLabel("Password : ");
		this.Type=new JLabel("Type :");
		
		
		
		this.name=new JTextField();
		this.dob=new JTextField();
		this.email=new JTextField();
		this.id=new JTextField();
		//this.level=new JTextField();
		this.username=new JTextField();
		this.password=new JTextField();
		this.type=new JTextField();
		
		this.Choosefile = new JTextField();
		this.choosefile=new JButton("Choose Image");
		
		this.Enroll=new JButton("Recruit");
		this.Sas=new JButton("Teacher List");
		
		Name.setBounds(10,10,100,50);
		name.setBounds(150,10,200,50);
		
		DOB.setBounds(10,60,100,50);
		dob.setBounds(150,60,200,50);
		
		Email.setBounds(10,110,100,50);
		email.setBounds(150,110,200,50);
		
		ID.setBounds(10,160,100,50);
		id.setBounds(150,160,200,50);
		
		Username.setBounds(10,210,100,50);
		username.setBounds(150,210,200,50);
		
		Password.setBounds(10,260,100,50);
		password.setBounds(150,260,200,50);
		
		Choosefile.setBounds(10,380,200,30);
		choosefile.setBounds(210,380,130,30);
		
	
		Enroll.setBounds(65, 450, 90, 50);
		Sas.setBounds(170,450,115,50);
		
		id.setToolTipText("Example : 2-**");
		
		
		this.Pic = new JLabel(new ImageIcon(this.getClass().getResource("/dpi.png")));
	    Pic.setBounds(440,20,256,256);
	    //Pic.setIcon(new ImageIcon("ddp.png"));
		
		
		this.add(Name);this.add(name);this.add(DOB);this.add(dob);this.add(Email);this.add(email);
		this.add(ID);this.add(id);/*this.add(Level);this.add(level)*/;this.add(Type);this.add(type);this.add(Username);this.add(username);this.add(Password);this.add(password);
		this.add(Choosefile);this.add(choosefile);
		this.add(Enroll);this.add(Sas);this.add(Pic);
		
		
		
		choosefile.addActionListener( new ActionListener() {
			
			public void actionPerformed(ActionEvent e)
			{
				JFileChooser fileChooser = new JFileChooser();
		         fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		         FileNameExtensionFilter filter = new FileNameExtensionFilter("*.pdf", "jpg","gif","png","*.pdf","*.TXT","*.docx");
		         fileChooser.addChoosableFileFilter(filter);
		         int result = fileChooser.showSaveDialog(null);
		         if(result == JFileChooser.APPROVE_OPTION){
		             File selectedFile = fileChooser.getSelectedFile();
		             
		             sf=selectedFile.getName();
		            
		             String path = new String(selectedFile.getAbsolutePath());
		             
		             Pic.setIcon(ResizeImage(path));
		             Choosefile.setText(path);
		              }
		         else if(result == JFileChooser.CANCEL_OPTION){
		             System.out.println("No Data");
		         }
			}
		
		});
		
		
		
	
		
		
		Enroll.addActionListener( new ActionListener(){
			
			public void actionPerformed(ActionEvent e)
			{	
				if(name.getText().equals("") || dob.getText().equals("") || email.getText().equals("") || id.getText().equals("")  || username.getText().equals("") || password.getText().equals("") )
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
						ResultSet rs = stmt.executeQuery("SELECT * FROM `user`");

						
						 while(rs.next())
						{
							 if ( rs.getString(4).equals(id.getText()) || rs.getString(6).equals(username.getText()) || rs.getString(7).equals(password.getText()) )
							 {	
								 if(rs.getString(4).equals(id.getText()))
								 {
									 JOptionPane.showMessageDialog(null," Choose an unique ID! ");
									 username.setText("");
									 flag=1;
									 break;
								 }
								 if (rs.getString(6).equals(username.getText()))
								 {
									 JOptionPane.showMessageDialog(null," Choose an unique Username! ");
									 username.setText("");
									 flag=1;
									 break;
								 }
								 if(rs.getString(7).equals(password.getText()))
								 {
									 JOptionPane.showMessageDialog(null," Choose an unique Password! ");
									 password.setText("");
									 flag=1;
									 break;
								 }
							 }
						}
						 
						}
						 
					catch(Exception ee) 
				    {
						System.out.println(ee);
					}		
				 
				 if(flag==0) 
				 {
					 	Admin.enrollTeacher(name.getText(), dob.getText(), email.getText(), id.getText(),"", username.getText(), password.getText(),Choosefile.getText(), "teacher" );
					 	name.setText("");
					 	dob.setText("");
					 	email.setText("");
					 	id.setText("");
					 	Choosefile.setText("");
					 	username.setText("");
					 	password.setText("");
					 	Pic.setIcon(new ImageIcon(this.getClass().getResource("/dpi.png")));
					 	
					 	flag=1;

					 	JOptionPane.showMessageDialog(null," Teacher Recruited!! ");					 
				 }
				
			}
			}		
		});
		
		
		Sas.addActionListener( new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				
				ArrayList<Teacher> list = frame.admin.teacherList();
				String columns [] = {"Name","DOB","Email","ID","Username","Password"};//,"Type"};
				String data[][] = new String[list.size()][6];


				for(int i=0;i<list.size();i++)
				{	
					data[i][0] = list.get(i).getName();
					data[i][1] = list.get(i).getDOB();;
					data[i][2] = list.get(i).getEmail();
					data[i][3] = list.get(i).getID();
					data[i][4] = list.get(i).getUn();
					data[i][5] = list.get(i).getPass();
				}
					

					 JFrame jf = new JFrame("Teacher List");
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