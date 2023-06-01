import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class HangmanGui {
    JFrame frame;
    JPanel mainPanel;
    JPanel hangmanPanel;
    JPanel wordPanel;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double screenWidth = screenSize.getWidth();
    double screenHeight = screenSize.getHeight();
    File wordFile;
    int width = 900;
    int height = 700;
    DrawStand stand;
    ArrayList<String> words;
    String word;


    //EFFECTS: initializes Hangman application
    public HangmanGui() {
        initializeGraphics();
    }

    private void chooseRandomWord() {
        Random random = new Random();
        int index = random.nextInt(words.size());
        word = words.get(index);
        int length = word.length();
        generateLetterBlocks(length);
    }

    //REQUIRES: wordFile exists
    //MODIFIES: this, words
    //EFFECTS: reads file indicated by difficulty choice and adds to words
    private void readWordFile() {
        String line = null;
        words = new ArrayList<>();

        try {
            // FileReader reads text files in the default encoding
            FileReader fileReader = new FileReader(wordFile);

            // Wrap FileReader in BufferedReader
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // Read each line from the file and add it to the list
            while ((line = bufferedReader.readLine()) != null) {
                words.add(line);
            }

            // Close the file
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    //EFFECTS: initializes graphics & initializes difficulty option pane
    private void initializeGraphics() {
        frame = new JFrame("HangmanGui");
        mainPanel = new JPanel();
        hangmanPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(width, height));
        hangmanPanel.setBounds(900, 0, width, height * 3/4);
        wordPanel.setBounds(900, 700, width, height / 4);
        wordPanel.setLayout(null);
        hangmanPanel.setLayout(new FlowLayout());
        frame.setContentPane(mainPanel);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(width, height));

        frame.pack();
        chooseDifficulty();

        mainPanel.add(wordPanel, BorderLayout.PAGE_END);
        mainPanel.repaint();
        wordPanel.setBackground(Color.BLACK);
        hangmanPanel.setVisible(true);
        readWordFile();
        chooseRandomWord();
        wordPanel.setVisible(true);
        mainPanel.setVisible(true);
        mainPanel.repaint();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }


    //EFFECTS: displays option pane for player to choose difficulty; changes length of words used
    private void chooseDifficulty() {
        JButton[] options = new JButton[3];
        options[0] = new JButton("Easy");
        options[0].setBackground(Color.GREEN);
        options[1] = new JButton("Medium");
        options[1].setBackground(Color.YELLOW);
        options[2] = new JButton("Hard");
        options[2].setBackground(Color.RED);
        ActionListener listener = createOptionsListener();

        for (JButton button : options) {
            button.addActionListener(listener);
        }

        JOptionPane pane = new JOptionPane("Choose a difficulty level", JOptionPane.QUESTION_MESSAGE,
                JOptionPane.DEFAULT_OPTION, null, options);
        JDialog dialog = pane.createDialog("Game Options");
        dialog.setVisible(true);


    }

    //EFFECTS: creates ActionListener for difficulty buttons; sets wordFile to use
    private ActionListener createOptionsListener() {
        ActionListener listener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();

                if (source instanceof JButton) {
                    JButton button = (JButton) source;
                    String text = button.getText();

                    switch (text) {
                        case "Easy":
                            wordFile = new File("easyWords.txt");
                            break;
                        case "Medium":
                            wordFile = new File("mediumWords.txt");
                            break;
                        case "Hard":
                            wordFile = new File("hardWords.txt");
                            break;
                    }

                    Window window = SwingUtilities.windowForComponent(button);
                    if (window instanceof JDialog) {
                        JDialog dialog = (JDialog) window;
                        dialog.dispose();
                    }
                }
            }
        };
        return listener;
    }

    private void generateLetterBlocks(int length) {
//        JPanel gapPanel = new JPanel();
//        JPanel secondGapPanel = new JPanel();
//        Dimension gapPanelSize = new Dimension(width, 125);
//        Dimension secondGapPanelSize = new Dimension(width, 75);
//        gapPanel.setSize(gapPanelSize);
//        secondGapPanel.setSize(secondGapPanelSize);
//        JPanel letterPanel = new JPanel();
//        Dimension size = new Dimension(width, 200);
//        letterPanel.setSize(size);
//        letterPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        for (int i = 0; i < length; i++) {
            wordPanel.add(new LetterBlocks(width, height, length, i));
        }
//        letterPanel.setVisible(true);
//        gapPanel.setVisible(true);
//        secondGapPanel.setVisible(true);
//        wordPanel.add(gapPanel);
//        wordPanel.add(secondGapPanel);

    }
}
