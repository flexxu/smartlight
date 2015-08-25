package org.smartlight.framework.jms;

import javax.jms.JMSException;

//import mail.MimeMailService;



public class DefaultMessageHandler implements MessageHandler{

    //private static Logger logger = LoggerFactory.getLogger(OrderMessageConsumer.class);

    private JmsTemplateMail jmstempMail;
    
    /**
     * @param jmstempMail the jmstempMail to set
     */
    public void setJmstempMail(JmsTemplateMail jmstempMail) {
       this.jmstempMail = jmstempMail;
    }

    public void handle(Object obj) {
       System.out.println("已经接受到消息,发送邮件中............");
        jmstempMail.sendNotificationMail(obj);

    }

}