package org.example.week04.dto;

public record BookRequest(
        String title,
        String author,
        int price,
        String category,
        String isbn
) {
}