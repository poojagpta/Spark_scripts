//processing in Json format
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types._

val workflowsInnerSchema = (new StructType).add("exceptions", StringType)
val workflowsSchema = (new StructType).add("live", workflowsInnerSchema)
val rawJsonSchema = (new StructType).add("uuid", StringType).add("source", StringType).add("timestamp", LongType).add("id", StringType).add("blob", StringType).add("jobID", LongType).add("valJobID", LongType).add("timeout", StringType).add("data", StringType).add("workflows", StringType)


val athenaDF = spark.read.schema(rawJsonSchema).json("hdfs://BDTEST2/user/hive/gbi/edw/ai/fraud/ai_fraud_dep_stg/processing_directory/athena_events_data_stg_318099971")
athenaDF.printSchema()

//Read data that has no exceptions
val exceptions=athenaDF.filter($"workflows.live.exceptions".notEqual("{}")).filter($"source"==="itunes_content_purchase").select("uuid").collectAsList()

//Processing in text format
val rawDF = spark.read.format("text").load("hdfs://BDTEST2/user/hive/gbi/edw/ai/fraud/ai_fraud_dep_stg/processing_directory/athena_events_data_stg_318099971")
val data=rawDF.filter($"value".contains("1e4a4750-6998-11e8-baa4-713299be3e3f")).collectAsList()
print(data)
