package com.example.classes.books;

import java.util.Comparator;

public enum BookComparators {
    BY_TITLE(Comparator.comparing(Book::getTitle)),
    BY_AUTHOR(Comparator.comparing(Book::getAuthor)),
    BY_PRICE(Comparator.comparingInt(Book::getPrice)),
    BY_EDITION(Comparator.comparing(Book::getEdition)),
    BY_ISBN(Comparator.comparingInt(Book::getIsbn)),

    BY_LANGUAGE(Comparator.comparing(ProgrammerBook::getLanguage)),
    BY_LEVEL(Comparator.comparingInt(ProgrammerBook::getLevel));


    private final Comparator<? extends Book> comparator;

    BookComparators(Comparator<? extends Book> comparator) {
        this.comparator = comparator; //Comparator.nullsFirst(comparator);
    }

    public Comparator<? extends Book> getComparator() {
        return comparator;
    }
}
