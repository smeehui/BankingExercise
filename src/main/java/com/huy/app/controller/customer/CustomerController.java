package com.huy.app.controller.customer;

import com.huy.app.model.Customer;
import com.huy.app.service.customer.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @GetMapping("/list")
    public ModelAndView showListCustomer() {
        ModelAndView modelAndView = new ModelAndView("pages/customer/list");
//        List<Customer> customers = customerService.findAll();
//        modelAndView.addObject("customers", customers);
        return modelAndView;
    }

    @GetMapping
    public ModelAndView showALlCustomer() {
        return showListCustomer();
    }

    @GetMapping("/create")
    public String showCreatingCustomerForm(Model model) {
        initPageAttrs(model, new Customer(), "create");
        return "pages/customer/create";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditCustomerForm(@PathVariable String id) {
        Long idLong = null;
        try {
            idLong = Long.parseLong(id);
        } catch (NumberFormatException e) {
        }
        ModelAndView modelAndView = new ModelAndView("pages/customer/create");
        modelAndView.addObject("customer", customerService.findById(idLong));
        modelAndView.addObject("view", "edit");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable String id) {
//        Long idLong = null;
//        try {
//            idLong = Long.parseLong(id);
//        } catch (NumberFormatException e) {
//        }
//        Customer customer = customerService.findById(idLong);
//        customer.setDeleted(true);
//        customerService.save(customer);
        return "redirect:/customer";
    }

    @PostMapping("/create")
    public String createNewCustomer(@Valid @ModelAttribute("customer") Customer customer, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            initPageAttrs(model, customer, "create");
            return "pages/customer/create";
        } else {
            customerService.save(customer);
            initPageAttrs(model, new Customer(), "create");
            model.addAttribute("success", "New customer created successfully");
            return "pages/customer/create";
        }
    }

    private static void initPageAttrs(Model model, Customer attributeValue, String create) {
        model.addAttribute("customer", attributeValue);
        model.addAttribute("view", create);
    }

    @PostMapping("/edit/{id}")
    public String updateCustomer(@PathVariable String id, @Valid @ModelAttribute Customer customer, BindingResult bindingResult, Model model) {
        Long idLong = null;
        try {
            idLong = Long.parseLong(id);
        } catch (NumberFormatException e) {
            return "redirect:/customer/";
        }
        customer.setId(idLong);
        if (bindingResult.hasErrors()) {
            initPageAttrs(model, customer, "edit");
            return "/pages/customer/create";
        }else {
            customerService.save(customer); customerService.save(customer);
            return "redirect:/customer/";
        }
    }
}
