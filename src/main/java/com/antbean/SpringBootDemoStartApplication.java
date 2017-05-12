package com.antbean;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.catalina.filters.RemoteIpFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootDemoStartApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDemoStartApplication.class, args);
	}

	@Bean
	public RemoteIpFilter remoteIpFilter() {
		return new RemoteIpFilter();
	}

	@Bean
	public FilterRegistrationBean testFilterRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(new MyFilter());
		registration.addUrlPatterns("/*");
		registration.addInitParameter("tag", "lmh");
		registration.setName("MyFilter");
		registration.setOrder(1);
		return registration;
	}

	public class MyFilter implements Filter {
		@Override
		public void destroy() {
			System.out.println("WebConfiguration.MyFilter.destroy()");
		}

		@Override
		public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
				throws IOException, ServletException {
			System.out.println("WebConfiguration.MyFilter.doFilter()");
			chain.doFilter(request, response);
		}

		@Override
		public void init(FilterConfig config) throws ServletException {
			System.out.println("WebConfiguration.MyFilter.init()");
			String tag = config.getInitParameter("tag");
			System.out.println(tag);
		}
	}
}
