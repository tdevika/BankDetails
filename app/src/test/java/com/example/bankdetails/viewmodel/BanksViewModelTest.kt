package com.example.bankdetails.viewmodel

import MainCoroutineRule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.bankdetails.data.source.FakeDataSource
import com.example.bankdetails.utils.DataSource
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BanksViewModelTest {

    private lateinit var banksViewModel: BanksViewModel
    private lateinit var fakeDataSource: FakeDataSource

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @ExperimentalCoroutinesApi
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
    fun deleteFavorite() {
        resetDataSorce()
        val favoriteBank = DataSource.FAVORITE_BANK
        banksViewModel.setFavorite(favoriteBank)
        banksViewModel.deleteFavorite(favoriteBank.ifsc)
        assertThat(fakeDataSource.favBanks).doesNotContain(favoriteBank)
    }

    @Test
    fun getBankDetails_active_returnNotNull() {
        resetDataSorce()
        fakeDataSource.bankDetails = mutableListOf(DataSource.BANK_DETAILS)
        banksViewModel.getBankDetails(DataSource.BANK_DETAILS.ifsc)
        val value = banksViewModel.bankDetails.value
        assertThat(value).isNotNull()
    }

    @Test
    fun getBankDetails_empty_returnNull() {
        resetDataSorce()
        banksViewModel.getBankDetails(DataSource.BANK_DETAILS.ifsc)
        val value = banksViewModel.bankDetails.value
        assertThat(value).isNull()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getBankDetails_both_returnEqual() {
        resetDataSorce()
        fakeDataSource.bankDetails = mutableListOf(DataSource.BANK_DETAILS)
        banksViewModel.getBankDetails(DataSource.BANK_DETAILS.ifsc)
        assertThat(banksViewModel.bankDetails.value).isEqualTo(DataSource.BANK_DETAILS)
    }

    @Test
    fun onSearchQueryChanged_correctIfsc_returnTrue() {
        fakeDataSource.ifscList = DataSource.IFSC_DATA.toMutableList()
        banksViewModel.onSearchQueryChanged(DataSource.FAVORITE_BANK.ifsc)
        assertThat(banksViewModel.searchedBanks.value).contains(DataSource.FAVORITE_BANK.ifsc)
    }

    @Test
    fun onSearchQueryChanged_IncorrectQuery_returnEmpty() {
        fakeDataSource.ifscList = DataSource.IFSC_DATA.toMutableList()
        banksViewModel.onSearchQueryChanged("KARB0000011")
        assertThat(banksViewModel.searchedBanks.value).isEmpty()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getFavoriteBanks() = mainCoroutineRule.runBlockingTest {
        fakeDataSource.favBanks = DataSource.FAVORITE_BANKS
        banksViewModel.getFavoriteBanks()
        val value = banksViewModel.favorites.value
        assertThat(value?.size).isEqualTo(3)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getFavoriteBanks_returnFirstItemsEqual() = mainCoroutineRule.runBlockingTest {
        resetDataSorce()
        fakeDataSource.favBanks = DataSource.FAVORITE_BANKS
        banksViewModel.getFavoriteBanks()
        val value = banksViewModel.favorites.value
        assertThat(value?.first()).isEqualTo(DataSource.FAVORITE_BANK)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getFavoriteBanks_noInput_returnEmpty() = mainCoroutineRule.runBlockingTest {
        resetDataSorce()
        banksViewModel.getFavoriteBanks()
        val value = banksViewModel.favorites.value
        assertThat(value).isEmpty()
    }

    private fun resetDataSorce() {
        fakeDataSource.favBanks.clear()
        fakeDataSource.bankDetails.clear()
        fakeDataSource.ifscList.clear()
    }

}