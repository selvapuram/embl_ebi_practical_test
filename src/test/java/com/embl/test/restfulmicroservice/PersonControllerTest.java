package com.embl.test.restfulmicroservice;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.embl.test.restfulmicroservice.config.model.PersonV1DTO;
import com.embl.test.restfulmicroservice.entity.PersonV1Entity;
import com.embl.test.restfulmicroservice.repository.PersonRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = PersonControllerTest.class)
public class PersonControllerTest {

    @MockBean
    PersonRepository persons;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @Before
    public void setUp() {
        given(this.persons.findById(1))
                .willReturn(Optional.of(PersonV1Entity.builder()
                		.firstName("test")
                		.lastName("lastname")
                		.age(50)
                		.favouriteColour("red")
                		.build()));

        given(this.persons.findById(2))
                .willReturn(Optional.empty());

        given(this.persons.save(any(PersonV1Entity.class)))
                .willReturn(PersonV1Entity.builder()
                		.firstName("test")
                		.lastName("lastname")
                		.age(50)
                		.favouriteColour("red")
                		.build());

        given(this.persons.findAll(any(Pageable.class)))
                .willReturn(null);

        doNothing().when(this.persons).deleteById(anyInt());
    }

    @Test
    public void testFindAll() throws Exception {

        this.mockMvc
                .perform(
                        get("/v1/persons?page=1&size=50")
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(null);

        verify(this.persons, times(1)).findAll(any(Pageable.class));
        verifyNoMoreInteractions(this.persons);
    }

    @Test
    public void testGetById() throws Exception {

        this.mockMvc
                .perform(
                        get("/v1/persons/{id}", 1L)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("test"));

        verify(this.persons, times(1)).findById(any(Integer.class));
        verifyNoMoreInteractions(this.persons);
    }

    @Test
    public void testGetByIdNotFound() throws Exception {

        this.mockMvc
                .perform(
                        get("/v1/persons/{id}", 2)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNotFound());

        verify(this.persons, times(1)).findById(any(Integer.class));
        verifyNoMoreInteractions(this.persons);
    }

    @Test
    public void testSave() throws Exception {

        this.mockMvc
                .perform(
                        post("/v1/persons")
                                .content(this.objectMapper.writeValueAsBytes(PersonV1DTO.builder()
                                		.firstName("test")
                                		.lastName("lastname")
                                		.age(50)
                                		.favouriteColour("red")
                                		.build()))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated());

        verify(this.persons, times(1)).save(any(PersonV1Entity.class));
        verifyNoMoreInteractions(this.persons);
    }

    @Test
    public void testUpdate() throws Exception {

        this.mockMvc
                .perform(
                        put("/v1/persons/1")
                                .content(this.objectMapper.writeValueAsBytes(PersonV1DTO.builder()
                                		.firstName("test")
                                		.lastName("lastname")
                                		.age(50)
                                		.favouriteColour("red")
                                		.build()))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNoContent());

        verify(this.persons, times(1)).findById(any(Integer.class));
        verify(this.persons, times(1)).save(any(PersonV1Entity.class));
        verifyNoMoreInteractions(this.persons);
    }

    @Test
    public void testDelete() throws Exception {

        this.mockMvc
                .perform(
                        delete("/v1/persons/1")
                )
                .andExpect(status().isNoContent());

        verify(this.persons, times(1)).findById(any(Integer.class));
        verify(this.persons, times(1)).deleteById(anyInt());
        verifyNoMoreInteractions(this.persons);
    }

}