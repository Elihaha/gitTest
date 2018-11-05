package com.bst.mallh5.shiro;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;
import org.springframework.stereotype.Component;

@Component("subjectFactory")
public class StatelessDefaultSubjectFactory extends DefaultWebSubjectFactory{

	@Override
	public Subject createSubject(SubjectContext context) {
		// 不创建session
		context.setSessionCreationEnabled(false);
		return super.createSubject(context);
	}
	
}
