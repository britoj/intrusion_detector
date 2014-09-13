#!/bin/bash
wget http://kdd.ics.uci.edu/databases/kddcup99/corrected.gz
gzip -d corrected.gz
cat corrected | sort -u >> kddcup_teste_ordenado_registros_unicos.csv