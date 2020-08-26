# CORSO INGEGNERIA DEL SOFTWARE A.A. 2018/19

## LABORATORIO 3

* TEAMMATE 1: Notarangelo Elisa 920295 elinot15
* TEAMMATE 2: Politano Andrea 909153 andrea_politano

Ogni coppia di studenti procede ad effettuare il **fork** di questo repository.
L'utente che ha effettuato il fork modifica questo README inserendo le opportune **informazioni sui membri del team** seguendo lo schema sopra riportato.
Inoltre, concede i permessi di scrittura al proprio compagno di team e i **permessi di lettura** a entrambi docenti (`carlobellettini` e `mmonga`).

### Processo

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
* in fase di consegna al committente, esegue una *release* all'interno del *branch master* attraverso il comando `git flow release start` e successivamente `git flow release finish`,
* effettua un *push* (di tutti i *branch*) su Bitbucket con `git push origin --all --follow-tags`.

Al termine del laboratorio effettua un ultimo *push* e **verifica su
Bitbucket** che ci sia la completa traccia di *commit* effettuati. Si
suggerisce di eseguire i test non soltanto con la IDE, ma anche eseguendo il
comando `gradle build` da riga di comando.


### Specifica dei requisiti

Scopo dell'esercitazione è creare un programma Java che implementi una struttura dati di tipo [LinkedList](https://en.wikipedia.org/wiki/Linked_list) di numeri *interi* (non è consentito l'uso di strutture dati già esistenti, ad esempio [java.util.LinkedList](https://docs.oracle.com/javase/8/docs/api/java/util/LinkedList.html)).
Nello specifico, i requisiti funzionali sono elencati di seguito in linguaggio naturale.
Il gruppo procede ad implementare il *test di accettazione* di ogni funzionalità uno alla volta, e aggiunge altri casi di test se lo ritiene utile.
Si ricorda che ad ogni iterazione del TDD va introdotto esattamente un `@Test` JUnit.

Il software implementato dovrà essere **corretto** rispetto alla specifica dei requisiti, e **manutenibile**.
In questo senso il gruppo dovrà adottare uno stile di programmazione orientato agli oggetti usando le principali convenzioni della pragrammazione Java.
Inoltre, prestare attenzione ad evitare *codice duplicato* e *poco leggibile*.
La *leggibilità* e la *manutenibilità* del software sono attributi di qualità di cui il gruppo deve occuparsi esplicitamente durante la fase di *refactoring*.

**Elenco delle funzionalità:**

* **Costruttore senza parametri**: L'invocazione del costruttore senza parametri inizializza una lista di interi vuota.
```
Esempio, la rappresentazione testuale (toString) della lista vuota è: "[]".
```

* **Aggiunta in coda**: L'invocazione del metodo `void addLast(int value)` provoca l'inserimento del parametro `value` in coda alla lista.
```
Esempio (1), dopo aver aggiunto l'elemento "1" in coda alla lista vuota, il metodo toString deve restituire: "[1]".
Esempio (2), dopo aver aggiunto l'elemento "3" in coda alla precedente lista, il metodo toString deve restituire: "[1 3]".
```

* Dopo aver fatto chiuso questa feature, fare una prima consegna al cliente mediante i comandi di `gitflow` per creare una `release`

* **Costruttore con parametro String**: L'invocazione del costruttore con parametro `String` inizializza una lista con i valori specificati.
```
Esempio (1), la costruzione di una lista con parametro "" inizializza una lista vuota.
Esempio (2), se una lista viene inizializzata con parametro "1", il metodo toString restituisce: "[1]".
Esempio (3), se una lista viene inizializzata con parametro "1 2 3", il metodo toString restituisce: "[1 2 3]".
```

* **Robustezza del costruttore**: L'invocazione del costruttore con parametro `String` non formattato correttamente, solleva un'eccezione di tipo `IllegalArgumentException` e messaggio `"Not supported input format"`.
```
Esempio, la costruzione di una lista con parametro "1 2 aaa" solleva l'eccezione (vedi NOTE IMPLEMENTATIVE in fondo) 
```

* **Aggiunta in testa**: L'invocazione del metodo `void addFirst(int value)` provoca l'inserimento del parametro `value` in testa alla lista.
```
Esempio (1), dopo aver aggiunto l'elemento "1" in testa alla lista vuota, il metodo toString deve restituire: "[1]".
Esempio (2), dopo aver aggiunto l'elemento "3" in testa alla precedente lista, il metodo toString deve restituire: "[3 1]".
```

* **Rimozione in testa**: L'invocazione del metodo `boolean removeFirst()` provoca la rimozione dell'elemento in testa e restituisce `true` in caso la rimozione vada a buon fine.
```
Esempio (1), se viene invocato removeFirst sulla lista [1 2], il metodo restituisce true e il contenuto della lista diventa: [2].
Esempio (2), se viene invocato removeFirst sulla lista [2], il metodo restituisce true e il contenuto della lista diventa: [].
Esempio (3), se viene invocato removeFirst sulla lista vuota, il metodo restituisce false.
```

* **Rimozione in coda**: L'invocazione del metodo `boolean removeLast()` provoca la rimozione dell'elemento in coda e restituisce `true` in caso la rimozione vada a buon fine.
```
Esempio (1), se viene invocato removeLast sulla lista [7 10], il metodo restituisce true e il contenuto della lista diventa: [7].
Esempio (2), se viene invocato removeLast sulla lista [7], il metodo restituisce true e il contenuto della lista diventa: [].
Esempio (3), se viene invocato removeLast sulla lista vuota, il metodo restituisce false.
```

* **Rimozione di una occorrenza di un valore**: L'invocazione del metodo `boolean remove(int value)` provoca la rimozione della prima occorrenza dell'elemento `value` e restituisce `true` in caso la rimozione vada a buon fine.
```
Esempio (1), se viene invocato remove(2) sulla lista [1 2 3 4 3 5], il metodo restituisce true e il contenuto della lista diventa: [1 3 4 3 5].
Esempio (2), se viene invocato remove(3) sulla lista [1 3 4 3 5], il metodo restituisce true e il contenuto della lista diventa: [1 4 3 5].
Esempio (3), se viene invocato remove(6) sulla lista [1 4 3 5], il metodo restituisce false.
```

* **Rimozione di tutte le occorrenze di un valore**: L'invocazione del metodo `boolean removeAll(int value)` provoca la rimozione di tutti gli elementi `value` della lista e restituisce `true` in caso la rimozione vada a buon fine.
```
Esempio (1), se viene invocato removeAll(3) sulla lista [1 2 3 4 3 5], il metodo restituisce true e il contenuto della lista diventa: [1 2 4 5].
Esempio (2), se viene invocato removeAll(6) sulla lista [1 2 4 5], il metodo restituisce false.
```

* **Calcolo del valore medio**: L'invocazione del metodo `double mean()` restituisce il valore medio degli elementi contenuti nella lista.
```
Esempio (1), l'invocazione di mean() sulla lista [1 2], restituisce: 1.5.
Esempio (2), l'invocazione di mean() sulla lista [160 591 114 229 230 270 128 1657 624 1503], restituisce: 550.6.
```

* **Calcolo della deviazione standard**: L'invocazione del metodo `double stdDev()` restituisce la [deviazione standard corretta](https://it.wikipedia.org/wiki/Scarto_tipo#Deviazione_standard_corretta) degli elementi contenuti nella lista.
```
Esempio (1), l'invocazione di stdDev() sulla lista vuota, restituisce: 0.
Esempio (2), l'invocazione di stdDev() sulla lista [1], restituisce: 0.
Esempio (3), l'invocazione di stdDev() sulla lista [160 591 114 229 230 270 128 1657 624 1503], restituisce: 572.026.
```

* **Scansione degli elementi**: I metodi `int next()` e `int prev()` restituiscono l'ultimo valore visitato della lista e aggiornano l'ultimo valore visitato al successivo/precedente valore, rispettivamente.
```
Esempio, l'invocazione di next(), next(), next(), next(), prev(), prev(), prev() sulla lista [1 2 3], restituiscono rispettivamente i risultati: 1, 2, 3, 3, 2, 1, 1.
```

* **Inizializzazione da file**: E' possibile inizializzare una lista a partire da un file di testo contenente una singola riga composta da valori separati da spazi. A questo scopo deve esistere un costruttore che accetta come parametro un oggetto di tipo [java.io.Reader](https://docs.oracle.com/javase/8/docs/api/java/io/Reader.html).


### NOTE IMPLEMENTATIVE

* per controllare con AssertJ un test che una parte di codice sollevi una specifica eccezione, potete usare le seguenti istruzioni:
```
#!java
    assertThatThrownBy(() -> {
      /* codice che deve sollevare una eccezione */
    })
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("Not supported input format.");
```

* non bisogna mai confrontare per uguaglianza diretta numeri in virgola mobile. In AssertJ si può scrivere:

```
#!java
assertThat( /* espressione che restituisce double */ ).isCloseTo( /* valore che ci si aspetta */, within( /* margine di errore */ ));
```

* Per ottenere un Reader a partire da un file Risorsa contenuto nelle sottodirectory `resources` si può usare il seguente codice: 

```
#!java
Reader resourceReader = new InputStreamReader(getClass().getResourceAsStream("/input.txt"));
```
