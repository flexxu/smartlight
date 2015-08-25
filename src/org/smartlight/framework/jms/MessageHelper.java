package org.smartlight.framework.jms;

import org.springframework.jms.core.JmsTemplate;

/**
 *
 * 发送发邮件消息.
 *
 */
public class MessageHelper {
    private JmsTemplate jmsTemplate;
    
    /**
     * 发送发邮件消息：当接到该消息后，请立马发送邮件(邮件内容根据消息内容来确定)
     * @param obj,邮件内容对象
     *  */
    public void sendMail(MailMessage mailMessage) {
       System.out.println("发送消息中...................");
       jmsTemplate.convertAndSend(mailMessage);
    }

    public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
       this.jmsTemplate = jmsTemplate;
    }
}