package com.example.JavaDevHW13.controller.controller;

import com.example.JavaDevHW13.controller.request.CreateNoteRequest;
import com.example.JavaDevHW13.controller.request.UpdateNoteRequest;
import com.example.JavaDevHW13.service.dto.NoteDto;
import com.example.JavaDevHW13.service.mapper.NoteMapper;
import com.example.JavaDevHW13.service.service.NoteService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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
    @Autowired
    private NoteMapper mapper;

    @GetMapping("/list")
    public ModelAndView showAllNotesPage() {
        ModelAndView mav = new ModelAndView("note");
        mav.addObject("notes", service.listAll());
        return mav;
    }

    @GetMapping("/add")
    public ModelAndView showAddNewNotePage() {
        ModelAndView mav = new ModelAndView("add");
        mav.addObject("note", new CreateNoteRequest());
        return mav;
    }

    @PostMapping(value = "/add")
    public String createNewNote(@Valid @NotNull CreateNoteRequest request) {
        service.add(mapper.toDto(request));

        return "redirect:/note/list";
    }

    @GetMapping("/edit")
    public ModelAndView showEditNotePage(@RequestParam @NotNull @Min(1) Long id) {
        NoteDto dto = service.getById(id);

        ModelAndView mav = new ModelAndView("edit");
        mav.addObject("note", dto);

        return mav;
    }

    @PostMapping("/edit")
    public String editNote(@RequestParam @NotNull @Min(1) Long id,
                           @Valid @NotNull UpdateNoteRequest request) {
        service.update(mapper.toDto(id, request));

        return "redirect:/note/list";
    }

    @PostMapping("/delete")
    public String deleteNote(@RequestParam @NotNull @Min(1) Long id) {
        service.deleteById(id);

        return "redirect:/note/list";
    }
}
