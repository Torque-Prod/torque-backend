package com.example.demo.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

/**
 * Sends SMS notifications to customers via text.lk (Sri Lankan SMS provider)
 * when a repair job status changes.
 *
 * API: POST https://app.text.lk/api/v3/sms/send
 * Auth: Bearer Token
 *
 * To enable:
 *   1. Log in to https://app.text.lk → API Tokens → copy your token
 *   2. Set  sms.textlk.api-token=<your_token>  in application.properties
 *   3. Set  sms.textlk.sender-id=<your_sender>  (approved sender name/ID)
 *   4. Set  sms.enabled=true
 *
 * SMS failures NEVER roll back job status updates — they are logged as warnings only.
 */
@Slf4j
@Service
public class SmsNotificationService {

    private static final String TEXT_LK_URL = "https://app.text.lk/api/v3/sms/send";

    @Value("${sms.textlk.api-token:}")
    private String apiToken;

    @Value("${sms.textlk.sender-id:Torque}")
    private String senderId;

    @Value("${sms.enabled:false}")
    private boolean enabled;

    private final HttpClient httpClient = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Sends a status-change SMS to the customer.
     *
     * @param toPhone      Customer's phone number.
     *                     text.lk expects the number WITHOUT the leading '+'.
     *                     e.g. "94771234567" (Sri Lanka country code 94)
     * @param customerName Customer's full name (first name used in greeting)
     * @param jobNumber    Job reference number, e.g. "JOB-0042"
     * @param statusLabel  Human-readable status, e.g. "Ready for Pickup"
     */
    public void sendStatusUpdate(String toPhone, String customerName,
                                 String jobNumber, String statusLabel) {
        if (!enabled) {
            log.debug("SMS skipped (disabled). Would have sent to {} for job {} → {}",
                    toPhone, jobNumber, statusLabel);
            return;
        }

        if (apiToken == null || apiToken.isBlank()) {
            log.warn("SMS skipped — sms.textlk.api-token is not configured.");
            return;
        }

        if (toPhone == null || toPhone.isBlank()) {
            log.warn("SMS skipped for job {} — customer has no phone number.", jobNumber);
            return;
        }

        // text.lk expects number WITHOUT leading '+' or spaces
        String normalizedPhone = toPhone.replaceAll("[^0-9]", "");

        String messageBody = buildMessage(customerName, jobNumber, statusLabel);

        try {
            // Build JSON payload
            String jsonPayload = objectMapper.writeValueAsString(new TextLkRequest(
                    normalizedPhone,
                    senderId,
                    "plain",
                    messageBody
            ));

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(TEXT_LK_URL))
                    .header("Authorization", "Bearer " + apiToken)
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonPayload))
                    .timeout(Duration.ofSeconds(15))
                    .build();

            HttpResponse<String> response = httpClient.send(request,
                    HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200 || response.statusCode() == 201) {
                log.info("✅ SMS sent via text.lk to {} for job {} | Status: {}",
                        normalizedPhone, jobNumber, statusLabel);
            } else {
                log.warn("⚠️  text.lk returned HTTP {} for job {}. Response: {}",
                        response.statusCode(), jobNumber, response.body());
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.warn("⚠️  SMS interrupted for job {}: {}", jobNumber, e.getMessage());
        } catch (Exception e) {
            // Never throw — must not break the job status update
            log.warn("⚠️  Failed to send SMS via text.lk for job {}: {}",
                    jobNumber, e.getMessage());
        }
    }

    private String buildMessage(String customerName, String jobNumber, String statusLabel) {
        String firstName = (customerName != null && !customerName.isBlank())
                ? customerName.trim().split("\\s+")[0]
                : "Customer";

        return String.format(
                "Hi %s, your repair job %s status has been updated to \"%s\". " +
                "Thank you for choosing Torque.",
                firstName, jobNumber, statusLabel
        );
    }

    /** Simple POJO for the text.lk JSON request body */
    private record TextLkRequest(
            String recipient,
            String sender_id,
            String type,
            String message
    ) {}
}
