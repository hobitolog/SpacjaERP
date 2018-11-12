package pl.poznan.put.student.spacjalive.erp.entity;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Set;

@Entity
@Table(name = "employee")
public class Employee {
	
	//TODO add fields validations
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message = "Pole nie może być puste!")
	@Size(min = 1, message = "Pole nie może być puste!")
	@Size(max = 45, message = "Długość pola nie może przekroczyć 45 znaków!")
	@Pattern(regexp = "[\\p{L}]+(\\ [\\p{L}]+)?", message = "Imie musi składać się wyłącznie ze znaków alfabetu!")
	@Column(name = "first_name")
	private String firstName;
	
	@NotNull(message = "Pole nie może być puste!")
	@Size(min = 1, message = "Pole nie może być puste!")
	@Size(max = 45, message = "Długość pola nie może przekroczyć 45 znaków!")
	@Pattern(regexp = "[\\p{L}]+(\\ [\\p{L}]+)?", message = "Imie może składać się wyłącznie ze znaków alfabetu!")
	@Column(name = "last_name")
	private String lastName;
	
	@NotNull(message = "Pole nie może być puste!")
	@Size(min = 1, message = "Pole nie może być puste!")
	@Size(max = 128, message = "Długość pola nie może przekroczyć 128 znaków!")
	@Pattern(regexp = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$", message = "Niepoprawny email!")
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@NotNull(message = "Pole nie może być puste!")
	@Size(min = 1, message = "Pole nie może być puste!")
	@Size(max = 15, message = "Długość pola nie może przekroczyć 15 znaków!")
	@Pattern(regexp = "\\+?([0-9]+\\ ?\\-?)+", message = "Niepoprawny format numeru!")
	@Column(name = "phone_number")
	private String phoneNumber;
	
	@Column(name = "student_index")
	private String studentIndex;
	
	@Column(name = "office_entrance")
	private int officeEntrance;
	
	@Column(name = "enabled")
	private int enabled;
	
	@Column(name = "car")
	private int car;
	
	@Column(name = "last_update")
	private String lastUpdate;
	
	@JoinTable(name = "user_authorities",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "authority_id"))
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Authority> authorities;
	
	public Employee() {
	
	}
	
	public Employee(String firstName, String lastName, String email, String phoneNumber, String studentIndex, String
			password, int	officeEntrance, int enabled, int car, String lastUpdate, Set<Authority> authorities) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.studentIndex = studentIndex;
		this.officeEntrance = officeEntrance;
		this.enabled = enabled;
		this.car = car;
		this.lastUpdate = lastUpdate;
		this.authorities = authorities;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getStudentIndex() {
		return studentIndex;
	}
	
	public void setStudentIndex(String studentIndex) {
		this.studentIndex = studentIndex;
	}
	
	public int getOfficeEntrance() {
		return officeEntrance;
	}
	
	public void setOfficeEntrance(int officeEntrance) {
		this.officeEntrance = officeEntrance;
	}
	
	public int getEnabled() {
		return enabled;
	}
	
	public void setEnabled(int active) {
		this.enabled = active;
	}
	
	public int getCar() {
		return car;
	}
	
	public void setCar(int car) {
		this.car = car;
	}
	
	public String getLastUpdate() {
		return lastUpdate;
	}
	
	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
	public Set<Authority> getAuthorities() {
		return authorities;
	}
	
	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}
	
	@Override
	public String toString() {
		return "Employee{" +
				"id=" + id +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				", phoneNumber='" + phoneNumber + '\'' +
				", studentIndex='" + studentIndex + '\'' +
				", officeEntrance=" + officeEntrance +
				", enabled=" + enabled +
				", car=" + car +
				", lastUpdate='" + lastUpdate + '\'' +
				", authorities=" + authorities +
				'}';
	}
}
