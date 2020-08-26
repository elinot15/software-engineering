# CORSO INGEGNERIA DEL SOFTWARE A.A. 2018/19

## LABORATORIO 4

* TEAMMATE 1: Lombarda, Alessia, 908879, alessia_lombarda
* TEAMMATE 2: Notarangelo, Elisa, 920295, elinot15

Ogni coppia di studenti procede ad effettuare il **fork** di questo repository.
L'utente che ha effettuato il fork modifica questo README inserendo le opportune **informazioni sui membri del team** seguendo lo schema sopra riportato.
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
* esegue il *merge* del *branch* per la funzionalità sviluppata con il *branch develop* attraverso il comando `git flow feature finish`,
* **solo in fase di rilascio**, esegue una *release*  attraverso il comando `git flow release start` e successivamente `git flow release finish` (effettua tra le altre cose il *merge* con il *branch master*),
* effettua un *push* (di tutti i *branch*) su Bitbucket con `git push origin --all --follow-tags`.

Al termine del laboratorio effettua un'**ultima release**, un ultimo *push*, e **verifica su Bitbucket** che ci sia la  traccia completa dei *commit* effettuati.
Si suggerisce di eseguire i test non soltanto dall'nterno della IDE, ma anche eseguendo `gradle build` da riga di comando.

## Refactoring

Riguardo al *refactoring*,
il gruppo deve prestare attenzione ai seguenti [code smell](https://it.wikipedia.org/wiki/Code_smell):

* codice duplicato, o pressoché uguale, in diverse sezioni,
* troppi livelli di indentazione (es., > 2),
* metodo troppo lungo (es., > 10 linee di codice),
* lunghe sequenze di *if*-*else* o *switch case*,
* nomi di classi/metodi/campi/variabili non significativi,
* troppi attributi per classe (es., > 2),
* uso di metodi *setter*/*getter* per modificare/accedere campi privati.

In presenza di *code smell*, il gruppo effettua alcuni passi di *refactoring*,
per ottenere codice più *leggibile* e *manutenibile*. 

Di seguito si accenna ad alcune possibili azioni di refactoring.
Accanto al tipo di refactoring è stata elencata una delle [Refactor Actions](https://www.baeldung.com/intellij-refactoring) di Idea (se presente) che la coppia può usare.

* [Rename Method](http://refactoring.com/catalog/renameMethod.html) (oppure Field, o Variable): *Rename*,
* [Replace Magic Number with Symbolic Constant](http://refactoring.com/catalog/replaceMagicNumberWithSymbolicConstant.html): *Extract Constant*,
* [Extract Variable](http://refactoring.com/catalog/extractVariable.html): *Extract  Variable*,  *Extract Field*,
* [Extract Method](http://refactoring.com/catalog/extractMethod.html): *Extract Method*,
* [Extract Class](http://refactoring.com/catalog/extractClass.html): *Extract Class*,
* [Replace Array with Object](http://refactoring.com/catalog/replaceArrayWithObject.html).

Una lista di possibili passi di refactoring è accessibile dal seguente
[Refactoring Catalog](https://refactoring.com/catalog/). 


## Specifica dei requisiti

Le funzionalità di seguito elencate vanno implementate **nell'ordine in cui
sono presentate**. Si suggerisce  di prendere visione di una funzionalità per
volta (procedendo subito al ciclo di implementazione della medesima) in modo
da non farsi influenzare dalle specifiche successive circa le scelte di
progetto.

Obiettivo dell'esercizio è creare una semplice "calcolatrice" che implementi
l'interfaccia `Calculator` che dichiara il metodo:
```
int add(String expression)
```

Il comportamento che tale metodo deve esibire è specificato dai seguenti punti:

* prendere in input 0, 1 o 2 interi separati da virgola, e restituire
   la loro somma (per convenzione, 0 numeri, ossia la stringa vuota, ha somma 0)
```
add("") restituisce 0
add("1") restituisce 1
add("1,2") restituisce 3
```

* prendere in input un *numero arbitrario* di interi, separati da virgola, e restituirne la somma;

* gestire il caso in cui i numeri siano separati da *a-capo*, oltre che da virgola,
```
add("1\n2,3") restituisce 6
```

* consentire anche la definizione di un *diverso separatore* come prima linea dell'input, secondo la seguente sintassi:
```
//char\nexpression
```
ad esempio:
```
add("//;\n1;2") restituisce 3
```
si osservi che la presenza della definizione del separatore è opzionale, ossia restano validi i separatori virgola e a-capo.

Le funzionalità fino ad ora sviluppate rappresentano la **prima release** del software che deve essere etichettata come `versione 1.0`.

* sollevare una eccezione con messaggio "Numeri negativi non ammessi: " seguito da tutti i *numeri negativi* eventualmente
  presenti nella lista, ad esempio
```
add("-1,3,-2") solleva l'eccezione "Numeri negativi non ammessi: -1, -2"
```

* ignorare *numeri superiori a 1000*, ad esempio:
```
add("1001,2") restituisce 2
```

* consentire anche la definizione di un *separatore multi-carattere* come prima linea dell'input, secondo la seguente sintassi:
```
//[delimiter]\n
```
ad esempio
```
add(“//[***]\n1***2***3”) restituisce 6
```

* consentire anche la definizione di due, o più, *separatori multi-carattere* secondo la sintassi:
```
//[delim1][delim2]\n
```
ad esempio
```
add(“//[*][%]\n1*2%3”) restituisce 6
```

Le funzionalità fino ad ora sviluppate rappresentano la **seconda release** (versione finale) del software che deve essere etichettata come `versione 2.0`.
