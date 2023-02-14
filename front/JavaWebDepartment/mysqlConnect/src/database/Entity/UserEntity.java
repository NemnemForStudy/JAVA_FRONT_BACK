package database.Entity;

public class UserEntity {
	private Integer id;
	private String password;
	private String name;
	private String phoneNumber;
	
	public UserEntity(Integer id, String password, String name, String phoneNumber) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.phoneNumber = phoneNumber;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", password=" + password + ", name=" + name + ", phoneNumber=" + phoneNumber
				+ "]";
	}
	
	
}
