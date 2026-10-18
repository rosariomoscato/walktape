# Video WalkTape

`walktape-la-tua-musica/` contiene il progetto HyperFrames della presentazione
verticale di 60 secondi, con regia confermata dall'utente.

Fonte delle decisioni: `../regia/walktape-la-tua-musica/DECISIONI.md`.
Le istruzioni operative e i prerequisiti sono nel BRIEF.md del progetto video.

## Riaprire il progetto

Richiede Node.js22+ e un browser compatibile con HyperFrames. Dalla cartella
`videos/walktape-la-tua-musica/` eseguire `npm.cmd ci` su Windows.
I media del montaggio corrente sono inclusi nel repository; non servono account
Navidrome o credenziali per riprodurre il video già composto.

Per usare i binari FFmpeg locali senza installazioni globali, nella PowerShell
dedicata al progetto video:

```powershell
$env:PATH = "$(Join-Path (Get-Location) 'node_modules\ffmpeg-static');$(Join-Path (Get-Location) 'node_modules\ffprobe-static\bin\win32\x64');$env:PATH"
npx.cmd -y hyperframes@0.8.171 preview --background
```

Le versioni degli strumenti sono fissate in package.json/package-lock.json.
Cache Studio, node_modules, configurazioni private, riprese grezze di allestimento
e MP4 finali in renders/ sono locali e ignorati da Git. Per nuove esportazioni
seguire BRIEF.md/AGENTS.md e scegliere un nome di output nuovo.
