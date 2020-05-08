package com.andradel.xous.search.repo

import com.andradel.xous.search.network.SearchDataSource
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val searchDataSource: SearchDataSource
) {
    fun getPopular() = flow {
        supervisorScope {
            emit(searchDataSource.getPopularShows())
        }
        supervisorScope {
            emit(searchDataSource.getPopularPeople())
        }
        /*
        supervisorScope {
            searchDataSource.getPopularMovies()
        }
         */
    }

    suspend fun searchShows(query: String) = searchDataSource.searchShows(query)
}