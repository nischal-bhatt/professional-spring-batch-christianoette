package com.christianoette._A_the_basics._01_hello_world;


import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootTest(classes = HelloWorldTest.TestConfig.class)
//@Disabled // TODO Remove disabled, if test won't start in your ide!
class HelloWorldTest {

	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;
	
    @Test
   // @Disabled // TODO Remove disabled, if test won't start in your ide!
    public void test() throws Exception {
    	
    	//data that you want to process
    	JobParameters jobParams =
    			new JobParametersBuilder()
    			.addParameter("outputText", new JobParameter("deepak kalal"))
    			.toJobParameters();
    	
    	//how u want to run the job
        this.jobLauncherTestUtils.launchJob(jobParams);
    }
    
    @Configuration
    @EnableBatchProcessing
    //numerous services are configures which you can use
    static class TestConfig{
    	
    	@Autowired
    	private JobBuilderFactory jobBuilderFactory;
    	
    	@Autowired
    	private StepBuilderFactory stepBuilderFactory;
    	
    	//algorithm about what exactly u wanna do 
    	// it is an object in memory 
    	// job is a series of steps 
    	@Bean
    	public Job helloWorldJob()
    	{
    		
    		Step step =  this.stepBuilderFactory.get("step")
    		.tasklet(new Tasklet() {

				@Override
				public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
					
				Map<String,Object> mapper=	chunkContext.getStepContext().getJobParameters();
				
				String word = (String)mapper.get("outputText");
				
					System.out.println(word);
					
					
					return RepeatStatus.FINISHED;
				}
    			
    		}).build();
    		
    		return this.jobBuilderFactory.get("helloWorldJob")
    		.start(step)
    		.build();
    	}
    	
    	
    	@Bean
    	public JobLauncherTestUtils utils()
    	{
    		return new JobLauncherTestUtils();
    	}
    	
    	
    }
}
