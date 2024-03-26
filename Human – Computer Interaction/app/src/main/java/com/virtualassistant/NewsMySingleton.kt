package com.virtualassistant

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class NewsMySingleton constructor(context: Context) {
    companion object {
        @Volatile
        private var INSTANCE: NewsMySingleton? = null

        // Get an instance of NewsMySingleton or create a new one if it doesn't exist
        fun getInstance(context: Context) =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: NewsMySingleton(context).also {
                    INSTANCE = it
                }
            }
    }

    // Create a RequestQueue using Volley library
    val requestQueue: RequestQueue by lazy {
        Volley.newRequestQueue(context.applicationContext)
    }

    // Add a request to the RequestQueue
    fun <T> addToRequestQueue(req: Request<T>) {
        requestQueue.add(req)
    }
}
