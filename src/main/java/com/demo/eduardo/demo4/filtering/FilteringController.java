package com.demo.eduardo.demo4.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {

	@GetMapping (path="/filtering")
	public MappingJacksonValue retrieveSomeOtherBean() {
		SomeOtherBean someOtherBean = new SomeOtherBean("valueA", "valueB", "valueC");
		
		
		// Setting a dynamic filter
		// Step 1: Create a filter (looks like it's not related to the Bean we want to filter yet,
		// we just pass the names of the fields).
		PropertyFilter filterOutAllExcept = SimpleBeanPropertyFilter.filterOutAllExcept("firstField", "secondField");   // only shows the fields specified, and disappears everything else.
		
		// Step 2: Add that filter to a simple filter provider.
		// The name you give to the filter here, must be added in the corresponding Bean class.
		FilterProvider filters = new SimpleFilterProvider().addFilter("EduardoDemoBeanFilter", filterOutAllExcept);
		
		// Step 3: In a MappingJacksonValue object add the bean and then the filter, in two steps:
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someOtherBean);
		mappingJacksonValue.setFilters(filters);
		
		// Step 4: Go to the SomeOtherBean and add the annotation @JsonFilter("EduardoDemoBeanFilter")
		
		// Step 5: Don't forget to set the return type as the MappingJacksonValue,
		// this method must return it instead of the actual Bean, in order to work.
		return mappingJacksonValue;
	}
	
	
	@GetMapping (path="/filtering-list")
	public MappingJacksonValue retrieveSomeOtherBeanList() {
		List<SomeOtherBean> asList = Arrays.asList(new SomeOtherBean("valueA-1", "valueB-1", "valueC-1"), new SomeOtherBean("valueA-2", "valueB-2", "valueC-2"));
		
		PropertyFilter filterOutAllExcept = SimpleBeanPropertyFilter.filterOutAllExcept("secondField", "thirdField");
		FilterProvider filters = new SimpleFilterProvider().addFilter("EduardoDemoBeanFilter", filterOutAllExcept);
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(asList);
		mappingJacksonValue.setFilters(filters);
		
		return mappingJacksonValue;
	}
}