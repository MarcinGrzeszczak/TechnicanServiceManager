package mg.frontend.gui.customcomponents;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TableModel extends AbstractTableModel {

    private String[] columns;
    private String[][] data;

    public TableModel(Map<String,String> tableMap) {
        this.columns = tableMap.keySet().toArray(new String[0]);
        this.data = new String[0][0];
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        columnIndex += 1;

        if( columnIndex < this.data[0].length-1)
            return this.data[rowIndex][columnIndex];

        return null;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    public void refresh(List<List<String>> data) {
        this.data = data.stream()
                .map(row-> row.toArray(new String[0]))
                .collect(Collectors.toList())
                .toArray(new String[0][0]);

        this.fireTableDataChanged();
    }

    public int getSelectionId(int row) {
        if(row >= 0)
            return Integer.parseInt(this.data[row][0]);
        return row;
    }
}
