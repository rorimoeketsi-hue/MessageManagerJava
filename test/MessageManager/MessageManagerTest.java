package MessageManager;

import MessageManagers.MessageManager;
import org.junit.Before;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author moeke
 */
public class MessageManagerTest {
    @Before 
    public void setup() 
    { MessageManager.initializeTestData(); 
    }
    
    @Test 
    public void testSentMessagesArrayCorrectlyPopulated() {
        assertEquals("Did you get the cake?",
                MessageManager.getSentMessages().get(0));
        assertEquals("It is dinner time!", MessageManager.getSentMessages().get(1));
    }
    @Test
    public void testDisplayLongestStoredMessage() {
        assertEquals("Where are you? You are late! I have asked you to be on time.",
                MessageManager.displayLongestStoredMessage());
    }
    @Test
    public void testSearchForMessageID() {
        assertTrue(MessageManager.searchByMessageID("0838884567").contains("It is dinner time!"));
              
    }
    @Test public void testSearchByRecipient(){
        String result = MessageManager.searchByRecipient("+27838884567");
        assertTrue(result.contains("Where are you? You are late! I have asked you to be on time."));
        assertTrue(result.contains("Ok, I am leaving without you."));
    }
       @Test
       public void testDeleteByMessageHash() {
           assertEquals("Message: \"Where are you? You are late! I have asked you to be on time.\" successfully deleted.",
                   MessageManager.deleteByMessageHash("Test Message 2"));
           assertEquals(1, MessageManager.storedMessages.size());
       }
       @Test
       public void testDisplayReport() {
           String report = MessageManager.displayReport();
           assertTrue(report.contains("Message Hash"));
           assertTrue(report.contains("Recipient"));
           assertTrue(report.contains("Did you get the cake?"))
       }
}
