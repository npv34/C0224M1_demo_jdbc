package org.codegym.library.entity;

public class Book {
    private int id;
    private String name;
    private String description;
    private int price;
    private int category_id;
    private Category category;


    public Book(String name, String description, int price, int category_id) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category_id = category_id;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public Category getCategory() {
        return category;
    }
}
