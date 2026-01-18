package com.library.service;

import com.library.model.Book;
import com.library.model.Member;

public interface ILibrary {
    void addBook(Book b);

    void addMember(Member m);

    void issueBook(String bookId, String memberId) throws Exception;

    void returnBook(String bookId, String memberId) throws Exception;

    void listBooks();

    void listMembers();
}
