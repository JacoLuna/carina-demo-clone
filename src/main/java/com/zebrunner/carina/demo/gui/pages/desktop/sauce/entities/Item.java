package com.zebrunner.carina.demo.gui.pages.desktop.sauce.entities;

import java.util.Objects;

public class Item {
//    private String img;
    private String name;
    private float price;

    public Item(String name, float price) {
        this.name = name;
        this.price = price;
//        this.img = img;
    }


//    public String getImg() {
//        return img;
//    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }


    @Override
    public String toString() {
        return "Item{" +
//                "img='" + img + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Float.compare(price, item.price) == 0 && Objects.equals(name, item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}
