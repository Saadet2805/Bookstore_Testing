package application.bookstore.test;



import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import application.bookstore.models.Order;



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

            Order.getTotal(10.0f, -5);

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

}