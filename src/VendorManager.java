import java.util.ArrayList;
import java.util.List;

public class VendorManager {
    private List<Vending> vendors;

    VendorManager() {
        vendors = new ArrayList<>();
    }

    void addVendor(Vending vendor) {
        vendors.add(vendor);
    }

    void printAllInventories() {
        for (int i = 0; i < vendors.size(); i++) {
            System.out.println("Vendor " + (i + 1) + ":");
            vendors.get(i).printInventory();
            System.out.println();
        }
    }

    List<Vending> getVendors() {
        return vendors;
    }
}
