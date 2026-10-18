# Provenienza dei materiali

## Revisione musicale VIDEO-T04

Sostituita la base96BPM dopo richiesta «più anni80 e molto più ritmata».
walktape-retro80-120bpm.wav è una composizione strumentale originale locale,
sintetizzata da tools/compose-retro80.cjs:120BPM,59,2s,44,1kHz stereo, senza
campioni esterni o modelli AI. Tema e accompagnamento non citano brani esistenti.
Provenienza e parametri in audio/retro80-provenance.json. Il clic originale80ms
rimane identico. Il mix di prova60s include lo stesso inizio0,8s e fade finale
dichiarati nella composizione; prove tecniche in QA.md e ascolto in boards/music.html.
La vecchia base96BPM e i brani di prova Android restano conservati come storico.

## Revisione illustrata VIDEO-T03

L'utente sceglie illustrazioni originali locali al posto degli screenshot parziali.
Tre SVG disegnati appositamente e rasterizzati con Chromium in PNG1080×1400;
nessuna generazione da modello AI, fotografia o copertina di terzi. Sorgenti in
illustrations/, script tools/rasterize-illustrations.cjs. Uso 8–20/20–35/35–45s.
Le acquisizioni sottostanti restano conservate come fonti; solo i video del player
sono ancora montati nella revisione corrente. Le immagini evocano benefici e non
sono presentate come screenshot o prove di operazioni nell'app.

## Acquisizioni

App Android WalkTape reale su AVD walktape_aosp, viewport 1080×1920 / density 320.
Nessuna UI ricostruita per il film. Le sequenze player sono registrazioni H.264;
album, coda, dialog e risultati sono schermate reali montate con tagli.

Navidrome deluan/navidrome image sha256:c4b5cb36a790b3eb63ca6a68bbe2fe149c2d7fa2e586f7a480e61db630e6664b,
istanza temporanea distinta sulla porta 4534, raccolta di prova di tre MP3 originali:
Blu Notte, Nastro Aperto, Fuori Rete; artista WalkTape Sessions, album La tua musica.
Una playlist dedicata «La mia mixtape» è stata creata tramite l'app. Nessuna modifica
al server personale sulla porta 4533 o alla sua raccolta.

Download: tre esiti Scaricato da 1,1 MB ciascuno, acquisiti nel gestore download.
Solo offline: toggle verificato attivo, poi server temporaneo arrestato PRIMA di
avviare la playlist scaricata. Stato In riproduzione e posizione in avanzamento
osservati sull'AVD, registrazione offline-player.mp4. È una dimostrazione su
emulatore, non un nuovo collaudo Motorola/Android Auto né una misura acustica.

Le registrazioni originali, prive di audio, sono conservate in captures/.
tools/prepare-media.cjs produce crop e letterbox per i pannelli approvati. Nessuna
correzione colore, sostituzione dei testi UI, bobina animata artificialmente o
indicatore di download finto. Attese sintetizzate tramite tagli su stati reali.

## Musica e clic

Scelta esplicita dell'utente: «Composizione locale (Recommended)» dopo segnalazione
del mancato login HeyGen. Base strumentale originale 96 BPM, synth/basso/batteria,
nessuna voce e nessun campione esterno. Script deterministico nella radice Android:
tools/Generate-VideoAssets.cjs; parametri e picco/RMS in audio/provenance.json.
Stesso autore procedurale per i brani della raccolta di prova e la cover raster.

Un clic originale di 80 ms sulla prima pressione Play, intorno a 0,8 s nella capture.
Base e clic sono audio editoriale separato; i video Android sono muti.

## Icona e font

walktape-icon.svg: conversione meccanica dei path del vettore Android originale
approvato, con fondo blu adattivo. Non è la cover della raccolta né una nuova icona.
Roboto (compreso 700 corsivo) e JetBrains Mono vengono congelati dal compiler
HyperFrames in @font-face deterministici; nessuna fetch di font a tempo di render.
Sorgenti immagini musicali e audio originali possono essere distribuiti con il
progetto WalkTape sotto la sua licenza MIT; nessun asset musicale di terzi incluso.
