package edu.eci.arsw.blueprints.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;
import org.springframework.stereotype.Component;

import java.util.*;

@Component("fs")
public class FiltroSubMuestreo extends BlueprintsPersistence {

    @Override
    public Map<Tuple<String, String>, Blueprint> getBlueprints() {
        return blueprints;
    }

    @Override
    public Blueprint filtrar(Blueprint bp) {
        Point[] arrayPuntos = bp.getPoints().toArray(new Point[0]);
        List<Point> listaPuntos = new ArrayList<>();
        boolean bandera = true;
        for (int i = 0; i < arrayPuntos.length-1; i++) {
            if (bandera){
                listaPuntos.add(arrayPuntos[i]);
                bandera = false;
            }
            else {
                bandera = true;
            }
        }
        return new Blueprint(bp.getAuthor(), bp.getName(), listaPuntos.toArray(new Point[0]));
    }
}
