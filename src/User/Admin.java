package User;

import java.sql.*;
import java.util.ArrayList;

import Other.Course;

public class Admin extends Employee {
	
	private String username;
	private String password;
	private String type;
	
	public Admin(String Name,String DOB,String email,String orgID,String username,String password,String type)
	{
		this.Name=Name;
		//this.fatherName=fatherName;
		//this.motherName=motherName;
		this.DOB=DOB;
		this.email=email;
		//this.address=address;
		//this.gender=gender;
		this.orgID=orgID;	
		this.username=username;
		this.password=password;
		this.type=type;
	}
	
	public void show() {};
	
	public String getName()
	{
		return Name;
	}

	public String getDOB()
	{
		return DOB;
	}
	public String getEmail()
	{
		return email;
	}
	public String getID()
	{
		return orgID;
	}
	public String getType()
	{
		return type;
	}
	
	
	
	public static void enrollStudent(String Name,String DOB,String Email,String orgID,String stdLevel,String username,String password,String pic,String type)
	{
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/my database","root","");
			Statement stmt = con.createStatement();
			String sql = "INSERT INTO `user`(`Name`, `DOB`, `Email`,`OrgID`,`StdLevel`,`Username`,`Password`,`Type`,`Picture`) VALUES ('"+Name+"','"+DOB+"','"+Email+"','"+orgID+"','"+stdLevel+"','"+username+"','"+password+"','"+type+"','"+pic+"' )";
			
			int a = stmt.executeUpdate(sql);
			con.close();
			
		}catch(Exception e){
			System.out.println(e);
		}
			
	}
	
	public static void enrollTeacher(String Name,String DOB,String Email,String orgID,String stdLevel,String username,String password,String pic,String type)
	{
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/my database","root","");
			Statement stmt = con.createStatement();
			String sql = "INSERT INTO `user`(`Name`, `DOB`, `Email`,`OrgID`,`StdLevel`,`Username`,`Password`,`Type`,`Picture`) VALUES ('"+Name+"','"+DOB+"','"+Email+"','"+orgID+"','"+stdLevel+"','"+username+"','"+password+"','"+type+"','"+pic+"' )";
			
			int a = stmt.executeUpdate(sql);
			con.close();
			
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
	public static void addCourse(String courseName,String Level,String Notice)
	{
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/my database","root","");
			Statement stmt = con.createStatement();
			String sql = "INSERT INTO `course`(`Course Name`, `Level`, `Notice`) VALUES ('"+courseName+"','"+Level+"','"+Notice+"' )";
			
			int a = stmt.executeUpdate(sql);
			con.close();
			
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
	
	
	
	
	public ArrayList<Course> courseList(){
		
		ArrayList<Course> courseList = new ArrayList<Course>();
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/my database","root","");
			Statement stmt = con.createStatement();
			String sql= "select * from course";
			ResultSet rs   = stmt.executeQuery(sql);
			
			Course course;
			
			while(rs.next())
			{
				course = new Course(rs.getString(1), rs.getString(2), rs.getString(3));
				courseList.add(course);
			}
			
			con.close();
			
		}catch(Exception e){
			System.out.println(e);
		}
		
		return courseList;
	}
	
	
	public ArrayList<Student> studentList(){
		
		ArrayList<Student> studentList = new ArrayList<Student>();
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/my database","root","");
			Statement stmt = con.createStatement();
			String sql= "SELECT * FROM `user` WHERE type='student'";
			ResultSet rs   = stmt.executeQuery(sql);
			
			Student student;
			
			while(rs.next())
			{
				student =new Student( rs.getString(1) , rs.getString(2) , rs.getString(3) , rs.getString(4) , rs.getString(5) , rs.getString(6) , rs.getString(7) , rs.getString(8) );
				studentList.add(student);
			}
			
			con.close();
			
		}catch(Exception e){
			System.out.println(e);
		}
		
		return studentList;
	}
	
	
public ArrayList<Teacher> teacherList(){
		
		ArrayList<Teacher> teacherList = new ArrayList<Teacher>();
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/my database","root","");
			Statement stmt = con.createStatement();
			String sql= "SELECT * FROM `user` WHERE type='teacher'";
			ResultSet rs   = stmt.executeQuery(sql);
			
			Teacher teacher;
			
			while(rs.next())
			{
				teacher =new Teacher( rs.getString(1) , rs.getString(2) , rs.getString(3) , rs.getString(4) , rs.getString(6) , rs.getString(7) , rs.getString(8) );
				teacherList.add(teacher);
			}
			
			con.close();
			
		}catch(Exception e){
			System.out.println(e);
		}
		
		return teacherList;
	}

	
	
}
