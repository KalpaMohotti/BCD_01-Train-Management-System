package lk.java;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author pavith
 */
@ServerEndpoint("/endpoint")
public class MessageListenerImpl implements MessageListener {

    private static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());

    @OnMessage
    public String onMessage(String message) {
        System.out.println("OnOpenMsg-----------------");
        System.out.println(message);
        return message;
    }

    @OnOpen
    public void onOpen(Session peer) throws IOException {
        System.out.println("call On open ");
        peers.add(peer);
//        broadcast("Socket Connection is Open for Session " + peer.getId());
    }

    @OnClose
    public void onClose(Session peer) {
        System.out.println("call On close");
        peers.remove(peer);
    }

    private static void broadcast(String message) throws IOException {
        System.out.println("peer size"+peers.size());
        peers.forEach((t) -> {
            try {
                t.getBasicRemote().sendText(message);
            } catch (IOException ex) {
                Logger.getLogger(MessageListenerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    /**
     * On JMS Message
     *
     * @param message
     */
    @Override
    public void onMessage(Message message) {
        try {
            TextMessage msg = (TextMessage) message;
            System.out.println("Servlet Context Thread : " + msg.getText());
            MessageListenerImpl.broadcast(msg.getText());
        } catch (JMSException | IOException ex) {
            Logger.getLogger(MessageListenerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
