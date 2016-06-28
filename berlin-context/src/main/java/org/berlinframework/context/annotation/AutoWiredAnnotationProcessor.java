package org.berlinframework.context.annotation;

import org.berlinframework.beans.factory.BeanFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

public class AutoWiredAnnotationProcessor {
	private BeanFactory beanFactory;

	public void setBeanFactory(BeanFactory beanFactory) {
		this.beanFactory = beanFactory;
	}

	public void process() {
		this.beanFactory.getBeans().forEach((k, v) -> {
			Class<?> beanClass = v.getClass();

			Method[] methods = beanClass.getDeclaredMethods();
			for (Method method : methods) {
				Annotation[] annotations = method.getAnnotations();
				for (Annotation annotation : annotations) {
					if (annotation instanceof AutoWired) {
						Class<?>[] paramTypes = method.getParameterTypes();
						Object[] params = new Object[paramTypes.length];

						int i = 0;
						for (Class<?> paramClass : paramTypes) {
							Object bean = this.beanFactory.getBean(paramClass.getName());
							params[i++] = bean;
						}

						method.setAccessible(true);
						try {
							method.invoke(v, params);
						} catch (Exception e) {
							throw new RuntimeException(e);
						}
					}
				}
				// Check for AutoWired fields and inject
			}
		});
	}
}
