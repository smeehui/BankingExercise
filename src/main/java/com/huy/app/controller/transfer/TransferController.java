package com.huy.app.controller.transfer;

import com.huy.app.model.Customer;
import com.huy.app.model.Transfer;
import com.huy.app.model.TransferDTO;
import com.huy.app.service.customer.ICustomerService;
import com.huy.app.service.transfer.ITransferDTOService;
import com.huy.app.service.transfer.ITransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/transfer")
public class TransferController {
    @Autowired
    ICustomerService customerService;
    @Autowired
    ITransferService transferService;
    @Autowired
    ITransferDTOService transferDTOService;

    @GetMapping()
    public ModelAndView showAllTransfers(){
        ModelAndView modelAndView = new ModelAndView("pages/transfer/list");
        List<TransferDTO> allTransfers = transferDTOService.findAll();
        modelAndView.addObject("transfers", allTransfers);
        modelAndView.addObject("profit", transferService.getProfit());
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView showTransferPage() {
        ModelAndView modelAndView = new ModelAndView("/pages/transfer/create");
        modelAndView.addObject("customers", customerService.findAll());
        modelAndView.addObject("transfer", new Transfer());
        return modelAndView;
    }

    @PostMapping("/create")
    public String createCustomer(@Valid @ModelAttribute Transfer transfer, BindingResult bindingResult, Model model) {

        model.addAttribute("customers", customerService.findAll());
        Customer sentCustomer = customerService.findById(transfer.getSentCustomer().getId());
        Customer receivedCustomer = customerService.findById(transfer.getReceivedCustomer().getId());
        if (sentCustomer.getId().equals(receivedCustomer.getId())) {
            bindingResult.rejectValue("sentCustomer","error.customer","ID must be different");
        }
        if (sentCustomer.getBalance()< transfer.getTotalAmount()){
            bindingResult.rejectValue("sentCustomer","error.customer","Sender's balance is not enough");
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("transfer", transfer);
            return "/pages/transfer/create";
        }else {
            sentCustomer.setBalance(sentCustomer.getBalance() - transfer.getTotalAmount());
            receivedCustomer.setBalance(receivedCustomer.getBalance() + transfer.getAmount());
            customerService.save(sentCustomer);
            customerService.save(receivedCustomer);
            transferService.save(transfer);
            return "redirect:/transfer";
        }
    }
}
