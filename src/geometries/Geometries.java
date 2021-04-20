package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Geometries implements Intersectable {

    public List<Intersectable> lstOfGeometries;

    public void Geometries(){
        /*
        We chose to use LinkedList and not ArrayList because LinkedList is
        more memory efficient and faster at runtime than ArrayList
         */
               lstOfGeometries = new LinkedList<Intersectable>();
    }

    public Geometries(Intersectable... geometries){
        lstOfGeometries= new LinkedList<Intersectable>();
        for (Intersectable item: geometries)
        {
            lstOfGeometries.add(item);
        }
    }

    public void add(Intersectable... geometries){
        for (Intersectable item: geometries)
        {
            lstOfGeometries.add(item);
        }
    }

    public List<Point3D> findIntersections(Ray ray) {
        return null;
    }


}
