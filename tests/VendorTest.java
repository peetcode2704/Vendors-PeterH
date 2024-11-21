import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
public class VendorTest {
    static Vending vendingMachine;

    @BeforeEach
    public void setUp() {
        vendingMachine = new Vending(10, 20); // Initialize with 10 candies and 20 gums
    }

    @Test
    public void testAddMoney() {
        vendingMachine.addMoney(5.00);

        Assertions.assertEquals(5.00, vendingMachine.getBalance());
    }
    @Test
    public void testAddNoMoney() {
        vendingMachine.addMoney(0);

        Assertions.assertEquals(0, vendingMachine.getBalance());
    }
    @Test
    public void testAddNegativeMoney() {
        vendingMachine.addMoney(-50);

        Assertions.assertEquals(0, vendingMachine.getBalance());
    }
    @Test
    public void testAddTooMuchMoney() {
        vendingMachine.addMoney(Integer.MAX_VALUE);

        Assertions.assertEquals(Integer.MAX_VALUE, vendingMachine.getBalance());
    }
    @Test
    public void testAddTooMuchMinMoney() {
        vendingMachine.addMoney(Integer.MIN_VALUE);

        Assertions.assertEquals(0, vendingMachine.getBalance());
    }


}