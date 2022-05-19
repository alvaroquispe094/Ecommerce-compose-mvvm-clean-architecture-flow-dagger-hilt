package com.groupal.ecommerce.common.enums

/**
 * A precise enumeration of which type of screen to display at the home route.
 *
 * There are 3 options:
 * - [FeedWithArticleDetails], which displays both a list of all articles and a specific article.
 * - [Feed], which displays just the list of all articles
 * - [ArticleDetails], which displays just a specific article.
 */
enum class HomeScreenEnum {
    FeedWithArticleDetails,
    Feed,
    ArticleDetails
}