package com.basic.myspringboottest.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BookReqDTO {
    @NotBlank(message = "Title은 필수 입력항목입니다.")
    private String title;
    @NotBlank(message = "Author은 필수 입력항목입니다.")
    private String author;
    @NotBlank(message = "Isbn은 필수 입력항목입니다.")
    private String isbn;
    private String genre;
}
