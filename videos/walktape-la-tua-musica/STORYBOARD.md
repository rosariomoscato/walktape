---
format: 1080x1920
duration: 60s
fps: 30
message: "Il piacere di una cassetta, la tua musica su Android"
arc: "Scoperta → raccolta personale → mixtape → offline → piacere → invito"
audience: "Appassionati con una raccolta musicale propria, anche senza conoscenza di Navidrome"
workflow: general-video
flow: automation
storyboard: yes
mode: collaborative
language: it
plan_status: approved
sketch_status: approved
layout_approved: true
final_status: rendered_verified
production_task: VIDEO-T05
output: renders/walktape-60s-retro80.mp4
music_bpm: 120
---

# Piano di produzione — WalkTape

Questo video racconta agli appassionati con una raccolta musicale propria che
possono ritrovare il piacere di una cassetta ascoltando la propria musica su Android.
L'identità rétro apre il racconto; raccolta Navidrome, mixtape e offline ne sono
il racconto. L'invito finale chiarisce sia la destinazione GitHub sia il requisito
del server, senza aggiungere un tutorial di configurazione.

## Stato e fonti

### Consegna VIDEO-T05 — MP4 esportato e verificato

Alla richiesta di conferma render l'utente risponde «ok, come funziona, devo
scaricare io il video?». Render locale eseguito: renders/walktape-60s-retro80.mp4,
60s/1080×1920/30fps/1800frame,H264+yuv420p/AACstereo,4.155.158byte.
Fotogrammi decodificati e audio verificati; dettagli in QA.md. File già sul
computer dell'utente, nessun download da un servizio o pubblicazione richiesti.

### Revisione musicale VIDEO-T04 — versione da ascoltare

Utente approva la revisione visiva («va bene») e chiede musica «più anni80 e molto
più ritmata». Composta una nuova base originale locale synth-pop120BPM, con
cassa in quattro/rullante elettronico/basso in ottave/sequencer e tema originale.
Solo la traccia musicale è sostituita; immagini, animazioni, titoli,60s e clic
iniziale sono conservati. Previewaudio60s in proofs/retro80-mix-preview.wav,
pagina boards/music.html. In attesa di feedback musicale o conferma render.

### Revisione illustrata — VIDEO-T03, direzione A approvata

L'utente apprezza le sequenze con la cassetta reale in riproduzione e chiede di
sostituire i ritagli parziali delle altre schermate, suggerendo immagini generate.
L'utente sceglie «A, seguo il tuo consiglio»: illustrazioni originali locali.
Create tre immagini e sostituiti gli screenshot nei segmenti indicati sotto;
le sequenze reali della cassetta restano conservate.

- Preservare frame1 (0–8), parte player del frame4 (45–50), frame5 (50–54) e
  firma finale frame6 (54–60), salvo futura indicazione esplicita.
- Sostituire materiale screenshot di frame2 (8–20), frame3 (20–35) e prima
  parte frame4 (35–45); messaggi, durata60s, musica e tipografia restano riferimento.
- Proposta contenuti: raccolta musicale personale come oggetti/cover; cassetta
  con etichetta della propria selezione; musica da portare con sé come cassetta
  e cuffie in viaggio. Grafica editoriale, non false schermate WalkTape.
- Look adottato: illustrazioni vettoriali originali locali blu/crema/argento,
  non immagini prodotte da un modello generativo. SVG e PNG1080×1400 in
  assets/illustrations; pagina affiancata boards/illustrations.html.
- Nuovo approccio sostituisce il precedente vincolo capture-only nei segmenti
  indicati, su richiesta dell'utente; non riaprire l'intera discovery.
- Le tre immagini sono presentate insieme e nel montaggio in Studio per revisione.
  Check:0errori/1warning già revisionato,14/14contrasti,motion300campioni.
  Nessun render fino ad approvazione della revisione finale.

BRIEF.md e frame.md sono già confermati. Questo documento sviluppa la lavorazione
senza cambiare scene, messaggio o durata. Piano e bozzetti approvati dall'utente
(«approvo i bozzetti»). Tutti i frame sono ora animated, con media Android reali,
titoli e audio originale locale. La board di layout in boards/ è conservata;
prove aggiornate in proofs/. Anteprima finale in attesa di approvazione render.

Primo checkpoint approvato dall'utente («approvo»). Bozzetti statici con colori,
font e testi definitivi, blocchi esplicitamente etichettati al posto delle capture
non ancora disponibili, approvati su boards/storyboard.html. I pannelli sono stati
riempiti con acquisizioni vere senza modificare l'impaginazione confermata.

Riferimenti di esecuzione: hyperframes/references/storyboard-format.md e
review-loop.md; hyperframes-creative/references/storyboard-recipe.md,
house-style.md, video-composition.md e beat-direction.md. Il frame.md di questo
progetto prevale sui consigli generici di house-style (niente decorazioni/derive
continue, nessuna cornice telefono, mantenere Roboto di brand).

## Architettura e ritmo

- Sei sub-composizioni autonome, una per scena, montate in index.html a 0/8/20/35/50/54 s.
- Un solo proprietario della timeline per composizione; media riprodotti dal framework. Ogni src mantiene lo stesso ID fra bozzetto e costruzione.
- Capture reali come video muti. Titoli editoriali separati dalla UI, mai ricostruzione DOM della UI Android.
- 30 fps, 1800 frame esatti. Audio sul master separato dalle scene, nuova base120BPM senza voce; unico clic su Play. Nessun SRT.
- Ritmo: scoperta breve → dimostrazione leggibile → selezione → prova offline → respiro → invito fermo.
- Titoli 6 frame/24 px/arresto netto, poi >=2,5 s fermi. Lettura e azioni restano alle finestre approvate, indipendenti dalla nuova griglia musicale (0,5 s/battito).
- Nessuno zoom durante un tap. Nell'apertura il gesto precede la rivelazione della camera, che termina entro 2,5 s.
- Crop proporzionali: il player è l'oggetto, non una schermata racchiusa in un altro dispositivo. Contenuti essenziali nell'area sicura x=90–990, y=180–1680.
- 6 scene brevi: lavorazione inline, senza deleghe a subagenti.

## Scelta dei trattamenti

Catalogo live consultato con `match cut same object hard cut real app footage`:
match-cut, text-match-cut e cut-the-curve richiedono moto/accelerazioni; app-showcase
aggiunge cornici smartphone. Non sono adatti ai tagli fermi e alle acquisizioni
reali concordate. Nessun componente installato: il raccordo è un taglio semplice
fra due crop allineati dello stesso elemento, non un morph o una transizione
ricostruita. I blueprint sotto forniscono la grammatica, non aggiunte al brief.

## Frame 1 — Premi Play

- status: animated
- src: compositions/frames/01-premi-play.html
- duration: 8s
- start: 0s
- poster: 5.5s
- type: hook
- narrativeRole: "Il piacere rétro rende desiderabile la musica propria su Android"
- scene: "Dalla cassetta al player completo, un unico allargamento motivato"
- transition_in: cut
- act: Scoperta
- blueprint: zoom-out-workspace-reveal
- rules: viewport-change
- assets: capture-player-paused, capture-player-playing

**Esperienza:** sembra una cassetta che si mette in moto; la rivelazione mostra
che l'oggetto vive nell'app. Curiosità, poi riconoscimento.

**Composizione:** titolo alto a sinistra, area x=90–990/y=180–500. Player nella
zona centrale/inferiore, con cassetta grande e Play leggibile. Nel bozzetto,
blocco media blu esplicitamente etichettato «Capture player da acquisire»;
nessun finto stato UI. Poster dopo la rivelazione, secondo titolo visibile.

**Coreografia:** primo gesto e clic con camera ferma; poi una sola ritirata fino
al player completo, fermo entro 2,5 s. Applicazione adattata di
zoom-out-workspace-reveal: nessun workspace/browser aggiunto, nessun secondo
zoom, nessuna animazione di bobine disegnata sopra un fermo immagine.

**Titoli globali:** «Il piacere di una cassetta» 0,2–3,2 s;
«La tua musica su Android» 4–7,5 s. Testi in due righe naturali, senza spezzare parole.

**Suono:** nuova base synth-pop120BPM dall'effettivo primo Play; unico clic <=100 ms
su quel gesto. Il tempo preciso del clic segue la capture, non un tap inventato.

**Consegna:** copertina del brano nella stessa posizione del dettaglio album
successivo, taglio a 8 s. Lo spettatore sa che è un'app Android dall'anima rétro.

## Frame 2 — La tua raccolta

- status: animated
- src: compositions/frames/02-la-tua-raccolta.html
- duration: 12s
- start: 8s
- poster: 2s
- type: product_intro
- narrativeRole: "Collega la cassetta alla raccolta personale, spiegando la sorgente Navidrome"
- scene: "Illustrazione originale di una collezione musicale personale"
- transition_in: cut
- act: La musica è tua
- blueprint: titlecard-reveal
- variant: illustrated-hold
- rules: discrete-text-sequence
- assets: assets/illustrations/01-raccolta.png

**Esperienza:** la propria discoteca diventa protagonista. Dischi e copertine
originali evocano la scelta personale, mentre il testo chiarisce la provenienza
Navidrome senza un elenco dell'interfaccia.

**Composizione:** titolo su due righe nella fascia alta e immagine originale
ampia nel pannello centrale; gerarchia e margini del layout confermato conservati.

**Coreografia:** posa illustrata ferma per 12s; entrate e sostituzioni dei due
titoli conservate. Adattamento quieto di titlecard-reveal, senza movimento
aggiunto o UI ricostruita. discrete-text-sequence riguarda solo le frasi editoriali.

**Titoli globali:** «Dal tuo server Navidrome» 8,2–12 s;
«Scegli cosa ascoltare» 14–18 s.

**Suono:** base continua. **Consegna:** taglio dalle copertine alla cassetta
personale della scena3; lo spettatore sa da dove arriva la musica.

## Frame 3 — La mia mixtape

- status: animated
- src: compositions/frames/03-la-mia-mixtape.html
- duration: 15s
- start: 20s
- poster: 4s
- type: feature_showcase
- narrativeRole: "Racconta il piacere di scegliere e conservare la propria selezione"
- scene: "Illustrazione originale di una cassetta personale e del suo cartoncino"
- transition_in: cut
- act: La musica è tua
- blueprint: titlecard-reveal
- variant: illustrated-hold
- rules: discrete-text-sequence
- assets: assets/illustrations/02-mixtape.png

**Esperienza:** i brani scelti diventano una sequenza personale, non una lista di
funzioni. Il nome della selezione dà un'identità alla musica che si porta con sé.

**Composizione:** titolo alto e cassetta illustrata grande nel pannello centrale,
con etichetta grafica, nastro e cartoncino. Nessuna lista, dialog o dato fittizio.

**Coreografia:** immagine ferma per15s, titoli alle finestre già confermate.
Titlecard-reveal adattato a posa illustrata; nessuna falsa dimostrazione di Salva.

**Titoli globali:** «Crea la tua mixtape» 20,2–25 s;
«Salvala come playlist» 28–33 s.

**Suono:** base continua. **Consegna:** dalla cassetta personale alla cassetta
con cuffie/mappa; lo spettatore comprende il valore di conservare la selezione.

## Frame 4 — Anche offline

- status: animated
- src: compositions/frames/04-anche-offline.html
- duration: 15s
- start: 35s
- poster: 10s
- type: benefit_highlight
- narrativeRole: "Dimostra che la musica scaricata resta disponibile senza collegamento al server"
- scene: "Illustrazione di cassetta, cuffie e mappa, poi ritorno al player reale offline"
- transition_in: cut
- act: Portala con te
- blueprint: device-surface-showcase
- variant: static-tour
- rules: discrete-text-sequence
- assets: assets/illustrations/03-offline.png, capture-offline-playing

**Esperienza:** la propria musica si porta con sé. Cuffie e mappa evocano il
viaggio, poi il player reale riporta il racconto al carattere distintivo dell'app.

**Composizione:** immagine originale ampia da35a45s, player offline vero da45a50s,
nella stessa zona. Titoli fuori dall'immagine; nessun cambio alla ripresa player.

**Coreografia:** posa illustrata0–10s locali, taglio al video reale10–15s.
Titoli e timing invariati; niente indicatore di avanzamento o controllo UI simulato.

**Titoli globali:** «Scarica la tua playlist» 35,2–40 s;
«La tua musica. Anche offline» 43–48,5 s.

**Suono:** la base editoriale continua; non è prova acustica del flusso Android.
**Consegna:** player offline alla medesima cassetta nella scena successiva a 50 s.
Lo spettatore sa che l'offline riguarda i contenuti scaricati.

## Frame 5 — Il tuo ritmo

- status: animated
- src: compositions/frames/05-il-tuo-ritmo.html
- duration: 4s
- start: 50s
- poster: 2s
- type: branding
- narrativeRole: "Riunisce controllo personale e piacere rétro prima dell'invito"
- scene: "Player frontale con La mia mixtape e motto unico"
- transition_in: cut
- act: Firma
- blueprint: titlecard-reveal
- assets: capture-player-playing

**Esperienza:** una pausa di lettura, non un'altra funzione da imparare. La
cassetta continua a vivere mentre si legge il motto.

**Composizione:** player frontale nella stessa zona media delle scene precedenti;
«La tua musica. / Il tuo ritmo.» nella fascia alta. Nessun elemento editoriale
permanente. Il marchio già nell'app resta visibile.

**Coreografia:** titlecard-reveal adattato: una sola entrata di titolo 6 frame/
24 px, poi quiete completa della composizione; bobine soltanto dalla capture
in riproduzione. Nessun crossfade, glow o drift suggerito da esempi generici.

**Titolo globale:** «La tua musica. Il tuo ritmo.» 50,2–53,5 s.
**Suono:** base verso la risoluzione. **Consegna:** posizione del marchio nella
UI raccordata alla firma finale, taglio a 54 s.

## Frame 6 — Prova WalkTape

- status: animated
- src: compositions/frames/06-prova-walktape.html
- duration: 6s
- start: 54s
- poster: 3s
- type: cta
- narrativeRole: "Trasforma l'interesse in un'azione concreta con il requisito Navidrome chiaro"
- scene: "Icona originale, marchio, APK su GitHub, indirizzo completo e requisito"
- transition_in: cut
- act: Firma
- blueprint: titlecard-reveal
- assets: original-launcher-icon, brand-roboto-italic

**Esperienza:** una firma chiara e calma, abbastanza a lungo da leggere cosa
scaricare e per quale sistema. Non suggerisce un servizio musicale o un account
WalkTape da creare.

**Composizione proposta:** icona e marchio nel terzo superiore dell'area sicura;
invito nella fascia centrale; URL su due righe `github.com/` e
`rosariomoscato/walktape` (testo completo); requisito in basso ma sopra y=1680.
Corpo 40 px ed etichette 36 px senza riduzioni per far entrare più testo. Firma
Roboto 700 corsivo da verificare/incorporare autentica prima della produzione.

**Coreografia:** un unico gruppo entra in 6 frame, poi scheda ferma da 54,2 a
60 s. titlecard-reveal adattato alla durata finale di 6 s; non è una catena di
card. Nessun duplicato del motto, nessuna dissolvenza finale.

**Testi:** WalkTape; «Scarica l'APK su GitHub»;
`github.com/rosariomoscato/walktape`;
«Per Android · Richiede un server Navidrome».

**Suono:** risoluzione entro 60 s, nessun effetto. **Consegna:** fine sulla firma;
lo spettatore sa dove trovare l'APK e quale server occorre.
