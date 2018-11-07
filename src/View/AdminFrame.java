package View;

import javax.swing.*;

import User.Admin;

public class AdminFrame extends JFrame{
	
	Admin admin;

	AdminProfile 		aP;
	AdminEnrollStudent 	aES;
	AdminEnrollTeacher  aET;
	AdminAddCourse      aAC;
	AdminAssignCourse   aASC;
	AdminUpdateProfileInfo aUPI;
	
	JTabbedPane			tab;

	public AdminFrame(Admin admin){
		
		this.admin=admin;
		this.setTitle("Admin Dashboard");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800,600);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setIconImage(new ImageIcon(this.getClass().getResource("/admin.png")).getImage());


		addComponentToFrame();
		this.setVisible(true);
	}

	public void addComponentToFrame(){
		
		this.aP 		= new AdminProfile(this);
		this.aES		= new AdminEnrollStudent(this);
		this.aET		= new AdminEnrollTeacher(this);
		this.aAC	    = new AdminAddCourse(this);
		this.aASC		= new AdminAssignCourse(this);
		this.aUPI       = new AdminUpdateProfileInfo(this);
		this.tab 		= new JTabbedPane();

		this.tab.addTab("Admin Profile", this.aP);
		this.tab.addTab("Enroll Student", this.aES);//Show Student List
		this.tab.addTab("Enroll Teacher",this.aET);//Show Teacher List
		this.tab.addTab("Add Course",this.aAC);//Show Course list
		this.tab.addTab("Assign Course", this.aASC);
		this.tab.addTab("Update User Information",this.aUPI);

		this.add(tab);

	}
}