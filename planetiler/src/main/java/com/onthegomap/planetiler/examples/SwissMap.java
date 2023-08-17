package com.onthegomap.planetiler.examples;

import com.onthegomap.planetiler.FeatureCollector;
import com.onthegomap.planetiler.FeatureMerge;
import com.onthegomap.planetiler.Planetiler;
import com.onthegomap.planetiler.Profile;
import com.onthegomap.planetiler.VectorTile;
import com.onthegomap.planetiler.geo.GeometryException;
import com.onthegomap.planetiler.config.Arguments;
import com.onthegomap.planetiler.reader.SourceFeature;
import com.onthegomap.planetiler.reader.osm.OsmElement;
import com.onthegomap.planetiler.reader.osm.OsmRelationInfo;
import com.onthegomap.planetiler.util.ZoomFunction;

import java.nio.file.Path;
import java.util.List;
import java.io.File;

public class SwissMap implements Profile {

  @Override
  public void processFeature(SourceFeature sourceFeature, FeatureCollector features) {

    String sourceName = sourceFeature.getSource();

    if (sourceName.endsWith("-65")) {
      features.polygon("koppen")
        .setMinZoom(1)
        .setMaxZoom(1)
        .setAttr("DN", sourceFeature.getTag("DN"))
        .setPixelTolerance(0.5);
    }

    if (sourceName.endsWith("-33")) {
      features.polygon("koppen")
        .setMinZoom(2)
        .setMaxZoom(3)
        .setAttr("DN", sourceFeature.getTag("DN"))
        .setPixelTolerance(0.5);
    }

    if (sourceName.endsWith("-17")) {
      features.polygon("koppen")
        .setMinZoom(4)
        .setMaxZoom(5)
        .setAttr("DN", sourceFeature.getTag("DN"))
        .setPixelTolerance(0.5);
    }

    if (sourceName.endsWith("-9")) {
      features.polygon("koppen")
        .setMinZoom(6)
        .setMaxZoom(6)
        .setAttr("DN", sourceFeature.getTag("DN"))
        .setPixelTolerance(0.5);
    }

  }

  @Override
  public String name() {
    return "Median Filter Landcover";
  }

  @Override
  public String description() {
    return "Median Filter Landcover";
  }

  @Override
  public String attribution() {
    return "Beck et al. [2018]";
  }

  public static void main(String[] args) throws Exception {
    run(Arguments.fromArgsOrConfigFile(args));
  }

  static void run(Arguments args) throws Exception {
    
    Planetiler p = Planetiler.create(args)
      .setProfile(new SwissMap())
      .overwriteOutput("pmtiles", Path.of("data", "median-filter-landcover.pmtiles"));
    
    File folder = new File("../zips");
    File[] files = folder.listFiles();
    for (File file : files) {
      if (file.isFile() && file.getName().endsWith(".zip")) {
          String fileNameWithoutSuffix = file.getName().replace(".zip", "");
          System.out.println("adding source " + fileNameWithoutSuffix);
          p.addGeoPackageSource("EPSG:4326", fileNameWithoutSuffix, Path.of("..", "zips", fileNameWithoutSuffix + ".zip"), "");
      }
    }
      
    p.run();
  }
}