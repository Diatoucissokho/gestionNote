package sn.edu.isepdiamniadio.dbe.GestionNote.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DataSourceConfiguration {
   @Value("${db.url}")
    private  String dbUrl;
    @Value("${db.username}")
    private  String dbUsername;
    @Value("${db.password}")
    private  String dbPassword;
    @Value("${db.driver}")
    private  String dbDriver;
    @Bean
    public DriverManagerDataSource dataSource(){
        DriverManagerDataSource dataSource = new  DriverManagerDataSource();
        dataSource.setUrl(dbUrl);
        dataSource.setUsername(dbUsername);
        dataSource.setPassword(dbPassword);
        dataSource.setDriverClassName(dbDriver);
        return  dataSource;
    }
    public JdbcTemplate jdbcTemplate(DriverManagerDataSource dataSource){
        return  new JdbcTemplate(dataSource);
    }

}
