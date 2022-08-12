package com.example.androidherexamen.quoteOfTheDay

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.androidherexamen.R
import com.example.androidherexamen.databinding.FragmentDashboardBinding
import com.example.androidherexamen.databinding.FragmentQuoteOfTheDayBinding

class QuoteOfTheDayFragment : Fragment() {

    private lateinit var viewModel : QuoteOfTheDayViewModel

    private lateinit var binding : FragmentQuoteOfTheDayBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Inflate view + instance van binding klasse
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_quote_of_the_day, container, false)

        viewModel = ViewModelProvider(this).get(QuoteOfTheDayViewModel::class.java)

        return binding.root
    }
}