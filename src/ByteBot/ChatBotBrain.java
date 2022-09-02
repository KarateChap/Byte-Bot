package ByteBot;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import com.sun.speech.freetts.*;
/**
 * This class will serve as the brain for the whole chat bot. It will store knowledge that is needed for the conversation between the user and the chat bot.
 * @author Bugay, Maglinao, Mariano, Olivar, Susara
 * @since 2019-03-28
 * 
 * @version 1.0
 */
public class ChatBotBrain {
    
    /**
    *
    */
    static ArrayList knowledgeBase = new ArrayList();
    static ArrayList responses = new ArrayList();
    final private String plainText = " abcdefghijklmnopqrstuvwxyz1234567890.'?!?,:";
    final private String cipherText = "ΑΒΓΔΕΖΗΘΙΚΛΜΝΞΟ$ΠΡΣ*ΤFΥΦЖЛРСТеймпёѬѥѧѿ҂Ѹѳ7+Ҟ";
    final private char[] plain = plainText.toLowerCase().toCharArray();
    final private char[] cipher = cipherText.toLowerCase().toCharArray();
    private int loopCount = 0;
    private int localCount = 0;
    static final String VOICENAME = "kevin16";
    
    /**
    * This method checks if there is a word that matches the words stored at KnowledgeDatabase.txt and ResponseDatabase.txt and then responses to the user accordingly.
    * @param message String that  sets and gets data from KnowledgeDatabase.txt and ResponseDatabase.txt.
    * @return "BYTEBOT can't understand you".
    * 
    */
    public String analyzeChat(String message){               
            knowledgeBase.add("name");
            responses.add("Hi! I am BYTEBOT, your personal chatbot!");
            
        try{
            FileReader knowledgeReader = new FileReader("KnowledgeDatabase.txt");
            BufferedReader knowledgeBuffRead = new BufferedReader(knowledgeReader);
            FileReader responseReader = new FileReader("ResponseDatabase.txt");
            BufferedReader responseBuffRead = new BufferedReader(responseReader);
            
        while(knowledgeBuffRead.ready()){
            
            String message1 = (String)knowledgeBuffRead.readLine();
            char[] message2 = message1.toCharArray();
            String message3 = "";
            
            for(char charMessage : message2){
            while(charMessage != cipher[localCount]){
                localCount++;
            }
            message3 += String.valueOf(plain[localCount]);
            localCount = 0;
        }
            knowledgeBase.add(message3.toLowerCase());
        }
        while(responseBuffRead.ready()){
            String message1 = (String)responseBuffRead.readLine();
            char[] message2 = message1.toCharArray();
            String message3 = "";
            
            for(char charMessage : message2){
            while(charMessage != cipher[localCount]){
                localCount++;
            }
            message3 += String.valueOf(plain[localCount]);
            localCount = 0;
        }
            
            responses.add(message3.toLowerCase());
        }    
        }
        catch(IOException e){  
        }
            for(Object newKnowledge : knowledgeBase){
                if (message.toLowerCase().contains((CharSequence) newKnowledge)){
                    Voice voice;
                    VoiceManager voiceManager = VoiceManager.getInstance();
                    voice = voiceManager.getVoice(VOICENAME);
        
                    voice.allocate();
                try{
                    voice.speak((String) responses.get(loopCount));
        }
                catch(Exception e){
        }
                return (String) responses.get(loopCount);
            }
            loopCount++;
                }
            Voice voice;
        VoiceManager voiceManager = VoiceManager.getInstance();
        voice = voiceManager.getVoice(VOICENAME);
        
        voice.allocate();
        
        try{
            voice.speak("BYTEBOT can't understand you");
        }
        catch(Exception e){
        }
        return "BYTEBOT can't understand you";  
}
    
    /**
    * This method accepts, encrypts, and stores knowledge to KnowledgeDatabase.txt. Runs when the user's input has the word "learn".
    * @param message String that passes knowledge to the method to store knowledge taught by  the user.
    * @return "What should I response to that".
    */
    public String learn(String message){
        try{
            FileWriter knowledgeWriter = new FileWriter("KnowledgeDatabase.txt",true);
            FileReader knowledgeReader = new FileReader("KnowledgeDatabase.txt");
            BufferedReader knowledgeBuffRead = new BufferedReader(knowledgeReader);
            
            String message1 = "";
            char[] message2 = message.toLowerCase().toCharArray();
            for(char charMessage : message2){
            while(charMessage != plain[localCount]){
                localCount++;
            }
            message1 += String.valueOf(cipher[localCount]);
            localCount = 0;
        }
            knowledgeWriter.write(message1.toLowerCase());
            knowledgeWriter.write("\r\n");
            knowledgeWriter.close();
            
            while(knowledgeBuffRead.ready()){
            String message5 = "";
            String message3 = (String)knowledgeBuffRead.readLine();
            char[] message4 = message3.toCharArray();
            
            for(char charMessage : message4){
            while(charMessage != cipher[localCount]){
                localCount++;
            }
            message5 += String.valueOf(plain[localCount]);
            localCount = 0;
        }
  
            knowledgeBase.add(message5.toLowerCase());
        }   
        }
        catch(IOException e){  
        }
        Voice voice;
        VoiceManager voiceManager = VoiceManager.getInstance();
        voice = voiceManager.getVoice(VOICENAME);
        
        voice.allocate();
        
        try{
            voice.speak("What should I response into that?");
        }
        catch(Exception e){
        }
            return "What should I response into that?";
    }   
    
    /**
    * This method stores the value of the message  to the ResponseDatabase.txt.
    * @param message String that passes responses to the method to store responses taught by  the user.
    * @return "Thank you!".
  
    */
    public String response(String message){
         try{
            FileWriter responseWriter = new FileWriter("ResponseDatabase.txt",true);
            FileReader responseReader = new FileReader("ResponseDatabase.txt");
            BufferedReader responseBuffRead = new BufferedReader(responseReader);
            
            String message1 = "";
            char[] message2 = message.toLowerCase().toCharArray();
            for(char charMessage : message2){
            while(charMessage != plain[localCount]){
                localCount++;
            }
            message1 += String.valueOf(cipher[localCount]);
            localCount = 0;
        }
            responseWriter.write(message1.toLowerCase());        
            responseWriter.write("\r\n");
            responseWriter.close();
            while(responseBuffRead.ready()){
            String message6 = "";
            String message3 = (String)responseBuffRead.readLine();
            char[] message4 = message3.toCharArray();
            
            for(char charMessage : message4){
            while(charMessage != cipher[localCount]){
                localCount++;
            }
            message6 += String.valueOf(plain[localCount]);
            localCount = 0;
        }
            
            responses.add(message6.toLowerCase());
            } 
        }
        catch(IOException e){   
        }
        Voice voice;
        VoiceManager voiceManager = VoiceManager.getInstance();
        voice = voiceManager.getVoice(VOICENAME);
        
        voice.allocate();
        
        try{
            voice.speak("Thank you!");
        }
        catch(Exception e){
        }
            return "Thank you!";
 }
    
    /**
     * This method executes D:\\Program Files\\Benbox\\3.7.99\\bin\\benbox.exe by sending the word "draw".
     */
    public static void draw(){
        try{
        Process program = Runtime.getRuntime().exec("C:\\Benbox\\3.7.99\\bin\\benbox.exe");
        
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
