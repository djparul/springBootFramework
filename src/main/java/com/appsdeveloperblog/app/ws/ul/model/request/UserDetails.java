package com.appsdeveloperblog.app.ws.ul.model.request;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDetails {
	@NotNull(message="First name cannot be null")
	@Size(min=2, message="First Name must not be less than 2 characters")
	private String firstName;
	private String _id;
	
	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	@NotNull(message="Last name cannot be null")
	@Size(min=2, message="Last Name must not be less than 2 characters")
	private String lastName;
	
	@NotNull(message="Email cannot be null")
	@Email
	private String email;
	
	@NotNull(message="Password cannot be null")
	@Size(min=8,max=16, message="Password must be equal or greater than 8 characters and less than 16 characters")
	private String password;
	
	//private String userId;
	
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
	
	/*
	 * public String getUserId() { return userId; }
	 * 
	 * public void setUserId(String userId) { this.userId = userId; }
	 */

	public UserDetails(
			@NotNull(message = "First name cannot be null") @Size(min = 2, message = "First Name must not be less than 2 characters") String firstName,
			@NotNull(message = "Last name cannot be null") @Size(min = 2, message = "Last Name must not be less than 2 characters") String lastName,
			@NotNull(message = "Email cannot be null") @Email String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		//this.userId = userId;
		this.password = password;
	}
	
}
