package com.example.demo.controller.ristorante;

import com.example.demo.dto.ristorante.requestdto.DishRequestDTO;
import com.example.demo.dto.ristorante.responsedto.CategoryCountDTO;
import com.example.demo.dto.ristorante.responsedto.DishResponseDTO;
import com.example.demo.services.ristorante.implementation.DishServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
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

        verify(dishService).findAll();
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

        when(dishService.findById(id)).thenThrow(new NoSuchElementException("Dish not found"));


        mockMvc.perform(get("/api/dishes/{id}", id))
                .andExpect(status().isNotFound());

//        verify(dishService).findById(id);
        verify(dishService, times(2)).findById(id);
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

        verify(dishService).save(any(DishRequestDTO.class));
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

        verify(dishService).update(eq(id), any(DishRequestDTO.class));
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

    @Test
    void findAvailableDish() throws Exception {
        DishResponseDTO dto = new DishResponseDTO();
        dto.setName("Pizza");

        List<DishResponseDTO> dishes = List.of(dto);

        when(dishService.findAvailableDish()).thenReturn(dishes);

        // Simulo una Get -> come farei da swagger o postman
        mockMvc.perform(get("/api/dishes/find-available-dishes"))
                // controllo che la risposta sia 200 ok --> Sennò fallisce
                .andExpect(status().isOk())
                // Verifico il Json restituito
                .andExpect(jsonPath("$[0].name").value("Pizza"));

        verify(dishService).findAvailableDish();
    }

    @Test
    void findUnderSpecificPriceDish() throws Exception {
        Double price = 25.0;
        DishResponseDTO dishResponseDTO = new DishResponseDTO();
        dishResponseDTO.setName("pizza");

        List<DishResponseDTO> dishes = new ArrayList<>();
        dishes.add(dishResponseDTO);

        when(dishService.findUnderSpecificPriceDish(price)).thenReturn(dishes);

        mockMvc.perform(get("/api/dishes/find-under-specific-price-dish").param("price", "25.0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("pizza"));

        verify(dishService).findUnderSpecificPriceDish(price);

    }

    @Test
    void findDishByCategoryName() throws Exception {
        String categoryName = "Carne";
        DishResponseDTO dishResponseDTO = new DishResponseDTO();
        dishResponseDTO.setName("Pizza");

        List<DishResponseDTO> dishResponseDTOS = new ArrayList<>();
        dishResponseDTOS.add(dishResponseDTO);

        when(dishService.findDishByCategoryName(categoryName)).thenReturn(dishResponseDTOS);

        mockMvc.perform(get("/api/dishes/find-dish-by-category-name").param("categoryName", "Carne"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Pizza"));

        verify(dishService).findDishByCategoryName(categoryName);
    }

    @Test
    void findDishByChefName() throws Exception {
        String chefname = "bruno";
        DishResponseDTO dishResponseDTO = new DishResponseDTO();
        dishResponseDTO.setName("Pizza");

        List<DishResponseDTO> dishResponseDTOS = new ArrayList<>();
        dishResponseDTOS.add(dishResponseDTO);

        when(dishService.findDishByChefName(chefname)).thenReturn(dishResponseDTOS);

        mockMvc.perform(get("/api/dishes/find-dish-by-chef-name").param("chefName", "bruno"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Pizza"));

        verify(dishService).findDishByChefName(chefname);

    }

    @Test
    void findAvailableDishByCategoryName() throws Exception {
        String categoryName = "Carne";

        DishResponseDTO dishResponseDTO = new DishResponseDTO();
        dishResponseDTO.setName("pizza");
        List<DishResponseDTO> dishResponseDTOS = new ArrayList<>();
        dishResponseDTOS.add(dishResponseDTO);

        when(dishService.findAvailableDishByCategoryName(categoryName)).thenReturn(dishResponseDTOS);

        mockMvc.perform(get("/api/dishes/find-available-dish-by-category-name").param("categoryName", "Carne"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("pizza"));

        verify(dishService).findAvailableDishByCategoryName(categoryName);
    }

    @Test
    void findDishByRangePrice() throws Exception {
        Double min = 10.0;
        Double max = 20.0;

        DishResponseDTO dishResponseDTO = new DishResponseDTO();
        dishResponseDTO.setName("pizza");
        List<DishResponseDTO> dishResponseDTOS = new ArrayList<>();
        dishResponseDTOS.add(dishResponseDTO);

        when(dishService.findDishByRangePrice(min, max)).thenReturn(dishResponseDTOS);

        mockMvc.perform(get("/api/dishes/find-dish-by-range-price")
                        .param("min", "10.0")
                        .param("max", "20.0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("pizza"));

        verify(dishService).findDishByRangePrice(min, max);
    }

    @Test
    void globalSearch() throws Exception{
        String name = "bruno";

        DishResponseDTO dishResponseDTO = new DishResponseDTO();
        dishResponseDTO.setName("pizza");
        List<DishResponseDTO> dishResponseDTOS = new ArrayList<>();
        dishResponseDTOS.add(dishResponseDTO);

        when(dishService.globalSearch(name)).thenReturn(dishResponseDTOS);

        mockMvc.perform(get("/api/dishes/global-search")
                        .param("name", "bruno"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("pizza"));

        verify(dishService).globalSearch(name);
    }

    @Test
    void countAvailableDishGroupByCategory() throws Exception{
        CategoryCountDTO categoryCountDTO = new CategoryCountDTO();
        categoryCountDTO.setCategory("Carne");

        List<CategoryCountDTO> categoryCountDTOS = new ArrayList<>();
        categoryCountDTOS.add(categoryCountDTO);

        when(dishService.countDishGroupingByCategory()).thenReturn(categoryCountDTOS);

        mockMvc.perform(get("/api/dishes/count-available-dish-group-by-category"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].category").value("Carne"));

        verify(dishService).countDishGroupingByCategory();
    }
}