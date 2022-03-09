/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;
import org.springframework.stereotype.Component;


import java.util.Map;

/**
 *
 * @author hcadavid
 */
@Component("inMemory")
public class InMemoryBlueprintPersistence extends BlueprintsPersistence{

    public InMemoryBlueprintPersistence() {
        // Plano 1
        Point[] pts=new Point[]{new Point(140, 140),new Point(115, 115)};
        Blueprint bp=new Blueprint("Juan", "plano1 ",pts);
        blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
        // Plano 2
        Point[] pts1=new Point[]{new Point(100, 70),new Point(150, 150)};
        Blueprint bp1=new Blueprint("David", "plano_casa ",pts1);
        blueprints.put(new Tuple<>(bp1.getAuthor(),bp1.getName()), bp1);
        // Plano 3
        Point[] pts2=new Point[]{new Point(50, 60),new Point(80, 100)};
        Blueprint bp2=new Blueprint("Juan", "plano2",pts2);
        blueprints.put(new Tuple<>(bp2.getAuthor(),bp2.getName()), bp2);
        // Plano 4
        Point[] pts3=new Point[]{new Point(120, 120),new Point(110, 95)};
        Blueprint bp3=new Blueprint("Carlos", "plano_edificio",pts3);
        blueprints.put(new Tuple<>(bp3.getAuthor(),bp3.getName()), bp3);
        // Plano 5
        Point[] pts4=new Point[]{new Point(180, 160),new Point(15, 15)};
        Blueprint bp4=new Blueprint("Maria", "plano_estadio",pts4);
        blueprints.put(new Tuple<>(bp4.getAuthor(),bp4.getName()), bp4);
        // Plano 6
        Point[] pts5=new Point[]{new Point(220, 260),new Point(35, 55)};
        Blueprint bp5=new Blueprint("John", "plano_puente",pts5);
        blueprints.put(new Tuple<>(bp5.getAuthor(),bp5.getName()), bp5);
    }

    @Override
    public Map<Tuple<String, String>, Blueprint> getBlueprints() {
        return blueprints;
    }

    @Override
    public Blueprint filtrar(Blueprint bp) {
        return bp;
    }
}
