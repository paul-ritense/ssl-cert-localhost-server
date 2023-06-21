package com.nicordesigns.soapserver;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

    // Configure our Spring Beans

    @Bean
    public ServletRegistrationBean messageDispactherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/spring-ws-insurance/*");
    }

    @Bean(name = "getInsurance")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema insuranceServiceSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("InsuranceServicePort");
        wsdl11Definition.setLocationUri("/spring-ws-insurance");
        wsdl11Definition.setTargetNamespace("http://www.nicordesigns.com/spring-ws-insurance");
        wsdl11Definition.setSchema(insuranceServiceSchema);

        return wsdl11Definition;

    }

    @Bean
    public XsdSchema insuranceServiceSchema() {
        return new SimpleXsdSchema(new ClassPathResource("InsuranceService.xsd"));
    }
}
