package com.twilio;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.twilio.sdk.verbs.TwiMLResponse;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.verbs.TwiMLException;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class AnsweringMachine extends HttpServlet {

    public static final String ACCOUNT_SID = "";
    public static final String AUTH_TOKEN = "";

    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        TwiMLResponse twiml = new TwiMLResponse();

        try {  
            twiml.append("INSERT RECORD HERE");
        } catch (TwiMLException e) {
            e.printStackTrace();
        }
        // Respond with TwiML
        response.setContentType("application/xml");
        response.getWriter().print(twiml.toXML()); 
    }

    public void sendSMS(){
        TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);

        // Build a filter for the MessageList
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("Body", "Jenny please?! I love you <3"));
        params.add(new BasicNameValuePair("To", ""));
        params.add(new BasicNameValuePair("From", ""));
     
        MessageFactory messageFactory = client.getAccount().getMessageFactory();
        Message message = messageFactory.create(params);
        System.out.println(message.getSid());
    }
}