package nohi.demo.helloservice.web;

import java.util.Date;

/**
 * Created by nohi on 2018/5/30.
 */
public class User {

	private String name;
	private Integer age;
	private String Address;
	private Date birthDate;
	public User(){

	}
	public User(String name, Integer age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "User{" +
				"name='" + name + '\'' +
				", age=" + age +
				", Address='" + Address + '\'' +
				", birthDate=" + birthDate +
				'}';
	}
}
