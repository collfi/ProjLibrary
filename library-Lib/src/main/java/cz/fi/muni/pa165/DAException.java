/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165;

import org.springframework.dao.DataAccessException;

/**
 * Our custom data access exception which is thrown in persistance layer.
 * 
 * @author michal.lukac, 430614
 */
public class DAException extends DataAccessException {

    public DAException(String msg) {
        super(msg);
    }
    
}
