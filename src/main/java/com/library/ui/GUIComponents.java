package com.library.ui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class GUIComponents extends JFrame {

    public GUIComponents() {
        setTitle("Library Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        tabbedPane.addTab("Manage Books", createBookPanel());
        tabbedPane.addTab("Manage Members", createMemberPanel());
        tabbedPane.addTab(
            "Circulation (Issue/Return)",
            createCirculationPanel()
        );
        tabbedPane.addTab("View Records", createViewPanel());

        add(tabbedPane);
        setVisible(true);
    }

    private JPanel createBookPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Add New Book");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        panel.add(new JLabel("Book ID:"), gbc);
        gbc.gridx = 1;
        JTextField bookIdField = new JTextField(20);
        panel.add(bookIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Book Title:"), gbc);
        gbc.gridx = 1;
        JTextField bookTitleField = new JTextField(20);
        panel.add(bookTitleField, gbc);

        gbc.gridx = 1;
        gbc.gridy++;
        JButton addButton = new JButton("Add Book");
        addButton.setBackground(new Color(70, 130, 180)); // Steel Blue
        addButton.setForeground(Color.WHITE);
        panel.add(addButton, gbc);

        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.add(panel, BorderLayout.NORTH);
        return wrapper;
    }

    private JPanel createMemberPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Register Member");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        panel.add(new JLabel("Member ID:"), gbc);
        gbc.gridx = 1;
        JTextField memberIdField = new JTextField(20);
        panel.add(memberIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Full Name:"), gbc);
        gbc.gridx = 1;
        JTextField memberNameField = new JTextField(20);
        panel.add(memberNameField, gbc);

        gbc.gridx = 1;
        gbc.gridy++;
        JButton addButton = new JButton("Add Member");
        addButton.setBackground(new Color(60, 179, 113));
        addButton.setForeground(Color.WHITE);
        panel.add(addButton, gbc);

        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.add(panel, BorderLayout.NORTH);
        return wrapper;
    }

    private JPanel createCirculationPanel() {
        JPanel mainPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Issue Section
        JPanel issuePanel = new JPanel(new GridBagLayout());
        issuePanel.setBorder(BorderFactory.createTitledBorder("Issue Book"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        issuePanel.add(new JLabel("Book ID:"), gbc);
        JTextField issueBookId = new JTextField(15);
        gbc.gridx = 1;
        issuePanel.add(issueBookId, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        issuePanel.add(new JLabel("Member ID:"), gbc);
        JTextField issueMemberId = new JTextField(15);
        gbc.gridx = 1;
        issuePanel.add(issueMemberId, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        JButton issueBtn = new JButton("Issue Book");
        issuePanel.add(issueBtn, gbc);

        JPanel returnPanel = new JPanel(new GridBagLayout());
        returnPanel.setBorder(BorderFactory.createTitledBorder("Return Book"));
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        returnPanel.add(new JLabel("Book ID:"), gbc);
        JTextField returnBookId = new JTextField(15);
        gbc.gridx = 1;
        returnPanel.add(returnBookId, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        returnPanel.add(new JLabel("Member ID:"), gbc);
        JTextField returnMemberId = new JTextField(15);
        gbc.gridx = 1;
        returnPanel.add(returnMemberId, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        JButton returnBtn = new JButton("Return Book");
        returnPanel.add(returnBtn, gbc);

        mainPanel.add(issuePanel);
        mainPanel.add(returnPanel);

        return mainPanel;
    }

    private JPanel createViewPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 1, 0, 10));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel booksPanel = new JPanel(new BorderLayout());
        booksPanel.setBorder(BorderFactory.createTitledBorder("All Books"));
        String[] bookCols = { "Book ID", "Title", "Status" };
        Object[][] bookData = {};
        JTable bookTable = new JTable(bookData, bookCols);
        booksPanel.add(new JScrollPane(bookTable), BorderLayout.CENTER);

        JPanel membersPanel = new JPanel(new BorderLayout());
        membersPanel.setBorder(BorderFactory.createTitledBorder("All Members"));
        String[] memberCols = { "Member ID", "Name" };
        Object[][] memberData = {};
        JTable memberTable = new JTable(memberData, memberCols);
        membersPanel.add(new JScrollPane(memberTable), BorderLayout.CENTER);

        panel.add(booksPanel);
        panel.add(membersPanel);

        return panel;
    }
}
