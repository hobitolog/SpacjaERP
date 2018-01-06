package pl.poznan.put.student.spacjalive.erp.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import pl.poznan.put.student.spacjalive.erp.converter.*;
import pl.poznan.put.student.spacjalive.erp.service.EmployeeService;
import pl.poznan.put.student.spacjalive.erp.service.EquipmentService;
import pl.poznan.put.student.spacjalive.erp.service.EventService;
import pl.poznan.put.student.spacjalive.erp.service.PositionService;

@EnableWebMvc
@org.springframework.context.annotation.Configuration
@ComponentScan(basePackages = "pl.poznan.put.student.spacjalive.erp")
public class Configuration extends WebMvcConfigurerAdapter{

    @Autowired
    PositionService positionService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    EquipmentService equipmentService;

    @Autowired
    EventService eventService;

    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver internalResourceViewResolver =  new InternalResourceViewResolver();
        internalResourceViewResolver.setPrefix("/WEB-INF/view/");
        internalResourceViewResolver.setSuffix(".jsp");

        return internalResourceViewResolver;
    }

//TODO add ResourceBundleMessageSource to custom error messages

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToLocalDateTimeConverter());
        registry.addConverter(new StringToBooleanConverter());
        registry.addConverter(new StringPositionIdToPositionConverter(positionService));
        registry.addConverter(new StringEmployeeIdToEmployeeConverter(employeeService));
        registry.addConverter(new StringEquipmentIdToEquipmentConverter(equipmentService));
        registry.addConverter(new StringEventIdToEventConverter(eventService));
    }

    

}
