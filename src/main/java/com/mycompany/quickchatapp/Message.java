/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quickchatapp;
import java.util.regex.Pattern;
/**
 *
 * @author Student
 */
public class Message {  //This is an instance feild for message tracking
    private String messageID;
    private int messageCount;
    private String recipientCell;
    private String messageContent;
    
    //To initialize a messsage profile
    public Message(String messageID, int messageCount, String recipientCell, String messageContent) {
        this.messageID = messageID;
        this.messageCount = messageCount;
        this.recipientCell = recipientCell;
        this.messageContent = messageContent;
    }

    
    //Validate wether the Message ID is no more than 10 characters
    public boolean checkMessageID() {
        if (this.messageID == null) return false;
        return this.messageID.length() <= 10;
    }
    
    //Measures the character limit requirements for the body of the message
    public String checkMessageLength() {
        if (this.messageContent == null) {
            return "Please enter a message that consists of less than 250 characters.";
        }
        if (this.messageContent.length() <= 250) {
            return "Message ready to send";
        } else {
            int exceededBy = this.messageContent.length() - 250;
            return "Message exceeds 250 characters by " + exceededBy + "; please reduce the length";
        }
    }
    
    //Uses the criteria from part 1 to validate recipient cellphone numbers 
    public String checkRecipientCell() {
        if (this.recipientCell == null) {
            return "Cellphone number is incorrectly formatted or does not contain an international code. Please correct the number and try again";
        }
        //This validates the code prefix "+" followed by the digits up to 10 characters total limit
        String regex = "^\\+\\d{1,3}\\d{1,10}$";
        if (this.recipientCell.matches(regex)) {
            return "Cellphone number successfully captured.";
        } else {
            return "Cellphone number is incorrectly formatted or does not contain an international code. Please correct the number and try again";
        }
    }
    
    //This autogenerates the uppercase Message Hash String 
    public String createMessageHash() {
        if (this.messageID ==null || this.messageID.length() < 2 || this.messageContent.trim().isEmpty()) {
           return "00:0:Error"; 
        }
        //Extracting the first 2 numbers of the message ID
        String idPrefix = this.messageID.substring(0,2);
        
        //Isolate the first and last words of the text content
        String[] words = this.messageContent.trim().split("\\s+");
        String firstWord = words[0];
        String lastWord = words[words.length - 1];
        
        //Remove the punctuation marks to match standard test formatting clean output
        firstWord = firstWord.replaceAll("[^a-zA-Z0-9]", "");
        lastWord = lastWord.replaceAll("[^a-zA-Z0-9]", "");
        
        //Constructing the combined token key in ALL CAPS
        String rawHash = idPrefix + ":" + this.messageCount + ":" + firstWord + lastWord;
        return rawHash.toUpperCase();
    }
    
        //This handles post-composition deployment statuses
        public String SentMessage(int userChoice) {
            switch (userChoice) {
                case 1:
                    return "Message successfully sent.";
                case 2: 
                    return "Press 0 to delete the message.";
                case 3:
                    return "Message successfully stored.";
                default: 
                    return "Invalid choice selection.";
            }
        }
        
        //Formats all of the active message metrics for the terminal output displays 
        public String printMessage() {
            return "Message ID: " + this.messageID + "\n" +
                    "Message Hash: " + createMessageHash() + "\n" +
                    "Recipient: " + this.recipientCell + "\n" +
                    "Message: " + this.messageContent;
        }
        
        //Simulating the data compilation to JSON format
        public void storeMessage() {
            System.out.println("Researching JSON transmission conversion output...");
            String jsonMock = "{\n" +
                    " \"messageID\": \"" + this.messageID + "\",\n" +
                    " \"messageHash\": \"" + createMessageHash() + "\",\n" +
                    " \"recipient\"" + this.recipientCell + "\",\n" +
                    " \"messageContent\":"  + this.messageContent + "\",\n" +
                    "}";
            System.out.println("jsonMock");
                            
        } 

   
    
}
