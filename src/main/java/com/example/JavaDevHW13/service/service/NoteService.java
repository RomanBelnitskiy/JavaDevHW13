package com.example.JavaDevHW13.service.service;

import com.example.JavaDevHW13.data.entity.NoteEntity;
import com.example.JavaDevHW13.data.repository.NoteRepository;
import com.example.JavaDevHW13.service.dto.NoteDto;
import com.example.JavaDevHW13.service.mapper.NoteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

import static java.util.Objects.requireNonNull;

@Service
@RequiredArgsConstructor
public class NoteService implements ServiceBase<NoteDto, Long> {
    private final NoteRepository repository;
    private final NoteMapper mapper;

    @Override
    public List<NoteDto> listAll() {
        return mapper.entitiesListToDto(repository.findAll());
    }

    @Override
    public NoteDto add(NoteDto noteDto) {
        requireNonNull(noteDto);

        NoteEntity entity = mapper.dtoToEntity(noteDto);
        entity = repository.save(entity);

        return mapper.entityToDto(entity);
    }

    @Override
    public void deleteById(Long id) {
        requireNonNull(id);

        repository.deleteById(id);
    }

    @Override
    public void update(NoteDto dto) {
        requireNonNull(dto);
        requireNonNull(dto.getId());

        repository.findById(dto.getId())
                .orElseThrow(() -> new NoSuchElementException());

        repository.save(mapper.dtoToEntity(dto));
    }

    @Override
    public NoteDto getById(Long id) {
        requireNonNull(id);

        NoteEntity entity = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException());

        return mapper.entityToDto(entity);
    }
}
