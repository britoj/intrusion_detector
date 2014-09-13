-------------------
INTRUSION DETECTOR
-------------------
[KDD Cup 99](http://kdd.ics.uci.edu/databases/kddcup99/kddcup99.html)

########################################
#          Pré-Requisitos              #
########################################

* Sistema operacional Linux
* Ambiente de execução da linguagem Java (Java Runtime Environment) disponível em http://www.oracle.com/technetwork/java/javase/downloads/index.html
* Bibliotecas commons-lang3 (http://commons.apache.org/lang/) e commons-io (http://commons.apache.org/io/) presentes no classpath de execução.
* Copiar os arquivos abaixo para o diretório /kddcup99
** header.arff
** download_tratamento_massa_treino.sh
** download_tratamento_massa_teste.sh
** CsvToArff.java
** Equalize.java
** commons-io-2.3.jar
** commons-lang3-3.1.jar

######################################################
# Geração de Arquivos ARFF para Normalização no WEKA #
######################################################
1) bash +x download_tratamento_massa_teste.sh
2) bash +x download_tratamento_massa_treino.sh
3) javac -cp commons-io-2.3.jar:commons-lang3-3.1.jar CsvToArff.java
4) javac -cp commons-io-2.3.jar Equalize.java
5) java -Xmx1024M -cp commons-io-2.3.jar:. Equalize
6) java -Xmx1024M -cp commons-io-2.3.jar:commons-lang3-3.1.jar:. CsvToArff

########################################
#       Normalização dos Dados         #
########################################
* O passo anterior criará seis arquivos no diretório /kddcup99, kddcup_teste_ordenado_registros_unicos.csv.arff e cinco arquivos de treino, kdd_treino_[0-4].csv.arff
* Executar a ferramente Weka Explorer e, em seguida, aplicar o filtro disponível em Filters/Unsupervised/Attribute/Normalize, configurado da seguinte maneira:
** ignoreClass=false
** scale=1.0
** translation=0.0
* Para cada arquivo listado acima, deve-se aplicar o filtro descrito e, ao final do pré-processamento, faz-se a gravação do arquivo resultante no mesmo diretório.

########################################
#       Processamento dos Dados        #
########################################
* A etapa anterior faz com que todos os valores numéricos dos registros presentes nos arquivos sejam ajustados para o intervalo [0,1].
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
* Habilitar a função de Cross-validation com 10 folds e selecionar o botão "Start" para iniciar o treinamento.
* Após o treinamento da rede, carregar o arquivo de teste selecionando a opção "Supplied test set" e, com o botão direito na área "Result list", utilizar a funcionalidade "Re-evaluate model on current test set"
* Os arquivos de resultado obtidos pelo grupo após esta etapa estão disponíveis com o nome massa_[0-4]_resultado_mlp.txt


Contact: Danilo Mutti, Eduardo Cerejo, Jaqueline Brito

