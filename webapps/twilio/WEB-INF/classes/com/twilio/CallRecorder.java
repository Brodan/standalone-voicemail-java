package com.twilio;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.twilio.sdk.verbs.TwiMLResponse;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.verbs.TwiMLException;

import java.util.ArrayList;
import java.util.List;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.twilio.sdk.verbs.Record;
import com.twilio.sdk.verbs.Say;

public class AnsweringMachine extends HttpServlet {

    public static final String ACCOUNT_SID = "";
    public static final String AUTH_TOKEN = "";
    public static final String YOUR_NUMBER = "";
    public static final String TWILIO_NUMBER = "";

    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        

        // Respond with TwiML
        response.setContentType("application/xml");
        response.getWriter().print(twiml.toXML()); 

        //sendSMS();
    }

    public void sendSMS() {
        try {
            TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);

            // Build a filter for the MessageList
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("Body", "Jenny please?! I love you <3"));
            params.add(new BasicNameValuePair("To", YOUR_NUMBER));
            params.add(new BasicNameValuePair("From", TWILIO_NUMBER));
         
            MessageFactory messageFactory = client.getAccount().getMessageFactory();
            Message message = messageFactory.create(params);
            System.out.println(message.getSid());
        }
        catch (TwilioRestException e) {
            System.out.println(e.getErrorMessage());
        }
    }
}