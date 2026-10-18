# WalkTape — regia confermata e consegnata

Task: VIDEO-T01. Slug: walktape-la-tua-musica. Data: 2026-10-18.
Stato: foglio completo confermato dall'utente con «confermo». Regia consegnata in
`../../videos/walktape-la-tua-musica/BRIEF.md` e `frame.md`. Nessun video costruito.

## Decisioni dette

- Soggetto: WalkTape, app sviluppata in questa codebase.
- Durata: 60 secondi.
- Materiali: ricavati dal progetto (logo, font, funzioni e particolarità).
- 7.1 Voce: nessuna narrazione parlata (scelta esplicita C).

## Decisioni delegate

Ogni «fai tu» dell'utente è riferito al blocco appena presentato.

- 1.1 Messaggio: «Il piacere di una cassetta, la tua musica su Android».
- 1.2 Domanda: «Cos'è WalkTape e perché dovrei provarla?».
- 1.3 Aggancio: ribaltare l'idea che un lettore rétro debba rinunciare alle comodità di oggi.
- 1.4 Tagli: configurazione, VPN, diagnostica, backup, aggiornamenti. Focus su player, libreria, mixtape, offline.
- 2.1 Pubblico: appassionati con raccolta propria, anche senza conoscenza di Navidrome.
- 2.2 Risultato: «WalkTape è un lettore Android per la mia musica su Navidrome, con un player rétro e download per ascoltare offline».
- 2.3 Destinazione: social verticale, 9:16, 1080 × 1920.
- 2.4 Lingua: italiano, schermate comprese.
- 3.1 Schermate: acquisizioni reali dell'app con raccolta di prova e materiali utilizzabili nel video, ancora da preparare. Nessuna raccolta già acquisita o verificata come disponibile.
- 3.2 Riferimenti: identità WalkTape, senza ambientazioni esterne.
- 4.1 Arco: 0–8 s cassetta/rivelazione; 8–20 s raccolta Navidrome; 20–35 s coda/mixtape; 35–50 s download/offline; 50–60 s marchio/invito.
- 4.2 Apertura: dettaglio cassetta reale ferma; pressione Play, bobine in movimento, allargamento sull'app.
- 4.3 Caso: scegliere brani, mostrare coda «La mia mixtape», salvarla come playlist e mostrarla scaricata in Solo offline. Tempi lunghi sintetizzati con tagli.
- 4.4 Svolta: «La tua libreria Navidrome» e ingresso nella raccolta reale; dietro la cassetta c'è la propria musica.
- 4.5 Chiusura (aggiornata da 10.1 A e conferma finale): ultimi 6 s icona/marchio, «Scarica l'APK su GitHub», github.com/rosariomoscato/walktape, «Per Android · Richiede un server Navidrome». Motto solo in scena 5.

## Materiali e vincoli

- 5.1 Direzione delegata: A · Oggetto notturno. Fondo #101A30, testo #F4F1EA, scocca #2A4D9B → #1B3268, dettagli #C9CED6, accento #F06A21. Roboto 900 titoli 104 px, Roboto 400 corpo 40 px, JetBrains Mono 400 etichette 36 px; marchio Roboto 700 corsivo. Motivo: valorizzare la scoperta della scocca e della cassetta sul blu notte.
- 5.2 Rappresentazione delegata: acquisizioni reali dell'app, ingrandimenti sui comandi e titoli esterni, senza cornice di telefono.
- 5.3 Cornice delegata: nessun elemento permanente; marchio editoriale solo in apertura/chiusura, marchio già presente nella UI conservato.
- 5.4 Esclusioni delegate: glitch, VHS, grana, aberrazione cromatica, particelle, rimbalzi elastici.

- DESIGN.md: token palette, Roboto/sistema, marchio bold italic, contatori monospace.
- app/src/main/res/drawable/ic_launcher_foreground.xml: icona originale approvata.
- README.md / SPEC.md: contenuti funzionali; PROJECT_MEMORY.md / WORKLOG.md: stato e verifiche.
- direzioni.html: tre studi statici di composizione, NON screenshot o UI dimostrativa del prodotto.
- HyperFrames typography.md consultato dal repository ufficiale: Roboto e JetBrains Mono incorporati. Corsivo del marchio da verificare in produzione; preservare brand rispetto ai consigli tipografici generici.
- Nessun asset musicale o screenshot pronto verificato. Riprese/capture restano prerequisito di produzione.
- Nessuna prova Android Auto o prestazione hardware implicita nelle scene.

## Movimento — blocco 6 delegato

- 6.1 Ritmo: 96 BPM, accento ogni 0,625 s; cambi di composizione ogni 4/8 battiti (2,5/5 s). Griglia subordinata ai tempi di lettura e ai confini narrativi, non modifica le durate concordate del blocco 4.
- 6.2 Entrate: 30 fps; testi in 6 frame (0,2 s), spostamento verticale 24 px, arresto netto senza rimbalzo; fermi almeno 2,5 s.
- 6.3 Quiete: sfondo, titolo e inquadratura fermi durante tap/cambi UI; si muovono solo l'azione e gli elementi della riproduzione reale.
- 6.4 Transizioni: tagli su elemento comune nella stessa posizione (cassetta → player, copertina → album, nome mixtape → playlist scaricata); nessun morph inventato dell'interfaccia.
- 6.5 Camera: rivelazione dettaglio cassetta → player completo nei primi 2,5 s; poi inquadrature ferme e tagli fra insieme/dettaglio, nessuno zoom durante i tap.

## Voce e suono — blocco 7

- 7.1 Detta: nessuna voce. Racconto con titoli, interfaccia, musica e un clic.
- 7.2 Delegata: un titolo per volta, massimo 2 righe / 8 parole totali, salvo scheda finale dedicata.
- 7.3 Delegata: WalkTape, server Navidrome, La mia musica, La mia mixtape, playlist, Solo offline. Mixtape = selezione personale in coda, playlist = versione salvata.
- 7.4 Delegata: elettronica strumentale calda a 96 BPM, batteria asciutta, basso morbido e accordi synth; niente canto né fruscio. Materiale utilizzabile per pubblicazione ancora da reperire. Nessun ducking per voce, essendo assente.
- 7.5 Delegata: un solo clic meccanico <=100 ms sulla prima pressione Play; accento editoriale, non dimostrazione del suono Android.
- 7.6 Delegata e adattata alla 7.1 C: nessun sottotitolo di parlato perché non c'è voce; titoli essenziali comunicano il messaggio anche a video muto. Nessun SRT vuoto.

## Scene confermate — blocco 8

L'utente ha risposto «confermo» alla sequenza completa: ordine, durate e contenuti
delle sei scene approvati. Successivamente ha confermato anche il foglio completo,
inclusa la semplificazione finale 10.1 A.

Tutte: fondo #101A30, acquisizioni reali ancora da preparare, nessuna voce.
Le coppie di testo separate da punto e virgola sono successive, mai sovrapposte.

1. 0–8 s, «Premi Play». Cassetta ferma → Play → bobine → player completo. Rivelazione nei primi 2,5 s, poi camera ferma. Titoli: «Il piacere di una cassetta» (0,2–3,2); «La tua musica su Android» (4–7,5). Un clic <=100 ms su Play, base 96 BPM. Uscita: taglio sulla copertina verso album/libreria. Dopo: è un'app Android dall'identità rétro.
2. 8–20 s, «La tua raccolta». Libreria e album reali; taglio sulla stessa copertina e vista ferma durante azioni. Titoli: «Dal tuo server Navidrome» (8,2–12); «Scegli cosa ascoltare» (14–18). Nessun effetto aggiunto. Uscita: selezione di un brano consegnata alla coda. Dopo: la musica arriva dal proprio server Navidrome.
3. 20–35 s, «La mia mixtape». Brani nella coda, nome La mia mixtape, salva come playlist; raccordi tramite titolo/brano comune, tempi tecnici compressi con tagli. Titoli: «Crea la tua mixtape» (20,2–25); «Salvala come playlist» (28–33). Nessun effetto aggiunto. Uscita: nome della playlist raccordato alla stessa playlist nei download. Dopo: si sceglie l'ordine e si conserva la selezione.
4. 35–50 s, «Anche offline». Download reale della playlist, esito completo, Solo offline e riproduzione. Nessuna falsa durata download: tagli espliciti tra avanzamento ed esito. Titoli: «Scarica la tua playlist» (35,2–40); «La tua musica. Anche offline» (43–48,5). Nessun effetto aggiunto. Uscita: cassetta in riproduzione raccordata al dettaglio successivo. Dopo: i brani scaricati si ascoltano senza collegamento al server.
5. 50–54 s, «Il tuo ritmo». Player reale con La mia mixtape e bobine in riproduzione, fermo frontale. Titolo «La tua musica. Il tuo ritmo.» (50,2–53,5). Nessun effetto aggiunto. Uscita: taglio al marchio nella stessa area visiva. Dopo: il piacere rétro accompagna la propria selezione.
6. 54–60 s, «Prova WalkTape». Icona originale e marchio, scheda statica dopo entrata 6 frame. Testi: WalkTape; «Scarica l'APK su GitHub»; github.com/rosariomoscato/walktape; «Per Android · Richiede un server Navidrome». Motto omesso qui per decisione 10.1 A, resta nella scena 5. Invito e requisito in blocchi distinti. Nessun effetto aggiunto, risoluzione musicale a fine video. Dopo: dove scaricare l'APK e quale server occorre.

Somma: 8 + 12 + 15 + 15 + 4 + 6 = 60 s. I titoli principali hanno almeno 2,5 s di lettura ferma. Scheda finale semplificata da decisione 10.1 A: verificare leggibilità reale sui bozzetti; URL su due righe se necessario senza abbreviazioni ambigue.

## Lavorazione — blocco 9 delegato

- 9.1 Storyboard: yes, revisione piano/bozzetti prima della costruzione.
- 9.2 Flow: automation. Mode derivato collaborative per storyboard yes; non companion.
- Il workflow richiede comunque conferma prima del render finale.

## Controllo d'insieme — decisione 10.1 A delegata

Conseguenza combinata di 9:16/feed, assenza di voce, cornice social e cinque
elementi finali in 6 s: rischio di lettura densa. Il motto è già letto nella scena
5 (50–54 s), quindi ripeterlo nella scheda 6 sottrae spazio a link e requisito.

Proposta A adottata dopo «fai tu» dell'utente: togliere soltanto il motto ripetuto
dalla scena 6; conservare icona/marchio, invito APK, URL e requisito Navidrome,
6 s finali e tutte le durate già confermate. Il motto resta nella scena 5.
Alternative B/C non adottate. Questa decisione aggiorna 4.5 e scena 6 soltanto
per l'omissione del motto ripetuto; tutte le durate confermate sono conservate.

Direzione A e motivo registrati in direzioni.html dopo delega dell'utente sul blocco 5.
Blocco 6 chiuso con delega dell'utente sulle scelte consigliate B/A/A/B/A.
Blocco 7 chiuso: scelta esplicita 7.1 C e delega sulle altre; sottotitoli/ducking adattati all'assenza di voce.
Blocco 8 confermato esplicitamente. Blocco 9 delegato alle scelte consigliate yes/automation.
Valori verificati su hyperframes/references/brief-contract.md ufficiale: automation+yes comporta revisione collaborativa del piano; automation+no esecuzione autonoma, companion lavoro guidato. Il render finale resta soggetto a conferma nel workflow corrente.
Decisione 10.1 A applicata, scena 6 aggiornata. Foglio completo confermato.

## Consegna verificata

- Progetto inizializzato PRIMA dei due file con HyperFrames CLI 0.8.171, blank portrait.
- `../../videos/walktape-la-tua-musica/BRIEF.md`: intento, asset, musica/clic, sei scene complete e provenienza delle decisioni.
- `../../videos/walktape-la-tua-musica/frame.md`: token direzione A, composizione e movimento.
- Formati verificati sui riferimenti ufficiali e sulle skill installate nel progetto.
- YAML e campi canonici verificati con parser; sei scene contigue 0–60 s, 1800 frame a 30 fps, palette/font allineati alla direzione A, percorsi fonti esistenti, CLI fissata 0.8.171.
- 11 skill HyperFrames (core più general-video) installate per OpenCode nello scope progetto; check ufficiale 11 current, 0 outdated, 0 coreMissing, 0 removed.
- Il comando standard skills update installa globalmente: usato HYPERFRAMES_SKIP_SKILLS=1 per update/init e installer skills con --agent opencode senza --global. Nessuna preferenza globale registrata.

## Prossima azione

Riavviare OpenCode per caricare le nuove skill e avviare:
`/hyperframes esegui il brief in videos/walktape-la-tua-musica`.
Primo checkpoint: piano e bozzetti (storyboard yes), poi costruzione.
Acquisizioni Android, musica e font corsivo restano da verificare in produzione.
index.html è lo scaffold blank, non il video finale; nessun render/check runtime del film eseguito.
