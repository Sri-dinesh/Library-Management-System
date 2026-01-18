package com.library.ui;

import javax.swing.*;
import java.awt.*;

public class GUIComponents {

    public GUIComponents() {

        JFrame frame = new JFrame("Library Management System");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLayout(new FlowLayout());

        frame.setLocationRelativeTo(null);

        // Book Entry
        JLabel bookName = new JLabel("Enter Book Name:");
        JLabel authorName = new JLabel("Enter Book Author Name:");
        JLabel bookId = new JLabel("Enter BookId: ");

        JTextField bookNameTF = new JTextField(30);
        JTextField authorNameTF = new JTextField(30);
        JTextField bookIdTF = new JTextField(30);

        JButton submitButtonBookEntry = new JButton("Submit Entry");

        frame.add(bookName);
        frame.add(bookNameTF);

        frame.add(authorName);
        frame.add(authorNameTF);

        frame.add(bookId);
        frame.add(bookIdTF);

        frame.add(submitButtonBookEntry);

        // Member Entry
        JLabel memberName = new JLabel("Enter Member Name:");
        JLabel memberId = new JLabel("Enter MemberId: ");

        JTextField memberNameTF = new JTextField(30);
        JTextField memberIdTF = new JTextField(30);

        JButton submitButtonMemberEntry = new JButton("Submit Entry");

        frame.add(memberName);
        frame.add(memberNameTF);

        frame.add(memberId);
        frame.add(memberIdTF);

        frame.add(submitButtonMemberEntry);

        JPanel BookIssuingReturning = new JPanel();

        JLabel name = new JLabel("Book Entry System !!!");

        BookIssuingReturning.add(name);

        JTabbedPane bookEntry = new JTabbedPane();
        bookEntry.addTab("Book Entry", BookIssuingReturning);

        // Books Issuing & Returning
        JLabel issueBookName = new JLabel("Enter the Book Name to give:");
        JTextField issueBookNameTF = new JTextField(30);
        JLabel issueAuthorName = new JLabel("Enter the Book Author Name to give:");
        JTextField issueAuthorNameTF = new JTextField(30);
        JLabel issueBookId = new JLabel("Enter the Book Id to give: ");
        JTextField issueBookIdTF = new JTextField(30);

        JLabel issuingTo = new JLabel("Enter the Member name to give:");
        JTextField issuingToTF = new JTextField(30);
        JLabel returningFrom = new JLabel("Enter the Book Returning Member Name:");
        JTextField returningFromTF = new JTextField(30);

        JLabel returnBookName = new JLabel("Enter the Book Name to return:");
        JTextField returnBookNameTF = new JTextField(30);

        JLabel returnBookId = new JLabel("Enter the Book Id to return: ");
        JTextField returnBookIdTF = new JTextField(30);

        BookIssuingReturning.add(issueBookName);
        BookIssuingReturning.add(issueBookNameTF);

        BookIssuingReturning.add(issueAuthorName);
        BookIssuingReturning.add(issueAuthorNameTF);

        BookIssuingReturning.add(issueBookId);
        BookIssuingReturning.add(issueBookIdTF);

        BookIssuingReturning.add(issuingTo);
        BookIssuingReturning.add(issuingToTF);

        BookIssuingReturning.add(returningFrom);
        BookIssuingReturning.add(returningFromTF);

        BookIssuingReturning.add(returnBookName);
        BookIssuingReturning.add(returnBookNameTF);

        BookIssuingReturning.add(returnBookId);
        BookIssuingReturning.add(returnBookIdTF);

        frame.add(BookIssuingReturning);

    }

}
