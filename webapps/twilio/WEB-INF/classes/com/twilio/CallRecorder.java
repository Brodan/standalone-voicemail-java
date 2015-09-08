package com.twilio;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.twilio.sdk.TwilioRestException;

import java.util.ArrayList;
import java.util.List;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class CallRecorder extends HttpServlet {

    public static final String ACCOUNT_SID = "";
    public static final String AUTH_TOKEN = "";
    public static final String YOUR_NUMBER = "+";
    public static final String TWILIO_NUMBER = "+";

    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {

        sendSMS(request.getParameter("From"), request.getParameter("RecordingUrl"));
    }

    public void sendSMS(String from, String recordingLink) {
        try {
            TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);

            // Build a filter for the MessageList
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("Body", 
                "You've received a voicemail from " + 
                from + 
                ". Use the following link to here this message: \n" +
                recordingLink
                )
            );
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