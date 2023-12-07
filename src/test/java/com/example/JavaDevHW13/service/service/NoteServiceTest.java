package com.example.JavaDevHW13.service.service;

import com.example.JavaDevHW13.data.entity.NoteEntity;
import com.example.JavaDevHW13.data.repository.NoteRepository;
import com.example.JavaDevHW13.service.dto.NoteDto;
import com.example.JavaDevHW13.service.mapper.NoteMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class NoteServiceTest {
    private NoteService service;
    private NoteRepository repository;
    private NoteMapper mapper;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(NoteRepository.class);
        mapper = new NoteMapper();
        service = new NoteService(repository, mapper);
    }

    @Test
    @DisplayName("Method add throws NPE when noteDto is null")
    void addNewNote_ThrowsException_IfNoteIsNull() {
        assertThrows(NullPointerException.class, () -> service.add(null));
    }

    @Test
    @DisplayName("Method listAll returns list with two notes")
    void listAllNotes_ReturnListWithTwoNotes() {
        NoteEntity noteEntity1 = NoteEntity.builder()
                .id(1L)
                .title("Note1 title")
                .content("Note1 body")
                .build();
        NoteEntity noteEntity2 = NoteEntity.builder()
                .id(2L)
                .title("Note2 title")
                .content("Note2 body")
                .build();
        when(repository.findAll()).thenReturn(List.of(noteEntity1, noteEntity2));

        List<NoteDto> noteDtos = service.listAll();

        assertEquals(2, noteDtos.size());
    }

    @Test
    @DisplayName("Method getById throws NPE when id is null")
    void getById_ThrowsExceptionWhenIdIsNull() {
        assertThrows(NullPointerException.class, () -> service.getById(null));
    }

    @Test
    @DisplayName("Method deleteById throws NPE when id is null")
    void deleteById_ThrowsExceptionWhenIdIsNull() {
        assertThrows(NullPointerException.class, () -> service.deleteById(null));
    }

//    @Test
//    @DisplayName("Method deleteById throws NSEE when there was no element with such id in map")
//    void deleteById_ThrowsExceptionWhenNoNoteWithSuchId() {
//        assertThrows(NoSuchElementException.class, () -> service.deleteById(1L));
//    }

    @Test
    @DisplayName("Method update throws NPE when entity is null")
    void update_ThrowsExceptionWhenNoteIsNull() {
        assertThrows(NullPointerException.class, () -> service.update(null));
    }

    @Test
    @DisplayName("Method update throws NPE when notes id is null")
    void update_ThrowsExceptionWhenNotesIdIsNull() {
        NoteDto noteDto = NoteDto.builder()
                .title("Note title")
                .content("Note body")
                .build();
        // TODO: change exception type. Something like NoSuchNoteIdException
        assertThrows(NullPointerException.class, () -> service.update(noteDto));
    }

    @Test
    @DisplayName("Method update throws NSEE when there is no such entity in map")
    void update_ThrowsExceptionWhenNoteIsAbsent() {
        NoteDto noteDto = NoteDto.builder()
                .id(1L)
                .title("Note title")
                .content("Note body")
                .build();
        assertThrows(NoSuchElementException.class, () -> service.update(noteDto));
    }
}