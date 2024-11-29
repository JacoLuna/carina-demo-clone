package com.zebrunner.carina.demo.gui.pages.desktop.tiendaMia.entities;

import java.util.Objects;

public class Item {
    private String link = "";
    private String img;
    private String name;
    private float price;
    private String brand = "";

    public Item( String link, String name, float price,String img, String brand) {
        this(name, price);
        this.link = link;
        this.img = img;
        this.brand = brand;
    }
    public Item(String name, float price) {
        this.name = name;
        this.price = price;
    }

    public String getLink() {
        return link;
    }

    public String getImg() {
        return img;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public String getBrand() {
        return brand;
    }

    @Override
    public String toString() {
        return "Item{" +
                "link='" + link + '\'' +
                ", img='" + img + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", brand='" + brand + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Float.compare(price, item.price) == 0 && Objects.equals(name, item.name) && Objects.equals(img, item.img);
    }

    @Override
    public int hashCode() {
        return Objects.hash(img, name, price);
    }
}
