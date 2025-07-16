package com.example.foyer.RestControllers;

import com.example.foyer.DAO.Entities.Chambre;
import com.example.foyer.DAO.Entities.TypeChambre;
import com.example.foyer.Services.Chambre.IChambreService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/chambre")

public class ChambreRestController {
    IChambreService chambreService;
    // http://localhost:8089/foyer/chambre/retrieve-all-chambres
    @GetMapping("/retrieve-all-chambres")
    @ResponseBody
    public List<Chambre> getChambres() {
        List<Chambre> listChambres = chambreService.retrieveAllChambres();
        return listChambres;
    }

    // http://localhost:8089/foyer/chambre/retrieve-chambre/8
    @GetMapping("/retrieve-chambre/{chambreId}")
    @ResponseBody
    public Chambre retrieveChambre(@PathVariable("chambreId") Long chambreId) {
        return chambreService.retrieveChambre(chambreId);
    }

    // http://localhost:8089/foyer/chambre/add-chambre
    @PostMapping("/add-chambre")
    @ResponseBody
    public Chambre addChambre(@RequestBody Chambre c) {
        Chambre chambre= chambreService.addChambre(c);
        return chambre;
    }

    // http://localhost:8089/foyer/chambre/update-chambre
    @PutMapping("/update-chambre")
    @ResponseBody
    public Chambre updateChambre(@RequestBody Chambre c) {
        Chambre chambre= chambreService.updateChambre(c);
        return chambre;
    }
    // http://localhost:8089/foyer/chambre/removeChambre
    @DeleteMapping("/removeChambre/{idChambre}")
    @ResponseBody
    public void removeChambre(@PathVariable("idChambre") Long idChambre) {
        chambreService.removeChambre(idChambre);
    }

    // http://localhost:8089/foyer/chambre/findByTypeCAndBlocIdBloc/DOUBLE/1
    @GetMapping("/findByTypeCAndBlocIdBloc/{typeChambre}/{idBloc}")
    @ResponseBody
    public List<Chambre> findByTypeCAndBlocIdBloc(@PathVariable("typeChambre") TypeChambre typeChambre, @PathVariable("idBloc")  Long idBloc) {
        List<Chambre> listChambresByTypeCAndBlocIdBloc = chambreService.findByTypeCAndBlocIdBloc(typeChambre,idBloc);
        return listChambresByTypeCAndBlocIdBloc;
    }

    // http://localhost:8089/foyer/chambre/findByReservationsEstValid/true
    @GetMapping("/findByReservationsEstValid/{estValid}")
    @ResponseBody
    public List<Chambre> findByReservationsEstValid(@PathVariable("estValid") Boolean estValid) {
        List<Chambre> listChambresByReservationsEstValid = chambreService.findByReservationsEstValid(estValid);
        return listChambresByReservationsEstValid;
    }

    // http://localhost:8089/foyer/chambre/findByBlocIdBlocAndBlocCapaciteBloc/1/100
    @GetMapping("/findByBlocIdBlocAndBlocCapaciteBloc/{idBloc}/{capaciteBloc}")
    @ResponseBody
    public List<Chambre> findByBlocIdBlocAndBlocCapaciteBloc(@PathVariable("idBloc") Long idBloc,@PathVariable("capaciteBloc")  Long capaciteBloc) {
        List<Chambre> listChambresByTypeCAndBlocIdBloc = chambreService.findByBlocIdBlocAndBlocCapaciteBlocGreaterThan(idBloc,capaciteBloc);
        return listChambresByTypeCAndBlocIdBloc;
    }


    // http://localhost:8089/foyer/chambre/getChambresParNomBloc/A
    @GetMapping("/getChambresParNomBloc/{nomBloc}")
    @ResponseBody
    public  List<Chambre> getChambresParNomBloc(@PathVariable("nomBloc") String nomBloc) {
        return chambreService.getChambresParNomBloc(nomBloc);
    }

    // http://localhost:8089/foyer/chambre/nbChambreParTypeEtBloc/DOUBLE/1
    @GetMapping("/nbChambreParTypeEtBloc/{type}/{idBloc}")
    @ResponseBody
    public  long nbChambreParTypeEtBloc(@PathVariable("type") TypeChambre type, @PathVariable("idBloc") long idBloc) {
        return chambreService.nbChambreParTypeEtBloc(type,idBloc);
    }
    // http://localhost:8089/foyer/chambre/getChambresNonReserveParNomFoyerEtTypeChambre/esprit foyer/SIMPLE
    @GetMapping("/getChambresNonReserveParNomFoyerEtTypeChambre/{nomFoyer}/{type}")
    @ResponseBody
    public  List<Chambre> getChambresNonReserveParNomFoyerEtTypeChambre(@PathVariable("nomFoyer") String nomFoyer,@PathVariable("type") TypeChambre type) {
        return chambreService.getChambresNonReserveParNomFoyerEtTypeChambre(nomFoyer,type);
    }

}

