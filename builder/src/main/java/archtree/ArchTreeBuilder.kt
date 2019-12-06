package archtree

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.annotation.MenuRes
import androidx.databinding.DataBindingComponent
import archtree.viewmodel.BaseViewModel

@Suppress("UNCHECKED_CAST")
abstract class ArchTreeBuilder<ViewModel : BaseViewModel, out Builder> {

    var layoutId: Int = -1
        private set

    var viewModelClass: Class<ViewModel>? = null
        private set
    var bindingKey = -1
        private set
    var viewModelInitMode: ViewModelInitMode = ViewModelInitMode.NON_FORCE_INIT
        private set

    var menuId: Int? = null
        private set

    var dataBindingComponent: DataBindingComponent? = null
        private set
    var dataBindingComponentBindingKey: Int = -1
        private set

    var lifecycleOwnerBindingKey: Int = -1
        private set

    var componentLayer: ComponentLayer<ViewModel>? = null
        private set

    var toolbarViewId: Int? = null
        private set
    var toolbarTitle: String? = null
        private set
    var toolbarIcon: Int? = null
        private set
    var displayHomeAsUpEnabled: Boolean = false
        private set
    var parentHasToolbarView: Boolean = false
        private set

    /**
     * This method defines a toolbar for the given component.
     *
     * @param viewId View id of the toolbar for this component.
     * @param title Title of the toolbar for this component.
     * @param displayHomeAsUpEnabled
     * @param parentHasToolbarView If the actual toolbar is not hosted by the current component
     * and is hosted by the parent component/view, set parentHasToolbarView to true.
     * @param icon Icon of the toolbar for this component.
     */
    @JvmOverloads
    open fun setToolbar(@IdRes viewId: Int? = null, title: String? = null,
                        displayHomeAsUpEnabled: Boolean = false, parentHasToolbarView: Boolean = false,
                        @DrawableRes icon: Int? = null): Builder {
        this.toolbarViewId = viewId
        this.toolbarTitle = title
        this.displayHomeAsUpEnabled = displayHomeAsUpEnabled
        this.parentHasToolbarView = parentHasToolbarView
        this.toolbarIcon = icon
        return this as Builder
    }

    /**
     * This method defines the layout id that will be used by this component.
     *
     * @param layoutId Layout id will be used by this component.
     */
    open fun setLayoutId(@LayoutRes layoutId: Int): Builder {
        this.layoutId = layoutId
        return this as Builder
    }

    /**
     * This method defines the viewmodel for this component.
     *
     * @param viewModelClass Class for the related viewmodel.
     * @param bindingKey If the viewmodel should be attached to the related component layout, define
     * the relevant binding key here.
     * @param viewModelInitMode This value defines the initialise mode for this viewmodel. This can
     * be used to always force to re-initialise the viewmodel base parameters (in case of
     * re-creating the component). But it can also be used to define the init mode as custom so that
     * you can set the exact BaseViewModel.init() yourself.
     */
    @JvmOverloads
    open fun setViewModel(viewModelClass: Class<ViewModel>, bindingKey: Int = -1,
                          viewModelInitMode: ViewModelInitMode = ViewModelInitMode.NON_FORCE_INIT): Builder {
        this.viewModelClass = viewModelClass
        this.bindingKey = bindingKey
        this.viewModelInitMode = viewModelInitMode
        return this as Builder
    }

    /**
     * This method attaches a databinding component to this component.
     *
     * @param dataBindingComponent Instance of a DataBindingComponent that will be attached
     * @param bindingKey If the DataBindingComponent should be attached to the related component
     * layout, define the relevant binding key here.
     */
    @SuppressLint("LogNotTimber")
    @JvmOverloads
    open fun setDatabindingComponent(dataBindingComponent: Any?, bindingKey: Int = -1): Builder {
        dataBindingComponentBindingKey = bindingKey

        try {
            this.dataBindingComponent = dataBindingComponent as? DataBindingComponent?
        } catch (ignore: ClassCastException) {
            //do nothing
        }

        return this as Builder
    }

    /**
     * This method attaches a LifecycleOwner to the component layout.
     *
     * @param bindingKey Binding key that will be used to attach the LifecycleOwner to the layout.
     */
    open fun setLifecycleOwner(bindingKey: Int): Builder {
        lifecycleOwnerBindingKey = bindingKey
        return this as Builder
    }

    /**
     * This method defines a menu for the component.
     *
     * @param menuId Menu resource id that will be used to initialise the menu for this component.
     */
    open fun setMenu(@MenuRes menuId: Int): Builder {
        this.menuId = menuId
        return this as Builder
    }

    /**
     * Internal method used to execute some common operations if the build() method of
     * ActivityBuilder/FragmentBuilder is called.
     *
     * @param layer custom componentLayer which can be used to execute certain operation for the predefined
     * lifecycle methods.
     * @since 1.0.0
     * @see ComponentLayer
     */
    protected open fun internalBuild(layer: ComponentLayer<ViewModel>) {
        this.componentLayer = layer
    }
}