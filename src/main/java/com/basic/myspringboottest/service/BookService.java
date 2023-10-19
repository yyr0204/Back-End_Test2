package com.basic.myspringboottest.service;

import com.basic.myspringboottest.entity.Book;
import com.basic.myspringboottest.exception.BusinessException;
import com.basic.myspringboottest.repository.BookRepository;
import com.basic.myspringboottest.dto.BookReqDTO;
import com.basic.myspringboottest.dto.BookReqForm;
import com.basic.myspringboottest.dto.BookResDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;
@Service
@Transactional
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    public BookResDTO saveBook(BookReqDTO bookReqDTO) {
        Book book = modelMapper.map(bookReqDTO, Book.class);
        Book savedBook = bookRepository.save(book);
        return modelMapper.map(savedBook, BookResDTO.class);
    }
    @Transactional(readOnly = true)
    public BookResDTO getBookById(Long id){
        Book bookEntity = bookRepository.findById(id)
                .orElseThrow(() -> new BusinessException(id+" Book Not Found", HttpStatus.NOT_FOUND));
        //Entity를 resDTO로
        BookResDTO bookResDTO = modelMapper.map(bookEntity,BookResDTO.class);
        return bookResDTO;
    }
    @Transactional(readOnly = true)
    public BookResDTO getBookByIsbn(String isbn){
        Book bookEntity = bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BusinessException(isbn+" Book Not Found", HttpStatus.NOT_FOUND));
        BookResDTO bookResDTO = modelMapper.map(bookEntity,BookResDTO.class);
        return bookResDTO;
    }
    @Transactional(readOnly = true)
    public List<BookResDTO> getBooks(){
        List<Book> bookList = bookRepository.findAll();
        List<BookResDTO> bookResDTOList = bookList.stream()
                .map(book -> modelMapper.map(book, BookResDTO.class))
                .collect(toList());
        return bookResDTOList;
    }

    public BookResDTO updateBook(Long id, BookReqDTO bookReqDTO) {
        Book existBook = bookRepository.findById(id)
                .orElseThrow(() ->
                        new BusinessException(id + " Book Not Found", HttpStatus.NOT_FOUND));
        existBook.setTitle(bookReqDTO.getTitle());
        existBook.setAuthor(bookReqDTO.getAuthor());
        existBook.setGenre(bookReqDTO.getGenre());
        return modelMapper.map(existBook, BookResDTO.class);
    }
    public void updateBookForm(BookReqForm bookReqForm){
        Book existBook = bookRepository.findById(bookReqForm.getId())
                .orElseThrow(() ->
                        new BusinessException(bookReqForm.getId() + " Book Not Found", HttpStatus.NOT_FOUND));
        existBook.setTitle(bookReqForm.getTitle());
        bookRepository.save(existBook);
    }

    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id) //Optional<User>
                .orElseThrow(() ->
                        new BusinessException(id + " Book Not Found", HttpStatus.NOT_FOUND));
        bookRepository.delete(book);
    }
}
