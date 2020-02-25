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
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;


// TODO: Bug fix on cancel return value
/**
 * A singleton class which displays a pop-up dialog for selecting elements from a DefaultListModel.
 */
public class ListSelectorDialog extends JDialog implements ActionListener {

    /**
     * Default serial.
     */
    private static final long serialVersionUID = 1L;

    private static ListSelectorDialog dialog;
    private static List<?> selection;
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

        list.clearSelection();

        Iterator<?> iterator = selection.iterator();

        while (iterator.hasNext()) {
            list.setSelectedValue(iterator.next(), false);
        }

    }

    private ListSelectorDialog(Frame frame, String dialogTitle, DefaultListModel<?> listModel,
            List<?> initalSelection) {
        super(frame, dialogTitle, true);

        // Create and initialize the buttons.
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);
        //
        final JButton setButton = new JButton("Set");
        setButton.setActionCommand("Set");
        setButton.addActionListener(this);
        getRootPane().setDefaultButton(setButton);

        // main part of the dialog
        list = new JList(listModel);

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
