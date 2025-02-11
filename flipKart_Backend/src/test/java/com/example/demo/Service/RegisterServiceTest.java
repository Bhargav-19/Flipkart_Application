package com.example.demo.Service;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.Repository.IregisterRepo;
import com.example.demo.model.Register;

@ExtendWith(MockitoExtension.class) // Enables Mockito support in JUnit 5
public class RegisterServiceTest {

    @InjectMocks  // Injects RegisterService for testing
    private registerService registerService;

    @Mock  // Mocks IregisterRepo to avoid real DB calls
    private IregisterRepo registerRepo;

    @Test
    public void testSave() {
        // Arrange: Create a mock Register object
        Register mockRegister = new Register();
        mockRegister.setId(1L);
        mockRegister.setUserName("John Doe");
        mockRegister.setEmail("john@example.com");

        // Mock behavior: When registerRepo.save() is called, return mockRegister
        when(registerRepo.save(any(Register.class))).thenReturn(mockRegister);

        // Act: Call the save() method
        Register savedRegister = registerService.save(mockRegister);

        // Assert: Check if returned object is correct
        assertNotNull(savedRegister);
        assertEquals(1L, savedRegister.getId());
        assertEquals("John Doe", savedRegister.getUserName());
        assertEquals("john@example.com", savedRegister.getEmail());

        // Verify that registerRepo.save() was called exactly once
        verify(registerRepo, times(1)).save(mockRegister);
    }
    
    
    @Test
    public void testFindByName() {
        // Arrange: Create a mock Register object
        String mockUserName = "bs5";
        Register mockRegister = new Register();
        mockRegister.setUserName(mockUserName);

        // Mock behavior of repository
        when(registerRepo.findByuserName(mockUserName)).thenReturn(Optional.of(mockRegister));

        // Act: Call the service method
        Register foundRegister = registerService.findByName(mockUserName);

        // Assert: Check if returned object is correct
        assertNotNull(foundRegister, "Returned register should not be null");
        assertEquals(mockUserName, foundRegister.getUserName(), "Username should match the mock value");

        // Verify that findByuserName() was called exactly once
        verify(registerRepo, times(1)).findByuserName(mockUserName);
    }
}
