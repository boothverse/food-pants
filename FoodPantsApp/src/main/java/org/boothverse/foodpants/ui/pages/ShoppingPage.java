package org.boothverse.foodpants.ui.pages;

import org.boothverse.foodpants.persistence.FoodInstance;
import org.boothverse.foodpants.ui.PageRunner;
import org.boothverse.foodpants.ui.Style;
import org.boothverse.foodpants.ui.components.ShoppingItem;
import org.boothverse.foodpants.ui.components.standard.StandardItem;
import org.boothverse.foodpants.ui.controllers.ShoppingController;
import org.boothverse.foodpants.ui.forms.AddFoodInstanceForm;
import org.boothverse.foodpants.ui.forms.StandardForm;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;


public class ShoppingPage extends Page {
    private static final String[] labels = {"+", "Modify", "Export", "Mark All", "New"};

    protected JPanel shoppingListDisplay;
    protected List<ShoppingItem> shoppingItems;

    protected ShoppingController shoppingController = new ShoppingController();

    protected ActionListener sideMenuListener;
    protected ActionListener addItemListener;

    protected Boolean modifying = false;

    public ShoppingPage() {
        super(labels);
        shoppingItems = new ArrayList<>();
        initSwing();
        initActionListeners();
        updateList();
    }

    private void initActionListeners() {
        sideMenuListener = e -> {
            if (e.getActionCommand().equals("Mark All")) {
                for (ShoppingItem listItem : shoppingItems) {
                    listItem.getCheckBox().setSelected(true);
                }
            }
            else if (e.getActionCommand().equals("Export")) {
                // create new save dialog
                JFileChooser fc = new JFileChooser();

                FileNameExtensionFilter filter = new FileNameExtensionFilter(".pdf", "pdf");

                fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fc.addChoosableFileFilter(filter);
                fc.setAcceptAllFileFilterUsed(false);

                int result = fc.showSaveDialog(PageRunner.getPageViewer());

                if (result == JFileChooser.APPROVE_OPTION) {
                    if (fc.getFileFilter() == filter) {
                        // adding extension .pdf to the end of the file path
                        String path = fc.getSelectedFile().toPath() + ".pdf";
                        new ShoppingController().export(Paths.get(path));
                    }
                }

            }
            else if (e.getActionCommand().equals("Modify")) {
                JButton modifyBtn = (JButton) e.getSource();
                modifying = !modifying;

                if (modifying) {
                    modifyBtn.setBackground(Style.GREY_0);
                } else {
                    modifyBtn.setBackground(Style.GREY_1);
                }

                for (StandardItem listItem : shoppingItems) {
                    listItem.setModification(modifying);
                }
            }
            else if (e.getActionCommand().equals("New List")) {
                shoppingListDisplay.removeAll();
                shoppingItems.clear();
            }
            else if (e.getActionCommand().equals("+")) {
                StandardForm form = new AddFoodInstanceForm(shoppingController);
                form.setLocationRelativeTo(this);
                form.setVisible(true);
            }
        };

        sideMenu.buttonMap.forEach((name, button) -> button.addActionListener(sideMenuListener));
    }

    private void initSwing() {
        // Setup display for the items
        shoppingListDisplay = new JPanel();
        shoppingListDisplay.setLayout(new BoxLayout(shoppingListDisplay, BoxLayout.PAGE_AXIS));
        shoppingListDisplay.setBackground(Style.TRANSPARENT);

        // Create a flowlayout wrapper so the component will not be resized
        JPanel listWrapper = new JPanel(new FlowLayout());
        add(listWrapper);
        listWrapper.add(shoppingListDisplay);
        listWrapper.setBackground(Style.TRANSPARENT);
    }

    protected void updateList() {
        List<FoodInstance> listItems = shoppingController.getItems();

        shoppingItems.clear();
        shoppingListDisplay.removeAll();
        for (FoodInstance item : listItems) {
            ShoppingItem thisItem = new ShoppingItem(item);
            shoppingItems.add(thisItem);
            shoppingListDisplay.add(thisItem);
        }
        revalidate();
    }

    @Override
    public void notifyPage() {
        updateList();
    }
}
