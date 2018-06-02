/*
 * Programming by: George <GeorgeNiceWorld@gmail.com>
 * Copyright (C) George And George Companies to Working For, All Rights Reserved.
 */

/*
 * ChangeLogPanel.java
 *
 * Created on 2011-8-15, 15:54:40
 */
package mainentry;

import basictoolkit.annotation.JdbcCommentItem;
import basictoolkit.annotation.JdbcComments;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/**
 *
 * @author George <GeorgeNiceWorld@gmail.com>
 */
public class BasicToolkitChangeLogPanel extends javax.swing.JPanel {

    private Class<?> clazz;

    /** Creates new form ChangeLogPanel */
    public BasicToolkitChangeLogPanel(Class<?> clazz) {
        this.clazz = clazz;
        initComponents();
        initTable();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        changeLogTableScrollPane = new javax.swing.JScrollPane();
        changeLogTable = new javax.swing.JTable();

        setLayout(new java.awt.BorderLayout());

        changeLogTable.setModel(getTableModel());
        changeLogTableScrollPane.setViewportView(changeLogTable);

        add(changeLogTableScrollPane, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    public DefaultTableModel getTableModel() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("时间");
        model.addColumn("版本");
        model.addColumn("记录内容");

        JdbcComments commentsAnnotation = clazz.getAnnotation(JdbcComments.class);
        if (commentsAnnotation != null) {
            JdbcCommentItem[] commentItems = commentsAnnotation.value();
            if (commentItems != null) {
                for (JdbcCommentItem item : commentItems) {
                    StringBuffer content = new StringBuffer();
                    String[] notes = item.notes();
                    for (String note : notes) {
                        content.append(">> ").append(note).append(System.getProperty("line.separator"));
                    }

                    model.addRow(new String[]{item.dateTime(), item.version(), content.toString()});
                }
            }
        }

        return model;
    }

    private void initTable() {
        this.changeLogTable.setDefaultRenderer(Object.class, new TextAreaTableCellRenderer());

        //设定列宽
        TableColumn datatimeColumn = this.changeLogTable.getColumnModel().getColumn(0);
        datatimeColumn.setPreferredWidth(129);
        datatimeColumn.setMaxWidth(129);
        datatimeColumn.setMinWidth(129);

        TableColumn versionColumn = this.changeLogTable.getColumnModel().getColumn(1);
        versionColumn.setPreferredWidth(60);
        versionColumn.setMaxWidth(60);
        versionColumn.setMinWidth(60);

        TableColumn noteColumn = this.changeLogTable.getColumnModel().getColumn(2);
        noteColumn.setPreferredWidth(300);
//        noteColumn.setMaxWidth(300);
//        noteColumn.setMinWidth(300);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable changeLogTable;
    private javax.swing.JScrollPane changeLogTableScrollPane;
    // End of variables declaration//GEN-END:variables

    /**
     * 定义个可以让表格文本换行的单元格渲染器
     */
    private class TextAreaTableCellRenderer extends JTextArea implements TableCellRenderer {

        public TextAreaTableCellRenderer() {
            setLineWrap(true);
            setWrapStyleWord(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

            // 计算当下行的最佳高度
            int maxPreferredHeight = 0;
            for (int i = 0; i < table.getColumnCount(); i++) {
                setText("" + table.getValueAt(row, i));
                setSize(table.getColumnModel().getColumn(column).getWidth(), 60);
                maxPreferredHeight = Math.max(maxPreferredHeight, getPreferredSize().height);
            }

            //此行是关键
            if (table.getRowHeight(row) != maxPreferredHeight) {
                table.setRowHeight(row, maxPreferredHeight);
            }

            setText(value == null ? "" : value.toString());
            return this;
        }
    }
}
