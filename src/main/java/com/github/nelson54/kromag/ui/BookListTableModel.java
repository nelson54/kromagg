package com.github.nelson54.kromag.ui;

import com.github.nelson54.kromag.AudioFile;
import com.github.nelson54.kromag.LibraryService;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.List;

public class BookListTableModel implements TableModel {

    List<String> columns;
    List<AudioFile> books;

    TableModelListener tableModelListener;


    public BookListTableModel(LibraryService libraryService) {
        columns = new ArrayList<>();
        books = new ArrayList<>();
        columns.add("Title");
        columns.add("Track");
        columns.add("File");
        books.addAll(libraryService.getFiles());
    }

    @Override
    public int getRowCount() {
        return books.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columns.get(columnIndex);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        AudioFile file = books.get(rowIndex);
        String value = null;

        switch (columnIndex) {

            case 0:
                value = file.getTrack();
                break;
            case 1:
                value = file.getTitle();
                break;
            case 2:
                value = file.getArtist();
                break;
        }

        return value;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        tableModelListener = l;
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        tableModelListener = null;
    }

    public List<AudioFile> getBooks() {
        return books;
    }

    public void setBooks(List<AudioFile> books) {
        this.books = books;
    }
}
