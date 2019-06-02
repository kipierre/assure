package org.cephas.acdia.springBatch;



import javax.sql.DataSource;

import org.cephas.acdia.model.User;
import org.cephas.acdia.processor.UserItemProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;



@Configuration
@EnableBatchProcessing
@PropertySource("classpath:application.properties")
public class SprindBatchcsvtodb  {
    @Value("${mysql.driverClassName}")
    private String driverClassName;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;


    // To resolve ${} in @Value

    // Data Source



    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Qualifier("dataSource")
    @Autowired
    private DataSource dataSource;

    // To resolve ${} in @Value



    @Bean
    public FlatFileItemReader<User> reade() {
        FlatFileItemReader<User> reader = new FlatFileItemReader<User>();
        reader.setResource(new ClassPathResource("users.csv"));
        reader.setLineMapper(new DefaultLineMapper<User>() {
            {
                setLineTokenizer(new DelimitedLineTokenizer() {
                    {
                        setNames(new String[] { "name" });
                    }
                });
                setFieldSetMapper(new BeanWrapperFieldSetMapper<User>() {
                    {
                        setTargetType(User.class);
                    }
                });

            }
        });

        return reader;
    }

    @Bean
    public JdbcBatchItemWriter<User> write() {
        JdbcBatchItemWriter<User> writer = new JdbcBatchItemWriter<>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<User>());
        writer.setSql("INSERT INTO user(name) VALUES (:name)");
        writer.setDataSource(dataSource);
        return writer;
    }

    @Autowired
    private  UserItemProcessor itemProcessor;


    @Bean
    public Step step0() {
        return stepBuilderFactory.get("step1").<User, User>chunk(3).reader(reade()).processor(itemProcessor)
                .writer(write()).build();
    }

    @Bean
    public Job importUserJob() {
        return jobBuilderFactory.get("importUserJob").incrementer(new RunIdIncrementer()).flow(step0()).end().build();
    }
}
