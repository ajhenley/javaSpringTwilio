package byaj;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@RestController
public class HomeController {
    @Autowired
    private TextRepository textRepository;
    
	public static final String ACCOUNT_SID = "ACd19512531079e6304dfa8f4a050ce0a3";
	public static final String AUTH_TOKEN = "db7ae7b872283b113a59e4243e90c262";
	  
    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
    
    @RequestMapping("/send")
    public String mess(){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber("+14432535494"),
            new PhoneNumber("+14435783283"), 
            "This is the ship that made the Kessel Run in fourteen parsecs?").create();

        return message.getSid();
    }
    
    @RequestMapping(value = "/incoming", method = RequestMethod.POST)
    public void incoming(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String fromNumber = request.getParameter("From");
        String message = request.getParameter("Body");
        Textings t = new Textings();
        t.setFrom(fromNumber);
        t.setContent(message);
        textRepository.save(t);
    }
    
    @RequestMapping("/all")
    public @ResponseBody Iterable<Textings>  getAllTexts() {
    	return textRepository.findAll();
    }
}
