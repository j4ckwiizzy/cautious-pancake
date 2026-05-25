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
        try (Scanner input = new Scanner(System.in)) {
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
                
                if (!userLogin.checkPasswordComplexity(password)) {
                System.out.println("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number and a special chracter");
                }
        
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
    
    
    
            runQuickChatMenu(input);
         
         }
        
        
        
       }

    private static void runQuickChatMenu(Scanner input) {
      //Main menu loop 
    int userChoice = 0;
    int messageCount = 0;
    int maxMessages = 0;
    
        System.out.println("\nHow many messages would you like to send? ");
        maxMessages = input.nextInt();
        input.nextLine(); //Clear buffer
        
        Message[] messages = new Message[maxMessages];
        
        while (userChoice != 3) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Send Message");
            System.out.println("2. Show Messages");
            System.out.println("3. Quit");
            System.out.println("Enter choice: ");
            userChoice = input.nextInt();
            input.nextLine(); //clear buffer
            
            if (userChoice == 1) {
                if (messageCount >= maxMessages) {
                    System.out.println("You have reached your message limit of " + maxMessages + ".");
                } else {
                    System.out.println("Enter recipient cell number: ");
                    String recipientCell = input.nextLine();
                    
                    System.out.println("Enter your message: ");
                    String messageContent = input.nextLine();
                    
                    messageCount++;
                    String messageID = "MSG" + messageCount;
                    
                    messages[messageCount - 1] = new Message(messageID, messageCount, recipientCell, messageContent);
                    Message msg = messages[messageCount - 1];
                    
                    System.out.println("\n--- Message Validation ---");
                    System.out.println(msg.checkMessageID());
                    System.out.println(msg.checkMessageLength());
                    System.out.println(msg.checkRecipientCell());
                    
                    
                    System.out.println("\n--- Send/Store/Delete ---");
                    System.out.println("1. Send Message");
                    System.out.println("2. Delete Message");
                    System.out.println("3.Store Message");
                    System.out.println("Enter choice: ");
                    int sendChoice = input.nextInt();
                    input.nextLine();
                    
                    System.out.println(msg.SentMessage(sendChoice));
                    
                    if (sendChoice == 3){
                        msg.storeMessage();
                    } 
                }
            }
            else if (userChoice == 2){
                if (messageCount == 0) {
                    System.out.println("No messages sent yet. ");
                }else{
                    System.out.println("\n --- Sent Messages ---");
                    for (int i = 0; i < messageCount; i++) {
                        System.out.println(messages[i].printMessage());
                        System.out.println("----------");
                    }
                }
            }
            else if (userChoice == 3) {
                System.out.println("Goodbye!");
            }
            else{
                System.out.println("Invalid choice, try again.");
            }
            
        }
            input.close();
    
    }
    
    }
   
      

