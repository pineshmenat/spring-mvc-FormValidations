package com.spring.mvc.Controller;

import javax.validation.Valid;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.mvc.Model.Customer;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@RequestMapping("/showForm")
	public String showForm(Model model) {
		model.addAttribute("customer", new Customer());
		return "customer-form";
	}
	
	/*To trim white space and true arg is for setting only blank spaces to null value
	 * */
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringtrimeditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringtrimeditor);
	}
	
	/*When performing Spring MVC validation, the location of the BindingResult parameter 
	 * is very important. In the method signature, the BindingResult parameter must 
	 * immediately after the model attribute. 
	 * */
	@RequestMapping("/processForm")
	public String processForm(@Valid @ModelAttribute("customer") Customer theCustomer,
			BindingResult theBindingResult) {
		System.out.println("LastName: |" + theCustomer.getLastName() + "|");
		System.out.println("BindingResult: " + theBindingResult);
		if(theBindingResult.hasErrors()) {
			return "customer-form";
		} else {
			return "customer-confirmation";
		}
	}
}