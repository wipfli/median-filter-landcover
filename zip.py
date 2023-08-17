import subprocess

ksize = 9
subprocess.run(f'rm zips/filtered-{ksize}', shell=True)

subprocess.run(f'zip zips/filtered-{ksize}.zip recolored-filtered-{ksize}-projection-added.gpkg', shell=True)
