/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.util.ArrayList;
import javax.ejb.Singleton;

/**
 *
 * @author Kalpa Sadaruwan
 */
@Singleton
public class saveBean implements saveBeanLocal {

    @Override
    public void saveData(String t_name, String longtude, String latitude, String tempeture, String speed, String humidity) {

        ArrayList<train_Details> trOb = new ArrayList<>();
        train_Details tr = new train_Details();
        tr.setT_name(t_name);
        tr.setLongtude(longtude);
        tr.setLatitude(latitude);
        tr.setTem(tempeture);
        tr.setSpeed(speed);
        tr.setHumadity(humidity);

        trOb.add(tr);

        for (train_Details t : trOb) {
            System.out.println(t_name);
            System.out.println(longtude);
            System.out.println(latitude);
            System.out.println(tempeture);
            System.out.println(speed);
            System.out.println(humidity);
        }
    }

}
