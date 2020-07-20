package bearmaps.proj2c;

import bearmaps.hw4.streetmap.Node;
import bearmaps.hw4.streetmap.StreetMapGraph;
import bearmaps.lab9.MyTrieSet;
import bearmaps.proj2ab.KDTree;
import bearmaps.proj2ab.Point;

import java.util.*;

/**
 * An augmented graph that is more powerful than a standard StreetMapGraph.
 * Specifically, it supports the following additional operations:
 *
 *
 * @author Alan Yao, Josh Hug, Mengting Zhang
 */
public class AugmentedStreetMapGraph extends StreetMapGraph {
    private KDTree pointsTree;
    private Map<Point, Long> pointsAndID;
    private MyTrieSet locationTrie;
    private Map<String, Node> cleanedLocationsMap;
    private List<Node> nodes;

    public AugmentedStreetMapGraph(String dbPath) {
        super(dbPath);
        nodes = new LinkedList<>();
        // You might find it helpful to uncomment the line below:
        nodes = this.getNodes();

        locationTrie = new MyTrieSet();
        cleanedLocationsMap = new HashMap<>();
        pointsAndID = new HashMap<>();
        List<Point> pointsList = new ArrayList<>();
        for (Node n: nodes) {
            if (n.name() != null) {
                String cleanName = cleanName(n.name());
                cleanedLocationsMap.put(cleanName, n);
                locationTrie.add(cleanName);

            }
            if (neighbors(n.id()).size() != 0) {
                Point p = new Point(n.lon(), n.lat());
                pointsList.add(p);
                pointsAndID.put(p, n.id());
            }
        }
        pointsTree = new KDTree(pointsList);

    }


    /**
     * For Project Part II
     * Returns the vertex closest to the given longitude and latitude.
     * @param lon The target longitude.
     * @param lat The target latitude.
     * @return The id of the node in the graph closest to the target.
     */
    public long closest(double lon, double lat) {
        Point nearestPoint = pointsTree.nearest(lon, lat);
        long id = pointsAndID.get(nearestPoint);
        return id;
    }

    private boolean validAToZ(char c) {
        return (('a' <= c) && (c <= 'z'));
    }

    private String cleanName(String name) {
        name = name.toLowerCase();
        name = name.replaceAll(" ", "");
        String newName = "";
        for (int i = 0; i < name.length(); i++) {
            if (validAToZ(name.charAt(i))) {
                newName = newName + name.charAt(i);
            }
        }
        return newName;
    }


    /**
     * For Project Part III (gold points)
     * In linear time, collect all the names of OSM locations that prefix-match the query string.
     * @param prefix Prefix string to be searched for. Could be any case, with our without
     *               punctuation.
     * @return A <code>List</code> of the full names of locations whose cleaned name matches the
     * cleaned <code>prefix</code>.
     */
    public List<String> getLocationsByPrefix(String prefix) {
        prefix = cleanName(prefix);

        List<String> cleanedName = locationTrie.keysWithPrefix(prefix);
        List<String> nameList = new LinkedList<>();

        if (cleanedName == null) {
            return nameList;
        }
        for (String name: cleanedName) {
            nameList.add(cleanedLocationsMap.get(name).name());
        }
        return nameList;
        //return new LinkedList<>();
    }

    /**
     * For Project Part III (gold points)
     * Collect all locations that match a cleaned <code>locationName</code>, and return
     * information about each node that matches.
     * @param locationName A full name of a location searched for.
     * @return A list of locations whose cleaned name matches the
     * cleaned <code>locationName</code>, and each location is a map of parameters for the Json
     * response as specified: <br>
     * "lat" -> Number, The latitude of the node. <br>
     * "lon" -> Number, The longitude of the node. <br>
     * "name" -> String, The actual name of the node. <br>
     * "id" -> Number, The id of the node. <br>
     */
    public List<Map<String, Object>> getLocations(String locationName) {

        String cleanedLocationName = cleanName(locationName);
        List<Map<String, Object>> list = new LinkedList<>();
        Node n0 = cleanedLocationsMap.get(cleanedLocationName);
        for (Node n: nodes) {
            if (n.name() != null) {
                if (n.name().equals(n0.name())) {
                    Map<String, Object> info = new HashMap<>();
                    info.put("lat", n.lat());
                    info.put("lon", n.lon());
                    info.put("name", n.name());
                    info.put("id", n.id());
                    list.add(info);
                }
            }
        }
        return list;
    }


    /**
     * Useful for Part III. Do not modify.
     * Helper to process strings into their "cleaned" form, ignoring punctuation and capitalization.
     * @param s Input string.
     * @return Cleaned string.
     */
    private static String cleanString(String s) {
        return s.replaceAll("[^a-zA-Z ]", "").toLowerCase();
    }

}
