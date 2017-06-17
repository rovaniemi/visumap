package visumap.Tools;

/**
 * CoordinateDistance luokkaa käytetään ainoastaan kahden pisteen välisen etäisyyden laskemiseen.
 */

public class CoordinateDistance {

    public final static double AVERAGE_RADIUS_OF_EARTH_CM = 6371 * 1000 * 100;



    public int distance(double lat1, double lon1, double lat2, double lon2) {

        double latDistance = Math.toRadians(lat1 - lat2);
        double lngDistance = Math.toRadians(lon1 - lon2);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lngDistance / 2) * Math.sin(lngDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return (int) (Math.round(AVERAGE_RADIUS_OF_EARTH_CM * c));
    }
}
