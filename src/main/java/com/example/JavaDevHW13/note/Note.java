package com.example.JavaDevHW13.note;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Note {
    private Long id;
    private String title;
    private String content;
}
