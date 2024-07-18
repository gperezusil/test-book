package com.test.exam.domain.repo;

import com.test.exam.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBookRepository extends JpaRepository<Book, String> {
}
