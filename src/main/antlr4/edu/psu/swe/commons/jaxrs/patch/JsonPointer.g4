grammar JsonPointer;

jsonPointer: ('/' (arrayIndex | referenceToken))+ endOfArray?;

referenceToken: reference=ReferenceToken;
arrayIndex: index=ArrayIndex;
endOfArray: end=EndOfArray;

EndOfArray: '/-';
ArrayIndex: '0'
          | [1-9][0-9]*;
ReferenceToken: (UnescapedCharacter | EscapedCharacter)+;
UnescapedCharacter: ~[/~];
EscapedCharacter: '~' [01]; // '~0' = '~'; '~1' = '/'