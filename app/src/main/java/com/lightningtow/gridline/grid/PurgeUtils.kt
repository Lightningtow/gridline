package com.lightningtow.gridline.grid

import LoadingScreen
import android.content.Context
import android.os.Bundle
import android.util.Log
import com.lightningtow.gridline.data.Model
import kotlinx.coroutines.launch
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import com.lightningtow.gridline.data.SecTrackHolder
import com.lightningtow.gridline.grid.PlaylistGetter
import com.lightningtow.gridline.ui.home.dataStore
import com.lightningtow.gridline.ui.home.loadingMessage
import com.lightningtow.gridline.ui.theme.GridlineTheme
import com.lightningtow.gridline.utils.Constants
import com.lightningtow.gridline.utils.toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

//var purging: Boolean = false
//var message: String = "default"


// just removes purgelist from the playlist passed as an arg
//class PurgeUtils(){ // todo this only hosts backend shit
//    companion object {

private val scope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)


fun purgePlaylist(
    victim: String,
    purgelist: String = Constants.PURGELIST,
    callback: () -> Unit
) {

    val api = Model.credentialStore.getSpotifyClientPkceApi()!!





    Log.e("coroutine?", Dispatchers.toString())


//    val purgeCallback =


    loadingMessage = "Getting Purgelist"
    Log.e("purge", "getting purgelist")

    PlaylistGetter.getPlaylistByURI(URI = purgelist, holder = 2,

        callback = {

            scope.launch {
            Log.e("purge", "removing")
            api.playlists.removePlayablesFromClientPlaylist(
                playlist = victim,
//            snapshotId = uh,
                playables = SecTrackHolder.contents.map { it.uri }.toTypedArray()
            )
                callback()

                Log.e("purge", "returning from $victim")
        }
    }) // end callback


    // todo recommended to have snapshot id

}

//    }
//}
//@Composable
//private fun PurgeMaster() {
//    GridlineTheme {
//
//        if (purging) // since `loading` is a mutableStateOf, PlaylistViewMaster automatically recomposes when loading changes
//            LoadingScreen(message = "Loading playlists...")
//        else
////            PlaylistViewPage(holder = holder, onPlaylistClick)
//
//
//    }
//
//}