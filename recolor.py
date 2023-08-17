import subprocess
import json

with open('color-mapping.json', 'r') as f:
    color_mapping = json.load(f)

expressions = []
for layer in color_mapping:
    for value in layer['values']:
        expressions += [f"(A=={value})*{layer['grayscale']}"]

calc = ' + '.join(expressions)

subprocess.run(f'gdal_calc.py -A Beck_KG_V1_present_0p0083.tif --calc="{calc}" --outfile="recolored.tif" --co COMPRESS=LZW', shell=True)
