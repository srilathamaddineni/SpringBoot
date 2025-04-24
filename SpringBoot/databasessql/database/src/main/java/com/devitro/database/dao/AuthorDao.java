package com.devitro.database.dao;

import com.devitro.database.domain.Author;

import java.util.Optional;
import java.util.List;

public interface AuthorDao {
    void create(Author author);

    Optional<Author> findOne(long l);

    List<Author> find();

    void update(long l, Author author);

    void delete(long l);
}
