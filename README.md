# ProPra Leitfragen

## Neue Blätter hinzufüfen

Erstellt ihm Ordner `public` einen neuen Ordner `WocheX` mit den neuen Leitfragen. Das Leitfragendokument sollte bestenfalls `leitfragenWocheX.html`heißen.

## Heroku Server

Link: https://propra-leitfragen.herokuapp.com

Der Node-Server ist auf Heroku gehostet und hat ein Limit von 550-1,000 dyno stunden pro monat. Alles was auf den nodejs-Branch gepusht wird, lässt den Server neustarten und die Website geht wieder live.

Wenn jemand zugang zum Heroku-Server möchte, kann mir einfach auf Discord schreiben: https://discordapp.com/users/444853663345934346

### Path's

Alles was sich im Ordner `public` befindet, kann in der `index.html` referenziert werden: z.B.
`<a href="Woche1/leitfragenWoche1.html">Woche 1</a>`

Wenn ihr neue routen hinzufügen wollt, könnt ihr eine weitere Zeite wie diese in `app.js`

```
app.get("/jens/nummel", (req, res) => {
  res.status(418).send("nummel😅 ... Was schreib ich denn hier?😅🍆🍆");
});
```

Wenn es eine Datei sein soll, könnt ihr `sendFile` benutzen und "/index.html" mit dem Pfad zur Datei ersetzten:

```
app.get("/", (req, res) => {
  res.sendFile(path.join(__dirname, "/index.html"));
});
```

### Was ist eine dyno hour

A "dyno hour" is simply one hour of a dyno running.
If you have just 1 app running just 1 dyno, that dyno could be available 24/7 forever, since even a 31 day month consists of 31 x 24 = 744 hours, which is less than the 1000 free dyno hours you have at your disposal.
