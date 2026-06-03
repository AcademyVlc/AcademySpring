package com.example.demo.services.ristorante.implementation;

import com.example.demo.dto.ristorante.requestdto.DishRequestDTO;
import com.example.demo.dto.ristorante.responsedto.CategoryCountDTO;
import com.example.demo.dto.ristorante.responsedto.DishResponseDTO;
import com.example.demo.entity.ristorante.Dish;
import com.example.demo.mapper.ristorante.DishMapper;
import com.example.demo.repository.ristorante.DishRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DishServiceImplTest {

    @Mock
    private DishRepository dishRepository;

    @Mock
    private DishMapper dishMapper;

    @InjectMocks
    private DishServiceImpl service;

    /**
     * Verifica:
     * Repository -> lista vuota
     * Mapper -> lista vuota
     * Service -> lista vuota
     */
    @Test
    void findAll() {
        // Quando il service chiamerà dishRepository.findAll(), restituisci una lista vuota.
        // Quindi non vai davvero sul database.
        when(dishRepository.findAll()).thenReturn(new ArrayList<>());
        // Quando il service chiamerà il mapper passando una qualunque lista, restituisci una lista vuota di DTO.
        // Anche qui non stai usando il vero mapper.
        when(dishMapper.entityToResponseDTO(anyList())).thenReturn(new ArrayList<>());
        // Esegui il metodo reale del service
        List<DishResponseDTO> dishes = service.findAll();
        // Verifichi che il risultato sia una lista vuota.
        assertEquals(new ArrayList<>(), dishes);
    }

    /**
     * Verifica due scenari:
     * <p>
     * 1.Caso positivo
     * Repository trova il piatto
     * Mapper converte il piatto
     * Service restituisce il DTO
     * 2.Caso negativo
     * Repository lancia eccezione
     * Service propaga eccezione
     * e controlli che venga effettivamente lanciata con assertThrows().
     */
    @Test
    void findById() {
        // Oggetto che simulerà il record trovato nel database.
        Dish dish = new Dish();
        // Per qualsiasi intero passato a findById, restituisci la dish.
        when(dishRepository.findById(anyInt())).thenReturn(Optional.of(dish));
        // Creo il DTO atteso e gli setto un nome fittizio
        DishResponseDTO value = new DishResponseDTO();
        value.setName("Crotalo al forno");
        // Se il mapper riceve quella Dish, restituisci quel DTO.
        when(dishMapper.entityToResponseDTO(dish)).thenReturn(value);
        // Qui eseguo il service --> Il metodo già implementato da testare
        DishResponseDTO byId = service.findById(1);
        // Controlli che il DTO restituito abbia il nome corretto.
        assertEquals("Crotalo al forno", byId.getName());
        // Quando qualcuno chiama findById, lancia un'eccezione.
        when(dishRepository.findById(anyInt())).thenThrow(NoSuchElementException.class);
        // Verifichi che il service lanci davvero quella eccezione. Se la lancia il test è passato, sennò fallito
        assertThrows(NoSuchElementException.class, () -> service.findById(1));
    }

    @Test
    void save() {
//
        DishRequestDTO dishRequestDTO = new DishRequestDTO();
        dishRequestDTO.setName("pippo");

        Dish dish = new Dish();
        Dish savedDish = new Dish();

        DishResponseDTO dishResponseDTO = new DishResponseDTO();
        dishResponseDTO.setName("pippo");

        when(dishMapper.requestDTOToEntity(dishRequestDTO)).thenReturn(dish);
        when(dishRepository.save(dish)).thenReturn(savedDish);
        when(dishMapper.entityToResponseDTO(savedDish)).thenReturn(dishResponseDTO);

        DishResponseDTO result = service.save(dishRequestDTO);

        assertEquals("pippo", result.getName());
    }

    @Test
    void update() {
        Integer id = 1;
        DishRequestDTO dishRequestDTO = new DishRequestDTO();
        dishRequestDTO.setName("pippo");

        Dish dish = new Dish();
        Dish updatedDish = new Dish();

        DishResponseDTO dishResponseDTO = new DishResponseDTO();
        dishResponseDTO.setName("pippo");

        when(dishRepository.findById(id)).thenReturn(Optional.of(dish));
        when(dishRepository.save(dish)).thenReturn(updatedDish);
        when(dishMapper.entityToResponseDTO(updatedDish)).thenReturn(dishResponseDTO);

        DishResponseDTO result = service.update(id, dishRequestDTO);
        assertEquals("pippo", result.getName());
    }

    @Test
    void deletedById() {
        Integer id = 1;
        doNothing().when(dishRepository).deleteById(id);
        when(dishRepository.existsById(id)).thenReturn(true);
        service.deletedById(id);
        when(dishRepository.existsById(id)).thenReturn(false);
        assertThrows(RuntimeException.class, () -> service.deletedById(id));
    }

    //
    @Test
    void findAvailableDish() {
        List<Dish> dishes = new ArrayList<>();
        dishes.add(new Dish());

        List<DishResponseDTO> dtos = new ArrayList<>();
        dtos.add(new DishResponseDTO());

        when(dishRepository.findAvailableDish()).thenReturn(dishes);
        when(dishMapper.entityToResponseDTO(dishes)).thenReturn(dtos);

        List<DishResponseDTO> result = service.findAvailableDish();
        assertEquals(1, result.size());
    }

    @Test
    void findUnderSpecificPriceDish() {
        Double price = 10.0;
        List<Dish> dishes = new ArrayList<>();
        dishes.add(new Dish());

        List<DishResponseDTO> dtos = new ArrayList<>();
        DishResponseDTO dto = new DishResponseDTO();
        dto.setName("pippo");
        dtos.add(dto);

        when(dishRepository.findUnderSpecificPriceDish(price)).thenReturn(dishes);
        when(dishMapper.entityToResponseDTO(dishes)).thenReturn(dtos);

        List<DishResponseDTO> result = service.findUnderSpecificPriceDish(price); // RESULT == dtos
        assertEquals(1, result.size());
        assertEquals("pippo", result.get(0).getName());
    }

    @Test
    void findDishByCategoryName() {
        String categoryName = "Pasta";

        List<Dish> dishes = new ArrayList<>();
        dishes.add(new Dish());

        List<DishResponseDTO> dishResponseDTOS = new ArrayList<>();
        DishResponseDTO dishResponseDTO = new DishResponseDTO();
        dishResponseDTO.setName("spaghetti");
        dishResponseDTOS.add(dishResponseDTO);

        when(dishRepository.findDishByCategoryName(categoryName)).thenReturn(dishes);
        when(dishMapper.entityToResponseDTO(dishes)).thenReturn(dishResponseDTOS);

        List<DishResponseDTO> result = service.findDishByCategoryName(categoryName);
        assertEquals(1, result.size());
        assertEquals("spaghetti", result.get(0).getName());
    }

    @Test
    void findDishByChefName() {
        String chefName = "Cannavacciuolo";

        List<Dish> dishes = new ArrayList<>();
        dishes.add(new Dish());

        List<DishResponseDTO> dishResponseDTOS = new ArrayList<>();
        DishResponseDTO dishResponseDTO = new DishResponseDTO();
        dishResponseDTO.setName("Antonino");
        dishResponseDTOS.add(dishResponseDTO);

        when(dishRepository.findDishByChefName(chefName)).thenReturn(dishes);
        when(dishMapper.entityToResponseDTO(dishes)).thenReturn(dishResponseDTOS);

        List<DishResponseDTO> result = service.findDishByChefName(chefName);
        assertEquals(1, result.size());
        assertEquals("Antonino", result.get(0).getName());
    }

    @Test
    void findAvailableDishByCategoryName() {
        String categoryName = "Pasta";

        List<Dish> dishes = new ArrayList<>();
        dishes.add(new Dish());

        List<DishResponseDTO> dishResponseDTOS = new ArrayList<>();
        DishResponseDTO dishResponseDTO = new DishResponseDTO();
        dishResponseDTO.setName("spaghetti");
        dishResponseDTOS.add(dishResponseDTO);

        when(dishRepository.findDishByCategoryName(categoryName)).thenReturn(dishes);
        when(dishMapper.entityToResponseDTO(dishes)).thenReturn(dishResponseDTOS);

        List<DishResponseDTO> result = service.findDishByCategoryName(categoryName);
        assertEquals(1, result.size());
        assertEquals("spaghetti", result.get(0).getName());
    }

    @Test
    void findDishByRangePrice() {
        Double min = 5.00;
        Double max = 20.00;

        List<Dish> dishes = new ArrayList<>();
        dishes.add(new Dish());

        List<DishResponseDTO> dtos = new ArrayList<>();
        DishResponseDTO dto = new DishResponseDTO();
        dto.setName("pizza");
        dtos.add(dto);

        when(dishRepository.findDishByRangePrice(min, max)).thenReturn(dishes);
        when(dishMapper.entityToResponseDTO(dishes)).thenReturn(dtos);

        List<DishResponseDTO> result = service.findDishByRangePrice(min, max);
        assertEquals(1, result.size());
        assertEquals("pizza", result.get(0).getName());
    }

    @Test
    void globalSearch() {
        String name = "pasta";

        List<Dish> dishes = new ArrayList<>();
        dishes.add(new Dish());

        List<DishResponseDTO> dtos = new ArrayList<>();
        DishResponseDTO dto = new DishResponseDTO();
        dto.setName("pizza");
        dtos.add(dto);

        when(dishRepository.globalSearch(name)).thenReturn(dishes);
        when(dishMapper.entityToResponseDTO(dishes)).thenReturn(dtos);

        List<DishResponseDTO> result = service.globalSearch(name);
        assertEquals(1, result.size());
        assertEquals("pizza", result.get(0).getName());
    }

    @Test
    void countDishGroupingByCategory() {

        List<Object[]> rows = new ArrayList<>();

        rows.add(new Object[]{"categoria a", 5L});

        when(dishRepository.countDishGroupingByCategory()).thenReturn(rows);

        List<CategoryCountDTO> result = service.countDishGroupingByCategory();

        assertEquals(1, result.size());
        assertEquals("categoria a", result.get(0).getCategory());
        assertEquals(5L, result.get(0).getTotal());
    }
}