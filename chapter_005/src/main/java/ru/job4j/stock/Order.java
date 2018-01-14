package ru.job4j.stock;

public class Order {
    private int id;
    private String bookname;
    private Operation operation;
    private double price;
    private int volume;
    private boolean isAvailable;

    public Order(int id, String bookname, Operation operation, double price, int volume, boolean isAvailable) {
        this.id = id;
        this.bookname = bookname;
        this.operation = operation;
        this.price = price;
        this.volume = volume;
        this.isAvailable = isAvailable;
    }

    enum Operation {
        SELL,
        BUY
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public boolean isAvailable() {
        return isAvailable;
    }


    public void setAvailable(boolean available) {
        isAvailable = available;
    }

}
