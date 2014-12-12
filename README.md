ProjLibrary
===========

PA165 Information system for public library in java.

Create an information system for a public library. The system should keep track of book collections in library departments, library members as well as individual loaned items of every member. The system should be capable of providing information about all the members and books, what a member borrowed and when, who borrowed a certain book and what condition they returned the book in. Take into account that a person can borrow multiple books in a single loan.

Build:    mvn clean install
run web:  mvn tomcat7:run, in library-web
run rest: mvn exec:java, in library-client

