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
        double amountofMoney = 100.00;

        vendingMachine.addMoney(amountofMoney);

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
        // 100 - 12.5 - 10 = 77.5
        double remainingBalance = amountofMoney - (10 * 1.25) - (20 * 0.50);
        Assertions.assertEquals(remainingBalance, vendingMachine.getBalance());
    }

    @Test
    public void restockItem() {
        // Restock Candy
        vendingMachine.restockItem("Candy", 5);

        // Verify new stock count
        Assertions.assertEquals(15, Vending.getStock().get("Candy").stock);
    }

    @Test
    public void restockItemNegative() {
        // Restock Candy
        vendingMachine.restockItem("Candy", -5);

        // Verify new stock count
        Assertions.assertEquals(10, Vending.getStock().get("Candy").stock);
    }

    @Test
    public void restockItemNonexist() {
        // Restock a non-existent item
        vendingMachine.restockItem("Chocolate", 5);
        // Check if chocolate is now the new item
        Assertions.assertTrue(Vending.getStock().containsKey("Chocolate"),
                "Chocolate should be added to the inventory.");
        // check if the amount is correct
        Assertions.assertEquals(5, Vending.getStock().get("Chocolate").stock,
                "Chocolate stock should match the restock amount.");
    }

    @Test
    public void restockItemMaxValue() {
        vendingMachine.restockItem("Candy", Integer.MAX_VALUE);

        Assertions.assertEquals(10, Vending.getStock().get("Candy").stock);
    }

    @Test
    public void restockItemMinValue() {
        vendingMachine.restockItem("Candy", Integer.MIN_VALUE);

        Assertions.assertEquals(10, Vending.getStock().get("Candy").stock);
    }

    @Test
    public void restockItemChips() {
        // Restock a non-existent item
        vendingMachine.restockItem("Chips", 15);
        // Check if chocolate is now the new item
        Assertions.assertTrue(Vending.getStock().containsKey("Chips"),
                "Chips should be added to the inventory.");
        // check if the amount is correct
        Assertions.assertEquals(15, Vending.getStock().get("Chips").stock,
                "Chips stock should match the restock amount.");
    }
}