package se.uog.table;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

/**
 * A singleton class which displays a pop-up dialog for selecting elements from
 * a DefaultListModel.
 */
public class ListSelectorDialog extends JDialog implements ActionListener {
    private static final long serialVersionUID = 1L;

    private static ListSelectorDialog dialog;
    private static List<?> selection;
    private DefaultListModel<?> listModel;
    private JList<?> list;

    public static List<?> showDialog(Component owner, String dialogTitle, DefaultListModel<?> model,
            List<?> initalSelection) {

        // Get the frame to attach the dialog to.
        Frame frame = JOptionPane.getFrameForComponent(owner);

        dialog = new ListSelectorDialog(frame, dialogTitle, model, initalSelection);
        dialog.setVisible(true);
        return selection;
    }

    private void setSelection(List<?> selection) {
        // Set the current selection
        ListSelectorDialog.selection = selection;

        // Update the view accordingly
        list.clearSelection();

        // Toggle selection on each item
        Iterator iterator = selection.iterator();
        while (iterator.hasNext()) {
            int index = listModel.indexOf(iterator.next());
            if (index >= 0) {
                list.addSelectionInterval(index, index);
            }
        }
    }

    private ListSelectorDialog(Frame frame, String dialogTitle, DefaultListModel<?> listModel,
            List<?> initalSelection) {
        super(frame, dialogTitle, true);

        this.listModel = listModel;

        // Create and initialize the buttons.
        JButton cancelButton = new JButton("Clear");
        cancelButton.addActionListener(this);
        //
        final JButton setButton = new JButton("Set");
        setButton.setActionCommand("Set");
        setButton.addActionListener(this);
        getRootPane().setDefaultButton(setButton);

        // main part of the dialog
        list = new JList(listModel);

        // Code taken from:
        // Allows single click (de)selection of a list
        // https://stackoverflow.com/questions/2528344/jlist-deselect-when-clicking-an-already-selected-item
        list.setSelectionModel(new DefaultListSelectionModel() {

            boolean gestureStarted = false;

            @Override
            public void setSelectionInterval(int index0, int index1) {
                if (!gestureStarted) {
                    if (isSelectedIndex(index0)) {
                        super.removeSelectionInterval(index0, index1);
                    } else {
                        super.addSelectionInterval(index0, index1);
                    }
                }
                gestureStarted = true;
            }

            @Override
            public void setValueIsAdjusting(boolean isAdjusting) {
                if (isAdjusting == false) {
                    gestureStarted = false;
                }
            }
        });
        // End code from source

        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);

        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(250, 80));

        JPanel listPane = new JPanel();
        listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));
        listPane.add(listScroller);
        listPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Lay out the buttons from left to right.
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        buttonPane.add(Box.createHorizontalGlue());
        buttonPane.add(cancelButton);
        buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonPane.add(setButton);

        // Put everything together, using the content pane's BorderLayout.
        Container contentPane = getContentPane();
        contentPane.add(listPane, BorderLayout.CENTER);
        contentPane.add(buttonPane, BorderLayout.SOUTH);

        setSelection(initalSelection);
        pack();
    }

    // Handle clicks on the Set and Cancel buttons.
    public void actionPerformed(ActionEvent e) {
        if ("Set".equals(e.getActionCommand())) {
            ListSelectorDialog.selection = list.getSelectedValuesList();
        }

        ListSelectorDialog.dialog.setVisible(false);
    }
}
