import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class ToDoApp {

    private JFrame frame;
    private DefaultListModel<String> taskListModel;
    private JList<String> taskList;
    private JTextField taskInputField;
    private JButton editButton, clearButton;

    public ToDoApp() {
        // Initialize the frame
        frame = new JFrame("To-Do List App");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Initialize the task list model and the task list
        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Scroll pane for the task list
        JScrollPane taskScrollPane = new JScrollPane(taskList);

        // Input field for adding new tasks
        taskInputField = new JTextField();
        taskInputField.addActionListener(new AddTaskListener());

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        // Add Task button
        JButton addButton = new JButton("Add Task");
        addButton.addActionListener(new AddTaskListener());

        // Remove Task button
        JButton removeButton = new JButton("Remove Task");
        removeButton.addActionListener(new RemoveTaskListener());

        // Edit Task button
        editButton = new JButton("Edit Task");
        editButton.addActionListener(new EditTaskListener());

        // Clear All Tasks button
        clearButton = new JButton("Clear All Tasks");
        clearButton.addActionListener(new ClearAllTasksListener());

        // Add buttons to the panel
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(editButton);
        buttonPanel.add(clearButton);

        // Add components to the frame
        frame.add(taskScrollPane, BorderLayout.CENTER);
        frame.add(taskInputField, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Load tasks from file
        loadTasks();

        // Make the frame visible
        frame.setVisible(true);
    }

    // Action listener for adding tasks
    private class AddTaskListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String task = taskInputField.getText().trim();
            if (!task.isEmpty()) {
                taskListModel.addElement(task);
                taskInputField.setText("");
                saveTasks();
            }
        }
    }

    // Action listener for removing selected tasks
    private class RemoveTaskListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedIndex = taskList.getSelectedIndex();
            if (selectedIndex != -1) {
                taskListModel.remove(selectedIndex);
                saveTasks();
            }
        }
    }

    // Action listener for editing selected tasks
    private class EditTaskListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedIndex = taskList.getSelectedIndex();
            if (selectedIndex != -1) {
                String task = taskInputField.getText().trim();
                if (!task.isEmpty()) {
                    taskListModel.set(selectedIndex, task);
                    taskInputField.setText("");
                    saveTasks();
                }
            }
        }
    }

    // Action listener for clearing all tasks
    private class ClearAllTasksListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            taskListModel.clear();
            saveTasks();
        }
    }

    // Method to load tasks from a file
    private void loadTasks() {
        try (BufferedReader br = new BufferedReader(new FileReader("tasks.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                taskListModel.addElement(line);
            }
        } catch (IOException e) {
            // Handle exception (file may not exist yet)
        }
    }

    // Method to save tasks to a file
    private void saveTasks() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("tasks.txt"))) {
            for (int i = 0; i < taskListModel.size(); i++) {
                bw.write(taskListModel.get(i));
                bw.newLine();
            }
        } catch (IOException e) {
            // Handle exception
        }
    }

    public static void main(String[] args) {
        // Run the app
        SwingUtilities.invokeLater(() -> new ToDoApp());
    }
}
