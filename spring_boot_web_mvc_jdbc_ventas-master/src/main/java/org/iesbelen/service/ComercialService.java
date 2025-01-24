package org.iesbelen.service;

import org.iesbelen.dao.ComercialDAO;
import org.iesbelen.modelo.Comercial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComercialService {

    @Autowired
    private ComercialDAO comercialDAO;

    public ComercialService(ComercialDAO comercialDAO) {
        this.comercialDAO = comercialDAO;
    }

    public List<Comercial> listAll() {
        return comercialDAO.getAll();
    }

    public Comercial one(int id) {

        Optional<Comercial> optCom = comercialDAO.find(id);
        if (optCom.isPresent()) {
            return optCom.get();
        } else {
            return null;
        }
    }

    public void newComercial(Comercial comercial) {

        comercialDAO.create(comercial);

    }

    public void replaceComercial(Comercial comercial) {

        comercialDAO.update(comercial);

    }

    public void deleteComercial(int id) {

        comercialDAO.delete(id);

    }

}
