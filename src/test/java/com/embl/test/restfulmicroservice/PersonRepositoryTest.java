package com.embl.test.restfulmicroservice;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.embl.test.restfulmicroservice.entity.PersonV1Entity;
import com.embl.test.restfulmicroservice.repository.PersonRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository persons;

    @Test
    public void mapping() {
        PersonV1Entity saved = this.persons.save( PersonV1Entity.builder()
        		.firstName("test")
        		.lastName("lastname")
        		.age(50)
        		.favouriteColour("red")
        		.build());
        PersonV1Entity p = this.persons.getOne(saved.getId());
        assertThat(p.getFirstName()).isEqualTo("test");
        assertThat(p.getId()).isNotNull();
    }
}