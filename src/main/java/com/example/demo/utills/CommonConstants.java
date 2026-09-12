/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papertrl.common.utils;


import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author Dimuthu
 */
public abstract class CommonConstants implements Serializable {

    public static final String BILL_COST_DISTRIBUTION = "ECD";
    public static final String BILL_ITEM_DISTRIBUTION = "ICD";

    //################################STRING PARAMETER CONSTANTS-ST############################
    public static final String LINE_NO_PARAM = "LINE_NO_PARAM";
    public static final String DOCUMENT_NUMBER_PARAM = "DOCUMENT_NUMBER_PARAM";
    public static final String VENDOR_NAME_PARAM = "VENDOR_NAME_PARAM";
    public static final String PAYEE_NAME_PARAM = "PAYEE_NAME_PARAM";
    public static final String DOCUMENT_ID_PARAM = "DOCUMENT_ID_PARAM";
    public static final String EXPORT_DATE_PARAM = "EXPORT_DATE_PARAM";
    public static final String REASON = "REASON_PARAM";
    public static final String ACCOUNT_NAME_PARAM = "ACCOUNT_NAME_PARAM";
    public static final String ACCOUNT_TYPE_PARAM = "ACCOUNT_TYPE_PARAM";
    public static final String PAYMENT_REFERENCE_NAME_PARAM = "PAYMENT_REFERENCE_NAME_PARAM";
    public static final String ITEM_NAME_PARAM = "ITEM_NAME_PARAM";
    public static final String DOC_NAME_PARAM = "DOC_NAME_PARAM";
    public static final String PROJECT_CODE_NAME_PARAM = "PROJECT_CODE_NAME_PARAM";
    public static final String USER_NAME_PARAM = "USER_NAME_PARAM";
    public static final String PO_NUMBER_PARAM = "PO_NUMBER_PARAM";
    public static final String BILL_NUMBER_PARAM = "BILL_NUMBER_PARAM";
    public static final String OCR_BILL_TEMPLATE_PARAM = "OCR_BILL_TEMPLATE_PARAM";
    public static final String SUBMITTED_BY_VENDOR_COMMUNITY_PARAM = "SUBMITTED_BY_VENDOR_COMMUNITY_PARAM";
    public static final String ITEM_INDEX = "ITEM_INDEX";
    public static final String EXCEPTION_MESSAGE_PARAM = "EXCEPTION_MESSAGE_PARAM";
    public static final String TENANT_ID_PARAM = "TENANT_ID_PARAM";
    public static final String EVENT_STR_LIST_PARAM = "EVENT_STR_LIST_PARAM";
    public static final String AMOUNT_PARAM = "AMOUNT_PARAM";
    public static final String PAYMENT_MEDIUM_PARAM = "PAYMENT_MEDIUM_PARAM";
    public static final String PAYMENT_TYPE_PARAM = "PAYMENT_TYPE_PARAM";
    public static final String CARD_TYPE_PARAM = "CARD_TYPE_PARAM";
    public static final String DOCUMENT_TYPE = "documentType";


    public static final String CLOSED_TEXT = "Closed";
    public static final String OPEN_TEXT = "Open";
    //################################STRING PARAMETER CONSTANTS-ED############################

    //################################LIFE CYCLE STATUS-ST#####################################
    public static final char STATUS_ACTIVE = 'A';
    public static final char STATUS_LOCKED = 'L';
    public static final char STATUS_INACTIVE = 'I';
    public static final char STATUS_DELETED = 'D';
    public static final char STATUS_REJECTED = 'R';
    public static final char STATUS_READ = 'R';
    public static final char STATUS_UN_READ = 'U';
    public static final char STATUS_PENDING = 'P';
    public static final char STATUS_PROCESSED = 'P';
    public static final char STATUS_UNPROCESSED = 'U';
    public static final char STATUS_HALF_PAID = 'H';
    public static final char STATUS_PROCESSING = 'O';
    public static final char STATUS_VERIFIED = 'V';
    public static final char STATUS_MAILED = 'M';
    public static final char STATUS_MAIL_EXPIRED = 'E';
    public static final char STATUS_YES = 'Y';
    public static final char STATUS_NO = 'N';
    public static final char STATUS_NOT_SUBMITTED = 'S';
    public static final char STATUS_DRAFT = 'T';
    public static final char STATUS_SUCCESS = 'S';
    public static final char STATUS_FAILED = 'F';
    public static final char MAIN_MENU = 'M';
    public static final char SUB_MENU = 'S';
    public static final char STATUS_UNDER_DISCUSSION = 'U';
    public static final char STATUS_CLOSED = 'C';
    public static final char STATUS_CREATED = 'C';
    public static final char STATUS_CANCELLED = 'C';
    public static final char STATUS_UPDATED = 'U';
    public static final char UNTAGGED_PAYMENT = 'U';
    public static final char STATUS_APPROVED = 'A';
    public static final char STATUS_VOID = 'V';
    public static final char ACCOUNT_TYPE_PURCHASE_ACCOUNT = 'Z';
    public static final char ACCOUNT_TYPE_NOT_PURCHASE_ACCOUNT = 'Q';
    public static final char STATUS_UNDO = 'U';
    public static final char STATUS_CONNECTED = 'C';
    public static final char STATUS_TXN_PENDING = 'O';
    public static final char STATUS_TXN_SUBMITTED = 'U';
    public static final char STATUS_TXN_SUCCESS = 'S';
    public static final char STATUS_TXN_COMPLETED = 'V';
    public static final char STATUS_TXN_FAILED = 'F';
    public static final char STATUS_TXN_PUBLISHED = 'B';
    public static final char STATUS_TXN_IN_PROGRESS = 'J';
    public static final char STATUS_TXN_ON_HOLD = 'H';
    public static final char STATUS_TXN_CREATED = 'K';
    public static final String STATUS_REQUIRED = "Z";
    public static final String STATUS_NOT_REQUIRED = "Q";
    public static final char STATUS_DEFAULT = 'D';
    public static final char STATUS_CUSTOM = 'C';
    public static final char STATUS_VISIBLE = 'V';
    public static final char STATUS_INVISIBLE = 'I';
    public static final char STATUS_FREEZE = 'F';
    public static final char STATUS_APPLIED_CREDIT = 'C';
    public static final char STATUS_PARTIALLY_APPLIED_CREDIT = 'G';
    public static final char STATUS_UN_APPLIED_CREDIT = 'U';
    public static final char STATUS_CHECK_RETURN = 'R';
    public static final char STATUS_CHECK_STOP = 'T';
    public static final char STATUS_REVOKED = 'R';
    public static final String STATUS_A = "'A'";

    // 'H' use for suspend status there for W use for on-hold
    public static final char STATUS_ON_HOLD = 'W';
    public static final char UNATTENDED_TYPE = 'U';
    public static final char API_INTEGRATION_TYPE = 'A';


    //################################LIFE CYCLE STATUS-ED#####################################

    //################################AUDIT_TRAIL_STATUSES-ST##################################
    public static final Integer AUDIT_TRAIL_STATUS_UPLOADED = 1;
    public static final Integer AUDIT_TRAIL_STATUS_REVIEWED = 2;
    public static final Integer AUDIT_TRAIL_STATUS_APPROVED = 3;
    public static final Integer AUDIT_TRAIL_STATUS_REJECTED = 4;
    public static final Integer AUDIT_TRAIL_STATUS_REASSIGNED = 5;
    public static final Integer AUDIT_TRAIL_STATUS_DELETED = 6;
    public static final Integer AUDIT_TRAIL_STATUS_RESUBMITTED = 7;
    public static final Integer AUDIT_TRAIL_STATUS_APPROVED_AND_REASSIGNED = 8;
    public static final Integer AUDIT_TRAIL_STATUS_UNDO = 9;
    public static final Integer AUDIT_TRAIL_STATUS_CREATED = 10;
    public static final Integer AUDIT_TRAIL_STATUS_PAYMENT_ISSUED = 11;
    public static final Integer AUDIT_TRAIL_STATUS_CHECK_MAILED = 12;
    public static final Integer AUDIT_TRAIL_STATUS_PAYMENT_REVOKED = 13;
    public static final Integer AUDIT_TRAIL_STATUS_PAYMENT_INVOICE_CHANGED = 14;
    public static final Integer AUDIT_TRAIL_STATUS_SUBMITTED = 15;
    public static final Integer AUDIT_TRAIL_STATUS_SKIPPED = 16;
    public static final Integer AUDIT_TRAIL_STATUS_UPDATED = 17;
    public static final Integer AUDIT_TRAIL_STATUS_SENT_TO_VENDOR_APPROVAL = 18;
    public static final Integer AUDIT_TRAIL_STATUS_UNDER_DISCUSSION = 19;
    public static final Integer AUDIT_TRAIL_STATUS_TAGGED_GRN = 20;
    public static final Integer AUDIT_TRAIL_STATUS_UN_TAGGED_GRN = 21;
    public static final Integer AUDIT_TRAIL_STATUS_IMPORTED = 22;
    public static final Integer AUDIT_TRAIL_STATUS_SAVE_AS_APPROVED = 23;
    public static final Integer AUDIT_TRAIL_STATUS_BULK_APPROVED = 24;
    public static final Integer AUDIT_TRAIL_STATUS_BULK_REJECTED = 25;
    public static final Integer AUDIT_TRAIL_STATUS_BULK_DELETED = 26;
    public static final Integer AUDIT_TRAIL_STATUS_CANCELLED = 27;
    public static final Integer AUDIT_TRAIL_STATUS_GENERATED = 28;
    public static final Integer AUDIT_TRAIL_STATUS_GENERATE_AND_APPROVED = 29;
    public static final Integer AUDIT_TRAIL_STATUS_BATCH_PROCESSING_STARTED = 30;
    public static final Integer AUDIT_TRAIL_STATUS_BATCH_SUBMITTED = 31;
    public static final Integer AUDIT_TRAIL_STATUS_BATCH_FAILED = 32;
    public static final Integer AUDIT_TRAIL_STATUS_BATCH_COMPLETED = 33;
    public static final Integer AUDIT_TRIAL_STATUS_NOTE_ADDED_BY = 34;
    public static final Integer AUDIT_TRIAL_STATUS_PO_OPEN_BY = 35;
    public static final Integer AUDIT_TRIAL_STATUS_PO_CLOSED_BY = 36;
    public static final Integer AUDIT_TRIAL_STATUS_EDIT_AND_APPROVED_BY = 37;
    public static final Integer AUDIT_TRIAL_STATUS_EDIT_AND_SUBMIT_FOR_APPROVAL_BY = 38;
    public static final Integer AUDIT_TRIAL_STATUS_CREDIT_NOTE_APPLIED_BY = 39;
    public static final Integer AUDIT_TRIAL_STATUS_CREDIT_NOTE_DELETED_BY = 40;
    public static final Integer AUDIT_TRIAL_STATUS_TRANSACTION_SUBMITTED_TO_PROVIDER = 45;
    public static final Integer AUDIT_TRIAL_STATUS_MARK_AS_OFFLINE_PAYMENT_PROCESSING_BY = 41;
    public static final Integer AUDIT_TRIAL_STATUS_ACTIVATED = 42;
    public static final Integer AUDIT_TRIAL_STATUS_INACTIVATED = 43;
    public static final Integer AUDIT_TRIAL_STATUS_TRANSACTION_FAILED = 46;
    public static final Integer AUDIT_TRIAL_STATUS_TRANSACTION_COMPLETED = 47;
    public static final Integer AUDIT_TRIAL_REVERTED_OFFLINE_PAYMENT_PROCESSING_BY = 48;
    public static final Integer AUDIT_TRIAL_STATUS_CARD_CREATED = 49;
    public static final Integer AUDIT_TRIAL_STATUS_CARD_TOPPED_UP = 50;
    public static final Integer AUDIT_TRIAL_STATUS_CARD_EFFECTIVE_UNTIL_UPDATED = 51;
    public static final Integer AUDIT_TRIAL_STATUS_CARD_VENDOR_REDEEMED = 52;
    public static final Integer AUDIT_TRIAL_STATUS_CARD_TOP_UP_FAILED = 53;
    public static final Integer AUDIT_TRIAL_STATUS_TRANSACTION_CANCELATION_COMPLETED = 54;
    public static final Integer AUDIT_TRIAL_STATUS_TRANSACTION_CANCELATION_REJECTED = 55;
    public static final Integer AUDIT_TRIAL_STATUS_CARD_CANCELATION_COMPLETED = 56;
    public static final Integer AUDIT_TRIAL_STATUS_CARD_CANCELATION_REJECTED = 57;
    public static final Integer AUDIT_TRIAL_STATUS_EDITED = 58;
    public static final Integer AUDIT_TRIAL_STATUS_MARKED_AS_PAID = 59;
    public static final Integer AUDIT_TRIAL_STATUS_MARKED_AS_UNPAID = 60;
    public static final Integer AUDIT_TRIAL_STATUS_TRANSACTION_DECLINED = 61;
    public static final Integer AUDIT_TRIAL_STATUS_CARD_ACTIVATION_DECLINED = 62;
    public static final Integer AUDIT_TRIAL_STATUS_CARD_INACTIVATION_DECLINED = 63;
    public static final Integer AUDIT_TRIAL_STATUS_CARD_TOP_UP_COMPLETED = 64;
    public static final Integer AUDIT_TRIAL_STATUS_CARD_TOP_UP_DECLINED = 65;
    public static final Integer AUDIT_TRIAL_STATUS_CARD_ACTIVATION_COMPLETED = 66;
    public static final Integer AUDIT_TRIAL_STATUS_CARD_INACTIVATION_COMPLETED = 67;
    public static final Integer AUDIT_TRIAL_STATUS_CARD_NICK_NAME_UPDATED = 68;
    public static final Integer AUDIT_TRIAL_STATUS_TRANSACTION_BEING_PROCESSING = 69;
    public static final Integer AUDIT_TRIAL_STATUS_CARD_UPDATED = 70;
    public static final Integer AUDIT_TRAIL_STATUS_TXN_HAS_BEEN_UPDATED = 84;
    public static final Integer AUDIT_TRAIL_STATUS_CARD_EXPIRED = 86;
    public static final Integer AUDIT_TRAIL_STATUS_CARD_CANCELED = 87;
    public static final Integer AUDIT_TRIAL_STATUS_TRANSACTION_RETURNED_COMPLETED = 88;
    public static final Integer AUDIT_TRIAL_STATUS_TRANSACTION_RELEASE_COMPLETED = 89;

    public static final Integer AUDIT_TRIAL_STATUS_INSERT_ADDITIONAL_APPROVER = 71;
    public static final Integer AUDIT_TRIAL_STATUS_NEGATIVE_BILL_APPLIED = 72;
    public static final Integer AUDIT_TRIAL_STATUS_APPLIED_TO_POSITIVE_BILL = 73;
    public static final Integer AUDIT_TRIAL_STATUS_NEGATIVE_BILL_REMOVED = 74;
    public static final Integer AUDIT_TRIAL_STATUS_REMOVED_FROM_POSITIVE_BILL = 75;

    public static final Integer AUDIT_TRIAL_STATUS_REOPENED = 76;
    public static final Integer AUDIT_TRIAL_STATUS_CHECK_RETURN = 77;
    public static final Integer AUDIT_TRIAL_STATUS_CHECK_STOP_PAYMENT = 78;
    public static final Integer AUDIT_TRIAL_STATUS_EXPORTED_BY = 80;
    public static final Integer AUDIT_TRIAL_STATUS_REPORT_EXPORTED = 79;

    public static final Integer AUDIT_TRIAL_STATUS_PURCHASE_ORDER_EMAIL_TO_VENDOR = 81;

    public static final Integer AUDIT_TRIAL_STATUS_TRANSACTION_ON_HOLD = 82;

    public static final Integer AUDIT_TRIAL_STATUS_TRANSACTION_RELEASE = 83;
    public static final Integer AUDIT_TRAIL_STATUS_RESEND_REMITTANCE_EMAIL = 85;

    //################################AUDIT_TRAIL_STATUSES-ED##################################

    //################################AUDIT TRIAL STATUS NAME-ST###############################
    public static final String AUDIT_TRIAL_STATUS_PENDING = "Pending Status";
    public static final String AUDIT_TRIAL_STATUS_REJECT = "Rejected Status";
    public static final String AUDIT_TRIAL_STATUS_DRAFT = "Draft Status";
    public static final String AUDIT_TRIAL_STATUS_PREVIOUS_ACTION = "Previous Action";

    // Placeholders for dynamic replacement
    public static final String AUDIT_TRAIL_OLD_AMOUNT_PARAM = "{OLD_AMOUNT}";
    public static final String AUDIT_TRAIL_NEW_AMOUNT_PARAM = "{NEW_AMOUNT}";

    // Placeholders for dynamic replacement
    public static final String AUDIT_TRAIL_OLD_FUNDING_ACCOUNT_PARAM = "{OLD_FUNDING_ACCOUNT}";
    public static final String AUDIT_TRAIL_NEW_FUNDING_ACCOUNT_PARAM = "{NEW_FUNDING_ACCOUNT}";

    public static final String AUDIT_TRAIL_AMOUNT_CHANGE_OLD_TO_NEW = "Amount changed from " + AUDIT_TRAIL_OLD_AMOUNT_PARAM + " to " + AUDIT_TRAIL_NEW_AMOUNT_PARAM;

    public static final String AUDIT_TRAIL_FUNDING_ACCOUNT_CHANGE = "Funding account changed from " + AUDIT_TRAIL_OLD_FUNDING_ACCOUNT_PARAM + " to " + AUDIT_TRAIL_NEW_FUNDING_ACCOUNT_PARAM;
    //#########################################################################################

    public static final Integer DEFAULT_INVOICE_APPROVAL_LEVEL = 0;
    public static final Integer PROJECT_EXPENSE_CATEGORY = 2;

    public static final Integer PREDEFINED_DATA_SOURCE = 1;
    public static final Integer CUSTOM_DATA_SOURCE = 2;

    //Audit Trail SPecific Messages-----------
    public static final String CHECK_PAYMENT = "Check";
    public static final String PAYMENT_TYPE = "PAYMENT_TYPE";
    public static final String PAYMENT_REFERENCE = "PAYMENT_REFERENCE";
    public static final String AUDIT_TRAIL_PAYMENT_AMOUNT = "PAYMENT_AMOUNT";
    public static final String AUDIT_TRAIL_BILL_REFERENCE_PARAM = "BILL_NUMBER";
    public static final String PAYMENT_ISSUED_MSG = PAYMENT_TYPE + " payment, Reference number is : " + PAYMENT_REFERENCE + ", Payment amount is : $" + AUDIT_TRAIL_PAYMENT_AMOUNT;

    public static final String CHECK_MAILED_MSG = "Check Number is : " + PAYMENT_REFERENCE;
    public static final String CREDIT_NOTE_APPLIED_MSG = "" + AUDIT_TRAIL_PAYMENT_AMOUNT + " applied from Credit Note #" + PAYMENT_REFERENCE;
    public static final String CREDIT_NOTE_DETACHED_MSG = "" + AUDIT_TRAIL_PAYMENT_AMOUNT + " has been removed from Credit Note #" + PAYMENT_REFERENCE;
    public static final String NEGATIVE_BILL_APPLIED_TO_POSITIVE_BILL_MSG = "" + AUDIT_TRAIL_PAYMENT_AMOUNT + " applied to Bill # " + AUDIT_TRAIL_BILL_REFERENCE_PARAM;
    public static final String NEGATIVE_BILL_APPLIED_FROM_NEGATIVE_BILL_MSG =
            "" + AUDIT_TRAIL_PAYMENT_AMOUNT + " applied from negative Bill # " + AUDIT_TRAIL_BILL_REFERENCE_PARAM;
    public static final String NEGATIVE_BILL_REMOVED_FROM_POSITIVE_BILL_MSG =
            "unapplied negative bill # " + AUDIT_TRAIL_BILL_REFERENCE_PARAM + " amounted to " + AUDIT_TRAIL_PAYMENT_AMOUNT;
    public static final String NEGATIVE_BILL_REMOVED_FROM_NEGATIVE_BILL_MSG = "" + AUDIT_TRAIL_PAYMENT_AMOUNT + " unapplied from Bill # " + AUDIT_TRAIL_BILL_REFERENCE_PARAM;
    public static final String ON_BEHALF_OF = " on behalf of ";
    public static final String STRING_FORMAT = "%.2f";
    public static final String INVOICE_SUBMITTED_FROM_VENDOR_PORTAL = "" + USER_NAME_PARAM + " of vendor " + VENDOR_NAME_PARAM + " through vendor community";


    //Audit Trail SPecific Messages-----------

    //Invoice Types
    public static final char INVOICE_TYPE_E = 'E';
    public static final char INVOICE_TYPE_O = 'O';
    public static final char INVOICE_TYPE_R = 'R';
    public static final char INVOICE_TYPE_C = 'C';

    //Invoice Detection Levels
    public static final char DETECTION_LEVEL_FULL = 'F';
    public static final char DETECTION_LEVEL_PARTIAL = 'P';
    public static final char DETECTION_LEVEL_NOT = 'N';

    //Invoice export status
    public static final char EXPORTED = 'E';
    public static final char NOT_EXPORTED = 'N';

    public static final int PAYMENT_TYPE_ACH = 1;
    public static final Integer PAYMENT_TYPE_CHECK = 2;
    public static final Integer PAYMENT_TYPE_VIRTUAL_CARD = 3;
    public static final Integer PAYMENT_TYPE_CREDIT_CARD = 4;
    public static final Integer PAYMENT_TYPE_CASH = 5;
    public static final Integer PAYMENT_TYPE_EFT = 6;
    public static final Integer PAYMENT_TYPE_WIRE = 7;
    public static final Integer PAYMENT_TYPE_CREDIT_MEMO = 8;
    public static final Integer PAYMENT_TYPE_OTHER = 9;
    public static final Integer PAYMENT_TYPE_DIGITAL_CARD = 10;
    public static final String PAYMENT_TYPE_CASH_STRING = "Cash";
    public static final String PAYMENT_TYPE_CREDIT_CARD_STRING = "CreditCard";

    public static final String USER_TYPE_EXTERNAL = "E";
    public static final String USER_TYPE_INTERNAL = "I";

    public static final String TEMP_FILE_PATH = "${application.temp-file-path}";
    public static final String THUMBNAIL_FILE_PATH = "${application.thumbnail-file-path}";
    public static final String SYSTEM_URL = "${application.url}";
    public static final String SUB_URL = "${application.sub-url}";
    public static final String SYSTEM_SUPPORT_EMAIL = "${application.support-email}";
    public static final String TRIAL_VERIFY_URL = "${application.email-verification-url}";
    public static final String SYSTEM_PASSWORD_RESET_URL = "${application.password-reset-url}";
    public static final String VENDOR_REGISTER_URL = "${application.vendor-register-url}";
    public static final String VENDOR_ACH_EXPIRED_AND_SUCCESS_EMAIL_SEND_TO = "${application.ach-expired-and-success-email-send-to}";
    public static final String VENDOR_ACH_EXPIRED_EMAIL_CC_TO = "${application.ach-expired-email-cc-to}";
    public static final String VENDOR_ACH_DETAILS_REQUEST_URL = "${application.vendor-ach-details-request-url}";
    public static final String VENDOR_ACH_DETAILS_REQUEST_RESEND_URL = "${application.vendor-ach-details-request-resend-url}";
    public static final String VENDOR_ACH_DETAILS_REQUEST_EXPIRE_TIME = "${application.ach-expired-time}";
    public static final String TEMP_PASSWORD_VALID_PERIOD = "${application.temp-password-valid-period}";
    public static final String VENDOR_EMAIL_VERIFICATION_URL = "${application.vendor-email-verification-url}";
    public static final String SUPPORT_TENANT_ID = "${application.support-tenant-id}";
    public static final String SYSTEM__TEC_SUPPORT_EMAIL = "${tec_support_email}";

    public static final String DEFAULTPROPICFILE_PATH = "${application.default.propic-file-path}";
    public static final String DEFAULTPROPIC_CONTENT_TYPE = "${application.default.propic-content-type}";
    public static final String DEFAULTPROPIC_NAME = "${application.default.propic-name}";

    public static final String APPLICATION_ALLOWED_ORIGINS = "${application.security.allowed-orgins}";
    public static final String APPLICATION_HEADER_CONTENT_SECURITY = "${application.security.header.content-security}";
    public static final String APPLICATION_HEADER_CONTENT_FEATURE_POLICY = "${application.security.header.feature-policy}";
    public static final String APPLICATION_HEADER_REFERER_POLICY = "${application.security.header.referer-policy}";

    public static final String APPLICATION_SYSTEM_SCHEDULE_USER_NAME = "papertrl_system_schedule";
    public static final String APPLICATION_SYSTEM_SCHEDULE_NAME = "System Schedule";
    public static final String APPLICATION_SYSTEM_INTEGRATION_USER = "System Integration User";
    public static final String APPLICATION_SYSTEM_INTEGRATION_USER_NAME = "papertrl_system_integration";
    public static final String AUTOMATED_VENDOR_IMPORT_SCHEDULE_NAME = "Automated Vendor Import Schedule";

    public static final String INBOX_EMAIL_DOMAIN = "@papertrl.com";
    public static final String ATTACHMENT_NAME = "ATTACHMENT_NAME";
    public static final String ATTACHMENT_ID = "ATTACHMENT_ID";

    public static final String AUTH_DB = "DB";
    public static final String AUTH_AD = "AD";
    public static final String AUTH_BASIC = "BS";
    public static final String AUTH_TOKEN = "BT";

    public static final String SFTP_MAX_IDLE = "${sftp-max-idle}";
    public static final String SFTP_MAX_TOTAL = "${sftp-max-total}";
    public static final String SFTP_MAX_WAIT_MILLS = "${sftp-max-wait-mills}";
    public static final String SFTP_MIN_IDLE = "${sftp-min-idle}";
    public static final String SFTP_MIN_EVICTABLE_IDLETIME = "${sftp-min-evictable-idletime}";
    public static final String SFTP_PROPERTY_DIRECTORY = "${sftp-remote-directory}";
    public static final String SFTP_NACHA_ACH_DIRECTORY = "${sftp-nacha-ach-directory}";
    public static final String SFTP_REMOTE_PATH_DIRECTORY = "${unattended-remote-path}";
    public static final String SFTP_DESTINATION_PATH_DIRECTORY = "${unattended-destination-path}";

    //SYSTEM NAMES------
    public static final String SYSTEM_VENDOR_PORTAL = "VP_SYSTEM";
    public static final String SYSTEM_AR_PORTAL = "AR_SYSTEM";
    public static final String SYSTEM_USER_MAN = "UMM_SYSTEM";

    //DEFAULT WORKFLOW ID
    public static final Integer DEFAULT_WORKFLOW_ID = 0;
    //WORKFLOW USERS
    public static final int EMPTY_WORKFLOW = 0;
    //VENDOR ROLE
    public static final Integer VENDOR_ROLE_ID = 1;
    //ADMIN ROLE
    public static final Integer ADMIN_ROLE_ID = 1;
    // SYSTEM SCHEDULER
    public static final Integer PAPERTRL_SYSTEM_SCHEDULER_ID = 1;
    //NO REMOVE OPTION
    public static final Integer NO_REMOVE_OPTION = 0;


    //APPROVAL GROUPS
    public static final Integer APPROVAL_GROUP_ADMIN = 1;
    public static final Integer APPROVAL_GROUP_VENDOR = 2;
    public static final Integer APPROVAL_GROUP_NO_APPROVAL_GROUP = 3;

    //DATE FORMATS---
    public static final String US_DATE_FORMATS_STRING = "MM/dd/yyyy";
    public static final String PHOTON_OCR_DATE_FORMAT_STRING = "yyyy-MM-dd";
    public static final String SEPERATOR_LESS_DATE_FORMATS_STRING = "MMddyyyy";
    public static final String UNATTENDED_PAYMENT_DATE_FORMAT = "yyy-MM-dd HH:mm:ss";

    public static final DateFormat SEPERATOR_LESS_DATE_FORMATS_YY_MM_DD = new SimpleDateFormat("yyMMdd");
    public static final DateFormat DATE_FORMAT_YYYY_MM_DD_WITH_HOURS_EXCEPT_SECONDS = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public static final DateFormat DATE_FORMAT_YYYY_MM_DD_WITH_HOURS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final DateFormat DATE_FORMAT_YYYY_MM_DD_WITH_HOURS_FOR_NACHAACH = new SimpleDateFormat("yyyy-MM-dd_HHmmss");
    public static final DateFormat DATE_FORMAT_MM_DD_YYYY_WITH_HOURS_FOR_NACHAACH = new SimpleDateFormat("MMddyyyy.HHmmss");
    public static final DateFormat DATE_FORMAT_MMDDYYYYHHMMSS_FOR_MECHANICS_BANK_ACH = new SimpleDateFormat("MMddyyyyHHmmss");
    public static final DateFormat DATE_FORMAT_YYYY_MM_DD_WITH_TMS = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
    public static final DateFormat DATE_FORMAT_YYYY_MM_DD = new SimpleDateFormat("yyyy-MM-dd");
    public static final DateFormat DATE_FORMAT_YYYY_MM_DD_SLASH = new SimpleDateFormat("yyyy/MM/dd");
    public static final DateFormat DATE_FORMAT_YYYY_MM = new SimpleDateFormat("yyyy-MM");
    public static final DateFormat DATE_FORMAT_YYYY_MM_SLASH = new SimpleDateFormat("yyyy/MM");
    public static final DateFormat DATE_FORMAT_MM_YYYY = new SimpleDateFormat("MM-yyyy");
    public static final DateFormat DATE_FORMAT_MM_YYYY_SLASH = new SimpleDateFormat("MM/yyyy");
    public static final DateFormat DATE_FORMAT_DD_MM_YYYY_SLASH = new SimpleDateFormat("dd/MM/yyyy");

    public static final DateFormat DATE_FORMAT_MM_DD_YYYY_SLASH = new SimpleDateFormat("MM/dd/yyyy");
    public static final DateFormat DATE_FORMAT_MM_DD_YYYY_SLASH_WITH_HOURS = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    public static final DateFormat DATE_FORMAT_MM_DD_YYYY_SLASH_WITH_HOURS_AMPM = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
    public static final DateFormat DATE_FORMAT_MM_DD_YYYY = new SimpleDateFormat("MM-dd-yyyy");
    public static final DateFormat DATE_FORMAT_YYYYMMDD_HHMMSS = new SimpleDateFormat("yyyyMMdd.HHmmss");
    public static final DateFormat DATE_FORMAT_YYYYMMDDHHMMSS = new SimpleDateFormat("yyyyMMddHHmmss");
    public static final DateFormat DATE_FORMAT_DDMMYYYY_HHMMSS = new SimpleDateFormat("ddMMyyyy.HHmmss");

    public static final DateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm");
    public static final DateFormat SEPERATOR_LESS_TIME_FORMAT = new SimpleDateFormat("HHmm");
    public static final String START_HOURS = " 00:00:00";
    public static final String END_HOURS = " 23:59:59";
    public static final String EMAIL_FORMAT = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
    public static final String UTC_ZONE = "UTC";
    //DARE FORMATS---

    //Decimal Formats---
    public static final String DECIMAL_FORMAT_STRING = "##,##,##,##,##,##,##0.00";
    public static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("##,##,##,##,##,##,##0.00");
    public static final DecimalFormat SEPERATOR_LESS_DECIMAL_FORMAT = new DecimalFormat("##########0.00");
    public static final DecimalFormat THREE_DECIMAL_FORMAT = new DecimalFormat("##,##,##,##,##,##,##0.000");
    //Decimal Formats---

    //SFTP PROPERTIES-----
    protected static final String SFTP_PROPERTY_HOST = "${sftp.host}";
    protected static final String SFTP_PROPERTY_PORT = "${sftp.port:22}";
    protected static final String SFTP_PROPERTY_USER = "${sftp.user}";
    protected static final String SFTP_PROPERTY_PRIVATE_KEY = "${sftp.privateKey}";
    protected static final String SFTP_PROPERTY_PRIVATE_KEY_PASS_PHASE = "${sftp.privateKeyPassphrase}";
    protected static final String SFTP_PROPERTY_PASSWORD = "${sftp.password}";
    public static final String SFTP_SERVICE_ACTIVATOR_NAME = "toSftpChannel";
    public static final String STRICT_HOSTKEY_CHECKING = "StrictHostKeyChecking";
    public static final String NO = "no";
    public static final String SFTP_CHANNEL = "sftp";
    public static final String SHELL_CHANNEL = "shell";
    public static final String FTP_CHANNEL = "ftp";
    public static final String EXECUTION_CHANNEL = "exec";
    //SFTP PROPERTIES-----

    public static final String CURRENY_USD = "USD";
    public static final String VENDOR_COMMUNITY_NAME_SUFFIX = "vendor";
    public static final String SUPPORT_NAME_SUFFIX = "support";
    public static final String PORTAL_NAME_SUFFIX = "portal";
    public static final String SUPPORT_NAME_FOR_STATIC_MENU = "Support";

    //EMAIL PROPERTIES-----
    public static final String EMAIL_MAX_ATTEMPTS = "${email.max-attempt}";
    public static final String EMAIL_TIME_INTERVEL = "${email.time-intervel}";
    public static final String EMAIL_TEMP_TO_MASTER_GAP = "${email.temp-to-master-gap}";
    public static final String EMAIL_TEMP_TO_MASTER_INTERVEL = "${email.temp-to-master-intervel}";
    public static final String EMAIL_FROM_ADDRESS = "${email.from-address}";
    //EMAIL PROPERTIES-----

    //SYSTEM PROPERTIES-----
    public static final String UNDEFINED = "undefined";
    public static final String BCRIPT_ENCRPTED = "{bcrypt}";
    public static final String NULL_STRING = "null";
    public static final String EMPTY_STRING = "";
    public static final String DOUBLE_QUOTE_STRING = "\"";
    public static final String SPACE_STRING = " ";
    public static final char SPACE_STRING_CHAR = ' ';
    public static final String EQUAL_STRING = "=";
    public static final String DOT_STRING = ".";
    public static final String DOT_STRING_ESCAPED = "[.]";
    public static final String OPENING_SQUARE_BRACKET = "]";
    public static final String CLOSING_SQUARE_BRACKET = "[";
    public static final String CLOSING_SQUARE_BRACKET_WITH_LEADING_SPACE = " [";
    public static final String COMMA_STRING = ",";
    public static final String COMMA_STRING_WITH_SPACE = ", ";
    public static final String ANDPESENT_STRING = "&";
    public static final String HASH_STRING = "#";
    public static final String SINGLE_QUOTE_STRING = "'";
    public static final String FORWARD_SLASH = "/";
    public static final String DOUBLE_FORWARD_SLASH = "//";
    public static final String PIPE_STRING = "|";
    public static final String COMBINATION_SPACE_STRING = " | ";
    public static final String BACKWARD_SLASH = "\\";
    public static final String DASH_STRING = "-";
    public static final String SPECIAL_DASH_STRING = "–";
    public static final String DASH_STRING_WITH_SPACE = " - ";
    public static final String UNDERSCORE_STRING = "_";
    public static final String PLUS_STRING = "+";
    public static final String SEMI_CLOLON_STRING = ";";
    public static final String COLON_STRING = ":";
    public static final String X_STRING = "X";
    public static final String ORDER_BY = " ORDER BY ";
    public static final String GROUP_BY = " GROUP BY ";
    public static final String DOLAR_STRING = "$";
    public static final String PDF_EXTENTION = ".pdf";
    public static final String ZIP_EXTENTION = ".zip";
    public static final String ALL = "*";
    public static final String TENANT_ID = "TENANT_ID";
    public static final String PAYLOAD = "PAYLOAD";
    public static final String BASE_URL = "BASE_URL";
    public static final String PARENT_TENANT_ID = "PARENT_TENANT_ID";
    public static final String CLIENT_TYPE = "client_type";
    public static final String ANY_REQUEST = "/**";
    public static final String SUPPORT_TENANT = "support";
    public static final String QUESTION_STRING = "?";
    public static final String BRACKET_OPEN = "(";
    public static final String BRACKET_CLOSE = ")";
    public static final String IMAGE_TYPE = "image";
    public static final String HTTPS_ESCAPED = "https://";
    public static final String ALL_STRING = "All";
    public static final String SUB_CLIENT_ID = "SUB-CLIENT-ID";
    public static final String NOT_AVAILABLE = "N/A";
    public static final String VERSION_1 = "V1";
    public static final String VERSION_2 = "V2";
    public static final String UUID_STRING = "UUID";
    public static final String UUID_IDENTIFIRE = "{UUID}";
    public static final String USTID_STRING = "USTID";
    public static final String TWO_NBSP_STRING = "&nbsp;&nbsp; ";
    public static final String AUDIT_TRAIL_COMMENTS = "Comments : ";
    public static final String AUDIT_TRAIL_REASON = "Reason : ";
    public static final String DUMMY_EMAIL_FORMAT = "@TENANT_ID.onpapertrl.com";
    public static final String SPLIT_BY_DOT = "\\.";
    public static final String YES_STRING_SIMPLE = "yes";
    public static final String NO_STRING_SIMPLE = "no";
    public static final String ON_STRING_SIMPLE = "on";
    public static final String BY_STRING_SIMPLE = "by";
    public static final String TRUE_STRING = "true";
    public static final String FALSE_STRING = "false";
    public static final String BEFORE_STRING_SIMPLE = " before ";
    public static final Integer BILL_PAYMENT_OTHER_TERM = 10;
    public static final String OTHER_NOTE_PREFIX = "[ Other Note : ";
    public static final String CLOSING_SQUARE_BRACKET_WITH_SPACE = " ]";
    public static final String IS_DEPOSIT = "0";
    public static final String IS_CREDIT = "1";
    public static final String CASE = " CASE ";
    public static final String STATUS = " STATUS ";
    public static final String MODULE = "MODULE";
    public static final String SUB_ACCOUNT = "SUB_ACCOUNT";
    public static final String PAYMENT_MODULE = "payments";
    public static final String SPLITTER = " SPLITTER ";

    //SYSTEM PROPERTIES-----
    //ACCESS LEVEL--------
    public static final Integer ACCESS_LEVEL_PM = 1;
    public static final Integer ACCESS_LEVEL_AP = 2;
    public static final Integer ACCESS_LEVEL_VP = 2;
    public static final Integer ACCESS_LEVEL_SA = 1;
    //ACCESS LEVEL--------

    //NOTIFICATION TYPE------
    public static final Integer INVOICE_RELATED_NOTIFICATION = 1;
    public static final Integer VENDOR_RELATED_NOTIFICATION = 2;
    public static final Integer USER_RELATED_NOTIFICATION = 3;
    public static final Integer AUTOMATION_RELATED_NOTIFICATION = 7;
    //NOTIFICATION TYPE------

    //NOTIFICATION NON SUBSCRIPTION --------
    public static String AUTOMATION_RELATED_NOTIFICATION_TITLE = "Automation";
    //NOTIFICATION NON SUBSCRIPTION TYPE --------

    public static final Integer CODE_CATEGORY_PROJECT = 2;
    public static final Integer CODE_CATEGORY_EXPENSE = 1;

    // DATA SOURCE TYPES
    public static final Integer DATA_SOURCE_TYPE_PREDEFINED = 1;
    public static final Integer DATA_SOURCE_TYPE_CUSTOME = 2;

    //OHER-----
    public static final String QUERY_CONDITION_BEAN = "queryConditions";
    public static final String QUERY_SORT_ORDER_BEAN = "querySortOrder";
    public static final String REMARK = "Remark : ";
    public static final String REASSIGNED_TO = "Assigned to ";
    public static final String SUBMITTED_BY_HEADER = "Submitted by ";
    public static final String BR_TAG = "</br>";
    public static final String W9_FORM_NAME = "W9_FORM";
    public static final String VENDOR_INVOICE_FOLDER = "Vendor_Invoice";
    public static final String PROPOSAL_FOLDER = "Proposal";
    public static final String PROJECT_FOLDER = "Project";
    public static final String OPPORTUNITY_FOLDER = "Opportunity";
    public static final String PROFILE_PIC_FOLDER = "PRO_PIC";
    public static final Integer COUNTRY_US = 1;
    public static final String ADDRESS_TYPE_PERMENANT = "PA";
    public static final String ADDRESS_TYPE_REMIT = "RA";
    public static final String ADDRESS_TYPE_SHIPPING = "SP";
    public static final String BILLING_ADDRESS_CHAR_COUNT = "Billing address must be less than 255 characters";
    public static final String SHIPPING_ADDRESS_CHAR_COUNT = "Shipping address must be less than 255 characters";
    public static final String SYSTEM_USER = "PAPERTRL_SYSTEM";
    public static final String APP_NAME_STRING = "app";
    public static final String ADMIN_USER = "admin";
    public static final String NOT_PROVIDED = "NP";
    public static final String INAP_CLIENT = "INAP_CLIENT";
    public static final String CHARACTER_TYPE = "ISO-8859-1";
    public static final String CHARACTER_TYPE_UTF = "UTF-8";
    public static final String ZERO_LENGTH_SPACE_CHARACTER = "\u200B";
    public static final String CHARACTER_TYPE_ASCII = "US-ASCII";
    public static final String HREF_ATTR = "href";
    public static final String PARAM_ATTR = "PARAM";
    public static final String ERROR_ATTR = "ERROR";
    public static final String ID_ATTR = "id";
    public static final String FROM = "from";
    public static final String TO = "to";
    public static final String XLS = "xls";
    public static final String XLSX = "xlsx";
    public static final String XLSM = "xlsm";
    public static final String APPLICATION_TYPE_XLSX = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    public static final String APPLICATION_TYPE_PDF = "application/pdf";
    public static final String APPLICATION_TYPE_OCTET_STREAM = "application/octet-stream";
    public static final String APPLICATION_TYPE_ZIP = "application/zip";
    public static final String APPLICATION_TYPE_JSON = "application/json";
    public static final String APPLICATION_MULTIPART_FORM_DATA = "multipart/form-data";
    public static final String APPLICATION_VND_MS_EXCEL = "application/vnd.ms-excel";
    public static final String TEXT_TYPE_CSV = "text/csv";
    public static final String TEXT_TYPE_PLAIN = "text/plain";
    public static final String ATTACHMENT_NAME_ATTR = "attachment; filename=\"";
    public static final String DOUBLE_QUOTE = "\"";
    public static final String EXPENSE = "expense";
    public static final String EXPENSE_STR = "Expense";
    public static final String ITEM_STR = "Item";
    public static final String VENDOR_EXPENSE_FOLDER = "Vendor_Expense";
    public static final String PURCHASE_ORDERS = "Purchase_Order";
    public static final String RECEIPT_NAME = "RECEIPT";
    public static final String APPROVAL_GROP = "APPROVAL_GROP";
    public static final String PO_RECEIPT_FOLDER = "PO_RECEIPT";
    public static final String PO_FOLDER = "PURCHASE_ORDER";
    public static final String VENDOR_CLASSIFICATION_FOLDER = "VENDOR_CLASSIFICATION_FOLDER";
    public static final String SUPPORT_TICKET_FOLDER = "SUPPORT_TICKET_FOLDER";
    public static final String VENDOR_OCR_TEMPLATE_FOLDER = "VENDOR_OCR_TEMPLATE_FOLDER";
    public static final String VENDOR_W9_FOLDER = "VENDOR_W9_FOLDER";
    public static final String BILL_FOLDER = "BILL_FOLDER";
    public static final String INBOX_FOLDER = "INBOX_FOLDER";
    public static final String CREDIT_CARD_FOLDER = "CREDIT_CARD_FOLDER";
    public static final String VENDOR_ITEM_IMAGE_FOLDER = "VENDOR_ITEM_IMAGE_FOLDER";
    public static final String ITEM_CATALOG_IMAGE_FOLDER = "ITEM_CATALOG_IMAGE_FOLDER";
    public static final String CREDIT_NOTE_FOLDER = "CREDIT_NOTE_ORDER";
    public static final Integer ZERO_BASE = 1;
    public static final String GROUP_STRING = " Group ";
    public static final String AND_STRING = " And ";
    public static final String LOWER_STRING = " LOWER ";
    public static final String ALL_DEPARTMENTS = "All";
    public static final String FIELD_IS_STRING = " field is ";
    public static final String FIELD_MANDATORY_STRING = "(Mandatory*)";
    public static final String FIELD_OPTIONAL_STRING = "(Optional)";
    public static final String NEW_LINE_STRING = "\n";
    public static final String CARRIAGE_STRING = "\r";
    public static final String TAB_STRING = "\t";
    public static final String YES_OR_NO_STRING = "Enter values as 'yes' or 'no' ";
    public static final String COMMA_SEPARATED_STRING = "Enter values as comma(,) separated";
    public static final String ASSIGN_BACK_TO = "Assigned back to ";
    public static final String SYSTEM_NAME_PARAM = "SYSTEM_NAME_PARAM";
    public static final String IMPORT_COMMENT = "Synced from " + SYSTEM_NAME_PARAM + " Application";
    public static final String ALL_PRIVILEGES = "All Privileges";
    public static final String PAYMENT_TYPE_LOGO = "PAYMENT_TYPE_LOGO";
    public static final String DIVERSE_SUPPLIER = "DIVERSE_SUPPLIER";
    public static final String RECURRING_BILL_FOLDER = "RECURRING_BILL";
    public static final String RECURRING_INVOICE_FOLDER = "RECURRING_INVOICE";
    public static final String PAYMENT_FOLDER = "PAYMENT_FOLDER";
    public static final String EXPENSE_FOLDER = "EXPENSE_FOLDER";
    public static final String VENDOR_FOLDER = "VENDOR_FOLDER";
    public static final String CUSTOMER_INVOICE_FOLDER = "CUSTOMER_INVOICE_FOLDER";
    public static final String INTEGRATED_PAYABLE_FOLDER = "INTEGRATED_PAYABLE_FOLDER";
    public static final String ATTENDED_FOLDER = "ATTENDED_FOLDER";
    public static final String SENT_STRING = "Sent";
    public static final String NOT_SENT_STRING = "Not-Sent";
    public static final String PO = "PO - ";
    public static final String LI_ST_TAG = "<li>";
    public static final String LI_ED_TAG = "</li>";
    public static final String UL_ST_TAG = "<ul>";
    public static final String UL_ED_TAG = "</ul>";
    public static final String UNATTENDED_FOLDER = "UNATTENDED_FOLDER";
    public static final String WORKING_FOLDER = "WORKING_FOLDER";
    public static final String ARCHIVE_FOLDER = "ARCHIVE_FOLDER";
    public static final String SUMMERY_REPORT = "SUMMERY_REPORT";
    public static final String PAYMENT_EXPORT = "PAYMENT_EXPORT";
    public static final String PAPERTRL_SYSTEM_EMAIL = "PaperTrlSystem@gmail.com";
    public static final String PAPERTRL_SYSTEM_USER_NAME = "PaperTrlSystem";
    public static final String ANONYMOUS_USER = "anonymousUser";


    //OTHER
    public static final String DESCRIPTION_TAX = "Tax";
    public static final String DESCRIPTION_SHIPPING_COST = "Shipping Cost";

    //URL ENCODE VALUES
    public static final String PLUS_CHARACTER_ENCODE = "%2B";

    //EMAIL PARAMS----
    public static final String PARAM_EMAIL_SEND_TO = "EMAIL_SEND_TO";
    public static final String PARAM_PACKAGE_NAME = "PACKAGE_NAME";
    public static final String PARAM_CLIENT_NAME = "CLIENT_NAME";
    public static final String PARAM_EMAIL_VENDOR_NAME = "VENDOR_NAME";
    public static final String PARAM_EMAIL_APPROVER_NAME = "APPROVER_NAME";
    public static final String ACTION_USER = "ACTION_USER";
    public static final String PARAM_EMAIL_REASON = "REASON";
    public static final String PARAM_EMAIL_USER_ID = "USER_ID";
    public static final String PARAM_EMAIL_TEMPORARY_TOKEN = "TEMPORARY_TOKEN";
    public static final String PARAM_BILL_NUMBER = "BILL_NUMBER";
    public static final String PARAM_APPROVAL_GROUP = "APPROVAL_GROUP";
    public static final String PARAM_SYSTEM_URL = "URL";
    public static final String PARAM_INVITED_BY = "INVITED_BY";
    public static final String PARAM_PASSWORD_RESET_URL = "PASSWORD_RESET_URL";
    public static final String PARAM_REGISTER_URL = "SYSTEM_REGISTER_URL";
    public static final String PARAM_VENDOR_ACH_DETAILS_REQUEST_URL = "VENDOR_ACH_DETAILS_REQUEST_URL";
    public static final String PARAM_VENDOR_ADDRESS = "VENDOR_ADDRESS";
    public static final String PARAM_CREATED_BY = "CREATED_BY";
    public static final String PARAM_VENDOR_NAME = "VENDOR_NAME";
    public static final String PARAM_INVOICE_TABLE = "INVOICE_TABLE";
    public static final String PARAM_PO_NUMBER = "PO_NUMBER";
    public static final String PARAM_SUBMITTED_BY = "SUBMITTED_BY";
    public static final String PARAM_PAPERTRL_URL = "PAPERTRL_URL";
    public static final String PARAM_PORTAL_URL = "PORTAL_URL";
    public static final String PARAM_VENDOR_PORTAL_URL = "VENDOR_PORTAL_URL";
    public static final String PARAM_VENDOR_USER_NAME = "VENDOR_USERNAME";
    public static final String PAREM_TRIAL_USER = "PAREM_TRIAL_USER";
    public static final String PAREM_TRIAL_USER_EMAIL = "PAREM_TRIAL_USER_EMAIL";
    public static final String PAREM_TRIAL_USER_COMPANY = "PAREM_TRIAL_USER_COMPANY";
    public static final String PAREM_ACCOUNT_NAME = "ACCOUNT_NAME";
    public static final String PAREM_EMAIL_SUBJECT = "EMAIL_SUBJECT";
    public static final String PAREM_EMAIL_CONTENT = "EMAIL_CONTENT";
    public static final String PAREM_REPORT_NAME = "REPORT_NAME";
    public static final String PARAM_INVOICE_NUMBER = "INVOICE_NUMBER";
    public static final String PARAM_EMAIL_ADDRESS = "EMAIL_ADDRESS";
    public static final String PARAM_DOCUMENT_DETAILS = "DOCUMENT_DETAILS";
    public static final String PARAM_PAY_REF = "PAY_REF";
    public static final String PARAM_PAYMENT_PROVIDER = "PAYMENT_PROVIDER";
    public static final String PARAM_FAIL_REASON = "FAIL_REASON";
    public static final String PARAM_REQUESTED_EMAIL = "REQUESTED_EMAIL";
    public static final String PARAM_EMAIL_SUBJECT_PO_NO = "PO_NUMBER";
    public static final String PARAM_MAILED_BY = "MAILED_BY";
    public static final String PARAM_COUNT = "COUNT";
    public static final String PARAM_TENANT_ID = "TENANT_ID";
    public static final String PARAM_CUST_EMAIL = "EMAIL";
    public static final String PARAM_CUST_NAME = "CUSTOMER_NAME";
    public static final String PARAM_EMAIL_FAIL_REASON = "FAIL_REASON";
    public static final String PARAM_EMPLOYEE_NAME = "EMPLOYEE_NAME";
    public static final String PARAM_ACCOUNT_UNLOCK_DURATION = "ACCOUNT_UNLOCK_DURATION";
    public static final String SENDOR_NAME = "SENDOR_NAME";
    public static final String PARAM_EMAIL_CONTENT = "EMAIL_CONTENT";
    public static final String PARAM_DELEGATE_OWNER = "DELE_OWNER";
    public static final String PARAM_DELEGATE_USER_PERMISSION_LIST = "PERMISSION_LIST";
    public static final String PARAM_REFERENCE_NUMBER = "REFERENCE_NUMBER";
    public static final String DOWNLOAD_FILE = "REFERENCE_NUMBER";
    public static final String PARAM_DOCUMENT_TYPE = "DOCUMENT_TYPE";
    public static final String PARAM_TYPE = "TYPE";
    public static final String PARAM_DOCUMENT_NUMBER = "DOCUMENT_NUMBER";
    public static final String PARAM_FILE_NAME = "FILE_NAME";
    public static final String PARAM_FILE_IMPORTED_DATE = "DATE";
    public static final String PARAM_TOTAL_RECORDS = "TOTAL_RECORDS";
    public static final String PARAM_TOTAL_SUCCESS_RECORDS = "TOTAL_SUCCESS_RECORDS";
    public static final String PARAM_TOTAL_FAILED_RECORDS = "TOTAL_FAILED_RECORDS";
    //EMAIL PARAMS----

    //SQL SPECIFIC-----
    public static final String SQL_AND_CONDITION = " AND ";
    public static final String SQL_OR_CONDITION = " OR ";
    public static final String SQL_BRACKET_OPEN = " (";
    public static final String SQL_BRACKET_CLOSE = ")";
    public static final String SQL_GRT_OREQL_CONDITION = " >= ";
    public static final String SQL_LT_OREQL_CONDITION = " <= ";
    public static final String SQL_GRT_THAN = " > ";
    public static final String SQL_LES_THAN = " < ";
    public static final String SQL_EQUAL = " = ";
    public static final String SQL_NOT_EQUAL = " <> ";
    public static final String SQL_IN = " IN ";
    public static final String SQL_IS = " IS ";
    public static final String SQL_LIKE_CLAUSE = " LIKE ";
    public static final String SQL_NOT_LIKE_CLAUSE = " NOT LIKE ";
    public static final String SQL_BETWEEN_CLAUSE = " BETWEEN ";
    public static final String SQL_PERCENTAGE_MARK = "%";
    public static final String LIKE_CONCAT_ANY = " CONCAT('%'," + PARAM_ATTR + ",'%') ";
    public static final String LIKE_CONCAT_ANY_V2 = " CONCAT('%',:" + PARAM_ATTR + ",'%') ";
    public static final String LIKE_CONCAT_START = " CONCAT(:" + PARAM_ATTR + ",'%') ";
    public static final String LIKE_CONCAT_END = " CONCAT('%',:" + PARAM_ATTR + ") ";

    public static final String SQL_WHERE_CONDITION = " WHERE ";
    public static final String SQL_NOTEQL_CONDITION = " <> ";
    public static final String SQL_EQUALS_CONDITION = " = ";
    public static final String SQL_SORT_ASCENDING = " ASC";
    public static final String SQL_SORT_DESCENDING = " DESC";
    public static final String SQL_UPPER = " UPPER(";
    public static final String SQL_IS_NOT_EMPTY = " IS NOT EMPTY ";
    public static final String SQL_IS_EMPTY = " IS EMPTY ";
    public static final String CREATED_ON_STR = "createdOn";
    public static final String START_DATE = "startDate";
    public static final String END_DATE = "endDate";
    public static final String SEARCH_VALUE = "searchValue";
    public static final String SUPPORT_STATUS = "supportStatus";
    public static final String SQL_WHEN_CONDITION = " WHEN ";
    public static final String SQL_THEN_O_ELSE_1_END = " THEN 0 ELSE 1 END ";

    //Content Types
    public static final String CONTENT_DEPOSITION = "Content-disposition";
    public static final String FILE_NAME = "inline; filename=";
    public static final String CONTENT_TYPE_APPLICATION_PDF = "application/pdf";

    //OAuth------------>
    public static final String APPLICATION_IN_APP_CLIENT_TYPE = "${application.oauth-in-app-client-type}";
    public static final String APPLICATION_IN_APP_CLIENT_PASSWORD = "${application.oauth-in-app-client-password}";
    public static final String AUTHORIZATION = "Authorization";
    public static final String BASIC = "Basic ";
    public static final String BEARER = "Bearer ";
    public static final String PRE_REQUEST = "pre";
    public static final String CLIENT_ID = "Client-id";
    public static final String PASSWORD = "Password";
    public static final String SECRET_KEY = "Secret-key";
    public static final String DELEGATE_OWNER = "Delegate-owner";
    //OAuth------------>

    //Discount Term -----
    public static final int DISCOUNT_TERM_OTHER = 10;
    //Discount Term -----

    //Menus
    public static final int PORTAL_DASHBOARD_MENUE = 999;
    public static final int SUB_ACCOUNT_MANUE = 1026;
    //

    //Expense cat id
    public static final Integer PROJECT_EXPENSE_CAT_ID = 2;

    //HTML TAG
    public static final String TD_TAG_START = "<td>";
    public static final String TD_TAG_END = "</td>";
    public static final String TR_TAG_START = "<tr>";
    public static final String TR_TAG_END = "</tr>";
    public static final String B_TAG_START = "<b>";
    public static final String B_TAG_END = "</b>";
    //

    //REPORT PARAMS------------------>
    public static final String DISCOUNT = "DISCOUNT";
    public static final String CONTACT_PERSON = "CONTACT_PERSON";
    public static final String GROSS = "GROSS";
    public static final String FROM_ADDRESS = "FROM_ADDRESS";
    public static final String SHIPPING_ADDRESS = "SHIPPING_ADDRESS";
    public static final String VENDOR = "VENDOR";
    public static final String VENDOR_EMAIL = "VENDOR_EMAIL";
    public static final String VENDOR_ADDRESS = "VENDOR_ADDRESS";
    public static final String BILL_NO_PARAM = "BILL_NO";
    public static final String DOCUMENT_NO_PARAM = "DOCUMENT_NO_PARAM";
    public static final String PO_NUMBER = "PO_NUMBER";
    public static final String FROM_ADDRESS_PARAM = "FROM_ADDRESS";
    public static final String BILL_TERM_PARAM = "BILL_TERM";
    public static final String TO_ADDRESS_PARAM = "TO_ADDRESS";
    public static final String BILLING_ADDRESS = "BILLING_ADDRESS";
    public static final String DATE_SUBMITTED = "DATE_SUBMITTED";
    public static final String TAX = "TAX";
    public static final String BILL_DATE_PARAM = "BILL_DATE";
    public static final String SUBMISSION_NO = "SUBMISSION_NO";
    public static final String COMPANY_NAME = "COMPANY_NAME";
    public static final String SPECIAL_NOTES = "SPECIAL_NOTES";
    public static final String PROJECT_CODE = "PROJECT_CODE";
    public static final String DEPARTMENT_NAME = "DEPARTMENT_NAME";
    public static final String TAX_AMOUNT = "TAX_AMOUNT";
    public static final String TOT_PAID = "TOT_PAID";
    public static final String NET_AMOUNT = "NET_AMOUNT";
    public static final String CONTACT = "CONTACT";
    public static final String SUBMITTED_BY = "SUBMITTED_BY";
    public static final String BILL_DUE_DATE_PARAM = "BILL_DUE_DATE";
    public static final String DATE_OF_DELIVERY = "DATE_OF_DELIVERY";
    public static final String NET = "NET";
    public static final String TOTAL_AMOUNT = "TOTAL_AMOUNT";
    public static final String PO_RECEIPT_DATE = "GRN_DATE";
    public static final String PO_RECEIPT_NO = "GRN_NO";
    public static final String VENDOR_NAME = "VENDOR_NAME";
    public static final String PO_DATE = "PO_DATE";
    public static final String RECEIVED_BY = "RECEIVED_BY";
    public static final String VEHICLE_NO = "VEHICLE_NO";
    public static final String CREATED_BY = "CREATED_BY";
    public static final String NOTES = "NOTES";
    public static final String REPORT_NAME = "REPORT_NAME";
    public static final String BUSINESS_PURPOSE = "BUSINESS_PURPOSE";
    public static final String ADDITIONAL_FIELDS = "ADDITIONAL_FIELDS";
    public static final String PO_ITEM_DETAILS = "PO_ITEM_DETAILS";
    public static final String PO_ACCOUNT_DETAILS = "PO_ACCOUNT_DETAILS";
    public static final String PO_ITEM_GROSS_AMOUNT = "ITEM_GROSS_AMOUNT";
    public static final String PO_ACCOUNT_GROSS_AMOUNT = "ACCOUNT_GROSS_AMOUNT";
    public static final String PO_SUB_TOTAL = "PO_SUB_TOTAL";
    public static final String PO_DEPARTMENT = "PO_DEPARTMENT";
    public static final String BILL_DEPARTMENT = "BILL_DEPARTMENT";
    public static final String CREATED_ON = "CREATED_ON";
    public static final String PO_STATUS = "PO_STATUS";
    public static final String BILL_STATUS = "BILL_STATUS";
    public static final String EXPENSE_COST_DISTRIBUTION_TOTAL = "EXPENSE_COST_DISTRIBUTION_TOTAL"; // DUPLICATE
    public static final String ITEM_COST_DISTRIBUTION_TOTAL = "ITEM_COST_DISTRIBUTION_TOTAL"; //DUPLICATE
    public static final String BILLS_AWAITING_APPROVAL = "BILLS_AWAITING_APPROVAL";
    public static final String PO_AWAITING_APPROVAL = "PO_AWAITING_APPROVAL";
    public static final String EXPENSE_AWAITING_APPROVAL = "EXPENSE_AWAITING_APPROVAL";
    public static final String MERCHANT_NAME = "MERCHANT_NAME";
    public static final String TOTAL_MILES_DRIVEN = "TOTAL_MILES_DRIVEN";
    public static final String TOTAL_MILEAGE_AMOUNT = "TOTAL_MILEAGE_AMOUNT";
    public static final String PARAM_LIST_DATA = "LIST_DATA";
    public static final String LINE_ADD_FIELD_EXIST = "LINE_ADD_FIELD_EXIST";
    public static final String LINE_ADD_FEILD_HEADERS = "LINE_ADD_FEILD_HEADERS";

    //ADDRESS PROPERTIES
    public static final String ADDRESS_LINE_1 = "ADDRESS_LINE_1";
    public static final String ADDRESS_LINE_2 = "ADDRESS_LINE_2";
    public static final String ADDRESS_CITY = "ADDRESS_CITY";
    public static final String ADDRESS_COUNTRY = "ADDRESS_COUNTRY";
    public static final String ADDRESS_STATE = "ADDRESS_STATE";
    public static final String ADDRESS_ZIP_CODE = "ADDRESS_ZIP_CODE";
    public static final String ADDRESS_TIME_ZONE = "ADDRESS_TIME_ZONE";


    public static final String RPT_PARAM_TENANT_NAME = "TENANT_NAME";
    public static final String RPT_PARAM_INVOICE_ID = "INVOICE_ID";
    public static final String RPT_PARAM_GENERATED_DATE = "GENERATE_DATE";
    public static final String RPT_PARAM_APPROVAL_DETAILS = "APPROVAL_DETAILS";
    public static final String RPT_PARAM_AUDIT_TRAIL_DETAILS = "AUDIT_TRAIL_DETAILS";
    public static final String RPT_PARAM_EXPENSE_ID = "EXPENSE_ID";
    public static final String RPT_PARAM_VENDOR_NAME = "VENDOR_NAME";
    public static final String RPT_PARAM_EXPENSE_COST_DISTRIBUTIONS = "EXPENSE_COST_DISTRIBUTIONS";
    public static final String RPR_PARAM_ITEM_COST_DISTRIBUTIONS = "ITEM_COST_DISTRIBUTIONS";
    public static final String RPR_PARAM_EXPENSE_DISTRIBUTION_TOTAL = "EXPENSE_DISTRIBUTION_TOTAL";
    public static final String RPR_PARAM_ITEM_COST_DISTRIBUTION_TOTAL = "ITEM_COST_DISTRIBUTION_TOTAL";
    public static final String RPR_IS_EXPENSE_LEVEL_ADDITIONAL_FIELD_AVAILABLE = "IS_EXPENSE_LEVEL_ADDITIONAL_FIELD_AVAILABLE";
    public static final String RPR_IS_ITEM_LEVEL_ADDITIONAL_FIELD_AVAILABLE = "IS_ITEM_LEVEL_ADDITIONAL_FIELD_AVAILABLE";
    public static final String REP_PARAM_PAYMENT_TERM = "PAYMENT_TERM";
    public static final String RPT_PARAM_BILL_ITEM_DETAILS = "BILL_ITEM_DETAILS";
    public static final String RPT_PARAM_BILL_SUBMITTED_BY_VENDOR = "VENDOR_SUBMITTED";
    public static final String PRT_PARAM_PROFILE_PIC = "PROFILE_PIC";


    public static String STR_PENDING = "Pending";
    public static String STR_ISSUED = "Issued";
    //REPORT PARAMS------------------>

    //MENU_URLS
    public static final String COMPANY_PROFILE_MANAGEMENT = "/home/settings";
    public static final String USER_MANAGEMENT = "/home/admin";
    public static final String CODE_MANAGEMENT = "/home/project-code";
    public static final String VENDOR_MANAGEMENT = "/home/vendor";

    //PAPERTRL SUPPORT ATTACHMENT TYPE
    public static final Integer ATTACHMENT_TYPE_RFP = 1;
    public static final Integer ATTACHMENT_TYPE_FINAL_PROP = 2;
    public static final Integer ATTACHMENT_TYPE_AMENDMENT = 3;
    public static final Integer ATTACHMENT_TYPE_DEBRIEF = 4;
    public static final Integer ATTACHMENT_TYPE_AWARD_LETTER = 5;
    public static final Integer ATTACHMENT_FINAL_REPORT = 6;
    public static final Integer ATTACHMENT_TYPE_SIGNED_CONTRACT = 7;
    public static final Integer ATTACHMENT_TYPE_CPARS = 8;
    public static final Integer ATTACHMENT_TYPE_EXPENSE_RECEIPT = 10;
    public static final Integer ATTACHMENT_TYPE_EXPENSE_GENERIC_ATTACHMENT = 11;
    public static final Integer ATTACHMENT_TYPE_EXPENSE = 12;
    public static final Integer ATTACHMENT_TYPE_EXPENSE_EXPENSE_REPORT = 11;
    public static final Integer ATTACHMENT_TYPE_PURCHASE_ORDER = 13;
    public static final Integer ATTACHMENT_TYPE_PO_RECEIPT_ATTACHMENT = 14;
    public static final Integer ATTACHMENT_TYPE_OTHER = 20;
    public static final Integer ATTACHMENT_TYPE_BILL = 21;
    public static final Integer ATTACHMENT_TYPE_DIVERSE_SUPPLIER = 22;
    public static final Integer ATTACHMENT_TYPE_RECURRING_BILL_ATTACHMENT = 23;
    public static final Integer ATTACHMENT_TYPE_PAYMENT = 24;
    public static final Integer ATTACHMENT_TYPE_VENDOR = 25;
    public static final Integer ATTACHMENT_TYPE_VENDOR_W9_ATTACHMENT = 26;
    public static final Integer ATTACHMENT_TYPE_OCR_TEMPLATE_ATTACHMENT = 27;
    public static final Integer ATTACHMENT_TYPE_INBOX_ATTACHMENT = 28;
    public static final Integer ATTACHMENT_TYPE_VENDOR_ITEM_ATTACHMENT = 29;
    public static final Integer ATTACHMENT_TYPE_ITEM_CATALOG_ATTACHMENT = 30;
    public static final Integer ATTACHMENT_TYPE_CREDIT_NOTE = 31;
    public static final Integer ATTACHMENT_TYPE_SUPPORT_TICKET = 32;
    public static final Integer ATTACHMENT_TYPE_CREDIT_CARD = 33;
    public static final Integer ATTACHMENT_TYPE_CUSTOMER_INVOICE = 34;
    public static final Integer EMAIL_TEMPLATE_UNATTENDED_PAYMENT = 57;
    public static final Integer UNATTENDED_PAYMENT_FILE_FAIL = 77;
    public static final Integer FAILED_PAYMENT_NOTIFICATION = 79;
    public static final Integer FAILED_PAYMENT_NOTIFICATION_SUPPORT = 80;
    public static final Integer API_INTEGRATION_PAYMENT_REQUEST_ERROR = 81;


    //PAPERTRL SUPPORT ATTACHMENT TYPE
    public static final int CUSTOMER_COLUMN_INDX = 1;

    //Regex Pattern
    public static final String QUESTION_SPLITTER = "\\?(?!\\?)";
    public static final String SPACE_REGEX = "\\s";
    public static final String BOX_BRACKET_SPLITTER = "\\[|\\]|";
    public static final String ADDITIONAL_FIELD_OPTION_VALUE_REGEX = "[^a-zA-Z0-9, ]";
    public static final String NUMBER_REGEX = "[0-9.]+";

    public static final String NUMBER_REGEX_WITH_STAR_MARK = "^[0-9*]+$";
    private static final String NUMBER_PATTERN_STRING = "\\d+";
    public static final Pattern NUMBER_PATTERN = Pattern.compile(NUMBER_PATTERN_STRING);
    private static final String DECIMAL_PATTERN_STRING = "^-?[0-9]\\d*(\\.\\d+)?$";
    public static final Pattern DECIMAL_PATTERN = Pattern.compile(DECIMAL_PATTERN_STRING);
    public static final String REGEX_PATTERN_ALPHANUMERIC_FULL_STR = "^[a-zA-Z0-9]+$";
    //Regex Pattern

    //COMMON UOM---------------------->
    public static final int HR_UOM = 1;
    public static final int EA_UOM = 2;
    //COMMON UOM---------------------->

    //PORTAL PARAMS
    public static final String PORTAL_TENANT = "-portal";
    public static final String ACCOUNT_STRING = "/ account - ";
    //

    //---------------- Sync Object Type Specific --------------------------
    public static final String OBJECT_TYPE_ACCOUNT = "OBJECT_TYPE_ACCOUNT";
    public static final String OBJECT_TYPE_ITEM = "OBJECT_TYPE_ITEM";

    public static final String OBJECT_TYPE_ITEM_INVENTORY = "OBJECT_TYPE_ITEM_INVENTORY";
    public static final String OBJECT_TYPE_ITEM_NON_INVENTORY = "OBJECT_TYPE_ITEM_NON_INVENTORY";
    public static final String OBJECT_TYPE_ITEM_SERVICE = "OBJECT_TYPE_ITEM_SERVICE";
    public static final String OBJECT_TYPE_ITEM_OTHER = "OBJECT_TYPE_ITEM_OTHER";
    public static final String CHECK_PAYMENT_OBJECT = "CHECK_PAYMENT_OBJECT";
    public static final String CARD_PAYMENT_OBJECT = "CARD_PAYMENT_OBJECT";
    public static final String OBJECT_TYPE_UOM = "OBJECT_TYPE_UOM";
    public static final String OBJECT_TYPE_PO_RECEIPT = "OBJECT_TYPE_PO_RECEIPT";

    public static final String OBJECT_TYPE_PO = "OBJECT_TYPE_PO";
    public static final String OBJECT_TYPE_EXPENSE = "OBJECT_TYPE_EXPENSE";
    public static final String OBJECT_TYPE_VENDOR = "OBJECT_TYPE_VENDOR";
    public static final String OBJECT_TYPE_PROJECT = "OBJECT_TYPE_PROJECT";
    public static final String OBJECT_TYPE_BILL = "OBJECT_TYPE_BILL";
    public static final String OBJECT_TYPE_BILL_PAYMENT = "OBJECT_TYPE_BILL_PAYMENT";

    public static final String OBJECT_TYPE_PAYMENT = "OBJECT_TYPE_PAYMENT";

    public static final String OBJECT_TYPE_CHECK_PAYMENT = "OBJECT_TYPE_CHECK_PAYMENT";

    public static final String OBJECT_TYPE_CARD_PAYMENT = "OBJECT_TYPE_CARD_PAYMENT";

    public static final String OBJECT_TYPE_TERM = "OBJECT_TYPE_TERM";
    public static final String OBJECT_TYPE_ATTACHMENT = "OBJECT_TYPE_ATTACHMENT";
    public static final String OBJECT_TYPE_ITEM_CATEGORY = "OBJECT_TYPE_ITEM_CATEGORY";
    public static final String OBJECT_TYPE_DEPARTMENT = "OBJECT_TYPE_DEPARTMENT";
    public static final String OBJECT_TYPE_ADDITIONAL_FIELD = "OBJECT_TYPE_ADDITIONAL_FIELD";
    public static final String OBJECT_TYPE_ADDITIONAL_FIELD_OPTION = "OBJECT_TYPE_ADDITIONAL_FIELD_OPTION";
    public static final String OBJECT_TYPE_PAYMENT_REQUEST = "OBJECT_TYPE_PAYMENT_REQUEST";
    public static final String OBJECT_TYPE_CREDIT_NOTE = "OBJECT_TYPE_CREDIT_NOTE";

    //COMMON DOCUMENT TYPE ID, EVENT ID AND FIELD ID#############################################
    public static final int DOCUMENT_TYPE_BILL = 1;
    public static final int DOCUMENT_TYPE_PO = 2;
    public static final int DOCUMENT_TYPE_PO_RECEIPT = 3;
    public static final int DOCUMENT_TYPE_EXPENSE = 4;
    public static final int DOCUMENT_TYPE_BILL_PAYMENT = 5;
    public static final int DOCUMENT_TYPE_VENDOR = 6;
    public static final int DOCUMENT_TYPE_CREDIT_NOTE = 8;
    public static final int DOCUMENT_TYPE_CREDIT_CARD = 9;
    public static final int DOCUMENT_TYPE_BATCH_PAYMENT = 10;
    public static final int DOCUMENT_TYPE_EXPENSE_PAYMENT = 11;
    public static final int DOCUMENT_TYPE_PAYMENT_REQUEST = 12;
    public static final int DOCUMENT_TYPE_INTEGRATED_PAYMENT = 13;
    public static final int DOCUMENT_TYPE_COMMON_FOR_PAYMENT = 70;

    public static final int DOCUMENT_EVENT_SUBMITTED = 1;
    public static final int DOCUMENT_EVENT_EDIT_RESUBMIT = 2;
    public static final int DOCUMENT_EVENT_APPROVED = 3;
    public static final int DOCUMENT_EVENT_REJECTED = 4;
    public static final int DOCUMENT_EVENT_SKIP_APPROVAL = 5;
    public static final int DOCUMENT_EVENT_DELETED = 6;
    public static final int DOCUMENT_EVENT_SEND_TO_VENDOR = 7;
    public static final int DOCUMENT_EVENT_CREATED = 8;
    public static final int DOCUMENT_EVENT_UPLOADED = 9;
    public static final int DOCUMENT_EVENT_REASSIGNED = 10;
    public static final int DOCUMENT_EVENT_VOID = 11;
    public static final int DOCUMENT_EVENT_MARK_AS_MAILED = 12;
    public static final int DOCUMENT_EVENT_APPLY_TO_DIFFERENT_BILL = 13;
    public static final int DOCUMENT_EVENT_SAVE_AS_APPROVED = 14;
    public static final int DOCUMENT_FIELD_STATUS = 74;
    public static final int DOCUMENT_EVENT_CREDIT_NOTE_SUBMITTED = 25;
    public static final int DOCUMENT_EVENT_CREDIT_NOTE_DELETED = 26;
    public static final int DOCUMENT_EVENT_CREDIT_NOTE_CANCELED = 27;
    public static final int DOCUMENT_EVENT_CREDIT_CARD_TRANSACTION_ASSIGNED = 28;
    public static final int DOCUMENT_EVENT_PAY_NOW = 17;
    public static final int DOCUMENT_EVENT_PAYMENT_SUCCESS = 18;
    public static final int DOCUMENT_EVENT_PAYMENT_FAIL = 19;
    public static final int DOCUMENT_EVENT_PAYMENT_CANCEL = 20;
    public static final int DOCUMENT_EVENT_CREDIT_CARD_RECEIPT_UPLOADED_BY_SOMEONE = 35;
    public static final int DOCUMENT_EVENT_SAVE_AS_DRAFT = 36;
    public static final int DOCUMENT_EVENT_EDIT = 37;
    public static final int DOCUMENT_EVENT_OPEN = 38;
    public static final int DOCUMENT_EVENT_CLOSE = 39;

    public static final int AUTOMATION_ACTION_CREATE_APPROVAL_WORKFLOW = 1;
    public static final int AUTOMATION_ACTION_SEND_EMAIL_NOTIFICATION = 2;
    public static final int AUTOMATION_ACTION_SEND_USER_NOTIFICATION = 3;
    public static final int AUTOMATION_ACTION_SET_FIELD_VALUE = 4;
    public static final int AUTOMATION_ACTION_EXECUTE_AUTOMATION = 5;
    public static final int AUTOMATION_ACTION_SYNC_WITH_THIRD_PARTY = 6;
    public static final int AUTOMATION_ACTION_ASSIGN_TO = 7;
    public static final int AUTOMATION_ACTION_ASSIGN_FINAL_APPROVAL_USER = 8;

    public static final int FIELD_PO_SAVE_AS_APPROVED = 73;
    public static final int FIELD_BILL_SAVE_AS_APPROVED = 74;
    public static final int FIELD_EXPENSE_SAVE_AS_APPROVED = 133;
    public static final int FIELD_BILL_APPROVAL_USER = 103;

    public static final int PAYMENT_MODULE_NUMBER = 1;

    // VENDOR ATTACHMENT DROPDOWN DATA ####################################
    public static final int VENDOR_W9_ATTACHMENT_TYPE = 1;
    public static final String VENDOR_W9_ATTACHMENT_TYPE_NAME = "W9 Attachment";
    public static final int VENDOR_CLASSIFICATION_ATTACHMENT_TYPE = 2;
    public static final String VENDOR_CLASSIFICATION_ATTACHMENT_TYPE_NAME = "Vendor Classification Attachment";
    //COMMON DOCUMENT TYPE ID#############################################

    //---------------- Upload File Type Specific --------------------------
    public static final String FILE_TYPE_PDF = "pdf";
    public static final String FILE_TYPE_UNDEFINED = "undefined";
    public static final String FILE_TYPE_PNG = "png";
    public static final String FILE_TYPE_JPG = "jpg";
    public static final String FILE_TYPE_JPEG = "jpeg";
    public static final String FILE_TYPE_XLSX = "xlsx";
    public static final String FILE_TYPE_CSV = "csv";
    public static final List<String> IMAGE_FILE_TYPES = Arrays.asList(FILE_TYPE_PNG, FILE_TYPE_JPG, FILE_TYPE_JPEG);
    //    excel mime types
    public static final String MEDIA_TYPE_XLSX = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    public static final String MEDIA_TYPE_XLSB = "application/vnd.ms-excel.sheet.binary.macroEnabled.12";
    public static final String MEDIA_TYPE_XLS = "application/vnd.ms-excel";
    public static final String MEDIA_TYPE_XLSM = "application/vnd.ms-excel.sheet.macroEnabled.12";

    public static final String MEDIA_TYPE_JPG = "image/jpg";
    public static final String MEDIA_TYPE_PNG = "image/png";
    public static final String MEDIA_TYPE_JPEG = "image/jpeg";
    //    word mime types
    public static final String MEDIA_TYPE_DOCX = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
    public static final String MEDIA_TYPE_DOC = "application/msword";
    public static final String MEDIA_TYPE_DOCM = "application/vnd.ms-word.document.macroEnabled.12";
    public static final String MEDIA_TYPE_DOTX = "application/vnd.openxmlformats-officedocument.wordprocessingml.template";
    public static final String MEDIA_TYPE_DOTM = "application/vnd.ms-word.template.macroEnabled.12";
    public static final String FILE_TYPE_INVALID = "Invalid image format, PNG, JPG and JPEG are the formats supports at here.";
    public static final String IMAGE_FILE_CORRUPTED = "The uploaded image file is corrupted.";
    public static final String IMAGE_FILE_TOO_LARGE = "The uploaded image file is too large.";
    public static final String INVALID_IMAGE_DIMENSIONS = "The uploaded image file does not meet the required dimensions.";

    // image file size specific
    public static final int PROFILE_PICTURE_MAX_SIZE_MEGABYTES = 1;

    public static final Integer INVENTORY_ITEM_TYPE = 1;
    public static final Integer NON_INVENTORY_ITEM_TYPE = 2;
    public static final Integer SERVICE_ITEM_TYPE = 3;
    public static final Integer OTHER_ITEM_TYPE = 4;

    public static final Integer ACCOUNT_PUSH_SERVICE_ID = 1;
    public static final Integer ACCOUNT_PULL_SERVICE_ID = 2;
    public static final Integer UOM_PULL_SERVICE_ID = 3;
    public static final Integer UOM_PUSH_SERVICE_ID = 4;
    public static final Integer ITEM_PUSH_SERVICE_ID = 5;
    public static final Integer ITEM_PULL_SERVICE_ID = 6;
    public static final Integer CLIENT_PUSH_SERVICE_ID = 7;
    public static final Integer CLIENT_PULL_SERVICE_ID = 8;
    public static final Integer TERM_PUSH_SERVICE_ID = 9;
    public static final Integer TERM_PULL_SERVICE_ID = 10;
    public static final Integer VENDOR_PUSH_SERVICE_ID = 11;
    public static final Integer VENDOR_PULL_SERVICE_ID = 12;
    public static final Integer PURCHASE_ORDER_PUSH_SERVICE_ID = 13;
    public static final Integer PURCHASE_ORDER_PULL_SERVICE_ID = 14;
    public static final Integer INVOICE_PUSH_SERVICE_ID = 15;
    public static final Integer INVOICE_PULL_SERVICE_ID = 16;
    public static final Integer PURCHASE_ORDER_RECIPT_PUSH_SERVICE_ID = 17;
    public static final Integer PURCHASE_ORDER_RECIPT_PULL_SERVICE_ID = 18;
    public static final Integer PAYMENT_PUSH_SERVICE_ID = 19;
    public static final Integer PAYMENT_PULL_SERVICE_ID = 20;
    public static final Integer DELETES_PUSH_SERVICE_ID = 21;
    public static final Integer DELETES_PULL_SERVICE_ID = 22;
    public static final Integer EXPENSE_PUSH_SERVICE_ID = 23;
    public static final Integer ITEM_CATEGORY_PULL_SERVICE_ID = 24;
    public static final Integer ITEM_CATEGORY_PUSH_SERVICE_ID = 25;
    public static final Integer DEPARTMENT_PULL_SERVICE_ID = 27;
    public static final Integer DEPARTMENT_PUSH_SERVICE_ID = 26;
    public static final Integer ADDITIONAL_FIELD_PUSH_SERVICE_ID = 28;
    public static final Integer ADDITIONAL_FIELD_PULL_SERVICE_ID = 29;
    public static final Integer PAYMENT_REQUEST_PULL_SERVICE_ID = 30;
    public static final Integer PAYMENT_REQUEST_PUSH_SERVICE_ID = 31;
    public static final Integer CREDIT_NOTE_PULL_SERVICE_ID = 32;
    public static final Integer CREDIT_NOTE_PUSH_SERVICE_ID = 33;


    public static final HashMap<String, Integer> OBJECT_TYPE_PUSH_ID_MAP = new HashMap<>();
    public static final String TENANT_STRING = "tenantId";
    public static final String SUPPORT_STRING = "support";

    static {
        OBJECT_TYPE_PUSH_ID_MAP.put(OBJECT_TYPE_ACCOUNT, ACCOUNT_PUSH_SERVICE_ID);
        OBJECT_TYPE_PUSH_ID_MAP.put(OBJECT_TYPE_UOM, UOM_PUSH_SERVICE_ID);
        OBJECT_TYPE_PUSH_ID_MAP.put(OBJECT_TYPE_ITEM, ITEM_PUSH_SERVICE_ID);
        OBJECT_TYPE_PUSH_ID_MAP.put(OBJECT_TYPE_PROJECT, CLIENT_PUSH_SERVICE_ID);
        OBJECT_TYPE_PUSH_ID_MAP.put(OBJECT_TYPE_TERM, TERM_PUSH_SERVICE_ID);
        OBJECT_TYPE_PUSH_ID_MAP.put(OBJECT_TYPE_VENDOR, VENDOR_PUSH_SERVICE_ID);
        OBJECT_TYPE_PUSH_ID_MAP.put(OBJECT_TYPE_PO, PURCHASE_ORDER_PUSH_SERVICE_ID);
        OBJECT_TYPE_PUSH_ID_MAP.put(OBJECT_TYPE_BILL, INVOICE_PUSH_SERVICE_ID);
        OBJECT_TYPE_PUSH_ID_MAP.put(OBJECT_TYPE_PO_RECEIPT, PURCHASE_ORDER_RECIPT_PUSH_SERVICE_ID);
        OBJECT_TYPE_PUSH_ID_MAP.put(OBJECT_TYPE_EXPENSE, EXPENSE_PUSH_SERVICE_ID);
        OBJECT_TYPE_PUSH_ID_MAP.put(OBJECT_TYPE_ITEM_CATEGORY, ITEM_CATEGORY_PUSH_SERVICE_ID);
        OBJECT_TYPE_PUSH_ID_MAP.put(OBJECT_TYPE_DEPARTMENT, DEPARTMENT_PUSH_SERVICE_ID);
        OBJECT_TYPE_PUSH_ID_MAP.put(OBJECT_TYPE_ADDITIONAL_FIELD, ADDITIONAL_FIELD_PUSH_SERVICE_ID);
        OBJECT_TYPE_PUSH_ID_MAP.put(OBJECT_TYPE_ADDITIONAL_FIELD_OPTION, ADDITIONAL_FIELD_PUSH_SERVICE_ID);
        OBJECT_TYPE_PUSH_ID_MAP.put(OBJECT_TYPE_BILL_PAYMENT, PAYMENT_PUSH_SERVICE_ID);
        OBJECT_TYPE_PUSH_ID_MAP.put(OBJECT_TYPE_PAYMENT, PAYMENT_PUSH_SERVICE_ID);
        OBJECT_TYPE_PUSH_ID_MAP.put(OBJECT_TYPE_CHECK_PAYMENT, PAYMENT_PUSH_SERVICE_ID);
        OBJECT_TYPE_PUSH_ID_MAP.put(OBJECT_TYPE_CARD_PAYMENT, PAYMENT_PUSH_SERVICE_ID);
        OBJECT_TYPE_PUSH_ID_MAP.put(OBJECT_TYPE_PAYMENT_REQUEST, PAYMENT_REQUEST_PUSH_SERVICE_ID);
        OBJECT_TYPE_PUSH_ID_MAP.put(OBJECT_TYPE_CREDIT_NOTE, CREDIT_NOTE_PUSH_SERVICE_ID);

    }

    public static final String QB_DATE_FORMAT = "yyyy-MM-dd";

    public static final Integer PROJECT_CODE_CATEGORY_ID = 2;
    public static final Integer INTEGRATION_SYSTEM_TYPE_ACCOUNT = 1;
    public static final Integer INTEGRATION_SERVICE_TYPE_PULL = 2;
    public static final Integer INTEGRATION_SERVICE_TYPE_PUSH = 1;

    //ADDITIONAL FIELD---------------------->
    public static final int DETAIL_SECTION_ID = 1;
    public static final int LINE_ITEM_SECTION_ID = 2;
    public static final int ITEM_COST_DISTRIBUTION_SECTION_ID = 3;
    public static final int EXPENSE_COST_DISTRIBUTION_SECTION_ID = 4;
    public static final int ACCOUNT_DETAIL_SECTION = 9;

    public static final int DEFAULT_ADDITIONAL_FIELD_DISPLAY_ORDER = 50;

    //ADITIONAL FIELD SECTION IDS IN VENDOR ---------->
    public static final int SECTION_ID_BASIC_INFO = 5;
    public static final int SECTION_ID_POSTAL_ADDRESS = 6;
    public static final int SECTION_ID_REMIT_ADDRESS = 7;
    public static final int SECTION_ID_W9_INFO = 8;
    public static final int SECTION_ID_PAYMENT_INFO = 10;

    //ADDITIONAL FIELD SECTION IDS OF PO
    public static final int ACCOUNT_SECTION_ID = 9;

    public static final int ADDITIONAL_FIELD_TYPE_TEX_BOX = 1;
    public static final int ADDITIONAL_FIELD_TYPE_DATE = 2;
    public static final int ADDITIONAL_FIELD_TYPE_DROP_DOWN = 3;
    public static final int ADDITIONAL_FIELD_TYPE_TEXT_AREA = 4;
    public static final int ADDITIONAL_FIELD_TYPE_LABEL = 5;
    public static final int ADDITIONAL_FIELD_TYPE_FILE = 6;
    public static final int ADDITIONAL_FIELD_TYPE_RADIO_BUTTON = 7;
    public static final int ADDITIONAL_FIELD_TYPE_CHECKBOX_BUTTON = 8;

    public static final int ADDITIONAL_FIELD_MAX_INPUT_LENGTH = 1000;
    //ADDITIONAL FIELD---------------------->

    //AUTOMATION CONDITION IDS--------------------------->
    public static final int AUTOMATION_CONDITION_EQUALS = 1;
    public static final int AUTOMATION_CONDITION_NOT_EQUALS = 2;
    public static final int AUTOMATION_CONDITION_GREATER_THAN = 3;
    public static final int AUTOMATION_CONDITION_GREATER_THAN_OR_EQUAL = 4;
    public static final int AUTOMATION_CONDITION_LESS_THAN = 5;
    public static final int AUTOMATION_CONDITION_LESS_THAN_OR_EQUAL = 6;
    public static final int AUTOMATION_CONDITION_CONTAINS = 7;
    public static final int AUTOMATION_CONDITION_BETWEEN = 8;
    public static final int AUTOMATION_CONDITION_IS_EMPTY = 9;
    public static final int AUTOMATION_CONDITION_NOT_EMPTY = 10;
    public static final List<Integer> AUTOMATION_VENDOR_FIELD_IDS = Arrays.asList(3, 19, 34);

    //AUTOMATION EVENT IDS--------------------->>
    public static final int AUTOMATION_EVENT_SUBMIT = 1;
    public static final int AUTOMATION_EVENT_ENTER_EXPENSE_LINE_DESCRIPTION = 15;
    public static final int AUTOMATION_EVENT_ENTER_ITEM_LINE_DESCRIPTION = 16;

    //AUTOMATION DOCUMENT TYPE IDS--------------------->>
    public static final int AUTOMATION_DOCUMENT_TYPE_PO = 2;
    public static final int AUTOMATION_DOCUMENT_TYPE_BILL = 1;

    //---------------- Product Type Specific ------------------------------
    public static final String PRODUCT_TYPE_INVENTORY = "Inventory";

    //---------------- Sync Object Type Specific --------------------------
    //CSV File Headers
    public static List<String> PAYMENT_IMPORT_CSV_FILE_HEADER = new ArrayList<>(
            Arrays.asList(new String[]{"Vendor", "Vendor Code", "Payment Method", "Invoice Number", "Invoice Date", "Payment Date", "Payment Amount", "Payment Reference"}));

    //Vendor tenant id
    public static final String VENDOR_COMMUNITY_TENANT_ID = "${application.vendor-tenant-id}";

    //Automation email template
    public static final String AUTOMATION_EMAIL_TEMPLATE = "${application.email.automation.automation-email-template}";

    //TP INTEGRATION SPECIFIC
    public static final String CONTENT_TYPE_JSON = "application/json";
    public static final String NO_CACHE = "no-cache";

    //------------------- SYSTEM PROPERTY --------------------------
    public static final String SYSTEM_PASSWORD_RESET_URL_PROPERTY = "application.password-reset-url";
    public static final String TRIAL_VERIFY_URL_PROPERTY = "application.email-verification-url";

    //COMMON CHART OF ACCOUNT#############################################
    public static final String CHART_OF_ACCOUNT_TEMPLATE = "${application.chart-of-accountlist-template-path}";
    public static final String CHART_OF_ACCOUNT_TEMPLATE_TYPE = "${application.chart-of-accountlist-template-type}";
    //COMMON CHART OF ACCOUNT#############################################

    //NOTIFICATION SUBSCRIPTION ##########################################
    public static final String MESSAGE_SERVICE_LOCAL_URL = "${application.local-service.message-service-url}";
    public static final String COMMON_SERVICE_LOCAL_URL = "${application.local-service.common-service-url}";
    public static final String VENDOR_SERVICE_LOCAL_URL = "${application.local-service.vendor-service-url}";
    public static final String USER_SERVICE_LOCAL_URL = "${application.local-service.user-service-url}";
    public static final String AUTH_SERVICE_LOCAL_URL = "${application.local-service.auth-service-url}";
    public static final String TENANT_SERVICE_LOCAL_URL = "${application.local-service.tenant-service-url}";
    //NOTIFICATION SUBSCRIPTION ##########################################

    //------------------- BILL EMAIL TEMPLATE PROPERTY ---------------------
    public static final Integer EMAIL_TEMPLATE_BILL_SUBMITTED_TO_DIRECT_APPROVER = 1;
    public static final Integer EMAIL_TEMPLATE_BILL_APPROVED = 2;
    public static final Integer EMAIL_TEMPLATE_BILL_REJECTED = 3;
    public static final Integer EMAIL_TEMPLATE_BILL_SUBMITTED_TO_APPROVAL_GROUP = 11;
    public static final Integer EMAIL_TEMPLATE_BILL_ASSIGNEE_LIST_EMPTY = 12;
    public static final Integer EMAIL_TEMPLATE_EXPENSE_SUBMITTED_TO_APPROVAL_GROUP = 16;
    public static final Integer EMAIL_TEMPLATE_EXPENSE_ASSIGNEE_LIST_EMPTY = 17;
    public static final Integer EMAIL_TEMPLATE_BULK_BILL_REJECTED = 18;
    public static final Integer EMAIL_TEMPLATE_BILL_SUBMITTED_TO_OVERRIDE_PRIVILEGED_USERS = 58;
    //------------------- BILL EMAIL TEMPLATE PROPERTY ---------------------

    //------------------- EXPENSE EMAIL TEMPLATE PROPERTY ---------------------
    public static final Integer EMAIL_TEMPLATE_EXPENSE_SUBMITTED_TO_DIRECT_APPROVER = 4;
    public static final Integer EMAIL_TEMPLATE_EXPENSE_APPROVED = 5;
    public static final Integer EMAIL_TEMPLATE_EXPENSE_REJECTED = 6;
    public static final Integer EMAIL_TEMPLATE_BULK_EXPENSE_REJECTED = 19;
    public static final Integer EMAIL_TEMPLATE_EXPENSE_SUBMITTED_TO_OVERRIDE_PRIVILEGED_USERS = 60;
    //------------------- EXPENSE EMAIL TEMPLATE PROPERTY ---------------------

    //------------------- PO EMAIL TEMPLATE PROPERTY ---------------------
    public static final Integer EMAIL_TEMPLATE_PO_SUBMITTED_TO_DIRECT_APPROVER = 7;
    public static final Integer EMAIL_TEMPLATE_PO_APPROVED = 8;
    public static final Integer EMAIL_TEMPLATE_PO_REJECTED = 9;
    public static final Integer EMAIL_TEMPLATE_PO_SUBMITTED_TO_APPROVAL_GROUP = 13;
    public static final Integer EMAIL_TEMPLATE_PO_ASSIGNEE_LIST_EMPTY = 14;
    public static final Integer EMAIL_TEMPLATE_BULK_PO_REJECTED = 20;
    public static final Integer EMAIL_TEMPLATE_PO_SUBMITTED_TO_OVERRIDE_PRIVILEGED_USERS = 59;
    //------------------- PO EMAIL TEMPLATE PROPERTY ---------------------

    //------------------- PAYMENT EMAIL TEMPLATE PROPERTY ---------------------
    public static final Integer EMAIL_TEMPLATE_PAYMENT_SUBMITTED_TO_DIRECT_APPROVER = 23;
    public static final Integer EMAIL_TEMPLATE_PAYMENT_SUBMITTED_TO_APPROVAL_GROUP = 24;
    public static final Integer EMAIL_TEMPLATE_PAYMENT_APPROVED = 25;
    public static final Integer EMAIL_TEMPLATE_PAYMENT_REJECTED = 26;
    public static final Integer EMAIL_TEMPLATE_PAYMENT_ASSIGNEE_LIST_EMPTY = 27;
    public static final Integer EMAIL_TEMPLATE_PAYMENT_SUCCESSFULLY_COMPLETED = 28;
    public static final Integer EMAIL_TEMPLATE_PAYMENT_FAILED = 29;
    public static final Integer EMAIL_TEMPLATE_SUPPORT_TEAM_EMAIL = 30;
    public static final Integer EMAIL_TEMPLATE_PAYMENT_SUBMITTED_TO_OVERRIDE_PRIVILEGED_USERS = 62;

    //------------------- PAYMENT EMAIL TEMPLATE PROPERTY ---------------------


    //------------------- VENDOR EMAIL TEMPLATE ----------------------
    public static final Integer EMAIL_TEMPLATE_INVOICE_APPROVED = 1;
    public static final Integer EMAIL_TEMPLATE_INVOICE_REJECTED = 2;
    public static final Integer EMAIL_TEMPLATE_SUBMITTED_TO_VENDOR_APPROVER = 3;
    public static final Integer EMAIL_TEMPLATE_BULK_INVOICE_REJECTED = 4;
    public static final Integer EMAIL_TEMPLATE_VENDOR_REQUEST_APPROVED = 5;
    public static final Integer EMAIL_TEMPLATE_VENDOR_REQUEST_REJECTED = 6;
    public static final Integer EMAIL_TEMPLATE_VENDOR_REQUEST_DELETED = 7;
    public static final Integer EMAIL_TEMPLATE_VENDOR_REQUEST_FOR_APPROVAL = 21;
    public static final Integer EMAIL_TEMPLATE_SEND_PO_ATTACHMENT_TO_VENDOR = 32;
    public static final Integer VENDOR_SYNC_ACTIVE = 1;
    public static final Integer VENDOR_SYNC_INACTIVE = 0;
    //------------------- VENDOR EMAIL TEMPLATE ----------------------

    //------------------- NON SUBSCRIPTION EMAIL -------------------------
    public static final Integer EMAIL_TEMPLATE_AUTOMATION = 22;
    public static final Integer EMAIL_TEMPLATE_USER_ACCOUNT_LOCKED = 44;
    public static final Integer EMAIL_TEMPLATE_VENDOR_USER_ACCOUNT_LOCKED = 8;
    //------------------- NON SUBSCRIPTION EMAIL -------------------------

    //------------------- INBOX EMAIL TEMPLATE ---------------------------
    public static final Integer EMAIL_TEMPLATE_CUSTOMER_EMAIL_CHANGE_REQUEST = 31;
    public static final Integer EMAIL_TEMPLATE_CUSTOMER_REQUEST_TO_CONFIGURATION_EMAIL = 45;
    //--------------------------------------------------------------------

    //------------------- DELEGATE USER RELATED EMAIL TEMPLATE ---------------------
    public static final Integer DELEGATE_USER_CREATED_EMAIL_TEMPLATE = 46;
    public static final Integer DELEGATE_USER_BILL_SUBMITTED_TO_DIRECT_APPROVER_EMAIL_TEMPLATE = 47;
    public static final Integer DELEGATE_USER_BILL_SUBMITTED_TO_APPROVAL_GROUP_EMAIL_TEMPLATE = 48;
    public static final Integer DELEGATE_USER_PO_SUBMITTED_TO_DIRECT_APPROVER_EMAIL_TEMPLATE = 49;
    public static final Integer DELEGATE_USER_PO_SUBMITTED_TO_APPROVAL_GROUP_EMAIL_TEMPLATE = 50;
    public static final Integer DELEGATE_USER_PAYMENT_SUBMITTED_TO_DIRECT_APPROVER_EMAIL_TEMPLATE = 51;
    public static final Integer DELEGATE_USER_PAYMENT_SUBMITTED_TO_APPROVAL_GROUP_EMAIL_TEMPLATE = 52;
    public static final Integer DELEGATE_USER_SUBMITTED_EXPENSE_REPORT_APPROVED_EMAIL_TEMPLATE = 53;
    public static final Integer DELEGATE_USER_SUBMITTED_EXPENSE_REPORT_REJECTED_EMAIL_TEMPLATE = 54;
    public static final Integer DELEGATE_USER_SUBMITTED_CREDIT_CARD_TRANSACTION_APPROVED_EMAIL_TEMPLATE = 55;
    public static final Integer DELEGATE_USER_SUBMITTED_CREDIT_CARD_TRANSACTION_REJECTED_EMAIL_TEMPLATE = 56;
    //-------------------------------------------------------------------------------

    //------------------- VENDOR ACH PAYMENT REQUEST TEMPLATE ---------------------
    public static final Integer VENDOR_ACH_PAYMENT_DETAILS_REQUEST_TEMPLATE = 64;
    public static final Integer TEMPLE_ISD_VENDOR_ACH_PAYMENT_DETAILS_REQUEST_TEMPLATE = 84;
    public static final Integer VENDOR_ACH_PAYMENT_DETAILS_REQUEST_EXPIRED_TEMPLATE = 65;
    public static final Integer VENDOR_ACH_PAYMENT_DETAILS_SUCCESSFULLY_SUBMITTED_TEMPLATE = 66;
    public static final Integer VENDOR_ACH_PAYMENT_DETAILS_SUCCESSFULLY_SUBMITTED_USER_TEMPLATE = 83;
    public static final Integer VENDOR_ACH_PAYMENT_DETAILS_REQUEST_PATH_FOR_COMMUNITY_TEMPLATE = 9;
    //-----------------------------------------------------------------------------

    public static final Integer AUTH0_USER_MIGRATION_SUCCESS_TEMPLATE = 85;

    //------------------- CREDIT CARD STATEMENT EMAIL -------------------------
    public static final Integer EMAIL_TEMPLATE_TRANSACTION_SUBMITTED = 33;
    public static final Integer EMAIL_TEMPLATE_NO_USERS_FOUND = 34;
    public static final Integer EMAIL_TEMPLATE_TRANSACTION_SUBMITTED_TO_APPROVAL_GROUP = 35;
    public static final Integer EMAIL_TEMPLATE_TRANSACTION_REJECTED = 36;
    public static final Integer EMAIL_TEMPLATE_TRANSACTION_BULK_REJECTED = 37;
    public static final Integer EMAIL_TEMPLATE_TRANSACTION_APPROVED = 38;
    public static final Integer EMAIL_TEMPLATE_TRANSACTION_SUBMITTED_TO_OVERRIDE_PRIVILEGED_USERS = 61;
    //------------------- CREDIT CARD STATEMENT EMAIL -------------------------

    //------------------- VENDOR IMPORT SUMMARY EMAIL ---------------------------------
    public static final Integer EMAIL_TEMPLATE_VENDOR_LIST_IMPORT_SUMMARY = 63;

    //------------------- VENDOR IMPORT SUMMARY EMAIL ---------------------------------

    //------------------- EMAIL TEMPLATE PROPERTY ----------------------
    public static final String DUMMY_BILL_NUMBER = "BILL-NO";
    public static final String DUMMY_PO_NUMBER = "PO-NO";
    public static final String DUMMY_EXPENSE_NAME = "EXPENSE-NAME";
    public static final String DUMMY_INVOICE_NUMBER = "INVOICE-NO";
    public static final String DUMMY_REF_NUMBER = "REF-NUMBER";
    //------------------- EMAIL TEMPLATE PROPERTY ---------------------

    //-------------------- DOCUMENT_APPROVAL_MENU_PRIVILEGE---------------
    public static final Integer BILL_APPROVAL_PRIVILEGE_ID = 64;
    public static final Integer BILL_OVERRIDE_APPROVAL = 119;
    public static final Integer PO_APPROVAL_PRIVILEGE_ID = 88;
    public static final Integer PO_OVERRIDE_APPROVAL = 120;
    public static final Integer EXPENSE_APPROVAL_PRIVILEGE_ID = 105;
    public static final Integer EXPENSE_OVERRIDE_APPROVAL = 121;
    public static final Integer PAYMENT_APPROVAL_PRIVILEGE_ID = 402;
    public static final Integer PAYMENT_OVERRIDE_APPROVAL = 409;
    public static final Integer CREDIT_CARD_APPROVE_PRIVILEGE_ID = 501;
    public static final Integer CREDIT_CARD_OVERRIDE_APPROVE_PRIVILEGE_ID = 507;
    public static final Integer INBOX_REVIEW_PRIVILEGE_ID = 447;
    public static final Integer VENDOR_REQUEST_APPROVAL_PRIVILEGE_ID = 58;
    //-------------------- DOCUMENT_APPROVAL_MENU_PRIVILEGE---------------

    //Query params
    public static final String PARAM_VENDOR_ID = "vendorId";
    public static final String PARAM_REL_VENDOR_ID = "relVendorId";
    public static final String PARAM_SYSTEM_ID = "systemId";
    public static final String PARAM_SYNC_STATUS = "syncStatus";
    public static final String PARAM_GROUP_ID = "groupId";
    public static final String PARAM_COMPANY_ID = "companyId";
    public static final String PARAM_CREATED_BY_ID = "createdBy";
    public static final String PARAM_APPROVAL_GROUP_ID = "approvalGroupId";
    public static final String APPROVAL_USER = "approvalUser";
    public static final String OVERRIDE_APPROVAL_STATUS = "overrideApprovalStatus";
    public static final String PARAM_USER_ID = "userId";
    public static final String PARAM_RECEIPT_ID_LIST = "receiptIdList";

    public static final String DECIMAL_ZERO = "0.00";
    public static final String ZERO_POINT_ZERO = "0.0";
    public static final String POINT_DOUBLE_ZERO = ".00";
    public static final String W9_ATTACHMENT_NAME = "w9.pdf";

    //Additional field constants
    public static final String CONTENT_TYPE_IMAGES = "image/*";
    public static final String CONTENT_TYPE_PDF = "application/pdf";
    public static final String CONTENT_TYPE_MS_EXCEL_93_2003 = "application/vnd.ms-excel";
    public static final String CONTENT_TYPE_MS_EXCEL = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    public static final String CONTENT_TYPE_MS_WORD_93_2003 = "application/msword";
    public static final String CONTENT_TYPE_MS_WORD = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";

    public static final String FILE_TYPE_IMAGES = "Image files";
    public static final String FILE_TYPE_PDFS = "PDF files";
    public static final String FILE_TYPE_MS_EXCEL_93_2003 = "MS Excel(93-2003) files";
    public static final String FILE_TYPE_MS_EXCEL = "MS Excel files";
    public static final String FILE_TYPE_MS_WORD_93_2003 = "MS Word files(93-2003)";
    public static final String FILE_TYPE_MS_WORD = "MS Word files";

    public static final String REGEX_PATTERN_ANY = ".*";
    public static final String REGEX_PATTERN_NUMERIC = "^[0-9]*$";
    public static final String REGEX_PATTERN_ALPHABETICAL = "[a-zA-Z\\s]*";
    public static final String REGEX_PATTERN_ALPHANUMERIC = "[a-zA-Z0-9\\s]*";
    public static final String REGEX_PATTERN_NUMERIC_SPECIAL = "[-0-9$&+,:;=?@#|\'<>.^*()%!]*";
    public static final String REGEX_PATTERN_ALPHABETICAL_SPECIAL = "[-a-zA-Z$&+,:;=?@#|\'<>.^*()%!]*";
    public static final String REGEX_PATTERN_NEW_LINE = "\\r?\\n";
    public static final String REGEX_PATTERN_SPECIAL = "[\\p{P}\\p{S}]";
    public static final String REGEX_PATTERN_POINT_DOUBLE_ZERO = "^\\d+\\.\\d{2}$";

    public static final String PATTERN_ANY = "Any";
    public static final String PATTERN_NUMERIC = "Numeric";
    public static final String PATTERN_ALPHABETICAL = "Alphabetical";
    public static final String PATTERN_ALPHANUMERIC = "Alphanumeric";
    public static final String PATTERN_NUMERIC_SPECIAL = "Numeric + Special";
    public static final String PATTERN_ALPHABETICAL_SPECIAL = "Alphabetical + Special";
    public static final String EXUS_PARTNERS_PORTAL_ID = "${exus-partners-portal-id}";
    public static final Integer BC_SYSTEM_ID = 4;

    public static final String EXCEL_LIST_SOURCE_CELL_REFERENCE = "!$A$1:$A$";
    public static final String EXCEL_SHEET_YES_NO_LIST = "YesNoList";
    public static final String EXCEL_DROP_DOWN_NAME_YES_NO = "yesno";
    public static final String EXCEL_SHEET_VENDOR_LIST = "VendorList";
    public static final String EXCEL_DROP_DOWN_NAME_VENDORS = "vendors";
    public static final String EXCEL_SHEET_PAYMENT_LIST = "PaymentList";
    public static final String EXCEL_DROP_DOWN_NAME_PAYMENTS = "payments";
    public static final String ADDITIONAL_FIELD_NAME_INVALID = "Invalid field name.";
    public static final String ADDITIONAL_FIELD_IS_MANDATORY = PARAM_ATTR + " field is Mandatory, please enter a value.";
    public static final String ADDITIONAL_FIELD_DATE_FORMAT_INVALID = "Invalid date format for " + PARAM_ATTR + " field.";
    public static final String ADDITIONAL_FIELD_DATE_INVALID = "Invalid date value for " + PARAM_ATTR + " field.";
    public static final String ADDITIONAL_FIELD_PATTERN_INVALID = "Invalid field value type for " + PARAM_ATTR;
    public static final String ADDITIONAL_FIELD_LENGTH_EXCEEDS = PARAM_ATTR + " Field value length exceeds.";
    public static final String ADDITIONAL_FIELD_OPTION_INVALID = "Invalid option value for " + PARAM_ATTR + " field.";
    public static final String ADDITIONAL_FIELD_CHECKBOX_INVALID = "Invalid value for " + PARAM_ATTR + " field.";
    public static final String MODULE_HAS_NO_ADDITIONAL_FIELDS = "No additional fields found, please remove additional columns.";

    //###########################################VENDOR ADDITIONAL FIELDS SECTIONS SPECIFIC-ST#########################################################
    public static final String VENDOR_BASIC_INFORMATION_SECTION_NAME = "(Basic Information section)";
    public static final String VENDOR_POSTAL_ADDRESS_SECTION_NAME = "(Postal Address section)";
    public static final String VENDOR_REMIT_ADDRESS_SECTION_NAME = "(Remit Address section)";
    public static final String VENDOR_W9_FORM_INFORMATION_SECTION_NAME = "(W9 Form Information section)";
    public static final String VENDOR_PAYMENT_INFORMATION_SECTION_NAME = "(Payment Information section)";
    public static final String VENDOR_IS_CONFIDENTIAL = "Is Confidential Vendor";
    //###########################################VENDOR ADDITIONAL FIELDS SECTIONS SPECIFIC-ED#########################################################

    //###########################################VENDOR ADDITIONAL FIELDS SECTIONS SPECIFIC-ST#########################################################
    public static final String PAYMENT_HEADER_SECTION_NAME = "(Header Section)";
    //###########################################VENDOR ADDITIONAL FIELDS SECTIONS SPECIFIC-ED#########################################################

    // short codes
    //###########################################SHORT CODES SPECIFIC-ST#########################################################
    public static final String BILL_PAYMENT_SHORT_CODE = "BILL_PAYMENT";
    public static final String BILL_SHORT_CODE = "BILL";
    public static final String BILL_PREFIX = "bill";
    public static final String PO_PREFIX = "purchase order";
    public static final String PO_RECEIPT_PREFIX = "po receipt";
    public static final String RECURRING_BILL_PREFIX = "recurring bill";
    public static final String AUTOMATION_PREFIX = "Automation";
    public static final String VENDOR_CONFIDENTIAL_STATUS_PROPERTY = "vendor.confidential";
    public static final String LINE_ITEM_AUTOMATION_ACCOUNT_ID_LIST = "expenseAccountIdList";
    public static final String LINE_ITEM_AUTOMATION_ITEM_ID_LIST = "itemIdList";
    public static final String LINE_ITEM_AUTOMATION_PROJECT_CODE_ID_LIST = "projectCodeIdList";
    public static final String VENDOR_ITEM_VENDOR_ID = "vendorItem.vendorId";
    //###########################################SHORT CODES SPECIFIC-ED##########################################################

    //###########################################INBOX PREFIXES SPECIFIC-ST#######################################################
    public static final String SEGREGATED_PREFIX = "segregated_";
    public static final String NOTIFICATION_MESSAGE_PROPERTY_NOTIFICATION = "notifications";
    public static final String NOTIFICATION_MESSAGE_PROPERTY_NOT_READ_MAIL_COUNT = "notReadMail";
    //###########################################INBOX PREFIXES SPECIFIC-ED#######################################################

    //###########################################AMOUNT LIMITATION SPECIFIC-ST####################################################
    public static final BigDecimal SYSTEM_AMOUNT_LIMITATION = new BigDecimal(99999999999999999.99);
    public static final BigDecimal SYSTEM_QTY_LIMITATION = new BigDecimal(9999999999.99);
    //###########################################AMOUNT LIMITATION SPECIFIC-ED####################################################

    //###########################################PROJECT CODE SORTING SPECIFIC-ST####################################################
    public static final Integer SORT_ASCENDING = 1;
    public static final Integer SORT_DESCENDING = -1;
    public static final String SORT_BY_PROJECT_CODE = "appCode.name";
    public static final String SORT_BY_BUDGET = "appCode.projectBudget";
    public static final String SORT_BY_CONTRACT_VALUE = "appCode.contractValue";
    public static final String SORT_BY_AMOUNT_INVOICED = "appCode.amountInvoiced";
    //###########################################PROJECT CODE SORTING SPECIFIC-ED####################################################

    //###########################################TIME ZONE SPECIFIC-ST####################################################
    public static final String US_EASTERN_TIME_ZONE = "US/Eastern";
    //###########################################TIME ZONE SPECIFIC-ED####################################################

    //###########################################FEATURE SPECIFIC-ST#########################################################
    public static final Integer CONFIDENTIAL_FEATURE_ID = 2;
    public static final Integer CONFIDENTIAL_MENU_PRIVILEGE_ID = 457;
    public static final Integer RESTRICT_DOCUMENT_FEATURE_ID = 7;
    public static final Integer NEGATIVE_CREDIT_MEMO_FEATURE_ID = 8;
    public static final Integer OCR_AUTO_RUN_FEATURE_ID = 9;
    public static final Integer VALIDATE_VENDOR_WITH_VENDOR_CODE_AND_EMAIL_FEATURE_ID = 10;
    public static final Integer FEATURE_ID_ENABLE_VENDOR_COMMUNITY = 11;
    //###########################################FEATURE SPECIFIC-ED#########################################################

    //###################### VENDOR COMMUNITY FUNCTIONS RELATED AUTH CODES #########################
    public static final String AUTH_CODE_VENDORS_SEND_VENDOR_INVITATION = "VENDORS_SEND_VENDOR_INVITATION";
    public static final String AUTH_CODE_VENDORS_DELETE_VENDOR_INVITATION = "VENDORS_DELETE_VENDOR_INVITATION";
    public static final String AUTH_CODE_VENDORS_RESEND_VENDOR_INVITATION = "VENDORS_RESEND_VENDOR_INVITATION";
    public static final String AUTH_CODE_VENDORS_APPROVE_VENDOR_REQUEST = "VENDORS_APPROVE_VENDOR_REQUEST";
    public static final String AUTH_CODE_VENDORS_DELETE_VENDOR_REQUEST = "VENDORS_DELETE_VENDOR_REQUEST";
    public static final String AUTH_CODE_VENDORS_REJECT_VENDOR_REQUEST = "VENDORS_REJECT_VENDOR_REQUEST";
    public static final String AUTH_CODE_VENDORS_ADD_TO_LOCAL_VENDOR_LIST = "VENDORS_ADD_TO_LOCAL_VENDOR_LIST";
    public static final String AUTH_CODE_PURCHASE_ORDER_SEND_VENDOR_APPROVAL = "PURCHASE_ORDER_SEND_VENDOR_APPROVAL";

    public static final List<String> DISABLED_COMMUNITY_NON_REQUIRED_AUTH_LIST = Arrays.asList(AUTH_CODE_VENDORS_SEND_VENDOR_INVITATION,
            AUTH_CODE_VENDORS_DELETE_VENDOR_INVITATION, AUTH_CODE_VENDORS_RESEND_VENDOR_INVITATION,
            AUTH_CODE_VENDORS_APPROVE_VENDOR_REQUEST, AUTH_CODE_VENDORS_DELETE_VENDOR_REQUEST, AUTH_CODE_VENDORS_REJECT_VENDOR_REQUEST,
            AUTH_CODE_VENDORS_ADD_TO_LOCAL_VENDOR_LIST, AUTH_CODE_PURCHASE_ORDER_SEND_VENDOR_APPROVAL);

    //###########################################AUTH CODE SPECIFIC-ST#########################################################
    public static final String ACCESS_TO_CONFIDENTIAL_DOC = "ENABLE_ACCESS_TO_CONFIDENTIAL_DOCUMENTS";
    //###########################################AUTH CODE SPECIFIC-ED#########################################################

    //##############################################SPECIAL ADDITIONAL FIELD ID-ST#############################################
    public static final Integer ADDITIONAL_FIELD_SITE_CODE = 1;
    //##############################################SPECIAL ADDITIONAL FIELD ID-ED#############################################

    //##############################################VENDOR STATUS FILTER-ST###################################################
    public static final String VENDOR_ACTIVE_STR = "Active";
    public static final String VENDOR_INACTIVE_STR = "Inactive";
    public static final String VENDOR_CANCELED_STR = "Canceled";
    //##############################################VENDOR STATUS FILTER-ED###################################################

    //##############################################PO Receipt STATUS FILTER-ST###################################################
    public static final String PO_RECEIPT_ACTIVE_STR = "Active";
    public static final String PO_RECEIPT_INACTIVE_STR = "Closed";
    //##############################################Po Receipt STATUS FILTER-ED###################################################

    //##############################################OCR RUNNING STATUS-ST#####################################################
    public static final char OCR_CAPTURING_NOT_STARTED = 'N';
    public static final char OCR_CAPTURING_PENDING = 'P';
    public static final char OCR_CAPTURING_DONE = 'Y';
    public static final char OCR_CAPTURING_FAILED = 'F';
    //##############################################OCR RUNNING STATUS-ED#####################################################

    //###############################################GLOBEL MAP NAMES-ST######################################################
    public static final String NOTIFICATION_TENANT_USER_MAP = "NOTIFICATION_TENANT_USER_MAP";
    public static final String BILL_TENANT_USER_MAP = "BILL_TENANT_USER_MAP";
    public static final String RECEIPT_TENANT_USER_MAP = "RECEIPT_TENANT_USER_MAP";
    //###############################################GLOBEL MAP NAMES-ED######################################################

    //###############################################FONT AWESOME ICON-ST#####################################################
    public static final String ICON_APPROVED_AND_REDESIGN = "fa fa-angle-double-right";
    public static final String ICON_APPROVED = "fa fa-check";
    public static final String ICON_REASSIGNED = "fa fa-share";
    public static final String ICON_SUPPORT_TICKET = "fa-solid fa-headset";
    //###############################################FONT AWESOME ICON-ED#####################################################


    //##############################################VENDOR ITEM SPECIFIC-ST##################################################
    public static final String TAXABLE = "Taxable";
    public static final String NON_TAXABLE = "Nontaxable";
    public static final String VENDOR_ITEM_INFORMATION_INVALID_LINE = "Vendor Item information, line number"
            + " " + LINE_NO_PARAM + " is invalid, please check and try again.";
    public static final String VENDOR_ITEM_INFORMATION_DUPLICATE_LINE = "Vendor Item information, line number"
            + " " + LINE_NO_PARAM + " is duplicated, please check and try again.";
    public static final String VENDOR_ITEM_NUMBER_LENGTH_EXCEEDED = "Vendor Item information, line number"
            + " " + LINE_NO_PARAM + " Vendor Part Number field value cannot exceed 50 characters.";
    public static final String VENDOR_ITEM_PRICE_LENGTH_EXCEEDED = "Vendor Item information, line number"
            + " " + LINE_NO_PARAM + " vendor price cannot exceed 99,999,999,999,999,999.99";
    public static final String VENDOR_ITEM_LEAD_TIME_LENGTH_EXCEEDED = "Vendor Item information, line number"
            + " " + LINE_NO_PARAM + " lead time field value cannot exceed 200 characters.";
    public static final String VENDOR_ITEM_DESCRIPTION_LENGTH_EXCEEDED = "Vendor Item information, line number"
            + " " + LINE_NO_PARAM + " item description field value cannot exceed 255 characters.";
    public static final String VENDOR_ITEM_VENDOR_CANNOT_EMPTY = "Vendor Item information, line number"
            + " " + LINE_NO_PARAM + " vendor cannot be empty.";
    public static final String VENDOR_ITEM_VENDOR_USE_IN_BILLS = "Vendor Item information, line number"
            + " " + LINE_NO_PARAM + " cannot change the vendor, it's already assigned to a bill.";
    public static final String VENDOR_ITEM_SKU_USE_IN_BILLS = "Vendor Item information, line number"
            + " " + LINE_NO_PARAM + " cannot change the Vendor Part Number, it's already assigned to a bill.";
    public static final String VENDOR_ITEM_VENDOR_USE_IN_PO = "Vendor Item information, line number"
            + " " + LINE_NO_PARAM + " cannot change the vendor, it's already assigned to a purchase order.";
    public static final String VENDOR_ITEM_SKU_USE_IN_PO = "Vendor Item information, line number"
            + " " + LINE_NO_PARAM + " cannot change the Vendor Part Number, it's already assigned to a purchase order.";
    public static final String VENDOR_ITEM_USE_IN_PO = "Vendor Item information, cannot delete the line number"
            + " " + LINE_NO_PARAM + ", it's already assigned to a purchase order.";
    public static final String VENDOR_ITEM_USE_IN_BILL = "Vendor Item information, cannot delete the line number"
            + " " + LINE_NO_PARAM + ", it's already assigned to a bill.";
    public static final String VENDOR_ITEM_USE_IN_RECURRING_BILL = "Vendor Item information, cannot delete the line number"
            + " " + LINE_NO_PARAM + ", it's already assigned to a recurring bill.";
    public static final String VENDOR_ITEM_VENDOR_USE_IN_RECURRING_BILL = "Vendor Item information, line number"
            + " " + LINE_NO_PARAM + " cannot change the vendor, it's already assigned to a recurring bill.";
    public static final String VENDOR_ITEM_SKU_USE_IN_RECURRING_BILL = "Vendor Item information, line number"
            + " " + LINE_NO_PARAM + " cannot change the Vendor Part Number, it's already assigned to a recurring bill..";
    public static final String VENDOR_ITEM_NUMBER_EMPTY = "Vendor Part Number cannot be empty.";
    public static final String VENDOR_ITEM_NUMBER_EXIST = "Vendor Part Number already exists for the given vendor code.";
    public static final String VENDOR_ITEM_NUMBER_SKU_EXIST = "Vendor Item information, line number"
            + " " + LINE_NO_PARAM + " Vendor Part Number already exists for the given vendor.";
    public static final String VENDOR_ITEM_LENGTH_EXCEED = "Vendor Part Number cannot exceed 50 characters.";
    public static final String VENDOR_ITEM_DESCRIPTION_LENGTH_EXCEED = "Vendor item description cannot exceed 500 characters.";
    //##############################################VENDOR ITEM SPECIFIC-ED##################################################


    //#####################SUPPORT TICKET RELATED ########################################
    public static final String TICKET_STATUS_WAITING_FOR_SUPPORT = "Waiting for support";

    //#####################CLOUD FLARE RELATED-ST ########################################
    public static final Integer CLOUD_FLARE_CONFIG_RECORD_ID = 1;
    public static final String X_AUTH_EMAIL = "X-Auth-Email";
    public static final String X_AUTH_KEY = "X-Auth-Key";
    public static final String PAPERTRL_DOMAIN = ".papertrl.com";
    public static final String HTTP_PREFIX = "https://";

    public static final String REQUEST_IS_BEING_PROCESSED_SUPPORT_TEAM_WILL_CONTACT_YOU_SOON = "Your request is being processed by our end. " +
            " Our support team member will contact you soon. Your patience is highly appreciated!";

    public static final String RECORD_ALREADY_EXIST = "Record already exists.";
    public static final String AUTHENTICATION_ERROR = "DNS Authentication error. (Error Code : 10000)";

    //#####################CLOUD FLARE RELATED-ED ########################################


    public static final String SYNC_RECORD_EVENT_CREATE = "Create";
    public static final String SYNC_RECORD_EVENT_UPDATE = "Update";
    public static final String SYNC_RECORD_EVENT_DELETE = "Delete";


    //-------------------------------------FEATURE IDS-----------------------------------
    public static final Integer FEATURE_ID_CREATE_NEW_RECORD_FOR_CUSTOM_TERMS = 3;


    public static final String FIELD_AMOUNT = "exp.amount";
    public static final String EXP_AMOUNT = "expamount";

    //############################SMS PROPERTIES-ST############################
    public static final int SMS_TYPE_OTP = 1;
    public static final int SMS_TYPE_OTHER = 2;
    //############################SMS PROPERTIES-ED############################

    public static final String ZERO = "0";

    //############################ Automation Compatibility Messages ############################

    public static final String FIELD_DOES_NOT_SUPPORTED = "The selected field does not support for the following event(s)";
    public static final String ACTION_DOES_NOT_SUPPORTED = "The selected action does not support for the following event(s)";

    //############################ Document Type Names ############################

    //############################ Automated Vendor Import Compatibility Messages ############################

    public static final String VENDOR_IMPORT_ISSUE_DESCRIPTION = "Line number : "
            + LINE_NO_PARAM + " " + PARAM_FAIL_REASON;

    //############################ Automated Vendor Import Compatibility Messages ############################

    public static final String BILL = "Bill ";
    public static final String PURCHASE_ORDER = "Purchase Order ";
    public static final String EXPENSE_REPORT = "Expense Report ";
    public static final String PURCHASE_ORDER_RECEIPT = "Purchase Order Receipt ";
    public static final String PAYMENT = "Payment ";

    //############################ Column Frozen Directions ############################

    public static final String LEFT_FROZEN_DIRECTION = "left";
    public static final String RIGHT_FROZEN_DIRECTION = "right";

    //############################ Numbers (String) ############################

    public static final String NUMBER_FIFTY_FROM_STRING = "50";
    public static final String NUMBER_TWO_HUNDRED_TEN_FROM_STRING = "210";
    public static final String NUMBER_ZERO_FROM_STRING = "0";
    public static final String NUMBER_ONE_FROM_STRING = "1";

    //############################ Numbers (int) ############################

    public static final int NUMBER_SEVENTY_TWO = 72;
    public static final String ACCOUNT_TYPE_DEBIT = "0";
    public static final String ACCOUNT_TYPE_CREDIT = "1";

    //############################ Column Names ############################

    public static final String COLUMN_CHECKBOX = "checkbox";
    public static final String COLUMN_ACTION = "action";
    public static final String COLUMN_HASH = "hash";
    public static final String COLUMN_TABLE_ACTION = "tableAction";

    //############################ Delegate User ############################

    public static final int DELEGATE_ACTION_EXPENSE_CREATION = 1;
    public static final int DELEGATE_ACTION_PROCESS_CREDIT_CARD_STATEMENTS = 2;
    public static final int DELEGATE_ACTION_APPROVAL = 3;

    //############################ Notification Subscription Types ############################

    public static final int NOTIFICATION_SUBSCRIPTION_TYPE_BILL = 1;
    public static final int NOTIFICATION_SUBSCRIPTION_TYPE_PO = 2;
    public static final int NOTIFICATION_SUBSCRIPTION_TYPE_PO_RECEIPT = 3;
    public static final int NOTIFICATION_SUBSCRIPTION_TYPE_EXPENSE = 4;
    public static final int NOTIFICATION_SUBSCRIPTION_TYPE_PAYMENT = 5;
    public static final int NOTIFICATION_SUBSCRIPTION_TYPE_VENDOR = 6;
    public static final int NOTIFICATION_SUBSCRIPTION_TYPE_AUTOMATION = 7;
    public static final int NOTIFICATION_SUBSCRIPTION_TYPE_CREDIT_NOTE = 8;
    public static final int NOTIFICATION_SUBSCRIPTION_TYPE_CREDIT_CARD_STATEMENTS = 9;
    public static final int NOTIFICATION_SUBSCRIPTION_TYPE_RECEIPT = 10;
    public static final int NOTIFICATION_SUBSCRIPTION_TYPE_DELEGATE_USER = 11;

    //############################ Notification Subscription Events ############################

    public static final int NOTIFICATION_SUBSCRIPTION_EVENT_EXPENSE_APPROVAL = 7;
    public static final int NOTIFICATION_SUBSCRIPTION_EVENT_EXPENSE_APPROVAL_GROUP = 17;

    //############################ Menu Ids ############################

    public static final int EXPENSE_MENU_ID = 1051;
    public static final int PROCESS_TRANSACTION_MENU_ID = 1054;
    public static final int BILL_MENU_ID = 1019;
    public static final int PO_MENU_ID = 1021;
    public static final int PAYMENT_MENU_ID = 1064;

    //############################ UNATTENDED_PAYMENT ############################
    public static final String PAYMENT_PROCESS_SCHEDULER = "Payment_Download";
    public static final String PAYMENT_CREATE_SCHEDULER = "Payment_Creation";

    //############################ Country Names ############################

    public static final String COUNTRY_NAME_CAPITAL_UNITED_STATES_OF_AMERICA = "UNITED STATES OF AMERICA";
    public static final String COUNTRY_NAME_UNITED_STATES = "United States";

    //############################ Auth0 ############################

    public static final String AUTH0_CLAIM_USERNAME = "/username";
    public static final String AUTH0_CLAIM_ORGANIZATION = "/organization";

    public static final String AUTH0_DEFAULT_CLIENT_ID = "auth0-client";
    public static final String AUTH0_HEADER_NAME_AUTHENTICATION_SOURCE = "AuthenticationSource";

    public static final String AUTH_SOURCE_AUTH0 = "auth0";

    public static final String AUTH0_DOMAIN = "${application.auth0.domain}";
    public static final String AUTH0_AUDIENCE = "${application.auth0.audience}";
    public static final String AUTH0_CONNECTION = "${application.auth0.connection}";
    public static final String AUTH0_CONNECTION_ID = "${application.auth0.connection_id}";
    public static final String AUTH0_MTM_CLIENT_ID = "${application.auth0.machine_to_machine.client_id}";
    public static final String AUTH0_MTM_CLIENT_SECRET = "${application.auth0.machine_to_machine.client_secret}";
    public static final String AUTH0_FRONTEND_CLIENT_ID = "${application.auth0.frontend.client_id}";
    public static final String AUTH0_CLAIMS_PREFIX = "${application.auth0.claims_prefix}";

    public static final String AUTH_ERROR_STATUS = "AUTH_ERROR_STATUS";
    public static final String AUTH_ERROR_MESSAGE = "AUTH_ERROR_MESSAGE";
}
