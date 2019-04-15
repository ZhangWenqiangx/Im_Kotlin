package com.example.android_sx_im_kotlin.ui.activity

import android.support.v7.widget.LinearLayoutManager
import com.example.android_sx_im_kotlin.R
import com.example.android_sx_im_kotlin.base.BaseActivity
import com.example.android_sx_im_kotlin.contract.AddFriendContract
import com.example.android_sx_im_kotlin.presenter.AddFriendPresenter
import com.example.android_sx_im_kotlin.ui.adapter.AddFriendListAdapter
import kotlinx.android.synthetic.main.activity_add_friend.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.toast

class AddFriendActivity : BaseActivity(),AddFriendContract.View {

    val presenter = AddFriendPresenter(this)

    override fun onSearchAfter(isSuccess: Boolean, error: String) {
        if(isSuccess){
            dismissProgressDialog()
            toast(R.string.search_success)
            recyclerView.adapter!!.notifyDataSetChanged()
        }else{
            dismissProgressDialog()
            toast(error)
        }
    }

    override fun getLayoutResId(): Int= R.layout.activity_add_friend

    override fun init() {
        super.init()
        headerTitle.text = getString(R.string.add_friend)

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = AddFriendListAdapter(context,presenter.addFriendItems)
        }

        search.setOnClickListener{search()}
        userName.setOnEditorActionListener { v, actionId, event ->
            search()
            true
        }
    }

    fun search(){
        hideSoftKeyboard()
        showProgressDialog(getString(R.string.searching))
        val username = userName.text.trim().toString()
        presenter.search(username)
    }
}
