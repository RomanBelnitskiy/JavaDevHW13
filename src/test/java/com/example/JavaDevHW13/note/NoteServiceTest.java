package com.example.JavaDevHW13.note;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class NoteServiceTest {
    private NoteService service;

    @BeforeEach
    void setUp() {
        service = new NoteService();
    }

    @Test
    @DisplayName("Method add sets id value to note")
    void addNewNotes_SetsRightIdToNote() {
        Note note1 = Note.builder()
                .title("Note title")
                .content("Note body")
                .build();
        Note note2 = Note.builder()
                .title("Note title")
                .content("Note body")
                .build();

        service.add(note1);
        service.add(note2);

        assertEquals(1L, note1.getId());
        assertEquals(2L, note2.getId());
    }

    @Test
    @DisplayName("Method add throws NPE when note is null")
    void addNewNote_ThrowsException_IfNoteIsNull() {
        assertThrows(NullPointerException.class, () -> service.add(null));
    }

    @Test
    @DisplayName("Method listAll returns list with two notes")
    void listAllNotes_ReturnListWithTwoNotes() {
        Note note1 = Note.builder()
                .title("Note1 title")
                .content("Note1 body")
                .build();
        Note note2 = Note.builder()
                .title("Note2 title")
                .content("Note2 body")
                .build();

        service.add(note1);
        service.add(note2);

        List<Note> notes = service.listAll();

        assertEquals(2, notes.size());
    }

    @Test
    @DisplayName("Method getById returns right note")
    void getById_ReturnsRightNote() {
        Note note1 = Note.builder()
                .title("Note1 title")
                .content("Note1 body")
                .build();

        service.add(note1);

        Note note = service.getById(note1.getId());

        assertEquals(note1, note);
    }

    @Test
    @DisplayName("Method getById throws NPE when id is null")
    void getById_ThrowsExceptionWhenIdIsNull() {
        assertThrows(NullPointerException.class, () -> service.getById(null));
    }

    @Test
    @DisplayName("Method deleteById removes one of two notes")
    void deleteById_RemovesOneOfTwoNotes() {
        Note note1 = Note.builder()
                .title("Note1 title")
                .content("Note1 body")
                .build();
        Note note2 = Note.builder()
                .title("Note2 title")
                .content("Note2 body")
                .build();

        service.add(note1);
        service.add(note2);

        service.deleteById(note1.getId());

        List<Note> notes = service.listAll();

        assertEquals(1, notes.size());
    }

    @Test
    @DisplayName("Method deleteById throws NPE when id is null")
    void deleteById_ThrowsExceptionWhenIdIsNull() {
        assertThrows(NullPointerException.class, () -> service.deleteById(null));
    }

    @Test
    @DisplayName("Method deleteById throws NSEE when there was no element with such id in map")
    void deleteById_ThrowsExceptionWhenNoNoteWithSuchId() {
        assertThrows(NoSuchElementException.class, () -> service.deleteById(1L));
    }

        @Test
    @DisplayName("Method update saves right note")
    void update_SavesRightNote() {
        Note note1 = Note.builder()
                .title("Note1 title")
                .content("Note1 body")
                .build();

        service.add(note1);

        Note note = service.getById(note1.getId());
        note.setTitle("New note title");
        note.setContent("New note content");

        service.update(note);

        Note updatedNote = service.getById(note.getId());

        assertEquals(1, updatedNote.getId());
        assertEquals("New note title", updatedNote.getTitle());
        assertEquals("New note content", updatedNote.getContent());
    }

    @Test
    @DisplayName("Method update throws NPE when note is null")
    void update_ThrowsExceptionWhenNoteIsNull() {
        assertThrows(NullPointerException.class, () -> service.update(null));
    }

    @Test
    @DisplayName("Method update throws NPE when notes id is null")
    void update_ThrowsExceptionWhenNotesIdIsNull() {
        Note note = Note.builder()
                .title("Note title")
                .content("Note body")
                .build();
        assertThrows(NullPointerException.class, () -> service.update(note));
    }

    @Test
    @DisplayName("Method update throws NSEE when there is no such note in map")
    void update_ThrowsExceptionWhenNoteIsAbsent() {
        Note note = Note.builder()
                .id(1L)
                .title("Note title")
                .content("Note body")
                .build();
        assertThrows(NoSuchElementException.class, () -> service.update(note));
    }
}