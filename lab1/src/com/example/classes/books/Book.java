package com.example.classes.books;

import java.util.Objects;

public class Book implements Cloneable, Comparable<ProgrammerBook>{
    private String title;
    private String author;
    private int price;
    private String edition;
    private int isbn;

    public Book() {
    }

    public Book(String title, String author, int price, String edition, int isbn) {
        setTitle(title);
        setAuthor(author);
        setPrice(price);
        setEdition(edition);
        setIsbn(isbn);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    @Override
    public int compareTo(ProgrammerBook o) {
        return Integer.compare(getIsbn(), o.getIsbn());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return price == book.price &&
                Objects.equals(title, book.title) &&
                Objects.equals(author, book.author) &&
                Objects.equals(edition, book.edition);
    }

    @Override
    public int hashCode() {
        long hash = price;
        hash = hash * 31 + Objects.hashCode(title);
        hash = hash * 31 + Objects.hashCode(author);
        hash = hash * 31 + Objects.hashCode(edition);
        return (int) (hash ^ (hash >>> 32));
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + getTitle() + '\'' +
                ", author='" + getAuthor() + '\'' +
                ", price=" + getPrice() +
                ", edition='" + getEdition() + '\'' +
                '}';
    }

    @Override
    public Book clone() {
        try {
            Book clone = (Book) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }
}
