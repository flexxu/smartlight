package org.smartlight.framework.jms;

import java.io.Serializable;


public class MailMessage implements Serializable{  
    /**
	 * 
	 */
	private static final long serialVersionUID = 2719450852816896661L;
	/** 
     * 邮件标题. 
     */  
    private String subject;
    /** 
     * 邮件内容. 
     */ 
    private String content;
    /** 
     * 发件人. 
     */ 
    private String from;
    /** 
     * 收件人. 
     */ 
    private String to;
    /** 
     * 收件人. 
     */ 
    private String host;
    /** 
     * 收件人. 
     */ 
    private String userName;
    /** 
     * 收件人. 
     */ 
    private String passWord;
    

	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
    
} 