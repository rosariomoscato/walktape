# WalkTape — Aggiornamenti

Funzione W11-T04. Controlli e download dalle **GitHub Releases** di
`rosariomoscato/walktape`. Nessun aggiornamento forzato, nessuna installazione automatica:
Android chiede sempre conferma.

## Release correnti

**[v0.2.0](https://github.com/rosariomoscato/walktape/releases/tag/v0.2.0)**,
`versionCode4`, asset `walktape-universal-0.2.0.apk` e `SHA256SUMS.txt`.
Dalla v0.1.1 il percorso in-app è quello normale: nome versione e versionCode crescenti,
offerta di aggiornamento, download verificato (checksum pubblicato + firma) e conferma
dell'installer di sistema.

Le precedenti **[v0.1.1](https://github.com/rosariomoscato/walktape/releases/tag/v0.1.1)**
(code3) e **[v0.1.0](https://github.com/rosariomoscato/walktape/releases/tag/v0.1.0)**
(code2) restano disponibili. Anche la build di sviluppo B04 denominata0.1.1/code3
riceve l'offerta0.2.0; basta il controllo in-app oppure l'installazione manuale
dell'APK sopra quello esistente, senza disinstallare. Download/installer interattivi
sul telefono restano prove distinte dalla disponibilità della release pubblica.

## Percorso

Impostazioni → **Aggiornamenti app**:

1. **Controlla aggiornamenti**: interroga `api.github.com` con un client dedicato (senza
   credenziali né token). HTTP404 è gestito come "Nessuna release pubblicata"; con
   una release pubblicata, l'API latest deve invece restituire la release corrente.
2. Se c'è una versione più recente: **novità** del rilascio e **Scarica APK** (asset scelto
   solo se univoco: build universal preferite alle ABI-specifiche; ambiguità rifiutata).
3. Il trasporto è **solo HTTPS**: l'URL dell'asset e ogni redirect di download vengono
   rifiutati se non `https://`. Se la release pubblica `SHA256SUMS.txt`, l'APK scaricato
   viene confrontato con il digest pubblicato **prima** della verifica firma: checksum
   mancante o non corrispondente blocca l'installazione con motivo esplicito.
4. L'APK viene verificato **prima** di qualsiasi installazione: stesso package
   `com.walktape.app`, versionCode crescente e **firma identica** a quella installata. Un APK
   di altra app, un downgrade, un file corrotto o una firma incompatibile vengono rifiutati con
   motivo esplicito; per firme incompatibili non si propone mai la disinstallazione (perderebbe
   i dati).
5. **Installa aggiornamento**: se Android non consente ancora installazioni da WalkTape, viene
   aperta l'impostazione "origine sconosciuta" **solo per questa app**; poi il installer di
   sistema mostra la conferma.

## Controllo automatico

Interruttore **opt-in, inizialmente spento**: al massimo un controllo al giorno, solo un
controllo — nessun download o installazione automatica. L'esito dell'ultimo controllo è
riportato nella schermata. In **Solo offline** nessun controllo viene effettuato (nemmeno
quello giornaliero viene "consumato").

## Note

- Il download avviene nella cache privata dell'app con file temporaneo: un download interrotto
  non lascia APK parziali e può essere annullato.
- Firma e pipeline sono predisposte in W12: la chiave resta locale e fuori da Git.
  Il digest pubblico del certificato e le istruzioni manutentore sono in [RELEASING.md](RELEASING.md).
