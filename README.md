ProjLibrary
===========

PA165 Information system for public library in java.

run web application: change directory to library-web and run: mvn tomcat7:run

To run rest client: change directory to library-client and run: mvn exec:java

Examle of client usage:
REST INTERACTIVE CLIENT MENU
1. Book service
2. Member service
3. Exit
Press 1-2 and enter to continue and 3 to exit!
1
REST: BOOK MENU
1. Find book
2. Add book
3. Menu
Press 1-3 and enter to continue!:
2
REST: ADD BOOK
1. Write "name; isbn; authors; department(Science, Sport, Religion, Autobiografy); description" or 0 if you want to go back to menu!:
name;978-3-16-148410-0;author;Sport;desc
log4j: reset attribute= "false".
log4j: Threshold ="null".
log4j: Level value for root is  [ERROR].
log4j: root level set to ERROR
log4j: Class name: [org.apache.log4j.ConsoleAppender]
log4j: Parsing layout of class: "org.apache.log4j.PatternLayout"
log4j: Setting property [conversionPattern] to [%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n].
log4j: Adding appender named [console] to category [root].
saving:cz.fi.muni.pa165.library.api.dto.BookDTO@b1
name;978-3-16-148410-0;author;Sport;desc
Press enter to continue!

REST: BOOK MENU
1. Find book
2. Add book
3. Menu
Press 1-3 and enter to continue!:
1
REST: FIND BOOK
1. Find books by name
2. Find books by ISBN
3. Find books by author
4. Find books by department
5. back
Press 1-5 and enter to continue!:
1
REST: FIND BOOK by name
1. Write name, or 0 if you want to go back!:
name
List of found books:
1: Name: name ISBN: 978-3-16-148410-0 Authors: author Department: Sport Description: desc
Press a number of book to select or 0 if you want to go back!:

