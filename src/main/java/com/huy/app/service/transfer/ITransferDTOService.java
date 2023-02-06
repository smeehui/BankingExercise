package com.huy.app.service.transfer;

import com.huy.app.model.TransferDTO;
import com.huy.app.service.IGeneralService;

import java.util.List;

public interface ITransferDTOService {
    List<TransferDTO> findAll();

    TransferDTO findById(Long id);

    void save(TransferDTO t);

    void remove(Long id);
}
