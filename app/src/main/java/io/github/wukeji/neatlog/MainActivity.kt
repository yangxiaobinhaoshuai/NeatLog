package io.github.wukeji.neatlog

import android.os.Bundle
import androidx.activity.ComponentActivity
import io.github.wukeji.neatlog.api.LogMixin

class MainActivity : ComponentActivity(), LogMixin {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logD("onCreate ::")
    }
}