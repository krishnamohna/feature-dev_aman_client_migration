@file:JvmName("AppConstants")
@file:JvmMultifileClass

package com.cardio.physician.ui.common.utils

import com.cardio.physician.BuildConfig

interface Preference {
    companion object {
        const val APP = "Doctor_Preferences"
        const val IS_TUTORIAL_SHOWN = "IsTutorialShown"
        const val SYNC_HEALTH = "SyncHealth"
        const val IS_TOPIC_SUBSCRIBED = "is_topic_subscribed"
        const val PREF_DISPLAY_NAME = "display_name"
        const val PREF_FIRST_NAME = "first_name"
        const val PREF_LAST_NAME = "last_name"
        const val PREF_PROFILE_IMAGE = "profile_image"
        const val PREF_EMAIL = "pref_email"
        const val LAST_SUBSCRIPTION_TOPIC = "last_subscription_token"
        const val HAS_MANUALLY_CHANGED_SUBSCRIPTION = "has_manully_changed_subscription"
    }
}

object BroadCastAction{
    const val ACTION_NOTIFICATION_UPDATE="action_notification_update"
}


interface Timer {
    companion object {
        const val SPLASH_TIME: Long = 1000
        const val OTP_EXPIRED: Long = 60
        const val INPUT_DELAY: Long = 500
        const val DOUBLE_CLICK_TIME_DELTA: Long = 1500

        const val OTP_EXPIRE_IN_MILISECONDS: Long = OTP_EXPIRED * 1000
        const val COUNT_DOWN_INTERVAL: Long = 1000
        const val OTP_TIME_FORMAT = "00"
        const val API_TIMEOUT: Long = 60000
        const val CHART_ANIMATE_TIME = 2000
    }
}

interface Log {
    companion object {
        const val TAG = "com.cardio.physician"
    }
}

interface ENUM {
    companion object {
        const val INT_1: Int = 1
        const val INT_2: Int = 2
        const val INT_3: Int = 3
        const val INT_10: Int = 10

    }
}

object FireStoreDocument{
    const val PATIENT="patient"
}

interface FireStoreCollection {
    companion object {
        const val USERS = "Users"
        const val DRUGS = "Drugs"
        const val HEALTH_LOGS = "HealthLogs"
        const val LOGS = "logs"
        const val QUESTIONNAIRE = "Questionnaire"
        const val DIAGNOSIS = "Diagnosis"
        const val NOTIFICATIONS = "Notifications"
        const val CONNECTIONS = "Connections"
    }
}

object UserType{
    const val USER_TYPE_PATIENT = "patient"
    const val USER_TYPE_PHYSICIAN = "physician"
}

interface WEBURL {
    companion object {
        const val PRIVACY_POLICY = "google.com"
        const val TERMS_AND_CONDITION = "google.com"
        const val ABOUT_US = "google.com"
        const val FAQ = "google.com"
        const val DEFAULT_URL = "google.com"

    }
}

interface FireStoreDocKey {
    companion object {
        const val USER_ID = "userId"
        const val FIRST_NAME = "firstName"
        const val LAST_NAME = "lastName"
        const val EMAIL = "email"
        const val IMAGE_URL = "imageUrl"
        const val COUNTRY_CODE = "countryCode"
        const val PHONE_NUMBER = "phoneNumber"
        const val DOB_PROFILE = "dob"
        const val SIGN_UP_TYPE = "signupType"
        const val GENDER = "gender"
        const val HEIGHT = "height"
        const val WEIGHT = "weight"
        const val NAME = "name"
        const val CATEGORY = "category"
        const val OTHER = "others"
        const val USER_TYPE = "userType"
        const val SEARCH_NAME_USER = "searchName"

        //for sync collection
        const val HEART_RATE = "heartRate"
        const val BLOOD_PRESURE = "bloodPressure"
        const val BLOOD_SYSTOLIC_BP = "systolicBP"
        const val BLOOD_DIASTOLIC_BP = "diastolicBP"
        const val STEP_COUNT = "stepCount"
        const val TIME_STAMP_CAMEL = "timeStamp"
        const val TIME_STAMP = TIME_STAMP_CAMEL

        //for diagnosis
        const val TOP_BP = "topBp"
        const val BOTTOM_BP = "bottomBp"
        const val AGE = "age"
        const val AILMENT = "ailment"
        const val DATE = "date"
        const val ATRIAL_FABRILLATION = "Atrial Fibrillation"
        const val CARDIAC_HEART_FAILURE = "Heart Failure"
        const val QUESTIONS = "Questions"
        const val QUESTIONNAIRE = "questionnaire"
        const val MEDICATIONS = "medications"
        const val QUESTION_KEY = "question"
        const val OPTION_1 = "option_1"
        const val OPTION_2 = "option_2"
        const val OPTION_3 = "option_3"
        const val OPTION_4 = "option_4"
        const val TYPE = "type"
        const val POSITION = "position"
        const val SECONDARY_OPTION_1 = "secondary_option_1"
        const val SECONDARY_OPTION_2 = "secondary_option_2"
        const val SECONDARY_OPTION_3 = "secondary_option_3"
        const val EJECTION_FRACTION_QUESTION_ID = "3"
        const val DRY_WEIGHT_QUESTION_ID = "4"
        const val DEFIBRILLAOR_QUESTION_ID = "6"
        const val PACEMAKER_QUESTION_ID = "5"
        const val UNKNOWN = "Unknown"
        const val NO = "No"
        const val YES = "yes"

        //for drug
        const val DOSAGE = "dosage"
        const val IS_DIURETICS = "diuretics"
        const val ID = "id"
        const val TRADE_NAME = "tradeName"
        const val RATE_CONTROL_AGENT = "rateControlAgent"
        const val SEARCH_KEY_MEDICINE = "searchKey"
        const val SEARCH_KEY_MEDICINE_OTHER = "searchKeyOther"

        const val SEARCH_NAME = "searchName"
        const val TIMESTAMP = "timeStamp"
        const val REQUEST_STATUS = "requestStatus"
        //for notification type
        const val NOTIFICATION_TYPE_REQUEST="1"
        const val NOTIFICATION_TYPE_ADD_DIAGNOSIS="2"
        const val NOTIFICATION_TYPE_EDIT_DIAGNOSIS="3"
        const val NOTIFICATION_TYPE_REQUEST_ACCEPTED="4"
        const val NOTIFICATION_ILLNESS_ADDED="illness_added"
        const val NOTIFICATION_TYPE="type"
    }
}

object GoogleFit {
    const val DATA_TYPE_HEART = "com.google.heart_rate.summary"
    const val DATA_TYPE_WEIGHT = "com.google.weight.summary"
    const val DATA_TYPE_STEP_COUNT = "com.google.step_count.delta"
    const val DATA_TYPE_BLOOD_PRESURE = "com.google.blood_pressure.summary"
    const val DATA_POINT_TYPE_AVERAGE = "average"
    const val DATA_POINT_STEPS = "steps"
    const val DATA_POINT_FIELD_SYSTOLIC_AVERAGE = "blood_pressure_systolic_average"
    const val DATA_POINT_FIELD_DIASTOLIC_AVERAGE = "blood_pressure_diastolic_average"
}

object Constants {
    const val GOOGLE_SPEECH_SEARCH_APP = "com.google.android.googlequicksearchbox"
    const val CHART_LABEL_COUNT = 7
    const val USER_TYPE_NOT_DEFINED = "not_defined"
    const val USER_TYPE_PATIENT = "patient"
    const val USER_TYPE_PHYSICIAN = "physician"
    const val HEALTH_LOG_PERIOD_FOR_CONNECT_FEATURE = 30
    const val WEIGHT_DIGITS_BEFORE_ZERO = 3
    const val HEIGHT_DIGITS_BEFORE_ZERO = 3
    const val HEART_DIGITS_BEFORE_ZERO = 3
    const val TOP_BP_DIGITS_BEFORE_ZERO = 3
    const val BOTTOM_BP_DIGITS_BEFORE_ZERO = 3
    const val STEP_BP_DIGITS_BEFORE_ZERO = 6
    const val ANSWER_UNKOWN = "Incomplete"
    var FCM_SERVER_KEY = BuildConfig.FCM_KEY
    const val ANSWER_2_OR_LESS = "2 or less"
    const val MAX_NO_OF_HEALTH_LOGS_PER_API = 30

}

object ERROR {
    const val INVALID_OTP_CODE = "ERROR_INVALID_VERIFICATION_CODE"
}

object DateFormat_ {
    const val DATE_FORMAT_DD_MMM_YYYY = "dd MMM yyyy"
    const val DATE_FORMAT_YYYY_MMM_DD = "yyyy-MM-dd"  //2021-10-18
    const val DATE_FORMAT_DD_MM_YYYY_DATE_PICKER = "dd-MM-yyyy"  //2021-10-18
    const val DATE_FORMAT_DD_MM = "MM/dd"  //2021-10-18
    const val DATE_FORMAT_HH_MM = "hh:mm a"
}

object QuestionTypes {
    const val TYPE_1 = 1L
    const val TYPE_2 = 2L
    const val TYPE_3 = 3L
    const val TYPE_4 = 4L
}

object QuestionId {
    const val EJECTION_FRACTION = "3"
    const val DRY_WEIGHT = "4"
}

object EXTRAS {
    const val DIAGNOSIS_MODEL = "extras_diagnosis_model"
    const val EDIT_TYPE = "extras_edit_type"
    const val CHOOSE_TYPE = "extras_choose_type"
    const val USER_PROFILE = "user_profile"
    const val HEAR_RATE = "heart_rate"
    const val NEW_EMAIL_ADDRESS = "new_email_address"
    const val TEXT_RECOGNIZATION = "text_recognization"
    const val WEB_URL = "extras_web_url"
    const val COUNTRY_CODE = "extras_country_code"
    const val USER_ID = "extras_user_id"
    const val SELECTED_IMAGE = "selected_image"
    const val EXTRAS_FROM_NOTIFICATION = "extras_from_notification"
    const val EXTRAS_PUSH_NOTIFICATON_TITLE = "extras_push_noti_title"
    const val EXTRAS_DOCTOR_ID = "doctorId"
}

object NotificationTitle{
    const val NOTIFICATION_TITLE_DIAGNOSIS="Diagnosis"
    const val NOTIFICATION_TITLE_ADD_REQUEST="Add Request"
}

object NotificationType{
    const val TYPE_ACCEPT_REQUEST="1"
    const val TYPE_ADD_DIAGNOSIS="2"
    const val TYPE_EDIT_DIAGNOSIS="3"
    const val TYPE_UNKNOWN="4"
}

