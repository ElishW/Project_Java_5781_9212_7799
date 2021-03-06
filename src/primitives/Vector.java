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
        Vector v =  new Vector((this.head.getX())*s,(this.head.getY())*s,(this.head.getZ())*s);
        return v;
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
        Vector v = new Vector(this.head);
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

    /**
     * This function rotates a vector according to the angle theta sent as parameter
     * @param axis the vector to rotate
     * @param theta the angle of rotation
     */
    public void rotateVector(Vector axis, double theta) {
        double x = this.head.getX();
        double y = this.head.getY();
        double z = this.head.getZ();

        double u = axis.head.getX();
        double v = axis.head.getY();
        double w = axis.head.getZ();

        double v1 = u * x + v * y + w * z;

        double thetaRad = Math.toRadians(theta);

        double xPrime = u * v1 * (1d - Math.cos(thetaRad))
                + x * Math.cos(thetaRad)
                + (-w * y + v * z) * Math.sin(thetaRad);

        double yPrime = v * v1 * (1d - Math.cos(thetaRad))
                + y * Math.cos(thetaRad)
                + (w * x - u * z) * Math.sin(thetaRad);

        double zPrime = w * v1 * (1d - Math.cos(thetaRad))
                + z * Math.cos(thetaRad)
                + (-v * x + u * y) * Math.sin(thetaRad);

        this.head = new Point3D(xPrime, yPrime, zPrime);
    }

    @Override
    public String toString() {
        return "Vector{" +
                "head=" + head +
                '}';
    }
}