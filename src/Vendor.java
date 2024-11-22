import java.util.HashMap;


/**
 * Class for a Vending Machine.  Contains a hashtable mapping item names to item data, as
 * well as the current balance of money that has been deposited into the machine.
 */
class Vending {
    private static HashMap<String, Item> Stock = new HashMap<String, Item>();
    private HashMap<String, Integer> purchaseHistory = new HashMap<>();

    private double balance;

    Vending(int numCandy, int numGum) {
        Stock.put("Candy", new Item(1.25, numCandy,"A sweet lemon candy"));
        Stock.put("Gum", new Item(.5, numGum, "A pack of mint gum for fresh breath"));
        purchaseHistory.put("Candy", 0);
        purchaseHistory.put("Gum", 0);
        this.balance = 0;
    }

    /**
     * resets the Balance to 0
     */
    void resetBalance() {
        this.balance = 0;
    }

    /**
     * returns the current balance
     */
    double getBalance() {
        return this.balance;
    }

    /**
     * adds money to the machine's balance
     *
     * @param amt how much money to add
     */
    void addMoney(double amt) {
        if (amt < 0) {
            System.out.println("Failed to add amount");
        } else
            this.balance = this.balance + amt;
    }

    /**
     * attempt to purchase named item.  Message returned if
     * the balance isn't sufficient to cover the item cost.
     *
     * @param name The name of the item to purchase ("Candy" or "Gum")
     */
    void select(String name) {
        if (Stock.containsKey(name)) {
            Item item = Stock.get(name);
            if (balance >= item.price) {
                item.purchase(1);
                this.balance = this.balance - item.price;
                purchaseHistory.put(name, purchaseHistory.getOrDefault(name, 0) + 1);

            } else
                System.out.println("Gimme more money");
        } else System.out.println("Sorry, don't know that item");
    }

    public static HashMap<String, Item> getStock() {
        return Stock;
    }

     void restockItem(String name, int amount) {
        if (Stock.containsKey(name)) {
            // max amount of restock for each item
            if (amount >= 0 && amount < 100) {
                Stock.get(name).restock(amount);
            }
            else {
                System.out.println("Failed to restock negative amount");
            }
        }
        else {
            double defaultPrice = 1.50;
            Stock.put(name, new Item(defaultPrice, amount,"Item description"));        }
    }
    void renameItem(String oldName, String newName) {
        //Check if the name of the item exists in our HashMap or not
        if (!Stock.containsKey(oldName)) {
            throw new IllegalArgumentException(oldName + "' does not exist.");
        }

        if (Stock.containsKey(newName)) {
            throw new IllegalArgumentException(newName + "' already exists.");
        }
        // After checking both conditions, get that old item and remove then change to newName

        Item item = Stock.get(oldName);
        Stock.remove(oldName);
        Stock.put(newName, item);
    }

    void printInventory() {
        System.out.println("Item Name\tPrice\tStock");
        for (String itemName : Stock.keySet()) {
            Item item = Stock.get(itemName);
            System.out.println(itemName + "\t\t$" + item.price + "\t" + item.stock);
        }
    }

    void removeItem(String name) {
        if (Stock.containsKey(name)) {
            Stock.remove(name);
            System.out.println(name + " has been removed from the inventory.");
        } else {
            throw new IllegalArgumentException("Item " + name + " does not exist in the inventory.");
        }
    }

    HashMap<String, Integer> getPurchaseHistory() {
        return purchaseHistory;
    }

    String getItemDetails(String name) {
        if (Stock.containsKey(name)) {
            Item item = Stock.get(name);
            return "Name: " + name + "\nPrice: $" + item.price + "\nStock: " + item.stock + "\nDescription: " + item.getDescription();
        } else {
            throw new IllegalArgumentException("Item " + name + " does not exist in the inventory.");
        }
    }


    class Examples {

        }
}