package com.example.foyer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.foyer.DAO.Entities.Chambre;
import com.example.foyer.DAO.Entities.TypeChambre;
import com.example.foyer.DAO.Repositories.ChambreRepository;
import com.example.foyer.Services.Chambre.ChambreService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ChambreServiceTest {

    @Mock
    private ChambreRepository chambreRepository;

    @InjectMocks
    private ChambreService chambreService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAjouterChambre_Valide() {
        Chambre chambre = Chambre.builder()
                .numeroChambre(101L)
                .typeC(TypeChambre.SIMPLE)
                .build();

        when(chambreRepository.save(any(Chambre.class))).thenReturn(chambre);

        Chambre savedChambre = chambreService.addChambre(chambre);

        assertNotNull(savedChambre);
        verify(chambreRepository, times(1)).save(any(Chambre.class));
    }

    @Test
    void testAjouterChambre_AvecIdExistante() {
        Chambre chambre = Chambre.builder()
                .idChambre(1L)
                .numeroChambre(101L)
                .typeC(TypeChambre.SIMPLE)
                .build();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            chambreService.addChambre(chambre);
        });

        assertEquals("La chambre ne doit pas déjà avoir un ID", exception.getMessage());
    }

    @Test
    void testAjouterChambre_NumeroInvalide() {
        Chambre chambre = Chambre.builder()
                .numeroChambre(0L)  // ou null
                .typeC(TypeChambre.DOUBLE)
                .build();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            chambreService.addChambre(chambre);
        });

        assertEquals("Le numéro de chambre doit être valide", exception.getMessage());
    }

    @Test
    void testAjouterChambre_TypeChambreNull() {
        Chambre chambre = Chambre.builder()
                .numeroChambre(102L)
                .typeC(null)
                .build();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            chambreService.addChambre(chambre);
        });

        assertEquals("Le type de chambre doit être défini", exception.getMessage());
    }
}
