package com.lms.repository;

import com.lms.model.BookRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookRequestRepository extends JpaRepository<BookRequest, Long> {
    List<BookRequest> findByUserId(long userId);
}
