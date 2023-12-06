package com.example.JavaDevHW13.controller;

import com.example.JavaDevHW13.data.note.Note;
import com.example.JavaDevHW13.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/note")
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
    public String createNewNote(@RequestParam String title,
                                @RequestParam String content) {
        Note note = Note.builder()
                .title(title)
                .content(content)
                .build();
        service.add(note);

        return "redirect:/note/list";
    }

    @GetMapping("/edit")
    public ModelAndView showEditNotePage(@RequestParam Long id) {
        Note note = service.getById(id);

        ModelAndView mav = new ModelAndView("edit");
        mav.addObject("note", note);

        return mav;
    }

    @PostMapping("/edit/{id}")
    public String editNote(@PathVariable Long id,
                           @RequestParam String title,
                           @RequestParam String content) {
        Note note = service.getById(id);
        note.setTitle(title);
        note.setContent(content);
        service.update(note);

        return "redirect:/note/list";
    }

    @PostMapping("/delete/{id}")
    public String deleteNote(@PathVariable Long id) {
        service.deleteById(id);

        return "redirect:/note/list";
    }
}