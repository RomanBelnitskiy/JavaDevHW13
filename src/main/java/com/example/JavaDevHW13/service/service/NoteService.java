package com.example.JavaDevHW13.service.service;

import com.example.JavaDevHW13.data.entity.NoteEntity;
import com.example.JavaDevHW13.data.repository.NoteRepository;
import com.example.JavaDevHW13.service.dto.NoteDto;
import com.example.JavaDevHW13.service.mapper.NoteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService implements ServiceBase<NoteDto, Long> {
    @Autowired private NoteRepository repository;
    @Autowired private NoteMapper mapper;

    @Override
    public List<NoteDto> listAll() {
        return mapper.entitiesListToDto(repository.findAll());
    }

    @Override
    public NoteDto add(NoteDto noteDto) {
        NoteEntity entity = mapper.dtoToEntity(noteDto);
        entity = repository.save(entity);

        return mapper.entityToDto(entity);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void update(NoteDto dto) {
        repository.update(mapper.dtoToEntity(dto));
    }

    @Override
    public NoteDto getById(Long id) {
        return mapper.entityToDto(repository.findById(id));
    }
}
