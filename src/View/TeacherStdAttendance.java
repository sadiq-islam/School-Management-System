package View;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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
import java.util.Calendar;
import org.jdesktop.swingx.JXDatePicker;
import java.text.SimpleDateFormat;

public class TeacherStdAttendance extends JPanel {
	TeacherFrame frame;
	
	JLabel Name;
	JLabel Id;
	JLabel Course;
	JLabel Atd;
	JLabel Date;
	
	JComboBox id;
	JComboBox course;
	JComboBox atd;
	//JTextField date;
	JXDatePicker datepicker;
	JComboBox name;
	
	JButton submit;
	JButton vwatd;
	
	JLabel Pic;
	
	public TeacherStdAttendance(TeacherFrame frame)
	{	
		this.frame=frame;
		this.setLayout(null);
		this.setBackground(new Color(134, 89, 45));
		addComponentToPanel();
	}


	private void addComponentToPanel() {
		
		this.Name=new JLabel("Name :");
		this.Id = new JLabel ("Student ID :");
		this.Course = new JLabel ("Course Name :");
		this.Atd = new JLabel("Attendance :");
		this.Date = new JLabel("Date :");
		
		this.id = new JComboBox();
		this.course = new JComboBox();
		this.atd = new JComboBox();
		//this.date = new JTextField();
		
		this.datepicker =  new JXDatePicker();
		datepicker.setDate(Calendar.getInstance().getTime());
		datepicker.setFormats(new SimpleDateFormat("dd-MM-yyyy"));
        SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");      
        
        
		this.name=new JComboBox();
		
		this.submit = new JButton("Submit");
		this.vwatd = new JButton("View Attendance");
		
		Course.setBounds(10,10,100,50);
		course.setBounds(150,10,200,50);
		
		Id.setBounds(10,60,100,50);
		id.setBounds(150,60,200,50);
		
		Name.setBounds(10,110,100,50);
		name.setBounds(150,110,200,50);
		
		Atd.setBounds(10,160,100,50);
		atd.setBounds(150,160,200,50);
		
		Date.setBounds(400,10,100,50);
		datepicker.setBounds(540,10,200,50);
		
		
		submit.setBounds(65, 420, 90, 50);
		vwatd.setBounds(170,420,140,50);
		
		//date.setToolTipText("Format : D**-M**-Y**");
		
		name.setEditable(false);	
		
		atd.addItem("0");
		atd.addItem("1");
		
		atd.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_0)
				{
					atd.setSelectedIndex(1);
				}

				if(e.getKeyCode() == KeyEvent.VK_1)
				{
					atd.setSelectedIndex(2);
				
				}				
				}

			@Override
			public void keyPressed(KeyEvent e) {
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		
		this.Pic = new JLabel(new ImageIcon(this.getClass().getResource("/stdatn.png")));
	    Pic.setBounds(440,150,256,256);this.add(Pic);
		
		this.add(Id);this.add(id);this.add(Course);this.add(course);this.add(Atd);this.add(atd);this.add(submit);this.add(vwatd);
		this.add(Date);/*this.add(date)*/;this.add(Name);this.add(name);this.add(datepicker);
		
		
		course.addItem("Choose Course");
		//id.addItem("Select Student ID");
		//name.addItem("Select Student ID");
		
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
		
		course.addActionListener( new ActionListener(){
			
			public void actionPerformed(ActionEvent e)
			{
				try{
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/my database","root","");
					Statement stmt = con.createStatement();
					String cname = course.getItemAt( course.getSelectedIndex() ).toString();
					ResultSet rs = stmt.executeQuery("select `Name`,`OrgID` from user WHERE StdLevel=(select level from course WHERE `Course Name`='"+cname+"')");
					
					id.removeAllItems();
					name.removeAllItems();
					
					//id.addItem("Select Student ID");
					//name.addItem("Select Student ID");
					
					while(rs.next())
					{	
						name.addItem(rs.getString(1));
						id.addItem(rs.getString(2));
					}
					
					
					name.setSelectedIndex(id.getSelectedIndex());
					
					
					con.close();
					
					}catch(Exception et){
					System.out.println(et);
				}
			}
			
		});
		
		
		//course.setNextFocusableComponent(id);
		//id.setNextFocusableComponent(name);
		
		id.addActionListener( new ActionListener(){
			
			public void actionPerformed(ActionEvent e)
			{	
				if(id.getItemAt( id.getSelectedIndex() ).toString().equals("Select Student ID"))
				{}
				else {
				try{
					name.setSelectedIndex(id.getSelectedIndex());
					}catch(Exception et){
					System.out.println(et);
				}}
			}
			
		});
		
		id.setEditable(false);
		name.setEnabled(false);
		

		
		submit.addActionListener( new ActionListener(){
			
			public void actionPerformed(ActionEvent e)
			{
				
				if(id.getItemAt( id.getSelectedIndex() ).toString().equals("Select Student ID") ||course.getItemAt( course.getSelectedIndex() ).toString().equals("Choose Course") || formater.format(datepicker.getDate()).equals("") || atd.getItemAt( atd.getSelectedIndex() ).toString().equals("")) {
					JOptionPane.showMessageDialog(null," Choose/Select all the fields! ");
				}
				else {
				try{
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/my database","root","");
					Statement stmt = con.createStatement();
					String coursen = course.getItemAt( course.getSelectedIndex() ).toString();
					String sid  = id.getItemAt( id.getSelectedIndex() ).toString();
					ResultSet rs = stmt.executeQuery("select * from attendance");
					
					int flag=0;
					
					while(rs.next())
					{
						if (rs.getString(1).equals(formater.format(datepicker.getDate())) && rs.getString(2).equals(coursen) && rs.getString(3).equals(sid))
						{
							JOptionPane.showMessageDialog(null," Attendance Already Taken!! ");
							flag=1;
							break;
						}
					}
					
					if(flag==0)
					{
						
						String sql = "INSERT INTO `attendance`(`Date`,`Course`,`StudentID`, `Attendance`) VALUES ('"+formater.format(datepicker.getDate())+"','"+coursen+"','"+sid+"','"+atd.getItemAt( atd.getSelectedIndex() ).toString()+"')";
						int a = stmt.executeUpdate(sql);
						
						
						try {
						id.setSelectedIndex(id.getSelectedIndex()+1);
						name.setSelectedIndex(id.getSelectedIndex());
						}
						catch(Exception etrt){
							JOptionPane.showMessageDialog(null," Attendance Taking Complete! ");
						}
						//JOptionPane.showMessageDialog(null," Attendance Taken!! ");
					}
					
					
					con.close();
					
					}catch(Exception ett){
					System.out.println(ett);
				}
			}
			}
			
		});
		
		
		vwatd.addActionListener( new ActionListener(){
			
			public void actionPerformed(ActionEvent e)
			{
				try{
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/my database","root","");
					Statement stmt = con.createStatement();
					String cname = course.getItemAt( course.getSelectedIndex() ).toString();
					ResultSet rs = stmt.executeQuery("select * from attendance WHERE Date='"+formater.format(datepicker.getDate())+"' AND Course='"+cname+"'");
					int rowCount=0;
					
					while(rs.next())
					{
						rowCount++;
						
					}
					
					String columns [] = {"Date","Student ID","Attendance"};					
					String data[][] = new String[rowCount][3];
					
					int i=0;
					
					ResultSet rs1 = stmt.executeQuery("select * from attendance WHERE Date='"+formater.format(datepicker.getDate())+"' AND Course='"+cname+"'");
					while(rs1.next())
					{	
						data[i][0] = rs1.getString(1);
						data[i][1] = rs1.getString(3);
						data[i][2] = rs1.getString(4);
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
				}
			}
			
		});
		
		
		
	}
}

	
	
//	    class datepickerLis implements ActionListener{
//		JXDatePicker datepicker;
//		public datepickerLis(JXDatePicker datepicker) {
//			this.datepicker = datepicker;
//		}
//		@Override
//		public void actionPerformed(ActionEvent e) {
//		
//		}
//		
//		public String datee()
//		{	
//			 SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
//		        
//		     return formater.format(datepicker.getDate());
//			 
//		}
//	}
		