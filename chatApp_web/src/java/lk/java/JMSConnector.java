package lk.java;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnectionFactory;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author pavit
 */
public class JMSConnector implements Runnable {

    public void run() {
        try {
            Context context = new InitialContext();
            TopicConnectionFactory factory = (TopicConnectionFactory) context.lookup("java:comp/env/mychatappFactory");
          //  TopicConnectionFactory factory = (TopicConnectionFactory) context.lookup("jms/mychatappFactory");
            Connection connection = factory.createConnection();
           // Topic topic = (javax.jms.Topic) context.lookup("java:comp/env/mychatapp");
            Topic topic = (javax.jms.Topic) context.lookup("java:comp/env/mychatapp");
           // Topic topic = (javax.jms.Topic) context.lookup("jms/mychatapp");
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            MessageConsumer consumer = session.createConsumer(topic);

            MessageListenerImpl messageListener = new MessageListenerImpl();
            
            consumer.setMessageListener(messageListener);
            connection.start();

            connection.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
//     void sendJMSMessageToMychatapp(Object messageData) throws JMSException, NamingException {
//        Context c = new InitialContext();
//        ConnectionFactory cf = (ConnectionFactory) c.lookup("java:comp/env/mychatappFactory");
//        Connection conn = null;
//        Session s = null;
//        try {
//            conn = cf.createConnection();
//            s = conn.createSession(false, s.AUTO_ACKNOWLEDGE);
//            Destination destination = (Destination) c.lookup("java:comp/env/mychatapp");
//            MessageProducer mp = s.createProducer(destination);
//            mp.send(createJMSMessageFormychatapp(s, messageData));
//        } finally {
//            if (s != null) {
//                try {
//                    s.close();
//                } catch (JMSException e) {
//                    Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Cannot close session", e);
//                }
//            }
//            if (conn != null) {
//                conn.close();
//            }
//        }
//    }
}
