package bean;

public class User {

	public String username;
	public String password;
	public String studentId;
	public String college;
	public String classes;
	
	public User(){};
	
	public User(String username, String password, String studentId,
			String college, String classes) {
		super();
		this.username = username;
		this.password = password;
		this.studentId = studentId;
		this.college = college;
		this.classes = classes;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	public String getClasses() {
		return classes;
	}
	public void setClasses(String classes) {
		this.classes = classes;
	}

}
