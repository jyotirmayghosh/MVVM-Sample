package com.jyotirmayg.mvvmsample.util

import java.io.IOException

class APIExceptions(message: String): IOException(message)

class NoInternetExceptions(message: String): IOException(message)