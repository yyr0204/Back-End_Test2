package com.basic.myspringboottest.controller;

import com.basic.myspringboottest.dto.BookReqDTO;
import com.basic.myspringboottest.dto.BookReqForm;
import com.basic.myspringboottest.dto.BookResDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.basic.myspringboottest.service.BookService;

import java.util.List;

@Controller
@RequestMapping("/bookspage")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @RequestMapping("/index")
    public ModelAndView index(){
        List<BookResDTO> bookResDTOList = bookService.getBooks();
        return new ModelAndView("index","books",bookResDTOList);
    }
    @GetMapping("/signup")
    public String showSignUpForm(BookReqDTO book) {
        return "add-book";
    }
    @PostMapping("/addbook")
    public String addUser(@Valid BookReqDTO book, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "add-book";
        }
        //등록 요청
        bookService.saveBook(book);
        return "redirect:/bookspage/index";
    }
    //수정 페이지 호출
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable Long id,Model model){
        BookResDTO bookResDTO = bookService.getBookById(id);
        model.addAttribute("book",bookResDTO);
        return "update-book";
    }

    @PostMapping("/update/{id}")
    public String updateBook(@PathVariable("id") Long id, @Valid BookReqForm book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("book",book);
            model.addAttribute("errors", result.getAllErrors());
            return "update-book";
        }
        bookService.updateBookForm(book);
        return "redirect:/bookspage/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
        return "redirect:/bookspage/index";
    }
    @GetMapping("/detail/{id}")
    public String detailBook(@PathVariable("id") Long id,Model model){
        BookResDTO bookResDTO = bookService.getBookById(id);
        model.addAttribute("book",bookResDTO);
        return "detail-book";
    }
}
