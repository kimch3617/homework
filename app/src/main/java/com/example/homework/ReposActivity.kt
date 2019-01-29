package com.example.homework

import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.homework.base.BaseActivity
import com.example.homework.model.Repos
import com.example.homework.model.User
import kotlinx.android.synthetic.main.activity_repos.*
import android.R.attr.data
import java.util.*


class ReposActivity : BaseActivity(), ReposView{
    private lateinit var reposAdapter: ReposAdapter
    private lateinit var presenter: ReposPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repos)

        reposAdapter = ReposAdapter()
        recycler_repos.adapter = reposAdapter
        recycler_repos.layoutManager = LinearLayoutManager(this)

        presenter = ReposPresenter()
        presenter.bindView(this)
        presenter.getUsername("jakewharton")
    }

    override fun setUserProfile(user: User) {
        presenter.getRepos("jakewharton")
        reposAdapter.addItem(user)
    }

    override fun setUserRepos(reposList: ArrayList<Repos>) {
        reposList.sortWith(Comparator { lhs, rhs ->
            when {
                lhs.stargazersCount > rhs.stargazersCount -> -1
                lhs.stargazersCount < rhs.stargazersCount -> 1
                else -> 0
            }
        })
        reposAdapter.addItems(reposList)
    }
}
