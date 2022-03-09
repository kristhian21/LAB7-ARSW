/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.persistence;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.persistence.impl.Tuple;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author hcadavid
 */
public abstract class BlueprintsPersistence {

    public final AbstractMap<Tuple<String,String>,Blueprint> blueprints = new ConcurrentHashMap<>();
    
    /**
     * 
     * @param bp the new blueprint
     * @throws BlueprintPersistenceException if a blueprint with the same name already exists,
     *    or any other low-level persistence error occurs.
     */
    public void saveBlueprint(Blueprint bp) throws BlueprintPersistenceException{
        if (blueprints.containsKey(new Tuple<>(bp.getAuthor(),bp.getName()))){
            throw new BlueprintPersistenceException("The given blueprint already exists: "+bp);
        }
        else{
            blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
        }
    };
    
    /**
     * 
     * @param author blueprint's author
     * @param bprintname blueprint's author
     * @return the blueprint of the given name and author
     * @throws BlueprintNotFoundException if there is no such blueprint
     */
    public Blueprint getBlueprint(String author,String bprintname) throws BlueprintNotFoundException{
        Set<Tuple<String,String>> llaves = blueprints.keySet();
        Blueprint result = null;
        for (Tuple<String,String> t: llaves) {
            if (t.getElem1().equals(author) && t.getElem2().equals(bprintname)){
                result = blueprints.get(t);
                return filtrar(result);
            }
        }
        return result;
    };

    /**
     *
     * @param author blueprint's author
     * @return all the blueprints of the given author
     * @throws BlueprintNotFoundException if the given author doesn't exist
     */
    public Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException {
        HashSet<Blueprint> result = new HashSet<>();
        Set<Tuple<String,String>> llaves = blueprints.keySet();
        for (Tuple<String,String> t: llaves) {
            if (t.getElem1().equals(author)){
                result.add(filtrar(blueprints.get(t)));
            }
        }
        return result;
    }

    public abstract Map<Tuple<String, String>, Blueprint> getBlueprints();

    public abstract Blueprint filtrar(Blueprint bp);
    
}
