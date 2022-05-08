package org.boothverse.foodpants.ui.pages;

import org.boothverse.foodpants.business.services.exceptions.PantsNotFoundException;
import org.boothverse.foodpants.persistence.FoodInstance;
import org.boothverse.foodpants.business.services.exceptions.PantsExportShoppingListException;
import org.boothverse.foodpants.ui.PageRunner;
import org.boothverse.foodpants.ui.Style;
import org.boothverse.foodpants.ui.components.ShoppingItem;
import org.boothverse.foodpants.ui.components.ItemList;
import org.boothverse.foodpants.ui.components.standard.StandardItem;
import org.boothverse.foodpants.ui.controllers.ShoppingController;
import org.boothverse.foodpants.ui.forms.AddFoodInstanceForm;
import org.boothverse.foodpants.ui.forms.EditFoodInstanceForm;
import org.boothverse.foodpants.ui.forms.StandardForm;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionListener;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class ShoppingPage extends Page {
    private static final String[] labels = {"+", "Modify", "Export", "Mark All", "New", "Purchase"};

    protected ShoppingController shoppingController = new ShoppingController();

    protected ActionListener sideMenuListener;
    protected ItemList itemDisplay;
    protected Boolean modifying = false;

    public ShoppingPage() {
        super(labels);
        itemDisplay = new ItemList(1, this);
        add(itemDisplay);

        initActionListeners();
        updateList();
    }

    private void initActionListeners() {
        sideMenuListener = e -> {
            if (e.getActionCommand().equals("Mark All")) {
                for (StandardItem listItem : itemDisplay.getItems()) {
                    ((ShoppingItem)listItem).getCheckBox().setSelected(true);
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
                        // TODO: properly handle the exception!
                        try {
                            new ShoppingController().export(Paths.get(path));
                        } catch (PantsExportShoppingListException pantsExportShoppingListException) {
                            pantsExportShoppingListException.printStackTrace();
                        }
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
                itemDisplay.setModifiable(modifying);
            }
            else if (e.getActionCommand().equals("New")) {
                itemDisplay.removeAll();
                shoppingController.removeAllItems();
            }
            else if (e.getActionCommand().equals("+")) {
                StandardForm form = new AddFoodInstanceForm("Add Item", shoppingController, this);
                form.setLocationRelativeTo(this);
                form.setVisible(true);
            }
            else if (e.getActionCommand().equals("Purchase")) {
                List<String> items = new ArrayList<>();
                for (StandardItem listItem : itemDisplay.getItems()) {
                    if (((ShoppingItem)listItem).getCheckBox().getModel().isSelected()) {
                        items.add(listItem.getFoodInstance().getId());
                    }
                }
                try {
                    shoppingController.purchaseItems(items);
                    updateList();
                } catch (PantsNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        };

        sideMenu.buttonMap.forEach((name, button) -> button.addActionListener(sideMenuListener));
    }

    protected void updateList() {
        List<FoodInstance> listItems = shoppingController.getItems();

        itemDisplay.removeAll();
        for (FoodInstance item : listItems) {
            itemDisplay.add(new ShoppingItem(item));
        }
        revalidate();
    }

    @Override
    public void notifyChange(String message, Object oldValue, Object newValue) {
        if (Objects.equals(message, "add")) {
            updateList();
        }
        else if (Objects.equals(message, "remove")) {
            try {
                shoppingController.removeItem(((StandardItem)oldValue).getFoodInstance().getId());
            }
            catch (PantsNotFoundException e) {
                System.out.println("Austin is bad at frontend");
                e.printStackTrace();
            }
        }
        else if (Objects.equals(message, "edit")) {
            StandardForm editItemForm = new EditFoodInstanceForm(((StandardItem) oldValue).getFoodInstance(), shoppingController, this);
            editItemForm.setVisible(true);
        }
        else if (Objects.equals(message, "update")) {
            updateList();
        }
    }
}
