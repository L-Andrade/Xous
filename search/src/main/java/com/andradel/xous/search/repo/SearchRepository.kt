package com.andradel.xous.search.repo

import com.andradel.xous.search.network.SearchDataSource
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val searchDataSource: SearchDataSource
) {
    fun getPopular() = flow {
        supervisorScope {
            val popularShows = async {
                searchDataSource.getPopularShows()
            }
            val popularPeople = async {
                searchDataSource.getPopularPeople()
            }
            /*
            val a = async {
                searchDataSource.getPopularMovies()
            }
             */
            awaitAll(popularShows, popularPeople).forEach { emit(it) }
        }
    }

    fun search(query: String) = flow {
        supervisorScope {
            val queriedShows = async {
                searchDataSource.searchShows(query)
            }
            val queriedPeople = async {
                searchDataSource.searchPeople(query)
            }
            awaitAll(queriedShows, queriedPeople).forEach { emit(it) }
        }
    }
}