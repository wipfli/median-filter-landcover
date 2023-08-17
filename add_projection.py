import subprocess

ksize = 1
print(f'adding projection...')
subprocess.run(f'gdal_translate recolored-filtered-{ksize}.tif recolored-filtered-{ksize}-projection-added.tif -a_srs "EPSG:4326" -a_ullr -180 90 180 -90 -co COMPRESS=LZW', shell=True)
