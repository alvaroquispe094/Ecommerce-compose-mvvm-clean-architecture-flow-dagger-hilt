package com.groupal.ecommerce.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.groupal.ecommerce.common.Resource
import com.groupal.ecommerce.domain.model.Product
import com.groupal.ecommerce.domain.use_case.categories.GetCategoriesUseCase
import com.groupal.ecommerce.domain.use_case.products.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject


/**
 * ViewModel that handles the business logic of the Home screen
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(HomeScreenState())
    val state: StateFlow<HomeScreenState> = _state

    fun getProducts() {
        getProductsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            products = result.data ?: emptyList(),
//                            isLoading = false
                        )
                    }
                }
                is Resource.Error -> {
                    _state.value = HomeScreenState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
//                is Resource.Loading -> {
//                    _state.value = HomeScreenState(isLoading = true)
//                }
                else -> {
//                    logica else
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getCategories() {
        getCategoriesUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            categories = result.data ?: emptyList(),
                            isLoading = false
                        )
                    }
                }
                is Resource.Error -> {
                    _state.value = HomeScreenState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
//                is Resource.Loading -> {
//                    _state.value = HomeScreenState(isLoading = true)
//                }
                else -> {
//                    logica else
                }
            }
        }.launchIn(viewModelScope)
    }

//    private val viewModelState = MutableStateFlow(HomeViewModelState(isLoading = true))
//
//    // UI state exposed to the UI
//    val uiState = viewModelState
//        .map { it.toUiState() }
//        .stateIn(
//            viewModelScope,
//            SharingStarted.Eagerly,
//            viewModelState.value.toUiState()
//        )

    init {
        getCategories()
        getProducts()
    }

    /**
     * Selects the given article to view more information about it.
     */
    fun selectProduct(product: Product) {
        // Treat selecting a detail as simply interacting with it
        _state.update {
            it.copy(
                isProductOpen = true,
//                isHomeOpen = false,
                product = product
            )
        }
//        al ir a detalles y antes justos se quito un producto de grilla se actualizara al volver
//        getProducts()
    }

    /**
     * Refresh posts and update the UI state accordingly
     */
//    fun refreshPosts() {
//        // Ui state is refreshing
//        viewModelState.update { it.copy(isLoading = true) }
//
//        viewModelScope.launch {
//            val result = postsRepository.getPostsFeed()
//            viewModelState.update {
//                when (result) {
//                    is Result.Success -> it.copy(postsFeed = result.data, isLoading = false)
//                    is Result.Error -> {
//                        val errorMessages = it.errorMessages + ErrorMessage(
//                            id = UUID.randomUUID().mostSignificantBits,
//                            messageId = R.string.load_error
//                        )
//                        it.copy(errorMessages = errorMessages, isLoading = false)
//                    }
//                }
//            }
//        }
//    }

    /**
     * Toggle favorite of a post
     */
//    fun toggleFavourite(postId: String) {
//        viewModelScope.launch {
//            postsRepository.toggleFavorite(postId)
//        }
//    }

    /**
     * Selects the given article to view more information about it.
     */
//    fun selectArticle(postId: String) {
//        // Treat selecting a detail as simply interacting with it
//        interactedWithArticleDetails(postId)
//    }

    /**
     * Notify that an error was displayed on the screen
     */
//    fun errorShown(errorId: Long) {
//        viewModelState.update { currentUiState ->
//            val errorMessages = currentUiState.errorMessages.filterNot { it.id == errorId }
//            currentUiState.copy(errorMessages = errorMessages)
//        }
//    }

    /**
     * Notify that the user interacted with the feed
     */
    fun interactedWithFeed() {
        _state.update {
            it.copy(
                isProductOpen = false,
//                isHomeOpen = true
            )
        }
//        getProducts()
    }

    /**
     * Notify that the user interacted with the article details
     */
//    fun interactedWithArticleDetails(postId: String) {
//        viewModelState.update {
//            it.copy(
//                selectedPostId = postId,
//                isArticleOpen = true
//            )
//        }
//    }

    /**
     * Notify that the user updated the search query
     */
//    fun onSearchInputChanged(searchInput: String) {
//        viewModelState.update {
//            it.copy(searchInput = searchInput)
//        }
//    }

    /**
     * Factory for HomeViewModel that takes PostsRepository as a dependency
     */
//    companion object {
//        fun provideFactory(
//            postsRepository: PostsRepository,
//        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
//            @Suppress("UNCHECKED_CAST")
//            override fun <T : ViewModel> create(modelClass: Class<T>): T {
//                return HomeViewModel(postsRepository) as T
//            }
//        }
//    }
}

/**
 * UI state for the Home route.
 *
 * This is derived from [HomeViewModelState], but split into two possible subclasses to more
 * precisely represent the state available to render the UI.
 */
//sealed interface HomeUiState {
//
//    val isLoading: Boolean
//    val errorMessages: List<ErrorMessage>
//    val searchInput: String
//
//    /**
//     * There are no posts to render.
//     *
//     * This could either be because they are still loading or they failed to load, and we are
//     * waiting to reload them.
//     */
//    data class NoPosts(
//        override val isLoading: Boolean,
//        override val errorMessages: List<ErrorMessage>,
//        override val searchInput: String
//    ) : HomeUiState
//
//    /**
//     * There are posts to render, as contained in [postsFeed].
//     *
//     * There is guaranteed to be a [selectedPost], which is one of the posts from [postsFeed].
//     */
//    data class HasPosts(
//        val postsFeed: PostsFeed,
//        val selectedPost: Post,
//        val isArticleOpen: Boolean,
//        val favorites: Set<String>,
//        override val isLoading: Boolean,
//        override val errorMessages: List<ErrorMessage>,
//        override val searchInput: String
//    ) : HomeUiState
//}

/**
 * An internal representation of the Home route state, in a raw form
 */
//private data class HomeViewModelState(
//    val postsFeed: PostsFeed? = null,
//    val selectedPostId: String? = null, // TODO back selectedPostId in a SavedStateHandle
//    val isArticleOpen: Boolean = false,
//    val favorites: Set<String> = emptySet(),
//    val isLoading: Boolean = false,
//    val errorMessages: List<ErrorMessage> = emptyList(),
//    val searchInput: String = "",
//) {
//
//    /**
//     * Converts this [HomeViewModelState] into a more strongly typed [HomeUiState] for driving
//     * the ui.
//     */
//    fun toUiState(): HomeUiState =
//        if (postsFeed == null) {
//            HomeUiState.NoPosts(
//                isLoading = isLoading,
//                errorMessages = errorMessages,
//                searchInput = searchInput
//            )
//        } else {
//            HomeUiState.HasPosts(
//                postsFeed = postsFeed,
//                // Determine the selected post. This will be the post the user last selected.
//                // If there is none (or that post isn't in the current feed), default to the
//                // highlighted post
//                selectedPost = postsFeed.allPosts.find {
//                    it.id == selectedPostId
//                } ?: postsFeed.highlightedPost,
//                isArticleOpen = isArticleOpen,
//                favorites = favorites,
//                isLoading = isLoading,
//                errorMessages = errorMessages,
//                searchInput = searchInput
//            )
//        }
//}


