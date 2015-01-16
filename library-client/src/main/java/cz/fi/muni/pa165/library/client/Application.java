package cz.fi.muni.pa165.library.client;

import cz.fi.muni.pa165.library.api.constants.Department;
import cz.fi.muni.pa165.library.api.dto.BookDTO;
import cz.fi.muni.pa165.library.api.dto.MemberDTO;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Rest interactive console client.
 *
 * @author michal.lukac
 */
public class Application {

    //patterns from http://regexlib.com/
    public static final String EMAIL_PATTERN = "\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}";
    //match ISBN-10 and 13
    public static final String ISBN_PATTERN = "((978[\\--– ])?[0-9][0-9\\--– ]{10}[\\--– ][0-9xX])|((978)?[0-9]{9}[0-9Xx])";
    public static final String DEPARTMENT_PATTERN = "(Science|Sport|Religion|Autobiografy)";

    //error codes
    public static final int EOK = 0;
    public static final int UERROR = 1;
    public static final int NOTEER = 2;
    public static final int BADADDR = 3;
    public static final int ERRPARS = 4;
    public static final int SERERR = 5;
    public static String PA165URL = "http://localhost:8080/pa165";
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
            switch (args.length) {
                case 0:
                    menu();
                    break;
                case 1:
                    if (args[0].equals("-h")) {
                        help();
                        break;
                    }
                    else {
                        PA165URL = "http://" + args[0] + "/pa165";
                        menu();
                        break;
                    }
                default:
                    help();
                    break;
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
        System.out.println("1. Find book");
        System.out.println("2. Add book");
        System.out.println("3. Menu");

        System.out.println("Press 1-3 and enter to continue!:");

        Scanner s = new Scanner(System.in);
        String str = s.nextLine();

        switch (str) {
            case "1":
                return findBook(EOK);
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
        System.out.println("1. Find member");
        System.out.println("2. Add member");
        System.out.println("3. Menu");

        System.out.println("Press 1-3 and enter to continue!:");

        Scanner s = new Scanner(System.in);
        String str = s.nextLine();

        switch (str) {
            case "1":
                return findMember(EOK);
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
        System.out.println("1. Write new \"name; email; address\" or 0 if you want to go "
                + "back to menu!:");

        Scanner s = new Scanner(System.in);
        String str = s.nextLine();

        switch (str) {
            case "0":
                return memberMenu();
            default:
                try {
                    RestTemplate restTemplate = new RestTemplate();
                    String[] data = str.split(";");

                    if (data.length != 3) throw new Exception();

                    String name = data[0].trim();
                    String email = data[1].trim();
                    String address = data[2].trim();

//                    only email have strict format
                    if (!checkInput(email, EMAIL_PATTERN)) {
                        System.out.println("bad format of mail");
                        throw new Exception();
                    }

                    MemberDTO member = new MemberDTO();
                    member.setIdMember(idMember);
                    member.setName(name);
                    member.setEmail(email);
                    member.setAddress(address);

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
                    System.out.print(ce);
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
        System.out.println("1. Write \"name; email; address\" or 0 if you want to go "
                + "back to menu!:");

        Scanner s = new Scanner(System.in);
        String str = s.nextLine();

        switch (str) {
            case "0":
                return memberMenu();
            default:
                try {
                    RestTemplate restTemplate = new RestTemplate();
                    String[] data = str.split(";");

                    if (data.length != 3) throw new Exception();

                    String name = data[0].trim();
                    String email = data[1].trim();
                    String address = data[2].trim();

                    //only email have strict format
                    if (!checkInput(email, EMAIL_PATTERN)) {
                        System.out.println("bad format of mail");
                        throw new Exception();
                    }

                    MemberDTO member = new MemberDTO();
                    member.setName(name);
                    member.setEmail(email);
                    member.setAddress(address);

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
        MemberDTO[] page;
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

    private static int findMembersByAddress(int error) {
        System.out.println("REST: FIND MEMBER by address");
        printError(error);
        System.out.println("1. Write address, or 0 if you want to go back!:");
        MemberDTO[] page;
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
        MemberDTO[] page;
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
        System.out.println("Run without argument to use default configuration");
        System.out.println("Run with one argument to configure server in format \"address:port\"");
        System.out.println("Run with -h argument to show help");
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
        BookDTO[] page;
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
        BookDTO[] page;
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
        BookDTO[] page;
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
        BookDTO[] page;
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
        System.out.println("1. Write new \"name; isbn; authors; department(Science, Sport, Religion, "
                + "Autobiografy); description\" or 0 if you want to go back to menu!:");

        Scanner s = new Scanner(System.in);
        String str = s.nextLine();

        switch (str) {
            case "0":
                return bookMenu();
            default:
                try {
                    RestTemplate restTemplate = new RestTemplate();
                    String[] data = str.split(";");
                    if (data.length != 5) {
                        throw new Exception();
                    }

                    String name = data[0].trim();
                    String ISBN = data[1].trim();
                    String authors = data[2].trim();
                    String department = data[3].trim();
                    String description = data[4].trim();

                    //only ISBN and department have strict format  
                    if (!checkInput(ISBN, ISBN_PATTERN)) {
                        System.out.println("bad format of ISBN");
                        throw new Exception();
                    }

                    if (!checkInput(department, DEPARTMENT_PATTERN)) {
                        System.out.println("not allowed value of department");
                        throw new Exception();
                    }

                    BookDTO book = new BookDTO();
                    book.setIdBook(idBook);

                    book.setName(name);
                    book.setISBN(ISBN);
                    book.setAuthors(authors);
                    book.setDepartment(Department.valueOf(department));
                    book.setDescription(description);

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
        System.out.println("1. Write \"name; isbn; authors; department(Science, Sport, Religion, Autobiografy);"
                + " description\" or 0 if you want to go back to menu!:");

        Scanner s = new Scanner(System.in);
        String str = s.nextLine();

        switch (str) {
            case "0":
                return bookMenu();
            default:
                try {
                    RestTemplate restTemplate = new RestTemplate();
                    String[] data = str.split(";");
                    if (data.length != 5) {
                        throw new Exception();
                    }

                    String name = data[0].trim();
                    String ISBN = data[1].trim();
                    String authors = data[2].trim();
                    String department = data[3].trim();
                    String description = data[4].trim();

                    //only ISBN and department have strict format  
                    if (!checkInput(ISBN, ISBN_PATTERN)) {
                        System.out.println("bad format of ISBN");
                        throw new Exception();
                    }

                    if (!checkInput(department, DEPARTMENT_PATTERN)) {
                        System.out.println("not allowed value of department");
                        throw new Exception();
                    }

                    BookDTO book = new BookDTO();
                    book.setName(name);
                    book.setISBN(ISBN);
                    book.setAuthors(authors);
                    book.setDepartment(Department.valueOf(department));
                    book.setDescription(description);

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

    private static boolean checkInput(String userInput, String allowedInput) {
        Matcher matcher;
        Pattern pattern;

        pattern = Pattern.compile(allowedInput);
        matcher = pattern.matcher(userInput);
        return matcher.matches();
    }

    public static class RestURIConstants {
        public static final String ADD_BOOK = "/api/book/add/";
        public static final String DEL_BOOK = "/api/book/delete/";
        public static final String GET_BOOKS = "/api/book/find/";
        public static final String UPD_BOOK = "/api/book/update/";

        public static final String ADD_MEMBER = "/api/member/add/";
        public static final String DEL_MEMBER = "/api/member/delete/";
        public static final String GET_MEMBERS = "/api/member/find/";
        public static final String UPD_MEMBER = "/api/member/update/";
    }
}