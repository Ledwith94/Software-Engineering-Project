package components;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.SwingUtilities;

public class GUI extends JPanel
        implements ActionListener {
    static private final String newline = "\n";
    JButton openButton, closeButton, allocateButton, showStudentsButton, getStudEntryButton;
    JTextArea log;
    JFileChooser fc;
    String fileName;
    Boolean fileOpen = false;

    public GUI() {
        super(new BorderLayout());
        log = new JTextArea(5, 20);
        log.setMargin(new Insets(100, 100, 100, 100));
        log.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(log);

        fc = new JFileChooser();

        openButton = new JButton("Open a File...");
        openButton.addActionListener(this);

        closeButton = new JButton("Close");
        closeButton.addActionListener(this);


        allocateButton = new JButton("Allocate");
        allocateButton.addActionListener(this);

        showStudentsButton = new JButton("Show Students List");
        showStudentsButton.addActionListener(this);

        getStudEntryButton = new JButton("Show Student Entry For...");
        getStudEntryButton.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(openButton);
        buttonPanel.add(allocateButton);
        buttonPanel.add(showStudentsButton);
        buttonPanel.add(getStudEntryButton);
        buttonPanel.add(closeButton);

        add(buttonPanel, BorderLayout.PAGE_START);
        add(logScrollPane, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e) {

        PreferenceTable prefs = null;

        if (e.getSource() == openButton) {
            int returnVal = fc.showOpenDialog(GUI.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                fileName = file.getAbsolutePath();
                fileOpen = true;
                log.append(file.getAbsolutePath() + " opened successfully." + newline);

            } else {
                log.append("Open command cancelled by user." + newline);
            }
        } else if (e.getSource() == closeButton) {
            System.exit(0);
        }
        log.setCaretPosition(log.getDocument().getLength());

        if (fileOpen) {
            if (e.getSource() == allocateButton) {
                try {
                    this.algorithms();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            } else if (e.getSource() == showStudentsButton) {
                try {
                    prefs = new PreferenceTable(fileName);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                log.append(prefs.getAllStudentEntries() + newline);

            } else if (e.getSource() == getStudEntryButton) {
                try {
                    prefs = new PreferenceTable(fileName);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                String sName = (String) JOptionPane.showInputDialog("Enter Student name:\n");
                if (prefs.getEntryFor(sName) == null) {
                    log.append("Not a valid student name" + newline);
                } else {
                    log.append(prefs.getEntryFor(sName) + newline);
                }
            }
        } else {
            log.append("A file must be opened before allocation." + newline);
        }
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Student Project Allocator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(new GUI());

        frame.pack();
        frame.setVisible(true);
    }

    public void algorithms() throws IOException {
        PreferenceTable prefs = new PreferenceTable(fileName);
        prefs.fillPreferencesOfAll(10);

        CandidateSolution best = new CandidateSolution(prefs);                // initialize a random solution
        best.calcTotalEnergy();

        log.append("\nInitial solution Energy = " + best.getEnergy() + "\nThis solution has " + best.getProjAllocEnergy() + " allocation energy and " + (best.getEnergy() - best.getProjAllocEnergy()) + " choice energy\n");

        int cont = 0;
        int SAchoice = 0;
        int choice = 0;

        while (cont != 1) {
            String[] options = new String[]{"Genetic Algorithm", "Simulated Annealing"};
            int picked = JOptionPane.showOptionDialog(null, "Select Genetic Algorithm or Simulated Annealing", "Pick what method you wish to use",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, options, options[0]);

            if (picked == 0) {
                GeneticAlgorithm GA = new GeneticAlgorithm(prefs);
                best = GA.mate();
                log.append("\n\n\tThe resulting solution was Energy = " + best.getEnergy() + "\nThis solution has " + best.getProjAllocEnergy() + " allocation energy and " + (best.getEnergy() - best.getProjAllocEnergy()) + " choice energy\n");
            } else if (picked == 1) {
                while (SAchoice != 1) {
                    log.append(best.SimulatedAnnealing());

                    log.append("\nBest Solution Energy generated = " + best.getEnergy() + "\nThis solution has " + best.getProjAllocEnergy() + " allocation energy and " + (best.getEnergy() - best.getProjAllocEnergy()) + " choice energy\n");

                    SAchoice = JOptionPane.showConfirmDialog(null,
                            "Run Simulated Annealing again ?", "Do you want to run again ?", JOptionPane.YES_NO_OPTION);

                    if (SAchoice == 1) {
                        break;
                    }
                }
            } else {
                log.append("Error, try again");
            }

            cont = JOptionPane.showConfirmDialog(null,
                    "Run Simulated Annealing or Genetic Algorithm again ?", "Do you want to run Simulated Annealing or Genetic Algorithm again ?", JOptionPane.YES_NO_OPTION);
        }

        if (best.getProjAllocEnergy() != 0) {
            choice = JOptionPane.showConfirmDialog(null,
                    "There are collisions in this solution ?", "Do you want to remove the collisions ?", JOptionPane.YES_NO_OPTION);

            if (choice == 0) {
                log.append("\n\t\tRemoving Collisions\n");
                best.removeCollisions();
            }
        }

        log.append("\n\n\tThe Overall Best solution was Energy = " + best.getEnergy() + "\nThis solution has " + best.getProjAllocEnergy() + " allocation energy and " + (best.getEnergy() - best.getProjAllocEnergy()) + " choice energy\n");

        log.append("\nGenerating results.tsv in the same folder as the program ...\n");

        best.toFile();
        choice = JOptionPane.showConfirmDialog(null,
                "There are collisions in this solution ?", "Do you want to remove the collisions ?", JOptionPane.YES_NO_OPTION);
        if(choice == 0){
            log.append(newline + best.toString() + newline);
        }

    }

    public static void main(String[] args) throws IOException {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                createAndShowGUI();
            }
        });
    }
}