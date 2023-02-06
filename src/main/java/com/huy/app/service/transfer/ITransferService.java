package com.huy.app.service.transfer;

import com.huy.app.model.Transfer;
import com.huy.app.service.IGeneralService;

import java.util.List;

public interface ITransferService  {
    List<Transfer> findAll();

    Transfer findById(Long id);

    void save(Transfer t);

    void remove(Long id);
    public Double getProfit();
}
