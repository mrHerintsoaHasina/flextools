package io.github.mrherintsoahasina.flextools;

import android.content.Context;
import android.content.res.TypedArray;

import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.google.android.flexbox.FlexboxLayout;

import androidx.annotation.IdRes;

/**
 * Created by mrHerintsoaHasina on 24/05/17.
 */
public class FlexRadioGroup extends FlexboxLayout {

    // holds the checked id; the selection is empty by default
    private int mCheckedId = -1;
    // tracks children radio buttons checked state
    private CompoundButton.OnCheckedChangeListener mChildOnCheckedChangeListener;
    // when true, mOnCheckedChangeListener discards events
    private boolean mProtectFromCheckedChange = false;
    private OnCheckedChangeListener mOnCheckedChangeListener;
    private PassThroughHierarchyChangeListener mPassThroughListener;

    public FlexRadioGroup(Context context) {
        super(context);
        init();
    }

    public FlexRadioGroup(Context context, AttributeSet attrs) {
        super(context, attrs);

        // retrieve selected radio button as requested by the user in the
        // XML layout file
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.FlexRadioGroup);

        int value = attributes.getResourceId(R.styleable.FlexRadioGroup_flexCheckedButton, View.NO_ID);
        if (value != View.NO_ID) {
            mCheckedId = value;
        }

        attributes.recycle();
        init();
    }

    private void init() {
        mChildOnCheckedChangeListener = new CheckedStateTracker();
        mPassThroughListener = new PassThroughHierarchyChangeListener();
        super.setOnHierarchyChangeListener(mPassThroughListener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setOnHierarchyChangeListener(ViewGroup.OnHierarchyChangeListener listener) {
        // the user listener is delegated to our pass-through listener
        mPassThroughListener.mOnHierarchyChangeListener = listener;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        // checks the appropriate radio button as requested in the XML file
        if (mCheckedId != -1) {
            mProtectFromCheckedChange = true;
            setCheckedStateForView(mCheckedId, true);
            mProtectFromCheckedChange = false;
            setCheckedId(mCheckedId);
        }
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        if (child instanceof RadioButton) {
            final RadioButton button = (RadioButton) child;
            if (button.isChecked()) {
                mProtectFromCheckedChange = true;
                if (mCheckedId != -1) {
                    setCheckedStateForView(mCheckedId, false);
                }
                mProtectFromCheckedChange = false;
                setCheckedId(button.getId());
            }
        }

        super.addView(child, index, params);
    }

    /**
     * <p>Sets the selection to the radio button whose identifier is passed in
     * parameter. Using -1 as the selection identifier clears the selection;
     * such an operation is equivalent to invoking {@link #clearCheck()}.</p>
     *
     * @param id the unique id of the radio button to select in this group
     * @see #getCheckedRadioButtonId()
     * @see #clearCheck()
     */
    public void check(@IdRes int id) {
        // don't even bother
        if (id != -1 && (id == mCheckedId)) {
            return;
        }

        if (mCheckedId != -1) {
            setCheckedStateForView(mCheckedId, false);
        }

        if (id != -1) {
            setCheckedStateForView(id, true);
        }

        setCheckedId(id);
    }

    private void setCheckedStateForView(int viewId, boolean checked) {
        View checkedView = findViewById(viewId);
        if (checkedView instanceof RadioButton) {
            ((RadioButton) checkedView).setChecked(checked);
        }
    }

    private void setCheckedId(@IdRes int id) {
        mCheckedId = id;
        if (mOnCheckedChangeListener != null) {
            mOnCheckedChangeListener.onCheckedChanged(this, mCheckedId);
        }
    }

    /**
     * <p>Returns the identifier of the selected radio button in this group.
     * Upon empty selection, the returned value is -1.</p>
     *
     * @return the unique id of the selected radio button in this group
     * @attr ref R.styleable#FlexRadioGroup_flexCheckedButton
     * @see #check(int)
     * @see #clearCheck()
     */
    @IdRes
    public int getCheckedRadioButtonId() {
        return mCheckedId;
    }

    /**
     * <p>Clears the selection. When the selection is cleared, no radio button
     * in this group is selected and {@link #getCheckedRadioButtonId()} returns
     * null.</p>
     *
     * @see #check(int)
     * @see #getCheckedRadioButtonId()
     */
    public void clearCheck() {
        check(-1);
    }

    /**
     * <p>Register a callback to be invoked when the checked radio button
     * changes in this group.</p>
     *
     * @param listener the callback to call on checked state change
     */
    public void setOnCheckedChangeListener(OnCheckedChangeListener listener) {
        mOnCheckedChangeListener = listener;
    }

    public interface OnCheckedChangeListener {
        /**
         * <p>Called when the checked radio button has changed. When the
         * selection is cleared, checkedId is -1.</p>
         *
         * @param group     the group in which the checked radio button has changed
         * @param checkedId the unique identifier of the newly checked radio button
         */
        void onCheckedChanged(FlexRadioGroup group, @IdRes int checkedId);
    }

    private class CheckedStateTracker implements CompoundButton.OnCheckedChangeListener {
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            // prevents from infinite recursion
            if (mProtectFromCheckedChange) {
                return;
            }

            mProtectFromCheckedChange = true;
            if (mCheckedId != -1) {
                setCheckedStateForView(mCheckedId, false);
            }
            mProtectFromCheckedChange = false;

            int id = buttonView.getId();
            setCheckedId(id);
        }
    }

    /**
     * <p>A pass-through listener acts upon the events and dispatches them
     * to another listener. This allows the table layout to set its own internal
     * hierarchy change listener without preventing the user to setup his.</p>
     */
    private class PassThroughHierarchyChangeListener implements
            ViewGroup.OnHierarchyChangeListener {
        private ViewGroup.OnHierarchyChangeListener mOnHierarchyChangeListener;

        /**
         * {@inheritDoc}
         */
        public void onChildViewAdded(View parent, View child) {
            if (parent == FlexRadioGroup.this && child instanceof RadioButton) {
                int id = child.getId();
                // generates an id if it's missing
                if (id == View.NO_ID) {
                    id = View.generateViewId();
                    child.setId(id);
                }

                ((RadioButton) child).setOnCheckedChangeListener(
                        mChildOnCheckedChangeListener);
            }

            if (mOnHierarchyChangeListener != null) {
                mOnHierarchyChangeListener.onChildViewAdded(parent, child);
            }
        }

        /**
         * {@inheritDoc}
         */
        public void onChildViewRemoved(View parent, View child) {
            if (parent == FlexRadioGroup.this && child instanceof RadioButton) {
                ((RadioButton) child).setOnCheckedChangeListener(null);
            }

            if (mOnHierarchyChangeListener != null) {
                mOnHierarchyChangeListener.onChildViewRemoved(parent, child);
            }
        }
    }

}
