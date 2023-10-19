package com.basic.myspringboottest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BookResDTO {
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private String genre;
    private LocalDateTime created_at;
}
