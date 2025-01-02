package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import application.bookstore.models.Book;

class BookTest {

	//Boundary Value Testing
	//Test min values for all parameters
	@Test
    void testValidInputsAtLowerBoundary() {
        Book book = new Book("1234567890", "A", 0, 0, null, 0, null, "X");
        assertTrue(book.isValid(), "All inputs valid at lower boundaries.");
    }

    @Test
    void testInvalidMinISBN() {
        Book book = new Book("123456789", "A", 0, 0, null, 0, null, "X");
        assertFalse(book.isValid(), "ISBN is too short.");
    }
    
    @Test
    void testNominalISBN() {
        Book book = new Book("12345678901", "A", 0, 0, null, 0, null, "X");
        assertFalse(book.isValid(), "ISBN is too short.");
    }
    
    @Test
    void testValidMaxISBN() {
        Book book = new Book("1234567890123", "A", 0, 0, null, 0, null, "X");
        assertTrue(book.isValid(), "ISBN is valid at maximum length.");
    }

    @Test
    void testInvalidMaxISBN() {
        Book book = new Book("12345678901234", "A", 0, 0, null, 0, null, "X");
        assertFalse(book.isValid(), "ISBN is too long.");
    }
    
    @Test
    void testInvalidMinSellingPrice() {
        Book book = new Book("1234567890", "A", 0, -0.01f, null, 0, null, "X");
        assertFalse(book.isValid(), "Selling price below minimum.");
    }
    
    @Test
    void testMinSellingPrice() {
        Book book = new Book("1234567890", "A", 0, 0, null, 0, null, "X");
        assertTrue(book.isValid(), "Selling price is valid.");
    }
    
    @Test
    void testNominalSellingPrice() {
        Book book = new Book("1234567890", "A", 0, 15f, null, 0, null, "X");
        assertTrue(book.isValid(), "Selling price is valid.");
    }
    
    @Test
    void testNegativePurchasedPrice() {
        Book book = new Book("1234567890", "A", -0.01f, 0, null, 0, null, "X");
        assertFalse(book.isValid(), "Purchased price below minimum.");
    }
    
    @Test
    void testMinPurchasedPrice() {
        Book book = new Book("1234567890", "A", 0, 0, null, 0, null, "X");
        assertTrue(book.isValid(), "Purchased price is valid.");
    }
    
    @Test
    void testNominalPurchasedPrice() {
        Book book = new Book("1234567890", "A", 10f, 0, null, 0, null, "X");
        assertTrue(book.isValid(), "Purchased price is valid.");
    }

    @Test
    void testNegativeStock() {
        Book book = new Book("1234567890", "A", 0, 0, null, -1, null, "X");
        assertFalse(book.isValid(), "Stock below minimum.");
    }

    @Test
    void testMinStock() {
        Book book = new Book("1234567890", "A", 0, 0, null, 0, null, "X");
        assertTrue(book.isValid(), "Stock is valid.");
    }
    
    @Test
    void testNominalStock() {
        Book book = new Book("1234567890", "A", 0, 0, null, 10, null, "X");
        assertTrue(book.isValid(), "Stock is valid.");
    }
    
    @Test
    void testEmptyTitle() { //no characters
        Book book = new Book("1234567890", "", 0, 0, null, 0, null, "X");
        assertFalse(book.isValid(), "Title is empty.");
    }
    
    @Test
    void testMinTitle() { //at least 1 character/word
        Book book = new Book("1234567890", "X", 0, 0, null, 0, null, "X");
        assertTrue(book.isValid(), "Title is valid.");
    }
    
    @Test
    void testNominalTitle() { //at least 1 character/word
        Book book = new Book("1234567890", "Oliver Twist", 0, 0, null, 0, null, "X");
        assertTrue(book.isValid(), "Title is valid.");
    }

    @Test
    void testEmptySupplier() {
        Book book = new Book("1234567890", "A", 0, 0, null, 0, null, "");
        assertFalse(book.isValid(), "Supplier is empty.");
    }
    
    @Test
    void testMinSupplier() {
        Book book = new Book("1234567890", "A", 0, 0, null, 0, null, "X");
        assertTrue(book.isValid(), "Supplier is valid.");
    }

    
    @Test
    void testValidSupplier() {
        Book book = new Book("1234567890", "A", 0, 0, null, 0, null, "Pegi");
        assertTrue(book.isValid(), "Supplier is valid.");
    }


 
    //Class Evaluation
    @Test
    void testValidInputs() {
        Book book = new Book("1234567890", "Book", 10.5f, 5.75f, null, 100, null, "Supplier");
        assertTrue(book.isValid(), "All inputs are valid.");
    }

    @Test
    void testInvalidISBN() {
        Book book = new Book("123", "Book", 10.5f, 5.75f, null, 100, null, "Supplier");
        assertFalse(book.isValid(), "Invalid ISBN.");
    }

    @Test
    void testInvalidPriceOrStock() {
        Book book = new Book("1234567890", "Book", -10.5f, 5.75f, null, 100, null, "Supplier");
        assertFalse(book.isValid(), "Invalid price or stock.");
    }

    @Test
    void testInvalidTitleOrSupplier() {
        Book book = new Book("1234567890", "Book!", 10.5f, 5.75f, null, 100, null, "Sup@");
        assertFalse(book.isValid(), "Invalid title or supplier.");
    }

    @Test
    void testInvalidISBNAndPriceStock() {
        Book book = new Book("123", "Book", -10.5f, 5.75f, null, 100, null, "Supplier");
        assertFalse(book.isValid(), "Invalid ISBN and price/stock.");
    }

    @Test
    void testInvalidISBNAndTitleSupplier() {
        Book book = new Book("123", "Book!", 10.5f, 5.75f, null, 100, null, "Sup@");
        assertFalse(book.isValid(), "Invalid ISBN and title/supplier.");
    }

    @Test
    void testInvalidPriceStockAndTitleSupplier() {
        Book book = new Book("1234567890", "Book!", -10.5f, 5.75f, null, 100, null, "Sup@");
        assertFalse(book.isValid(), "Invalid price/stock and title/supplier.");
    }

    @Test
    void testAllInvalidInputs() {
        Book book = new Book("123", "Book!", -10.5f, 5.75f, null, 100, null, "Sup@");
        assertFalse(book.isValid(), "All inputs are invalid.");
    }
    
    //Condition Coverage: statement, branch, condition, mc/dc
    @Test
    void testValidAll() {
        Book book = new Book("1234567890", "Book", 10.5f, 5.75f, null, 100, null, "Supplier");
        assertTrue(book.isValid(), "Book is valid, all conditions should pass.");
    }
    

    @Test
    void testInvalidISBN1() {
        Book book = new Book("123", "Book", 10.5f, 5.75f, null, 100, null, "Supplier");
        assertFalse(book.isValid(), "ISBN is invalid.");
    }

    @Test
    void testInvalidPurchasedPrice() {
        Book book = new Book("1234567890", "Book", -10.5f, 5.75f, null, 100, null, "Supplier");
        assertFalse(book.isValid(), "Price is invalid.");
    }
    
    @Test
    void testInvalidSellingPrice() {
        Book book = new Book("1234567890", "Book", 5.75f, -10.5f, null, 100, null, "Supplier");
        assertFalse(book.isValid(), "Price is invalid.");
    }

    @Test
    void testInvalidStock() {
        Book book = new Book("1234567890", "Book", 10.5f, 5.75f, null, -5, null, "Supplier");
        assertFalse(book.isValid(), "Stock is invalid.");
    }


    @Test
    void testInvalidTitle() {
        Book book = new Book("1234567890", "Book!", 10.5f, 5.75f, null, 100, null, "Supplier");
        assertFalse(book.isValid(), "Title is invalid.");
    }

    @Test
    void testInvalidSupplier() {
        Book book = new Book("1234567890", "Book", 10.5f, 5.75f, null, 100, null, "Sup@");
        assertFalse(book.isValid(), "Supplier is invalid.");
    }

    @Test
    void testInvalidPriceAndStock() {
        Book book = new Book("1234567890", "Book", -10.5f, -5.75f, null, -5, null, "Supplier");
        assertFalse(book.isValid(), "Price and Stock are both invalid.");
    }

    @Test
    void testAllInvalidConditions() {
        Book book = new Book("123", "Book!", -10.5f, -5.75f, null, -5, null, "Sup@");
        assertFalse(book.isValid(), "All conditions are invalid.");
    }

}
