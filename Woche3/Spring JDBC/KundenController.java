package de.propra.db;

import de.propra.db.dto.Bestellposition;
import de.propra.db.dto.Bestellung;
import de.propra.db.dto.Kunde;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Controller
public class KundenController {

  private final JdbcTemplate db;

  public KundenController(JdbcTemplate db) {
    this.db = db;
  }

  private static Kunde mapKunde(ResultSet rs, int i) throws SQLException {
    long nr = rs.getLong(1);
    String name = rs.getString(2);
    String adresse = rs.getString(3);
    return new Kunde(nr, name, adresse);
  }

  private static Bestellung mapBestellung(ResultSet rs, int i) throws SQLException {
    long bstnr = rs.getLong(1);
    LocalDate datum = rs.getDate(2).toLocalDate();
    long kdnr = rs.getLong(3);
    String name = rs.getString(4);
    return new Bestellung(bstnr, datum, kdnr, name);
  }

  @GetMapping("/")
  public String liste(Model model) {
    String sql = "SELECT * FROM kunde";
    // List<Kunde> kunden = db.query(sql, KundenController::mapKunde);
    List<Kunde> kunden = db.query(sql, new DataClassRowMapper<>(Kunde.class));
    model.addAttribute("kunden", kunden);
    return "kunden";
  }

  @GetMapping("/search-kunde")
  public ModelAndView searchKunde(ModelMap model, String kundenname) {
    if (!kundenname.isEmpty()) {
      String sql = """
          SELECT *
          FROM kunde k
          WHERE k.name LIKE ?;
          """;
      String newKundenname = "%" + kundenname + "%";
      List<Kunde> kunden = db.query(sql, new DataClassRowMapper<>(Kunde.class), newKundenname);
      model.addAttribute("kunden", kunden);
      return new ModelAndView("kunden", model);
    } else {
      return new ModelAndView("redirect:/");
    }
  }

  @GetMapping("details")
  public String bestellungen(Model model, int nr) {
    String sql = """
        SELECT b.nr, b.datum, b.kunde, k.name FROM bestellung b
        JOIN kunde k ON k.nr = b.kunde
        WHERE b.kunde = ?
        """;

    List<Bestellung> bestellungen = db.query(sql, KundenController::mapBestellung, nr);

    model.addAttribute("bestellungen", bestellungen);
    return "details";
  }

  @GetMapping("bestellung")
  public String bestellung(Model model, int nr) {
    String sql_bestellung = """
        SELECT b.nr, b.datum, b.kunde AS kundennummer, k.name AS kundenname
        FROM bestellung b
          JOIN kunde k ON k.nr = b.kunde
        WHERE b.nr = ?
        """;

    String sql_bestellpositionen = """
        SELECT b.nr AS bestellung, b2.anzahl AS anzahl, p.bezeichnung AS bezeichnung, p.beschreibung AS beschreibung, p.preis AS preis
        FROM bestellung b
          JOIN bestellposition b2 ON b.nr = b2.bestellung
          JOIN produkt p ON p.nr = b2.produkt
        WHERE b.nr = ?
        """;

    Bestellung bestellung = db.queryForObject(sql_bestellung, new DataClassRowMapper<>(Bestellung.class), nr);
    List<Bestellposition> bestellpositionen = db.query(sql_bestellpositionen,
        new DataClassRowMapper<>(Bestellposition.class), nr);

    model.addAttribute("bestellung", bestellung);
    model.addAttribute("bestellpostion", bestellpositionen);
    return "bestellung";
  }

  @PostMapping("delete-kunde")
  public String kundeLoeschen(Model model, int nr) {
    String sql_delete = """
        DELETE FROM kunde WHERE nr = ?
        """;

    String sql_bestellungen = """
        SELECT b.nr, b.datum, b.kunde, k.name FROM bestellung b
        JOIN kunde k ON k.nr = b.kunde
        WHERE b.kunde = ?
        """;

    List<Bestellung> bestellungen = db.query(sql_bestellungen, KundenController::mapBestellung, nr);
    if (bestellungen.isEmpty()) {
      db.update(sql_delete, nr);
    }
    return liste(model);
  }

}
