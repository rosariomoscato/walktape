# Verifica costruzione — VIDEO-T02

## Consegna verificata VIDEO-T05

- Approvazione render ricevuta con «ok» in risposta alla domanda finale sulla
  base musicale e sul render; chiarito che il file viene generato localmente.
- CLI0.8.171 già corrente al probe upgrade. Render delivery/30fps/2workers
  completato in4m55,2s: screenshot capture/hardwareGPU, compile3,6s,
  extract9,2s,audio11,5s,probe1s,setup1,7s,capture195,9s,encode68,3s,assemble1,8s.
- Artefatto: renders/walktape-60s-retro80.mp4,4.155.158byte (~4MB).
- FFprobe e tools/verify-render.cjs: PASS durata60,000000s,1080×1920,
  30/1fps,1800frame,H264High/yuv420p/BT709, audioAAC-LC48000Hz/stereo.
- Nove frame decodificati dal MP4 a0,4/5,5/10/24/39/47/52/57/59,95s,
  contact sheet e dettagli full-resolution ispezionati. Player e illustrazioni
  presenti, testo leggibile, firma mantenuta nell'ultimo fotogramma campionato.
  Evidenze e metadataffprobe in proofs/rendered; non sono snapshot dell'editor.
- Audio AAC realmente decodificato: mean−16,1dBFS/max−2,3dBFS, senza clipping
  rilevato. Non equivale a un ascolto umano completo dell'output su hardware.
- Nessun upload o pubblicazione. File locale ignorato da Git. Preview rimane
  disponibile per modifiche; nessun nuovo test Android necessario all'esportazione.

## Revisione musicale corrente VIDEO-T04

- Utente approva il look illustrato e richiede musica più anni80/molto più ritmata.
  Nuova base originale120BPM; solo src/gain della musica cambiati in index.html.
  Durata, immagini, animazioni, clic80ms e inizio0,8s conservati.
- Sorgente59,2s/44,1kHz/stereo, picco−1,7dBFS/RMS−15,0dBFS. Mix PCM60s con
  volume0,9, clic0,6 e fade finale: mean−16,0dBFS/max−2,6dBFS, nessun clipping.
  FFprobe:60,000000s/pcm_s16le/44100Hz/2canali. Questa prova riproduce i parametri
  delle tracce, non è ancora verifica del mix codificato nel futuro MP4.
- Check0.8.171 passato: runtime/layout/motion0errori,14/14contrasti,300campioni
  motion, lint0errori/1warning già valutato (startlocaleoffline). Browser eseguito.
  Report non salvato per lockpreview19816, outputJSON completo verificato.
- Ascolto estetico della nuova musica da parte dell'utente ancora da raccogliere;
  nessuna valutazione percettiva o renderMP4 dichiarata dall'agente.
- Fileascolto proofs/retro80-mix-preview.wav; pagina boards/music.html;
  metadati/generator originali in assets/audio/retro80-provenance.json e tools/compose-retro80.cjs.

## Revisione corrente VIDEO-T03 — illustrazioni originali

- Direzione A scelta dall'utente. Tre SVG originali locali e PNG1080×1400
  sostituiscono screenshot di8–20/20–35/35–45s. Player45–50 conservato, come
  apertura e scene5/6. Durata60s, audio e timing titoli conservati.
- Hash delle composizioni protette prima/dopo identici: frame1
  138e18b224412421f3a2d5d736afc8a26694d637; frame5
  a5db6a48a9b46f95ee526f3e36b124ef23be36a9; frame6
  64f4d4e8e779446efc1b592f289fd6a347cbd7eb.
- Check0.8.171 della revisione: oktrue/browserSkippedfalse, lint0errori/1warning,
  runtime/layout/motion0errori,14/14contrasti,300campioni motion. Warning unico:
  startlocale10 del video offline, conservato e già verificato come assoluto45s.
  Report non salvato per lock history del processo preview19816; risultato JSON
  ricevuto integralmente, non presentato come artefatto salvato.
- Sei snapshot a5,5/10/24/39/52/57s in proofs/illustrated, contact sheet e dettagli
  ispezionati; tre PNG originali ispezionati prima del montaggio. Nessun vecchio
  screenshot parziale montato nei segmenti illustrati. Articoli e immagini nella
  pagina affiancata boards/illustrations.html.
- Rasterizzazione: Puppeteer richiedeva Chrome155 assente; tentativo con Chrome153
  completo in timeout, riuscito con headless-shell153 già disponibile. Nessun
  browser o modello installato. Prima snapshot senza FFmpeg nel PATH fallita,
  ripetuta con binari locali nel PATH del solo processo e riuscita.
- Nessun render della revisione ancora eseguito. Richiesta revisione immagini e
  montaggio prima della conferma MP4; il resto di questo file descrive la baseline.

## Risultato corrente

Composizione 1080×1920, 60 s, 30 fps, sei scene; render finale non eseguito.
Piano e bozzetti approvati. Musica originale locale approvata dall'utente;
acquisizioni Android vere con raccolta di prova originale, non mockup.

Studio: http://127.0.0.1:3002/#project/walktape-la-tua-musica
Anteprima persistente verificata con preview --status, pagina HTTP 200 e aperta
nel browser predefinito. È l'ambiente di revisione prima del render.

## Controlli

- HyperFrames 0.8.171 check finale: **ok true**, browserSkipped false; runtime e
  layout 0 errori, contrasto 14/14, cinque assertion di movimento su 300 campioni
  senza errori. Tempi layout: 0,5/1,5/5,5/10/15/21/24/28,5/32/36/39/43,5/46/52/57/59,95.
- Report finale ignorato: .hyperframes/artifacts/checks/c817729f-df3b-43f8-997e-a0c4811179b1.json.
- Primo check senza motion sidecar passato; prima esecuzione con sidecar ha
  rilevato il wrapper finale a tutta altezza traslato di 4 px fuori canvas durante
  l'entrata. Corretto animando soltanto il gruppo contenuti a dimensione naturale;
  assertion conservata e check ripassato. Il check concorrente a snapshot aveva
  anche segnalato lock della history: controllo finale eseguito in sequenza e salvato.
- Due warning lint revisionati: 5 stati nella stessa scena mixtape (tagli della
  stessa operazione, voluti); video offline con start locale 10 s (timeline
  conferma start assoluto 45 s, fine 50 s). Non sono errori o tempi ambigui nel montaggio.
- Snapshot di nove pose, ispezionati contact sheet e primi 30 s in dettaglio;
  finale nuovamente acquisito a 57 e 59,95 s dopo la correzione. File proofs/stills/.
- Keyframes della camera: fromTo scale 1,6→1 da 1 a 2,5 s; prova selettore in
  proofs/camera-proof.png, ispezionata. Nessun zoom durante il primo gesto Play.
- Animation-map prodotto in .hyperframes/anim-map/animation-map.json e revisionato:
  33 tween mappati/60 enumerati, 27 micro-tween esclusi. Flag paced-fast coerenti
  con entrate 0,2 s; pause di lettura/staticità deliberate. Collision/invisible su
  selettori generici h1.headline e timeline figlie duplicate non costituiscono prova
  di un difetto: confrontati con snapshot ai tempi propri e check seek dedicato.
- Helper bootstrap automatico inizialmente indisponibile; producer 0.8.171 cita
  core 0.8.171 non pubblicato (ETARGET). Installati solo nel progetto helper
  producer/core **0.8.170** con versioni esatte; CLI e check finale **0.8.171**.
- Font Roboto, incluso corsivo del marchio, e JetBrains Mono incorporati dal compiler.
- Audio base WAV 59,5 s, 44,1 kHz stereo, picco **−8,7 dBFS**, media −25,6 dBFS.
  Volume montato 0,85, inizio a 0,8 s, fade finale dichiarato fino a 60 s;
  unico clic 80 ms a volume 0,6. Nessun clipping nel sorgente. Mix codificato e
  ascolto umano dell'output restano da verificare dopo render; non dichiarati superati.
- Timeline: durata 60 s, due tracce audio separate, video offline 45–50 e 50–54
  contigui con source offset 0/5 s. Icona non animata internamente, visibile fino
  al fotogramma finale verificato; nessun reset a nero.

## Allestimento e cleanup

- Docker Navidrome temporaneo walktape-video-demo, porta 4534, arrestato PRIMA
  delle acquisizioni offline. Server personale 4533 non modificato.
- AVD originale physical 1080×2400 / density 420; riprese con override 1080×1920 /
  320. Override size/density rimossi, scale animazioni riportate a 1, app fermata,
  temporanei wt-video-* noti rimossi dal dispositivo ed emulatore spento.
- Profilo dimostrativo e download di prova restano nel solo AVD per riproducibilità,
  container fermo/volume e credenziali effimere solo in local/ ignorato. Nessun telefono usato.
- Preview resta aperta per revisione. Stop esplicito quando finita la revisione:
  `npx.cmd -y hyperframes@0.8.171 preview --stop`.

## Gate restante

Conferma dell'utente: render ora o modifiche. Poi MP4 a 30 fps, verifica ffprobe,
ispezione frame decodificati e verifica mix effettivo. Nessuna pubblicazione o commit autorizzati.
