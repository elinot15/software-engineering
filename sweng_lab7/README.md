# CORSO DI INGEGNERIA DEL SOFTWARE A.A. 2018/19

## LABORATORIO 7

* TEAMMATE 1: Politano, Andrea, 909153, andrea_politano
* TEAMMATE 2: Notarangelo, Elisa, 920295, elinot15

Ogni coppia di studenti procede ad effettuare il **fork** di questo repository.
L'utente che ha effettuato il fork modifica questo README inserendo le opportune **informazioni sui membri del team** Segundo lo schema sopra riportato.
Inoltre, concede i permessi di scrittura al proprio compagno di team e i **permessi di lettura** a **entrambi** i docenti (`carlobellettini`, `matteocamilli`).

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

Obiettivo dell'esercizio è *progettare* e *realizzare* un insieme di classi
che consentano di:

* **contare** il numero di *versi* emessi per *specie* di felino (ossia per ogni
  nome di classe che implementa l'interfaccia [Feline](/src/main/java/it/unimi/di/sweng/lecture/Feline.java)),
* **emettere su _standard error_** un messaggio di *log* contenente il nome
  della *specie*  per ogni *verso* emesso.

Tale obiettivo deve essere raggiunto usando il *design pattern* denominato
**_observer_**; più in dettaglio, è richiesta la realizzazione, nel *package*
`it.unimi.di.sweng.lab07`, delle seguenti classi:

1. Il *decorator* `ObservableFeline` che implementi l'interfaccia [Feline](/src/main/java/it/unimi/di/sweng/lecture/Feline.java)
   ed estenda la classe [Observable](https://docs.oracle.com/javase/8/docs/api/java/util/Observable.html) e decori il [Feline](/src/main/java/it/unimi/di/sweng/lecture/Feline.java)
   passato al suo costruttore in modo che l'invocazione del metodo `roar` possa essere osservata.

1. L'**_observer_** `FelineLoggerObserver`  in modalità **_PULL_** che implementi l'interfaccia [Observer](https://docs.oracle.com/javase/8/docs/api/java/util/Observer.html)
   ed emetta sullo _standard error_, per ogni invocazione di `roar`, il nome della
   classe sulla cui istanza osservata è stato invocato; --- **release** `v1.0-log`.

1. L'**_observer_** `FelineCounterObserver` in modalità **_PUSH_** che implementi l'interfaccia [Observer](https://docs.oracle.com/javase/8/docs/api/java/util/Observer.html)
   ed abbia un metodo con *signature* `public int getCount(final String name)`
   che, dato un nome di classe come argomento, restituisca il numero di volte
   per cui `roar` è stato invocato su istanze osservate di tale classe e un metodo con *signature*
   `public void resetCount()` che azzeri tutti i conteggi; --- **release** `v2.0-count`.

1. La *factory* `ObservedFelineFactory` che implementi la classe astratta [AbstractFelineFactory](/src/main/java/it/unimi/di/sweng/lecture/AbstractFelineFactory.java)
   e il cui costruttore accetti una lista di [Observer](https://docs.oracle.com/javase/8/docs/api/java/util/Observer.html) `List<Observer>`
   di cui sopra e costruisca le istanze di vari tipi di *felini* opportunamente
   avvolte dal *decorator* `ObservableFeline` e poste sotto osservazione dagli *observer* passati al
   costruttore; --- **release** `v3.0-factory`.


### Suggerimenti

Nell'implementazioni delle classi sopra specificate può essere utile tenere
conto dei seguenti suggerimenti:

1. la classe `ObservableFeline` è bene che decori il metodo `roar` usando almeno
   i metodi [setChanged](https://docs.oracle.com/javase/8/docs/api/java/util/Observable.html#setChanged--)
   e [notifyObservers](https://docs.oracle.com/javase/8/docs/api/java/util/Observable.html#notifyObservers-java.lang.Object-)
   necessari a rendere le invocazioni osservabili dai due *observer* da implementare;

1. la classe di cui un oggetto è istanza può essere ottenuta invocando
   [getClass](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html#getClass--),
   data una classe è possibile ottenere il suo nome invocando
   [getSimpleName](https://docs.oracle.com/javase/8/docs/api/java/lang/Class.html#getSimpleName--);
   detto altrimenti, dato un oggetto definito come `AClass anObject = new AClass()`, l'espressione
   `anObject.getClass().getSimpleName()` ha valore `AClass`;

1. la classe `FelineLoggerObserver` emetterà i messaggi invocando opportuni metodi di [System.err](https://docs.oracle.com/javase/8/docs/api/java/lang/System.html#err),
   per testare il suo comportamento si può usare una
   [SystemErrRule](https://stefanbirkner.github.io/system-rules/apidocs/org/junit/contrib/java/lang/system/SystemErrRule.html)
   in analogia a quanto fatto in [FelineTaleTest](/src/test/java/it/unimi/di/sweng/lecture/FelineTaleTest.java) per il test dei
   messaggi emessi su _standard output_.

1. la classe `FelineCounterObserver` può usare ad esempio una implementazione di [Map](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html)
   (di tipo specifico `Map<String,Integer>`) per tener traccia del numero di invocazioni per classe;


## Quality Assurance (QA) Test

Si osservi che le classi implementate dovranno **rispettare strettamente le specifiche** sopra elencate, in particolare riguardo a: il *package* in cui devono essere collocate, il nome che devono avere, i parametri dei costruttori e la *signature* dei metodi addizionali.

Al fine di verificare tale richiesta, nonché la corretta implementazione della specifica, sono stati realizzati dei *test di accettazione*.
In maggior dettaglio, durante l'esercitazione, verso le ore `17:00` verrà indicato l'indirizzo di un nuovo repository contenente un *branch* etichettato come `qa_test` con due file di test addizionali: `QATestFelineLogger.java` e `QATestFelineCounter.java`.

Tale repo dovrà essere *aggiunto* con il comando `git remote add prof <indirizzoDato>` e portato in locale con il comando `git fetch prof`

Per importare le suddette classi di test all'interno del progetto, il gruppo dopo essersi posizionato sul *branch* `develop` con il comando `git checkout develop` dovrà dare il comando  `git cherry-pick prof/qa_test`.
 
Se i test QA non eseguono correttamente, il gruppo apre un nuovo *branch* `feature` per apportare le opportune modifiche al codice errato.
Dopodiché, chiude il ramo ed effettua un'ultima **release** etichettata come: `v4.0-final`.
