package View;

import javax.swing.*;

import User.Student;

public class StudentFrame extends JFrame{
	Student student;
	
	StudentProfile sP;
	StudentViewAttendance sVA;
	StudentNotice sNtc;
	StudentNoteDownload sND;
	
	JTabbedPane			tab;

	public StudentFrame(Student student){
		this.student=student;
		this.setTitle("Student Dashboard");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800,600);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setIconImage(new ImageIcon(this.getClass().getResource("/student.png")).getImage());


		addComponentToFrame();
		this.setVisible(true);
	}

	public void addComponentToFrame(){
		
		this.sP    = new StudentProfile(this);
		this.sVA   = new StudentViewAttendance(this);
		this.sNtc  = new StudentNotice(this);
		this.sND   = new StudentNoteDownload(this);
		this.tab   = new JTabbedPane();
		

		this.tab.addTab("Student Profile", this.sP);
		this.tab.addTab("Attendance", this.sVA);
		this.tab.addTab("Notice", this.sNtc);
		this.tab.addTab("Notes Download",this.sND);
		

		this.add(tab);

	}
}