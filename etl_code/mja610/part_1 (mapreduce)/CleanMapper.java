import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class CleanMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    @Override
    public void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {

        String line = value.toString();
        String[] lineArray = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

        if (lineArray[0].equals("Project ID")) {
            // do not count header row
        } else {
            String postCode = lineArray[8];
            String completedDate = lineArray[3];
            int countedUnits = Integer.parseInt(lineArray[39]);
            
            if (completedDate.isEmpty()) {
                // remove row
            } else if (postCode.isEmpty()) {
                // remove row
            } else {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                LocalDate formattedDate = LocalDate.parse(completedDate, formatter);
                String finalDate = String.format("%1$s/%2$d", formattedDate.getYear(), formattedDate.getMonthValue());

                context.write(new Text(postCode + "," + finalDate.toString()),
                        new IntWritable(countedUnits));
            }
        }
    }
}
