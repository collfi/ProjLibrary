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
public class Application {

    public class RestURIConstants {
        public static final String DUMMY_EMP = "/rest/emp/dummy";
        public static final String GET_EMP = "/rest/emp/{id}";
        public static final String GET_ALL_EMP = "/rest/emps";
        public static final String CREATE_EMP = "/rest/emp/create";
        public static final String DELETE_EMP = "/rest/emp/delete/{id}";
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
