package User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Other.Course;

public class Student extends Person {

	private String orgID;
	private String stdLevel;
	private String username;
	private String password;
	private String type;
	
	public static Student student[]= new Student[100];
	public static int studentCounter=0;
	
	public Student(String Name,String DOB,String email,String orgID,String stdLevel,String username,String password,String type)
	{
		this.Name=Name;
		//this.fatherName=fatherName;
		//this.motherName=motherName;
		this.DOB=DOB;
		this.email=email;
		//this.address=address;
		//this.gender=gender;
		this.orgID=orgID;
		this.stdLevel=stdLevel;
		
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
	public String getLevel() {
		return this.stdLevel;
	}
	public String getUn()
	{
		return this.username;
	}
	public String getPass() {
		return this.password;
	}

	
	
}
