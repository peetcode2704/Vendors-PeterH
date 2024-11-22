import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
public class VendorTest {
    static Vending vendingMachine;

    @BeforeEach
    public void setUp() {
        vendingMachine = new Vending(10, 20);
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
    @Test
    public void BuyItem() {
        vendingMachine.addMoney(5.00);
        vendingMachine.select("Candy"); //Candy price 1.25

        // 5.00 - 1.25 = 3.75 for a candy
        Assertions.assertEquals(3.75, vendingMachine.getBalance());

        // Decrease candy amount by 1
        Item candy = Vending.getStock().get("Candy");
        Assertions.assertEquals(9, candy.stock);
    }

    @Test
    public void buyItemNoMoney() {
        vendingMachine.addMoney(0.50);
        vendingMachine.select("Candy"); //Candy price 1.25

        //Not enough money to buy candy
        Assertions.assertEquals(0.50, vendingMachine.getBalance());

        //Candy amounts stay the same
        Item candy = Vending.getStock().get("Candy");
        Assertions.assertEquals(10, candy.stock);
    }

    @Test
    public void buyNonExistentItem() {
        vendingMachine.addMoney(5.00);
        vendingMachine.select("Chips"); //Chips is not one of the item

        // Since Chips is not in an item in the vendor, the fund doesn't change
        Assertions.assertEquals(5.00, vendingMachine.getBalance());
    }

    @Test
    public void buyNegative() {
        vendingMachine.addMoney(5.00);
        vendingMachine.select("Chips"); //Chips is not one of the item

        // Since Chips is not in an item in the vendor, the fund doesn't change
        Assertions.assertEquals(5.00, vendingMachine.getBalance());
    }

    @Test
    public void emptyInventory() {

        vendingMachine.addMoney(100.00);

        // Purchase all Candy
        while (Vending.getStock().get("Candy").stock > 0) {
            vendingMachine.select("Candy");
        }

        // Purchase all Gum
        while (Vending.getStock().get("Gum").stock > 0) {
            vendingMachine.select("Gum");
        }

        // Verify that all items are out of stock
        Assertions.assertEquals(0, Vending.getStock().get("Candy").stock);
        Assertions.assertEquals(0, Vending.getStock().get("Gum").stock);

        // Check for the remaining balance
        double remainingBalance = vendingMachine.getBalance() - (10 * 1.25) - (20 * 0.50);
        Assertions.assertEquals(remainingBalance, vendingMachine.getBalance());
    }
}