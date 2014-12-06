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
 * @author michal.lukac
 */
//http://www.journaldev.com/2552/spring-restful-web-service-example-with-json-jackson-and-client-program
//http://spring.io/guides/gs/rest-service/
//http://www.restapitutorial.com/lessons/httpmethods.html
//http://codetutr.com/2013/04/09/spring-mvc-easy-rest-based-json-services-with-responsebody/
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
    
    public static int main(String args[]) {
        
        if(args.length < 2) {
            menu();
        }
        else {
            for (String arg: args) {
                switch(arg) {
                    case "-b":
                        return menu();
                    case "-m":
                        return menu();
                    case "-menu":
                        return menu();
                    case "-h":
                        return help();
                    default:
                        return help();
                }
            }
        }
        
        /*
        RestTemplate restTemplate = new RestTemplate();
        Book page = restTemplate.getForObject("http://graph.facebook.com/pivotalsoftware", Book.class);
        System.out.println("Name:    " + page.getName());
        System.out.println("About:   " + page.getAbout());
        System.out.println("Phone:   " + page.getPhone());
        System.out.println("Website: " + page.getWebsite());
        */
        return 0;
    }
    
    public static int menu() {
        
        return 0;
    }
    
    public static int help() {
        System.out.println("PA165 Library, Interactive REST Client, HELP");
        System.out.println("Put one following argument");
        System.out.println("-b, book management");
        System.out.println("-m, member management");
        System.out.println("-menu, menu");
        System.out.println("-h, help");
        return 0;
    }
}
