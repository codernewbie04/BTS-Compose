package com.akmal.bandungcompose

import com.akmal.bandungcompose.model.DataDummy
import com.akmal.bandungcompose.model.TempatWisata
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class BandungRepository {
    private val data = mutableListOf<TempatWisata>()

    init {
        if(data.isEmpty()) {
            DataDummy.data.forEach{
                data.add(
                    TempatWisata(
                        id = it.id,
                        img= it.img,
                        name = it.name,
                        open = it.open,
                        address = it.address,
                        description = it.description
                    )
                )
            }
        }
    }

    fun getAllDestination(): Flow<List<TempatWisata>> {
        return flowOf(data)
    }

    fun getDestination(idDestination: String): TempatWisata {
        return data.first{
            it.id == idDestination
        }
    }

    companion object {
        @Volatile
        private var instance: BandungRepository? = null

        fun getIntance(): BandungRepository =
            instance ?: synchronized(this) {
                BandungRepository().apply {
                    instance = this
                }
            }
    }
}