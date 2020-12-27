package entity;

import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

/**
 * Created by Administrator on 2020/12/18.
 */
public class SmsUtils implements MessageCreator {

    private String phone;
    private String content;

    public SmsUtils(String phone, String content) {
        this.phone = phone;
        this.content = content;
    }

    @Override
    public Message createMessage(Session session) throws JMSException {
        MapMessage mapMessage = session.createMapMessage();
        mapMessage.setString("phone",phone);
        mapMessage.setString("content",content);
        return mapMessage;
    }
}
