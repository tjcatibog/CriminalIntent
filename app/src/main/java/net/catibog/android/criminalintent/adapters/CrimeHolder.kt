package net.catibog.android.criminalintent.adapters

import android.icu.text.DateFormat
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import net.catibog.android.criminalintent.databinding.ListItemCrimeDefaultBinding
import net.catibog.android.criminalintent.databinding.ListItemCrimeUrgentBinding
import net.catibog.android.criminalintent.models.Crime
import java.util.*

open class CrimeHolder(private val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {
    open fun bind(crime: Crime, onCrimeClicked: (crimeId: UUID) -> Unit) {
        when (binding) {
            is ListItemCrimeDefaultBinding -> {
                binding.crimeTitle.text = crime.title
                binding.crimeDate.text = formatDate(crime.date)
                binding.crimeSolved.visibility = if (crime.isSolved) View.VISIBLE else View.GONE
            }
            is ListItemCrimeUrgentBinding -> {
                binding.crimeTitle.text = crime.title
                binding.crimeDate.text = formatDate(crime.date)
                binding.crimeSolved.visibility = if (crime.isSolved) View.VISIBLE else View.GONE
            }
        }
        binding.root.setOnClickListener {
            onCrimeClicked(crime.id)
        }
    }
    private fun formatDate(date: Date): String {
        return DateFormat.getPatternInstance("EEEE, MMMM d, YYYY").format(date)
    }
}

class DefaultCrimeHolder(binding: ListItemCrimeDefaultBinding) : CrimeHolder(binding)

class UrgentCrimeHolder(private val binding: ListItemCrimeUrgentBinding) : CrimeHolder(binding) {
    override fun bind(crime: Crime, onCrimeClicked: (crimeId: UUID) -> Unit) {
        super.bind(crime, onCrimeClicked)
        binding.contactPoliceButton.setOnClickListener {
            Snackbar.make(binding.root, "Contacting police for ${crime.title}!", Snackbar.LENGTH_SHORT).show()
        }
    }
}