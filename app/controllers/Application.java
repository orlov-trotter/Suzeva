package controllers;

import static play.data.Form.form;
import play.*;
import play.mvc.*;

import com.typesafe.plugin.*;

import views.html.*;

public class Application extends Controller {
	
	static String msg = "";
  
    public static Result index() {
        return ok(index.render());
    }
    
    public static Result getStarted() {
    	return ok(start.render());
    }
    
    public static Result buyBasic(){
    	return ok(standard.render(msg));
    }
    
    public static Result contact(){
    	String name = form().bindFromRequest().get("name");
    	String email = form().bindFromRequest().get("email");
    	String companyId = form().bindFromRequest().get("companyId");
    	String phone = form().bindFromRequest().get("phone");
    	String comments = form().bindFromRequest().get("comment");
    	
    //Send Email
    	MailerAPI mail = play.Play.application().plugin(MailerPlugin.class).email();
    	mail.setSubject("mailer");
    	mail.addRecipient("prashant@suzeva.com","sanjeev@suzeva.com","rajendra@suzeva.com");
    	mail.addFrom("prashant@suzeva.com");
    	//sends html
    	mail.sendHtml("<html><p>Following Person has made an Inquiry at www.suzeva.com:</p><p>Name="+name+"</p><p>Company="+companyId+"</p><p>Email="+email+"</p><p>Phone="+phone+"</p><p>Comments="+comments+"</p></html>" );
    	msg = "A Suzeva Representative will contact you shortly";
    	return ok(standard.render(msg));
    }
}
