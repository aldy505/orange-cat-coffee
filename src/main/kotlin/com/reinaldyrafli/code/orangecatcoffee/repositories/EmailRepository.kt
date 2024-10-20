package com.reinaldyrafli.code.orangecatcoffee.repositories

import org.simplejavamail.api.email.Email
import org.simplejavamail.email.EmailBuilder
import org.simplejavamail.mailer.MailerBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class EmailRepository(
    @Value("\${mail.smtp.server}")
    private val smtpServer: String? = null,
    @Value("\${mail.smtp.port}")
    private val smtpPort: Int? = null,
    @Value("\${mail.smtp.username}")
    private val smtpUsername: String? = null,
    @Value("\${mail.smtp.password}")
    private val smtpPassword: String? = null,
) {
    fun sendRaw(email: Email) {
        val mailer = MailerBuilder
            .withSMTPServerHost(smtpServer)
            .withSMTPServerPort(smtpPort)
            .withSMTPServerUsername(smtpUsername)
            .withSMTPServerPassword(smtpPassword)
            .buildMailer()

        mailer.sendMail(email)
    }

    fun send(from: String, to: String, subject: String, htmlBody: String, textBody: String) {
        val email = EmailBuilder.startingBlank()
            .from(from)
            .to(to)
            .withSubject(subject)
            .withHTMLText(htmlBody)
            .withPlainText(textBody)
            .buildEmail()

        sendRaw(email)
    }
}