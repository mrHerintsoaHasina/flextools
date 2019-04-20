# flextools
An Android Library which uses the FlexboxLayout https://github.com/google/flexbox-layout

## Installation
Add to your app `build.gradle` :
````
dependencies {
    implementation 'com.hopenlib.library:flextools:1.0.0'
}
````

## Version 1.0.0
- contains the class FlexRadioGroup which extends the FlexBoxLayout.

The official RadioGroup widget doesn't support many RadioButtons in the same direction because it inherits his UI behavior from the LinearLayout ViewGroup. To have a flexible UI with many RadioButtions inside a single RadioGroup, use the FlexRadioGroup instead as shown below :

````xml
<com.hopenlib.flextools.FlexRadioGroup
        xmlns:app="http://schemas.android.com/apk/res-auto"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:checkedButton="@+id/rb_2"
        app:alignContent="space_around"
        app:alignItems="flex_start"
        app:flexWrap="wrap">

        <RadioButton
            android:id="@+id/rb_1"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="RB 1" />

        <RadioButton
            android:id="@+id/rb_2"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:checked="true" 
            android:text="RB 2" />

        <RadioButton
            android:id="@+id/rb_3"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content" 
            android:text="RB 3" />

        <RadioButton
            android:id="@+id/rb_4"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content" 
            android:text="RB 4" />

        <RadioButton
            android:id="@+id/rb_5"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content" 
            android:text="RB 5" />

        <RadioButton
            android:id="@+id/rb_6"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content" 
            android:text="RB 6" />
            
        <RadioButton
            android:id="@+id/rb_7"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="RB 7" />

        <RadioButton
            android:id="@+id/rb_8"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="RB 8" />

    </com.hopenlib.flextools.FlexRadioGroup>
````

Use the custom attribute `checkedButton` to specify the RadioButton's id which is checked by default. Don't forget to set the `android:checked` attribute as `true`for that RadioButton.

You can see more details about the FlexboxLayout UI params (alignContent, alignItems, flexWrap, ...) to customize the appearence of the FlexRadioGroup and his content.

![FlexRadioGroup in action](/assets/screenshot.png)
