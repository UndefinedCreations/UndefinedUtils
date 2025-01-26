package com.undefined.api.logger

import com.undefined.api.UndefinedUtils

fun sendError(message: String) = UndefinedUtils.logger.severe(message)
fun sendWarn(message: String) = UndefinedUtils.logger.warning(message)
fun sendInfo(message: String) = UndefinedUtils.logger.info(message)
fun sendDebug(message: String) = UndefinedUtils.logger.config(message)
fun sendTrace(message: String) = UndefinedUtils.logger.fine(message)