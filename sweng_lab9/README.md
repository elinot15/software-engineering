# CORSO DI INGEGNERIA DEL SOFTWARE A.A. 2018/19

## LABORATORIO 9

* TEAMMATE 1: Notarangelo, Elisa, 920295, elinot15
* TEAMMATE 2: sgrelli, alessandro, 910432, alessandrosgrelli

Ogni coppia di studenti procede ad effettuare il **fork** di questo repository.
L'utente che ha effettuato il fork modifica questo README inserendo le opportune **informazioni sui membri del team** Segundo lo schema sopra riportato.
Inoltre, concede i permessi di scrittura al proprio compagno di team e i **permessi di lettura** a **entrambi** i docenti (`carlobellettini`, `mmonga`).

## Processo

Una volta effettuato il **clone** del repository, il gruppo esegue il comando `git flow init` all'interno della directory clonata.
Dopodiché, il gruppo implementa secondo la *metodologia TDD* 
le specifiche riportate di seguito; in maggior dettaglio, ripete i passi seguenti fino ad aver implementato tutte le funzionalità richieste:

* crea un nuovo *branch* per la funzionalità corrente attraverso l'esecuzione del comando `git flow feature start`,
* implementa un test per le funzionalità richieste **procedendo nell'ordine** in cui sono specificate,
* verifica che **il codice compili correttamente**, ma l'**esecuzione del test fallisca**; solo a questo punto effettua un *commit* (usando `git add` e `git commit`) iniziando il messaggio di commit con la stringa `ROSSO:`,
* aggiunge la minima implementazione necessaria a realizzare la funzionalità, in modo che **il test esegua con successo**; solo a questo punto
  effettua un *commit* (usando `git add` e `git commit`) iniziando il messaggio di commit con la stringa `VERDE:`,
* procede, se necessario, al **refactoring** del codice, accertandosi che le modifiche non comportino il fallimento di alcun test; solo in questo caso fa seguire ad ogni
  passo un *commit* (usando `git add` e `git commit`) iniziando il messaggio di commit con la stringa `REFACTORING:`,
* esegue il *merge* del *branch* per la funzionalità sviluppata all'interno del *branch develop* attraverso il comando `git flow feature finish`,
* **solo in fase di rilascio**, esegue una *release* all'interno del *branch master* attraverso il comando `git flow release start` e successivamente `git flow release finish`,
* effettua un *push* (di tutti i *branch*) su Bitbucket con `git push origin --all --follow-tags`.

Al termine del laboratorio effettua una **ultima release**, un ultimo *push*, e **verifica su Bitbucket** che ci sia la completa traccia di *commit* effettuati.
Si suggerisce di eseguire i test non soltanto con la IDE, ma anche eseguendo `gradle test` da riga di comando.


## Specifica dei requisiti

Nell'ipotetico social network Facebuk ogni utente possiede una lista di amici (da notare la relazione di amicizia è bi-direzionale: se A è amico di B, allora B è amico di A).
Un'informazione che viene richiesta molto spesso risulta essere la lista degli amici in comune, mostrata ogni volta che visitiamo la pagina di un utente.
Siccome questa lista non cambia frequentemente è possibile calcolarla periodicamente (es., una volta al giorno).

Lo scopo dell'esercizio è progettare e realizzare lo strumento software che calcola la lista degli amici in comune per ogni coppia di utenti di Facebuk.
Assumiamo che l'input consista in un insieme di righe del tipo:

    <utente> <lista di amici>

e che venga fornito tramite uno stream di dati (oggetto che risponde all'interfaccia Reader).

Un possibile input potrebbe essere il seguente:

    Mario Roberta Luca Filippo
    Roberta Mario Luca Filippo Anna
    Luca Mario Roberta Filippo Anna
    Filippo Mario Roberta Luca Anna
    Anna Roberta Luca Filippo

Un possibile output invece (calcolato a partire dal precedente input) è il seguente:

    (Filippo Anna) [Roberta Luca]
    (Luca Anna) [Roberta Filippo]
    (Luca Filippo) [Mario Roberta Anna]
    (Mario Filippo) [Roberta Luca]
    (Mario Luca) [Roberta Filippo]
    (Mario Roberta) [Luca Filippo]
    (Roberta Anna) [Luca Filippo]
    (Roberta Filippo) [Mario Luca Anna]
    (Roberta Luca) [Mario Filippo Anna]


Il quale è ottenuto affiancando ad ogni coppia di utenti (A B) la lista degli amici in comune [amici comuni] e ordinando il risultato alfabeticamente in base alla concatenazione dei due nomi utente (A B).

Lo svolgimento dell'esercizio deve tener conto dei seguenti requisiti addizionali:

- La lettura dell'input deve avvenire usando un Reader, non necessariamente System.in, questo per rendere più agevole sia il test sia l'eventuale estensione a casi in cui l'input non provenga dalla console

- In una versione preliminare è possibile assumere che gli utenti vengano identificati da un unica stringa (username), ma è bene progettare la lettura così che possa essere estesa (nelle eventuali versioni successive) in modo da accettare dati in input dove ogni utente è identificato da due stringhe (nome cognome).

- In una versione preliminare è possibile assumere che l'ordinamento venga eseguito in modo unico e prefissato, ma è bene fare in modo che possa essere facilmente esteso cambiando l'ordinamento, ad esempio in base al numero di amici in comune in modo crescente.

- Il formato dell'output non deve essere unico e prefissato: in prima istanza può avere il formato descritto nell'esempio di esecuzione, ma un ulteriore formato da prevedere potrebbe essere il seguente:

      (Mario Roberta) 2
      (Mario Luca) 2
      (Mario Filippo) 2
      (Roberta Luca) 3
      (Roberta Filippo) 2
      (Roberta Anna) 2
      (Luca Filippo) 3
      (Luca Anna) 2
      (Filippo Anna) 2

in cui ogni ad coppia viene affiancato il numero di amici in comune.


### Suggerimenti per la progettazione

Fermo restando che ciascun gruppo può scegliere i design pattern che ritiene più opportuni nel realizzare l'esercizio, di seguito si accenna ad alcuni tra essi che potrebbero risultare particolarmente indicati:

- La lettura dell'input potrebbe essere fatta tramite diverse implementazioni di un Iterator. Le varie implementazioni potrebbero essere istanziate grazie a delle Factory, eventualmente raccolte sotto il cappello di una Abstract Factory.

- L'ordinamento e l'output potrebbero essere prodotti, a seconda del formato desiderato, da una serie di implementazioni di differenti Strategy.

- La costruzione dello strumento software finale potrebbe essere gestita attraverso un Builder.

### Rilasci del software
Il primo rilascio del software `v1.0` è previsto al termine dello sviluppo delle funzionalità di base, ovvero una volta che il primo test di accettazione esegue con successo. La versione finale del software che comprende anche lo svolgimento dei requisiti informali deve dare luogo alla release finale `v2.0`.

Per ogni funzionalità aggiuntiva sviluppata, qualora il gruppo lo ritenga opportuno, è possibile effettuare ulteriori release, ad esempio a fronte di modifiche sostanziali dalla precedente versione, dovute a refactoring e/o introduzione di design pattern.

Al termine dell'esercitazione, se la versione finale non è stata raggiunta, eseguire un'ultima release `time-up` lasciando i rami feature/ incompleti aperti, dopodiché un push (di tutti i branch).

### **Attenzione**: 
prima di andarvene controllate su bitbucket che:
1. Siano visibili nella pagina iniziale del vostro progetto i vostri nomi e identificatori (il README presentato è quello del ramo master... controllate che sia corretto e correttamente pushato)
2. Di aver dato i permessi di lettura ai professori
3. Di aver scritto correttamente i vostri dati (in particolare la vostra matricola)

**Errori di questo genere che non permettono la vostra identificazione corretta, porteranno alla esclusione dalla correzione.**
