package org.iesbelen.dao;

import java.util.List;
import java.util.Optional;

import org.iesbelen.modelo.Comercial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//Anotación lombok para logging (traza) de la aplicación
@Slf4j
@Repository
//Utilizo lombok para generar el constructor
@AllArgsConstructor
public class ComercialDAOImpl implements ComercialDAO {

    //JdbcTemplate se inyecta por el constructor de la clase automáticamente
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void create(Comercial comercial) {

        jdbcTemplate.update("INSERT INTO comercial (nombre, apellido1, apellido2, comisión) VALUES (?, ?, ?, ?)", comercial.getNombre(), comercial.getApellido1(), comercial.getApellido2(), comercial.getComision());

    }

    @Override
    public List<Comercial> getAll() {

        List<Comercial> listaComerciales = jdbcTemplate.query(
                "SELECT * FROM comercial",
                (rs, rowNum) -> new Comercial(rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido1"),
                        rs.getString("apellido2"),
                        rs.getFloat("comisión"))

        );

        log.info("Devueltos {} registros.", listaComerciales.size());

        return listaComerciales;
    }

    @Override
    public Optional<Comercial> find(int id) {

        Comercial com = jdbcTemplate
                .queryForObject("SELECT * FROM comercial WHERE id = ?",
                        (rs, rowNum) -> new Comercial(rs.getInt("id"),
                                rs.getString("nombre"),
                                rs.getString("apellido1"),
                                rs.getString("apellido2"),
                                rs.getFloat("comisión"))
                        , id
                );

        if (com != null) {
            return Optional.of(com);
        } else {
            log.info("Comercial no encontrado.");
            return Optional.empty();
        }
    }

    @Override
    public void update(Comercial comercial) {

        int rows = jdbcTemplate.update("""
                        UPDATE comercial SET
                                             nombre = ?,
                                             apellido1 = ?,
                                             apellido2 = ?,
                                             comisión = ?
                                         WHERE id = ?
                        """, comercial.getNombre(),
                comercial.getApellido1(),
                comercial.getApellido2(),
                comercial.getComision(),
                comercial.getId());

        log.info("Updateados {} registros.", rows);

    }

    @Override
    public void delete(long id) {

        int rows = jdbcTemplate.update("DELETE FROM comercial WHERE id = ?", id);

        log.info("Delete de Comercial con {} registros eliminados.", rows);
    }

}
