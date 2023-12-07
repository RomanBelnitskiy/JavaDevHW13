package com.example.JavaDevHW13.data.repository;

import com.example.JavaDevHW13.data.entity.NoteEntity;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class NoteRepository {
    private Map<Long, NoteEntity> notes = new HashMap<>();

    public List<NoteEntity> findAll() {
        return notes.values()
                .stream()
                .toList();
    }

    public Optional<NoteEntity> findById(Long id) {
        NoteEntity entity = notes.get(id);
        if (entity == null) {
            return Optional.empty();
        }
        return Optional.of(entity);
    }

    public NoteEntity save(NoteEntity noteEntity) {
        noteEntity.setId(getNextId());
        notes.put(noteEntity.getId(), noteEntity);

        return noteEntity;
    }

    public NoteEntity deleteById(Long id) {
        return notes.remove(id);
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
