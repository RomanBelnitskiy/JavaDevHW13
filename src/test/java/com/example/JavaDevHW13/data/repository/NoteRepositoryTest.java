package com.example.JavaDevHW13.data.repository;

import com.example.JavaDevHW13.data.entity.NoteEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NoteRepositoryTest {
    private NoteRepository repository;

    @BeforeEach
    void setUp() {
        repository = new NoteRepository();
    }

    @Test
    @DisplayName("Method save sets id value to entity")
    void saveNewNotes_SetsRightIdToNote() {
        NoteEntity noteEntity1 = NoteEntity.builder()
                .title("Note title")
                .content("Note body")
                .build();
        NoteEntity noteEntity2 = NoteEntity.builder()
                .title("Note title")
                .content("Note body")
                .build();

        repository.save(noteEntity1);
        repository.save(noteEntity2);

        assertEquals(1L, noteEntity1.getId());
        assertEquals(2L, noteEntity2.getId());
    }

    @Test
    @DisplayName("Method findAll returns list with two notes")
    void findAllNotes_ReturnListWithTwoNotes() {
        NoteEntity noteEntity1 = NoteEntity.builder()
                .title("Note1 title")
                .content("Note1 body")
                .build();
        NoteEntity noteEntity2 = NoteEntity.builder()
                .title("Note2 title")
                .content("Note2 body")
                .build();

        repository.save(noteEntity1);
        repository.save(noteEntity2);

        List<NoteEntity> noteEntities = repository.findAll();

        assertEquals(2, noteEntities.size());
    }

    @Test
    @DisplayName("Method findById returns right entity")
    void findById_ReturnsRightNote() {
        NoteEntity noteEntity1 = NoteEntity.builder()
                .title("Note1 title")
                .content("Note1 body")
                .build();

        repository.save(noteEntity1);

        NoteEntity noteEntity = repository.findById(noteEntity1.getId()).get();

        assertEquals(noteEntity1, noteEntity);
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

        repository.save(noteEntity1);
        repository.save(noteEntity2);

        repository.deleteById(noteEntity1.getId());

        List<NoteEntity> noteEntities = repository.findAll();

        assertEquals(1, noteEntities.size());
    }
}