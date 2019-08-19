package com.project.stylealpha.stylealpha.repositories;

import com.project.stylealpha.stylealpha.model.Tag;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TagRepository extends CrudRepository<Tag, String> {

    List<Tag> findAll();
}
