import java.util.HashMap;
import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

public class CleanReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
    public void reduce(Text key, Iterable<IntWritable> values, Context context)
            throws IOException, InterruptedException {
        int sumUnits = 0;

        for (IntWritable val : values) {
            sumUnits += val.get();
        }

        String keyString = key + "," + sumUnits;

        context.write(new Text(key), new IntWritable(sumUnits));
    }
}
