package org.smartlight.framework.jms;

import javax.jms.JMSException;

//import mail.MimeMailService;

public interface MessageHandler {  
    /** 
     * Handle the message. 
     * 
     * @param obj 
     *            消息对象 
     * @throws JMSException 
     *             exception 
     */  
    void handle(Object obj) throws JMSException;  
} 