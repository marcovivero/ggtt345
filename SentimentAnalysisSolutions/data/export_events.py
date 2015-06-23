from datetime import datetime as dt
import predictionio
import pytz
import string

exporter = predictionio.FileExporter("./data/training.json")
file = open("./data/train.tsv", "r").read().split("\n")[ : -1]


count = 0
for elem in file:
    elem = elem.split("\t")[2 : ]
    exporter.create_event(
        event = "training",
        entity_id = count,
        entity_type = "training",
        properties = {
            "text" : elem[0],
            "label" : int(elem[1])
        },
        event_time = dt.now(pytz.utc)
    )
    count += 1

new_exporter = predictionio.FileExporter("./data/special.json")

count = 0
for elem in list(string.punctuation):
    new_exporter.create_event(
        event = "special",
        entity_id = count,
        entity_type = "special",
        properties = {
            "char" : elem
        }
    )
    count += 1

