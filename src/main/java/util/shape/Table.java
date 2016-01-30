package util.shape;

import javax.swing.JTable;

import configuration.uiConfiguration.Dimension;
import configuration.uiConfiguration.Format;

public class Table {

    @SuppressWarnings("deprecation")
    public static JTable table(Dimension dim, int fontSize, int row, int col, boolean enable, boolean user) {
        String[] colName = new String[col];
        String[][] data = new String[row][col];
        for (int i = 0; i < col; ++i)
            colName[i] = "";
        for (int i = 0; i < row; ++i)
            for (int j = 0; j < col; ++j)
                data[i][j] = "";
        JTable table;
        if (user)
            table = new JTable(data, colName) {
                private static final long serialVersionUID = 1L;

                public boolean isCellEditable(int r, int c) {
                    return (c % 2 == 1);
                }
            };
        else {
            table = new JTable(data, colName);
            if (!enable)
                table.disable();
        }
        table.setBounds(dim.getX(), dim.getY(), dim.getW(), dim.getH());
        table.setRowHeight(20);
        table.setFont(Format.getFont(fontSize));
        table.applyComponentOrientation(Format.getArabicLanguage());
        return table;
    }
}
