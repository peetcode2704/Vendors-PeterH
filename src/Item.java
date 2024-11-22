class Item {
    double price;
    int stock;

    String description;

    Item(double price, int numPieces, String description) {
        this.price = price;
        this.stock = numPieces;
        this.description = description;
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
}