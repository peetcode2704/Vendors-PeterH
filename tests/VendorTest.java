import org.junit.gen5.api.Assertions;
import org.junit.gen5.api.BeforeAll;
import org.junit.gen5.api.Assertions;
import org.junit.gen5.api.Test;
import org.junit.gen5.api.BeforeEach;
import org.junit.gen5.api.Assertions.*;

class VendingTest {
    private Vending vendingMachine;

    @BeforeEach
    void setUp() {
        vendingMachine = new Vending(10, 20); // Initialize with 10 candies and 20 gums
    }

    @Test
    void testAddMoney() {
        vendingMachine.addMoney(5.00);

        Assertions.assertEquals(5.00, vendingMachine.getBalance());
    }
    @Test
    void testAddNoMoney() {
        vendingMachine.addMoney(0);

        Assertions.assertEquals(0, vendingMachine.getBalance());
    }
    @Test
    void testAddNegativeMoney() {
        vendingMachine.addMoney(-50);

        Assertions.assertEquals(0, vendingMachine.getBalance());
    }
    @Test
    void testAddTooMuchMoney() {
        vendingMachine.addMoney(Integer.MAX_VALUE);

        Assertions.assertEquals(Integer.MAX_VALUE, vendingMachine.getBalance());
    }
    @Test
    void testAddTooMuchMinMoney() {
        vendingMachine.addMoney(Integer.MIN_VALUE);

        Assertions.assertEquals(0, vendingMachine.getBalance());
    }

}