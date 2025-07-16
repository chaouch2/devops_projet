package com.example.foyer.Services.Chambre;


import com.example.foyer.DAO.Entities.Chambre;
import com.example.foyer.DAO.Entities.TypeChambre;

import java.util.List;

public interface IChambreService {
    List<Chambre> retrieveAllChambres();
    Chambre addChambre(Chambre c);
    Chambre updateChambre(Chambre c);
    Chambre retrieveChambre(Long idChambre);
    void removeChambre(Long idChambre);

    List<Chambre> findByTypeCAndBlocIdBloc(TypeChambre typeChambre, Long idBloc);

    List<Chambre> findByReservationsEstValid(Boolean estValid);

    List<Chambre> findByBlocIdBlocAndBlocCapaciteBlocGreaterThan(Long idBloc,Long capaciteBloc);

    List<Chambre>  getChambresParNomBloc( String nomBloc) ;

    long  nbChambreParTypeEtBloc( TypeChambre type, long idBloc) ;

    List<Chambre>  getChambresNonReserveParNomFoyerEtTypeChambre( String nomFoyer,TypeChambre type) ;

    void pourcentageChambreParTypeChambre();
}

