package cs544.CMP1;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;

@Entity
public class School {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	
	@OneToMany(cascade=CascadeType.PERSIST)
	@MapKey(name="studentid")
	private Map<String, Student> students = new HashMap<>();

	public School() {
		
	}
	
	public School(String name) {
		this.name = name;
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
	
	public Map<String, Student> getStudents() {
		return students;
	}

	public void setStudents(Map<String, Student> students) {
		this.students = students;
	}
	
	public void addStudent(Student student) {
		students.put(student.getStudentid(), student);
	}
	
	public void removeStudent(Student student) {
		students.remove(student.getStudentid());
	}
}
