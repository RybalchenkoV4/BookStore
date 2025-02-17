package com.example.books_store.service;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.file.FileHeaders;
import org.springframework.messaging.handler.annotation.Header;

import java.time.LocalDateTime;

@MessagingGateway(defaultRequestChannel = "textInputChannel")
public interface FileGateway {
    void writeToFile(@Header(FileHeaders.FILENAME) String fileName, String date);
}
