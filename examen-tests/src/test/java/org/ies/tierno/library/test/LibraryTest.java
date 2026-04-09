package org.ies.tierno.library.test;

import org.ies.tierno.library.exceptions.BookNotFoundException;
import org.ies.tierno.library.exceptions.CustomerNotFoundException;
import org.ies.tierno.library.model.Book;
import org.ies.tierno.library.model.BookLend;
import org.ies.tierno.library.model.Customer;
import org.ies.tierno.library.model.Library;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@ExtendWith(MockitoExtension.class)
public class LibraryTest {
    private Library library;
    private long isbnExistente = 124568903333L;
    private String nifExistente = "Paco-XML";
    @BeforeEach
    void SetUp() {
        Map<Long, Book> books = new HashMap<>();
        Map<String, Customer> customers = new HashMap<>();
        List<BookLend> lends = new ArrayList<>();
        books.put(isbnExistente, new Book(isbnExistente, "ElQuijote", "Cervantes", List.of("novela")));
        customers.put(nifExistente, new Customer(nifExistente, "Paco", "Velazco", 1235, 1213234));
        library = new Library("Mi Libreria", books, customers, lends);
    }
    @Test
    void testExiste() throws Exception {
        library.getBookLends().add(new BookLend(nifExistente, isbnExistente, null, null));
        boolean resultado = library.existsBookLend(isbnExistente, nifExistente);
        Assertions.assertTrue(resultado, "devuelte true");
    }

    @Test
    void returnFalse() throws Exception {
        boolean resultado = library.existsBookLend(isbnExistente, nifExistente);
        Assertions.assertFalse(resultado, "devuelte false");
    }
    @Test

    void testExceptionBookNotFoundException() {
        Long isbnExistente = 999999999L;
        Assertions.assertThrows(BookNotFoundException.class, () -> {
            library.existsBookLend(isbnExistente, nifExistente);
        });
    }

    @Test
    void testExceptionCustomerNotFound() {
        String nifExistente = "999999";
        Assertions.assertThrows(CustomerNotFoundException.class, () -> {
            library.existsBookLend(isbnExistente, nifExistente);
        });
    }
}
