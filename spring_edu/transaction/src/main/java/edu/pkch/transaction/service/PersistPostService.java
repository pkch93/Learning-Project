package edu.pkch.transaction.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersistPostService {

    @Transactional
    public String save() {
        return null;
    }
}
