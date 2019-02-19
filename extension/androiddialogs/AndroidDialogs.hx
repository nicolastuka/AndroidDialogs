package extension.androiddialogs;

#if (android && openfl)
	import lime.system.JNI;
#end

class AndroidDialogs {

	//Constants for duration Toast
	public static var LENGTH_SHORT 	= 1;
	public static var LENGTH_LONG	= 2;

	//Atención de recepción
	public static var objHaxe:CallbackHandler;

	//JNI signatures
	private static var __ShowToast:String->Int->Void = function(message:String, duration:Int):Void{};
	private static var __ShowAlertDialog:String->String->String->String->Dynamic->Void = function(title:String, message:String, confirmtext:String, canceltext:String, hxo:CallbackHandler):Void{};
	private static var __ShowAlertSelectOption:String->Dynamic->Dynamic->Void = function(title:String, listItems:Array<String>, hxo:CallbackHandler):Void{};
	private static var __ShowAlertMultipleSelectOption:String->Dynamic->Dynamic->Void = function(title:String, listItems:Array<String>, hxo:CallbackHandler):Void{};
	
	//Show Toast, set message and duration with constants
	public static function ShowToast(message:String, duration:Int):Void
	{
		if (duration < 1 && duration > 2)
		{
			duration = 2;
			trace("LENGTH_SHORT:1 - LENGTH_LONG:2 <<<ONLY>>>");
		}
		__ShowToast = JNI.createStaticMethod("org/haxe/extension/AndroidDialogs", "ShowToast", "(Ljava/lang/String;I)V");
		__ShowToast(message, duration);
	}

	//Show Alert Dialog, first setup dialog and...
	public static function ShowAlertDialog(title:String = "Title", message:String  = "Message", confirmtext:String  = "Confirm Button Text", canceltext:String  = "Cancel Button Text"):Void
	{
		objHaxe = new CallbackHandler();
		__ShowAlertDialog = JNI.createStaticMethod("org/haxe/extension/AndroidDialogs", "ShowAlertDialog", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lorg/haxe/lime/HaxeObject;)V");
		__ShowAlertDialog(title, message, confirmtext, canceltext, objHaxe);
	}

	//Show Dialog Single Select Options
	public static function ShowAlertSelectOption(title:String = "Title", listItems:Array<String>):Void
	{
		objHaxe = new CallbackHandler();
		__ShowAlertSelectOption = JNI.createStaticMethod("org/haxe/extension/AndroidDialogs", "ShowDialogSelectSimpleRadio", "(Ljava/lang/String;[Ljava/lang/String;Lorg/haxe/lime/HaxeObject;)V");
		__ShowAlertSelectOption(title, listItems, objHaxe);
	}

	//Show Dialog Multiple Select Options
	public static function ShowAlertMultipleSelectOption(title:String = "Title", listItems:Array<String>):Void
	{
		objHaxe = new CallbackHandler();
		__ShowAlertMultipleSelectOption = JNI.createStaticMethod("org/haxe/extension/AndroidDialogs", "ShowDialogSelectMultiple", "(Ljava/lang/String;[Ljava/lang/String;Lorg/haxe/lime/HaxeObject;)V");
		__ShowAlertMultipleSelectOption(title, listItems, objHaxe);
	}
	
}

private class CallbackHandler
{
	public var answerDialog:String;
	public var answerOptionSelected:String;
	public var answersOptionsMultipleSelected:Array<String>;
    public function new(){}
    public function onMessageReceived(msg:String):Void{ answerDialog = msg; }
    public function onOptionSelected(msg:String):Void{ answerOptionSelected = msg; }
    public function onOptionMultipleSelected(msg:Dynamic):Void{ answersOptionsMultipleSelected = msg; }
}
