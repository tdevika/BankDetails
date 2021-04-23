package com.example.bankdetails.utils

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ValidateIfscTest{

    @Test
    fun `empty ifscCode returns false`(){
        val result = ""
        assertThat(result.isValidIFSCode()).isFalse()
    }

    @Test
    fun ` entered input is starting with digits ifscCode returns false`(){
        val result = "1234SBIN012"
        assertThat(result.isValidIFSCode()).isFalse()
    }

    @Test
    fun `entered input is correct ifscCode returns true `(){
        val result = "KARB0000001"
        assertThat(result.isValidIFSCode()).isTrue()
    }

    @Test
    fun `entered input is not correct ifscCode returns false `(){
        val result = "SBIN7125620098"
        assertThat(result.isValidIFSCode()).isFalse()
    }

}
