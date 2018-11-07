package User;


public class Teacher extends Employee {
	
	private String username;
	private String password;
	private String type;
	
	public Teacher(String Name,String DOB,String email,String orgID,String username,String password,String type)
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
	public String getUn()
	{
		return this.username;
	}
	public String getPass() {
		return this.password;
	}
	
}
