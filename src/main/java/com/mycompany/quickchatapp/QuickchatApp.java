/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.quickchatapp;

import java.util.Scanner;

/**
 *
 * @author Student
 */
public class QuickchatApp {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
    Login userLogin = new Login();
       
    String userName;
    String password;
    String phoneNumber;
    
     //username validation 
    do {
        System.out.println("Please enter the username: ");
        userName = input.nextLine();
        
        if (!userLogin.checkuserName(userName)) {
            System.out.println("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length");
            
        }
        
    }while (!userLogin.checkuserName(userName));
    
    System.out.println("Username successfully captured");
    
    //password validation
    do {
        System.out.println("Enter your password: ");
        password = input.nextLine();
        
        if (!userLogin.checkPasswordComplexity(password));
        System.out.println("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number and a special chracter");
        
        
        
    } while (!userLogin.checkPasswordComplexity(password));
    
    System.out.println("Password successfully captured");
    
    //phone number validation
    do { 
        System.out.println("Enter your cellphone number: ");
        phoneNumber = input.nextLine();
        
        if (!userLogin.checkCellPhoneNumber(phoneNumber)) {
            System.out.println("Cellphone number incorrectly formatted or does not contain internationalcode");
            
        }
        
    } while (!userLogin.checkCellPhoneNumber(phoneNumber));
    
    System.out.println("Phone number successfully captured");
    
    //register user segment
    String registrationMessage = userLogin.registerUser(userName, password);
    System.out.println(registrationMessage);
    
    //login segment
    System.out.println("\n== LOGIN ==");
    
    System.out.println("Enter username: ");
    String loginUserName = input.nextLine();
    
    System.out.println("Enter password: ");
    String loginPassword = input.nextLine();
    
    boolean loginResult = userLogin.loginUser(loginUserName, loginPassword);
    
    //display the user login status
    System.out.println(userLogin.returnLoginStatus(loginResult));
    
    input.close();
    }
     
      
   }

