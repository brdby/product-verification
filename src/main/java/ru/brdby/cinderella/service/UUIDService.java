package ru.brdby.cinderella.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UUIDService {
    public String generateRandomUUID() {
        return UUID.randomUUID().toString();
    }
}
