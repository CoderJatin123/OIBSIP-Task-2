package com.example.todoapp.Model;

public class Todo {
    private String title, note;
    private boolean isChecked=false;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public Todo(String title, String note, boolean isChecked) {
        this.title = title;
        this.note = note;
        this.isChecked = isChecked;
    }
}
