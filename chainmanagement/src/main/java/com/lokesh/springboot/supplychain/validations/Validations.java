package com.lokesh.springboot.supplychain.validations;

import org.springframework.stereotype.Component;

@Component
public class Validations {
	
	
	
	public void validatePhoneNumber(String phone) {
		if(phone == null || phone.length() != 10) {
            throw new IllegalArgumentException("Phone number should be exactly 10 digits long");
		}
	}
	
	public void validateEmailAddress(String email) {
        if (email == null || !email.endsWith("@gmail.com")) {
            throw new IllegalArgumentException("Email address should end with '@gmail.com'");
        }
    }
	
	public void validateFirstName(String firstName) {
        if (firstName == null || !firstName.matches("[a-zA-Z]+")) {
            throw new IllegalArgumentException("First name should only have alphabets");
        }
    }
    
    public void validateLastName(String lastName) {
        if (lastName == null || !lastName.matches("[a-zA-Z]+")) {
            throw new IllegalArgumentException("Last name should only have alphabets");
        }
    }
	
	

}
