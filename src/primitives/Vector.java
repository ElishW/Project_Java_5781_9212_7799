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
        return new Vector(this.head.getX()+v.head.getX(),this.head.getY()+v.head.getY(),this.head.getZ()+v.head.getZ());
    }

    public Vector substractV(Vector v)
    {
        return new Vector(this.head.getX()-v.head.getX(),this.head.getY()-v.head.getY(),this.head.getZ()-v.head.getZ());
    }

    public Vector substractP(Point3D p)
    {
        return new Vector(this.head.getX()-p.getX(),this.head.getY()-p.getY(),this.head.getZ()-p.getZ());
    }

    public Vector scale(double s)
    {
        return new Vector((this.head.getX())*s,(this.head.getY())*s,(this.head.getZ())*s);
    }

    public double dotProduct(Vector v)
    {
        return (this.head.getX())*(v.head.getX()) + (this.head.getY())*(v.head.getY())+ (this.head.getZ())*(v.head.getZ());
    }

    public Vector crossProduct(Vector v)
    {
        return new Vector((this.head.getY())*(v.head.getZ())-(this.head.getZ())*(v.head.getY()),(this.head.getZ())*(v.head.getX())-(this.head.getX())*(v.head.getZ()), (this.head.getX())*(v.head.getY())-(this.head.getY())*(v.head.getX()));
    }

    public double lengthSquared()
    {
        return (this.head.getX()*this.head.getX() + this.head.getY()*this.head.getY() +this.head.getZ()*this.head.getZ());
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
        this.head= new Point3D((this.head.getX())/this.length(),(this.head.getY())/this.length(),(this.head.getZ())/this.length());
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
