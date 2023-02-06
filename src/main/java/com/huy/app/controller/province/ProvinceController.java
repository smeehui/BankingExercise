package com.huy.app.controller.province;

import com.huy.app.model.Province;
import com.huy.app.service.province.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/provinces")
public class ProvinceController {
    @Autowired
    private IProvinceService provinceService;
    @GetMapping("/")
    public ModelAndView listProvinces() {
        Iterable<Province> provinces = provinceService.findAll();
        ModelAndView modelAndView = new ModelAndView("pages/province/list");
        modelAndView.addObject("provinces", provinces);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("pages/province/create");
        modelAndView.addObject("province", new Province());
        modelAndView.addObject("view", "create");
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView saveProvince(@Valid @ModelAttribute("province") Province province, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("pages/province/create");
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("province", province);
            return modelAndView;
        }
        provinceService.save(province);
        modelAndView.addObject("province", new Province());
        modelAndView.addObject("success", "New province created successfully");
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Optional<Province> province = provinceService.findById(id);
        if (province.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("pages/province/create");
            modelAndView.addObject("province", province.get());
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit")
    public ModelAndView updateProvince(@Valid @ModelAttribute("province") Province province,BindingResult bindingResult) {
        ModelAndView modelAndView;
        if (bindingResult.hasErrors()) {
            modelAndView = new ModelAndView("pages/province/create");
            modelAndView.addObject("view", "edit");
            modelAndView.addObject("province", province);
            return modelAndView;
        }
        modelAndView = new ModelAndView("pages/province/list");
        provinceService.save(province);
        modelAndView.addObject("provinces", provinceService.findAll());
        modelAndView.addObject("success", "Province updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public String showDeleteForm(@PathVariable Long id) {
        Optional<Province> province = provinceService.findById(id);
        if (province.isPresent()) {
            provinceService.remove(id);
            return "redirect:/provinces/";
        } else {
            return "error.404";
        }
    }
}
