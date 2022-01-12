/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
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
    @ActivationConfigProperty(propertyName = "clientId", propertyValue = "mychatapp")
    ,
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "mychatapp")
    ,
        @ActivationConfigProperty(propertyName = "subscriptionDurability", propertyValue = "Durable")
    ,
        @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "mychatapp")
    ,
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
})
public class msgbean implements MessageListener {

    @EJB
    private saveBeanLocal saveBean;

    public msgbean() {
    }

    @Override
    public void onMessage(Message message) {
        TextMessage t = (TextMessage) message;
        try {

            System.out.println(t.getText());
            String msg = t.getText();
            String[] splitArray;
            splitArray = msg.split("\\+");
            String tname = splitArray[0];
            String lati = splitArray[1];
            String longtude = splitArray[2];
            String tem = splitArray[3];
            String speed = splitArray[4];
            String humadity = splitArray[5];

            saveBean.saveData(tname, lati, longtude, tem, speed, humadity);

        } catch (Exception ex) {
            Logger.getLogger(msgbean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
