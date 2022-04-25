package com.christianoette._A_the_basics._01_hello_world_application.jobconfig;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.beans.factory.annotation.Qualifier;

@Retention(RetentionPolicy.RUNTIME)
@Inherited()
@Qualifier("job2")
public @interface MyJob2 {

}
