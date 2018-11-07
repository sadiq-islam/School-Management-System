package View;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

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
import javax.swing.ImageIcon;

public class TeacherNoteUpload extends JPanel{
	
	TeacherFrame frame;
	
	JLabel Course;
	
	JComboBox course;
	
	JTextField Choosefile;
	JButton choosefile;
	JButton upnts;
	JButton vwnts;
	
	JLabel label;
	String sf;
	String fp;
	
	JLabel Pic;
	
	public TeacherNoteUpload(TeacherFrame frame)
	{	
		this.frame=frame;
		this.setLayout(null);
		this.setBackground(new Color(191, 128, 64));


		addComponentToPanel();
	}
	
	public void addComponentToPanel() {
		
		this.Course = new JLabel("Course :");
		this.Choosefile = new JTextField();
		this.course = new JComboBox();
		
		this.choosefile=new JButton("Choose File");
		
		this.upnts=new JButton("Upload");
		this.vwnts=new JButton("View Notes"); 
		
		Course.setBounds(10,10,100,50);
		course.setBounds(150,10,200,50);
		
		Choosefile.setBounds(10,90,200,30);
		choosefile.setBounds(210,90,130,30);
		
		upnts.setBounds(65, 420, 90, 50);
		vwnts.setBounds(170,420,140,50);
		
		
		//label = new JLabel();
	    //label.setBounds(10,130,400,300);
		
		//Choosefile.setEditable(false);
		
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
		
		this.Pic = new JLabel(new ImageIcon(this.getClass().getResource("/noteup.png")));
	    Pic.setBounds(440,120,256,256);this.add(Pic);
		
		
		this.add(Choosefile);this.add(choosefile);this.add(Course);this.add(course);this.add(upnts);this.add(vwnts);
		
		
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
		            
		            // System.out.println(filepath);
		             StringBuilder path = new StringBuilder(selectedFile.getAbsolutePath());
		       
		             path.insert(3, "\\");
		             fp=path.toString();
		             //label.setIcon(ResizeImage(path));
		             Choosefile.setText(fp);
		             //sf = path;
		              }
		         else if(result == JFileChooser.CANCEL_OPTION){
		             System.out.println("No Data");
		         }
			}
		
		});
		
		upnts.addActionListener( new ActionListener() {
			
			public void actionPerformed(ActionEvent e)
			{
				if(course.getItemAt( course.getSelectedIndex() ).toString().equals("Choose Course"))
				{
					JOptionPane.showMessageDialog(null," Choose A Course First!! ");
				}
				
				else if(Choosefile.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null," Choose A File To Upload!! ");
				}
				
				else
				{
					
					try {
			            Class.forName("com.mysql.jdbc.Driver");
			            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/my database","root","");
			            Statement stmt = con.createStatement();

			            String crs = course.getItemAt( course.getSelectedIndex() ).toString();
						String sql = "INSERT  INTO uploadedfile (`Course`,`FileName`,`FilePath`) VALUES  ('"+crs+"', '"+sf+"' , '"+fp+"' )";
						int a = stmt.executeUpdate(sql);
			            
			           // PreparedStatement psmnt = (PreparedStatement) 
			            //con.prepareStatement("INSERT  INTO uploadedfile (`Course`,`FileName`,`FilePath`) VALUES  ('"+crs+"', '"+sf+"' , '"+Choosefile.getText()+"' )");   //con is java.sql.Connection object
//			            psmnt.setString(1, ""+crs+"");
//			            psmnt.setString(2, ""+sf+"");
//			            //psmnt.setCharacterStream(3,fr,(int)f.length());
//			            psmnt.setBytes(3, b);
//			            //psmnt.setBinaryStream(3,  (InputStream),(int)inFile.length());
//			            psmnt.executeUpdate();
			            
			            JOptionPane.showMessageDialog(null," Note Uploaded!! ");
			            Choosefile.setText("");
			            
			            con.close();  
			                          
			            }catch (Exception ee) {ee.printStackTrace();}	
					
				}
			}
		
		});
		
		
		vwnts.addActionListener( new ActionListener() {
			
			public void actionPerformed(ActionEvent e)
			{
				if(course.getItemAt( course.getSelectedIndex() ).toString().equals("Choose Course"))
				{
					JOptionPane.showMessageDialog(null," Choose A Course First!! ");
				}
				
				else {
					
					try{
						Class.forName("com.mysql.jdbc.Driver");
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/my database","root","");
						Statement stmt = con.createStatement();
						String crs= course.getItemAt( course.getSelectedIndex() ).toString();
						ResultSet rs = stmt.executeQuery("select (`FileName`) from uploadedfile WHERE `Course`='"+crs+"' ");

						int rowCount=0;
						
						while(rs.next())
						{
							rowCount++;
						}
						
						String columns [] = {"Notes"};					
						String data[][] = new String[rowCount][1];
						
						int i=0;
						
						ResultSet rs1 = stmt.executeQuery("select (`FileName`) from uploadedfile WHERE `Course`='"+crs+"' ");
						while(rs1.next())
						{	
							data[i][0] = rs1.getString(1);
							//System.out.println(data[i][0]);
							i++;
						}
						
						
					     JFrame jf = new JFrame("Notes List");
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