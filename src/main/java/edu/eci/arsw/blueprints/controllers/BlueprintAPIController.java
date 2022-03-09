/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.controllers;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.services.BluePrintsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author hcadavid
 */
@RestController
@RequestMapping(value = "/blueprints")
public class BlueprintAPIController {

    @Autowired
    BluePrintsServices bps;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAllBluePrints() {
        try {
            Set<Blueprint> data = bps.getAllBlueprints();
            return new ResponseEntity<>(data, HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error al obtener los planos", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{authorName}",method = RequestMethod.GET)
    public ResponseEntity<?> getBluePrintsByAuthor(@PathVariable("authorName") String authorName){
        try {
            Set<Blueprint> data = bps.getBlueprintsByAuthor(authorName);
            return new ResponseEntity<>(data, HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error 404", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{authorName}/{bpname}",method = RequestMethod.GET)
    public ResponseEntity<?> getBluePrint(@PathVariable("authorName") String authorName, @PathVariable("bpname") String bpName){
        try {
            Blueprint data = bps.getBlueprint(authorName, bpName);
            return new ResponseEntity<>(data, HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error 404", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ResponseEntity<?> addBluePrint(@RequestBody Blueprint newBluePrint){
        try {
            bps.addNewBlueprint(newBluePrint);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception ex) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error 404", HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(value = "/{authorName}/{bpname}", method = RequestMethod.PUT)
    public  ResponseEntity<?> updateBluePrint(@PathVariable("authorName") String authorName, @PathVariable("bpname") String bpName, @RequestBody Blueprint updateBluePrint){
        try {
            Blueprint planoPorEditar = bps.getBlueprint(authorName, bpName);
            StringBuilder respuesta = new StringBuilder();
            respuesta.append("Antiguos puntos del plano: " + planoPorEditar.getPoints().toString() + "\n");
            respuesta.append("Nuevos puntos del plano: " + updateBluePrint.getPoints().toString());
            planoPorEditar.setPoints(updateBluePrint.getPoints());
            return new ResponseEntity<>(respuesta.toString(), HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error", HttpStatus.FORBIDDEN);
        }
    }


}

