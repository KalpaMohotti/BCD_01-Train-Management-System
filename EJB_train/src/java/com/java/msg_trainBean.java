/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 *
 * @author Kalpa Sadaruwan
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "clientId", propertyValue = "train")
    ,
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "train")
    ,
        @ActivationConfigProperty(propertyName = "subscriptionDurability", propertyValue = "Durable")
    ,
        @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "train")
    ,
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
})
public class msg_trainBean implements MessageListener {
    
    public msg_trainBean() {
    }
    
    @Override
    public void onMessage(Message message) {
         TextMessage t=(TextMessage) message;
        try {
            System.out.println(t.getText());
        } catch (JMSException ex) {
            Logger.getLogger(msg_trainBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
