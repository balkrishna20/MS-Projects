package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.context.config.annotation.EnableContextCredentials;
import org.springframework.cloud.aws.context.config.annotation.EnableContextRegion;
import org.springframework.cloud.aws.jdbc.config.annotation.EnableRdsInstance;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
//create table profile(id Integer,firstname varchar(20), lastname varchar(20),email varchar(20),address varchar(20),organization varchar(20),aboutMyself varchar(20))



@SpringBootApplication
@EnableJpaRepositories
//@EnableRdsInstance(databaseName="profile", dbInstanceIdentifier="i-4318bcf6", username="root",password="root")
//@EnableContextCredentials(accessKey="AKIAJZ3NEXEFDFMJ2YOQ", secretKey="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")
//@EnableContextRegion(region="us-west-1")
@EnableScheduling

public class Cmpe275Lab2Application {

	public static void main(String[] args) {
		SpringApplication.run(Cmpe275Lab2Application.class, args);
	}
}
