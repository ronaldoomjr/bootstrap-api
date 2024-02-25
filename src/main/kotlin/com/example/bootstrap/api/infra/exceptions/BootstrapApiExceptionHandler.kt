package com.example.bootstrap.api.infra.exceptions

import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.BindingResult
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.net.http.HttpHeaders

@RestControllerAdvice
class BootstrapApiExceptionHandler(private val messageSource: MessageSource) : ResponseEntityExceptionHandler() {

    // Valida atributos desconhecidos inseridos no JSON
    override fun handleHttpMessageNotReadable(
        ex: HttpMessageNotReadableException,
        headers: org.springframework.http.HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any>? {
        val message = messageSource.getMessage("message.invalid", null, LocaleContextHolder.getLocale())
        val exception = ex.message.toString()
        val errors = listOf(Error(message, exception))

        return handleExceptionInternal(ex, errors,
            org.springframework.http.HttpHeaders(), HttpStatus.BAD_REQUEST, request)
    }

    // Para auxiliar no retorno das validações pelo Bean Validation
    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: org.springframework.http.HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any>? {
        val errors = getErrors(ex.bindingResult)

        return handleExceptionInternal(ex, errors,
            org.springframework.http.HttpHeaders(), HttpStatus.BAD_REQUEST, request)
    }

    private fun getErrors(bindingResult: BindingResult): Collection<Error> {
        val erros = ArrayList<Error>()

        bindingResult.fieldErrors.forEach { fieldError ->
            val message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale())
            val exception = fieldError.toString()
            erros.add(Error(message, exception))
        }

        return erros
    }

    inner class Error(val message: String, val exception: String)
}