import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ToDoListGUI extends JFrame {
    private static final String FILENAME = "todo.txt";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final long ONE_DAY_MS = 24 * 60 * 60 * 1000;

    private DefaultListModel<String> taskListModel;
    private JList<String> taskList;
    private JTextField taskField;
    private JTextField deadlineField;
    private JLabel countdownLabel;

    private javax.swing.Timer timer;

    public ToDoListGUI() {
        setTitle("To-Do List");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        JScrollPane scrollPane = new JScrollPane(taskList);
        add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        JLabel taskLabel = new JLabel("Task:");
        taskField = new JTextField();
        JLabel deadlineLabel = new JLabel("Deadline (yyyy-MM-dd):");
        deadlineField = new JTextField();
        JButton addButton = new JButton("Add Task");
        JButton editButton = new JButton("Edit Task");
        JLabel countdownTextLabel = new JLabel("Time remaining:");

        countdownLabel = new JLabel();
        inputPanel.add(taskLabel);
        inputPanel.add(taskField);
        inputPanel.add(deadlineLabel);
        inputPanel.add(deadlineField);
        inputPanel.add(addButton);
        inputPanel.add(editButton);
        inputPanel.add(countdownTextLabel);
        inputPanel.add(countdownLabel);
        add(inputPanel, BorderLayout.SOUTH);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTask();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editTask();
            }
        });

        loadTasks();
        startCountdownTimer();

        setVisible(true);
    }

    private void loadTasks() {
        taskListModel.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                taskListModel.addElement(line);
            }
        } catch (IOException e) {
            // File does not exist or cannot be read, ignore
        }
    }

    private void saveTasks() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME))) {
            for (int i = 0; i < taskListModel.size(); i++) {
                bw.write(taskListModel.getElementAt(i));
                bw.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error occurred while saving tasks.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addTask() {
        String task = taskField.getText().trim();
        String deadlineText = deadlineField.getText().trim();
        if (!task.isEmpty()) {
            if (!deadlineText.isEmpty()) {
                try {
                    DATE_FORMAT.parse(deadlineText);
                } catch (ParseException e) {
                    JOptionPane.showMessageDialog(this, "Invalid date format. Please use yyyy-MM-dd.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                task += " (Due: " + deadlineText + ")";
            }
            taskListModel.addElement(task);
            taskField.setText("");
            deadlineField.setText("");
            saveTasks();
        }
    }

    private void editTask() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex != -1) {
            String editedTask = JOptionPane.showInputDialog(this, "Enter edited task:", taskListModel.getElementAt(selectedIndex));
            if (editedTask != null && !editedTask.isEmpty()) {
                taskListModel.setElementAt(editedTask, selectedIndex);
                saveTasks();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a task to edit.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void startCountdownTimer() {
        timer = new javax.swing.Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateCountdown();
            }
        });
        timer.start();
    }

    private void updateCountdown() {
        for (int i = 0; i < taskListModel.size(); i++) {
            String task = taskListModel.getElementAt(i);
            int dueIndex = task.indexOf("(Due: ");
            if (dueIndex != -1) {
                String deadlineString = task.substring(dueIndex + 6, task.indexOf(")", dueIndex));
                try {
                    Date deadline = DATE_FORMAT.parse(deadlineString);
                    long remainingTimeMs = deadline.getTime() - System.currentTimeMillis();
                    if (remainingTimeMs > 0) {
                        long remainingDays = remainingTimeMs / ONE_DAY_MS;
                        long remainingHours = (remainingTimeMs % ONE_DAY_MS) / (60 * 60 * 1000);
                        long remainingMinutes = (remainingTimeMs % (60 * 60 * 1000)) / (60 * 1000);
                        long remainingSeconds = (remainingTimeMs % (60 * 1000)) / 1000;
                        String countdownText = String.format("%02d:%02d:%02d:%02d", remainingDays, remainingHours, remainingMinutes, remainingSeconds);
                        taskListModel.setElementAt(task.substring(0, dueIndex) + "(Due: " + deadlineString + ") (Time Remaining: " + countdownText + ")", i);
                    } else {
                        taskListModel.setElementAt(task.substring(0, dueIndex) + "(Due: " + deadlineString + ") (Time Expired)", i);
                    }
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ToDoListGUI();
            }
        });
    }
}
