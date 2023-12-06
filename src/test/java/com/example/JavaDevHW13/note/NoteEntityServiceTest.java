package com.example.JavaDevHW13.note;

import com.example.JavaDevHW13.data.entity.NoteEntity;
import com.example.JavaDevHW13.service.service.NoteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class NoteEntityServiceTest {
    private NoteService service;

    @BeforeEach
    void setUp() {
        service = new NoteService();
    }

    @Test
    @DisplayName("Method add sets id value to entity")
    void addNewNotes_SetsRightIdToNote() {
        NoteEntity noteEntity1 = NoteEntity.builder()
                .title("Note title")
                .content("Note body")
                .build();
        NoteEntity noteEntity2 = NoteEntity.builder()
                .title("Note title")
                .content("Note body")
                .build();

        service.add(noteEntity1);
        service.add(noteEntity2);

        assertEquals(1L, noteEntity1.getId());
        assertEquals(2L, noteEntity2.getId());
    }

    @Test
    @DisplayName("Method add throws NPE when entity is null")
    void addNewNote_ThrowsException_IfNoteIsNull() {
        assertThrows(NullPointerException.class, () -> service.add(null));
    }

    @Test
    @DisplayName("Method listAll returns list with two notes")
    void listAllNotes_ReturnListWithTwoNotes() {
        NoteEntity noteEntity1 = NoteEntity.builder()
                .title("Note1 title")
                .content("Note1 body")
                .build();
        NoteEntity noteEntity2 = NoteEntity.builder()
                .title("Note2 title")
                .content("Note2 body")
                .build();

        service.add(noteEntity1);
        service.add(noteEntity2);

        List<NoteEntity> noteEntities = service.listAll();

        assertEquals(2, noteEntities.size());
    }

    @Test
    @DisplayName("Method getById returns right entity")
    void getById_ReturnsRightNote() {
        NoteEntity noteEntity1 = NoteEntity.builder()
                .title("Note1 title")
                .content("Note1 body")
                .build();

        service.add(noteEntity1);

        NoteEntity noteEntity = service.getById(noteEntity1.getId());

        assertEquals(noteEntity1, noteEntity);
    }

    @Test
    @DisplayName("Method getById throws NPE when id is null")
    void getById_ThrowsExceptionWhenIdIsNull() {
        assertThrows(NullPointerException.class, () -> service.getById(null));
    }

    @Test
    @DisplayName("Method deleteById removes one of two notes")
    void deleteById_RemovesOneOfTwoNotes() {
        NoteEntity noteEntity1 = NoteEntity.builder()
                .title("Note1 title")
                .content("Note1 body")
                .build();
        NoteEntity noteEntity2 = NoteEntity.builder()
                .title("Note2 title")
                .content("Note2 body")
                .build();

        service.add(noteEntity1);
        service.add(noteEntity2);

        service.deleteById(noteEntity1.getId());

        List<NoteEntity> noteEntities = service.listAll();

        assertEquals(1, noteEntities.size());
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
    @DisplayName("Method update saves right entity")
    void update_SavesRightNote() {
        NoteEntity noteEntity1 = NoteEntity.builder()
                .title("Note1 title")
                .content("Note1 body")
                .build();

        service.add(noteEntity1);

        NoteEntity noteEntity = service.getById(noteEntity1.getId());
        noteEntity.setTitle("New entity title");
        noteEntity.setContent("New entity content");

        service.update(noteEntity);

        NoteEntity updatedNoteEntity = service.getById(noteEntity.getId());

        assertEquals(1, updatedNoteEntity.getId());
        assertEquals("New entity title", updatedNoteEntity.getTitle());
        assertEquals("New entity content", updatedNoteEntity.getContent());
    }

    @Test
    @DisplayName("Method update throws NPE when entity is null")
    void update_ThrowsExceptionWhenNoteIsNull() {
        assertThrows(NullPointerException.class, () -> service.update(null));
    }

    @Test
    @DisplayName("Method update throws NPE when notes id is null")
    void update_ThrowsExceptionWhenNotesIdIsNull() {
        NoteEntity noteEntity = NoteEntity.builder()
                .title("Note title")
                .content("Note body")
                .build();
        assertThrows(NullPointerException.class, () -> service.update(noteEntity));
    }

    @Test
    @DisplayName("Method update throws NSEE when there is no such entity in map")
    void update_ThrowsExceptionWhenNoteIsAbsent() {
        NoteEntity noteEntity = NoteEntity.builder()
                .id(1L)
                .title("Note title")
                .content("Note body")
                .build();
        assertThrows(NoSuchElementException.class, () -> service.update(noteEntity));
    }
}