package Entity;

public class Student {

	public static String[] sexstatus = { "Å®", "ÄÐ" }; 
	public int id;
	public String name;
	public int sex;
	public int  classid;
	public String class_name;
	public String getSex_Name() {
		return sexstatus[sex];
	}
	public static String[] getSexstatus() {
		return sexstatus;
	}
	public static void setSexstatus(String[] sexstatus) {
		Student.sexstatus = sexstatus;
	}
	public String getClass_name() {
		return class_name;
	}
	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}
	public String getSexStatus() {
		return sexstatus[sex];
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public int getClassid() {
		return classid;
	}
	public void setClassid(int classid) {
		this.classid = classid;
	}
	
}
