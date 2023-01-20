package net.catibog.android.criminalintent

import android.icu.text.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import net.catibog.android.criminalintent.databinding.ListItemCrimeDefaultBinding
import net.catibog.android.criminalintent.databinding.ListItemCrimeUrgentBinding
import java.util.*

class CrimeListAdapter(private val crimes: List<Crime>): RecyclerView.Adapter<CrimeHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrimeHolder {
        val inflater = LayoutInflater.from(parent.context)
        if (viewType == 1) {
            val binding = ListItemCrimeUrgentBinding.inflate(inflater, parent, false)
            return UrgentCrimeHolder(binding)
        }
        val binding = ListItemCrimeDefaultBinding.inflate(inflater, parent, false)
        return DefaultCrimeHolder(binding)
    }

    override fun onBindViewHolder(holder: CrimeHolder, position: Int) {
        holder.bind(crimes[position])
    }

    override fun getItemCount(): Int = crimes.size

    override fun getItemViewType(position: Int): Int {
        return if (crimes[position].requiresPolice) 1 else 0
    }
}

open class CrimeHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {
    open fun bind(crime: Crime) {}
    fun formatDate(date: Date): String {
        return DateFormat.getPatternInstance("EEEE, MMMM d, YYYY").format(date)
    }
}

class DefaultCrimeHolder(private val binding: ListItemCrimeDefaultBinding) : CrimeHolder(binding) {
    override fun bind(crime: Crime) {
        binding.crimeTitle.text = crime.title
        binding.crimeDate.text = formatDate(crime.date)
        binding.root.setOnClickListener {
            Snackbar.make(binding.root, "${crime.title} clicked!", Snackbar.LENGTH_SHORT).show()
        }
    }
}

class UrgentCrimeHolder(private val binding: ListItemCrimeUrgentBinding) : CrimeHolder(binding) {
    override fun bind(crime: Crime) {
        binding.crimeTitle.text = crime.title
        binding.crimeDate.text = formatDate(crime.date)
        binding.root.setOnClickListener {
            Snackbar.make(binding.root, "${crime.title} clicked!", Snackbar.LENGTH_SHORT).show()
        }
        binding.contactPoliceButton.setOnClickListener {
            Snackbar.make(binding.root, "Contacting police for ${crime.title}!", Snackbar.LENGTH_SHORT).show()
        }
    }
}