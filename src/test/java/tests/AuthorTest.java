package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
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

	    private Author author;
	    
	    
	    @BeforeEach
	    public void setUp() {
	        author = new Author("Selma", "Aksoy");
	        Author.getAuthors().clear(); // Clear the static list before each test
	    }
	    @Test
	    //to check if method correctly concatenates the firstName and lastName without spaces.
	    public void testToString() {
	        assertEquals("SelmaAksoy", author.toString(), "should concatenate firstName and lastName without spaces");
	    }
	    
	    @Test
	    //to check if the author already exists in the list
	    public void testExistsInList() {
	        Author.getAuthors().add(author); // Add the author to the static list
	        assertTrue(author.existsInList(), "returns True if author exists in the list");

	        Author newAuthor = new Author("Saadet", "Yilmaz");
	        assertFalse(newAuthor.existsInList(), "returns false if the author does not exist in the list");
	    }
	    
	    @Test
	    //returns the correct full name with the specified format  (3 spaces bet first and last name)
	    public void testGetFullName() {
	        assertEquals("Selma   Aksoy", author.getFullName(), "returns the full name with three spaces between firstName and lastName.");
	    }
	    
	    @Test
	    //correctly validates the author's name fields
	    public void testIsValid() {
	        assertTrue(author.isValid(), "returns true if both firstName and lastName are not empty");
	        Author invalidAuthor = new Author("", "Aksoy");
	        assertFalse(invalidAuthor.isValid(), "returns false if firstName is empty.");
	        invalidAuthor = new Author("Selma", "");
	        assertFalse(invalidAuthor.isValid(), "returns false if lastName is empty.");
	    }
	    
	    

	    
	    

}
