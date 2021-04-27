package com.example.bankdetails.viewmodel

import MainCoroutineRule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.bankdetails.data.source.FakeDataSource
import com.example.bankdetails.utils.DataSource
import com.example.bankdetails.utils.TestCoroutineRule
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BanksViewModelTest {

    lateinit var banksViewModel: BanksViewModel
    lateinit var fakeDataSource: FakeDataSource
    private val coroutineDispatcher = TestCoroutineDispatcher()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var testCoroutineRule = TestCoroutineRule()

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setupViewModel() {
        fakeDataSource = FakeDataSource()
        banksViewModel = BanksViewModel(
            fakeDataSource,
            ApplicationProvider.getApplicationContext(),
            mainCoroutineRule.dispatcher
        )
    }

    @Test
    fun setFavorite() {
        resetDataSorce()
        val favoriteBank = DataSource.FAVORITE_BANK
        banksViewModel.setFavorite(DataSource.FAVORITE_BANK)
        assertThat(fakeDataSource.favBanks).contains(favoriteBank)
    }

    @Test
    fun deleteFavorite(){
        resetDataSorce()
        val favoriteBank = DataSource.FAVORITE_BANK
        banksViewModel.setFavorite(favoriteBank)
        banksViewModel.deleteFavorite(favoriteBank.ifsc)
        assertThat(fakeDataSource.favBanks).doesNotContain(favoriteBank)
    }

    @Test
    fun getBankDetails() {
        resetDataSorce()
        fakeDataSource.bankDetails = mutableListOf(DataSource.BANK_DETAILS)
        val bankDetails = banksViewModel.getBankDetails(DataSource.BANK_DETAILS.ifsc)
        assertThat(bankDetails).isNotNull()
    }

    @Test
    fun onSearchQueryChanged(){
        fakeDataSource.ifscList = DataSource.IFSC_DATA.toMutableList()
        banksViewModel.onSearchQueryChanged(DataSource.FAVORITE_BANK.ifsc)
        assertThat(banksViewModel.searchedBanks.value).contains(DataSource.FAVORITE_BANK.ifsc)
        banksViewModel.onSearchQueryChanged("KARB0000011")
        assertThat(banksViewModel.searchedBanks.value).isEmpty()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getFavoriteBanks() = mainCoroutineRule.dispatcher.runBlockingTest {
        fakeDataSource.favBanks = DataSource.FAVORITE_BANKS
        banksViewModel.getFavoriteBanks()
        val value = banksViewModel.favorites.value
        // assertThat(value?.size).isEqualTo(3)
    }

    private fun resetDataSorce() {
        fakeDataSource.favBanks.clear()
        fakeDataSource.bankDetails.clear()
        fakeDataSource.ifscList.clear()
    }

}