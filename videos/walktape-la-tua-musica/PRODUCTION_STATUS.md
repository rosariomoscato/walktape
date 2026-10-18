# Produzione — VIDEO-T02

**Consegna corrente VIDEO-T05 completata:** renders/walktape-60s-retro80.mp4,
60s/1080×1920/30fps, H264+AAC stereo,4.155.158byte. Approvazione render ricevuta,
export delivery locale e verifiche file/audio/frame completati. Il file è già
sul computer, non è necessario scaricarlo da un servizio. Dettagli in QA.md.
Le sezioni successive conservano lo storico delle revisioni precedenti.

Revisione corrente VIDEO-T04: immagini approvate, musica sostituita su richiesta
con composizione originale synth-pop anni80 a120BPM, più ritmata. Mix PCM60s
verificato senza clipping e check composizione passato; ascolto in boards/music.html.
Studio resta aggiornato. In attesa di feedback musicale o conferma renderMP4.

Data: 2026-10-18. Fase: piano e bozzetti approvati; sei scene animate con acquisizioni
reali e audio originale locale. Check finale passato. In attesa di conferma render.

Revisione corrente VIDEO-T03: utente sceglie A, illustrazioni originali locali.
Sostituiti gli screenshot a8–20/20–35/35–45s con tre immagini originali, mantenuti
player e firma finale. Check revisione0errori/1warning,14/14contrasti,motion300campioni.
Confronto immagini boards/illustrations.html, snapshot proofs/illustrated.
La nuova versione è da rivedere prima del render; dettaglio aggiornato in QA.md.

Anteprima: http://127.0.0.1:3002/#project/walktape-la-tua-musica (HTTP 200 verificato).
Verifiche dettagliate, limiti e cleanup in QA.md; provenienza in assets/PROVENANCE.md.

## Verifiche iniziali

- `usage --json`: status unknown, reason unsupported_harness. Quota e reset sconosciuti.
- `hyperframes@latest upgrade --project . --check`: pin 0.8.171 già corrente; nessun aggiornamento package.json.
- `skills check --dir .agents/skills --json` dalla radice Android: 11 current, 0 outdated/coreMissing/removed, scope project.
- `skills update general-video` con HYPERFRAMES_SKIP_SKILLS=1: gestione esterna rispettata, nessuna installazione globale.
- History begin: ee72164c, autore opencode; nessun cambiamento esterno registrato al controllo iniziale.
- ADB disponibile in `%LOCALAPPDATA%/Android/Sdk/platform-tools/adb.exe`, fuori dal PATH; devices -l non mostra dispositivi collegati.
- Emulator disponibile in `%LOCALAPPDATA%/Android/Sdk/emulator/emulator.exe`; AVD walktape_aosp e walktape_test elencati, non avviati per il piano.
- Get-Command adb/emulator/ffmpeg non trova questi comandi nel PATH; disponibilità ffmpeg altrove non ancora verificata.
- Ricerca mirata materiali local/: soltanto ICON_PROPOSALS_W12.png; non trattarlo come capture del player o icona finale. Nessuna capture pronta individuata in quella ricerca.
- Nessun accesso a provider autenticati, acquisto o generazione media eseguito.

## Materiali della produzione

| Materiale | Stato | Uso previsto |
| --- | --- | --- |
| Icona Android originale | Convertita meccanicamente in SVG | Frame 6 |
| Capture player pausa/Play | Registrazioni reali pronte | Frame 1/5 |
| Capture libreria/album | Schermate reali pronte | Frame 2 |
| Capture coda/nome/salvataggio | Schermate reali, playlist di prova creata | Frame 3 |
| Capture download/Solo offline | Tre file scaricati e riprodotti a server fermo | Frame 4 |
| Musica 96 BPM | Originale locale, scelta utente, provenienza registrata | Master, senza voce |
| Clic <=100 ms | Originale locale 80 ms | Unico effetto su Play |
| Roboto 900/400 e JetBrains Mono 400 | Incorporati dal compiler | Titoli/testi/etichette |
| Roboto 700 corsivo | Incorporato e verificato visivamente | Marchio editoriale |

## Checkpoint

1. Approvazione piano STORYBOARD.md: ricevuta.
2. Bozzetti statici e board: approvati con «approvo i bozzetti».
3. Capture e animazione sui layout confermati: completate. HeyGen non autenticato, utente sceglie composizione locale originale.
4. Verifiche e anteprima: completate, check 0 errori/2 warning revisionati. Approvazione render in attesa (attuale), poi MP4 e verifica output.

Sei scene animate, durate contigue 0–60 s, layout_approved true. Board dei bozzetti
conservata in boards/, evidenze della composizione reale in proofs/. Nessun MP4 finale.

## Verifica del piano

Controllo Node con parser YAML: PASS sei frame outline, inizio/durata contigui
0–60 s, 1800 frame a 30 fps, poster locali all'interno delle scene, src dichiarati
in compositions/frames e blueprint citati effettivamente presenti. Il controllo
non attesta layout o movimento, che non sono ancora costruiti. Nessun lint/check
runtime o snapshot del film eseguito nella fase testuale.

## Verifica bozzetti

- Snapshot dei sei poster a 5,5/10/24/45/52/57 s riuscito tramite CLI 0.8.171.
- Prime immagini ispezionate a risoluzione piena: scoperto fallback monospace;
  aggiunta dichiarazione font-family esplicita per JetBrains Mono, rifatte tutte
  le sei immagini. Compiler conferma due famiglie incorporate, 5 font caricati.
- Snapshot finali 1080×1920 e contact sheet in boards/stills; ispezionate immagini
  e scheda finale. Titoli e requisito leggibili, margini mantenuti.
- Browser snapshot: GPU hardware AMD/ANGLE Direct3D11; non dipende dal Chrome
  assente nel percorso del Playwright MCP della sessione precedente.
- Bozzetti statici volutamente senza musica, movimento e screenshot Android:
  pannelli e icona sono segnaposto dichiarati nella board. Non sono prove dell'app.
- Come richiesto da review-loop nel solo sketch pass, usato snapshot senza
  lint/check/render. I controlli completi del film avverranno in costruzione.
- Usage prima della fase ancora unknown/unsupported_harness.
