---
version: alpha
name: "WalkTape — Oggetto notturno"
description: "Direzione A confermata: cassetta e player reali sul blu notte, crema leggibile e arancione come accento."
unit: "1080x1920, 9:16, 30 fps"
colors:
  bg: "#101A30"
  text: "#F4F1EA"
  chassis: "#2A4D9B"
  chassis-deep: "#1B3268"
  steel: "#C9CED6"
  accent: "#F06A21"
typography:
  headline: { fontFamily: "Roboto", weight: 900, px: 104, lineHeight: 1.04, tracking: "-0.035em", color: "text" }
  body: { fontFamily: "Roboto", weight: 400, px: 40, lineHeight: 1.5, color: "text" }
  label: { fontFamily: "JetBrains Mono", weight: 400, px: 36, lineHeight: 1.5, color: "text" }
  brand: { fontFamily: "Roboto", weight: 700, style: "italic", color: "text" }
spacing:
  pad-x: "90px"
  pad-y-top: "180px"
  reserve-bottom: "240px"
  gap: "32px"
components:
  persistent-chrome:
    enabled: false
    description: "Nessun logo editoriale fisso, contatore, cornice telefono o progress bar. Conservare il marchio che esiste nella UI reale."
  app-capture:
    description: "Acquisizioni Android reali, senza involucro smartphone; crop per dettagli e vista intera, senza ricostruire la UI."
  editorial-illustration:
    description: "VIDEO-T03 approvata: illustrazioni originali locali in 8–20,20–35,35–45s, palette WalkTape, senza fotografie o UI simulate."
  closing:
    backgroundColor: "#101A30"
    textColor: "#F4F1EA"
    description: "Icona originale, WalkTape, invito APK, URL completo e requisito Navidrome. Non ripetere il motto della scena 5."
---

# WalkTape — Oggetto notturno

## Overview

Il player emerge dal blu notte come un oggetto da scoprire. La cassetta e i
tasti meccanici danno il carattere rétro, le acquisizioni reali ne dimostrano
l'uso. Testo crema leggibile, dettagli argento e arancione come unico accento.

Direzione **A** confermata per delega e nel foglio finale. Riferimento:
`../../regia/walktape-la-tua-musica/direzioni.html`. La direzione appartiene al
video; non modifica il design dell'app Android.

Revisione VIDEO-T03 approvata dall'utente: sostituire screenshot parziali con tre
illustrazioni vettoriali originali (raccolta, mixtape, musica da portare). Conservare
player reale0–8/45–54 e firma54–60, palette, font, audio e durata60s. Nei segmenti
illustrati il racconto è evocativo, non una dimostrazione di azioni nell'interfaccia.

## Composition Rules

### Do

- Canvas 1080 × 1920; acquisizioni senza cornice smartphone, titoli esterni su fondo blu notte.
- Un protagonista alla volta. Mostrare il comando e il suo risultato senza coprirli con testo.
- Roboto 900 titoli 104 px, Roboto 400 corpo 40 px, JetBrains Mono 400 etichette 36 px: valori coincidenti con A. Marchio Roboto 700 corsivo, dimensionato secondo composizione, senza sostituire il nome con un font display nuovo.
- Usare i font incorporati dal renderer; verificare il corsivo autentico del marchio o incorporare un asset locale coerente. Nessuna dipendenza da Google Fonts durante il render.
- Due righe e otto parole al massimo per titolo, salvo scheda finale. Soste leggibili di almeno 2,5 s dopo l'entrata.
- Preservare colori, geometria e metadati delle acquisizioni; blu scocca → blu profondo e argento appartengono al prodotto reale.
- Margini operativi per 9:16: 90 px laterali, 180 px in alto, 240 px liberi in basso per overlay social; gap base 32 px. Sono valori esecutivi di impaginazione, non una nuova cornice permanente. Verificare nella revisione storyboard sul feed di destinazione.
- Scheda finale ferma da 54,2 a 60 s: icona/marchio, invito, URL e requisito. URL completo su due righe se necessario, senza scendere sotto il corpo/etichette concordati. Il motto resta solo da 50,2 a 53,5 s.
- Testi crema su blu notte (15,35:1); crema su blu scocca (7,07:1), crema su blu profondo (10,93:1). Contrasto minimo testo 4,5:1 anche su capture e ritagli da verificare.
- Arancione come dettaglio, mai testo piccolo su crema o blu; nessun nuovo colore editoriale. Gli altri colori reali delle cover/UI non sono una nuova palette del video.

### Don't

- Niente glitch, VHS, grana, aberrazione cromatica, particelle, rimbalzi elastici, riflessi decorativi aggiunti o whoosh.
- Nessun marchio editoriale permanente, bordo smartphone o barra di avanzamento. Non cancellare il marchio già presente sul player.
- Nessun mockup presentato come app reale. I disegni di direzioni.html sono soltanto studi di stile.
- Non animare comandi o esiti mai avvenuti; niente morph inventati tra schermate, download istantaneo finto o bobine in moto su acquisizione in pausa.
- Non allungare o comprimere il totale di 60 s per allinearlo alla musica.
- Nessuna tinta generale o filtro sulle capture per farle diventare notturne: la direzione viene da fondo, tagli e composizione.

## Motion

- **Griglia musicale aggiornata VIDEO-T04:** 120 BPM, 0,5 s per battito, cassa in quattro. La nuova musica non modifica finestre di lettura, animazioni o confini 0/8/20/35/50/54/60 s; inizio audio conservato a0,8s sul primo Play.
- **Frame rate:** 30 fps; 60 s = 1800 frame. Un battito musicale è15frame. Il montaggio visivo approvato resta fermo, senza riallineamenti forzati ai nuovi accenti.
- **Entrate testo:** 6 frame = 0,2 s; traslazione verticale 24 px, arresto netto senza overshoot o rimbalzo. Posizione finale statica per almeno 2,5 s. Il blocco finale entra una volta e rimane fermo.
- **Quiete:** durante tap e cambi UI, camera, sfondo e titoli fermi. Si muovono soltanto il gesto, la UI e le bobine appartenenti alla riproduzione reale.
- **Transizioni:** tagli netti raccordati su un elemento comune nella stessa posizione: cassetta → player, copertina → album, brano → coda, nome mixtape → playlist scaricata. Nessuna dissolvenza sottrae tempo alla scheda finale.
- **Camera:** dettaglio cassetta → player completo nei primi 2,5 s. Poi viste ferme alternate con tagli fra insieme e dettagli; nessuno zoom durante il tap. Non deformare le acquisizioni.
- **Timing narrativo:** scene di 8/12/15/15/4/6 s; le attese tecniche sono sintetizzate con tagli espliciti, non con animazioni di falso avanzamento.
- **Suono sincronizzato:** unico clic80ms sulla prima pressione Play, nuova base synth-pop originale120BPM da quel gesto; nessuna voce, sottotitolo di parlato, fruscio o effetto aggiuntivo. Risoluzione musicale entro la fine. Timbri: cassa elettronica, rullante/clap a coda corta, basso in ottave, accordi poly-synth detunati, sequencer e tema originale.
