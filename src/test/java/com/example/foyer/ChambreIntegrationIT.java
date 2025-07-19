package com.example.foyer;
 
import com.example.foyer.DAO.Entities.Chambre;
import com.example.foyer.DAO.Entities.TypeChambre;
import com.example.foyer.Services.Chambre.ChambreService;
 
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
 
import java.util.List;
 
import static org.junit.jupiter.api.Assertions.*;
 
@SpringBootTest
@Transactional
class ChambreIntegrationIT {
 
    @Autowired
    private ChambreService chambreService;
 
    @Test
    void testAjouterEtRécupérerChambre_SansBloc() {
        Chambre chambre = Chambre.builder()
                .numeroChambre(100L)
                .typeC(TypeChambre.DOUBLE)
                .bloc(null)  // Pas de bloc ici
                .build();
 
        Chambre saved = chambreService.addChambre(chambre);
        assertNotNull(saved.getIdChambre());
 
        Chambre retrieved = chambreService.retrieveChambre(saved.getIdChambre());
        assertNotNull(retrieved);
        assertEquals(100L, retrieved.getNumeroChambre());
        assertEquals(TypeChambre.DOUBLE, retrieved.getTypeC());
        assertNull(retrieved.getBloc());  // bloc doit être null
    }
 
    @Test
    void testRetrieveAllChambres() {
        Chambre c1 = Chambre.builder().numeroChambre(101L).typeC(TypeChambre.SIMPLE).bloc(null).build();
        Chambre c2 = Chambre.builder().numeroChambre(102L).typeC(TypeChambre.DOUBLE).bloc(null).build();
        chambreService.addChambre(c1);
        chambreService.addChambre(c2);
 
        List<Chambre> chambres = chambreService.retrieveAllChambres();
        assertTrue(chambres.size() >= 2);
    }
 
    @Test
    void testUpdateChambre_SansBloc() {
        Chambre chambre = Chambre.builder()
                .numeroChambre(200L)
                .typeC(TypeChambre.SIMPLE)
                .bloc(null)
                .build();
        Chambre saved = chambreService.addChambre(chambre);
 
        saved.setNumeroChambre(201L);
        saved.setTypeC(TypeChambre.TRIPLE);
        Chambre updated = chambreService.updateChambre(saved);
 
        assertEquals(201L, updated.getNumeroChambre());
        assertEquals(TypeChambre.TRIPLE, updated.getTypeC());
    }
 
    @Test
    void testRemoveChambre() {
        Chambre chambre = Chambre.builder()
                .numeroChambre(300L)
                .typeC(TypeChambre.SIMPLE)
                .bloc(null)
                .build();
        Chambre saved = chambreService.addChambre(chambre);
        Long id = saved.getIdChambre();
 
        chambreService.removeChambre(id);
        Chambre deleted = chambreService.retrieveChambre(id);
        assertNull(deleted);
    }
 
}
