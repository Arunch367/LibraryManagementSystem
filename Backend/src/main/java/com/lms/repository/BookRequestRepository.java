package com.lms.repository;

import com.lms.model.BookRequest;
import com.lms.model.BorrowStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRequestRepository extends JpaRepository<BookRequest, Long> {
    List<BookRequest> findByUserId(long userId);

    // ✅ Updated to find requests regardless of status
    Optional<BookRequest> findByUserIdAndBookId(long userId, long bookId);
}
