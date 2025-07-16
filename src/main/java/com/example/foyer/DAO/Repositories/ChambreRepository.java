package com.example.foyer.DAO.Repositories;

import com.example.foyer.DAO.Entities.Chambre;
import com.example.foyer.DAO.Entities.TypeChambre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.List;
@Repository
public interface ChambreRepository extends JpaRepository<Chambre, Long> {
    List<Chambre> findByTypeCAndBlocIdBloc(TypeChambre typeChambre, Long idBloc);

    List<Chambre> findByReservationsEstValid(Boolean estValid);

    List<Chambre> findByBlocIdBlocAndBlocCapaciteBlocGreaterThan(Long idBloc, Long capaciteBloc);

    Chambre findByNumeroChambre(Long numeroChambre);

    List<Chambre>  findByBlocNomBloc(String nomBloc);

    @Query("select  count(c) from Chambre c where c.typeC=:type and c.bloc.idBloc= :idBloc")
    long nbChambreParTypeEtBloc(@Param("type") TypeChambre type, @Param("idBloc") long idBloc);


    @Query("select count (c) from Chambre c join c.reservations r where r.anneeUniversitaire between :startDate and  :endDate and c.typeC= :typeChambre and c.numeroChambre= :numeroChambre")
    long checkNbReservationsChambre(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate,
                                    @Param("typeChambre") TypeChambre typeChambre, @Param("numeroChambre") Long numeroChambre);

    @Query("SELECT count (c) from Chambre c where c.typeC= :typeC")
    Integer nbChambresParType(@Param("typeC") TypeChambre typeChambre);

    @Query("select c FROM Chambre c where c.bloc.idBloc = :idBloc and c.typeC=:typeC ")
    List<Chambre> findByTypeCAndBloc(@Param("typeC") TypeChambre typeChambre, @Param("idBloc") Long idBloc);


    @Query("select c from Chambre c join c.reservations r where  r.estValid= :valid")
    List<Chambre> findByReservationsValide(@Param("valid") Boolean estValid);


    @Query("select c FROM Chambre c, Bloc b where  c.bloc.idBloc=b.idBloc and b.idBloc= :idBloc and b.capaciteBloc >= :capaciteBloc")
    List<Chambre> findByBlocIdBlocAndBlocCapaciteBloc(@Param("idBloc") Long idBloc, @Param("capaciteBloc") Long capaciteBloc);


    interface BlocRepository {
    }
}