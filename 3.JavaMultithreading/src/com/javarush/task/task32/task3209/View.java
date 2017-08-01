package com.javarush.task.task32.task3209;

import com.javarush.task.task32.task3209.listeners.FrameListener;
import com.javarush.task.task32.task3209.listeners.TabbedPaneChangeListener;
import com.javarush.task.task32.task3209.listeners.UndoListener;

import javax.swing.*;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame implements ActionListener {
    private Controller controller;
    private JTabbedPane tabbedPane = new JTabbedPane();
    private JTextPane htmlTextPane = new JTextPane();
    private JEditorPane plainTextPane = new JEditorPane();
    private UndoManager undoManager = new UndoManager();
    private UndoListener undoListener = new UndoListener(undoManager);

    public Controller getController() {
        return controller;
    }

    public UndoListener getUndoListener() {
        return undoListener;
    }

    public View() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            ExceptionHandler.log(e);
        } catch (InstantiationException e) {
            ExceptionHandler.log(e);
        } catch (IllegalAccessException e) {
            ExceptionHandler.log(e);
        } catch (UnsupportedLookAndFeelException e) {
            ExceptionHandler.log(e);
        }
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        switch (actionEvent.getActionCommand()){
            case "Новый" : controller.createNewDocument();
            case "Открыть" : controller.openDocument();
            case "Сохранить" : controller.saveDocument();
            case "Сохранить как..." : controller.saveDocumentAs();
            case "Выход" : controller.exit();
            case "О программе" : showAbout();
        }
    }

    public void init() {
        initGui();
        addWindowListener(new FrameListener(this));
        setVisible(true);
    }

    public void exit() {
        controller.exit();
    }

    public void initMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        MenuHelper.initFileMenu(this, menuBar);
        MenuHelper.initEditMenu(this, menuBar);
        MenuHelper.initStyleMenu(this, menuBar);
        MenuHelper.initAlignMenu(this, menuBar);
        MenuHelper.initColorMenu(this, menuBar);
        MenuHelper.initFontMenu(this, menuBar);
        MenuHelper.initHelpMenu(this, menuBar);
        this.getContentPane().add(menuBar, BorderLayout.NORTH);
    }

    public void initEditor() {
        htmlTextPane.setContentType("text/html");
        JScrollPane jScrollPaneHtml = new JScrollPane(htmlTextPane);
        tabbedPane.addTab("HTML", jScrollPaneHtml);
        JScrollPane jScrollPaneText = new JScrollPane(plainTextPane);
        tabbedPane.addTab("Текст", jScrollPaneText);
        tabbedPane.setPreferredSize(new Dimension(1000, 800));
        tabbedPane.addChangeListener(new TabbedPaneChangeListener(this));
        this.getContentPane().add(tabbedPane, BorderLayout.CENTER);
    }

    public void initGui() {
        initEditor();
        initMenuBar();
        pack();
    }

    public void selectedTabChanged() {
        if(isHtmlTabSelected()){
            controller.setPlainText(plainTextPane.getText());
        }else{
            plainTextPane.setText(controller.getPlainText());
        }
        resetUndo();
    }

    public boolean canUndo() {
        if (undoManager.canUndo()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean canRedo() {
        if (undoManager.canRedo()) {
            return true;
        } else {
            return false;
        }
    }

    public void undo() {
        try {
            undoManager.undo();
        } catch (CannotUndoException e) {
            ExceptionHandler.log(e);
        }
    }

    public void redo() {
        try {
            undoManager.redo();
        } catch (CannotRedoException e) {
            ExceptionHandler.log(e);
        }
    }

    public void resetUndo() {
        undoManager.discardAllEdits();
    }

    public boolean isHtmlTabSelected(){
        if(tabbedPane.getSelectedIndex()!=0){return true;}else{return false;}
    }

    public void selectHtmlTab(){
        tabbedPane.setSelectedIndex(0);
        resetUndo();
    }

    public void update(){ htmlTextPane.setDocument(controller.getDocument());
    }

    public void showAbout(){
        JOptionPane.showMessageDialog(null, "info", "info", JOptionPane.INFORMATION_MESSAGE);
    }
}
