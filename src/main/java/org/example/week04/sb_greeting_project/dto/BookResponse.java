package org.example.week04.sb_greeting_project.dto;

public record BookResponse(
        Long id,
        String title,
        String author,
        int price,
        String category,
        String isbn
) {
}
