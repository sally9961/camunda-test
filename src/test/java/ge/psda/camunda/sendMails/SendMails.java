package ge.psda.camunda.sendMails;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.impl.util.json.JSONObject;
import javax.mail.PasswordAuthentication;




public class SendMails implements JavaDelegate{
	
	String  text,from,password;
	Object jsonObject1=new JSONObject();
 public static void send( final String from, final String password,String to,String sub,String msg){  
	 Properties props = new Properties();    
     props.put("mail.smtp.host", "smtp.gmail.com");    
     props.put("mail.smtp.socketFactory.port", "465");    
     props.put("mail.smtp.socketFactory.class",    
               "javax.net.ssl.SSLSocketFactory");    
     props.put("mail.smtp.auth", "true");    
     props.put("mail.smtp.port", "465");    
     //get Session   
     Session session = Session.getDefaultInstance(props,    
      new javax.mail.Authenticator() {    
      protected PasswordAuthentication getPasswordAuthentication() {    
      return new PasswordAuthentication(from,password);  
      }    
     });    
     //compose message    
     try {    
      MimeMessage message = new MimeMessage(session);    
      message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));    
      message.setSubject(sub);    
      message.setText(msg);    
      //send message  
      Transport.send(message);    
      System.out.println("message sent successfully");    
     } catch (MessagingException e) {throw new RuntimeException(e);}    
        
}  

	public void execute(DelegateExecution execution) throws Exception {
        
        text = execution.getVariable("selectedDocuments").toString();
        send("sally.tkhilaishvili@gmail.com","xxxxx","salome.tkhilaishvili@gmail.com","hello javatpoint","How r u?");  
       
	    }
	}

