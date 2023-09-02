package com.creative.spotifykt.usecase.home

import com.creative.spotifykt.App
import com.creative.spotifykt.data.model.local.HomeLayoutUI
import com.creative.spotifykt.ui.home.HomeListState
import com.creative.spotifykt.usecase.BaseFlowUseCase
import com.squareup.moshi.Moshi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by dan on 02/09/2023
 *
 * Copyright © 2023 1010 Creative. All rights reserved.
 */

class GetHomeLayoutUseCase(
    private val app: App, private val moshi: Moshi
) : BaseFlowUseCase<Unit, HomeListState>() {
    override fun create(input: Unit): Flow<HomeListState> {
        return flow {
            val jsonAdapter = moshi.adapter(HomeLayoutUI::class.java)
            val homeLayoutUI: HomeLayoutUI? = kotlin.runCatching {
                jsonAdapter.fromJson(runCatching {
                    app.assets.open("home-layout.json").let {
                        val size = it.available()
                        val buffer = ByteArray(size)
                        it.read(buffer)
                        it.close()
                        String(buffer, Charsets.UTF_8)
                    }
                }.onFailure {
                    com.creative.spotifykt.core.log(it.message ?: "Error")
                }.getOrDefault(""))
            }.onFailure {
                com.creative.spotifykt.core.log(it.message ?: "Error")
            }.getOrDefault(null)
            val res = homeLayoutUI?.entries?.filterNotNull() ?: listOf()
            if (res.isEmpty())
                emit(HomeListState.Empty)
            else {
                emit(HomeListState.Success(res))
            }
        }
    }
}