package eu.parent.android.app.common.presentation.dialogs

import android.app.Activity
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import com.parent.domain.common.date.getHour
import com.parent.domain.common.date.getMinute
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog
import com.wdullaer.materialdatetimepicker.time.Timepoint
import java.util.*

/**
 * Created by mahmoud on 11/14/17.
 */
fun Fragment.errorDialog(errorMessage: String, onClosed: () -> Unit = {}) {
    showErrorDialog(errorMessage, onClosed, childFragmentManager)
}

fun Fragment.retryErrorDialog(errorMessage: String, onRetry: () -> Unit = {}) {
    retryErrorDialog(errorMessage, onRetry, childFragmentManager)
}

fun AppCompatActivity.errorDialog(errorMessage: String, onClosed: () -> Unit = {}) {
    showErrorDialog(errorMessage, onClosed, supportFragmentManager)
}

fun AppCompatActivity.showRetryErrorDialog(errorMessage: String, onRetry: () -> Unit = {}) {
    retryErrorDialog(errorMessage, onRetry, supportFragmentManager)
}


fun showErrorDialog(errorMessage: String, onClosed: () -> Unit = {}, childFragmentManager: FragmentManager) {
    ErrorDialog(errorMessage).apply {
        this.onCloseClick = onClosed

        isCancelable = false
    }.show(childFragmentManager, "error_dialog")
}

fun retryErrorDialog(errorMessage: String, onRetry: () -> Unit = {}, childFragmentManager: FragmentManager) {
    RetryErrorDialog(errorMessage).apply {
        this.onRetryClick = onRetry
    }.show(childFragmentManager, "retry_error_dialog")
}

fun Fragment.showDatePicker(date: Long = -1, minDate: Long = -1,maxDate: Long = -1, onDateSelected: (Int, Int, Int, Long) -> Unit) {
    if (isAdded) {
        val calendar = Calendar.getInstance()
        if (date > -1) {
            calendar.timeInMillis = date
        }
        val defaultYear = calendar.get(Calendar.YEAR)
        val defaultMonth = calendar.get(Calendar.MONTH)
        val defaultDay = calendar.get(Calendar.DAY_OF_MONTH)

     var datePicker=   DatePickerDialog.newInstance({ _, year, monthOfYear, dayOfMonth ->
            val timInMillis = Calendar.getInstance().apply {
                set(year, monthOfYear, dayOfMonth, 0, 0, 0)
                set(Calendar.MILLISECOND, 0)
            }.timeInMillis
            onDateSelected(year, monthOfYear, dayOfMonth, timInMillis)
        }, defaultYear, defaultMonth, defaultDay).apply {
            if (minDate > -1) {
                setMinDate(Calendar.getInstance().apply { timeInMillis = minDate })
            }
         if(maxDate>-1){
             setMaxDate(Calendar.getInstance().apply { timeInMillis = maxDate })
         }
        }



        datePicker.show(activity.fragmentManager, "date_picker")
    }
}

fun Activity.showDatePicker(date: Long = -1, minDate: Long = -1, onDateSelected: (Int, Int, Int, Long) -> Unit) {
    val calendar = Calendar.getInstance()
    if (date > -1) {
        calendar.timeInMillis = date
    }
    val defaultYear = calendar.get(Calendar.YEAR)
    val defaultMonth = calendar.get(Calendar.MONTH)
    val defaultDay = calendar.get(Calendar.DAY_OF_MONTH)

    DatePickerDialog.newInstance({ _, year, monthOfYear, dayOfMonth ->
        val timInMillis = Calendar.getInstance().apply {
            set(year, monthOfYear, dayOfMonth, 0, 0, 0)
            set(Calendar.MILLISECOND, 0)
        }.timeInMillis
        onDateSelected(year, monthOfYear, dayOfMonth, timInMillis)
    }, defaultYear, defaultMonth, defaultDay).apply {
        if (minDate > -1) {
            setMinDate(Calendar.getInstance().apply { timeInMillis = minDate })
        }
    }.show(this.fragmentManager, "date_picker")
}

fun Fragment.GetTimePoint(time: Long): Timepoint {

    return Timepoint(time.getHour(), time.getMinute(), 0)

}

fun Fragment.showTimePicker(date: Long = -1, minTime: Long = -1, maxTime: Long = -1, is24Hours: Boolean = false, onTimeSelected: (Int, Int, Long) -> Unit) {
    if (isAdded) {
        val calendar = Calendar.getInstance()
        if (date > -1) {
            calendar.timeInMillis = date
        }

        val defaultHour = calendar.get(Calendar.HOUR_OF_DAY)
        val defaultMinute = calendar.get(Calendar.MINUTE)

        TimePickerDialog.newInstance({ _, hour, minute, second ->
            val timeInMillis = Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, hour)
                set(Calendar.MINUTE, minute)
                set(Calendar.SECOND, second)
            }.timeInMillis
            onTimeSelected(hour, minute, timeInMillis)
        }, defaultHour, defaultMinute, is24Hours).apply {
            if (minTime > -1) {
                setMinTime(GetTimePoint(minTime))
            }
            if (maxTime > -1) {
                setMaxTime(GetTimePoint(maxTime))
            }

        }.show(activity.fragmentManager, "time_picker")
    }
}