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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import javax.xml.bind.*;

public class AdminUpdateProfileInfo extends JPanel{
	AdminFrame frame;
	
	
	JLabel Id;
	JLabel Name;
	JLabel DOB;
	JLabel Email;
	JLabel Username;
	JLabel Password;
	
	JComboBox id;
	JTextField name;
	JTextField dob;
	JTextField email;
	JTextField username;
	JTextField password;
	
	JTextField Choosefile;
	JButton choosefile;
	
	JButton updt;
	JButton dlt;
	//JButton updtdinfo;
	
	String n,d,e,u,p,pcr;
	
	JLabel Pic;
	String picpath;
	
	
	
	public AdminUpdateProfileInfo(AdminFrame frame)
	{	
		this.frame=frame;
		this.setLayout(null);
		this.setBackground(new Color(214, 255, 102));
		addComponentToPanel();
	}
	
	public void addComponentToPanel() {
		
		this.Id = new JLabel("ID");
		this.id = new JComboBox();
		
		this.Name=new JLabel("Name :");
		this.DOB=new JLabel("DOB :");
		this.Email=new JLabel("E-Mail :");
		this.Username=new JLabel("User Name :");
		this.Password=new JLabel("Password :");
		
		this.name=new JTextField();
		this.dob=new JTextField();
		this.email=new JTextField();
		this.username=new JTextField();
		this.password=new JTextField();
		
		
		this.Choosefile = new JTextField();
		this.choosefile=new JButton("Choose Image");
		
		
		this.updt=new JButton("Update");
		this.dlt =new JButton("Delete");
		//this.updtdinfo = new JButton("Updated Information");
		
		
		Id.setBounds(10,10,100,50);
		id.setBounds(150,10,200,50);
		
		Name.setBounds(10,60,100,50);
		name.setBounds(150,60,200,50);
		
		DOB.setBounds(10,110,100,50);
		dob.setBounds(150,110,200,50);
		
		Email.setBounds(10,160,100,50);
		email.setBounds(150,160,200,50);
		
		Username.setBounds(10,210,100,50);
		username.setBounds(150,210,200,50);
		
		Password.setBounds(10,260,100,50);
		password.setBounds(150,260,200,50);
		
		Choosefile.setBounds(10,340,200,30);
		choosefile.setBounds(210,340,130,30);
		
		updt.setBounds(65, 420,100, 50);
		dlt.setBounds(180, 420, 100, 50);
		//updtdinfo.setBounds(295, 420, 150, 50);
		
		this.Pic = new JLabel(new ImageIcon(this.getClass().getResource("/dpi.png")));
	    Pic.setBounds(440,20,256,256);
		
		Choosefile.setEditable(false);
		
		this.add(Name);this.add(name);this.add(DOB);this.add(dob);this.add(Email);this.add(email);
		this.add(Id);this.add(id);this.add(Username);this.add(Password);this.add(username);this.add(password);
		this.add(updt);this.add(dlt);//this.add(updtdinfo);
		this.add(Choosefile);this.add(choosefile);this.add(Pic);
		
		
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/my database","root","");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select `OrgID` from user");
			
			id.addItem("Select User ID");
			while(rs.next())
			{	
				id.addItem(rs.getString(1));
			}
			
			con.close();
			
			}catch(Exception et){
			System.out.println(et);
		}
		
		
		id.addActionListener( new ActionListener(){
			
			
			public void actionPerformed(ActionEvent e)
			{
				
				if(id.getItemAt( id.getSelectedIndex() ).toString().equals("Select User ID")) {
					
					name.setText("");
					dob.setText("");
					email.setText("");
					username.setText("");
					password.setText("");
					Choosefile.setText("");
					Pic.setIcon(new ImageIcon(this.getClass().getResource("/dpi.png")));
					id.setSelectedIndex(0);					
					
					
				JOptionPane.showMessageDialog(null," Kindly Select An ID to Proceed! ");
			}
			
				else {
				
				try{
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/my database","root","");
					Statement stmt = con.createStatement();
					String uid = id.getItemAt( id.getSelectedIndex() ).toString();
					ResultSet rs = stmt.executeQuery("select `Name`,`DOB`,`Email`,`Username`,`Password`,`Picture` from user WHERE OrgID='"+uid+"'");
					
					
					while(rs.next())
					{	
						name.setText(rs.getString(1));
						dob.setText(rs.getString(2));
						email.setText(rs.getString(3));
						username.setText(rs.getString(4));
						password.setText(rs.getString(5));
						Choosefile.setText(rs.getString(6));
						Pic.setIcon(ResizeImage(rs.getString(6)));
					}
					
					JOptionPane.showMessageDialog(null," Edit Fields To Update! ");
					
					con.close();
					
					}catch(Exception et){
					System.out.println(et);
				}
			}
			}
			
		});
		
		
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
		             
		             //sf=selectedFile.getName();
		            
		             StringBuilder path = new StringBuilder(selectedFile.getAbsolutePath());
		             
		             Pic.setIcon(ResizeImage(path.toString()));
		             
		             /*char c='/';
		             for(int i=0;i<path.length();i++)
		             {
		            	 if(path.charAt(1) == c)
		            	 {
		            		 path.insert(i+1, c);
		            		 System.out.print(i+"  ");
		            	 }
		             }*/
		             
		             System.out.println(path);
		             Choosefile.setText(path.toString());
		             
		              }
		         else if(result == JFileChooser.CANCEL_OPTION){
		             System.out.println("No Data");
		         }
			}
		
		});		
		
		
		
		
		
		updt.addActionListener( new ActionListener(){
			
			public void actionPerformed(ActionEvent ee)
			{	
				if(id.getItemAt( id.getSelectedIndex() ).toString().equals("Select User ID")) {
					
					name.setText("");
					dob.setText("");
					email.setText("");
					username.setText("");
					password.setText("");
					Choosefile.setText("");
					Pic.setIcon(new ImageIcon(this.getClass().getResource("/dpi.png")));
					id.setSelectedIndex(0);
					
					//JOptionPane.showMessageDialog(null," Kindly Select An ID to Proceed! ");
				}
				
				else {
					
					int c=JOptionPane.showConfirmDialog(null,"Update User Information?");  
					   if(c==JOptionPane.YES_OPTION){ 	
					
				try{
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/my database","root","");
					Statement stmt = con.createStatement();
					String uid = id.getItemAt( id.getSelectedIndex() ).toString();
					
					n=name.getText();
					d=dob.getText();
					e=email.getText();
					u=username.getText();
					p=password.getText();
					pcr=Choosefile.getText();

					
					String sql = "UPDATE user SET `Name`='"+n+"' ,`DOB`='"+d+"',`Email`='"+e+"',`Username`='"+u+"',`Password`='"+p+"',`Picture`='"+pcr+"' WHERE OrgID='"+uid+"'";
					int a = stmt.executeUpdate(sql);
					
				
					
					ResultSet rs = stmt.executeQuery("select `Name`,`DOB`,`Email`,`OrgID`,`Username`,`Password` from user WHERE OrgID = '"+uid+"'");
					
					int rowCount=0;
					
					while(rs.next())
					{
						rowCount++;
						
					}
					
					String columns [] = {"Name","DOB","Email","ID","Username","Password"};					
					String data[][] = new String[rowCount][6];
					
					int i=0;
					
					ResultSet rs1 = stmt.executeQuery("select `Name`,`DOB`,`Email`,`OrgID`,`Username`,`Password` from user WHERE OrgID = '"+uid+"'");
					while(rs1.next())
					{	
						data[i][0] = rs1.getString(1);
						data[i][1] = rs1.getString(2);
						data[i][2] = rs1.getString(3);
						data[i][3] = rs1.getString(4);
						data[i][4] = rs1.getString(5);
						data[i][5] = rs1.getString(6);
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
		         
		         JOptionPane.showMessageDialog(null," Information Updated! ");
		         
		         	name.setText("");
					dob.setText("");
					email.setText("");
					username.setText("");
					password.setText("");
					Choosefile.setText("");
					Pic.setIcon(new ImageIcon(this.getClass().getResource("/dpi.png")));
					id.setSelectedIndex(0);
					
					con.close();
					
					}catch(Exception et){
					System.out.println(et);
				}}
				
					   if(c==JOptionPane.CANCEL_OPTION)  
					   {
						name.setText("");
						dob.setText("");
						email.setText("");
						username.setText("");
						password.setText("");
						Choosefile.setText("");
						Pic.setIcon(null);
						id.setSelectedIndex(0);
						
						}
					   
					   
				}
			}
			
		});
		
		
		
			dlt.addActionListener( new ActionListener(){
			
			public void actionPerformed(ActionEvent ee)
			{	
				if(id.getItemAt( id.getSelectedIndex() ).toString().equals("Select User ID")) {
					JOptionPane.showMessageDialog(null," Kindly Select An ID to Proceed! ");
				}
				
				else {
					
					int b=JOptionPane.showConfirmDialog(null,"Delete User Information?");  
					   if(b==JOptionPane.YES_OPTION){  
					
					try{
						Class.forName("com.mysql.jdbc.Driver");
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/my database","root","");
						Statement stmt = con.createStatement();
						String uid = id.getItemAt( id.getSelectedIndex() ).toString();
						
						String sql = "DELETE from user WHERE OrgID='"+uid+"'";
						int a = stmt.executeUpdate(sql);
						
						String sql1 = "DELETE from grade WHERE StudentID='"+uid+"'";
						int a1 = stmt.executeUpdate(sql1);
						
						String sql2 = "DELETE from attendance WHERE StudentID='"+uid+"'";
						int a2 = stmt.executeUpdate(sql2);
						
						String sql3 = "DELETE from teachercourse WHERE `Teacher ID`='"+uid+"'";
						int a3 = stmt.executeUpdate(sql3);
						
						
						JOptionPane.showMessageDialog(null," User information has been deleted successfully!!! ");
						
						
						try{
							Class.forName("com.mysql.jdbc.Driver");
							Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/my database","root","");
							Statement stmtt = conn.createStatement();
							ResultSet rs = stmtt.executeQuery("select `OrgID` from user");
							
							id.removeAllItems();
							id.addItem("Select User ID");
							while(rs.next())
							{	
								id.addItem(rs.getString(1));
							}
														
							name.setText("");
							dob.setText("");
							email.setText("");
							username.setText("");
							password.setText("");
							Choosefile.setText("");
							Pic.setIcon(null);
							id.setSelectedIndex(0);
														
							con.close();
							
							}catch(Exception et){
							System.out.println(et);
						}
						
						
						
						con.close();
						
						}catch(Exception et){
						System.out.println(et);
					}
					   }
					   
					   
					   if(b==JOptionPane.CANCEL_OPTION)  
					   {
						   	name.setText("");
							dob.setText("");
							email.setText("");
							username.setText("");
							password.setText("");
							Choosefile.setText("");
							Pic.setIcon(null);
							id.setSelectedIndex(0);
						
						}
					
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


