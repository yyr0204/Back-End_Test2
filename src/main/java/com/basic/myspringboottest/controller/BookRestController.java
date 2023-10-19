package com.basic.myspringboottest.controller;

import com.basic.myspringboottest.dto.BookReqDTO;
import com.basic.myspringboottest.dto.BookResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.basic.myspringboottest.service.BookService;

import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/books")
public class BookRestController {
    private final BookService bookService;
    @PostMapping
    public BookResDTO saveBook(@RequestBody BookReqDTO bookReqDTO){
        return bookService.saveBook(bookReqDTO);
    }

    @GetMapping
    public List<BookResDTO> getBooks(){
        return bookService.getBooks();
    }

    @GetMapping("/{id}")
    public BookResDTO getBookById(@PathVariable Long id){
        return bookService.getBookById(id);
    }
    @GetMapping("/isbn/{isbn}")
    public BookResDTO getBookByIsbn(@PathVariable String isbn){
        return bookService.getBookByIsbn(isbn);
    }


    @PatchMapping("/{id}")
    public BookResDTO updateBook(@PathVariable Long id, @RequestBody BookReqDTO bookReqDTO){
        return bookService.updateBook(id,bookReqDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok(id + " User가 삭제처리 되었습니다.");
    }
}
