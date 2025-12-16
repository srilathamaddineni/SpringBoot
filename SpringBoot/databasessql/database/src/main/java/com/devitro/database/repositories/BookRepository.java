package com.devitro.database.repositories;

import com.devitro.database.domain.entites.BookEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends CrudRepository<BookEntity,String> {

}
