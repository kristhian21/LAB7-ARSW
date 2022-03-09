package edu.eci.arsw.blueprints.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;
import org.springframework.stereotype.Component;

import java.util.*;

@Component("fr")
public class FiltroRedundancia extends BlueprintsPersistence {

    @Override
    public Blueprint filtrar(Blueprint bp){
        Point[] arrayPuntos = bp.getPoints().toArray(new Point[0]);
        List<Point> listaPuntos = new ArrayList<>();
        for (int i = 0; i < arrayPuntos.length-2; i++) {
            if (arrayPuntos[i].getX() == arrayPuntos[i+1].getX() && arrayPuntos[i].getY() == arrayPuntos[i+1].getY()){
                if (listaPuntos.size() > 0){
                    if (!(listaPuntos.get(listaPuntos.size()-1).getX() == arrayPuntos[i+1].getX() && listaPuntos.get(listaPuntos.size()-1).getY() == arrayPuntos[i+1].getY())){
                        listaPuntos.add(arrayPuntos[i+1]);
                    }
                }
                else {
                    listaPuntos.add(arrayPuntos[i+1]);
                }
            }
            else {
                listaPuntos.add(arrayPuntos[i+1]);
            }
        }
        return new Blueprint(bp.getAuthor(), bp.getName(), listaPuntos.toArray(new Point[0]));
    }

    @Override
    public Map<Tuple<String, String>, Blueprint> getBlueprints() {
        return blueprints;
    }
}
