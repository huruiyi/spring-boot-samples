package com.example.batch;

import com.example.batch.model.Singer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JobExecutionStatsListener extends JobExecutionListenerSupport {


  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public JobExecutionStatsListener(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public void afterJob(JobExecution jobExecution) {
    if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
      log.info(" --> Singers were saved to the database. Printing results ...");
      jdbcTemplate.query("SELECT first_name, last_name, song FROM SINGER", (rs, row) -> new Singer(rs.getString(1), rs.getString(2), rs.getString(3)))
          .forEach(singer -> log.info(singer.toString()));
    }
  }
}
