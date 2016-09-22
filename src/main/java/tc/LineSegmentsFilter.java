package tc;

import common.data.TCLine;
import common.data.TCPoint;
import org.apache.spark.api.java.function.Function;
import scala.Tuple2;

public class LineSegmentsFilter implements
        Function<Tuple2<String, Tuple2<Tuple2<Integer, TCLine>, Tuple2<Integer, TCLine>>>, Boolean>{

    private static final int time_eps = 3;

    public LineSegmentsFilter()
    {

    }

    @Override
    public Boolean call(Tuple2<String, Tuple2<Tuple2<Integer, TCLine>, Tuple2<Integer, TCLine>>> input) throws Exception {

        // TODO: prune segement pair: dist_min(MBR1, MBR2) > epsilon
        //MBR mbr1 = new MBR();
        //MBR mbr2 = new MBR();

        int objectId1 = input._2._1._1;
        int objectId2 = input._2._2._1;
        TCLine line1 = input._2._1._2;
        TCLine line2 = input._2._2._2;



        if(Math.abs(((TCPoint)line1.getP1()).getTimeStamp()
                - ((TCPoint)line2.getP2()).getTimeStamp()) > time_eps ||
                Math.abs(((TCPoint)line2.getP2()).getTimeStamp()
                        - ((TCPoint)line1.getP1()).getTimeStamp()) > time_eps)
        {
            return false;
        }

        return true;
    }
}
