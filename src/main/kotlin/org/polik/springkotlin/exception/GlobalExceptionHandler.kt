package org.polik.springkotlin.exception

import lombok.extern.slf4j.Slf4j
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.BindException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import javax.validation.ValidationException

@Slf4j
@ControllerAdvice
class GlobalExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(ValidationException::class)
    fun handleValidationException(): ResponseEntity<Error?>? {
        return createResponseEntity(HttpStatus.BAD_REQUEST, VALIDATION_MESSAGE)
    }

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFound(): ResponseEntity<Error?>? {
        return createResponseEntity(HttpStatus.NOT_FOUND, NOT_FOUND_MESSAGE)
    }

    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {

        return createResponseEntity(HttpStatus.BAD_REQUEST, VALIDATION_MESSAGE)
    }

    override fun handleBindException(
        ex: BindException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        return createResponseEntity(HttpStatus.BAD_REQUEST, VALIDATION_MESSAGE)
    }


    override fun handleHttpMessageNotReadable(
        ex: HttpMessageNotReadableException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        return createResponseEntity(HttpStatus.BAD_REQUEST, VALIDATION_MESSAGE)
    }

    override fun handleExceptionInternal(
        ex: Exception,
        body: Any?,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        logger.error("Internal Exception", ex)
        return createResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, INTERNAL_ERROR_MESSAGE)
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T> createResponseEntity(status: HttpStatus, message: String): ResponseEntity<T> {
        return ResponseEntity.status(status).body(ErrorInfo(status.value(), message)) as ResponseEntity<T>
    }

    companion object {
        const val VALIDATION_MESSAGE = "Validation Failed"
        const val NOT_FOUND_MESSAGE = "Student not found"
        const val INTERNAL_ERROR_MESSAGE = "Internal error"
    }
}