package com.maximo;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class CharacterTableModel extends AbstractTableModel {
    private final String[] columnNames = { "ID", "Nombre", "Estado", "Especie" };
    private List<Character> characters = new ArrayList<>();

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return characters.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Character character = characters.get(rowIndex);
        switch (columnIndex) {
            case 0: return character.getId();
            case 1: return character.getName();
            case 2: return character.getStatus();
            case 3: return character.getSpecies();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}
