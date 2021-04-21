package com.example.bankdetails.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.getSystemService
import androidx.fragment.app.Fragment

interface BankSelected {
    fun onBankSelected(ifscCode: String)
}

fun Activity.hideSoftInput() {
    val imm: InputMethodManager? = getSystemService()
    val currentFocus = currentFocus
    if (currentFocus != null && imm != null) {
        imm.hideSoftInputFromWindow(currentFocus.windowToken, 0)
    }
}

fun Fragment.hideSoftInput() = requireActivity().hideSoftInput()
fun View.showSoftInput() = (context.getSystemService(Context.INPUT_METHOD_SERVICE)
        as InputMethodManager).showSoftInput(this, InputMethodManager.SHOW_FORCED)
