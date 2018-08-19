package archtree

import android.annotation.SuppressLint
import android.databinding.DataBindingComponent
import android.os.Bundle
import android.util.Log
import archtree.viewmodel.BaseViewModel

@Suppress("UNCHECKED_CAST")
abstract class ArchTreeBuilder<ViewModel : BaseViewModel, out Builder> {

    var layoutId: Int = -1
        private set

    var viewModelClass: Class<ViewModel>? = null
        private set
    var bindingKey = -1
        private set
    var skipViewModelInit: Boolean = false
        private set
    var dataBindingComponent: DataBindingComponent? = null
        private set

    var title: String? = null
        private set
    var bundle: Bundle? = null
        private set
    var layer: ArchTreeLayer<ViewModel>? = null
        private set

    open fun setLayoutId(layoutId: Int): Builder {
        this.layoutId = layoutId
        return this as Builder
    }

    @SuppressLint("LogNotTimber")
    @JvmOverloads
    open fun setViewModel(viewModelClass: Class<ViewModel>, bindingKey: Int = -1,
                     dataBindingComponent: Any? = null, skipViewModelInit: Boolean = false): Builder {
        this.viewModelClass = viewModelClass
        this.bindingKey = bindingKey
        this.skipViewModelInit = skipViewModelInit

        try {
            this.dataBindingComponent = dataBindingComponent as? DataBindingComponent?
        } catch (e: ClassCastException) {
            Log.e(ArchTreeBuilder::class.java.name, e.message)
        }

        return this as Builder
    }

    open fun setTitle(title: String): Builder {
        this.title = title
        return this as Builder
    }

    open fun setBundle(bundle: Bundle): Builder {
        this.bundle = bundle
        return this as Builder
    }

    /**
     * Internal method used to execute some common operations if the build() method of
     * ActivityBuilder/FragmentBuilder is called.
     *
     * @param layer custom layer which can be used to execute certain operation for the predefined
     * lifecycle methods.
     * @throws RuntimeException throws this exception if the layout for this component is not set.
     * @since 1.0.0
     * @see ArchTreeLayer
     */
    @Throws(RuntimeException::class)
    protected open fun internalBuild(layer: ArchTreeLayer<ViewModel>) {
        this.layer = layer

        if (layoutId == -1) {
            throw RuntimeException("Did you forget to set the layout for this component?")
        }
    }
}