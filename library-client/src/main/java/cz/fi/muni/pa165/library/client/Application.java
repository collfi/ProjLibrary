package cz.fi.muni.pa165.library.client;
import cz.fi.muni.pa165.datatransferobject.BookDTO;
import cz.fi.muni.pa165.datatransferobject.LoanDTO;
import cz.fi.muni.pa165.datatransferobject.MemberDTO;
import cz.fi.muni.pa165.datatransferobject.PrintedBookDTO;
import cz.fi.muni.pa165.entity.Book;
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
    public static final String PA165URL = "http://localhost:8080/pa165";

    public static class RestURIConstants {
        public static final String ADD_BOOK = "/api/book/add/";
        public static final String DEL_BOOK = "/api/book/delete/";
        public static final String GET_BOOK = "/api/book/get/";
        public static final String GET_BOOKS = "/api/book/find/";
        public static final String UPD_BOOK = "/api/book/update/";
        
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
        System.out.println("REST: INTERACTIVE CLIENT MENU");
        System.out.println("1. Get book");
        System.out.println("2. Delete book");
        System.out.println("3. Find book");
        System.out.println("4. Update book");
        System.out.println("5. Add book");
        System.out.println("6. Menu");

        System.out.println("Press 1-6 and enter to continue!:");
        
        Scanner s = new Scanner(System.in);
        String str = s.nextLine();
        
        switch (str) {
            case "1":
                return getBook(EOK);
            case "2":
                return deleteBook(EOK);
            case "3":
                return findBook(EOK);
            case "4":
                return updateBook(EOK);
            case "5":
                return addBook(EOK);
            case "6":
                return menu();
            default:
                return memberMenu();
        }   
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
        System.out.println("3. Find member by email");
        System.out.println("4. back");

        System.out.println("Press 1-4 and enter to continue!:");
        
        Scanner s = new Scanner(System.in);
        String str = s.nextLine();
        

        switch (str) {
            case "1": return findMembersByName(EOK);
            case "2": return findMembersByAddress(EOK);
            case "3": return findMemberByEmail(EOK);
            case "4": return memberMenu();
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
    //vypise chybu ked nenajde (preco ostatne nie?)
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
    
    private static int findMemberByEmail(int error) {
        System.out.println("REST: FIND MEMBER by email");
        printError(error);
        System.out.println("1. Write email, or 0 if you want to go back!:");
        
        Scanner s = new Scanner(System.in);
        String str = s.nextLine();

        switch (str) {
            case "0": return findMember(EOK);
            default:
                try {
                    RestTemplate restTemplate = new RestTemplate();
                    MemberDTO[] page = restTemplate.getForObject(PA165URL+RestURIConstants.GET_MEMBERS+"?email="+str, MemberDTO[].class);
                    for(MemberDTO member : page) {
                        System.out.println("Name: " + member.getName() + " Address: " + member.getAddress() + " Email: " + member.getEmail());
                    }
                }
                catch (IllegalStateException | HttpClientErrorException e) {
                    return findMemberByEmail(BADADDR);
                }
                catch (NullPointerException e) {
                    return findMemberByEmail(NOTEER);
                }
        }
        
        System.out.println("Press any key to continue!");
        s.nextLine();
        return findMemberByEmail(EOK);
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
    
    private static int getBook(int error) {
        System.out.println("REST: GET BOOK by ID");
        printError(error);
        System.out.println("1. Write Id > 0, or 0 if you want to go back to menu!:");
        
        Scanner s = new Scanner(System.in);
        String str = s.nextLine();

        switch (str) {
            case "0": return bookMenu();
            default:
                try {
                    RestTemplate restTemplate = new RestTemplate();
                    BookDTO page = restTemplate.getForObject(PA165URL+RestURIConstants.GET_BOOK+str, BookDTO.class);
                    System.out.println("Name:    " + page.getName());
                    System.out.println("Authors:   " + page.getAuthors());
                    System.out.println("ISBN:   " + page.getISBN());
                    System.out.println("Genre:   " + page.getDepartment());
                    System.out.println("Description:   " + page.getDescription());
                    System.out.println("Printed Books:");
                    for(PrintedBookDTO pbook : page.getBooks()) {
                        System.out.println(pbook.getIdPrintedBook() + ": " + pbook.getCondition());
                    }
                    break;
                }
                catch (IllegalStateException | HttpClientErrorException e) {
                    return getBook(BADADDR);
                }
                catch (NullPointerException e) {
                    return getBook(NOTEER);
                }
        }
        
        System.out.println("Press any key to continue!");
        s.nextLine();
        return getBook(0);
    }

    private static int deleteBook(int error) {
        System.out.println("REST: DELETE BOOK by ID");
        printError(error);
        System.out.println("1. Write Id > 0, or 0 if you want to go back to menu!:");
        
        Scanner s = new Scanner(System.in);
        String str = s.nextLine();

        switch (str) {
            case "0": return bookMenu();
            default:
                try {
                    RestTemplate restTemplate = new RestTemplate();
                    BookDTO page = restTemplate.getForObject(PA165URL+RestURIConstants.DEL_BOOK+str, BookDTO.class);
                    if(page == null) {
                        throw new NullPointerException();
                    }
                    else {
                        System.out.println("Deleted book with id="+str);
                    }
                    break;
                }
                catch (IllegalStateException | HttpClientErrorException e) {
                    return deleteBook(BADADDR);
                }
                catch (NullPointerException e) {
                    return deleteBook(NOTEER);
                }
        }
        
        System.out.println("Press any key to continue!");
        s.nextLine();
        return deleteBook(EOK);
    }

    private static int findBook(int error) {
        System.out.println("REST: FIND BOOK");
        printError(error);
       
        System.out.println("REST: INTERACTIVE CLIENT MENU");
        System.out.println("1. Find books by name");
        System.out.println("2. Find books by ISBN");
        System.out.println("3. Find books by author");
        System.out.println("4. Find books by department");
        System.out.println("5. back");

        System.out.println("Press 1-5 and enter to continue!:");
        
        Scanner s = new Scanner(System.in);
        String str = s.nextLine();
        

        switch (str) {
            case "1": return findBooksByName(EOK);
            case "2": return findBooksByISBN(EOK);
            case "3": return findBooksByAuthors(EOK);
            case "4": return findBooksByDepartment(EOK);
            case "5": return bookMenu();
            default:
                return findBook(EOK);            
        }
    }
    
    private static int findBooksByName(int error) {
        System.out.println("REST: FIND BOOK by name");
        printError(error);
        System.out.println("1. Write name, or 0 if you want to go back!:");
        
        Scanner s = new Scanner(System.in);
        String str = s.nextLine();

        switch (str) {
            case "0": return findBook(EOK);
            default:
                try {
                    RestTemplate restTemplate = new RestTemplate();
                    BookDTO[] page = restTemplate.getForObject(PA165URL+RestURIConstants.GET_BOOKS+"?name="+str, BookDTO[].class);
                    for(BookDTO book : page) {
                        System.out.println("Name: " + book.getName() + " ISBN: " + book.getISBN() + 
                                " Authors: " + book.getAuthors() + " Department: " + book.getDepartment() + 
                                " Description: " + book.getDescription());
                    }
                }
                catch (IllegalStateException | HttpClientErrorException e) {
                    return findBooksByName(BADADDR);
                }
                catch (NullPointerException e) {
                    return findBooksByName(NOTEER);
                }
        }
        
        System.out.println("Press any key to continue!");
        s.nextLine();
        return findBooksByName(EOK);
    }

    private static int findBooksByISBN(int error) {
        System.out.println("REST: FIND BOOK by ISBN");
        printError(error);
        System.out.println("1. Write ISBN, or 0 if you want to go back!:");
        
        Scanner s = new Scanner(System.in);
        String str = s.nextLine();

        switch (str) {
            case "0": return findBook(EOK);
            default:
                try {
                    RestTemplate restTemplate = new RestTemplate();
                    BookDTO[] page = restTemplate.getForObject(PA165URL+RestURIConstants.GET_BOOKS+"?isbn="+str, BookDTO[].class);
                    for(BookDTO book : page) {
                        System.out.println("Name: " + book.getName() + " ISBN: " + book.getISBN() + 
                                " Authors: " + book.getAuthors() + " Department: " + book.getDepartment() + 
                                " Description: " + book.getDescription());
                    }
                }
                catch (IllegalStateException | HttpClientErrorException e) {
                    return findBooksByISBN(BADADDR);
                }
                catch (NullPointerException e) {
                    return findBooksByISBN(NOTEER);
                }
        }
        
        System.out.println("Press any key to continue!");
        s.nextLine();
        return findBooksByISBN(EOK);
    }

    private static int findBooksByAuthors(int error) {
        System.out.println("REST: FIND BOOK by author");
        printError(error);
        System.out.println("1. Write author, or 0 if you want to go back!:");
        
        Scanner s = new Scanner(System.in);
        String str = s.nextLine();

        switch (str) {
            case "0": return findBook(EOK);
            default:
                try {
                    RestTemplate restTemplate = new RestTemplate();
                    BookDTO[] page = restTemplate.getForObject(PA165URL+RestURIConstants.GET_BOOKS+"?authors="+str, BookDTO[].class);
                    for(BookDTO book : page) {
                        System.out.println("Name: " + book.getName() + " ISBN: " + book.getISBN() + 
                                " Authors: " + book.getAuthors() + " Department: " + book.getDepartment() + 
                                " Description: " + book.getDescription());
                    }
                }
                catch (IllegalStateException | HttpClientErrorException e) {
                    return findBooksByAuthors(BADADDR);
                }
                catch (NullPointerException e) {
                    return findBooksByAuthors(NOTEER);
                }
        }
        
        System.out.println("Press any key to continue!");
        s.nextLine();
        return findBooksByAuthors(EOK);
    }

    private static int findBooksByDepartment(int error) {
        System.out.println("REST: FIND BOOK by department");
        printError(error);
        System.out.println("1. Write department (Science, Sport, Religion, Autobiografy), "
                + "or 0 if you want to go back!:");
        
        Scanner s = new Scanner(System.in);
        String str = s.nextLine();

        switch (str) {
            case "0": return findBook(EOK);
            default:
                try {
                    RestTemplate restTemplate = new RestTemplate();
                    BookDTO[] page = restTemplate.getForObject(PA165URL+RestURIConstants.GET_BOOKS+"?department="+str, BookDTO[].class);
                    for(BookDTO book : page) {
                        System.out.println("Name: " + book.getName() + " ISBN: " + book.getISBN() + 
                                " Authors: " + book.getAuthors() + "Department: " + book.getDepartment() + 
                                "Description: " + book.getDescription());
                    }
                }
                catch (IllegalStateException | HttpClientErrorException e) {
                    return findBooksByDepartment(BADADDR);
                }
                catch (NullPointerException e) {
                    return findBooksByDepartment(NOTEER);
                }
        }
        
        System.out.println("Press any key to continue!");
        s.nextLine();
        return findBooksByDepartment(EOK);
    }

    private static int updateBook(int error) {
        System.out.println("REST: UPDATE BOOK");
        printError(error);
        System.out.println("1. Write \"id name isbn authors department(Science, Sport, Religion, Autobiografy)"
                + " description\" or 0 if you want to go back to menu!:");
        
        Scanner s = new Scanner(System.in);
        String str = s.nextLine();

        switch (str) {
            case "0": return bookMenu();
            default:
                try {
                    RestTemplate restTemplate = new RestTemplate();
                    String[] data = str.trim().split(" ");
                    if(data.length != 6) {
                        throw new Exception();
                    }
                    
                    BookDTO book = new BookDTO();
                    book.setIdBook(Integer.parseInt(data[0]));
                    book.setName(data[1]);
                    book.setISBN(data[2]);
                    book.setAuthors(data[3]);
                    book.setDepartment(Book.Department.valueOf(data[4]));
                    book.setDescription(data[5]);
                    
                    System.out.println("saving:" + book.toString());
                    String response = restTemplate.postForObject(PA165URL+RestURIConstants.UPD_BOOK, book,  String.class);
                    if(response == null) {
                        throw new NullPointerException();
                    }
                    else {
                        System.out.println(str);
                    }

                    break;
                }
                catch (IllegalStateException | HttpClientErrorException e) {
                    return updateBook(BADADDR);
                }
                catch (NullPointerException e) {
                    return updateBook(NOTEER);
                }
                catch (Exception e) {
                    return updateBook(ERRPARS);
                }

        }
        
        System.out.println("Press any key to continue!");
        s.nextLine();
        return updateBook(EOK);
    }

    private static int addBook(int error) {
        System.out.println("REST: ADD BOOK");
        printError(error);
        System.out.println("1. Write \"name isbn authors department(Science, Sport, Religion, Autobiografy)"
                + " description \" or 0 if you want to go back to menu!:");
        
        Scanner s = new Scanner(System.in);
        String str = s.nextLine();

        switch (str) {
            case "0": return bookMenu();
            default:
                try {
                    RestTemplate restTemplate = new RestTemplate();
                    String[] data = str.trim().split(" ");
                    if(data.length != 5) {
                        throw new Exception();
                    }
                    BookDTO book = new BookDTO();
                    book.setName(data[0]);
                    book.setISBN(data[1]);
                    book.setAuthors(data[2]);
                    book.setDepartment(Book.Department.valueOf(data[3]));
                    book.setDescription(data[4]);
                    
                    System.out.println("saving:" + book.toString());
                    String response = restTemplate.postForObject(PA165URL+RestURIConstants.ADD_BOOK, book,  String.class);
                    if(response == null) {
                        throw new NullPointerException();
                    }
                    else {
                        System.out.println(str);
                    }
                }
                catch (IllegalStateException | HttpClientErrorException e) {
                    return addBook(BADADDR);
                }
                catch (NullPointerException e) {
                    return addBook(NOTEER);
                }
                catch (Exception e) {
                    return addBook(ERRPARS);
                }

        }
        
        System.out.println("Press any key to continue!");
        s.nextLine();
        return addBook(EOK);
    }
}
