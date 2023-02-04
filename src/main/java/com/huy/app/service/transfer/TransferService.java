package com.huy.app.service.transfer;

import com.huy.app.model.Transfer;
import com.huy.app.repository.transfer.ITransferRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TransferService implements ITransferService {
    @Autowired
    ITransferRepository transferRepository;

    @Override
    public List<Transfer> findAll() {
        return transferRepository.findAll();
    }

    @Override
    public Transfer findById(Long id) {
        return transferRepository.findById(id);
    }

    @Override
    public void save(Transfer transfer) {
        transfer.setFeeAmount(transfer.getAmount() * transfer.getFeeRate()/100);
        transfer.setTotalAmount(transfer.getAmount() + transfer.getAmount() * transfer.getFeeRate()/100);
        transferRepository.save(transfer);
    }

    @Override
    public void remove(Long id) {
        transferRepository.remove(id);
    }
}
