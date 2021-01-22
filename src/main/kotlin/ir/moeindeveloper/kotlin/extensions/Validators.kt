package ir.moeindeveloper.kotlin.extensions

import ir.moeindeveloper.kotlin.validators.IranBankUtils
import ir.moeindeveloper.kotlin.validators.IranCodeUtils
import ir.moeindeveloper.kotlin.validators.IranNationalUtils


/**
 *  Checks if the given string is a valid iranian phone/mobile number
 */
fun String.isIranianPhoneNumber(): Boolean = IranCodeUtils.isPhoneNumber(this)

/**
 * Checks if the given string is a valid iranian mobile number
 */
fun String.isValidIranianMobileNumber(): Boolean = IranCodeUtils.isValidIranianMobileNumber(this)

/**
 * Checks if the given string is a valid iranian phone number
 */
fun String.isValidIranianPhoneNumber(): Boolean = IranCodeUtils.isValidIranianPhoneNumber(this)

/**
 * Checks if the given string is a valid iranian postal code
 */
fun String.isValidPostalCode(): Boolean = IranCodeUtils.isValidPostalCode(this)

/**
 * gets the carrier name based on the provided mobile number
 */
fun String.carrierName(): String? = IranCodeUtils.getCarrierName(this)

/**
 * get the province name based on the provided phone number
 */
fun String.phoneProvince(): String? = IranCodeUtils.getProvinceName(this)

/**
 * checks if the given string is a valid sheba number
 */
fun String.isValidShebaNumber(): Boolean = IranBankUtils.isValidIBAN(this)

/**
 * Checks if the given string is a valid shetab number
 */
fun String.isValidShetabNumber(): Boolean = IranBankUtils.isValidShetabNumber(this)

/**
 * Checks if the given string is a valid Iranian national code
 */
fun String.isValidNationalCode(): Boolean = IranNationalUtils.isValidNationalCode(this)

/**
 * Checks if the given String is a valid Iranian national legal code
 */
fun String.isValidNationalLegalCode(): Boolean = IranNationalUtils.isValidNationalLegalNumber(this)