package com.example.JavaDevHW13.note;

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
}
