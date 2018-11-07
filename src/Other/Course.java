package Other;

public class Course {
	
	String courseName;
	String level;
	String notice;
	
	public Course(String courseName,String level,String notice)
	{
		this.courseName=courseName;
		this.level=level;
		this.notice=notice;
	}
	
	public String getCourseName()
	{
		return this.courseName;
	}
	
	public String getLevel()
	{
		return this.level;
	}
	
	public String getNotice()
	{
		return this.notice;
	}
	
}
