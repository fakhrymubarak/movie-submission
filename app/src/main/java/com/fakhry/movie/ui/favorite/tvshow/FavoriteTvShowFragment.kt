package com.fakhry.movie.ui.favorite.tvshow

import androidx.fragment.app.Fragment


class FavoriteTvShowFragment : Fragment() {
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?,
//    ): View? {
//        return inflater.inflate(R.layout.fragment_movie, container, false)
//    }
//
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        showLoading(true)
//
//        if (activity != null) {
//            val factory = ViewModelFactory.getInstance(requireActivity())
//            val moviesViewModel = ViewModelProvider(
//                this, factory
//            )[FavTvShowViewModel::class.java]
//            EspressoIdlingResource.increment()
//            moviesViewModel.getPopularMovies().observe(this, { movies ->
//                showRecyclerView(movies)
//                EspressoIdlingResource.decrement()
//            })
//        }
//    }
//
//    private fun showRecyclerView(movies: List<MovieResponse>) {
//        rv_movie.setHasFixedSize(true)
//        val movieAdapter = FavTVShowAdapter()
//        movieAdapter.setMovies(movies)
//        movieAdapter.notifyDataSetChanged()
//        rv_movie.layoutManager = LinearLayoutManager(context)
//        rv_movie.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
//        rv_movie.adapter = movieAdapter
//        movieAdapter.setOnItemClickCallback(object : FavTVShowAdapter.OnItemClickCallback {
//            override fun onItemClicked(data: MovieResponse) {
//                showSelectedUser(data.id)
//            }
//        })
//        showLoading(false)
//    }
//
//    private fun showSelectedUser(itemId: Int?) {
//        val intent = Intent(requireActivity(), DetailsActivity::class.java)
//        intent.putExtra(DetailsActivity.EXTRA_MOVIE, itemId)
//        startActivity(intent)
//    }
//
//    private fun showLoading(state: Boolean) =
//        if (state) progressBar.visibility = View.VISIBLE else progressBar.visibility = View.GONE
}
