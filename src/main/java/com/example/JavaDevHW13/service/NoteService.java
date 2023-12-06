package com.example.JavaDevHW13.service;

import com.example.JavaDevHW13.data.note.Note;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.util.Objects.requireNonNull;

@Service
public class NoteService implements ServiceBase<Note, Long> {
    private Map<Long, Note> notes = new HashMap<>();

    @Override
    public List<Note> listAll() {
        return notes.values()
                .stream()
                .toList();
    }

    @Override
    public Note add(Note note) {
        requireNonNull(note);

        note.setId(getNextId());
        notes.put(note.getId(), note);

        return note;
    }

    private Long getNextId() {
        Optional<Long> maxIdOpt = notes.keySet()
                .stream()
                .max(Long::compare);

        return maxIdOpt.isPresent() ? maxIdOpt.get() + 1 : 1L;
    }

    @Override
    public void deleteById(Long id) {
        requireNonNull(id);
        Note note = notes.remove(id);
        if (note == null) {
            throw new NoSuchElementException();
        }
    }

    @Override
    public void update(Note note) {
        requireNonNull(note);
        requireNonNull(note.getId());

        if (!notes.containsKey(note.getId())) {
            throw new NoSuchElementException();
        }

        notes.put(note.getId(), note);
    }

    @Override
    public Note getById(Long id) {
        requireNonNull(id);
        return notes.get(id);
    }

    @PostConstruct
    private void testListOfNotes() {
        notes.put(1L, Note.builder()
                .id(1L)
                .title("My first note")
                .content("Content of first note")
                .build());

        notes.put(2L, Note.builder()
                .id(2L)
                .title("My second note")
                .content("Content of second note")
                .build());

        notes.put(3L, Note.builder()
                .id(3L)
                .title("My third note")
                .content("Content of third note")
                .build());
    }
}
