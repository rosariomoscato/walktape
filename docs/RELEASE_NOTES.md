# WalkTape — note di rilascio

## v0.2.0

Tre modelli del lettore — Android 8.0+ (API26), Navidrome.
`versionName=0.2.0`, `versionCode=4`, package `com.walktape.app`, Room13 invariato.

### Installazione

- [Release v0.2.0](https://github.com/rosariomoscato/walktape/releases/tag/v0.2.0).
- APK universal **walktape-universal-0.2.0.apk**, checksum in **SHA256SUMS.txt**.
- Da v0.1.0, v0.1.1 o build di sviluppo B04: Impostazioni → Aggiornamenti app →
  Controlla aggiornamenti, oppure installazione manuale sopra l'app esistente.
  Firma stabile, nessuna disinstallazione; dati e download conservati.

### Cosa cambia

- **WM-2 · Argento tecnico**: scocca argento, fascia grafite con dettaglio diagonale
  e Play verde tenue.
- **WM-F5 Sports · Sports giallo**: scocca gialla, tasti grafite e Play verde.
  Risorse originali e marchio WalkTape; tutte le funzioni del player restano disponibili.
- **Modello del lettore** in Impostazioni → Aspetto, con miniature e selezione radio
  accessibile in italiano e inglese. **TPS-L2 resta il modello predefinito**, con
  aspetto conservato. Tema chiaro/scuro/sistema e identità dell'app sono indipendenti.
- Scelta persistente per dispositivo, inclusa nei backup manuali delle preferenze.
  I backup precedenti e gli ID sconosciuti ricadono esplicitamente sul TPS-L2.
- Cambio puramente visivo: conserva brano, coda/UUID/shuffle, posizione, volume,
  timer e DSP. Il collector audio ora reagisce ai soli campi pertinenti; anche il
  controllo automatico degli aggiornamenti è isolato dalle preferenze estetiche.
- Preservate gestione bassi per uscita D41 e correzioni D42: autenticazione chiave
  API OpenSubsonic, updater HTTPS/checksum/firma e blocco della ricerca esterna.
  Android Auto e notifiche mantengono la propria interfaccia di piattaforma.

### Verifiche del codice B04 e collaudi residui

- **197/197 JVM**, nessuno saltato; lint debug **0 errori / 46 avvisi**.
- Su AOSP in esecuzioni separate: materiali+backup6/6, cambio modello durante audio
  locale Solo offline1/1, regressioni coda/Keystore/Room13/effetti8/8, matrice UI1/1
  per ciascuno dei tre viewport e movimento ridotto1/1; confronto raster finaleTPS1/1.
- 108capture: tre modelli, IT/EN, tre temi, font100/200%, portrait/landscape/tablet.
  Smoke aggiuntivo nella shell Android normale e persistenza dopo force-stop.
- Nella prova del servizio: nessun seek/transizione musicale, zero HTTP e nessuna
  riapplicazione degli effetti audio al cambio skin. Le prove con WAV e libreria di
  fixture restano distinte da hardware e server Navidrome reali.
- Riscontro utente **«Funziona bene»** dopo la consegna: conferma generale positiva.
  Restano prove puntuali TalkBack/clic/aptica e collaudi hardware/Android Auto già
  aperti; non sono dichiarati superati dalla pubblicazione di questa release.

---

## v0.1.1

Correzioni dalla revisione dell'app pubblicata — Android 8.0+ (API 26), Navidrome.
`versionName=0.1.1`, `versionCode=3`, package `com.walktape.app`.

### Installazione

- APK universal: **walktape-universal-0.1.1.apk**; checksum in **SHA256SUMS.txt**.
- [Release v0.1.1](https://github.com/rosariomoscato/walktape/releases/tag/v0.1.1).
- Da v0.1.0 (o build di test 0.1.0): aggiornamento in-app da Impostazioni →
  Aggiornamenti app, oppure installazione manuale sopra quella esistente.
  Stessa firma, nessuna disinstallazione, dati conservati.

SHA256 APK:
`4a6149aa38ca36b06aa673f442fb900666afd0cc73d1b645404df12cd4f250ed`.

### Cosa cambia

- **Chiave API OpenSubsonic** (estensione `apiKeyAuthentication`): un profilo può
  autenticarsi con una chiave creata e revocabile dalla UI del server, al posto della
  password. La chiave viaggia da sola (mai insieme a username/token), è salvata cifrata
  nell'Android Keystore e non entra mai in backup, QR o diagnostica. Su HTTP una chiave
  intercettata resta revocabile, a differenza della password. Il server deve offrire
  l'estensione: Navidrome 0.62.0 oggi non la annuncia, quindi il percorso resta
  disponibile ma non attivabile finché il server non la supporta.
- **Aggiornamenti più robusti**: il canale di download è solo HTTPS (anche sui redirect)
  e, quando la release pubblica `SHA256SUMS.txt`, l'APK scaricato viene confrontato col
  digest pubblicato prima della verifica firma; se il file pubblicato non è leggibile,
  non contiene il digest dell'APK o il digest non corrisponde, l'installazione viene
  rifiutata. Se la release non pubblica checksum, resta la verifica obbligatoria della firma.
- **Blocco app e ricerca esterna**: con il blocco biometrico attivo, la riproduzione
  avviata da ricerche vocali o da altre app (MEDIA_PLAY_FROM_SEARCH) è rifiutata finché
  l'app non viene sbloccata una volta; dopo un riavvio del processo nulla parte da solo.
- **Documenti onesti**: la guida d'installazione nomina i controlli reali
  («Verifica e salva», «Aggiorna libreria»); l'informativa privacy elenca tutti i
  permessi dichiarati (anche WAKE_LOCK e MODIFY_AUDIO_SETTINGS); l'etichetta HTTP dice
  «connessione non cifrata» e l'aiuto spiega che su HTTP chi intercetta il traffico può
  catturare un dato equivalente alla password (consigliati HTTPS, VPN o chiave API);
  il README usa gli stessi nomi delle schermate (timer di ascolto, dissolvenza incrociata).

### Verifiche

- **192/192 test JVM** (zero skip), incluso il login contro il server reale
  Navidrome 0.62.0; copertura aggiunta per chiave API su ping/stream/download, parsing e
  verifica checksum, trasporto HTTPS-only, gate ricerca esterna, redazione diagnostica,
  round-trip backup del flag.
- **Suite Android: OK (6 tests)** su emulatore AOSP: migrazione database 12→13 con profili
  conservati, profilo chiave API end-to-end (richiesta, Keystore, riuso, rimozione) e
  regressioni profili/keystore esistenti. Il test server reale opt-in senza configurazione
  viene saltato tramite Assume: non è una nuova prova server dall'emulatore.
- Lint **0 errori / 46 avvisi**; schema Room 13 esportato.
- Non eseguiti in questa versione: percorso aggiornamento in-app interattivo contro una
  release reale (questa è la prima release con versionCode crescente), prova del toggle
  chiave API e del gate di ricerca su telefono fisico, collaudi Android Auto/batteria
  già aperti nella v0.1.0.

### Documentazione e licenza

[Installazione](https://github.com/rosariomoscato/walktape/blob/main/docs/INSTALL.md) ·
[Aggiornamenti](https://github.com/rosariomoscato/walktape/blob/main/docs/UPDATES.md) ·
[Privacy](https://github.com/rosariomoscato/walktape/blob/main/docs/PRIVACY.md) ·
[Android Auto](https://github.com/rosariomoscato/walktape/blob/main/docs/ANDROID_AUTO.md) ·
[Licenza MIT](https://github.com/rosariomoscato/walktape/blob/main/LICENSE).

© 2026 [Rosario Moscato](https://rosmoscato.xyz) - handcrafted by a human, upgraded by agents.

---

## v0.1.0

Prima versione pubblica — Android 8.0+ (API 26), Navidrome.
`versionName=0.1.0`, `versionCode=2`, package `com.walktape.app`.

### Installazione

- APK universal: **walktape-universal-0.1.0.apk**.
- Checksum: **SHA256SUMS.txt**, nella stessa pagina release.
- [Release v0.1.0](https://github.com/rosariomoscato/walktape/releases/tag/v0.1.0).
- [Guida installazione e Tailscale](https://github.com/rosariomoscato/walktape/blob/main/docs/INSTALL.md).

SHA256 APK:
`c1ccacb4632d39da11c4bd0344cbe092a90f1eca7d6badc253ca04be72668957`.

Firma stabile identica alle build di sviluppo di questo progetto: l'APK si installa
sopra di esse senza disinstallare. Le build di test già denominate **0.1.0** risultano
già aggiornate nel controllo in-app: per passare al pacchetto release, scaricare e
installare manualmente questo APK. I futuri aggiornamenti richiederanno versionName
e versionCode crescenti e la stessa firma.

### Funzioni

- Profili Navidrome multipli con credenziali cifrate in Android Keystore, dati e download
  isolati; QR senza password e blocco biometrico facoltativo.
- Libreria con album/artisti/brani/generi/playlist, ricerca locale, filtri, ordinamenti,
  preferiti e testi forniti dal server.
- Player TPS-L2 con cassetta animata, tasti a larghezza piena, coda con duplicati,
  shuffle/repeat, nome mixtape, timer e volume multimediale Android.
- Splash con logo, versione e credits; ritorno esplicito dalle sotto-pagine;
  italiano/inglese, temi chiaro/scuro/sistema e layout adattivo.
- Originali MP3/FLAC e qualità ridotta Opus configurabile; crossfade, ReplayGain,
  equalizzatore ed effetto cassetta opzionali, suono pulito di default.
- Rinforzo bassi disponibile sulle cuffie secondo il dispositivo; sospeso su altoparlante
  o route non rilevata, con avviso e preferenza/intensità conservate.
- Download brani/album/playlist con pausa/ripresa, cache limitata, modalità Solo offline
  a zero richieste; modifiche offline a playlist/preferiti e risoluzione conflitti.
- Ascolti con deduplica locale e ACK durevole; limite di idempotenza lato Navidrome
  documentato (nessuna garanzia exactly-once se si perde la risposta del server).
- Catalogo Android Auto moderno/legacy sullo stesso servizio e sulla stessa coda.
- Backup manuale senza credenziali/audio, diagnostica esportabile senza invii automatici,
  aggiornamenti GitHub con verifica package/versionCode/firma e conferma Android.

### Verifiche e controlli ancora aperti

- Codice finale: **177/177 test JVM**, lint debug **0 errori / 45 avvisi**; suite Android
  W12 in esecuzioni separate su AOSP con fixture e server reale Navidrome 0.62.0.
- APK release riallineato al codice finale: build e lint release riusciti
  (**0 errori / 45 avvisi**), firma V2 verificata; processo e digest certificato in
  [RELEASING.md](https://github.com/rosariomoscato/walktape/blob/main/docs/RELEASING.md).
- Ultime regressioni bassi: controller, handover offline e timer durante overlap superati
  in tre esecuzioni Android distinte. Esiti e fallimenti intermedi nel WORKLOG.
- Motorola G55 / Android 16: checklist W12 A/B positiva, W11 18/24; splash/tasti e correzione
  bassi hanno ricevuto riscontri positivi dell'utente, senza chiudere voci non attestate.
- **Ancora aperti**: proiezione Android Auto/DHU e collaudo in auto, matrice residua
  chiamate/route/audiofx/batteria/font ingranditi, trasferimento backup su ambiente pulito
  e percorso interattivo completo di un futuro aggiornamento in-app.

La pubblicazione dell'APK non equivale al superamento di questi collaudi.
Compatibilità dichiarata solo con Navidrome; niente Play Store, Cast/DLNA o libreria
di file locali indipendente dal server.
