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

    public static final String YOUR_NUMBER = "";
    public static final String TWILIO_NUMBER = "";

    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        TwiMLResponse twiml = new TwiMLResponse();

        Say pleaseLeaveMessage = new Say("Please leave your message for Brodan now.");
        // Record the caller's voice.
        Record record = new Record();
        record.setMaxLength(60);
        record.setTimeout(5);
        record.setAction("/handle-recording");
        record.setPlayBeep(true);

        try {
            twiml.append(pleaseLeaveMessage);
            twiml.append(record);
        } catch (TwiMLException e) {
            e.printStackTrace();
        }

        // Respond with TwiML
        response.setContentType("application/xml");
        response.getWriter().print(twiml.toXML()); 

    }
}