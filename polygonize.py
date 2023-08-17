import subprocess

ksize = 1
print('removing old file...')
subprocess.run(f'rm recolored-filtered-{ksize}-projection-added.gpkg', shell=True)

print(f'polygonizing...')
subprocess.run(f'gdal_polygonize.py recolored-filtered-{ksize}-projection-added.tif -b 1 -f "GPKG" recolored-filtered-{ksize}-projection-added.gpkg', shell=True)
