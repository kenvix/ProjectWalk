//--------------------------------------------------
// Class UserPass
//--------------------------------------------------
// Written by Kenvix <i@kenvix.com>
//--------------------------------------------------

package com.kenvix.walk.ui.login

data class UserPass(
        val name: String,
        val password: String,
        val code: String? = null
)