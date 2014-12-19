/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.library.web;

/**
 * @author michal
 */
public class SearchModel {
    private String search;
    private String input;

    private String book;

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getBook() {
        return this.book;
    }

    public void setBook(String book) {
        this.book = book;
    }

}
