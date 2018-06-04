package archtree

import android.os.Bundle
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

    var title: String? = null
        private set
    var bundle: Bundle? = null
        private set
    var layer: ArchTreeLayer<ViewModel>? = null
        private set

    fun setLayoutId(layoutId: Int): Builder {
        this.layoutId = layoutId
        return this as Builder
    }

    @JvmOverloads
    fun setViewModel(viewModelClass: Class<ViewModel>, bindingKey: Int = -1, skipViewModelInit: Boolean = false): Builder {
        this.viewModelClass = viewModelClass
        this.bindingKey = bindingKey
        this.skipViewModelInit = skipViewModelInit
        return this as Builder
    }

    fun setTitle(title: String): Builder {
        this.title = title
        return this as Builder
    }

    fun setBundle(bundle: Bundle): Builder {
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
    protected fun internalBuild(layer: ArchTreeLayer<ViewModel>) {
        this.layer = layer

        if (layoutId == -1) {
            throw RuntimeException("Did you forget to set the layout for this component?")
        }
    }
}