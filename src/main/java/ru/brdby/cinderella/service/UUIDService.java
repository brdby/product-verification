package ru.brdby.cinderella.service;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UUIDService {
    public String generateRandomUUID() {
        return UUID.randomUUID().toString();
    }
}
