package com.example.demo.service.implementation;

import com.example.demo.dto.request.CourseRequestDTO;
import com.example.demo.dto.response.CourseCustomerCountDTO;
import com.example.demo.dto.response.CourseResponseDTO;
import com.example.demo.entity.palestra.Course;
import com.example.demo.entity.palestra.Customer;
import com.example.demo.entity.palestra.Room;
import com.example.demo.entity.palestra.Trainer;
import com.example.demo.exception_handling.palestra.exceptions.CourseHasSubscribersException;
import com.example.demo.exception_handling.palestra.exceptions.CourseNotFoundException;
import com.example.demo.exception_handling.palestra.exceptions.RoomNotFoundException;
import com.example.demo.mapper.palestra.CourseMapper;
import com.example.demo.mapper.palestra.RoomMapper;
import com.example.demo.repository.palestra.CourseRepository;
import com.example.demo.repository.palestra.RoomRepository;
import com.example.demo.repository.palestra.TrainerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CourseServiceImplTest {

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private CourseMapper courseMapper;

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private RoomMapper roomMapper;

    @Mock
    private TrainerRepository trainerRepository;

    @InjectMocks
    private CourseServiceImpl courseService;

    @Test
    void findAll() {

        List<Course> courses = new ArrayList<>();

        CourseResponseDTO courseResponseDTO = new CourseResponseDTO();
        courseResponseDTO.setName("bodybuilding");

        List<CourseResponseDTO> courseResponseDTOS = new ArrayList<>();
        courseResponseDTOS.add(courseResponseDTO);

        when(courseRepository.findAll()).thenReturn(courses);
        when(courseMapper.entityToResponseDTO(courses)).thenReturn(courseResponseDTOS);

        List<CourseResponseDTO> result = courseService.findAll();

        assertEquals("bodybuilding", result.get(0).getName());
    }

    @Test
    void findByIdFounded() {
        Integer id = 1;
        Course course = new Course();

        CourseResponseDTO courseResponseDTO = new CourseResponseDTO();
        courseResponseDTO.setName("bodybuilding");

        when(courseRepository.findById(id)).thenReturn(Optional.of(course));
        when(courseMapper.entityToResponseDTO(course)).thenReturn(courseResponseDTO);

        CourseResponseDTO result = courseService.findById(id);

        assertEquals("bodybuilding", result.getName());
    }

    @Test
    void findByIdNotFounded() {
        Integer id = 1;

        when(courseRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(CourseNotFoundException.class, () -> courseService.findById(id));
        // Mi aspetto che l'esecuzione di service.findById() lanci una courseNotFoundException
    }

    @Test
    void save() {
        Course course = new Course();
        Course savedCourse = new Course();

        CourseResponseDTO courseResponseDTO = new CourseResponseDTO();
        courseResponseDTO.setName("bodybuilding");

        CourseRequestDTO courseRequestDTO = new CourseRequestDTO();
        courseRequestDTO.setName("bodybuilding");
        courseRequestDTO.setTrainerName("Mario");
        courseRequestDTO.setRoomName("Sala A");

        Trainer trainer = new Trainer();
        Room room = new Room();

        when(courseMapper.requestDTOToEntity(courseRequestDTO)).thenReturn(course);
        when(courseRepository.save(course)).thenReturn(savedCourse);
        when(courseMapper.entityToResponseDTO(savedCourse)).thenReturn(courseResponseDTO);
        when(trainerRepository.findByFirstname("Mario")).thenReturn(Optional.of(trainer));
        when(roomRepository.findByName("Sala A")).thenReturn(Optional.of(room));

        CourseResponseDTO result = courseService.save(courseRequestDTO);
        assertEquals("bodybuilding", result.getName());

        verify(courseMapper).requestDTOToEntity(courseRequestDTO);
        verify(courseRepository).save(course);
        verify(courseMapper).entityToResponseDTO(savedCourse);
    }

    @Test
    void update() {
        Integer id = 1;
        Course course = new Course();
        Course updatedCourse = new Course();

        CourseRequestDTO courseRequestDTO = new CourseRequestDTO();
        courseRequestDTO.setName("bodybuilding");
        courseRequestDTO.setLevel("medium");
        courseRequestDTO.setDurationMinutes(120);
        courseRequestDTO.setTrainerName("fabio");
        courseRequestDTO.setRoomName("giove");

        CourseResponseDTO courseResponseDTO = new CourseResponseDTO();
        courseResponseDTO.setName("bodybuilding");

        when(courseRepository.findById(id)).thenReturn(Optional.of(course));
        when(courseRepository.save(course)).thenReturn(updatedCourse);
        when(courseMapper.entityToResponseDTO(updatedCourse)).thenReturn(courseResponseDTO);

        CourseResponseDTO result = courseService.update(id, courseRequestDTO);

        assertEquals("bodybuilding", result.getName());

        verify(courseRepository).findById(id);
        verify(courseRepository).save(course);
        verify(courseMapper).entityToResponseDTO(updatedCourse);
    }

    @Test
    void deletedById() {
        Integer id = 1;

        when(courseRepository.existsById(id)).thenReturn(true);
        doNothing().when(courseRepository).deleteById(id);

        String result = courseService.deletedById(id);
        assertEquals("Deleted course with id - 1", result);
        verify(courseRepository).deleteById(id);
    }

    @Test
    void deletedByIdNotFound() {
        Integer id = 1;

        when(courseRepository.existsById(id)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> courseService.deletedById(id));
    }

    @Test
    void changeCourseRoomCheckCapacity() {
        Integer courseId = 1;
        Integer roomId = 2;

        Course course = new Course();
        Room room = new Room();
        course.setCustomers(new ArrayList<>());
        room.setCapacity(120);

        CourseResponseDTO courseResponseDTO = new CourseResponseDTO();
        courseResponseDTO.setName("bodybuilding");

        when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));
        when(roomRepository.findById(roomId)).thenReturn(Optional.of(room));
        when(courseRepository.save(course)).thenReturn(course);
        when(courseMapper.entityToResponseDTO(course)).thenReturn(courseResponseDTO);

        CourseResponseDTO result = courseService.changeCourseRoomCheckCapacity(courseId, roomId);

        assertEquals("bodybuilding", result.getName());
        assertEquals(room, course.getRoom());

        verify(courseRepository).findById(courseId);
        verify(roomRepository).findById(roomId);
        verify(courseRepository).save(course);
        verify(courseMapper).entityToResponseDTO(course);
    }

    @Test
    void changeCourseRoomCheckCapacity_courseNotFound() {

        Integer courseId = 1;
        Integer roomId = 2;

        when(courseRepository.findById(courseId)).thenReturn(Optional.empty());

        assertThrows(CourseNotFoundException.class, () -> courseService.changeCourseRoomCheckCapacity(courseId, roomId));

        verify(courseRepository).findById(courseId);
        verify(roomRepository, never()).findById(any());
    }

    @Test
    void changeCourseRoomCheckCapacity_roomNotFound() {

        Integer courseId = 1;
        Integer roomId = 2;

        Course course = new Course();
        course.setCustomers(new ArrayList<>());

        when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));

        when(roomRepository.findById(roomId)).thenReturn(Optional.empty());

        assertThrows(RoomNotFoundException.class, () -> courseService.changeCourseRoomCheckCapacity(courseId, roomId));

        verify(courseRepository).findById(courseId);
        verify(roomRepository).findById(roomId);
    }

    @Test
    void deleteCourseOnlyIfZeroSubscribers() {

        Integer courseId = 1;

        Course course = new Course();
        course.setCustomers(new ArrayList<>());

        when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));

        courseService.deleteCourseOnlyIfZeroSubscribers(courseId);

        verify(courseRepository).findById(courseId);
        verify(courseRepository).deleteById(courseId);
    }

    @Test
    void deleteCourseOnlyIfZeroSubscribers_hasSubscribers() {

        Integer courseId = 1;

        Course course = new Course();

        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer());

        course.setCustomers(customers);

        when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));

        assertThrows(CourseHasSubscribersException.class, () -> courseService.deleteCourseOnlyIfZeroSubscribers(courseId));

        verify(courseRepository).findById(courseId);
        verify(courseRepository, never()).deleteById(any());
    }

    @Test
    void findCoursesByTrainerFirstname() {

        String trainerName = "mario";
        List<Course> courses = new ArrayList<>();
        List<CourseResponseDTO> courseResponseDTOS = new ArrayList<>();
        CourseResponseDTO courseResponseDTO = new CourseResponseDTO();
        courseResponseDTO.setName("mario");
        courseResponseDTOS.add(courseResponseDTO);

        when(courseRepository.findCoursesByTrainerFirstname(trainerName)).thenReturn(courses);
        when(courseMapper.entityToResponseDTO(courses)).thenReturn(courseResponseDTOS);

        List<CourseResponseDTO> result = courseService.findCoursesByTrainerFirstname(trainerName);

        assertEquals("mario", result.get(0).getName());

        verify(courseRepository).findCoursesByTrainerFirstname(trainerName);
        verify(courseMapper).entityToResponseDTO(courses);
    }

    @Test
    void findCoursesLongerThan() {
        Integer minutes = 60;

        List<Course> courses = new ArrayList<>();

        List<CourseResponseDTO> courseResponseDTOS = new ArrayList<>();
        CourseResponseDTO courseResponseDTO = new CourseResponseDTO();
        courseResponseDTO.setName("mario");
        courseResponseDTOS.add(courseResponseDTO);

        when(courseRepository.findCoursesLongerThan(minutes)).thenReturn(courses);
        when(courseMapper.entityToResponseDTO(courses)).thenReturn(courseResponseDTOS);

        List<CourseResponseDTO> result = courseService.findCoursesLongerThan(minutes);
        assertEquals("mario", result.get(0).getName());

        verify(courseRepository).findCoursesLongerThan(minutes);
        verify(courseMapper).entityToResponseDTO(courses);
    }

    @Test
    void countCustomersByCourse() {

        CourseCustomerCountDTO courseCustomerCountDTO = new CourseCustomerCountDTO();
        List<CourseCustomerCountDTO> courseCustomerCountDTOS = new ArrayList<>();
        courseCustomerCountDTO.setCourseName("bodybuilding");
        courseCustomerCountDTOS.add(courseCustomerCountDTO);

        when(courseRepository.countCustomersByCourse()).thenReturn(courseCustomerCountDTOS);

        List<CourseCustomerCountDTO> result = courseService.countCustomersByCourse();

        assertEquals("bodybuilding", result.get(0).getCourseName());

        verify(courseRepository).countCustomersByCourse();
    }

    @Test
    void findCoursesWithAtLeastCustomers() {
        Long min = 100L;

        List<Course> courses = new ArrayList<>();
        List<CourseResponseDTO> courseResponseDTOS = new ArrayList<>();
        CourseResponseDTO courseResponseDTO = new CourseResponseDTO();
        courseResponseDTO.setName("bodybuilding");
        courseResponseDTOS.add(courseResponseDTO);

        when(courseRepository.findCoursesWithAtLeastCustomers(min)).thenReturn(courses);
        when(courseMapper.entityToResponseDTO(courses)).thenReturn(courseResponseDTOS);

        List<CourseResponseDTO> result = courseService.findCoursesWithAtLeastCustomers(min);

        assertEquals("bodybuilding", result.get(0).getName());

        verify(courseRepository).findCoursesWithAtLeastCustomers(min);
        verify(courseMapper).entityToResponseDTO(courses);
    }
}