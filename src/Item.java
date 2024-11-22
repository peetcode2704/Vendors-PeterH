class Item {
    double price;
    int stock;

    String description;
    double discount;
    boolean isBestseller;


    Item(double price, int numPieces, String description) {
        this.price = price;
        this.stock = numPieces;
        this.description = description;
        this.discount = 0;
        this.isBestseller = false;

    }

    void restock(int amount) {
        this.stock = this.stock + amount;
    }

    void purchase(int amount) {
        this.stock = this.stock - amount;
    }
    String getDescription() {
        return description;
    }
    void setDiscount(double discount) {
        if (discount < 0 || discount > 1) {
            throw new IllegalArgumentException("Discount must be between 0 and 1");
        }
        this.discount = discount;
    }
    String getDiscount() {
        return (discount * 100) + "% off";
    }

    double getPrice() {
        return price * (1 - discount);
    }
    boolean isBestseller() {
        return this.isBestseller;
    }

    void setAsBestseller(boolean isBestseller) {
        this.isBestseller = isBestseller;
    }


}