import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ToDoApp {

    private JFrame frame;
    private DefaultListModel<String> taskListModel;
    private JList<String> taskList;
    private JTextField taskInputField;

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

        // Add buttons to the panel
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);

        // Add components to the frame
        frame.add(taskScrollPane, BorderLayout.CENTER);
        frame.add(taskInputField, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.SOUTH);

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
            }
        }
    }

    public static void main(String[] args) {
        // Run the app
        SwingUtilities.invokeLater(() -> new ToDoApp());
    }
}
