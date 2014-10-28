/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.service.api;

import cz.fi.muni.pa165.datatransferobject.BookDTO;

/**
 *
 * @author michal
 */
public interface BookService {
    public void insertBook(BookDTO pbookto);
    public BookDTO findBook(BookDTO pbookto);       

}
