package com.yogeeswar.notes;

public class NoteData {
    private String name, note;

    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public NoteData(String name, String note) {
        this.name = name;
        this.note = note;
    }
}
