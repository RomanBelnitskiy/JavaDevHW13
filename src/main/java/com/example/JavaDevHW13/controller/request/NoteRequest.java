package com.example.JavaDevHW13.controller.request;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class NoteRequest {
    @Size(min = 3, max=150)
    private String title;
    @Size(min = 3, max=255)
    private String content;
}
