package edu.pkch.transaction.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReadPostService {

    @Transactional(readOnly = true)
    public String readAll() {
        return null;
    }
}
