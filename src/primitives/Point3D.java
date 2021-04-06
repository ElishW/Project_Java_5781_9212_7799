package primitives;

public class Point3D {
    Coordinate x;
    Coordinate y;
    Coordinate z;

    public Point3D(double x, double y, double z)
    {
        this(new Coordinate(x),new Coordinate(y),new Coordinate(z));
    }

    public Point3D(Coordinate x, Coordinate y, Coordinate z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Coordinate getX() {
        return x;
    }

    public Coordinate getY() {
        return y;
    }

    public Coordinate getZ() {
        return z;
    }

    public final static Point3D ZERO = new Point3D(0.0,0.0,0.0);
    public Point3D(Point3D p)
    {
        this.x=p.x;
        this.y=p.y;
        this.z=p.z;
    }

    public Vector subtract(Point3D p)
    {
        return new Vector(new Point3D(this.x.coord - p.x.coord, this.y.coord - p.y.coord,this.z.coord - p.z.coord));
    }

    public Point3D add(Vector v)
    {
        return new Point3D(this.x.coord+v.head.x.coord,this.y.coord+v.head.y.coord,this.z.coord+v.head.z.coord);
    }

    public double distanceSquared(Point3D p)
    {
        return Math.sqrt(distance(p));
    }

    public double distance(Point3D p)
    {
        return (this.x.coord-p.x.coord)*(this.x.coord-p.x.coord)+(this.y.coord-p.y.coord)*(this.y.coord-p.y.coord)+(this.z.coord-p.z.coord)*(this.z.coord-p.z.coord);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Point3D)) return false;
            Point3D other = (Point3D) o;
        return this.x.equals(other.x) && this.y.equals(other.y)&& this.z.equals(other.z);

    }

    @Override
    public String toString() {
        return "Point3D{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
