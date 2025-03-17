package com.lms.controller;

import com.lms.model.BookRequest;
import com.lms.services.BookRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/requests")
public class BookRequestController {

    @Autowired
    BookRequestService bookRequestService;

    @GetMapping
    public List<BookRequest> getAllRequests(){
        return bookRequestService.getAllRequests();
    }

    @GetMapping("/{id}")
    public BookRequest requestById(@PathVariable long id){
        return bookRequestService.requestById(id);
    }

    @PostMapping
    public BookRequest addRequest(@RequestBody BookRequest br){
        return bookRequestService.addRequest(br);
    }

    @PutMapping("/{id}/approve")
    public BookRequest updateRequest(@PathVariable long id, @RequestParam("admin") long adminId){
        return bookRequestService.updateRequest(id, adminId);
    }

    @PutMapping("/{id}/return")
    public BookRequest updateRequestReturn(@PathVariable long id){
        return bookRequestService.updateRequestReturn(id);
    }

    @DeleteMapping("/{id}")
    public void deleteRequest(@PathVariable long id){
        bookRequestService.deleteRequest(id);
    }

    @GetMapping("/user/{userId}")
    public List<BookRequest> getRequestsByUser(@PathVariable long userId) {
        return bookRequestService.getRequestsByUser(userId);
    }

    @PutMapping("/{id}/status")
    public BookRequest updateRequestStatus(@PathVariable long id, @RequestParam("status") String status) {
        return bookRequestService.updateRequestStatus(id, status);
    }
}
