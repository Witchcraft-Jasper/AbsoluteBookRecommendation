import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class WebLogDriver {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        conf.addResource("classpath:/resources/core-site.xml");
        conf.addResource("classpath:/resources/hdfs-site.xml");
        conf.addResource("classpath:/resources/mapred-site.xml");
        conf.addResource("classpath:/resources/yarn-site.xml");
        Job job = null;
        job = Job.getInstance(conf);
        job.setJarByClass(WebLogDriver.class);
        job.setJobName("Min temperature");
        job.setMapperClass(WebLogMapper.class);
        job.setReducerClass(WebLogReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FloatWritable.class);
        FileInputFormat.addInputPath(job, new Path("hdfs://10.10.0.10:9000/weblog/in/coolshell_20140212.log"));
        FileOutputFormat.setOutputPath(job, new Path("hdfs://10.10.155.101:9000/weblog/out/"));
        // System.exit(job.waitForCompletion(true) ? 0 : 1);
        job.submit();
    }
}
