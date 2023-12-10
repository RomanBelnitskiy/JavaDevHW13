package com.example.JavaDevHW13.controller;

import com.example.JavaDevHW13.controller.controller.NoteController;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = NoteController.class)
class NoteControllerTest {
    @Autowired
    private MockMvc mvc;

//    @Test
//    void whenRequestIndexPage_thenReturnsStatus200() throws Exception {
//
//        mvc.perform(get("/"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void whenRequestListOfNotes_thenReturnsStatus200() throws Exception {
//
//        mvc.perform(get("/list"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void whenPathVariableIsInvalid_thenReturnsStatus400() throws Exception {
//
//        mvc.perform(get("/validatePathVariable/3"))
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    void whenRequestParameterIsInvalid_thenReturnsStatus400() throws Exception {
//        mvc.perform(get("/validateRequestParameter")
//                        .param("param", "3"))
//                .andExpect(status().isBadRequest());
//    }
}