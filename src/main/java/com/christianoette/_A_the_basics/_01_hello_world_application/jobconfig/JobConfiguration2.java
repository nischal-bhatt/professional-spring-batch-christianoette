package com.christianoette._A_the_basics._01_hello_world_application.jobconfig;

import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobConfiguration2 {

	
	private final JobBuilderFactory jobBuilderFactory;
	
	
	private  final StepBuilderFactory stepBuilderFactory;
	
	
	
	public JobConfiguration2(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
		
		this.jobBuilderFactory = jobBuilderFactory;
		this.stepBuilderFactory = stepBuilderFactory;
	}



	//algorithm about what exactly u wanna do 
	// it is an object in memory 
	// job is a series of steps 
	@Bean
	@MyJob
	public Job anotherJob()
	{
		
		Step step =  this.stepBuilderFactory.get("step")
		.tasklet(new Tasklet() {

			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				
			Map<String,Object> mapper=	chunkContext.getStepContext().getJobParameters();
			
			String word = (String)mapper.get("outputText");
			
				System.out.println("another job lala" + word);
				
				
				return RepeatStatus.FINISHED;
			}
			
		}).build();
		
		return this.jobBuilderFactory.get("helloWorldJob")
		.start(step)
		.build();
	}
}
