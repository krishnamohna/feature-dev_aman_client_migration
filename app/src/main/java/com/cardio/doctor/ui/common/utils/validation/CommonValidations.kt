package com.cardio.doctor.ui.common.utils.validation

import android.content.Context
import com.cardio.doctor.domain.common.model.validation.ValidationModelV2
import com.cardio.doctor.network.Status
import com.cardio.doctor.ui.common.utils.ENUM

object CommonValidations {

     fun emptyValidation(value: String,fieldtype:FieldType,errorMsg:Int,context: Context): ValidationModelV2 {
         var validation = when {
             value.isEmpty()->{
                 ValidationModelV2(Status.ERROR,
                 context.getString(errorMsg),
                 fieldtype)}
             else -> {
                 ValidationModelV2(Status.SUCCESS,
                     "",
                     fieldtype)
             }
         }
         return validation
    }
    fun minThreeCharValidation(value: String,fieldtype:FieldType,errorMsg:Int,context: Context): ValidationModelV2 {
        var validation = when {
            value.isEmpty()->{
                ValidationModelV2(Status.ERROR,
                    context.getString(errorMsg),
                    fieldtype)}
              value.isNotEmpty() && value.length < ENUM.INT_3 -> {
                  ValidationModelV2(Status.ERROR,
                      context.getString(errorMsg),
                      fieldtype)
              }
            else -> {
                ValidationModelV2(Status.SUCCESS,
                    "",
                    fieldtype)
            }
        }
        return validation
    }
}