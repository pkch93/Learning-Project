package edu.pkch.transaction.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private ReadPostService readPostService;
    private PersistPostService persistPostService;

    public String readAll() {
        return readPostService.readAll();
    }

    public String save() {
        return persistPostService.save();
    }
}
