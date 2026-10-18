---
workflow: general-video
flow: automation
storyboard: yes
message: "Il piacere di una cassetta, la tua musica su Android"
destination: reels-shorts
aspect: 1080x1920
language: it
audience: "Appassionati con una raccolta musicale propria, anche senza conoscenza di Navidrome"
length: 60s
narration: no
---

# WalkTape — Il piacere di una cassetta

## Intent

Regia completa confermata dall'utente il 2026-10-18, task VIDEO-T01. Non riaprire
le domande di brief. Presentazione social verticale dell'app Android WalkTape,
60 secondi esatti a 30 fps (1800 fotogrammi), testi e schermate in italiano,
senza narrazione. Risponde a «Cos'è WalkTape e perché dovrei provarla?».

L'aggancio ribalta l'idea che un lettore rétro debba rinunciare alle comodità
di oggi: si scopre una cassetta, poi il player reale, la raccolta del proprio
server Navidrome, la selezione personale e l'ascolto offline. Dopo il video chi
guarda sa dire: «WalkTape è un lettore Android per la mia musica su Navidrome,
con un player rétro e download per ascoltare offline».

Il pubblico possiede una raccolta ma può non conoscere Navidrome: nominarlo
come server già nella scena 2 e ribadire il requisito in chiusura. Il video
deve funzionare anche senza audio grazie a titoli brevi e azioni leggibili.

## Assets

Percorsi relativi a questa directory; le fonti indicate esistono. I file di
capture/audio descritti come da preparare non sono asset già disponibili.

- `../../app/src/main/res/drawable/ic_launcher_foreground.xml` — icona originale Cassetta approvata; convertire fedelmente in SVG/PNG locale per la chiusura, senza ridisegnare un marchio sostitutivo.
- `../../app/src/main/res/mipmap-anydpi/ic_launcher.xml` — riferimento dell'icona adattiva completa; usare il fondo blu di brand.
- `../../DESIGN.md` — fonte palette, tipografia, materiali, marchio bold italic e player; vincoli tradotti in `frame.md`.
- `../../README.md` — fonte per funzioni, distribuzione GitHub e requisito Navidrome.
- `../../SPEC.md` — fonte per comportamento musicale, mixtape e offline.
- `../../PROJECT_MEMORY.md` e `../../WORKLOG.md` — stato e verifiche; non trattare collaudi hardware aperti come superati.
- `../../regia/walktape-la-tua-musica/direzioni.html` — confronto e scelta A Oggetto notturno con motivo; bozzetti di stile, non schermate di prodotto da montare come vere.
- `../../regia/walktape-la-tua-musica/DECISIONI.md` — registro di tutte le decisioni e relative conferme.
- Acquisizioni Android da preparare — app reale in italiano con raccolta di prova: player prima/dopo Play, libreria/album, coda, nome mixtape, salvataggio playlist, download completato, Solo offline in riproduzione. Conservare la stessa selezione e copertina fra scene.
- `assets/audio/walktape-retro80-120bpm.wav` — nuova composizione originale locale120BPM synth-pop anni80, richiesta VIDEO-T04; provenienza in assets/audio/retro80-provenance.json e assets/PROVENANCE.md. Vecchia base96BPM conservata come storico, non montata.
- `assets/audio/play-click.wav` — clic originale locale di 80 ms, sulla prima pressione Play a circa 0,8 s.
- `assets/captures/` e `assets/media/` — acquisizioni Android reali ora disponibili e derivati per il montaggio; raccolta originale di prova su Navidrome temporaneo separato.

## Customizations

- **Voce:** assente per scelta esplicita 7.1 C. Non generare TTS o voce introduttiva.
- **Testo editoriale:** un titolo per volta, massimo due righe e otto parole complessive, salvo scheda finale dedicata. Titoli crema sul fondo blu notte; non coprire comandi o esiti mostrati. Testi esatti nelle scene sotto.
- **Musica, revisione VIDEO-T04:** l'utente approva le immagini ma chiede una base «più anni80 e molto più ritmata». Nuova composizione synth-pop originale locale120BPM: cassa in quattro, rullante/clap a coda corta, hats in sedicesimi, basso in ottave, accordi detunati, sequencer e tema originale. Niente canto o fruscio. Avvio0,8s su Play, continuità e risoluzione entro60s; volume0,9 con fade finale. Nessun ducking perché non c'è voce. Il mix di provaPCM60s misura picco−2,6dBFS; ascolto estetico finale demandato alla revisione utente.
- **Effetti:** un clic meccanico <=100 ms coincidente con il primo Play. Nessun altro suono di conferma o whoosh. Il clic è editoriale, non prova del suono di sistema Android.
- **Audio acquisizioni:** montare capture silenziose; la base è accompagnamento editoriale, non dimostrazione del suono che esce dall'app. Non utilizzare accidentalmente musica della libreria come colonna sonora.
- **Sottotitoli:** nessun sottotitolo di parlato né SRT vuoto. I titoli bastano per la comprensione muta; preservare testi UI reali.
- **Movimento e look:** applicare `frame.md`, unica verità visiva di questo progetto.

## Notes

### Esecuzione

- **Consegna VIDEO-T05:** versione illustrata con nuova musica120BPM approvata
  ed esportata in renders/walktape-60s-retro80.mp4. Durata60s,1080×1920,30fps,
  H264/AACstereo; verifiche effettive dell'output in QA.md. Nessuna pubblicazione.

- **Revisione VIDEO-T03:** feedback screenshot parziali poco apprezzati; utente
  sceglie A, illustrazioni originali locali. Sostituiti 8–20/20–35/35–45s con
  raccolta di dischi/copertine, cassetta personale e cassetta/cuffie/mappa.
  Player0–8 e45–54, firma54–60 e audio conservati. Questa revisione prevale sulle
  vecchie indicazioni capture-only delle scene2/3 e prima parte4 sotto riportate
  come regia precedente. STORYBOARD contiene il trattamento corrente.
  SVG/PNG e provenienza in assets/illustrations; confronto in boards/illustrations.html.

- Workflow general-video: flow automation, storyboard yes; mode derivato collaborative. Prima piano e bozzetti da rivedere, poi costruzione, anteprima finale e conferma render. La conferma del brief non autorizza a saltare queste revisioni.
- Fonti, contenuti, direzione e scene sono già decisi. Restano scelte tecniche di esecuzione e verifica degli asset, non nuove domande di regia.
- Setup completato con HyperFrames CLI 0.8.171. Piano e bozzetti approvati; index.html monta ora sei scene animate da 60 s complessivi, con registrazioni/schermate reali e musica originale. Board dei bozzetti conservata; anteprima Studio aperta per conferma prima del render. Nessun MP4 finale ancora consegnato.
- Skill nello scope progetto: `.agents/skills` alla radice Android. Le installazioni HyperFrames standard sono globali: usare `HYPERFRAMES_SKIP_SKILLS=1` per i comandi CLI e gestire installazioni/aggiornamenti con `skills add ... --agent opencode` senza `--global`. Su PowerShell usare `npx.cmd`.
- Acquisire con strumenti Android (ADB/emulatore o telefono), non con Playwright. Preferire account/playlist di prova e materiali utilizzabili; non modificare la libreria personale per allestire il video. Non inserire segreti nei media o nei file.
- Produzione: raccolta originale separata di tre brani/cover, server Navidrome temporaneo porta 4534, capture AVD; playback offline registrato dopo arresto del server. Musica locale approvata, Roboto corsivo e altri font incorporati. Nessuna UI simulata.

### Arco e confini

0–8 s fascino/rivelazione; 8–20 s raccolta Navidrome; 20–35 s mixtape; 35–50 s
download/offline; 50–60 s firma e invito (motto 50–54, scheda 54–60).
La griglia 96 BPM guida gli accenti ma non sposta questi confini.

### Scene

Scene, ordine, durate e consegne sono confermate; lo storyboard le sviluppa.
Tutte le scene hanno fondo #101A30. Tutte senza voce. Gli intervalli di testo
sono in secondi globali; le coppie successive non vanno sovrapposte. Entrata
testi di 0,2 s inclusa negli intervalli; almeno 2,5 s di lettura ferma.

#### 1 · Premi Play — 0–8 s (8 s)

- **Idea:** il fascino della cassetta diventa un'app Android.
- **Protagonista/ingresso:** dettaglio della cassetta reale, inizialmente ferma; pressione Play, bobine in movimento, allargamento al player completo nei primi 2,5 s. Poi camera ferma.
- **Testo:** «Il piacere di una cassetta» 0,2–3,2 s; «La tua musica su Android» 4–7,5 s.
- **Voce:** nessuna. **Suono:** avvio base su Play e unico clic <=100 ms sul gesto.
- **Uscita/consegna:** taglio sulla copertina del brano verso la stessa copertina in libreria/album.
- **Dopo questa scena:** «WalkTape è un'app Android con un player dall'anima rétro».

#### 2 · La tua raccolta — 8–20 s (12 s)

- **Idea:** dietro il player c'è la musica del proprio server.
- **Protagonista/ingresso:** stessa copertina per introdurre album e libreria reale; scelta di un brano. Inquadratura ferma durante i gesti.
- **Testo:** «Dal tuo server Navidrome» 8,2–12 s; «Scegli cosa ascoltare» 14–18 s.
- **Voce:** nessuna. **Suono:** base continua, nessun effetto aggiunto.
- **Uscita/consegna:** il brano scelto si ritrova nella coda della scena successiva.
- **Dopo questa scena:** «WalkTape riproduce la raccolta del mio server Navidrome».

#### 3 · La mia mixtape — 20–35 s (15 s)

- **Idea:** la selezione personale diventa una playlist da conservare.
- **Protagonista/ingresso:** brani reali nella coda, assegnazione del nome «La mia mixtape», salvataggio come playlist. Tagli tra passaggi, nessuna accelerazione dei comandi da leggere.
- **Testo:** «Crea la tua mixtape» 20,2–25 s; «Salvala come playlist» 28–33 s.
- **Voce:** nessuna. **Suono:** base continua, nessuna conferma aggiunta.
- **Uscita/consegna:** nome della playlist raccordato alla medesima selezione nei download.
- **Dopo questa scena:** «Posso scegliere l'ordine dei brani e salvare la mia selezione».

#### 4 · Anche offline — 35–50 s (15 s)

- **Idea:** i brani scaricati restano ascoltabili senza collegamento al server.
- **Protagonista/ingresso:** download reale della playlist, taglio al completamento, attivazione Solo offline e riproduzione effettiva. I tagli rendono chiara la sintesi dell'attesa, senza fingere download istantaneo.
- **Testo:** «Scarica la tua playlist» 35,2–40 s; «La tua musica. Anche offline» 43–48,5 s.
- **Voce:** nessuna. **Suono:** base editoriale continua, nessun effetto aggiunto.
- **Uscita/consegna:** cassetta in riproduzione raccordata al dettaglio seguente.
- **Dopo questa scena:** «I brani scaricati si ascoltano anche senza collegarsi al server».

#### 5 · Il tuo ritmo — 50–54 s (4 s)

- **Idea:** riportare i benefici al piacere dell'ascolto.
- **Protagonista/ingresso:** player frontale reale, etichetta «La mia mixtape», bobine in movimento; camera ferma.
- **Testo:** «La tua musica. Il tuo ritmo.» 50,2–53,5 s.
- **Voce:** nessuna. **Suono:** frase musicale verso la chiusura, nessun effetto aggiunto.
- **Uscita/consegna:** taglio dal marchio nel player alla firma finale nella stessa area visiva.
- **Dopo questa scena:** «Il carattere rétro accompagna la musica che scelgo io».

#### 6 · Prova WalkTape — 54–60 s (6 s)

- **Idea:** un passo successivo concreto e il requisito essenziale.
- **Protagonista/ingresso:** icona originale e marchio, entrata 6 frame, poi scheda ferma fino a fine video.
- **Testi:** «WalkTape»; «Scarica l'APK su GitHub»; `github.com/rosariomoscato/walktape`; «Per Android · Richiede un server Navidrome». Invito, link e requisito in blocchi distinti; URL può andare a capo dopo `github.com/`, mantenendo testo completo.
- **Voce:** nessuna. **Suono:** risoluzione musicale a 60 s, nessun effetto aggiunto.
- **Uscita/consegna:** fine sulla scheda leggibile, nessuna dissolvenza che sottragga lettura.
- **Dopo questa scena:** «So dove trovare WalkTape e che serve un server Navidrome».
- **Controllo d'insieme 10.1 A:** il motto non si ripete qui perché è già in scena 5. Nessun cambiamento delle durate.

### Tagli, nomi e vincoli

- Fuori configurazione/login, VPN, diagnostica, backup, aggiornamenti; nessun ampliamento ad Android Auto o promesse hardware non verificate.
- Nomi invarianti: WalkTape; server Navidrome; La mia musica; La mia mixtape; playlist; Solo offline. Mixtape = selezione personale nella coda, playlist = versione salvata.
- Nessun finto screenshot, dato o risultato. Nessun glitch, VHS, grana, aberrazione cromatica, particella, rimbalzo elastico o whoosh.
- Nessuna cornice smartphone, logo editoriale persistente o barra di avanzamento. Marchio reale nell'app conservato.
- Leggibilità di UI e scheda finale da verificare su bozzetti e snapshot nel feed. Contrasti dei token già calcolati; quello delle capture va misurato in produzione.

### Provenienza delle decisioni

- **Dette:** soggetto WalkTape, 60 s, fonti dalla codebase; 7.1 C nessuna voce. Scene 1–6 confermate esplicitamente; foglio complessivo confermato dopo controllo d'insieme.
- **Delegate 1.1–1.4:** messaggio, domanda, aggancio e tagli di Intent/Notes.
- **Delegate 2.1–2.4:** pubblico, risultato, social verticale e italiano di frontmatter/Intent.
- **Delegate 3.1–3.2:** capture reali con raccolta di prova e sola identità WalkTape in Assets.
- **Delegate 4.1–4.5:** arco, apertura, caso, svolta e chiusura sviluppati nelle scene.
- **Delegate 5.1–5.4:** direzione A, rappresentazione, assenza cornice ed esclusioni in frame.md.
- **Delegate 6.1–6.5:** ritmo, entrate, quiete, transizioni e camera in frame.md.
- **Delegate 7.2–7.6:** testo, nomi, musica, clic e nessuna trascrizione senza parlato in Customizations/Notes.
- **Delegate 9.1–9.2:** storyboard yes e flow automation, confermati nel foglio finale.
- **Delegata 10.1 A:** togliere soltanto il motto ripetuto dalla scheda finale; tempi invariati.
