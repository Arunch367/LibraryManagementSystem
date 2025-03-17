package com.lms.services;

import com.lms.model.Admin;
import com.lms.model.BookRequest;
import com.lms.model.BorrowStatus;
import com.lms.repository.BookRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookRequestService {

    @Autowired
    private BookRequestRepository bookRequestRepository;

    public List<BookRequest> getAllRequests() {
        List<BookRequest> requests = new ArrayList<>();
        bookRequestRepository.findAll().forEach(requests::add);
        return requests;
    }

    public BookRequest requestById(long id) {
        return bookRequestRepository.findById(id).orElseThrow();
    }

    public BookRequest addRequest(BookRequest br) {
        // ✅ Allow new request if last request was "RETURNED"
        Optional<BookRequest> existingRequest = bookRequestRepository.findByUserIdAndBookId(
                br.getUser().getId(), br.getBook().getId()
        );

        if (existingRequest.isPresent() && existingRequest.get().getStatus() != BorrowStatus.RETURNED) {
            throw new RuntimeException("You have already requested this book.");
        }

        br.setStatus(BorrowStatus.REQUESTED);
        return bookRequestRepository.save(br);
    }

    public BookRequest updateRequest(long requestId, long adminId) {
        BookRequest existingRequest = requestById(requestId);
        existingRequest.setStatus(BorrowStatus.ACCEPTED); // ✅ Correct status update

        Admin admin = new Admin();
        admin.setId(adminId);
        existingRequest.setAdmin(admin);

        return bookRequestRepository.save(existingRequest);
    }

    public void deleteRequest(long id) {
        bookRequestRepository.deleteById(id);
    }

    public List<BookRequest> getRequestsByUser(long userId) {
        return bookRequestRepository.findByUserId(userId);
    }

    public BookRequest updateRequestStatus(long requestId, String status) {
        BookRequest existingRequest = requestById(requestId);
        BorrowStatus newStatus = BorrowStatus.valueOf(status.toUpperCase());
        existingRequest.setStatus(newStatus);
        return bookRequestRepository.save(existingRequest);
    }

    public BookRequest updateRequestReturn(long requestId) {
        BookRequest bookRequest = requestById(requestId);
        bookRequest.setStatus(BorrowStatus.RETURNED);
        return bookRequestRepository.save(bookRequest);
    }
}
