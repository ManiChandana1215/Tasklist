import java.awt.event.*;
import javax.swing.*;

public class TaskListApp {
    public static void main(String[] args) {
        //setting frame
        JFrame f = new JFrame();
        f.setSize(1000, 800);
        //creating list to display the entered text
        JLabel jl=new JLabel("double click the item to delete");
        jl.setBounds(750,0,200,20);
        f.add(jl);
        DefaultListModel<String> l1 = new DefaultListModel<>();
        //created button,when clicked, the text in the textfield will be displayed as Jlist item
        JButton b = new JButton("enter string in above textfield and Click me to add new task");
        b.setBounds(50, 600, 400, 50);
        //text field to enter text
        JTextField tf = new JTextField();
        tf.setBounds(10, 20, 700, 550);
        f.add(b);
        f.add(tf);
        //action gets performed when button is clicked
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String s = tf.getText();
                if (!s.isEmpty()) {
                    l1.addElement(s);
                    // Clear the text field after adding the task
                    tf.setText(""); 
                }
            }
        });

        JList<String> taskList = new JList<>(l1);
        //event listener when the item in the list gets clicked 2 times, the item gets clicked.
        taskList.addMouseListener(new DeleteListItemListener(taskList));//DeleteListItemListener() class invokes DeleteListItemListener() constructor

        JScrollPane scrollPane = new JScrollPane(taskList);
        scrollPane.setBounds(730, 20, 200, 550);
        f.add(scrollPane);
        //frame properties
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static class DeleteListItemListener extends MouseAdapter {
        private JList<String> list;
        //constructor
        public DeleteListItemListener(JList<String> list) {
            this.list = list;
        }
        //mouse clicked method is overwritten
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() == 2) { // Double-click
                int index = list.locationToIndex(e.getPoint());//e.getPoint() gets x,y coordinates of the list item.
                //locationToIndex returns cell index close to given coordinate system
                if (index != -1) {
                    DefaultListModel<String> model = (DefaultListModel<String>) list.getModel();
                    model.remove(index);//deleted the item/data item
                }
            }
        }
    }
}
