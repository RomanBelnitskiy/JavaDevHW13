package com.example.JavaDevHW13.service.mapper;

import com.example.JavaDevHW13.controller.request.CreateNoteRequest;
import com.example.JavaDevHW13.controller.request.NoteRequest;
import com.example.JavaDevHW13.data.entity.NoteEntity;
import com.example.JavaDevHW13.service.dto.NoteDto;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class NoteMapper {

    public NoteEntity toEntity(NoteDto dto) {
        return NoteEntity
                .builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .content(dto.getContent())
                .build();
    }

    public NoteDto toDto(NoteEntity entity) {
        return NoteDto
                .builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .build();
    }

    public List<NoteEntity> toEntities(List<NoteDto> dtoList) {
        return dtoList
                .stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    public List<NoteDto> toDtos(List<NoteEntity> entities) {
        return entities
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public NoteDto toDto(CreateNoteRequest request) {
        return NoteDto
                .builder()
                .title(request.getTitle())
                .content(request.getContent())
                .build();
    }

    public NoteDto toDto(Long id, NoteRequest request) {
        return NoteDto
                .builder()
                .id(id)
                .title(request.getTitle())
                .content(request.getContent())
                .build();
    }

    public List<NoteDto> toDtos(Collection<CreateNoteRequest> requests) {
        return requests
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
