/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.library.client;
import cz.fi.muni.pa165.library.data.Book;
import org.springframework.web.client.RestTemplate;


/**
 *
 * @author michal
 */
//http://www.journaldev.com/2552/spring-restful-web-service-example-with-json-jackson-and-client-program
//http://spring.io/guides/gs/rest-service/
public class Application {

    public class RestURIConstants {
        public static final String ADD_BOOK = "/rest/book/add/";
        public static final String DEL_BOOK = "/rest/book/delete/{id}";
        public static final String GET_BOOK = "/rest/book/get/{id}";
        public static final String GET_BOOKS = "/rest/book/find/{name}";
        
        public static final String ADD_MEMBER = "/rest/member/add/";
        public static final String DEL_MEMBER = "/rest/member/delete/{id}";
        public static final String GET_MEMBER = "/rest/member/get/{id}";
        public static final String GET_MEMBERS = "/rest/member/find/{name}";
        
        public static final String ADD_PBOOK = "/rest/pbook/add/";
    }
    
    public static void main(String args[]) {   
        RestTemplate restTemplate = new RestTemplate();
        Book page = restTemplate.getForObject("http://graph.facebook.com/pivotalsoftware", Book.class);
        System.out.println("Name:    " + page.getName());
        System.out.println("About:   " + page.getAbout());
        System.out.println("Phone:   " + page.getPhone());
        System.out.println("Website: " + page.getWebsite());
    }
}
