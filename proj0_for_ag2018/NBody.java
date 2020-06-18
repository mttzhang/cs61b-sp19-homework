public class NBody {
    /**
     * read radius data in the given file
     * @param fileName the file name
     * @return radius of the universe
     */
    public static double readRadius(String fileName){
        In in = new In(fileName);
        int first = in.readInt();
        double second = in.readDouble();
        return second;
    }

    public static Planet[] readBodies(String fileName){
        In in = new In(fileName);
        int first = in.readInt();
        Planet[] planets= new Planet[first];
        double second = in.readDouble();
        for (int i = 0 ; i < first; i++){
            planets[i] = new Planet (in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readString());
        }
        return planets;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planets = readBodies(filename);

        for(double time = 0; time <= T; time+=dt){
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];
            int i = 0;
            for(Planet b:planets){
                xForces[i] = b.calcNetForceExertedByX(planets);
                yForces[i] = b.calcNetForceExertedByY(planets);
                i++;
            }
            for(int j = 0; j < planets.length; j++){
                planets[j].update(dt,xForces[j],yForces[j]);
            }

            //draw the background
            String backg = "/Users/mt/IdeaProjects/cs61b-homework/proj0/images/starfield.jpg";
            StdDraw.enableDoubleBuffering();
            StdDraw.setScale(-radius,radius);
            StdDraw.clear();
            StdDraw.picture(0,0,backg);

            //draw the bodies in Planet array
            for (Planet b:planets){
                b.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }

    }

}
