package com.petines.RESTApi.repositories;

import com.petines.RESTApi.model.Tag;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TagRepository extends CrudRepository<Tag, String> {

    List<Tag> findAll();
}
