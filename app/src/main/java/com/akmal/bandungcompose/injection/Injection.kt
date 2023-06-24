package com.akmal.bandungcompose.injection

import com.akmal.bandungcompose.BandungRepository

object Injection {
    fun provideRepository(): BandungRepository {
        return  BandungRepository.getIntance()
    }
}