package com.antonioladeia.cadernetamaconica.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.antonioladeia.cadernetamaconica.CadernetaMaconicaApplication
import com.antonioladeia.cadernetamaconica.ui.home.HomeViewModel
import com.antonioladeia.cadernetamaconica.ui.session.SessionDetailsViewModel
import com.antonioladeia.cadernetamaconica.ui.session.SessionEditViewModel
import com.antonioladeia.cadernetamaconica.ui.session.SessionEntryViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            SessionEditViewModel(
                this.createSavedStateHandle(),
                cadernetaApplication().container.sessionRepository
            )
        }
        initializer {
            SessionEntryViewModel(cadernetaApplication().container.sessionRepository)
        }

        initializer {
            SessionDetailsViewModel(
                this.createSavedStateHandle(),
                cadernetaApplication().container.sessionRepository
            )
        }

         initializer {
            HomeViewModel(cadernetaApplication().container.sessionRepository)
        }
    }
}

fun CreationExtras.cadernetaApplication(): CadernetaMaconicaApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as CadernetaMaconicaApplication)
