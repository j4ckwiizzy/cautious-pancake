/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quickchatapp;

/**
 *
 * @author Student
 */
class Login {
    
    
    private String storedUserName;
    private String storedPassword;
    //check username method
    boolean checkuserName(String userName) {
        if (userName.contains("_") && userName.length() <= 5) {//This method is used to ensure that the username contains an underscore and is no more than five characters in length
            return true;   
        } else {
            return false;
        }   
        
    }
    
    //check password complexity method 
    boolean checkPasswordComplexity(String password) {// This method is used to ensure that the password meets the password complexity rules
        if (password.length() < 8)
            return false;
        
        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasspecialCharacter = false;
        
        for (char ch : password.toCharArray()) {
            if (Character.isUpperCase(ch)) hasCapital = true;
            else if (Character.isDigit(ch)) hasNumber = true;            
            else if (!Character.isLetterOrDigit(ch)) hasspecialCharacter = true;
            
            //Stop if all the conditions are met
            if (hasCapital && hasNumber && hasspecialCharacter) return true;
        }
        
       return false;
    }
    
    //check cellphone number method
    boolean checkCellPhoneNumber(String phoneNumber) {
        if (!phoneNumber.startsWith("+")){
            return false;
            
        }
        String digitsOnly = phoneNumber.substring(1);
        if (!digitsOnly.matches("\\d+")){
            return false;
            
        }
        if (digitsOnly.length() < 10 || digitsOnly.length() > 15)
            return false;
        return true;
    }
    
    //register user method
    String registerUser(String userName, String password) {
        
        if (!checkuserName(userName)) {
            return "Username is not correctly formatted.";
        }
        
        if (!checkPasswordComplexity(password)) {
            return "Password does not meet complexity rules.";
        }
        
        //store valid details
        storedUserName = userName;
        storedPassword = password;
        
        return "User has been registered successfully.";
       
    }
    
    //login user method
    boolean loginUser(String userName, String password) {
        return userName.equals(storedUserName) && password.equals(storedPassword);
       
    }
    
    //return login status method
    String returnLoginStatus(boolean loginSuccess) {
        if (loginSuccess) {
            return "Login successful.";
        } else {
            return "Login failed.";
        }
        
    }
}
    
    
   

