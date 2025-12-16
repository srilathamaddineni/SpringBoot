package com.devitro.database.repositories;


import com.devitro.database.domain.entites.AuthorEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<AuthorEntity,Long> {
    Iterable<AuthorEntity> ageLessThan(int age);

//    @Query("SELECT a from Author a where a.age > :age")
//    Iterable<AuthorEntity> findAuthorsWithAgeGreaterThan(@Param("age") int age);
}
