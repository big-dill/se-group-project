package se.uog.table;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
 * A singleton class which displays a pop-up dialog for selecting elements from a DefaultListModel.
 *
 * This class is adapted from the Swing tutorial for ListDialog:
 * https://docs.oracle.com/javase/tutorial/uiswing/examples/components/ListDialogRunnerProject/src/components/ListDialog.java
 */

@SuppressWarnings("serial")
public class ListSelectorDialog extends JDialog {

    private static ListSelectorDialog dialog;
    private static List<?> selection;
    private DefaultListModel<?> listModel;
    private JList<?> listView;

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

        // Toggle selection on each item in the view
        listView.clearSelection();
        Iterator iterator = selection.iterator();
        while (iterator.hasNext()) {
            int index = listModel.indexOf(iterator.next());
            if (index >= 0) {
                listView.addSelectionInterval(index, index);
            }
        }
    }

    private ListSelectorDialog(Frame frame, String dialogTitle, DefaultListModel<?> listModel,
            List<?> initalSelection) {

        super(frame, dialogTitle, true);
        this.listModel = listModel;

        setupButtons();
        setupListView();
        setSelection(initalSelection);
        pack();
    }

    private void setupButtons() {
        // Create and initialize the buttons.

        // Clear selection button
        JButton clearButton = new JButton("Clear Selection");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setSelection(new ArrayList<>());
            }
        });

        // Set selection button
        final JButton setButton = new JButton("Set");
        setButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Set the selection and close.
                ListSelectorDialog.selection = listView.getSelectedValuesList();
                ListSelectorDialog.dialog.setVisible(false);
            }
        });

        // The default button is 'Set'
        getRootPane().setDefaultButton(setButton);

        // Lay out the buttons from left to right.
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        buttonPane.add(Box.createHorizontalGlue());
        buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonPane.add(setButton);
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
    }

    private void setupListView() {
        // main part of the dialog
        listView = new JList(listModel);

        // Allows single click (de)selection of a list
        // Code taken from:
        // https://stackoverflow.com/questions/2528344/jlist-deselect-when-clicking-an-already-selected-item
        listView.setSelectionModel(new DefaultListSelectionModel() {

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
                if (!isAdjusting) {
                    gestureStarted = false;
                }
            }
        });
        // End code.

        // Allow multiple selections and set the view to be vertical.
        listView.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        listView.setLayoutOrientation(JList.VERTICAL);

        // Add a list scroller for the list.
        JScrollPane listScroller = new JScrollPane(listView);
        listScroller.setPreferredSize(new Dimension(250, 80));

        // Attach to a panel.
        JPanel listPane = new JPanel();
        listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));
        listPane.add(listScroller);
        listPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        getContentPane().add(listPane, BorderLayout.CENTER);
    }
}
