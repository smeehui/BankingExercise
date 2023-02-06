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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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
    public String showTransferPage(Model model) {
        addAttr(model);
        return "/pages/transfer/create";
    }

    private void addAttr(Model model) {
        model.addAttribute("customers", customerService.findAll());
        model.addAttribute("transfer", new Transfer());
    }

    @PostMapping("/create")
    public String createCustomer(@Valid @ModelAttribute Transfer transfer, BindingResult bindingResult, Model model) {

        model.addAttribute("customers", customerService.findAll());
        Optional <Customer> sentCustomer = customerService.findById(transfer.getSentCustomer().getId());
        Optional <Customer> receivedCustomer = customerService.findById(transfer.getReceivedCustomer().getId());
        if (!sentCustomer.isPresent()) {
            bindingResult.rejectValue("sentCustomer","error.customer","Sender's ID is null");
        }
        if (receivedCustomer.isPresent()) {
            bindingResult.rejectValue("sentCustomer","error.customer","Recipient's ID is null");
        }
        if (sentCustomer != null && receivedCustomer != null) {
            if (sentCustomer.get().getId().equals(receivedCustomer.get().getId())) {
                bindingResult.rejectValue("sentCustomer","error.customer","ID must be different");
            }
            if (sentCustomer.get().getBalance()< transfer.getTotalAmount()){
                bindingResult.rejectValue("sentCustomer","error.customer","Sender's balance is not enough");
            }
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("transfer", transfer);
            return "/pages/transfer/create";
        }else {
            transferService.save(transfer);
            sentCustomer.get().setBalance(sentCustomer.get().getBalance() - transfer.getTotalAmount());
            receivedCustomer.get().setBalance(receivedCustomer.get().getBalance() + transfer.getAmount());
            customerService.save(sentCustomer.get());
            customerService.save(receivedCustomer.get());
            model.addAttribute("success", "New transfer is created successfully");
            addAttr(model);
            return "/pages/transfer/create";
        }
    }
}
