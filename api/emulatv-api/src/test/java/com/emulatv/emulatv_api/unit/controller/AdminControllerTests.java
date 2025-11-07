package com.emulatv.emulatv_api.unit.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.emulatv.emulatv_api.repository.ServiceRepository;
import com.emulatv.emulatv_api.util.AgentHandler;
import com.emulatv.emulatv_api.controller.AdminController;

@ExtendWith(MockitoExtension.class)
public class AdminControllerTests {
    
    @Mock
    private AgentHandler agentHandler;

    @Mock
    private ServiceRepository serviceRepository;

    @Mock
    private MultipartFile multipartFile;

    @InjectMocks
    private AdminController adminController;

    @Test
    void postUploadService_WhenFileIsEmpty_ReturnsBadRequest() {
        when(multipartFile.isEmpty()).thenReturn(true);

        ResponseEntity<?> response = adminController.postUploadService("test", "http://test.com", multipartFile);
        
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("No file found", response.getBody());
    }
    
    @Test
    void postUploadService_WhenFileIsNotSvg_ReturnsBadRequest(){
        when(multipartFile.isEmpty()).thenReturn(false);
        when(multipartFile.getContentType()).thenReturn("image/png");

        ResponseEntity<?> response = adminController.postUploadService("test","http://test.com", multipartFile);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("File must be an svg", response.getBody());
    }
    
    @Test
    void postUploadService_WhenFileCannotBeRead_ReturnsInternalError() throws IOException{
        when(multipartFile.isEmpty()).thenReturn(false);
        when(multipartFile.getContentType()).thenReturn("image/svg+xml");
        when(multipartFile.getInputStream()).thenThrow(new IOException("Error reading file"));

        ResponseEntity<?> response = adminController.postUploadService("test","http://test.com", multipartFile);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error reading the file", response.getBody());
    }

    @Test
    void postUploadService_WhenEverythingIsValid_ReturnsOk() throws IOException{
        when(multipartFile.isEmpty()).thenReturn(false);
        when(multipartFile.getContentType()).thenReturn("image/svg+xml");
        when(multipartFile.getInputStream()).thenReturn(new ByteArrayInputStream(new byte[0]));

        ResponseEntity<String> mockResponse = ResponseEntity.ok("SVG file store with success ! ");
        when(agentHandler.postForEntityRequest(anyString(), any() )).thenReturn(mockResponse);

        ResponseEntity<?> response = adminController.postUploadService("test","http://test.com", multipartFile);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Service has been successfully added !", response.getBody());
        verify(serviceRepository, times(1)).save(any());
    }

    @Test
    void postUploadService_WhenAgentRejectsFile_ReturnsInternalServerError() throws IOException {
        when(multipartFile.isEmpty()).thenReturn(false);
        when(multipartFile.getContentType()).thenReturn("image/svg+xml");
        when(multipartFile.getInputStream()).thenReturn(new ByteArrayInputStream(new byte[0]));

        ResponseEntity<String> mockResponse = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Agent error");
        when(agentHandler.postForEntityRequest(anyString(), any())).thenReturn(mockResponse);

        ResponseEntity<?> response = adminController.postUploadService("test", "http://test.com", multipartFile);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Agent has not accepted the fileAgent error", response.getBody());
    }

}
