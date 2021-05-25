package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static primitives.Util.alignZero;

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



    @Override
    public List<GeoPoint> findGeoIntersections(Ray _ray) {
        List<GeoPoint> result = null;
        for (Intersectable item : lstOfGeometries) {
            //get intersections points of a particular item from lstOfGeometries
            List<Point3D> itempoints = item.findIntersections(_ray);
            if(itempoints!= null){
                //first time initialize result to new LinkedList
                if(result== null){
                    result= new LinkedList<>();
                }
                //add all item points and his geometry to the resulting list
                for (Point3D interPoint : itempoints)
                {
                    result.add(new GeoPoint((Geometry)item,interPoint ));
                }

            }
        }
        return result;
    }




}