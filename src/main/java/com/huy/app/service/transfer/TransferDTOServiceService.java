package com.huy.app.service.transfer;

import com.huy.app.model.TransferDTO;
import com.huy.app.repository.transfer.ITransferDTORepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TransferDTOServiceService implements ITransferDTOService {
    @Autowired
    ITransferDTORepository transferDTORepository;
    @Override
    public List<TransferDTO> findAll() {
        return transferDTORepository.findAll();
    }

    @Override
    public TransferDTO findById(Long id) {
        return null;
    }

    @Override
    public void save(TransferDTO transferDTO) {

    }

    @Override
    public void remove(Long id) {

    }
}
