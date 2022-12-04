package com.example.devops.repository;

import com.example.devops.entity.BookEntity;
import com.example.devops.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity,Long> {
}
