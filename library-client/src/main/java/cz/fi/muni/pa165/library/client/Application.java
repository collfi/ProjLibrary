package cz.fi.muni.pa165.library.client;
import cz.fi.muni.pa165.datatransferobject.LoanDTO;
import cz.fi.muni.pa165.datatransferobject.MemberDTO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


/**
 * Rest interactive console client.
 * @author michal.lukac
 */
//http://www.journaldev.com/2552/spring-restful-web-service-example-with-json-jackson-and-client-program
//http://spring.io/guides/gs/rest-service/
//http://www.restapitutorial.com/lessons/httpmethods.html
//http://codetutr.com/2013/04/09/spring-mvc-easy-rest-based-json-services-with-responsebody/
public class Application {

    public static final int EOK = 0;
    public static final int UERROR = 1;
    public static final int NOTEER = 2;
    public static final int BADADDR = 3;
    public static final int ERRPARS = 4;
    
    public static Map<Integer, String> errorCodes;
    public static final String PA165URL = "http://localhost:8084/pa165";

    public static class RestURIConstants {
        public static final String ADD_BOOK = "/api/book/add/";
        public static final String DEL_BOOK = "/api/book/delete/";
        public static final String GET_BOOK = "/api/book/get/";
        public static final String GET_BOOKS = "/api/book/find/";
        
        public static final String ADD_MEMBER = "/api/member/add/";
        public static final String DEL_MEMBER = "/api/member/delete/";
        public static final String GET_MEMBER = "/api/member/get/";
        public static final String GET_MEMBERS = "/api/member/find/";
        public static final String UPD_MEMBER = "/api/member/update/";
        
        public static final String ADD_PBOOK = "/api/pbook/add/";
    }
        
    public static void main(String args[]) {
        try {
        // initialize
        errorCodes = new HashMap<Integer, String>();
        errorCodes.put(EOK, "Bye!");
        errorCodes.put(UERROR, "Unexpected error!");
        errorCodes.put(NOTEER, "No entity in Database with specific query!");
        errorCodes.put(BADADDR, "Sorry bad api address to server!");
        errorCodes.put(ERRPARS, "Error with parsing data!");

        int retvalue = 0;
        if(args.length < 2) {
            menu();
        }
        else {
            for (String arg: args) {
                switch(arg) {
                    case "-b":
                        menu();
                        break;
                    case "-m":
                        menu();
                        break;
                    case "-menu":
                        menu();
                        break;
                    case "-h":
                        help();
                        break;
                    default:
                        help();
                        break;
                }
            }
        }
        } catch (Exception e) {
            printError(1);
        }
        
        return;
    }
    
    public static int menu() {
        System.out.println("REST INTERACTIVE CLIENT MENU");
        System.out.println("1. Book service");
        System.out.println("2. Member service");
        System.out.println("3. Exit");
        System.out.println("Press 1-2 and enter to continue!");
        
        Scanner s = new Scanner(System.in);
        String str = s.nextLine();
        
        switch (str) {
            case "1":
                return bookMenu();
            case "2":
                return memberMenu();
            case "3":
                return EOK;
            default:
                return menu();
        }        
    }

    private static int bookMenu() {
        return 1;
    }

    private static int memberMenu() {
        System.out.println("REST: INTERACTIVE CLIENT MENU");
        System.out.println("1. Get member");
        System.out.println("2. Delete member");
        System.out.println("3. Find member");
        System.out.println("4. Update member");
        System.out.println("5. Add member");
        System.out.println("6. Menu");

        System.out.println("Press 1-6 and enter to continue!:");
        
        Scanner s = new Scanner(System.in);
        String str = s.nextLine();
        
        switch (str) {
            case "1":
                return getMember(EOK);
            case "2":
                return deleteMember(EOK);
            case "3":
                return findMember(EOK);
            case "4":
                return updateMember(EOK);
            case "5":
                return addMember(EOK);
            case "6":
                return menu();
            default:
                return memberMenu();
        }   
    }

    private static int updateMember(int error) {
        System.out.println("REST: UPDATE MEMBER");
        printError(error);
        System.out.println("1. Write \"id name email address\" or 0 if you want to go back to menu!:");
        
        Scanner s = new Scanner(System.in);
        String str = s.nextLine();

        switch (str) {
            case "0": return memberMenu();
            default:
                try {
                    RestTemplate restTemplate = new RestTemplate();
                    String[] data = str.trim().split(" ");
                    if(data.length != 4) {
                        throw new Exception();
                    }
                    
                    MemberDTO member = new MemberDTO();
                    member.setIdMember(Integer.parseInt(data[0]));
                    member.setEmail(data[2]);
                    member.setAddress(data[3]);
                    member.setName(data[1]);
                    
                    System.out.println("saving:" + member.toString());
                    String response = restTemplate.postForObject(PA165URL+RestURIConstants.UPD_MEMBER, member,  String.class);
                    if(response == null) {
                        throw new NullPointerException();
                    }
                    else {
                        System.out.println(str);
                    }

                    break;
                }
                catch (IllegalStateException | HttpClientErrorException e) {
                    return updateMember(BADADDR);
                }
                catch (NullPointerException e) {
                    return updateMember(NOTEER);
                }
                catch (Exception e) {
                    return updateMember(ERRPARS);
                }

        }
        
        System.out.println("Press any key to continue!");
        s.nextLine();
        return updateMember(EOK);
    }

    private static int addMember(int error) {
        System.out.println("REST: ADD MEMBER");
        printError(error);
        System.out.println("1. Write \"name email address\" or 0 if you want to go back to menu!:");
        
        Scanner s = new Scanner(System.in);
        String str = s.nextLine();

        switch (str) {
            case "0": return memberMenu();
            default:
                try {
                    RestTemplate restTemplate = new RestTemplate();
                    String[] data = str.trim().split(" ");
                    if(data.length != 3) {
                        throw new Exception();
                    }
                    
                    MemberDTO member = new MemberDTO();
                    member.setEmail(data[1]);
                    member.setAddress(data[2]);
                    member.setName(data[0]);
                    
                    System.out.println("saving:" + member.toString());
                    String response = restTemplate.postForObject(PA165URL+RestURIConstants.ADD_MEMBER, member,  String.class);
                    if(response == null) {
                        throw new NullPointerException();
                    }
                    else {
                        System.out.println(str);
                    }
                }
                catch (IllegalStateException | HttpClientErrorException e) {
                    return addMember(BADADDR);
                }
                catch (NullPointerException e) {
                    return addMember(NOTEER);
                }
                catch (Exception e) {
                    return addMember(ERRPARS);
                }

        }
        
        System.out.println("Press any key to continue!");
        s.nextLine();
        return addMember(EOK);
    }
    
    
    private static int findMember(int error) {
        System.out.println("REST: FIND MEMBER");
        printError(error);
       
        System.out.println("REST: INTERACTIVE CLIENT MENU");
        System.out.println("1. Find members by name");
        System.out.println("2. Find members by address");
        System.out.println("3. back");

        System.out.println("Press 1-3 and enter to continue!:");
        
        Scanner s = new Scanner(System.in);
        String str = s.nextLine();
        

        switch (str) {
            case "1": return findMembersByName(EOK);
            case "2": return findMembersByAddress(EOK);
            case "3": return memberMenu();
            default:
                return findMember(EOK);            
        }
    }
    
    private static int findMembersByName(int error) {
        System.out.println("REST: FIND MEMBER by name");
        printError(error);
        System.out.println("1. Write name, or 0 if you want to go back!:");
        
        Scanner s = new Scanner(System.in);
        String str = s.nextLine();

        switch (str) {
            case "0": return findMember(EOK);
            default:
                try {
                    RestTemplate restTemplate = new RestTemplate();
                    MemberDTO[] page = restTemplate.getForObject(PA165URL+RestURIConstants.GET_MEMBERS+"?name="+str, MemberDTO[].class);
                    for(MemberDTO member : page) {
                        System.out.println("Name: " + member.getName() + " Address: " + member.getAddress() + " Email: " + member.getEmail());
                    }
                }
                catch (IllegalStateException | HttpClientErrorException e) {
                    return findMembersByName(BADADDR);
                }
                catch (NullPointerException e) {
                    return findMembersByName(NOTEER);
                }
        }
        
        System.out.println("Press any key to continue!");
        s.nextLine();
        return findMembersByName(EOK);
    }

    private static int findMembersByAddress(int error) {
        System.out.println("REST: FIND MEMBER by address");
        printError(error);
        System.out.println("1. Write address, or 0 if you want to go back!:");
        
        Scanner s = new Scanner(System.in);
        String str = s.nextLine();

        switch (str) {
            case "0": return findMember(EOK);
            default:
                try {
                    RestTemplate restTemplate = new RestTemplate();
                    MemberDTO[] page = restTemplate.getForObject(PA165URL+RestURIConstants.GET_MEMBERS+"?address="+str, MemberDTO[].class);
                    for(MemberDTO member : page) {
                        System.out.println("Name: " + member.getName() + " Address: " + member.getAddress() + " Email: " + member.getEmail());
                    }
                }
                catch (IllegalStateException | HttpClientErrorException e) {
                    return findMembersByAddress(BADADDR);
                }
                catch (NullPointerException e) {
                    return findMembersByAddress(NOTEER);
                }
        }
        
        System.out.println("Press any key to continue!");
        s.nextLine();
        return findMembersByAddress(EOK);
    }

    private static int deleteMember(int error) {
        System.out.println("REST: DELETE MEMBER by ID");
        printError(error);
        System.out.println("1. Write Id > 0, or 0 if you want to go back to menu!:");
        
        Scanner s = new Scanner(System.in);
        String str = s.nextLine();

        switch (str) {
            case "0": return memberMenu();
            default:
                try {
                    RestTemplate restTemplate = new RestTemplate();
                    MemberDTO page = restTemplate.getForObject(PA165URL+RestURIConstants.DEL_MEMBER+str, MemberDTO.class);
                    if(page == null) {
                        throw new NullPointerException();
                    }
                    else {
                        System.out.println("Deleted member with id="+str);
                    }
                    break;
                }
                catch (IllegalStateException | HttpClientErrorException e) {
                    return deleteMember(BADADDR);
                }
                catch (NullPointerException e) {
                    return deleteMember(NOTEER);
                }
        }
        
        System.out.println("Press any key to continue!");
        s.nextLine();
        return deleteMember(EOK);
    }
    
    private static int getMember(int error) {
        System.out.println("REST: GET MEMBER by ID");
        printError(error);
        System.out.println("1. Write Id > 0, or 0 if you want to go back to menu!:");
        
        Scanner s = new Scanner(System.in);
        String str = s.nextLine();

        switch (str) {
            case "0": return memberMenu();
            default:
                try {
                    RestTemplate restTemplate = new RestTemplate();
                    MemberDTO page = restTemplate.getForObject(PA165URL+RestURIConstants.GET_MEMBER+str, MemberDTO.class);
                    System.out.println("Name:    " + page.getName());
                    System.out.println("Address:   " + page.getAddress());
                    System.out.println("Email:   " + page.getEmail());
                    System.out.println("Loans:");
                    for(LoanDTO loan : page.getLoans()) {
                        System.out.println(loan.getPrintedBook().getBook().getName());
                    }
                    break;
                }
                catch (IllegalStateException | HttpClientErrorException e) {
                    return getMember(BADADDR);
                }
                catch (NullPointerException e) {
                    return getMember(NOTEER);
                }
        }
        
        System.out.println("Press any key to continue!");
        s.nextLine();
        return getMember(0);
    }
    
    public static void printError(int error)
    {
        if(error > 0)
            System.out.println(errorCodes.get(error));
    }
    
    public static int help() {
        System.out.println("PA165 Library, Interactive REST Client, HELP");
        System.out.println("Run with one following argument as arg in cmd!");
        System.out.println("-b, book management");
        System.out.println("-m, member management");
        System.out.println("-menu, menu");
        System.out.println("-h, help");
        return EOK;
    }
}
