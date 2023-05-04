package com.example.Swipe.Admin.service.impl;

import com.example.Swipe.Admin.entity.Agent;
import com.example.Swipe.Admin.entity.Contractor;
import com.example.Swipe.Admin.entity.Notary;
import com.example.Swipe.Admin.repository.NotaryRepo;
import com.example.Swipe.Admin.service.NotaryService;
import org.aspectj.weaver.ast.Not;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotaryServiceImpl implements NotaryService {
    private final NotaryRepo notaryRepo;


    public NotaryServiceImpl(NotaryRepo notaryRepo) {
        this.notaryRepo = notaryRepo;
    }
    public List<Notary> notaries(){
        return notaryRepo.findAll();
    }

    @Override
    public List<Notary> findAll() {
        return notaryRepo.findAll();
    }

    @Override
    public Notary findById(int id) {
        Optional<Notary> notary = notaryRepo.findById(id);
        if(notary.isPresent()){
            return notary.get();
        }
        else {
            return Notary.builder().build();
        }
    }

    @Override
    public void saveEntity(Notary notary) {
        notaryRepo.save(notary);
    }

    @Override
    public void deleteById(int id) {
        notaryRepo.deleteById(id);
    }

    @Override
    public void updateEntity(Notary notary, int id) {
        Optional<Notary> notaryOptional = notaryRepo.findById(id);
        if(notaryOptional.isPresent()){
            Notary notaryUpdate = notaryOptional.get();
            if(notary.getName()!=null){
                notaryUpdate.setName(notary.getName());
            }
            if(notary.getMail()!=null){
                notaryUpdate.setMail(notary.getMail());
            }
            if(notary.getNumber()!=null){
                notaryUpdate.setNumber(notary.getNumber());
            }
            if(notary.getSurname()!=null){
                notaryUpdate.setSurname(notary.getSurname());
            }
            notaryRepo.saveAndFlush(notaryUpdate);
        }
        else {
            Notary notaryUpdate = Notary.builder().name("").surname("").mail("").number("").build();
            notaryRepo.saveAndFlush(notaryUpdate);
        }
    }
}
