package com.sbissaker.trip

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.oscim.map.Map
import org.oscim.android.MapView
import org.oscim.layers.tile.bitmap.BitmapTileLayer
import org.oscim.scalebar.MapScaleBar
import org.oscim.tiling.source.bitmap.BitmapTileSource
import org.oscim.tiling.source.bitmap.DefaultSources
import org.oscim.theme.VtmThemes
import org.oscim.layers.tile.vector.labeling.LabelLayer
import org.oscim.layers.tile.buildings.BuildingLayer



class MainActivity : AppCompatActivity() {

    private lateinit var map: Map
    private lateinit var mapScaleBar: MapScaleBar
    private lateinit var mapTileSource: BitmapTileSource
    private lateinit var mapBitmapLayer: BitmapTileLayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        //mapView = MapView(this)
        map = mapView.map()

        mapTileSource = DefaultSources.OPENSTREETMAP.build()
        mapBitmapLayer = BitmapTileLayer(map, mapTileSource)

        map.setBaseMap(mapBitmapLayer)
        //map.layers().add(mapBitmapLayer)

        // Building layer
        //mapView.map().layers().add(BuildingLayer(mapView.map(), tileLayer))

        // Label layer
        //mapView.map().layers().add(LabelLayer(mapView.map(), tileLayer))

        map.setMapPosition(51.05, -0.72, 14.0)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Track", Snackbar.LENGTH_LONG)
                .setAction("Track", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        mapView.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        mapView.onDestroy()
        super.onDestroy()
    }
}
