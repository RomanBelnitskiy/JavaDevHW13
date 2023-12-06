package com.example.JavaDevHW13.data.repository;

import com.example.JavaDevHW13.data.entity.NoteEntity;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.*;

import static java.util.Objects.requireNonNull;

@Component
public class NoteRepository {
    private Map<Long, NoteEntity> notes = new HashMap<>();

    public List<NoteEntity> findAll() {
        return notes.values()
                .stream()
                .toList();
    }

    public NoteEntity findById(Long id) {
        requireNonNull(id);
        return notes.get(id);
    }

    public NoteEntity save(NoteEntity noteEntity) {
        requireNonNull(noteEntity);

        noteEntity.setId(getNextId());
        notes.put(noteEntity.getId(), noteEntity);

        return noteEntity;
    }

    public void update(NoteEntity noteEntity) {
        requireNonNull(noteEntity);
        requireNonNull(noteEntity.getId());

        if (!notes.containsKey(noteEntity.getId())) {
            throw new NoSuchElementException();
        }

        notes.put(noteEntity.getId(), noteEntity);
    }

    public void deleteById(Long id) {
        requireNonNull(id);
        NoteEntity noteEntity = notes.remove(id);
        if (noteEntity == null) {
            throw new NoSuchElementException();
        }
    }

    private Long getNextId() {
        Optional<Long> maxIdOpt = notes.keySet()
                .stream()
                .max(Long::compare);

        return maxIdOpt.isPresent() ? maxIdOpt.get() + 1 : 1L;
    }

    @PostConstruct
    private void testListOfNotes() {
        notes.put(1L, NoteEntity.builder()
                .id(1L)
                .title("My first note")
                .content("Content of first note")
                .build());

        notes.put(2L, NoteEntity.builder()
                .id(2L)
                .title("My second note")
                .content("Content of second note")
                .build());

        notes.put(3L, NoteEntity.builder()
                .id(3L)
                .title("My third note")
                .content("Content of third note")
                .build());
    }
}
