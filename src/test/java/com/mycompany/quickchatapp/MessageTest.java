/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.quickchatapp;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Student
 */
public class MessageTest {
    
    public MessageTest() {
    }
    
       // Helper to capture System.out for storeMessage test
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    // 1. checkMessageID tests
    @Test
    public void testCheckMessageID_Valid() {
        Message msg = new Message("MSG123", 1, "+27821234567", "Hello");
        assertTrue(msg.checkMessageID());
    }

    @Test
    public void testCheckMessageID_TooLong() {
        Message msg = new Message("MSG12345678901", 1, "+27821234567", "Hello");
        assertFalse(msg.checkMessageID());
    }

    @Test
    public void testCheckMessageID_Null() {
        Message msg = new Message(null, 1, "+27821234567", "Hello");
        assertFalse(msg.checkMessageID());
    }

    // 2. checkMessageLength tests
    @Test
    public void testCheckMessageLength_Valid() {
        Message msg = new Message("MSG1", 1, "+27821234567", "Short message");
        assertEquals("Message ready to send", msg.checkMessageLength());
    }

    @Test
    public void testCheckMessageLength_TooLong() {
        String longMsg = "a".repeat(251);
        Message msg = new Message("MSG1", 1, "+27821234567", longMsg);
        assertEquals("Message exceeds 250 characters by 1; please reduce the length", msg.checkMessageLength());
    }

    @Test
    public void testCheckMessageLength_Null() {
        Message msg = new Message("MSG1", 1, "+27821234567", null);
        assertEquals("Please enter a message that consists of less than 250 characters.", msg.checkMessageLength());
    }

    // 3. checkRecipientCell tests
    @Test
    public void testCheckRecipientCell_Valid() {
        Message msg = new Message("MSG1", 1, "+27821234567", "Hello");
        assertEquals("Cellphone number successfully captured.", msg.checkRecipientCell());
    }

    @Test
    public void testCheckRecipientCell_InvalidFormat() {
        Message msg = new Message("MSG1", 1, "0821234567", "Hello");
        assertTrue(msg.checkRecipientCell().startsWith("Cellphone number is incorrectly formatted"));
    }

    @Test
    public void testCheckRecipientCell_Null() {
        Message msg = new Message("MSG1", 1, null, "Hello");
        assertTrue(msg.checkRecipientCell().startsWith("Cellphone number is incorrectly formatted"));
    }

    // 4. createMessageHash tests
    @Test
    public void testCreateMessageHash_MultiWord() {
        Message msg = new Message("AB123", 5, "+27821234567", "Hello World!");
        // idPrefix = "AB", messageCount = 5, firstWord = "Hello", lastWord = "World"
        assertEquals("AB:5:HELLOWORLD", msg.createMessageHash());
    }

    @Test
    public void testCreateMessageHash_SingleWord() {
        Message msg = new Message("CD456", 2, "+27821234567", "Hello");
        // firstWord == lastWord when only 1 word
        assertEquals("CD:2:HELLOHELLO", msg.createMessageHash());
    }

    @Test
    public void testCreateMessageHash_ErrorNullID() {
        Message msg = new Message(null, 1, "+27821234567", "Hello");
        assertEquals("00:0:Error", msg.createMessageHash());
    }

    @Test
    public void testCreateMessageHash_ErrorShortID() {
        Message msg = new Message("A", 1, "+27821234567", "Hello");
        assertEquals("00:0:Error", msg.createMessageHash());
    }

    @Test
    public void testCreateMessageHash_ErrorEmptyContent() {
        Message msg = new Message("AB123", 1, "+27821234567", " ");
        assertEquals("00:0:Error", msg.createMessageHash());
    }

    // 5. SentMessage tests
    @Test
    public void testSentMessage_AllCases() {
        Message msg = new Message("MSG1", 1, "+27821234567", "Hello");
        assertEquals("Message successfully sent.", msg.SentMessage(1));
        assertEquals("Press 0 to delete the message.", msg.SentMessage(2));
        assertEquals("Message successfully stored.", msg.SentMessage(3));
        assertEquals("Invalid choice selection.", msg.SentMessage(99));
    }

    // 6. printMessage test
    @Test
    public void testPrintMessage() {
        Message msg = new Message("AB123", 1, "+27821234567", "Hello World");
        String expected = "Message ID: AB123\n" +
                          "Message Hash: AB:1:HELLOWORLD\n" +
                          "Recipient: +27821234567\n" +
                          "Message: Hello World";
        assertEquals(expected, msg.printMessage());
    }

    // 7. storeMessage test
    @Test
    public void testStoreMessage() {
        System.setOut(new PrintStream(outContent));
        Message msg = new Message("MSG1", 1, "+27821234567", "Hello");
        msg.storeMessage();
        
        String output = outContent.toString();
        assertTrue(output.contains("Researching JSON transmission conversion output..."));
        assertTrue(output.contains("\"messageID\": \"MSG1\""));
        assertTrue(output.contains("\"messageHash\": \"MSG:1:HELLOHELLO\""));
        
        System.setOut(originalOut); // reset System.out
    }
    

   
}
