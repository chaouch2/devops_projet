package com.example.foyer.Services.Chambre;

import com.example.foyer.DAO.Entities.Bloc;
import com.example.foyer.DAO.Entities.Chambre;
import com.example.foyer.DAO.Entities.Foyer;
import com.example.foyer.DAO.Entities.TypeChambre;
import com.example.foyer.DAO.Repositories.ChambreRepository;
import com.example.foyer.DAO.Repositories.FoyerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class ChambreService implements IChambreService {

    private final ChambreRepository chambreRepository;
    private final FoyerRepository foyerRepository;

    @Override
    public List<Chambre> retrieveAllChambres() {
        return chambreRepository.findAll();
    }

    @Override
    public Chambre addChambre(Chambre chambre) {
        if (chambre.getIdChambre() != null) {
            throw new IllegalArgumentException("La chambre ne doit pas déjà avoir un ID");
        }
        if (chambre.getNumeroChambre() == null || chambre.getNumeroChambre() <= 0) {
            throw new IllegalArgumentException("Le numéro de chambre doit être valide");
        }
        if (chambre.getTypeC() == null) {
            throw new IllegalArgumentException("Le type de chambre doit être défini");
        }
        return chambreRepository.save(chambre);
    }

    @Override
    public Chambre updateChambre(Chambre chambre) {
        return chambreRepository.save(chambre);
    }

    @Override
    public Chambre retrieveChambre(Long idChambre) {
        return chambreRepository.findById(idChambre).orElse(null);
    }

    @Override
    public void removeChambre(Long idChambre) {
        chambreRepository.deleteById(idChambre);
    }

    @Override
    public List<Chambre> findByTypeCAndBlocIdBloc(TypeChambre typeChambre, Long idBloc) {
        return chambreRepository.findByTypeCAndBlocIdBloc(typeChambre, idBloc);
    }

    @Override
    public List<Chambre> findByReservationsEstValid(Boolean estValid) {
        return chambreRepository.findByReservationsValide(estValid);
    }

    @Override
    public List<Chambre> findByBlocIdBlocAndBlocCapaciteBlocGreaterThan(Long idBloc, Long capaciteBloc) {
        return chambreRepository.findByBlocIdBlocAndBlocCapaciteBloc(idBloc, capaciteBloc);
    }

    @Override
    public List<Chambre> getChambresParNomBloc(String nomBloc) {
        return chambreRepository.findByBlocNomBloc(nomBloc);
    }

    @Override
    public long nbChambreParTypeEtBloc(TypeChambre type, long idBloc) {
        return chambreRepository.nbChambreParTypeEtBloc(type, idBloc);
    }

    @Override
    public List<Chambre> getChambresNonReserveParNomFoyerEtTypeChambre(String nomFoyer, TypeChambre type) {
        List<Chambre> chambresDisponibles = new ArrayList<>();
        LocalDate startDate = LocalDate.of(LocalDate.now().getYear(), 1, 1);
        LocalDate endDate = LocalDate.of(LocalDate.now().getYear(), 12, 31);

        Foyer foyer = foyerRepository.findByNomFoyer(nomFoyer);
        if (foyer != null && foyer.getBlocs() != null) {
            for (Bloc bloc : foyer.getBlocs()) {
                for (Chambre chambre : bloc.getChambres()) {
                    if (chambre.getTypeC() == type) {
                        long nbReservations = chambreRepository.checkNbReservationsChambre(startDate, endDate, type, chambre.getNumeroChambre());
                        boolean disponible =
                                (type == TypeChambre.SIMPLE && nbReservations == 0) ||
                                        (type == TypeChambre.DOUBLE && nbReservations < 2) ||
                                        (type == TypeChambre.TRIPLE && nbReservations < 3);

                        if (disponible) {
                            chambresDisponibles.add(chambre);
                        }
                    }
                }
            }
        }

        return chambresDisponibles;
    }

    @Override
    public void pourcentageChambreParTypeChambre() {
        List<Chambre> chambres = chambreRepository.findAll();
        int total = chambres.size();
        log.info("Nombre total de chambres : {}", total);

        Arrays.stream(TypeChambre.values()).forEach(type -> {
            int count = chambreRepository.nbChambresParType(type);
            double pourcentage = (double) count / total * 100;
            log.info("Type : {}, Pourcentage : {}%", type, pourcentage);
        });
    }
}
