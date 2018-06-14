package org.tungnt.springcms.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AnoUpload{
	
	@Autowired
	TaskExecutor taskEx;
	
	public void executeTask(){
		taskEx.execute(()->{
			for(int i=0; i<=1000; i++) {
				System.out.println("inside "+Thread.currentThread().getName()+" "+i);
			}	
		});
	}
}
