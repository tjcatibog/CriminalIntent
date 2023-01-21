package net.catibog.android.criminalintent.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import net.catibog.android.criminalintent.databinding.ListItemCrimeDefaultBinding
import net.catibog.android.criminalintent.databinding.ListItemCrimeUrgentBinding
import net.catibog.android.criminalintent.models.Crime
import java.util.*

class CrimeListAdapter(
    private val crimes: List<Crime>,
    private val onCrimeClicked: (crimeId: UUID) -> Unit
): RecyclerView.Adapter<CrimeHolder>() {
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
        holder.bind(crimes[position], onCrimeClicked)
    }

    override fun getItemCount(): Int = crimes.size

    override fun getItemViewType(position: Int): Int {
        return if (crimes[position].requiresPolice) 1 else 0
    }
}