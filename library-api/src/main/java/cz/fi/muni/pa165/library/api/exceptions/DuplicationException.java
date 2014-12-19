/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.library.api.exceptions;


/**
 *
 * @author Boris Valentovic - xvalent2
 */
public class DuplicationException extends RuntimeException {

    public DuplicationException(String msg) {
        super(msg);
    }
}