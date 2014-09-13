-------------------
INTRUSION DETECTOR
-------------------
[KDD Cup 99](http://kdd.ics.uci.edu/databases/kddcup99/kddcup99.html)

########################################
#          Pr�-Requisitos              #
########################################

* Sistema operacional Linux
* Ambiente de execu��o da linguagem Java (Java Runtime Environment) dispon�vel em http://www.oracle.com/technetwork/java/javase/downloads/index.html
* Bibliotecas commons-lang3 (http://commons.apache.org/lang/) e commons-io (http://commons.apache.org/io/) presentes no classpath de execu��o.
* Copiar os arquivos abaixo para o diret�rio /kddcup99
** header.arff
** download_tratamento_massa_treino.sh
** download_tratamento_massa_teste.sh
** CsvToArff.java
** Equalize.java
** commons-io-2.3.jar
** commons-lang3-3.1.jar

######################################################
# Gera��o de Arquivos ARFF para Normaliza��o no WEKA #
######################################################
1) bash +x download_tratamento_massa_teste.sh
2) bash +x download_tratamento_massa_treino.sh
3) javac -cp commons-io-2.3.jar:commons-lang3-3.1.jar CsvToArff.java
4) javac -cp commons-io-2.3.jar Equalize.java
5) java -Xmx1024M -cp commons-io-2.3.jar:. Equalize
6) java -Xmx1024M -cp commons-io-2.3.jar:commons-lang3-3.1.jar:. CsvToArff

########################################
#       Normaliza��o dos Dados         #
########################################
* O passo anterior criar� seis arquivos no diret�rio /kddcup99, kddcup_teste_ordenado_registros_unicos.csv.arff e cinco arquivos de treino, kdd_treino_[0-4].csv.arff
* Executar a ferramente Weka Explorer e, em seguida, aplicar o filtro dispon�vel em Filters/Unsupervised/Attribute/Normalize, configurado da seguinte maneira:
** ignoreClass=false
** scale=1.0
** translation=0.0
* Para cada arquivo listado acima, deve-se aplicar o filtro descrito e, ao final do pr�-processamento, faz-se a grava��o do arquivo resultante no mesmo diret�rio.

########################################
#       Processamento dos Dados        #
########################################
* A etapa anterior faz com que todos os valores num�ricos dos registros presentes nos arquivos sejam ajustados para o intervalo [0,1].
* Em seguida, deve-se fazer o carregamento dos arquivos normalizados de treino kdd_treino_[0-4].arff
* Na aba "Classify", escolher o classificador Classifiers/Functions/MultilayerPerceptron, configurado da seguinte maneira:
** decay=false
** hiddenLayers=a
** learningRate=0.2
** momentum=0.2
** nominalToBinaryFilter=true
** normalizeAttributes=true
** normalizeNumericClass=true
** reset=true
** seed=0
** trainingTime=25
** validationSetSize=0
** validationThreshold=20
* Habilitar a fun��o de Cross-validation com 10 folds e selecionar o bot�o "Start" para iniciar o treinamento.
* Ap�s o treinamento da rede, carregar o arquivo de teste selecionando a op��o "Supplied test set" e, com o bot�o direito na �rea "Result list", utilizar a funcionalidade "Re-evaluate model on current test set"
* Os arquivos de resultado obtidos pelo grupo ap�s esta etapa est�o dispon�veis com o nome massa_[0-4]_resultado_mlp.txt


Contact: Danilo Mutti, Eduardo Cerejo, Jaqueline Brito

