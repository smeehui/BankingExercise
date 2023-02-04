package com.huy.app.repository.transfer;

import com.huy.app.model.Customer;
import com.huy.app.model.Transfer;
import com.huy.app.repository.transfer.ITransferRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class TransferRepository implements ITransferRepository {
    @PersistenceContext
    private EntityManager em;
    @Override
    public List<Transfer> findAll() {
        TypedQuery<Transfer> query = em.createQuery("SELECT t FROM Transfer t", Transfer.class);
        return query.getResultList();
    }

    @Override
    public Transfer findById(Long id) {
        return null;
    }

    @Override
    public void save(Transfer transfer) {
        if (transfer.getId() != null) {
            em.merge(transfer);
        } else {
            em.persist(transfer);
        }
    }

    @Override
    public void remove(Long id) {

    }
}
