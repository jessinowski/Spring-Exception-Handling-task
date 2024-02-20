package de.neuefische.springexceptionhandlingtask;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class AnimalControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void getAnimalSpecies() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/animals/dog"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("dog"));
    }

//    @Test
//    @DirtiesContext
//    void handleIllegalArgumentException() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.get("/api/animals/cat"))
//                .andExpect(MockMvcResultMatchers.status().isBadRequest())
//                .andExpect(MockMvcResultMatchers.content().json("""
//                        {
//                            "message": "Folgender Fehler ist aufgetreten: Only 'dog' is allowed"
//                        }
//                        """));
//    }

    @Test
    @DirtiesContext
    void handleIllegalArgumentException() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/animals/cat"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", is("Folgender Fehler ist aufgetreten: Only 'dog' is allowed")))
                .andExpect(jsonPath("$.timestamp", is(notNullValue())));
    }
//    @Test
//    void getAllAnimals() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.get("/api/animals"))
//                .andExpect(MockMvcResultMatchers.status().isBadRequest())
//                .andExpect(MockMvcResultMatchers.content().json("""
//                        {
//                            "message": "Ein Fehler ist aufgetreten: No Animals found"
//                        }
//                        """));
//    }
    @Test
    void getAllAnimals() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/animals"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", is("Ein Fehler ist aufgetreten: No Animals found")))
                .andExpect(jsonPath("$.timestamp", is(notNullValue())));
    }
}