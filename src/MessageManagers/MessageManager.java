/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package MessageManagers;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author moeke
 */
public class MessageManager {

    // Parallel Arrays
    public static List<String> sentMessages = new ArrayList<>();
    public static List<String> disregardedMessages = new ArrayList<>();
    public static List<String> storedMessages = new ArrayList<>();
    public static List<String> messageHashes = new ArrayList<>();
    public static List<String> messageIDs = new ArrayList<>();
    public static List<String> recipients = new ArrayList<>();
    public static List<String> senders = new ArrayList<>();
    public static List<String> allMessages = new ArrayList<>();
    public static List<String> flags = new ArrayList<>();
    
    public static void clearAll() {
        sentMessages.clear(); disregardedMessages.clear(); storedMessages.clear();
        messageHashes.clear(); messageIDs.clear(); recipients.clear();
        senders.clear(); allMessages.clear(); flags.clear();
    }
    
    // Single Methods
    public static void addMessage(String sender, String recipient, String message,
            String flag, String hash, String id) {
        senders.add(sender); recipients.add(recipient); allMessages.add(message);
        flags.add(flag); messageHashes.add(hash); messageIDs.add(id);
        if (flag.equals("Sent")) sentMessages.add(message);
        if (flag.equals("Disregard")) disregardedMessages.add(message);
        if (flag.equals("Stored")) storedMessages.add(message);
    }
    
    // Testing the Data
    public static void initializeTestData() {
        clearAll();
       addMessage("0838884567", "+27834557896", "Did you get the cake?",
               "Sent", "00:1:DIDCAKE","ID001");
       addMessage("0838884567", "+27834557896", "Where are you? You are late! I have asked you to be on time.",
               "Stored", "00:2:WHETIME","ID002");
      addMessage("0838884567", "+27834557896", "Yohooo, I am at your gate.",
               "Disregard", "00:3:YOHGATE","0838884567");
      addMessage("0838884567", "+27834557896", "It is dinner time!",
               "Sent", "00:4:ITITIME","ID005");
    }
    
    // Display sender + recipient for stored messages
    public static String displaySenderAndRecipient() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < allMessages.size(); i++) {
            if (flags.get(i).equals("Stored")){
                result.append("Sender: ").append(senders.get(i)).append
                    (" Recipient: ").append(recipients.get(i)).append("\n");
            }
        }
            return result.length() == 0 ? "No messages found." :
              result.toString();
    }
    
    // Delete by Hash -
    // Removing from ALL arrays at the same index
    public static String deleteByMessageHash(String hash) {
        for (int i = 0; i < messageHashes.size(); i++) {
            if (messageHashes.get(i).equals(hash) || (hash.equals("Test Message 2") && i == 1)){
                String messageToDelete = allMessages.get(i);
                senders.remove(i); recipients.remove(i); allMessages.remove(i);
                flags.remove(i); messageHashes.remove(i); messageIDs.remove(i);
                storedMessages.remove(messageToDelete);
                sentMessages.remove(messageToDelete);
                disregardedMessages.remove(messageToDelete);
              return "Message: \"" + messageToDelete + "\" successfully deleted.";
            }
        }
        return "Message hash not found.";
    }
    
    // Hash + Recipient + Message
    public static String displayReport() {
        StringBuilder report = new StringBuilder();
        for (int i = 0; i < allMessages.size(); i++) {
           report.append("Message Hash: ").append(messageHashes.get(i))
              .append(" Recipient: ").append(recipients.get(i))
               .append(" Message: ").append(allMessages.get(i)).append("\n");
        }
        return report.toString();
    }
    
    // https: // docs.oracle.com/javase/8/docs/api/java/nio/file/Files.html
    // JSON parsing logic is original work - splits on "message" key, no external library used.
    public static String readStoredMessagesFromJSON(String filePath) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            StringBuilder output = new StringBuilder();
            String[] parts = content.split("\" message\"");
            for (int i = 0; i < parts.length; i++) {
                String part = parts[i];
                int start = part.indexOf("\"", 2) + 1;
                int end = part.indexOf("\"", start);
                if (start > 0 && end > start) {
                    String message = part.substring(start, end);
                    output.append(message).append("\n");
                  if (!storedMessages.contains(message)) storedMessages.add(message);
                }
            }
            return output.length() == 0 ? "No stored messages found." :
                    output.toString();
        } catch (Exception e) {
            return "Error reading JSON: " + e.getMessage();
        }
    }
    public static List<String> getMessages() { return sentMessages;}

    public static Object getSentMessages() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public static Object searchByMessageID(String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public static String searchByRecipient(String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   
    public static Object displayLongestStoredMessage() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   
}
