package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import application.bookstore.models.Author;

class AuthorTest {
	// MC/DC Coverage Testing

	 @Test
	    void testBothValid() {
	        Author person = new Author("John", "Smith");
	        assertTrue(person.isValid(), "Both first and last names are valid; should return true.");
	    }

	    @Test
	    void testInvalidFirstName() {
	        Author person = new Author("", "Smith");
	        assertFalse(person.isValid(), "First name is invalid; should return false.");
	    }

	    @Test
	    void testInvalidLastName() {
	        Author person = new Author("John", "");
	        assertFalse(person.isValid(), "Last name is invalid; should return false.");
	    }

	    @Test
	    void testBothInvalid() {
	        Author person = new Author("","");
	        assertFalse(person.isValid(), "Both first and last names are invalid; should return false.");
	    }

}
