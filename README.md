# AndroidDialogs
Helper to implements UI Dialogs and Toast on Android.

# Installation

· haxelib install androiddialogs

### Set up haxelib in .XML

``<haxelib name="androiddialogs" />``

### Simple Example Use Extension (Toast):

```haxe

import extension.androiddialogs.AndroidDialogs;

class SimpleExampleToast
{
    function AnyFuntion():Void
    {
        AndroidDialogs.ShowToast("Hola putin", AndroidDialogs.LENGTH_LONG);//or LENGTH_SHORT duration
    }
}
```

### Simple Example Use Extension (Dialog Confirmation):

```haxe

import extension.androiddialogs.AndroidDialogs;

class SimpleExampleDialogConfirmation
{
    function AnyFuntion():Void
    {
        /*
            param: Title dialog.
            param: Message dialog.
            param: Caption confirm button dialog.
            param: Caption cancel button dialog.
        */
        AndroidDialogs.ShowAlertDialog("Title", "Message", "Confirm", "Cancel");
    }

    function OtherFunctionGetResult():Void
    {
        //get string user selection
        trace(AndroidDialogs.objHaxe.answerDialog);
    }
}

```

### Simple Example Use Extension (Dialog List Options with Radio Button):

```haxe

import extension.androiddialogs.AndroidDialogs;

class SimpleExampleDialogRadioButton
{
    function AnyFuntion():Void
    {
        var names_players:Array<String> = new Array<String>();
        names_players.push("Pity Martinez");
        names_players.push("Jonathan Maidana");
        names_players.push("Enzo Perez");
        names_players.push("Ignacio Scoco");
        names_players.push("Leonardo Ponzio");
        /*
            param: Title dialog.
            param: Message dialog.
            param: Caption confirm button dialog.
            param: Caption cancel button dialog.
        */
        AndroidDialogs.ShowAlertSelectOption("Title", names_players);
    }

    function OtherFunctionGetResult():Void
    {
        //get string user selection
        trace(AndroidDialogs.objHaxe.answerOptionSelected);
    }
}

```

### Simple Example Use Extension (Dialog List Options with Multiple Selection):

```haxe

import extension.androiddialogs.AndroidDialogs;

class SimpleExampleDialogMultipleSelection
{
    function AnyFuntion():Void
    {
        var names_players:Array<String> = new Array<String>();
        names_players.push("Pity Martinez");
        names_players.push("Jonathan Maidana");
        names_players.push("Enzo Perez");
        names_players.push("Ignacio Scoco");
        names_players.push("Leonardo Ponzio");
        /*
            param: Title dialog.
            param: Message dialog.
            param: Caption confirm button dialog.
            param: Caption cancel button dialog.
        */
        AndroidDialogs.ShowAlertMultipleSelectOption("Title", names_players);
    }

    function OtherFunctionGetResult():Void
    {
        //get string user selection
        .
        .
        .
    }
}

```


### License

The MIT License (MIT) - LICENSE.md

Copyright © 2018 MilköGames (http://www.milkogames.xyz)

Author: Nicolás Capel
