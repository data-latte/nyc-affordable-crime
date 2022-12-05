import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class CleanMapper_Final extends Mapper<LongWritable, Text, Text, IntWritable> {
    @Override
    public void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {

        String line = value.toString();
        String[] lineArray = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

        if (lineArray[0].equals("POLICE_PRECINCT")) {
            // do not count header row
        } else {
            String precinct = lineArray[0];
            String completedDate = lineArray[1];
            int countedUnits = Integer.parseInt(lineArray[2]);

            context.write(new Text(precinct + "," + completedDate),
                    new IntWritable(countedUnits));

        }
    }
}
