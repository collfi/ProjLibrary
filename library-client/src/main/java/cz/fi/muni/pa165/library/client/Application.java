package cz.fi.muni.pa165.library.client;

import cz.fi.muni.pa165.datatransferobject.BookDTO;
import cz.fi.muni.pa165.datatransferobject.MemberDTO;
import cz.fi.muni.pa165.entity.Book;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


/**
 * Rest interactive console client.
 *
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
    public static final int SERERR = 5;
    public static final String PA165URL = "http://localhost:8080/pa165";
    public static long idMember = 0;
    public static long idBook = 0;
    public static Map<Integer, String> errorCodes;

    public static void main(String args[]) {
        try {
            // initialize
            errorCodes = new HashMap<Integer, String>();
            errorCodes.put(EOK, "Bye!");
            errorCodes.put(UERROR, "Unexpected error!");
            errorCodes.put(NOTEER, "No entity in Database with specific query!");
            errorCodes.put(BADADDR, "Sorry bad api address to server!");
            errorCodes.put(ERRPARS, "Error with parsing data!");
            errorCodes.put(SERERR, "Server unavailable!");

            if (args.length < 2) {
                menu();
            } else {
                for (String arg : args) {
                    switch (arg) {
                        case "-b":
                            bookMenu();
                            break;
                        case "-m":
                            memberMenu();
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
            printError(UERROR);
        }
    }

    public static int menu() {
        System.out.println("REST INTERACTIVE CLIENT MENU");
        System.out.println("1. Book service");
        System.out.println("2. Member service");
        System.out.println("3. Exit");
        System.out.println("Press 1-2 and enter to continue and 3 to exit!");

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
        System.out.println("REST: BOOK MENU");
        //System.out.println("1. Get book");
        //System.out.println("2. Delete book");
        System.out.println("1. Find book");
        // System.out.println("4. Update book");
        System.out.println("2. Add book");
        System.out.println("3. Menu");

        System.out.println("Press 1-3 and enter to continue!:");

        Scanner s = new Scanner(System.in);
        String str = s.nextLine();

        switch (str) {
            //case "1":
            //    return getBook(EOK);
            //case "2":
            //    return deleteBook(EOK);
            case "1":
                return findBook(EOK);
            //case "4":
            //    return updateBook(EOK);
            case "2":
                return addBook(EOK);
            case "3":
                return menu();
            default:
                return bookMenu();
        }
    }

    private static int memberMenu() {
        System.out.println("REST: MEMBER MENU");
        //System.out.println("1. Get member");
        //System.out.println("2. Delete member");
        System.out.println("1. Find member");
        //System.out.println("4. Update member");
        System.out.println("2. Add member");
        System.out.println("3. Menu");

        System.out.println("Press 1-3 and enter to continue!:");

        Scanner s = new Scanner(System.in);
        String str = s.nextLine();

        switch (str) {
            //case "1":
            //    return getMember(EOK);
            //case "2":
            //    return deleteMember(EOK);
            case "1":
                return findMember(EOK);
            //case "4":
            //    return updateMember(EOK);
            case "2":
                return addMember(EOK);
            case "3":
                return menu();
            default:
                return memberMenu();
        }
    }

    private static int updateMember(int error) {
        System.out.println("REST: UPDATE MEMBER");
        printError(error);
        System.out.println("1. Write new \"name email (xx@yy.zz) address\" or 0 if you want to go "
                + "back to menu!:");

        Scanner s = new Scanner(System.in);
        String str = s.nextLine();

        switch (str) {
            case "0":
                return memberMenu();
            default:
                try {
                    RestTemplate restTemplate = new RestTemplate();
                    String[] data = str.trim().split(" ");
                    if (data.length != 3) {
                        throw new Exception();
                    }

                    MemberDTO member = new MemberDTO();
                    member.setIdMember(idMember);
                    member.setName(data[0]);
                    member.setEmail(data[1]);
                    member.setAddress(data[2]);

                    System.out.println("saving:" + member.toString());
                    String response = restTemplate.postForObject(PA165URL + RestURIConstants.UPD_MEMBER, member, String.class);
                    if (response == null) {
                        throw new NullPointerException();
                    } else {
                        System.out.println(str);
                    }

                    break;
                } catch (IllegalStateException | HttpClientErrorException e) {
                    return updateMember(BADADDR);
                } catch (NullPointerException e) {
                    return updateMember(NOTEER);
                } catch (ResourceAccessException ce) {
                    return updateMember(SERERR);
                } catch (Exception e) {
                    return updateMember(ERRPARS);
                }

        }
        idMember = 0;
        System.out.println("Press enter to continue!");
        s.nextLine();
        return findMember(EOK);
    }

    private static int addMember(int error) {
        System.out.println("REST: ADD MEMBER");
        printError(error);
        System.out.println("1. Write \"name email (xx@yy.zz) address\" or 0 if you want to go "
                + "back to menu!:");

        Scanner s = new Scanner(System.in);
        String str = s.nextLine();

        switch (str) {
            case "0":
                return memberMenu();
            default:
                try {
                    RestTemplate restTemplate = new RestTemplate();
                    String[] data = str.trim().split(" ");
                    if (data.length != 3) {
                        throw new Exception();
                    }

                    MemberDTO member = new MemberDTO();
                    member.setEmail(data[1]);
                    member.setAddress(data[2]);
                    member.setName(data[0]);

                    System.out.println("saving:" + member.toString());
                    String response = restTemplate.postForObject(PA165URL + RestURIConstants.ADD_MEMBER, member, String.class);
                    if (response == null) {
                        throw new NullPointerException();
                    } else {
                        System.out.println(str);
                    }
                } catch (IllegalStateException | HttpClientErrorException e) {
                    return addMember(BADADDR);
                } catch (NullPointerException e) {
                    return addMember(NOTEER);
                } catch (ResourceAccessException ce) {
                    return updateMember(SERERR);
                } catch (Exception e) {
                    return addMember(ERRPARS);
                }
        }

        System.out.println("Press enter to continue!");
        s.nextLine();
        return memberMenu();
    }

    private static int findMember(int error) {
        System.out.println("REST: FIND MEMBER");
        printError(error);

        System.out.println("1. Find members by name");
        System.out.println("2. Find members by address");
        System.out.println("3. Find member by email");
        System.out.println("4. back");

        System.out.println("Press 1-4 and enter to continue!:");

        Scanner s = new Scanner(System.in);
        String str = s.nextLine();


        switch (str) {
            case "1":
                return findMembersByName(EOK);
            case "2":
                return findMembersByAddress(EOK);
            case "3":
                return findMemberByEmail(EOK);
            case "4":
                return memberMenu();
            default:
                return findMember(EOK);
        }
    }

    private static int findMembersByName(int error) {
        System.out.println("REST: FIND MEMBER by name");
        printError(error);
        System.out.println("1. Write name, or 0 if you want to go back!:");
        MemberDTO[] page = null;
        Scanner s = new Scanner(System.in);
        String str = s.nextLine();

        switch (str) {
            case "0":
                return findMember(EOK);
            default:
                try {
                    RestTemplate restTemplate = new RestTemplate();
                    page = restTemplate.getForObject(PA165URL + RestURIConstants.GET_MEMBERS + "?name=" + str, MemberDTO[].class);
                    System.out.println("List of found members:");
                    for (int i = 0; i < page.length; i++) {
                        System.out.println((i + 1) + ": " + "Name: " + page[i].getName() + " Address: " + page[i].getAddress() + " Email: " + page[i].getEmail());
                    }
                } catch (IllegalStateException | HttpClientErrorException e) {
                    return findMembersByName(BADADDR);
                } catch (NullPointerException e) {
                    return findMembersByName(NOTEER);
                } catch (ResourceAccessException ce) {
                    return addBook(SERERR);
                }
        }
        //TODO ????
        if (page.length == 0) {
            return findMembersByName(NOTEER);
        }
        System.out.println("Press a number of member to select or 0 if you want to go back!:");
        str = s.nextLine();
        switch (str) {
            case "0":
                findMembersByName(EOK);
            default:
                if (isValid(str, page.length)) {
                    idMember = page[Integer.valueOf(str) - 1].getIdMember();
                    return memberSelected(EOK);
                } else {
                    return findMembersByName(BADADDR);
                }
        }
    }

    private static boolean isValid(String s, int i) {
        for (int j = 1; j < i + 1; j++) {
            if (s.equals(String.valueOf(j))) return true;
        }
        return false;
    }

    private static int memberSelected(int error) {
        System.out.println("WHAT DO YOU WANT TO DO?");
        printError(error);

        System.out.println("1. Delete member");
        System.out.println("2. update member");
        System.out.println("3. back");

        System.out.println("Press 1-3 and enter to continue!:");

        Scanner s = new Scanner(System.in);
        String str = s.nextLine();


        switch (str) {
            case "1":
                return deleteMember(EOK);
            case "2":
                return updateMember(EOK);
            case "3":
                return findMember(EOK);
            default:
                return memberSelected(EOK);
        }
    }

    //vypise chybu ked nenajde (preco ostatne nie?)
    private static int findMembersByAddress(int error) {
        System.out.println("REST: FIND MEMBER by address");
        printError(error);
        System.out.println("1. Write address, or 0 if you want to go back!:");
        MemberDTO[] page = null;
        Scanner s = new Scanner(System.in);
        String str = s.nextLine();

        switch (str) {
            case "0":
                return findMember(EOK);
            default:
                try {
                    RestTemplate restTemplate = new RestTemplate();
                    page = restTemplate.getForObject(PA165URL + RestURIConstants.GET_MEMBERS + "?address=" + str, MemberDTO[].class);
                    System.out.println("List of found members:");
                    for (int i = 0; i < page.length; i++) {
                        System.out.println((i + 1) + ": " + "Name: " + page[i].getName() + " Address: " + page[i].getAddress() + " Email: " + page[i].getEmail());
                    }
                } catch (IllegalStateException | HttpClientErrorException e) {
                    return findMembersByAddress(BADADDR);
                } catch (NullPointerException e) {
                    return findMembersByAddress(NOTEER);
                } catch (ResourceAccessException ce) {
                    return addBook(SERERR);
                }
        }
        //TODO ????
        if (page.length == 0) {
            return findMembersByAddress(NOTEER);
        }
        System.out.println("Press a number of member to select or 0 if you want to go back!:");
        str = s.nextLine();
        switch (str) {
            case "0":
                findMembersByAddress(EOK);
            default:
                if (isValid(str, page.length)) {
                    idMember = page[Integer.valueOf(str) - 1].getIdMember();
                    return memberSelected(EOK);
                } else {
                    return findMembersByAddress(BADADDR);
                }
        }
    }

    private static int findMemberByEmail(int error) {
        System.out.println("REST: FIND MEMBER by email");
        printError(error);
        System.out.println("1. Write email, or 0 if you want to go back!:");
        MemberDTO[] page = null;
        Scanner s = new Scanner(System.in);
        String str = s.nextLine();

        switch (str) {
            case "0":
                return findMember(EOK);
            default:
                try {
                    RestTemplate restTemplate = new RestTemplate();
                    page = restTemplate.getForObject(PA165URL + RestURIConstants.GET_MEMBERS + "?email=" + str, MemberDTO[].class);
                    System.out.println("List of found members:");
                    for (int i = 0; i < page.length; i++) {
                        System.out.println((i + 1) + ": " + "Name: " + page[i].getName() + " Address: " + page[i].getAddress() + " Email: " + page[i].getEmail());
                    }
                } catch (IllegalStateException | HttpClientErrorException e) {
                    return findMemberByEmail(BADADDR);
                } catch (NullPointerException e) {
                    return findMemberByEmail(NOTEER);
                } catch (ResourceAccessException ce) {
                    return addBook(SERERR);
                }
        }
        //TODO ????
        if (page.length == 0) {
            return findMemberByEmail(NOTEER);
        }
        System.out.println("Press a number of member to select or 0 if you want to go back!:");
        str = s.nextLine();
        switch (str) {
            case "0":
                findMemberByEmail(EOK);
            default:
                if (isValid(str, page.length)) {
                    idMember = page[Integer.valueOf(str) - 1].getIdMember();
                    return memberSelected(EOK);
                } else {
                    return findMemberByEmail(BADADDR);
                }
        }
    }

    private static int deleteMember(int error) {
        System.out.println("REST: DELETE MEMBER");
        printError(error);
        System.out.println("1. Write 1 if you are sure that you want to delete selected member, "
                + "or 0 if you want to go back to menu!:");

        Scanner s = new Scanner(System.in);
        String str = s.nextLine();
        switch (str) {
            case "0":
                return memberMenu();
            case "1":
                try {
                    RestTemplate restTemplate = new RestTemplate();
                    MemberDTO page = restTemplate.getForObject(PA165URL + RestURIConstants.DEL_MEMBER + idMember, MemberDTO.class);
                    if (page == null) {
                        throw new NullPointerException();
                    } else {
                        System.out.println("Deleted member with id=" + idMember);
                    }
                    break;
                } catch (IllegalStateException | HttpClientErrorException e) {
                    return deleteMember(BADADDR);
                } catch (NullPointerException e) {
                    return deleteMember(NOTEER);
                } catch (ResourceAccessException ce) {
                    return addBook(SERERR);
                }
            default:
                return deleteMember(BADADDR);
        }
        idMember = 0;
        System.out.println("Press enter to continue!");
        s.nextLine();
        return findMember(EOK);
    }

    public static void printError(int error) {
        if (error > 0)
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

    private static int bookSelected(int error) {
        System.out.println("WHAT DO YOU WANT TO DO?");
        printError(error);

        System.out.println("1. Delete book");
        System.out.println("2. update book");
        System.out.println("3. back");

        System.out.println("Press 1-3 and enter to continue!:");

        Scanner s = new Scanner(System.in);
        String str = s.nextLine();


        switch (str) {
            case "1":
                return deleteBook(EOK);
            case "2":
                return updateBook(EOK);
            case "3":
                return findBook(EOK);
            default:
                return bookSelected(EOK);
        }
    }

    private static int deleteBook(int error) {
        System.out.println("REST: DELETE BOOK");
        printError(error);
        System.out.println("1. Write 1 if you are sure that you want to delete selected book, "
                + "or 0 if you want to go back to menu!:");

        Scanner s = new Scanner(System.in);
        String str = s.nextLine();
        switch (str) {
            case "0":
                return bookMenu();
            case "1":
                try {
                    RestTemplate restTemplate = new RestTemplate();
                    BookDTO page = restTemplate.getForObject(PA165URL + RestURIConstants.DEL_BOOK + idBook, BookDTO.class);
                    if (page == null) {
                        throw new NullPointerException();
                    } else {
                        System.out.println("Deleted book with id=" + idBook);
                    }
                    break;
                } catch (IllegalStateException | HttpClientErrorException e) {
                    return deleteBook(BADADDR);
                } catch (NullPointerException e) {
                    return deleteBook(NOTEER);
                } catch (ResourceAccessException ce) {
                    return addBook(SERERR);
                }
            default:
                return deleteBook(BADADDR);
        }
        idBook = 0;
        System.out.println("Press enter to continue!");
        s.nextLine();
        return findBook(EOK);
    }

    private static int findBook(int error) {
        System.out.println("REST: FIND BOOK");
        printError(error);

        System.out.println("1. Find books by name");
        System.out.println("2. Find books by ISBN");
        System.out.println("3. Find books by author");
        System.out.println("4. Find books by department");
        System.out.println("5. back");

        System.out.println("Press 1-5 and enter to continue!:");

        Scanner s = new Scanner(System.in);
        String str = s.nextLine();

        switch (str) {
            case "1":
                return findBooksByName(EOK);
            case "2":
                return findBooksByISBN(EOK);
            case "3":
                return findBooksByAuthors(EOK);
            case "4":
                return findBooksByDepartment(EOK);
            case "5":
                return bookMenu();
            default:
                return findBook(EOK);
        }
    }

    private static int findBooksByName(int error) {
        System.out.println("REST: FIND BOOK by name");
        printError(error);
        System.out.println("1. Write name, or 0 if you want to go back!:");
        BookDTO[] page = null;
        Scanner s = new Scanner(System.in);
        String str = s.nextLine();

        switch (str) {
            case "0":
                return findBook(EOK);
            default:
                try {
                    RestTemplate restTemplate = new RestTemplate();
                    page = restTemplate.getForObject(PA165URL + RestURIConstants.GET_BOOKS + "?name=" + str, BookDTO[].class);
                    System.out.println("List of found books:");
                    for (int i = 0; i < page.length; i++) {
                        System.out.println((i + 1) + ": " + "Name: " + page[i].getName() + " ISBN: "
                                + page[i].getISBN() + " Authors: " + page[i].getAuthors() +
                                " Department: " + page[i].getDepartment() + " Description: "
                                + page[i].getDescription());
                    }
                } catch (IllegalStateException | HttpClientErrorException e) {
                    return findBooksByName(BADADDR);
                } catch (NullPointerException e) {
                    return findBooksByName(NOTEER);
                } catch (ResourceAccessException ce) {
                    return addBook(SERERR);
                }
        }
        //TODO ????
        if (page.length == 0) {
            return findBooksByName(NOTEER);
        }
        System.out.println("Press a number of book to select or 0 if you want to go back!:");
        str = s.nextLine();
        switch (str) {
            case "0":
                findBooksByName(EOK);
            default:
                if (isValid(str, page.length)) {
                    idBook = page[Integer.valueOf(str) - 1].getIdBook();
                    return bookSelected(EOK);
                } else {
                    return findBooksByName(BADADDR);
                }
        }
    }

    private static int findBooksByISBN(int error) {
        System.out.println("REST: FIND BOOK by ISBN");
        printError(error);
        System.out.println("1. Write ISBN, or 0 if you want to go back!:");
        BookDTO[] page = null;
        Scanner s = new Scanner(System.in);
        String str = s.nextLine();

        switch (str) {
            case "0":
                return findBook(EOK);
            default:
                try {
                    RestTemplate restTemplate = new RestTemplate();
                    page = restTemplate.getForObject(PA165URL + RestURIConstants.GET_BOOKS + "?isbn=" + str, BookDTO[].class);
                    System.out.println("List of found books:");
                    for (int i = 0; i < page.length; i++) {
                        System.out.println((i + 1) + ": " + "Name: " + page[i].getName() + " ISBN: "
                                + page[i].getISBN() + " Authors: " + page[i].getAuthors() +
                                " Department: " + page[i].getDepartment() + " Description: "
                                + page[i].getDescription());
                    }
                } catch (IllegalStateException | HttpClientErrorException e) {
                    return findBooksByISBN(BADADDR);
                } catch (NullPointerException e) {
                    return findBooksByISBN(NOTEER);
                } catch (ResourceAccessException ce) {
                    return addBook(SERERR);
                }
        }
        //TODO ????
        if (page.length == 0) {
            return findBooksByISBN(NOTEER);
        }
        System.out.println("Press a number of book to select or 0 if you want to go back!:");
        str = s.nextLine();
        switch (str) {
            case "0":
                findBooksByISBN(EOK);
            default:
                if (isValid(str, page.length)) {
                    idBook = page[Integer.valueOf(str) - 1].getIdBook();
                    return bookSelected(EOK);
                } else {
                    return findBooksByISBN(BADADDR);
                }
        }
    }

    private static int findBooksByAuthors(int error) {
        System.out.println("REST: FIND BOOK by author");
        printError(error);
        System.out.println("1. Write author, or 0 if you want to go back!:");
        BookDTO[] page = null;
        Scanner s = new Scanner(System.in);
        String str = s.nextLine();

        switch (str) {
            case "0":
                return findBook(EOK);
            default:
                try {
                    RestTemplate restTemplate = new RestTemplate();
                    page = restTemplate.getForObject(PA165URL + RestURIConstants.GET_BOOKS + "?authors=" + str, BookDTO[].class);
                    System.out.println("List of found books:");
                    for (int i = 0; i < page.length; i++) {
                        System.out.println((i + 1) + ": " + "Name: " + page[i].getName() + " ISBN: "
                                + page[i].getISBN() + " Authors: " + page[i].getAuthors() +
                                " Department: " + page[i].getDepartment() + " Description: "
                                + page[i].getDescription());
                    }
                } catch (IllegalStateException | HttpClientErrorException e) {
                    return findBooksByAuthors(BADADDR);
                } catch (NullPointerException e) {
                    return findBooksByAuthors(NOTEER);
                } catch (ResourceAccessException ce) {
                    return addBook(SERERR);
                }
        }
        //TODO ????
        if (page.length == 0) {
            return findBooksByAuthors(NOTEER);
        }
        System.out.println("Press a number of book to select or 0 if you want to go back!:");
        str = s.nextLine();
        switch (str) {
            case "0":
                findBooksByAuthors(EOK);
            default:
                if (isValid(str, page.length)) {
                    idBook = page[Integer.valueOf(str) - 1].getIdBook();
                    return bookSelected(EOK);
                } else {
                    return findBooksByAuthors(BADADDR);
                }
        }
    }

    private static int findBooksByDepartment(int error) {
        System.out.println("REST: FIND BOOK by department");
        printError(error);
        System.out.println("1. Write department (Science, Sport, Religion, Autobiografy), "
                + "or 0 if you want to go back!:");
        BookDTO[] page = null;
        Scanner s = new Scanner(System.in);
        String str = s.nextLine();

        switch (str) {
            case "0":
                return findBook(EOK);
            default:
                try {
                    RestTemplate restTemplate = new RestTemplate();
                    page = restTemplate.getForObject(PA165URL + RestURIConstants.GET_BOOKS + "?department=" + str, BookDTO[].class);
                    System.out.println("List of found books:");
                    for (int i = 0; i < page.length; i++) {
                        System.out.println((i + 1) + ": " + "Name: " + page[i].getName() + " ISBN: "
                                + page[i].getISBN() + " Authors: " + page[i].getAuthors() +
                                " Department: " + page[i].getDepartment() + " Description: "
                                + page[i].getDescription());
                    }
                } catch (IllegalStateException | HttpClientErrorException e) {
                    return findBooksByDepartment(BADADDR);
                } catch (NullPointerException e) {
                    return findBooksByDepartment(NOTEER);
                } catch (ResourceAccessException ce) {
                    return addBook(SERERR);
                }
        }
        //TODO ????
        if (page.length == 0) {
            return findBooksByDepartment(NOTEER);
        }
        System.out.println("Press a number of book to select or 0 if you want to go back!:");
        str = s.nextLine();
        switch (str) {
            case "0":
                findBooksByDepartment(EOK);
            default:
                if (isValid(str, page.length)) {
                    idBook = page[Integer.valueOf(str) - 1].getIdBook();
                    return bookSelected(EOK);
                } else {
                    return findBooksByDepartment(BADADDR);
                }
        }
    }

    private static int updateBook(int error) {
        System.out.println("REST: UPDATE BOOK");
        printError(error);
        System.out.println("1. Write new \"name isbn authors department (Science, Sport, Religion, "
                + "Autobiografy) description\" or 0 if you want to go back to menu!:");

        Scanner s = new Scanner(System.in);
        String str = s.nextLine();

        switch (str) {
            case "0":
                return bookMenu();
            default:
                try {
                    RestTemplate restTemplate = new RestTemplate();
                    String[] data = str.trim().split(" ");
                    if (data.length != 5) {
                        throw new Exception();
                    }
                    if (!validDepartment(data[3])) {
                        return addBook(ERRPARS);
                    }

                    BookDTO book = new BookDTO();
                    book.setIdBook(idBook);
                    book.setName(data[0]);
                    book.setISBN(data[1]);
                    book.setAuthors(data[2]);
                    book.setDepartment(Book.Department.valueOf(data[3]));
                    book.setDescription(data[4]);

                    System.out.println("saving:" + book.toString());
                    String response = restTemplate.postForObject(PA165URL + RestURIConstants.UPD_BOOK, book, String.class);
                    if (response == null) {
                        throw new NullPointerException();
                    } else {
                        System.out.println(str);
                    }

                    break;
                } catch (IllegalStateException | HttpClientErrorException e) {
                    return updateBook(BADADDR);
                } catch (NullPointerException e) {
                    return updateBook(NOTEER);
                } catch (ResourceAccessException ce) {
                    return updateMember(SERERR);
                } catch (Exception e) {
                    return updateBook(ERRPARS);
                }

        }
        idBook = 0;
        System.out.println("Press enter to continue!");
        s.nextLine();
        return findBook(EOK);
    }

    private static int addBook(int error) {
        System.out.println("REST: ADD BOOK");
        printError(error);
        System.out.println("1. Write \"name isbn authors department (Science, Sport, Religion, Autobiografy)"
                + " description\" or 0 if you want to go back to menu!:");

        Scanner s = new Scanner(System.in);
        String str = s.nextLine();

        switch (str) {
            case "0":
                return bookMenu();
            default:
                try {
                    RestTemplate restTemplate = new RestTemplate();
                    String[] data = str.trim().split(" ");
                    if (data.length != 5) {
                        throw new Exception();
                    }
                    if (!validDepartment(data[3])) {
                        return addBook(ERRPARS);
                    }

                    System.out.println("SDADASDSADS");
                    BookDTO book = new BookDTO();
                    book.setName(data[0]);
                    book.setISBN(data[1]);
                    book.setAuthors(data[2]);
                    book.setDepartment(Book.Department.valueOf(data[3]));
                    book.setDescription(data[4]);

                    System.out.println("saving:" + book.toString());
                    String response = restTemplate.postForObject(PA165URL + RestURIConstants.ADD_BOOK, book, String.class);
                    if (response == null) {
                        throw new NullPointerException();
                    } else {
                        System.out.println(str);
                    }
                } catch (IllegalStateException | HttpClientErrorException e) {
                    return addBook(BADADDR);
                } catch (NullPointerException e) {
                    return addBook(NOTEER);
                } catch (ResourceAccessException ce) {
                    return addBook(SERERR);
                } catch (Exception e) {
                    return addBook(ERRPARS);
                }

        }

        System.out.println("Press enter to continue!");
        s.nextLine();
        return bookMenu();
    }

    private static boolean validDepartment(String d) {
        try {
            Book.Department dep = Book.Department.valueOf(d);
        } catch (IllegalArgumentException iae) {
            return false;
        }
        return true;
    }

    public static class RestURIConstants {
        public static final String ADD_BOOK = "/api/book/add/";
        public static final String DEL_BOOK = "/api/book/delete/";
        //public static final String GET_BOOK = "/api/book/get/";
        public static final String GET_BOOKS = "/api/book/find/";
        public static final String UPD_BOOK = "/api/book/update/";

        public static final String ADD_MEMBER = "/api/member/add/";
        public static final String DEL_MEMBER = "/api/member/delete/";
        //public static final String GET_MEMBER = "/api/member/get/";
        public static final String GET_MEMBERS = "/api/member/find/";
        public static final String UPD_MEMBER = "/api/member/update/";

        public static final String ADD_PBOOK = "/api/pbook/add/";
    }
}
