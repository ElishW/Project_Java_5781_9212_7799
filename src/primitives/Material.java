package primitives;


public class Material {


    public double kS=0,kD=0;
    public int nShininess=0;

    public Material(double kD, double kS, int nShininess) {
        this.kD = kD;
        this.kS = kS;
        this.nShininess = nShininess;
    }

    public Material() {
        this.kD = 0;
        this.kS = 0;
        this.nShininess = 0;
    }
    // chaining setters (with builder pattern)
    public Material setKs(double kS) {
        this.kS = kS;
        return this;
    }

    public Material setKd(double kD) {
        this.kD = kD;
        return this;
    }

    public Material setShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }

    /*
    Getters

    public double getKs() {
        return kS;
    }

    public double getKd() {
        return kD;
    }

    public int getShininess() {
        return nShininess;
    }

     */
}
