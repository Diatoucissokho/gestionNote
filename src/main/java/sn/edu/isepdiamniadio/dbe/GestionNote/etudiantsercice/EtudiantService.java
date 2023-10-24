package sn.edu.isepdiamniadio.dbe.GestionNote.etudiantsercice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import sn.edu.isepdiamniadio.dbe.GestionNote.etudiants.Etudiant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
@Service
public class EtudiantService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static List<Etudiant> etudiants = new ArrayList<>(Arrays.asList(
            new Etudiant(1l, "fatou", "ciss", "fatou@gmail.com")
    ));
    private Long nextId = 1L;

    public List<Etudiant> getAllEtudiants() {
        String SQL = "SELECT * FROM etudiant";
        List<Etudiant> etudiants = jdbcTemplate.query(SQL,
                new BeanPropertyRowMapper<>(Etudiant.class));
        return etudiants;
    }

    public Etudiant getEtudiantById(Long id) {
        Optional<Etudiant> optionalEtudiant = etudiants.stream()
                .filter(etudiant -> etudiant.getId().equals(id))
                .findFirst();
        return optionalEtudiant.orElse(null);
    }

    /*public Etudiant createEtudiant(Etudiant etudiant) {
        etudiant.setId(nextId);  
        nextId++;
        etudiants.add(etudiant);
        return etudiant;
    }*/
    public Etudiant createEtudiant(Etudiant etudiant) {
        String SQL = "INSERT INTO etudiant (id,nom, prenom, email) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(SQL,etudiant.getId(), etudiant.getNom(), etudiant.getPrenom(), etudiant.getEmail());
        return etudiant;
    }


    public Etudiant updateEtudiant(Long id, Etudiant updatedEtudiant) {
        for (Etudiant etudiant : etudiants) {
            if (etudiant.getId().equals(id)) {
                etudiant.setNom(updatedEtudiant.getNom());
                etudiant.setPrenom(updatedEtudiant.getPrenom());
                etudiant.setEmail(updatedEtudiant.getEmail());
                return etudiant;
            }
        }
        return null;
    }

    public void deleteEtudiant(Long id) {
        etudiants.removeIf(etudiant -> etudiant.getId().equals(id));
    }

    /*String sql = "SELECT * FROM etudiant";
    int result = jdbcTemplate.queryForObject(sql, Integer.class);
*/
    /*public List<Etudiant> getAllEtudiants() {
        String sql = "SELECT * FROM etudiant";
        List<Etudiant> etudiants = jdbcTemplate.query(sql, (rs, rowNum) -> {
            Etudiant etudiant = new Etudiant();
            etudiant.setId(rs.getLong("id"));
            etudiant.setNom(rs.getString("nom"));
            etudiant.setPrenom(rs.getString("prenom"));
            etudiant.setEmail(rs.getString("email"));
            return etudiant;
        });
        return etudiants;
    }

    public Etudiant getEtudiantById(Long id) {
        String sql = "SELECT * FROM etudiant WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[] { id }, (rs, rowNum) -> {
            Etudiant etudiant = new Etudiant();
            etudiant.setId(rs.getLong("id"));
            etudiant.setNom(rs.getString("nom"));
            etudiant.setPrenom(rs.getString("prenom"));
            etudiant.setEmail(rs.getString("email"));
            return etudiant;
        });
    }

    public Etudiant createEtudiant(Etudiant etudiant) {
        String sql = "INSERT INTO etudiant (nom, prenom, email) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, etudiant.getNom(), etudiant.getPrenom(), etudiant.getEmail());
        return etudiant;
    }

    public Etudiant updateEtudiant(Long id, Etudiant updatedEtudiant) {
        String sql = "UPDATE etudiant SET nom = ?, prenom = ?, email = ? WHERE id = ?";
        jdbcTemplate.update(sql, updatedEtudiant.getNom(), updatedEtudiant.getPrenom(), updatedEtudiant.getEmail(), id);
        return updatedEtudiant;
    }

    public void deleteEtudiant(Long id) {
        String sql = "DELETE FROM etudiant WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }*/

}


