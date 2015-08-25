package org.smartlight.framework.jms;

import java.io.ByteArrayInputStream;

import java.io.ByteArrayOutputStream;

import java.io.IOException;

import java.io.ObjectInputStream;

import java.io.ObjectOutputStream;

import java.util.HashMap;

import javax.jms.JMSException;

import javax.jms.Message;

import javax.jms.ObjectMessage;

import javax.jms.Session;

import org.apache.activemq.command.ActiveMQObjectMessage;

import org.springframework.jms.support.converter.MessageConversionException;

import org.springframework.jms.support.converter.MessageConverter;


/**
 * JMS消息转换器
 */
public class DefaultMessageConverter implements MessageConverter {
	
	public Message toMessage(Object obj, Session session) throws JMSException {
		// check Type
		ActiveMQObjectMessage objMsg = (ActiveMQObjectMessage) session
				.createObjectMessage();
		HashMap<String, byte[]> map = new HashMap<String, byte[]>();
		try {
			// POJO must implements Seralizable
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(obj);
			map.put("POJO", bos.toByteArray());
			objMsg.setObjectProperty("Map", map);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return objMsg;

	}

	@SuppressWarnings("unchecked")
	public Object fromMessage(Message msg) throws JMSException {
		if (msg instanceof ObjectMessage) {
			HashMap<String, byte[]> map = (HashMap<String, byte[]>) ((ObjectMessage) msg).getObjectProperty("Map");
			try {
				// POJO must implements Seralizable
				ByteArrayInputStream bis = new ByteArrayInputStream(
						map.get("POJO"));
				ObjectInputStream ois = new ObjectInputStream(bis);
				return ois.readObject();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return null;
		} else {
			throw new JMSException("Msg:[" + msg + "] is not Map");
		}
	}
}