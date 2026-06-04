package com.example.demo.controller.ristorante;

import com.example.demo.dto.ristorante.requestdto.DishRequestDTO;
import com.example.demo.dto.ristorante.responsedto.DishResponseDTO;
import com.example.demo.services.ristorante.implementation.DishServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DishController.class)
class DishControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private DishServiceImpl dishService;

    @Test
    void findAll() throws Exception {
        DishResponseDTO dto = new DishResponseDTO();
        dto.setName("Pizza");

        List<DishResponseDTO> dishes = List.of(dto);

        when(dishService.findAll()).thenReturn(dishes);

        // Simulo una Get -> come farei da swagger o postman
        mockMvc.perform(get("/api/dishes"))
                // controllo che la risposta sia 200 ok --> Sennò fallisce
                .andExpect(status().isOk())
                // Verifico il Json restituito
                .andExpect(jsonPath("$[0].name").value("Pizza"));
    }

    @Test
    void findByID() throws Exception {
        Integer id = 1;
        DishResponseDTO dishResponseDTO = new DishResponseDTO();
        dishResponseDTO.setName("pizza");

        when(dishService.findById(id)).thenReturn(dishResponseDTO);

        mockMvc.perform(get("/api/dishes/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("pizza"));
    }

    @Test
    void save() throws Exception {

        String json = """
            {
                "name":"Pizza",
                "price":15.0,
                "available":true
            }
            """;

        DishResponseDTO dishResponseDTO = new DishResponseDTO();
        dishResponseDTO.setName("pizza");

        when(dishService.save(any(DishRequestDTO.class)))
                .thenReturn(dishResponseDTO);

        mockMvc.perform(
                        post("/api/dishes")
                                // dice a Spring "Il body che sto inviando è un JSON"
                                // equivale all'header HTTP: Content-Type: application/json
                                .contentType(MediaType.APPLICATION_JSON)
                                // Inserisce il contenuto della richiesta -> Cioè la variabile json creata prima
                                .content(json)
                )
                .andExpect(status().isOk());
    }

    @Test
    void update() throws Exception {

        Integer id = 1;

        String json = """
            {
                "name":"Pizza",
                "price":15.0,
                "available":true
            }
            """;

        DishResponseDTO dishResponseDTO = new DishResponseDTO();
        dishResponseDTO.setName("Pizza");

        // eq = Deve arrivare proprio l'id che ho definito sopra
        when(dishService.update(eq(id), any(DishRequestDTO.class))).thenReturn(dishResponseDTO);

        mockMvc.perform(
                        put("/api/dishes/{id}", id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Pizza"));
    }

    @Test
    void deletedById() throws Exception {

        Integer id = 1;

        mockMvc.perform(delete("/api/dishes/{id}", id))
                .andExpect(status().isNoContent());

        // Con assert noi stiamo dicendo "Controlla che il valore restituito sia "Pizza"."
        // Nel caso della delete dal controller noi non restituiamo dati. Perchè non abbiamo un oggetto da confrontare.
        // Verify significa: Controlla che il controller abbia chiamato dishService.deletedById(id).
        verify(dishService).deletedById(id);
    }

//    @Test
//    void findAvailableDish() {
//    }
//
//    @Test
//    void findUnderSpecificPriceDish() {
//    }
//
//    @Test
//    void findDishByCategoryName() {
//    }
//
//    @Test
//    void findDishByChefName() {
//    }
//
//    @Test
//    void findAvailableByCategoryName() {
//    }
//
//    @Test
//    void findDishByRangePrice() {
//    }
//
//    @Test
//    void globalSearch() {
//    }
//
//    @Test
//    void countAvailableDishGroupByCategory() {
//    }
}