package com.groupal.shared.ecommerce.data.di // ktlint-disable filename

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SharedDatabase

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthenticatedHttpClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class UnauthenticatedHttpClient
