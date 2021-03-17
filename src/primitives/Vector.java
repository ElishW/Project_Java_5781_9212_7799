package primitives;

public class Vector {
    Point3D head;

    public Vector(double x ,double y, double z)
    {
        Point3D p;
        p= new Point3D(x,y,z);
        if(p.equals(Point3D.ZERO))
        {
            throw new IllegalArgumentException("cannot be vector 0");
        }
        head =new Point3D(x,y,z);
    }
    public Vector(Point3D p)
    {
        if(p.equals(Point3D.ZERO))
        {
            throw new IllegalArgumentException("cannot be vector 0");
        }
        head = new Point3D(p);
    }
    public Vector(Vector v)
    {
        if(v.head.equals(Point3D.ZERO))
        {
            throw new IllegalArgumentException("cannot be vector 0");
        }
        head = new Point3D(v.head);
    }

    public Vector add(Vector v)
    {
        return new Vector(this.head.x.coord+v.head.x.coord,this.head.y.coord+v.head.y.coord,this.head.z.coord+v.head.z.coord);
    }

    public Vector substract(Vector v)
    {
        return new Vector(this.head.x.coord-v.head.x.coord,this.head.y.coord-v.head.y.coord,this.head.z.coord-v.head.z.coord);
    }

    public Vector substract(Point3D p)
    {
        return new Vector(this.head.x.coord-p.x.coord,this.head.y.coord-p.y.coord,this.head.z.coord-p.z.coord);
    }

    public Vector scale(int s)
    {
        return new Vector((this.head.x.coord)*s,(this.head.y.coord)*s,(this.head.z.coord)*s);
    }

    public double dotProduct(Vector v)
    {
        return (this.head.x.coord)*(v.head.x.coord) + (this.head.y.coord)*(v.head.y.coord)+ (this.head.z.coord)*(v.head.z.coord);
    }

    public Vector crossProduct(Vector v)
    {
        return new Vector((this.head.y.coord)*(v.head.z.coord)-(this.head.z.coord)*(v.head.y.coord),(this.head.z.coord)*(v.head.x.coord)-(this.head.x.coord)*(v.head.z.coord), (this.head.x.coord)*(v.head.y.coord)-(this.head.y.coord)*(v.head.x.coord));
    }

    public double lengthSquared()
    {
        return (this.head.x.coord*this.head.x.coord + this.head.y.coord*this.head.y.coord +this.head.z.coord*this.head.z.coord);
    }

    public double length()
    {
        return Math.sqrt(lengthSquared());
    }

    public Vector normalized()
    {
        Vector v = new Vector(this);
        v.normalize();
        return v;
    }

    public Vector normalize()
    {
        if(this.length()==0)
        {
            throw new ArithmeticException("cannot divide by 0");
        }
        this.head= new Point3D((this.head.x.coord)/this.length(),(this.head.y.coord)/this.length(),(this.head.z.coord)/this.length());
        return this;
    }

 public Point3D getHead() {
        return head;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vector vector = (Vector) o;

        return head.equals(vector.head);
    }

    @Override
    public String toString() {
        return "Vector{" +
                "head=" + head +
                '}';
    }
}
