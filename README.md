## Садовский А.Д. @aleks_wb_spb описание задачи:

## Задача 2. Частота операций

## Описание

В процессе создания программы для робота-доставщика вы решили сделать так, чтобы при каждом обновлении мапа `sizeToFreq` на экран выводился бы текущий лидер среди частот. Для этого придётся просматривать весь `Map` в поисках лидера, что достаточно затратно.

Вместо того, чтобы делать это в том же потоке, что и считал размер, заведите отдельный поток, который будет заниматься только подсчётом максимума в мапе и выводом этой информации на экран.

Делать это он будет в цикле, а чтобы подсчёт и вывод происходили только когда нужно, он будет ждать «сигнала» через `wait-notify` от считающих потоков.

Условием цикла поставьте проверку на то, что поток не прервали. В основном потоке после `for` с `join` прервите этот выводящий максимумы поток.

## Реализация

Эта задача выполняется на основе первой задачи этого домашнего задания. Отведите для неё новую ветку `freqlog`.

1. Создайте отдельный поток для вывода на экран лидера в мапу `sizeToFreq`.
2. В этом потоке должен быть реализован бесконечный цикл с проверкой на прерванность `while (!Thread.interrupted()) { ... }`.
3. В цикле перед каждым поиском и выводом на экран максимума в мапе `sizeToFreq` поток должен ждать сигнала от заполняющих мапу потоков.
4. В заполняющих мапу потоках добавьте отправку сигнала печатающему максимумы потоку.
5. После завершения всех считающих потоков прервите печатающий поток через `thread.interrupt()`.

На проверку отправьте ссылку на ветку `freqlog` в вашем репозиториии с решением.