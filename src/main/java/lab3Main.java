import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Map;

public class lab3Main {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("lab3");
        JavaSparkContext sc = new JavaSparkContext(conf);
        Map<Long, String> RDD = sc.textFile("file.csv")
                    .zipWithIndex()
                    .filter(lineWithInd -> lineWithInd._2() != 0)
                    .map(lineWithInd -> getAirportObj(lineWithInd._1()))
                    .mapToPair(airport -> new Tuple2<>(airport.getID(), airport.getName()))
                    .collectAsMap();
        sc.textFile("sample.csv")
                .zipWithIndex()
                .filter(lineWithInd -> lineWithInd._2() != 0)
                .map(lineWithInd -> getFlightObj(lineWithInd._1()))
                .mapToPair(flight -> new Tuple2<>(
                                            new Tuple2<>(flight.getOriginID(), flight.getDestID()),
                                            new FlightInfoSer(flight.getDelayTime(), flight.getIfCancelled())
                        )
                )
                .groupByKey()
                .map(x -> {
                        Iterator<FlightInfoSer> flightInfo = x._2().iterator();
                        Long cancelled = 0L, countCans = 0L, countDel = 0L, maxDelTime = 0L, delTime = 0L;
                        while (flightInfo.hasNext()) {
                            FlightInfoSer nextInfo = flightInfo.next();
                            delTime = nextInfo.getDelayTime();
                            if (delTime > 0) {
                                countDel++;
                            }
                            maxDelTime = max(maxDelTime, delTime);
                        }
                })

    }

}
