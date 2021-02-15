package com.embl.test.restfulmicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.embl.test.restfulmicroservice.entity.PersonV1Entity;

public interface PersonRepository extends JpaRepository<PersonV1Entity, Integer> {

}
