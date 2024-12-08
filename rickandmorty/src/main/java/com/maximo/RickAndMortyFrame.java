package com.maximo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class RickAndMortyFrame extends JFrame {

    private final RickAndMortyService service;
    private final CharacterTableModel tableModel;
    private int currentPage = 1;

    private JButton prevButton;
    private JButton nextButton;
    private JLabel pageLabel;
    private JTable characterTable;

    public RickAndMortyFrame() {
        service = new RickAndMortyService();
        tableModel = new CharacterTableModel();

        setTitle("Rick and Morty Characters");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        characterTable = new JTable(tableModel);
        add(new JScrollPane(characterTable), BorderLayout.CENTER);

        JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        prevButton = new JButton("Anterior");
        nextButton = new JButton("Siguiente");
        pageLabel = new JLabel("Página: " + currentPage);

        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentPage > 1) {
                    currentPage--;
                    loadData();
                }
            }
        });

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentPage++;
                loadData();
            }
        });

        navPanel.add(prevButton);
        navPanel.add(pageLabel);
        navPanel.add(nextButton);
        add(navPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        loadData();
    }

    private void loadData() {
        try {
            ApiResponse response = service.getCharacters(currentPage);
            tableModel.setCharacters(response.getResults());
            pageLabel.setText("Página: " + currentPage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
