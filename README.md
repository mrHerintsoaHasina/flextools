# flextools
An Android Library which uses the FlexboxLayout https://github.com/google/flexbox-layout

## Installation
Add to your app `build.gradle` :
````
dependencies {
    implementation 'io.github.mrherintsoahasina:flextools:1.0.3'
}
````
**Starting from 1.0.3, the groupId is changed to `io.github.mrherintsoahasina` due to migration from JCenter to Maven Central.**

## Version 1.0.0
- contains the class FlexRadioGroup which extends the FlexBoxLayout.

The official RadioGroup widget doesn't support many RadioButtons in the same direction because it inherits his UI behavior from the LinearLayout ViewGroup. To have a flexible UI with many RadioButtions inside a single RadioGroup, use the FlexRadioGroup instead as shown below :

````xml
<io.github.mrherintsoahasina.flextools.FlexRadioGroup
        xmlns:app="http://schemas.android.com/apk/res-auto"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:flexCheckedButton="@+id/rb_2"
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

</io.github.mrherintsoahasina.flextools.FlexRadioGroup>
````

Use the custom attribute `flexCheckedButton` to specify the RadioButton's id which is checked by default. Don't forget to set the `android:checked` attribute as `true`for that RadioButton.

You can see more details about the FlexboxLayout UI params (alignContent, alignItems, flexWrap, ...) to customize the appearence of the FlexRadioGroup and his content.

![FlexRadioGroup in action](/assets/screenshot.png)

## License
Apache 2.0. See the [LICENSE](https://github.com/mrHerintsoaHasina/flextools/blob/master/LICENSE.md) file for details.
```
   Copyright 2019 Hasina R.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.`
```
