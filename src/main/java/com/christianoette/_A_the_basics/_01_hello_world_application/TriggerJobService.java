package com.christianoette._A_the_basics._01_hello_world_application;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.stereotype.Component;

import com.christianoette._A_the_basics._01_hello_world_application.jobconfig.MyJob;
import com.christianoette._A_the_basics._01_hello_world_application.jobconfig.MyJob2;
@Component
public class TriggerJobService {

	
	private final JobLauncher jobLauncher;
	private final Job job;
	
	
	
	public TriggerJobService(JobLauncher jobLauncher,@MyJob2 Job job) {
		
		this.jobLauncher = jobLauncher;
		this.job = job;
	}




	public void runJob() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException, InterruptedException
	{
		
		JobParameters jobParams = new JobParametersBuilder()
				.addParameter("outputText",new JobParameter("my first app production"))
				.toJobParameters();
		
		this.jobLauncher.run(this.job, jobParams);
		
		Thread.sleep(3000);
		
		JobParameters jobParams2 = new JobParametersBuilder()
				.addParameter("outputText",new JobParameter("my first app production 2"))
				.toJobParameters();
		
		this.jobLauncher.run(this.job, jobParams2);
	}
}
