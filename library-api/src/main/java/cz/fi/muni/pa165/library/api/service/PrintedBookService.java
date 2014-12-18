/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.library.api.service;

import cz.fi.muni.pa165.library.api.dto.BookDTO;
import cz.fi.muni.pa165.library.api.dto.LoanDTO;
import cz.fi.muni.pa165.library.api.dto.PrintedBookDTO;

import java.util.List;


/**
 * Service class of printed books
 *
 * @author Boris Valentovic - xvalent2
 */
public interface PrintedBookService {

    /**
     * Inserts printed book
     *
     * @param pbookto Printed book to insert
     */
    public void insertPrintedBook(PrintedBookDTO pbookto);

    /**
     * Update given printed book dto
     *
     * @param pbookto Printed book to update
     */
    public void updatePrintedBook(PrintedBookDTO pbookto);

    /**
     * Delete given printed book
     *
     * @param pbookto Printed Book to delete
     */
    public void deletePrintedBook(PrintedBookDTO pbookto);

    /**
     * Find printed book
     *
     * @param pbookto Printed book which should be found
     * @return Printed book
     */
    public PrintedBookDTO findPrintedBook(PrintedBookDTO pbookto);

    /**
     * Find printed books by book
     *
     * @param bookto Book, of which printed books we look for
     * @return List of all printed books that belong to book
     */
    public List<PrintedBookDTO> findPrintedBooksByBook(BookDTO bookto);

    /**
     * Find printed books by book and state
     *
     * @param bookto Book, of which printed books we look for
     * @param state  True for loaned books, false otherwise
     * @return List of printed books that belong to book and have indicated state
     */
    public List<PrintedBookDTO> findPrintedBookByState(BookDTO bookto, Boolean state);

    /**
     * Find printed book by ID
     *
     * @param id id of printed book we look for
     * @return Printed book with indicated id
     */
    public PrintedBookDTO findPrintedBookById(Long id);

    /**
     * Find printed books by loan
     *
     * @param loanto Loan, of which printed books we look for
     * @return List of printed books that belong to loan
     */
    public List<PrintedBookDTO> findAllPrintedBooksByLoan(LoanDTO loanto);

    /**
     * Find all printed books that are borrowed
     *
     * @return List of all borrowed printed books
     */
    public List<PrintedBookDTO> findAllBorrowedPrintedBooks();

    /**
     * Find printed books by loan and book
     *
     * @param bookto Book, of which printed books we look for
     * @param loanto Loan, of which printed books we look for
     * @return List of all printed books that belong to indicated book and loan
     */
    public List<PrintedBookDTO> findPrintedBooksByLoan(BookDTO bookto, LoanDTO loanto);


}
