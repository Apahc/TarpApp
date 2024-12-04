package com.tarotapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TarotInterface {
    private JFrame frame;
    private JPanel cardsPanel;
    private JButton drawButton;
    private JLabel meaningLabel;
    private List<JButton> cardButtons;

    public TarotInterface() {
        // Создание основного окна
        frame = new JFrame("Дневной расклад Таро");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        // Инициализация компонентов
        initComponents();

        // Устанавливаем видимость окна
        frame.setVisible(true);
    }

    private void initComponents() {
        JPanel gridPanel = new JPanel(new GridLayout(3, 1, 0, 10)); // 10 пикселей отступа по вертикали

        // Панель для отображения карт
        cardsPanel = new JPanel();
        cardsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // 10 пикселей между карточками

        // Начальное состояние, один зеленый прямоугольник
        JButton initialCard = createCardButton(" ");
        initialCard.setBackground(Color.GREEN);
        initialCard.addActionListener(e -> showFourCards());
        cardsPanel.add(initialCard);

        gridPanel.add(cardsPanel);

        // Кнопка для начала расклада
        drawButton = new JButton("Начать расклад");
        drawButton.addActionListener(e -> showFourCards());

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(drawButton);
        gridPanel.add(buttonsPanel);

        // Метка для толкования карты
        meaningLabel = new JLabel("Толкование карты", SwingConstants.CENTER);
        gridPanel.add(meaningLabel);

        frame.add(gridPanel, BorderLayout.CENTER);
    }

    private JButton createCardButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(150, 200)); // Изменение размера на 3x4
        button.setOpaque(true);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setVerticalAlignment(SwingConstants.CENTER);
        return button;
    }

    private void showFourCards() {
        // Удаляем все компоненты из панели для карт и кнопки
        cardsPanel.removeAll();

        // Создаем 4 зеленых прямоугольника
        cardButtons = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            JButton card = createCardButton("");
            card.setBackground(Color.GREEN);

            // Пример добавления иконки в карточку с масштабированием
            String imagePath = "src/com/tarotapp/images/image" + (i + 1) + ".png";
            ImageIcon icon = createScaledIcon(imagePath, 150, 200);
            card.setIcon(icon);

            card.addActionListener(new CardActionListener(i));
            cardsPanel.add(card);
            cardButtons.add(card);
        }

        // Удаляем кнопку "Начать расклад"
        drawButton.setVisible(false);

        // Обновляем интерфейс
        cardsPanel.revalidate();
        cardsPanel.repaint();
    }

    private ImageIcon createScaledIcon(String path, int width, int height) {
        ImageIcon icon = new ImageIcon(path);
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImg);
    }

    private void showCardMeaning(int cardIndex) {
        // Пример для демонстрации
        String[] cardNames = {"The Fool", "The Magician", "The High Priestess", "The Empress"};
        String[] cardMeanings = {
                "New beginnings, optimism, trust in life",
                "Action, the power to manifest",
                "Inaction, going within, the subconscious",
                "Fertility, femininity, beauty, nature, abundance"
        };

        meaningLabel.setText("Карта: " + cardNames[cardIndex] + " - Толкование: " + cardMeanings[cardIndex]);

        // Отключаем оставшиеся карточки
        for (JButton button : cardButtons) {
            button.setEnabled(false);
        }
    }

    private class CardActionListener implements ActionListener {
        private final int cardIndex;

        public CardActionListener(int cardIndex) {
            this.cardIndex = cardIndex;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            source.setBackground(Color.RED);
            showCardMeaning(cardIndex);
        }
    }

    public static void main(String[] args) {
        // Запуск приложения
        SwingUtilities.invokeLater(TarotInterface::new);
    }
}