/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import javax.ejb.Local;

/**
 *
 * @author Kalpa Sadaruwan
 */
@Local
public interface saveBeanLocal {

    void saveData(String t_name, String longtude, String latitude, String tempeture, String speed, String humidity);
    
}
