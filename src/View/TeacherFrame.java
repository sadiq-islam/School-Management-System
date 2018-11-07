package View;

import javax.swing.*;

import User.Teacher;

public class TeacherFrame extends JFrame{
	
	Teacher teacher;

	TeacherProfile tP;
	TeacherGradeUpload tGU;
	TeacherNoteUpload tNU;
	TeacherNotice tNtc;
	TeacherStdAttendance tSA;
	
	JTabbedPane			tab;

	public TeacherFrame(Teacher teacher){
		this.teacher=teacher;
		this.setTitle("Teacher Dashboard");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800,600);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setIconImage(new ImageIcon(this.getClass().getResource("/teacher.png")).getImage());


		addComponentToFrame();
		this.setVisible(true);
	}

	public void addComponentToFrame(){
		
		this.tP    = new TeacherProfile(this);
		this.tSA   = new TeacherStdAttendance(this);
		this.tGU   = new TeacherGradeUpload(this);
		this.tNU   = new TeacherNoteUpload(this);
		this.tNtc  = new TeacherNotice(this);
		this.tab   = new JTabbedPane();

		this.tab.addTab("Teacher Profile", this.tP);
		this.tab.addTab("Student Attendance", this.tSA);
		this.tab.addTab("Grade Upload", this.tGU);
		this.tab.addTab("Note Upload",this.tNU);
		this.tab.addTab("Notice Upload",this.tNtc);

		this.add(tab);

	}
}