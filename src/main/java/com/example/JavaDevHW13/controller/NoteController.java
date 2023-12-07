package com.example.JavaDevHW13.controller;

import com.example.JavaDevHW13.service.dto.NoteDto;
import com.example.JavaDevHW13.service.service.NoteService;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/note")
@Validated
public class NoteController {
    @Autowired
    private NoteService service;

    @GetMapping("/list")
    public ModelAndView showAllNotesPage() {
        ModelAndView mav = new ModelAndView("note");
        mav.addObject("notes", service.listAll());
        return mav;
    }

    @GetMapping("/add")
    public ModelAndView showAddNewNotePage() {
        ModelAndView mav = new ModelAndView("add");
        return mav;
    }

    @PostMapping("/add")
    public String createNewNote(@RequestParam @NotBlank @Size(min = 3, max=150) String title,
                                @RequestParam @NotBlank @Size(min = 3, max=255) String content) {
        NoteDto dto = NoteDto.builder()
                .title(title)
                .content(content)
                .build();
        service.add(dto);

        return "redirect:/note/list";
    }

    @GetMapping("/edit")
    public ModelAndView showEditNotePage(@RequestParam @NotNull @Min(1) Long id) {
        NoteDto dto = service.getById(id);

        ModelAndView mav = new ModelAndView("edit");
        mav.addObject("note", dto);

        return mav;
    }

    @PostMapping("/edit/{id}")
    public String editNote(@PathVariable @NotNull @Min(1) Long id,
                           @RequestParam @NotBlank @Size(min = 3, max=150) String title,
                           @RequestParam @NotBlank @Size(min = 3, max=255) String content) {
        NoteDto dto = NoteDto
                .builder()
                .id(id)
                .title(title)
                .content(content)
                .build();
        service.update(dto);

        return "redirect:/note/list";
    }

    @PostMapping("/delete/{id}")
    public String deleteNote(@PathVariable @NotNull @Min(1) Long id) {
        service.deleteById(id);

        return "redirect:/note/list";
    }
}
