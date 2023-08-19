package com.creative.spotifykt.spotifylite.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.creative.spotifykt.R
import com.creative.spotifykt.base.viewmodel.BaseViewModel
import com.creative.spotifykt.spotifylite.App
import com.creative.spotifykt.spotifylite.data.ActionIcon
import com.creative.spotifykt.spotifylite.data.LayoutConfig
import com.creative.spotifykt.spotifylite.data.LayoutOrientation
import com.creative.spotifykt.spotifylite.data.MusicListUI
import com.creative.spotifykt.spotifylite.data.MusicSquareUI
import com.creative.spotifykt.spotifylite.data.TextLabel

class HomeFragmentViewModel(
    private val appContext: App
) : BaseViewModel() {

    private val listDumData = mutableListOf<MusicSquareUI>().apply {
        for (i in 0..21) {
            add(
                MusicSquareUI(
                    id = i.toString(),
                    image = HomeFragment.DUMMY_URL,
                    headline = TextLabel(
                        "Spotify Lite ${i + 1}",
                        "PRIMARY"
                    )
                )
            )
        }
    }

    private val listMusicListUIS = mutableListOf<MusicListUI>().apply {
        add(
            MusicListUI(
                0,
                headline = TextLabel(appContext.getString(R.string.recent_title), "SECONDARY", "SECONDARY"),
                actionIcon = ActionIcon(),
                items = listDumData.toList(),
                LayoutConfig(
                    orientation = LayoutOrientation.HORIZONTAL.value,
                    spanCount = 1
                )
            )
        )

        add(
            MusicListUI(
                1,
                headline = TextLabel(appContext.getString(R.string.recent_title), "SECONDARY", "SECONDARY"),
                actionIcon = ActionIcon(),
                items = listDumData.toList(),
                LayoutConfig(
                    orientation = LayoutOrientation.HORIZONTAL.value,
                    spanCount = 3
                )
            )
        )

        add(
            MusicListUI(
                2, TextLabel(appContext.getString(R.string.made_for_you)),
                items = listDumData.toList(),
                actionIcon = ActionIcon(),
                layoutConfig = LayoutConfig(
                    orientation = LayoutOrientation.VERTICAL.value,
                    spanCount = 2
                )
            )
        )

        add(
            MusicListUI(
                2, TextLabel(appContext.getString(R.string.recommended_for_you)),
                items = listDumData.toList(),
                actionIcon = ActionIcon(),
                layoutConfig = LayoutConfig(
                    orientation = LayoutOrientation.VERTICAL.value,
                    spanCount = 2
                )
            )
        )
    }

    private val homeListLiveData: MutableLiveData<HomeListState> = MutableLiveData()
    val homeList: LiveData<HomeListState> = homeListLiveData

    init {
        homeListLiveData.value = HomeListState.Success(listMusicListUIS)
    }
}

sealed class HomeListState {
    object Loading : HomeListState()
    data class Success(val data: List<MusicListUI>) : HomeListState()
    object Error : HomeListState()
}