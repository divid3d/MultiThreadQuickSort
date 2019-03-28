# MultiThreadQuickSort

W zasadzie działanie całego programu opiera się na testowaniu:

* Jednowątkowej tablicowej implementacji quicksorta
* Wielowątkowej tablicowej implemetacji quicksrota
* Metody Arrays.sort() biblioteki standardowej Javy wykorzystującej Dual-Pivot Quicksort i sortującej jednowątko
* Metody Arrays.parallelSort() wykorzystującej Mergesorta i sortującej wielowątkowo 

Implementacja jedno i wielowątkowa QuickSorta zdolna jest do sortowania tablic typów generycznych rozszczerzających Comparable. Sortowanie może być testowane na liczbach całkowitych, zmiennoprzecinkowych albo Stringach. Dane testujące mogą być generowane jako posortowane rosnąco, malejąco lub w sposób pseudolosowy.
Testowanie przebiega od pewnej początkowej liczby elementów, aż do końcowej liczby elementów co pewien krok. Testowanie na danej liczbie elementów jest powtarzane n razy.
Średnie czasy sortowania poszczególnych algorytmów dla danej liczy danych wejściowych są przechowywane.
Wynik testu zapisywany jest do pliku CSV.


Jeżeli chodzi o implementacje wielowątkową quicksorta to:

* W klasie **MultiThreadQuickSort** znajduje się Thread pool executor o stałym rozmiarze zależnym od liczby rdzeni i wątków procesora
* Wewnątrz tej samej klasy znajduje się statyczna klasa wewnętrzna **QuickSortRunnable**, która implementuje interfejs **Runnable**
* Znajduje się też tam AtomicInteger, który pozwala na monitorowanie liczby uruchomionych wątków
* Tablica jest dzielona na podtablice (posortowane według elementu osiowego) i następnie wywoływany jest quicksort na podtablicach w kolejnych wątkach lub w tym samym w żależności od wartości synchronizowanej zmiennej **count** 
* Na zmiennej atomicznej wykonywane są tylko operacje atomiczne takie jak **getAndDecrement()**, **getAndAdd()** i jest ona jednocześnie źródłem synchronizacji

<p align="center">
<img src="https://github.com/divid3d/MultiThreadQuickSort/blob/master/sort_time.png?raw=true" width="75%" height="75%">
<img src="https://github.com/divid3d/MultiThreadQuickSort/blob/master/linear_regression.png?raw=true" width="75%" height="75%">
</p>
