package com.huy.app.repository.transfer;

import com.huy.app.model.TransferDTO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class TransferDTORepository implements ITransferDTORepository{
    @PersistenceContext
    EntityManager em;
    @Override
    public List<TransferDTO> findAll() {
        return em.createQuery("SELECT t FROM TransferDTO t", TransferDTO.class).getResultList();
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
