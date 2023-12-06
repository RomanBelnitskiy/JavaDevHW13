package com.example.JavaDevHW13.service.mapper;

import com.example.JavaDevHW13.data.entity.NoteEntity;
import com.example.JavaDevHW13.service.dto.NoteDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class NoteMapper {

    public NoteEntity dtoToEntity(NoteDto dto) {
        return NoteEntity
                .builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .content(dto.getContent())
                .build();
    }

    public NoteDto entityToDto(NoteEntity entity) {
        return NoteDto
                .builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .build();
    }

    public List<NoteEntity> dtoListToEntities(List<NoteDto> dtoList) {
        return dtoList
                .stream()
                .map(this::dtoToEntity)
                .collect(Collectors.toList());
    }

    public List<NoteDto> entitiesListToDto(List<NoteEntity> entities) {
        return entities
                .stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }
}
