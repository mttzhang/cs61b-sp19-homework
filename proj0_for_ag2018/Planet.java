public class Planet {

    /**
     * instance variables
     */
    //current x position
    public double xxPos;

    //current y position
    public double yyPos;

    //current velocity in x direction
    public double xxVel;

    //current velocity in y direction
    public double yyVel;

    //its mass
    public double mass;

    //the name of the file, eg. jupiter.gif
    public String imgFileName;

    /**
    two constructors
     */
    public Planet (double xP, double yP, double xV, double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;

    }

    public Planet (Planet b){
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    /**
     * calculate the distance between Planet b and this Planet
     * @param b: a Planet
     * @return a double equal to the distance between Planet b and this Planet
     */
    public double calcDistance(Planet b){
        double dx = xxPos - b.xxPos;
        double dy = yyPos - b.yyPos;
        double r2 = Math.pow(dx,2)+Math.pow(dy,2);
        return Math.sqrt(r2);
    }

    /**
     * calculate the force exerted on this Planet by the given Planet b
     * @param b: a given Planet
     * @return a double describing the force exerted on this Planet by the given Planet b
     */
    public double calcForceExertedBy(Planet b){
        double G = 6.67 * Math.pow(10,-11);
        double r = calcDistance(b);
        return G * mass * b.mass/Math.pow(r,2);
    }

    /**
     * calculate the force exerted on this Planet by the given Planet b in x direction
     * @param b: a given Planet
     * @return the force exerted on this Planet by the given Planet b in x direction
     */
    public double calcForceExertedByX(Planet b){
        double dx = b.xxPos - xxPos;
        double F = calcForceExertedBy(b);
        double r = calcDistance(b);
        return F * dx / r;
    }

    /**
     * calculate the force exerted on this Planet by the given Planet b in y direction
     * @param b: a given Planet
     * @return the force exerted on this Planet by the given Planet b in x direction
     */
    public double calcForceExertedByY(Planet b){

        double dy = b.yyPos - yyPos;
        double F = calcForceExertedBy(b);
        double r = calcDistance(b);
        return F * dy / r;
    }

    /**
     * calculates the net X force exerted by all bodies in that array upon the current Planet
     * @param arrB:array of Planets
     * @return the net X force
     */
    public double calcNetForceExertedByX(Planet[] arrB){

        double sumF = 0;
        for (Planet b : arrB){
            if(!this.equals(b)){
                double Fx = calcForceExertedByX(b);
                sumF = sumF + Fx;
            }
        }
        return sumF;
    }

    /**
     * calculates the net Y force exerted by all bodies in that array upon the current Planet
     * @param arrB: array of Planets
     * @return the net Y force
     */
    public double calcNetForceExertedByY(Planet[] arrB){

        double sumF = 0;
        for (Planet b : arrB){
            if(!this.equals(b)){
                double Fy = calcForceExertedByY(b);
                sumF = sumF + Fy;
            }
        }
        return sumF;
    }

    /**
     * determines how much the forces exerted on the Planet will cause that Planet to accelerate
     * @param time small period of time
     * @param Fx force in x direction
     * @param Fy force in y direction
     * @return null
     */
    public void update(double time, double Fx, double Fy){

        double ax = Fx / mass;
        double ay = Fy / mass;

        xxVel = xxVel + time * ax;
        yyVel = yyVel + time * ay;

        xxPos = xxPos + xxVel * time;
        yyPos = yyPos + yyVel * time;
    }

    public void draw(){
        String imageToDraw = "/Users/mt/IdeaProjects/cs61b-homework/proj0/images/"+imgFileName;
        StdDraw.enableDoubleBuffering();
        StdDraw.picture(xxPos,yyPos,imageToDraw);
        StdDraw.show();
    }

}
