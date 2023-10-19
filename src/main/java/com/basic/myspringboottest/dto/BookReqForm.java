package com.basic.myspringboottest.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookReqForm {
    private Long id;
    @NotBlank(message = "Title은 필수 입력항목입니다.")
    private String title;
    @NotBlank(message = "Author은 필수 입력항목입니다.")
    private String author;
    @NotBlank(message = "Isbn은 필수 입력항목입니다.")
    private String isbn;
    private String genre;
}
