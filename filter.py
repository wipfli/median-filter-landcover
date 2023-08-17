import cv2
# import json

# with open('process.json', 'r') as f:
#     data = json.load(f)

# value = int(data['value'])
# ksize = int(data['ksize'])

print(f'reading recolored.tif...')
image = cv2.imread(f'recolored.tif')

ksize = 1
print(f'filtering with ksize={ksize}...')
filtered = cv2.medianBlur(image, ksize=ksize)

print(f'writing recolored-filtered-{ksize}.tif...')
cv2.imwrite(f'recolored-filtered-{ksize}.tif', filtered)


# gdal_translate value-29-filtered-129.tif with-projection.tif -a_srs "EPSG:4326" -a_ullr -180 90 180 -90
# gdal_polygonize.py with-projection.tif -b 1 -f "GPKG" polygons.gpkg

# 1
# 3
# 7
# 15
# 31
# 63
# 127

# 1 = 1
# 2 = 3
# 4 = 5
# 8 = 9
# 16 = 17
# 32 = 33
# 64 = 65
# 128 = 129
