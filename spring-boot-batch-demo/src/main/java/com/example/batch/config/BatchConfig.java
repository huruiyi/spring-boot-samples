package com.example.batch.config;

import com.example.batch.JobExecutionStatsListener;
import com.example.batch.model.Singer;
import com.example.batch.SingerItemProcessor;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Slf4j
@Configuration
@EnableBatchProcessing
public class BatchConfig {

  private final JobBuilderFactory jobBuilderFactory;
  private final StepBuilderFactory stepBuilderFactory;
  private final DataSource dataSource;
  private final SingerItemProcessor itemProcessor;


  public BatchConfig(JobBuilderFactory jobBuilder, StepBuilderFactory stepBuilder, DataSource dataSource, SingerItemProcessor itemProcessor) {
    this.jobBuilderFactory = jobBuilder;
    this.stepBuilderFactory = stepBuilder;
    this.dataSource = dataSource;
    this.itemProcessor = itemProcessor;
  }

  @Bean
  public Job job(JobExecutionStatsListener listener) {
    return jobBuilderFactory.get("singerJob").listener(listener).flow(step1()).next(step2()).next(step3()).end().build();
  }


  @Bean
  public FlatFileItemReader<Singer> itemReader() {
    FlatFileItemReader<Singer> itemReader = new FlatFileItemReader<>();
    itemReader.setResource(new ClassPathResource("support/test-data.csv"));

    DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
    delimitedLineTokenizer.setNames("firstName", "lastName", "song");

    BeanWrapperFieldSetMapper<Singer> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
    fieldSetMapper.setTargetType(Singer.class);

    DefaultLineMapper<Singer> singerDefaultLineMapper = new DefaultLineMapper<>();
    singerDefaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
    singerDefaultLineMapper.setFieldSetMapper(fieldSetMapper);

    itemReader.setLineMapper(singerDefaultLineMapper);
    return itemReader;
  }

  @Bean
  public JdbcBatchItemWriter<Singer> itemWriter() {
    JdbcBatchItemWriter<Singer> itemWriter = new JdbcBatchItemWriter<>();
    itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
    itemWriter.setSql("INSERT INTO SINGER (first_name, last_name, song) VALUES (:firstName, :lastName, :song)");
    itemWriter.setDataSource(dataSource);
    return itemWriter;
  }

  @Bean
  protected Step step1() {
    StepBuilder stepBuilder = stepBuilderFactory.get("step1");
    return stepBuilder.<Singer, Singer>chunk(10).reader(itemReader()).processor(itemProcessor).writer(itemWriter()).build();
  }


  @Bean
  public Step step2() {
    StepBuilder stepBuilder = stepBuilderFactory.get("step2");
    return stepBuilder.tasklet((contribution, chunkContext) -> {
      log.info("step 2");
      return RepeatStatus.FINISHED;
    }).build();
  }

  @Bean
  public Step step3() {
    StepBuilder stepBuilder = stepBuilderFactory.get("step3");
    return stepBuilder.tasklet((contribution, chunkContext) -> {
      log.info("step 3");
      return RepeatStatus.FINISHED;
    }).build();
  }


}
