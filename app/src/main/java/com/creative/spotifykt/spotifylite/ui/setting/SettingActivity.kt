package com.creative.spotifykt.spotifylite.ui.setting

import android.os.Bundle
import androidx.navigation.findNavController
import com.creative.spotifykt.R
import com.creative.spotifykt.databinding.ActivitySettingsBinding
import com.creative.spotifykt.spotifylite.di.component.ActivityComponent
import com.creative.spotifykt.base.ui.BaseActivity

class SettingActivity : BaseActivity<ActivitySettingsBinding, SettingActivityViewModel>() {

    override fun provideViewBinding(): ActivitySettingsBinding = ActivitySettingsBinding.inflate(layoutInflater)

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun setupView(savedInstanceState: Bundle?) {
        findNavController(R.id.nav_host_fragment_settings)
            .setGraph(R.navigation.setting_navigation, intent.extras)
    }
}