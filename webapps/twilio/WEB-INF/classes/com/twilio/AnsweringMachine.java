package com.twilio;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.twilio.sdk.verbs.TwiMLResponse;
import com.twilio.sdk.verbs.TwiMLException;

import com.twilio.sdk.verbs.Record;
import com.twilio.sdk.verbs.Say;

public class AnsweringMachine extends HttpServlet {

    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        TwiMLResponse twiml = new TwiMLResponse();

        Say pleaseLeaveMessage = new Say("Please leave your message for Brodan now.");
        // Record the caller's voice.
        Record record = new Record();
        record.setMaxLength(60);
        record.setTimeout(5);
        record.setAction("/twilio/handle-recording");
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