const express = require("express");
const path = require("path");
const app = express();
const port = process.env.PORT || 3000;

//Alles im Ordner public kann als statische Datei geliefert werden

app.use(express.static(path.join(__dirname, "public")));

/**
 * Paths. Hier weitere Pfade hinzufügen
 */

app.get("/", (req, res) => {
  res.sendFile(path.join(__dirname, "/index.html"));
});

app.get("/jens/nummel", (req, res) => {
  res.status(418).send("nummel😅 ... Was schreib ich denn hier?😅🍆🍆");
});

//

app.listen(port, () => {
  console.log(`Example app listening at http://localhost:${port}`);
});
