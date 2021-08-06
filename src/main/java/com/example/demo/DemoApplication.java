package com.example.demo;

import com.example.demo.component.AbstractDecisionMaker;
import com.example.demo.component.BatchDecisionMaker;
import com.example.demo.component.IDecisionAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EntityScan("com.example.demo.data.model")
@EnableJpaRepositories("com.example.demo.repository")
@SpringBootApplication
public class DemoApplication {

	private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);

	@Autowired
	private IDecisionAlgorithm decisionAlgorithm;

	public static void main(String[] args) {
		logger.debug("[DemoApplication] START");
		ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);
		AbstractDecisionMaker decisionMaker = context.getBean(BatchDecisionMaker.class);
		if(decisionMaker == null) {
			logger.error("[DemoApplication] decisionMaker is null");
		} else {
			boolean result = decisionMaker.run();
			logger.debug(String.format("[DemoApplication] run execution returned %b", result));
		}

		logger.debug("[DemoApplication] END");
	}

}
