//--------------------------------------------------
// Class LoginActivity
//--------------------------------------------------
// Written by Kenvix <i@kenvix.com>
//--------------------------------------------------

package com.kenvix.walk.ui.login

import android.os.Bundle
import com.kenvix.walk.R
import com.kenvix.walk.ui.base.BaseActivity

class LoginActivity : BaseActivity() {
    override fun onInitialize(savedInstanceState: Bundle?) {

    }

    override fun getBaseLayout(): Int = R.layout.activity_login
    override fun getBaseContainer(): Int = R.id.login_container
}