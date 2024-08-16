package com.example.classes.books;

import java.util.Objects;

public class ProgrammerBook extends Book {
    private String language;
    private int level;

    public ProgrammerBook() {
        super();
    }

    public ProgrammerBook(String title, String author, int price, String edition, int isbn, String language, int level) {
        super(title, author, price, edition, isbn);
        setLanguage(language);
        setLevel(level);
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ProgrammerBook that = (ProgrammerBook) o;
        return level == that.level &&
                Objects.equals(language, that.language);
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = hash * 31 + Objects.hashCode(language);
        hash = hash * 31 + level;
        return hash ^ (hash >>> 16);
    }

    @Override
    public String toString() {
        return "ProgrammerBook{" +
                "title='" + getTitle() + '\'' +
                ", author='" + getAuthor() + '\'' +
                ", price=" + getPrice() +
                ", edition='" + getEdition() + '\'' +
                "language='" + getLanguage() + '\'' +
                ", level=" + getLevel() +
                '}';
    }

    @Override
    public ProgrammerBook clone() {
        ProgrammerBook clone = (ProgrammerBook) super.clone();
        return clone;
    }
}
