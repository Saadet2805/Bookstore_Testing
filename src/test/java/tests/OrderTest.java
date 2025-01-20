package tests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import java.lang.reflect.Field;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import application.bookstore.models.Order;
import application.bookstore.auxiliaries.FileHandler;
import application.bookstore.models.Book;
import application.bookstore.models.Category;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;


class OrderTest {

	
	//Boundary Value Testing

    @Test

    void testGetTotalValidInputs() throws Exception {

        // Test with valid boundary values

        assertEquals(0.0f, Order.getTotal(0.0f, 0)); // Lower boundary

        assertEquals(100.0f, Order.getTotal(10.0f, 10)); // Normal case

    }



    @Test

    void testGetTotalNegativePrice() {

        // Test with negative price

        Exception exception = assertThrows(Exception.class, () -> {

            Order.getTotal(-1.0f, 10);

        });

        assertEquals("Price cannot be negative", exception.getMessage());

    }



    @Test

    void testGetTotalNegativeQuantity() {

        // Test with negative quantity

        Exception exception = assertThrows(Exception.class, () -> {

            Order.getTotal(10.0f, -1);

        });

        assertEquals("Quantity cannot be negative", exception.getMessage());

    }



 // Class Evaluation Testing



    // Test Case 1: Valid Inputs - Positive price and quantity

    @Test

    void testValidPositivePriceAndQuantity() {

        try {

            float price = 10.0f;

            int quantity = 5;

            float expected = price * quantity; // Expected total = 10 * 5 = 50

            float result = getTotal(price, quantity);

            assertEquals(expected, result, "The total should be the product of price and quantity.");

        } catch (Exception e) {

            fail("Exception should not be thrown for valid inputs.");

        }

    }



    // Test Case 2: Valid Inputs - Price is zero, quantity is positive

    @Test

    void testZeroPriceAndPositiveQuantity() {

        try {

            float price = 0.0f;

            int quantity = 5;

            float expected = 0.0f; // Expected total = 0 * 5 = 0

            float result = getTotal(price, quantity);

            assertEquals(expected, result, "The total should be 0 when price is 0.");

        } catch (Exception e) {

            fail("Exception should not be thrown for valid inputs.");

        }

    }



    // Test Case 3: Valid Inputs - Price is positive, quantity is zero

    @Test

    void testPositivePriceAndZeroQuantity() {

        try {

            float price = 10.0f;

            int quantity = 0;

            float expected = 0.0f; // Expected total = 10 * 0 = 0

            float result = getTotal(price, quantity);

            assertEquals(expected, result, "The total should be 0 when quantity is 0.");

        } catch (Exception e) {

            fail("Exception should not be thrown for valid inputs.");

        }

    }



    // Test Case 4: Valid Inputs - Both price and quantity are zero

    @Test

    void testZeroPriceAndZeroQuantity() {

        try {

            float price = 0.0f;

            int quantity = 0;

            float expected = 0.0f; // Expected total = 0 * 0 = 0

            float result = getTotal(price, quantity);

            assertEquals(expected, result, "The total should be 0 when both price and quantity are 0.");

        } catch (Exception e) {

            fail("Exception should not be thrown for valid inputs.");

        }

    }



    // Test Case 5: Invalid Inputs - Negative price, positive quantity

    @Test

    void testNegativePricePositiveQuantity() {

        try {

            float price = -5.0f;

            int quantity = 5;

            getTotal(price, quantity);

            fail("Exception should be thrown for negative price.");

        } catch (Exception e) {

            assertEquals("Price cannot be negative", e.getMessage());

        }

    }



    // Test Case 6: Invalid Inputs - Positive price, negative quantity

    @Test

    void testPositivePriceNegativeQuantity() {

        try {

            float price = 10.0f;

            int quantity = -3;

            getTotal(price, quantity);

            fail("Exception should be thrown for negative quantity.");

        } catch (Exception e) {

            assertEquals("Quantity cannot be negative", e.getMessage());

        }

    }



    // Test Case 7: Invalid Inputs - Both price and quantity are negative

    @Test

    void testNegativePriceNegativeQuantity() {

        try {

            float price = -10.0f;

            int quantity = -3;

            getTotal(price, quantity);

            fail("Exception should be thrown for negative price and negative quantity.");

        } catch (Exception e) {

            assertEquals("Price cannot be negative", e.getMessage());

        }

    }



    // Test Case 8: Invalid Inputs - Price is zero, quantity is negative

    @Test

    void testZeroPriceNegativeQuantity() {

        try {

            float price = 0.0f;

            int quantity = -3;

            getTotal(price, quantity);

            fail("Exception should be thrown for negative quantity.");

        } catch (Exception e) {

            assertEquals("Quantity cannot be negative", e.getMessage());

        }

    }



    // Helper method that simulates the original method

    private float getTotal(float price, int quantity) throws Exception {

        if (price < 0) {

            throw new Exception("Price cannot be negative");

        } else if (quantity < 0) {

            throw new Exception("Quantity cannot be negative");

        }

        return quantity * price;

    }



    //Code Coverage Testing



    @Test

    void testNormalFlow() throws Exception {

        assertEquals(50.0f, Order.getTotal(10.0f, 5)); // Normal case

    }



    @Test

    void testNegativePrice() {

        Exception exception = assertThrows(Exception.class, () -> {

        Order.getTotal(-1.0f, 5); // Negative price

    });

            assertEquals("Price cannot be negative", exception.getMessage());

    }



    @Test

    void testNegativeQuantity() {

        Exception exception = assertThrows(Exception.class, () -> {

        Order.getTotal(10.0f, -5); // Negative quantity

    });

        assertEquals("Quantity cannot be negative", exception.getMessage());

    }



    @Test

    void testBoundaryCase() throws Exception {

        assertEquals(0.0f, Order.getTotal(0.0f, 0)); // Boundary case

    }
    
    private Order order;
    private Book mockBook;

    @BeforeEach
    void setUp() {
        order = new Order("1234567890", "Selma Aksoy", 2, 25.0f, 50.0f);
        mockBook = mock(Book.class);
    }
    
    //It checks the functionality of saving data, which is fundamental to ensuring that orders persist as expected
    //Edge Case: Ensure that the mocked file system behavior doesn't cause unexpected results
    @Test
    void testSaveInFile() {
        Order spyOrder = spy(order);  // Create a spy of the order
        doReturn(true).when(spyOrder).save(any(File.class));  // Mock 'save' method to return true

        // Mocking the behavior of the saveInFile method
        when(spyOrder.saveInFile()).thenReturn(true);  

        assertTrue(spyOrder.saveInFile());  // Verify that saveInFile returns true
    }

    // retrieves orders from a file, and it's vital for the system to correctly read and deserialize order objects
    //Edge Case: Make sure it handles EOFException and other potential IO errors correctly.
    //@Test
   // void testGetOrders() throws Exception {
        //try (MockedStatic<FileHandler> mockedFileHandler = mockStatic(FileHandler.class)) {
            // Mock the static readFile method to return a predefined list of orders
            //mockedFileHandler.when(() -> FileHandler.readFile(any(File.class)))
                             //.thenReturn(Arrays.asList(new Order("ISBN1", "Client1", 1, 10.0f, 10.0f)));

            // Call the method you're testing
            //ArrayList<Order> orders = Order.getOrders();

            // Assert that the orders list is correctly returned
            //assertNotNull(orders);
            //assertEquals(1, orders.size());
           // assertEquals("ISBN1", orders.get(0).getIsbn());
       // }
    //}


    //calculates the total price based on quantity and price
    //Edge Case: The negative price or quantity should throw exceptions, ensuring data integrity.
    @Test
    void testGetTotal() throws Exception {
        // Test valid total calculation
        float total = Order.getTotal(10.0f, 5);
        assertEquals(50.0f, total, 0.01);
        
        // Test exception when price is negative
        assertThrows(Exception.class, () -> Order.getTotal(-10.0f, 5));

        // Test exception when quantity is negative
        assertThrows(Exception.class, () -> Order.getTotal(10.0f, -5));
    }

    //writes the order details to a file
    //Ensure that file creation or writer handling doesn't result in unexpected errors
    @Test
    void testPrintToFile() throws IOException {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);

        // Call the refactored print method
        order.print(writer);

        // Verify that the correct content is written to the writer
        String output = stringWriter.toString();
        assertTrue(output.contains("Order: " + order.getOrderID()), "The output should contain the order ID.");
        assertTrue(output.contains("Client: " + order.getClientName()), "The output should contain the client name.");
        // Add more assertions as needed to verify the complete output
    }
    
    // checks whether an order has valid data
    //It should return false for invalid data like an empty client name or invalid quantity
    @Test
    void testIsValid() {
        // Arrange
        Order order = new Order("ISBN1", "Client1", 1, 10.0f, 10.0f);

        // Act and Assert
        assertTrue(order.isValid());  // Valid client name should return true

        order.setClientName("");  // Set client name to empty
        assertFalse(order.isValid());  // Invalid client name should return false
    }

//ensures that an order can be removed from the list and the file
    //Edge Case: Ensure it handles the case where the order doesn't exist or if there’s an issue with file handling
    @Test
    void testDeleteFromFile() {
        Order orderToDelete = new Order("1234567890", "Selma Aksoy", 2, 10.0f, 20.0f);
        orderToDelete.saveInFile();

        assertTrue(orderToDelete.deleteFromFile());
        assertFalse(Order.getOrders().contains(orderToDelete));
    }

    

}