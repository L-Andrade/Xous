package com.andradel.xous.search.repo

import com.andradel.xous.core.models.Resource
import com.andradel.xous.search.network.SearchDataSource
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val searchDataSource: SearchDataSource
) {
    suspend fun getPopular(): List<Resource<Any>> {
        return supervisorScope {
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
            awaitAll(popularShows, popularPeople)
        }
    }

    suspend fun search(query: String): List<Resource<Any>> {
        return supervisorScope {
            val queriedShows = async {
                searchDataSource.searchShows(query)
            }
            val queriedPeople = async {
                searchDataSource.searchPeople(query)
            }
            awaitAll(queriedShows, queriedPeople)
        }
    }
}