package de.neuefische.springexceptionhandlingtask;

import org.junit.jupiter.api.Nested;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class CarControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void getCarBrand() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/cars/porsche"))
                .andExpect(status().isOk())
                .andExpect(content().string("porsche"));
    }

    //    @Test
//    void handleIllegalArgumentException() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.get("/api/cars/bmw"))
//                .andExpect(MockMvcResultMatchers.status().isBadRequest())
//                .andExpect(MockMvcResultMatchers.content().json("""
//                        {
//                            "message": "Folgender Fehler ist aufgetreten: Only 'porsche' allowed"
//                        }
//                        """));
//    }
    @Test
    void handleIllegalArgumentException() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/cars/bmw"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("Folgender Fehler ist aufgetreten: Only 'porsche' allowed")))
                .andExpect(jsonPath("$.timestamp", is(notNullValue())));
    }


    //    @Test
//    void getAllCars() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.get("/api/cars"))
//                .andExpect(status().isBadRequest())
//                .andExpect(content().json("""
//                        {
//                            "message": "Ein Fehler ist aufgetreten: No Cars found"
//                        }
//                        """));
//    }
    @Test
    void getAllCars() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/cars"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("Ein Fehler ist aufgetreten: No Cars found")))
                .andExpect(jsonPath("$.timestamp", is(notNullValue())));
    }
}